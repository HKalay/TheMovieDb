package com.kalay.component

import android.os.Parcelable
import com.kalay.component.constant.RecyclerviewAdapterConstant.TYPES.TYPE_NEWS_CARD
import com.kalay.core.ui.recyclerview.DisplayItem
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieCardDTO(
    val photoPath: String?
) : Parcelable, DisplayItem(TYPE_NEWS_CARD)