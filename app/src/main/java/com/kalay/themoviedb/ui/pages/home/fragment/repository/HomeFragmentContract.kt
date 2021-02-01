package com.kalay.themoviedb.ui.pages.home.fragment.repository

import com.kalay.core.networking.DataFetchResult
import com.kalay.data.response.HomePageResponse
import io.reactivex.Single
import io.reactivex.subjects.BehaviorSubject


interface HomeFragmentContract {

    interface Repository {

        val upComingMovieDataResult: BehaviorSubject<DataFetchResult<HomePageResponse>>
        fun getUpComingMovieData()

        fun <T> handleError(result: BehaviorSubject<DataFetchResult<T>>, error: Throwable)
    }

    interface Remote {
        fun getHomePageData(): Single<HomePageResponse>
    }
}
