package com.kalay.themoviedb.ui.pages.home.fragment.repository

import com.kalay.core.ioc.scopes.FragmentScope
import com.kalay.data.request.IHomePageApi
import com.kalay.data.response.HomePageResponse
import io.reactivex.Single


@FragmentScope
class HomeFragmentRemoteData(
	private val homePageApiInterface: IHomePageApi
) : HomeFragmentContract.Remote {

	override fun getUpComingMovieData(): Single<HomePageResponse> =
		 homePageApiInterface.getUpcomingMovies()

	override fun getTopRatedMovieData(): Single<HomePageResponse> =
		homePageApiInterface.getTopRatedMovies()

	override fun getPopularMovieData(): Single<HomePageResponse> =
		homePageApiInterface.getPopularMovies()

}
