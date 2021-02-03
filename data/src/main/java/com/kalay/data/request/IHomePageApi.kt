package com.kalay.data.request

import com.kalay.data.response.HomePageResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface IHomePageApi {

    @GET("movie/upcoming")
    fun getUpcomingMovies(): Single<HomePageResponse>

    @GET("movie/top_rated")
    fun getTopRatedMovies(): Single<HomePageResponse>

    @GET("movie/popular")
    fun getPopularMovies(@Query("page") pageIndex: Int): Single<HomePageResponse>
}