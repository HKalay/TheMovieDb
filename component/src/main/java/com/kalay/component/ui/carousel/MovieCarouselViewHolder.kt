package com.kalay.component.ui.carousel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.kalay.component.R
import com.kalay.core.extensions.loadImage
import com.kalay.core.ui.recyclerview.DisplayItem
import com.kalay.core.ui.recyclerview.ViewHolder
import com.kalay.core.ui.recyclerview.ViewHolderBinder
import com.kalay.core.ui.recyclerview.ViewHolderFactory
import javax.inject.Inject


class MovieCarouselViewHolder(val view: View) : ViewHolder<MovieCarouselDTO>(view) {

    private var rootViewItemMovieCarousel: MaterialCardView =
        view.findViewById(R.id.rootViewItemMovieCarousel)
    private var imgCarouselMoviePhoto: AppCompatImageView =
        view.findViewById(R.id.imgItemCarouselMoviePhoto)
    private var tvCarouselMovieVoteAverage: AppCompatTextView =
        view.findViewById(R.id.tvItemCarouselMovieVoteAverage)
    private var tvCarouselMovieTitle: AppCompatTextView =
        view.findViewById(R.id.tvItemCarouselMovieTitle)

    override fun bind(item: MovieCarouselDTO) {

        val results = item.results

        results?.poster_path?.let { _posterPath -> imgCarouselMoviePhoto.loadImage(_posterPath) }
        tvCarouselMovieVoteAverage.text = "Vote Average:"+ results?.vote_average
        tvCarouselMovieTitle.text = results?.title

        rootViewItemMovieCarousel.setOnClickListener {
            itemClickListener?.invoke(item, adapterPosition)
        }
    }


    class HolderFactory @Inject constructor() : ViewHolderFactory {
        override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder =
            MovieCarouselViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_carousel,
                    parent,
                    false
                )
            )
    }

    class BinderFactory @Inject constructor() : ViewHolderBinder {
        override fun bind(holder: RecyclerView.ViewHolder, item: DisplayItem) {
            (holder as MovieCarouselViewHolder).bind(item as MovieCarouselDTO)
        }
    }
}
