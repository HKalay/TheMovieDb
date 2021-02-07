package com.kalay.themoviedb.ui.pages.moviedetail.repository

import com.kalay.component.ui.moviecard.MovieCardDTO
import com.kalay.core.networking.DataFetchResult
import com.kalay.data.database.model.MovieCardDbDTO
import com.kalay.data.response.MovieDetailResponse
import io.reactivex.Single
import io.reactivex.subjects.BehaviorSubject

interface MovieDetailFragmentContract {

    interface Repository {

        val movieDetailPageDataResult: BehaviorSubject<DataFetchResult<MovieDetailResponse>>
        fun getMovieDetailPageData(movieId: Int)


        val getAllLocalMovieWithIdDataResult: BehaviorSubject<DataFetchResult<MovieCardDbDTO>>
        fun getAllLocalMovieWithId(movieId: Int?)

        fun insertLocalMovie(movieCardDTO: MovieCardDTO)
        fun deleteLocalMovie(movieCardDTO: MovieCardDTO)

        fun <T> handleError(result: BehaviorSubject<DataFetchResult<T>>, error: Throwable)
    }

    interface Remote {
        fun getMovieDetailPageData(movieId: Int): Single<MovieDetailResponse>
    }

    interface Local {
        fun getAllLocalMovieWithId(movieId: Int?): Single<MovieCardDbDTO>
        fun insertLocalMovie(movieCardDTO: MovieCardDTO)
        fun deleteLocalMovie(movieCardDTO: MovieCardDTO)
    }
}