package com.kalay.core.constants

import com.kalay.core.BuildConfig


object AppConstants {

    const val API_URL = BuildConfig.BASE_URL
    const val API_KEY = BuildConfig.THE_MOVIE_DB_KEY
    const val NETWORK_TIMEOUT: Long = 10
    const val MAX_MEMORY_CACHE: Long = 20 * 1024 * 1024
    const val DB_NAME = BuildConfig.DATABASE_NAME
    const val API_KEY_NAME = "api_key"


    val IMAGE_BASE_URL="https://image.tmdb.org/t/p/"
    val IMAGE_W92="w92"
    val IMAGE_W154="w154"
    val IMAGE_W185="w185"
    val IMAGE_W342="w342"
    val IMAGE_W500="w500"
    val IMAGE_W780="w780"

}
