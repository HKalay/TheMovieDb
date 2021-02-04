package com.kalay.themoviedb.ui.pages.moviedetail

import com.kalay.component.ui.moviedetail.MovieDetailDTO
import com.kalay.core.ui.recyclerview.DisplayItem
import com.kalay.data.response.MovieDetailResponse
import io.reactivex.disposables.CompositeDisposable

class MovieDetailFragmentGetData(private val compositeDisposable: CompositeDisposable) {

    // MOVIE DATA
    fun getMovieData(items: MovieDetailResponse?): MutableList<DisplayItem> {
        val movieDetail = mutableListOf<DisplayItem>()
        items?.let {
            movieDetail.add(
                MovieDetailDTO(
                    movieDetail = items
                )
            )
        }
        return movieDetail
    }
}
