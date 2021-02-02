package com.kalay.data.response.dataclasses

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Results (
    val id:Int?,
    val backdrop_path:String?,
    val original_title:String?,
    val overview:String?,
    val poster_path:String?,
    val release_date:String?,
    val title:String?,
    val vote_average:Double?,
    val vote_count:Int?
):Parcelable