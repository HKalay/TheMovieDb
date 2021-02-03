package com.kalay.data.response.dataclasses

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieDetail (
    val backdrop_path:String?,
    val title:String?,
    val release_date:String?,
    val genres:List<Genres>?,
    val overview:String
): Parcelable