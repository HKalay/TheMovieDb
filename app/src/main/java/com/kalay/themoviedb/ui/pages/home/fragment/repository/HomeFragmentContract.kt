package com.kalay.themoviedb.ui.pages.home.fragment.repository

import com.kalay.core.networking.DataFetchResult
import com.kalay.data.response.HomePageResponse
import io.reactivex.Single
import io.reactivex.subjects.BehaviorSubject


interface HomeFragmentContract {

    interface Repository {

        val upComingMovieDataResult: BehaviorSubject<DataFetchResult<HomePageResponse>>
        fun getUpComingMovieData()

        val topRatedMovieDataResult: BehaviorSubject<DataFetchResult<HomePageResponse>>
        fun getTopRatedMovieData()

        val popularMovieDataResult: BehaviorSubject<DataFetchResult<HomePageResponse>>
        fun getPopularMovieData(pageIndex:Int)


        fun <T> handleError(result: BehaviorSubject<DataFetchResult<T>>, error: Throwable)
    }

    interface Remote {
        fun getUpComingMovieData(): Single<HomePageResponse>
        fun getTopRatedMovieData(): Single<HomePageResponse>
        fun getPopularMovieData(pageIndex:Int): Single<HomePageResponse>
    }
}
