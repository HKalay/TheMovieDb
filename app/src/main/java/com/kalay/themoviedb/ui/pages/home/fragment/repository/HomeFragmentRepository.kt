package com.kalay.themoviedb.ui.pages.home.fragment.repository

import com.kalay.core.extensions.*
import com.kalay.core.ioc.scopes.FragmentScope
import com.kalay.core.networking.DataFetchResult
import com.kalay.core.networking.Scheduler
import com.kalay.data.response.HomePageResponse
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.BehaviorSubject
import timber.log.Timber

@FragmentScope
class HomeFragmentRepository(
    private val remote: HomeFragmentContract.Remote,
    private val scheduler: Scheduler,
    private val compositeDisposable: CompositeDisposable
) : HomeFragmentContract.Repository {

    override val upComingMovieDataResult =
        BehaviorSubject.create<DataFetchResult<HomePageResponse>>()

    override fun getUpComingMovieData() {
        upComingMovieDataResult.loading(true)
        remote.getUpComingMovieData()
            .performOnBackOutOnMain(scheduler)
            .subscribe(
                {
                    upComingMovieDataResult.success(it)
                },
                { error ->
                    handleError(upComingMovieDataResult, error)
                })
            .addTo(compositeDisposable)
    }


    override val topRatedMovieDataResult =
        BehaviorSubject.create<DataFetchResult<HomePageResponse>>()

    override fun getTopRatedMovieData() {
        topRatedMovieDataResult.loading(true)
        remote.getTopRatedMovieData()
            .performOnBackOutOnMain(scheduler)
            .subscribe(
                {
                    topRatedMovieDataResult.success(it)
                },
                { error ->
                    handleError(topRatedMovieDataResult, error)
                })
            .addTo(compositeDisposable)
    }


    override val popularMovieDataResult =
        BehaviorSubject.create<DataFetchResult<HomePageResponse>>()

    override fun getPopularMovieData() {
        popularMovieDataResult.loading(true)
        remote.getPopularMovieData()
            .performOnBackOutOnMain(scheduler)
            .subscribe(
                {
                    popularMovieDataResult.success(it)
                },
                { error ->
                    handleError(popularMovieDataResult, error)
                })
            .addTo(compositeDisposable)
    }

    override fun <T> handleError(result: BehaviorSubject<DataFetchResult<T>>, error: Throwable) {
        result.failed(error)
        Timber.e(error.localizedMessage)
    }
}
