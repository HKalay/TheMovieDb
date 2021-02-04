package com.kalay.themoviedb.ui.pages.moviedetail.repository

import com.kalay.core.networking.DataFetchResult
import com.kalay.data.response.MovieDetailResponse
import io.reactivex.Single
import io.reactivex.subjects.BehaviorSubject

interface MovieDetailFragmentContract {

    interface Repository {

        val movieDetailPageDataResult: BehaviorSubject<DataFetchResult<MovieDetailResponse>>
        fun getMovieDetailPageData(movieId: Int)

        fun <T> handleError(result: BehaviorSubject<DataFetchResult<T>>, error: Throwable)
    }

    interface Remote {
        fun getMovieDetailPageData(movieId: Int): Single<MovieDetailResponse>
    }


}