package com.kalay.component.ui.imagebig

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.kalay.component.R
import com.kalay.core.extensions.loadImage
import com.kalay.core.ui.recyclerview.DisplayItem
import com.kalay.core.ui.recyclerview.ViewHolder
import com.kalay.core.ui.recyclerview.ViewHolderBinder
import com.kalay.core.ui.recyclerview.ViewHolderFactory
import javax.inject.Inject

class ImageBigViewHolder(val view: View) : ViewHolder<ImageBigDTO>(view) {

    private var imgMoviePhoto: AppCompatImageView =
        view.findViewById(R.id.imgItemMoviePhoto)
    private var tvMovieTitle: AppCompatTextView = view.findViewById(R.id.tvItemMovieTitle)

    override fun bind(item: ImageBigDTO) {
        item.movieDetail?.backdrop_path.let { _backPath ->
            imgMoviePhoto.loadImage(_backPath.toString())
        }
        tvMovieTitle.text = item.movieDetail?.title
    }


    class HolderFactory @Inject constructor() : ViewHolderFactory {
        override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder =
            ImageBigViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_image_big,
                    parent,
                    false
                )
            )
    }

    class BinderFactory @Inject constructor() : ViewHolderBinder {
        override fun bind(holder: RecyclerView.ViewHolder, item: DisplayItem) {
            (holder as ImageBigViewHolder).bind(item as ImageBigDTO)
        }
    }
}
