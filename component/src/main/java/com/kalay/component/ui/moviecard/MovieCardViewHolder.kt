package com.kalay.component.ui.moviecard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import javax.inject.Inject
import com.kalay.component.R
import com.kalay.core.extensions.loadImage
import com.kalay.core.ui.recyclerview.DisplayItem
import com.kalay.core.ui.recyclerview.ViewHolder
import com.kalay.core.ui.recyclerview.ViewHolderBinder
import com.kalay.core.ui.recyclerview.ViewHolderFactory
import io.reactivex.disposables.CompositeDisposable


/**
 * Created by Hamdullah KALAY on 3/30/2020.
 */

class MovieCardViewHolder(val view: View) : ViewHolder<MovieCardDTO>(view) {

    private var rootViewMovieCard: MaterialCardView =
        view.findViewById(R.id.rootViewItemMovieCard)
    private var imgMovieCardPhoto: AppCompatImageView =
        view.findViewById(R.id.imgItemMovieCardPhoto)
    private var tvMovieCardTitle: AppCompatTextView =
        view.findViewById(R.id.tvItemMovieCardTitle)
    private var tvMovieCardVoteAverage: AppCompatTextView =
        view.findViewById(R.id.tvItemMovieCardVoteAverage)
    private var imgNewsCardSave: AppCompatImageView =
        view.findViewById(R.id.imgItemMovieCardSave)


    override fun bind(item: MovieCardDTO) {
        val results = item.results

        results?.poster_path?.let { _posterPath -> imgMovieCardPhoto.loadImage(_posterPath) }
        tvMovieCardVoteAverage.text = "Vote Average:" + results?.vote_average
        tvMovieCardTitle.text = results?.title

        rootViewMovieCard.setOnClickListener {
            itemClickListener?.invoke(item, adapterPosition)
        }
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
}
