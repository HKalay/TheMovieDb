package com.kalay.component.ui.moviedetail.genres

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

class GenresViewHolder(val view: View) : ViewHolder<GenresDTO>(view) {

    private var tvGenresName: AppCompatTextView = view.findViewById(R.id.tvItemGenresName)

    override fun bind(item: GenresDTO) {
        tvGenresName.text = item.genres?.name
    }


    class HolderFactory @Inject constructor() : ViewHolderFactory {
        override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder =
            GenresViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_genres,
                    parent,
                    false
                )
            )
    }

    class BinderFactory @Inject constructor() : ViewHolderBinder {
        override fun bind(holder: RecyclerView.ViewHolder, item: DisplayItem) {
            (holder as GenresViewHolder).bind(item as GenresDTO)
        }
    }
}
