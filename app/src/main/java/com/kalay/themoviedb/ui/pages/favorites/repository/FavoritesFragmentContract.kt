package com.kalay.themoviedb.ui.pages.favorites.repository

import com.kalay.component.ui.moviecard.MovieCardDTO
import com.kalay.core.networking.DataFetchResult
import com.kalay.data.database.model.MovieCardDbDTO
import io.reactivex.Single
import io.reactivex.subjects.BehaviorSubject

interface FavoritesFragmentContract {

    interface Repository {

        val getLocalAllMovieDataResult: BehaviorSubject<DataFetchResult<List<MovieCardDbDTO>>>
        fun getLocalAllMovie()

        fun deleteLocalMovie(movieCardDTO: MovieCardDTO)


        fun <T> handleError(result: BehaviorSubject<DataFetchResult<T>>, error: Throwable)
    }

    interface Local {
        fun getLocalAllMovie(): Single<List<MovieCardDbDTO>>
        fun deleteLocalMovie(movieCardDTO: MovieCardDTO)
    }
}
