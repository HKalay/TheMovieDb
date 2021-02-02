package com.kalay.component.ui.moviecard

import android.os.Parcelable
import com.kalay.component.constant.RecyclerviewAdapterConstant.TYPES.TYPE_MOVIE_CARD
import com.kalay.core.ui.recyclerview.DisplayItem
import com.kalay.data.response.dataclasses.Results

import kotlinx.android.parcel.Parcelize


@Parcelize
data class MovieCardDTO(
    val results: Results?
) : Parcelable, DisplayItem(TYPE_MOVIE_CARD)
