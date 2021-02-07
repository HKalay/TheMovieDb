package com.kalay.component.ui.moviecard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.afollestad.rxkprefs.RxkPrefs
import com.afollestad.rxkprefs.rxjava.observe
import com.afollestad.rxkprefs.rxkPrefs
import com.google.android.material.card.MaterialCardView
import javax.inject.Inject
import com.kalay.component.R
import com.kalay.core.enums.SharedPref
import com.kalay.core.extensions.addTo
import com.kalay.core.extensions.loadImage
import com.kalay.core.ui.recyclerview.DisplayItem
import com.kalay.core.ui.recyclerview.ViewHolder
import com.kalay.core.ui.recyclerview.ViewHolderBinder
import com.kalay.core.ui.recyclerview.ViewHolderFactory
import io.reactivex.disposables.CompositeDisposable


class MovieCardViewHolder(val view: View) : ViewHolder<MovieCardDTO>(view) {

    private var rootViewMovieCard: MaterialCardView =
        view.findViewById(R.id.rootViewItemMovieCard)
    private var imgMovieCardPhoto: AppCompatImageView =
        view.findViewById(R.id.imgItemMovieCardPhoto)
    private var tvMovieCardTitle: AppCompatTextView =
        view.findViewById(R.id.tvItemMovieCardTitle)
    private var tvMovieCardVoteAverage: AppCompatTextView =
        view.findViewById(R.id.tvItemMovieCardVoteAverage)
    private var imgMovieCardSave: AppCompatImageView =
        view.findViewById(R.id.imgItemMovieCardSave)

    private lateinit var myPrefs: RxkPrefs
    private val compositeDisposable = CompositeDisposable()

    override fun bind(item: MovieCardDTO) {

        loadImageSavedStatus(item.movieCardIsSaved)

        val results = item.results
        results?.poster_path?.let { _posterPath -> imgMovieCardPhoto.loadImage(_posterPath) }
        tvMovieCardVoteAverage.text = "Vote Average:" + results?.vote_average
        tvMovieCardTitle.text = results?.title

        rootViewMovieCard.setOnClickListener {
            itemViewClickListener?.invoke(it, item, adapterPosition)
        }

        imgMovieCardSave.setOnClickListener {
            itemViewClickListener?.invoke(it, item, adapterPosition)
            loadImageSavedStatus(item.movieCardIsSaved)
        }

        myPrefs = rxkPrefs(view.context, SharedPref.PREF_KEY.value)
        val mySaveMovieStatus = myPrefs.boolean(item.results?.id.toString(), false)
        mySaveMovieStatus.observe().subscribe {
            it.let { _saveDeleteStatus ->
               if (_saveDeleteStatus){
                   imgMovieCardSave.loadImage(R.drawable.ic_saved)
               }else{
                   imgMovieCardSave.loadImage(R.drawable.ic_save)
               }
                item.movieCardIsSaved = _saveDeleteStatus
            }
        }.addTo(compositeDisposable)

    }

    class HolderFactory @Inject constructor() : ViewHolderFactory {
        override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder =
            MovieCardViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_movie_card,
                    parent,
                    false
                )
            )
    }

    class BinderFactory @Inject constructor() : ViewHolderBinder {
        override fun bind(holder: RecyclerView.ViewHolder, item: DisplayItem) {
            (holder as MovieCardViewHolder).bind(item as MovieCardDTO)
        }
    }

    private fun loadImageSavedStatus(isSaved: Boolean) {
        if (isSaved) {
            imgMovieCardSave.loadImage(R.drawable.ic_saved)
        } else {
            imgMovieCardSave.loadImage(R.drawable.ic_save)
        }
    }
}
