package com.kalay.data.request

import com.kalay.data.response.MovieDetailResponse
import com.kalay.data.response.SearchPageResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface IMovieDetailPageApi {

    @GET("search/movie")
    fun getSearchMovies(@Query("query") searchQuery: String): Single<SearchPageResponse>

    // details
    @GET("/movie/{movie_id}")
    fun getTvShowDetails(@Path("id") movieId:Int): Single<MovieDetailResponse>
}