package com.kalay.component.constant

import com.kalay.component.ui.slider.SliderViewHolder
import com.kalay.component.helpers.horizontalrcycler.HorizontalRecyclerViewHolder
import com.kalay.component.ui.categorytitle.CategoryTitleViewHolder
import com.kalay.component.ui.carousel.MovieCarouselViewHolder

class RecyclerviewAdapterConstant {
    object TYPES {
        const val TYPE_NONE = 0
        const val TYPE_HORIZONTAL_RECYCLER = 1
        const val TYPE_CATEGORY_TITLE =2
        const val TYPE_SLIDER = 3
        const val TYPE_CAROUSEL = 4
    }

    var binderFactoryMap = mutableMapOf(
        TYPES.TYPE_HORIZONTAL_RECYCLER to HorizontalRecyclerViewHolder.BinderFactory(),
        TYPES.TYPE_CATEGORY_TITLE to CategoryTitleViewHolder.BinderFactory(),
        TYPES.TYPE_SLIDER to SliderViewHolder.BinderFactory(),
        TYPES.TYPE_CAROUSEL to MovieCarouselViewHolder.BinderFactory()
    )

    var holderFactoryMap = mutableMapOf(
        TYPES.TYPE_HORIZONTAL_RECYCLER to HorizontalRecyclerViewHolder.HolderFactory(),
        TYPES.TYPE_CATEGORY_TITLE to CategoryTitleViewHolder.HolderFactory(),
        TYPES.TYPE_SLIDER to SliderViewHolder.HolderFactory(),
        TYPES.TYPE_CAROUSEL to MovieCarouselViewHolder.HolderFactory()
    )
}