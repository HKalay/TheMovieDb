package com.kalay.component.ui.carousel

import android.os.Parcelable
import com.kalay.component.constant.RecyclerviewAdapterConstant.TYPES.TYPE_CAROUSEL
import com.kalay.core.ui.recyclerview.DisplayItem
import com.kalay.data.response.dataclasses.Results
import kotlinx.android.parcel.Parcelize


@Parcelize
data class MovieCarouselDTO(
    val results: Results?
) : Parcelable, DisplayItem(TYPE_CAROUSEL)

