package com.kalay.component.ui.categorytitle

import android.os.Parcelable
import com.kalay.component.constant.RecyclerviewAdapterConstant.TYPES.TYPE_CATEGORY_TITLE
import com.kalay.core.ui.recyclerview.DisplayItem
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CategoryTitle(
    val categoryTitle: String?
) : Parcelable, DisplayItem(TYPE_CATEGORY_TITLE)