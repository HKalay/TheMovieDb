package com.kalay.component.ui.slider

import android.os.Parcelable
import com.kalay.component.constant.RecyclerviewAdapterConstant.TYPES.TYPE_SLIDER
import com.kalay.core.ui.recyclerview.DisplayItem
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SliderListDTO(
    var sliderList: ArrayList<SliderDTO>?
) : Parcelable, DisplayItem(TYPE_SLIDER)