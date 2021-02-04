package com.kalay.component.ui.imagebig

import android.os.Parcelable
import com.kalay.component.constant.RecyclerviewAdapterConstant.TYPES.TYPE_IMAGE_BIG
import com.kalay.core.ui.recyclerview.DisplayItem
import com.kalay.data.response.MovieDetailResponse
import kotlinx.android.parcel.Parcelize


@Parcelize
data class ImageBigDTO(
    val movieDetail: MovieDetailResponse? = null
) : Parcelable, DisplayItem(TYPE_IMAGE_BIG)