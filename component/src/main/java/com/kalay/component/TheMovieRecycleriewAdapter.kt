package com.kalay.component

import com.kalay.component.constant.RecyclerviewAdapterConstant
import com.kalay.core.ui.recyclerview.DefaultDisplayItemComperator
import com.kalay.core.ui.recyclerview.RecyclerViewAdapter

class TheMovieRecycleriewAdapter {

    fun getAdapter() = _adapter

    val _adapter = RecyclerViewAdapter(
        itemComperator = DefaultDisplayItemComperator(),
        viewBinderFactoryMap = RecyclerviewAdapterConstant().binderFactoryMap,
        viewHolderFactoryMap = RecyclerviewAdapterConstant().holderFactoryMap
    )
}