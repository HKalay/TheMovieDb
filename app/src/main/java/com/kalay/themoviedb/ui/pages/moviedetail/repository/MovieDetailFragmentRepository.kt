package com.kalay.themoviedb.ui.pages.moviedetail.repository

import com.kalay.core.extensions.*
import com.kalay.core.ioc.scopes.FragmentScope
import com.kalay.core.networking.DataFetchResult
import com.kalay.core.networking.Scheduler
import com.kalay.data.response.MovieDetailResponse
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.BehaviorSubject
import timber.log.Timber


@FragmentScope
class MovieDetailFragmentRepository(
    private val remote: MovieDetailFragmentContract.Remote,
    private val scheduler: Scheduler,
    private val compositeDisposable: CompositeDisposable
) : MovieDetailFragmentContract.Repository {

    override val movieDetailPageDataResult =
        BehaviorSubject.create<DataFetchResult<MovieDetailResponse>>()

    override fun getMovieDetailPageData(movieId: Int) {
        movieDetailPageDataResult.loading(true)
        remote.getMovieDetailPageData(movieId)
            .performOnBackOutOnMain(scheduler)
            .subscribe(
                {
                    movieDetailPageDataResult.success(it)
                },
                { error ->
                    handleError(movieDetailPageDataResult, error)
                })
            .addTo(compositeDisposable)
    }


    override fun <T> handleError(result: BehaviorSubject<DataFetchResult<T>>, error: Throwable) {
        result.failed(error)
        Timber.e(error.localizedMessage)
    }
}