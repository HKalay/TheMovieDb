package com.kalay.component

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kalay.core.ui.recyclerview.DisplayItem
import com.kalay.core.ui.recyclerview.ViewHolder
import com.kalay.core.ui.recyclerview.ViewHolderBinder
import com.kalay.core.ui.recyclerview.ViewHolderFactory
import com.google.android.material.card.MaterialCardView
import javax.inject.Inject

class MovieCardViewHolder(val view: View) : ViewHolder<MovieCardDTO>(view) {

    override fun bind(item: MovieCardDTO) {

    }

    class HolderFactory @Inject constructor() : ViewHolderFactory {
        override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder =
            MovieCardViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_news_card,
                    parent,
                    false
                )
            )
    }

    class BinderFactory @Inject constructor() : ViewHolderBinder {
        override fun bind(holder: RecyclerView.ViewHolder, item: DisplayItem) {
            (holder as MovieCardViewHolder).bind(item as MovieCardDTO)
        }
    }
}