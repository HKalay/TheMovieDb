package com.kalay.component.helpers.horizontalrcycler

import android.os.Parcelable
import com.kalay.component.constant.RecyclerviewAdapterConstant
import com.kalay.core.ui.recyclerview.DisplayItem
import kotlinx.android.parcel.Parcelize

@Parcelize
data class HorizontalRecyclerDTO(
    var horizontalRecyclerlist: List<DisplayItem>,
    var isCircleLooping: Boolean = false
) : Parcelable, DisplayItem(RecyclerviewAdapterConstant.TYPES.TYPE_HORIZONTAL_RECYCLER)
