package com.kalay.data.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.kalay.data.response.dataclasses.Results
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SearchPageResponse(
    @SerializedName("results") val results: List<Results>?
) : Parcelable

