package com.kalay.component.constant

import com.kalay.component.MovieCardViewHolder

class RecyclerviewAdapterConstant {
    object TYPES {
        const val TYPE_NONE = 0
        const val TYPE_NEWS_CARD =1
    }

    var binderFactoryMap = mutableMapOf(
        TYPES.TYPE_NEWS_CARD to MovieCardViewHolder.BinderFactory()
    )

    var holderFactoryMap = mutableMapOf(
        TYPES.TYPE_NEWS_CARD to MovieCardViewHolder.HolderFactory()
    )
}