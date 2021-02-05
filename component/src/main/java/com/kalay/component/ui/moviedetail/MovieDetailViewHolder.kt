package com.kalay.component.ui.moviedetail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.kalay.component.R
import com.kalay.component.constant.RecyclerviewAdapterConstant
import com.kalay.component.helpers.horizontalrcycler.HorizontalRecyclerDTO
import com.kalay.component.ui.moviedetail.genres.GenresDTO
import com.kalay.core.extensions.addTo
import com.kalay.core.extensions.loadImage
import com.kalay.core.extensions.setup
import com.kalay.core.ui.recyclerview.*
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MovieDetailViewHolder(val view: View) : ViewHolder<MovieDetailDTO>(view) {

    private var imgMoviePhoto: AppCompatImageView =
        view.findViewById(R.id.imgItemMoviePhoto)
    private var tvMovieTitle: AppCompatTextView = view.findViewById(R.id.tvItemMovieTitle)
    private var tvDetailReleaseDate: AppCompatTextView =
        view.findViewById(R.id.tvItemDetailReleaseDate)
    private var tvMovieDetailVoteAverage: AppCompatTextView =
        view.findViewById(R.id.tvItemMovieDetailVoteAverage)
    private var tvMovieDetailOverview: AppCompatTextView =
        view.findViewById(R.id.tvItemMovieDetailOverview)

    private var rvMovieDetailGenres: RecyclerView = view.findViewById(R.id.rvItemMovieDetailGenres)

    lateinit var compositeDisposable: CompositeDisposable

    override fun bind(item: MovieDetailDTO) {
        compositeDisposable = CompositeDisposable()
        item.movieDetail?.backdrop_path.let { _backPath ->
            imgMoviePhoto.loadImage(_backPath.toString())
        }
        tvMovieTitle.text = item.movieDetail?.title
        tvDetailReleaseDate.text = item.movieDetail?.release_date

        val adapterRecyclerView = RecyclerViewAdapter(
            itemComperator = DefaultDisplayItemComperator(),
            viewBinderFactoryMap = RecyclerviewAdapterConstant().binderFactoryMap,
            viewHolderFactoryMap = RecyclerviewAdapterConstant().holderFactoryMap
        )

        rvMovieDetailGenres.setup(
            adapter = adapterRecyclerView
        )

        val genres = arrayListOf<GenresDTO>()
        Observable.fromIterable(item.movieDetail?.genres).subscribe { _genres->
            genres.add(GenresDTO(_genres))
        }.addTo(compositeDisposable)


        adapterRecyclerView.updateAllItems( listOf(HorizontalRecyclerDTO(genres, false)))

        tvMovieDetailVoteAverage.text = item.movieDetail?.vote_average.toString()
        tvMovieDetailOverview.text = item.movieDetail?.overview
    }


    class HolderFactory @Inject constructor() : ViewHolderFactory {
        override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder =
            MovieDetailViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_movie_detail,
                    parent,
                    false
                )
            )
    }

    class BinderFactory @Inject constructor() : ViewHolderBinder {
        override fun bind(holder: RecyclerView.ViewHolder, item: DisplayItem) {
            (holder as MovieDetailViewHolder).bind(item as MovieDetailDTO)
        }
    }
}
