package com.kalay.themoviedb.ui.pages.moviedetail

import com.kalay.component.ui.imagebig.ImageBigDTO
import com.kalay.core.ui.recyclerview.DisplayItem
import com.kalay.data.response.MovieDetailResponse
import io.reactivex.disposables.CompositeDisposable

class MovieDetailFragmentGetData(private val compositeDisposable: CompositeDisposable) {

    // SPOTH DATA
    fun getMovieDetailSpothData(items: MovieDetailResponse?): MutableList<DisplayItem> {
        val movieDetailSpoth = mutableListOf<DisplayItem>()
        items?.let {
            movieDetailSpoth.add(
                ImageBigDTO(
                    movieDetail = items
                )
            )
        }
        return movieDetailSpoth
    }
}
