package com.kalay.themoviedb.ui.pages.search.repository

import com.kalay.core.ioc.scopes.FragmentScope
import com.kalay.data.request.IHomePageApi

@FragmentScope
class SearchFragmentRemoteData(
	private val searchPageApiInterface: IHomePageApi
) : SearchFragmentContract.Remote {


}
