package com.kalay.data.response

import android.os.Parcelable
import com.kalay.data.response.dataclasses.Genres
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieDetailResponse (
    val backdrop_path:String?,
    val title:String?,
    val release_date:String?,
    val genres:List<Genres>?,
    val overview:String,
    val vote_average:Double?
): Parcelable