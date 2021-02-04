package com.kalay.component.ui.moviedetail.genres

import android.os.Parcelable
import com.kalay.component.constant.RecyclerviewAdapterConstant.TYPES.TYPE_GENRES
import com.kalay.core.ui.recyclerview.DisplayItem
import com.kalay.data.response.dataclasses.Genres
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GenresDTO (
    val genres: Genres?
): Parcelable, DisplayItem(TYPE_GENRES)