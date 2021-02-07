package com.kalay.themoviedb.ui.pages.favorites.repository

import com.kalay.component.ui.moviecard.MovieCardDTO
import com.kalay.core.extensions.*
import com.kalay.core.ioc.scopes.FragmentScope
import com.kalay.core.networking.DataFetchResult
import com.kalay.core.networking.Scheduler
import com.kalay.data.database.model.MovieCardDbDTO
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.BehaviorSubject
import timber.log.Timber

@FragmentScope
class FavoritesFragmentRepository(
	private val local: FavoritesFragmentContract.Local,
	private val scheduler: Scheduler,
	private val compositeDisposable: CompositeDisposable
) : FavoritesFragmentContract.Repository {

	override val getLocalAllMovieDataResult =
		BehaviorSubject.create<DataFetchResult<List<MovieCardDbDTO>>>()

	override fun getLocalAllMovie() {
		getLocalAllMovieDataResult.loading(true)
		local.getLocalAllMovie()
			.performOnBackOutOnMain(scheduler)
			.subscribe(
				{
					getLocalAllMovieDataResult.success(it)
				},
				{ error ->
					handleError(getLocalAllMovieDataResult, error)
				})
			.addTo(compositeDisposable)
	}

	override fun deleteLocalMovie(movieCardDTO: MovieCardDTO) {
		return local.deleteLocalMovie(movieCardDTO)
	}

	override fun <T> handleError(result: BehaviorSubject<DataFetchResult<T>>, error: Throwable) {
		result.failed(error)
		Timber.e(error.localizedMessage)
	}
}
