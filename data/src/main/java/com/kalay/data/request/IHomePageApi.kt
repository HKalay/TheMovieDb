package com.kalay.data.request

import com.kalay.data.response.HomePageResponse
import io.reactivex.Single
import retrofit2.http.GET

interface IHomePageApi {

    @GET("movie/upcoming")
    fun getUpcomingMovies(): Single<HomePageResponse>

}