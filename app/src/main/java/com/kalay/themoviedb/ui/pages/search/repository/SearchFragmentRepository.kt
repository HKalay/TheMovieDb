package com.kalay.themoviedb.ui.pages.search.repository

import com.kalay.core.extensions.*
import com.kalay.core.ioc.scopes.FragmentScope
import com.kalay.core.networking.DataFetchResult
import com.kalay.core.networking.Scheduler
import com.kalay.data.response.SearchPageResponse
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.BehaviorSubject
import timber.log.Timber

@FragmentScope
class SearchFragmentRepository(
	private val remote: SearchFragmentContract.Remote,
	private val scheduler: Scheduler,
	private val compositeDisposable: CompositeDisposable
) : SearchFragmentContract.Repository {


	override val searchMovieDataResult =
		BehaviorSubject.create<DataFetchResult<SearchPageResponse>>()

	override fun getSearchMovieData(searchQuery: String) {
		searchMovieDataResult.loading(true)
		remote.getSearchMovieData(searchQuery)
			.performOnBackOutOnMain(scheduler)
			.subscribe(
				{
					searchMovieDataResult.success(it)
				},
				{ error ->
					handleError(searchMovieDataResult, error)
				})
			.addTo(compositeDisposable)
	}

	override fun <T> handleError(result: BehaviorSubject<DataFetchResult<T>>, error: Throwable) {
		result.failed(error)
		Timber.e(error.localizedMessage)
	}
}
