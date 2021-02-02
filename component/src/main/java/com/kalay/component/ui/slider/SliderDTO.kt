package com.kalay.component.ui.slider

import android.os.Parcelable
import com.kalay.data.response.dataclasses.Results
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SliderDTO(
    val results: Results?
) : Parcelable
