package com.kalay.component.ui.moviedetail

import android.os.Parcelable
import com.kalay.component.constant.RecyclerviewAdapterConstant.TYPES.TYPE_MOVIE_DETAIL
import com.kalay.core.ui.recyclerview.DisplayItem
import com.kalay.data.response.MovieDetailResponse
import kotlinx.android.parcel.Parcelize


@Parcelize
data class MovieDetailDTO(
    val movieDetail: MovieDetailResponse?
) : Parcelable, DisplayItem(TYPE_MOVIE_DETAIL)