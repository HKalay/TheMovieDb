package com.kalay.data.request

import com.kalay.data.response.SearchPageResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ISearchPageApi {

    @GET("search/movie")
    fun getSearchMovies(@Query("query") searchQuery: String): Single<SearchPageResponse>
}