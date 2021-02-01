package com.kalay.data.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.kalay.data.response.dataclasses.Results
import kotlinx.android.parcel.Parcelize


@Parcelize
data class HomePageResponse(
    @SerializedName("results") val results: Results?
) : Parcelable

