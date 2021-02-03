package com.kalay.themoviedb.ui.pages.search.repository

import com.kalay.core.ioc.scopes.FragmentScope
import com.kalay.data.request.ISearchPageApi
import com.kalay.data.response.SearchPageResponse
import io.reactivex.Single

@FragmentScope
class SearchFragmentRemoteData(
	private val searchPageApiInterface: ISearchPageApi
) : SearchFragmentContract.Remote {
	override fun getSearchMovieData(searchQuery: String): Single<SearchPageResponse> =
		searchPageApiInterface.getSearchMovies(searchQuery)
}
