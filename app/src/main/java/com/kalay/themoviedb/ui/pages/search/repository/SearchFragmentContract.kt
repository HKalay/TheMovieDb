package com.kalay.themoviedb.ui.pages.search.repository

import com.kalay.component.ui.moviecard.MovieCardDTO
import com.kalay.core.networking.DataFetchResult
import com.kalay.data.database.model.MovieCardDbDTO
import com.kalay.data.response.SearchPageResponse
import io.reactivex.Single
import io.reactivex.subjects.BehaviorSubject

interface SearchFragmentContract {

    interface Repository {

        val searchMovieDataResult: BehaviorSubject<DataFetchResult<SearchPageResponse>>
        fun getSearchMovieData(searchQuery:String)

        fun insertLocalMovie(movieCardDTO: MovieCardDTO)
        fun deleteLocalMovie(movieCardDTO: MovieCardDTO)
        fun getAllLocalMovie()

        fun <T> handleError(result: BehaviorSubject<DataFetchResult<T>>, error: Throwable)
    }

    interface Remote {
        fun getSearchMovieData(searchQuery:String): Single<SearchPageResponse>
    }

    interface Local {
        fun insertLocalMovie(movieCardDTO: MovieCardDTO)
        fun deleteLocalMovie(movieCardDTO: MovieCardDTO)
        fun getAllLocalMovie(): Single<List<MovieCardDbDTO>>
    }
}
