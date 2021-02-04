package com.kalay.data.request

import com.kalay.data.response.MovieDetailResponse
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface IMovieDetailPageApi {

    // details
    @GET("movie/{movie_id}")
    fun getMovieDetails(@Path("movie_id") movieId:Int): Single<MovieDetailResponse>

}