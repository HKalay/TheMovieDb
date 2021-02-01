package com.kalay.themoviedb.ui.pages.favorites.repository

import com.kalay.core.ioc.scopes.FragmentScope
import com.kalay.data.request.IHomePageApi

@FragmentScope
class FavoritesFragmentRemoteData(
	private val favoritesPageApiInterface: IHomePageApi
) : FavoritesFragmentContract.Remote {


}
