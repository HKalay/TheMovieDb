package com.kalay.themoviedb.ui.pages.moviedetail.repository

import com.kalay.core.ioc.scopes.FragmentScope
import com.kalay.data.request.IHomePageApi
import com.kalay.data.request.IMovieDetailPageApi
import com.kalay.data.response.MovieDetailResponse
import io.reactivex.Single

@FragmentScope
class MovieDetailFragmentRemoteData(
    private val movieDetailPageApiInterface: IMovieDetailPageApi
) : MovieDetailFragmentContract.Remote {


    override fun getMovieDetailPageData(movieId: Int): Single<MovieDetailResponse> =
        movieDetailPageApiInterface.getMovieDetails(movieId)


}
