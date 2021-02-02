package com.kalay.component.ui.categorytitle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.kalay.component.R
import com.kalay.core.ui.recyclerview.DisplayItem
import com.kalay.core.ui.recyclerview.ViewHolder
import com.kalay.core.ui.recyclerview.ViewHolderBinder
import com.kalay.core.ui.recyclerview.ViewHolderFactory
import javax.inject.Inject

class CategoryTitleViewHolder(view: View) : ViewHolder<CategoryTitle>(view) {

    private var tvItemCategoryTitle: AppCompatTextView =
        view.findViewById(R.id.tvItemCategoryTitle)

    override fun bind(item: CategoryTitle) {
        tvItemCategoryTitle.text = item.categoryTitle
    }

    class HolderFactory @Inject constructor() : ViewHolderFactory {
        override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder =
            CategoryTitleViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_category_title,
                    parent,
                    false
                )
            )
    }

    class BinderFactory @Inject constructor() : ViewHolderBinder {
        override fun bind(holder: RecyclerView.ViewHolder, item: DisplayItem) {
            (holder as CategoryTitleViewHolder).bind(item as CategoryTitle)
        }
    }
}