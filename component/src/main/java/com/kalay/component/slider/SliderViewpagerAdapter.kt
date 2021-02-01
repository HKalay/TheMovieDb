package com.kalay.component.slider

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.bumptech.glide.Glide
import com.kalay.component.R
import com.kalay.component.helpers.loopingpager.LoopingPagerAdapter
import com.kalay.core.extensions.loadImage
import com.kalay.core.ui.recyclerview.DisplayItem

class SliderViewpagerAdapter(
    context: Context,
    private val items: SliderListDTO,
    isInfinite: Boolean,
    private val itemClickListener: ((item: DisplayItem, position: Int) -> Unit)? = null
) : LoopingPagerAdapter<SliderDTO>(context, items.sliderList!!, isInfinite) {

    override fun inflateView(viewType: Int, container: ViewGroup, listPosition: Int): View {
        return LayoutInflater.from(context).inflate(R.layout.item_slider_article, container, false)
    }

    override fun bindView(convertView: View, listPosition: Int, viewType: Int) {
        val item = itemList?.get(listPosition)
        val imgSliderNewsPhoto: AppCompatImageView =
            convertView.findViewById(R.id.imgItemSliderNewsPhoto)
        val tvSliderNewsTitle: AppCompatTextView =
            convertView.findViewById(R.id.tvItemSliderNewsTitle)
        val results = item?.results

        convertView.setOnClickListener {
            itemClickListener?.invoke(items, listPosition)
        }

        results?.poster_path?.let { _posterPath -> imgSliderNewsPhoto.loadImage(_posterPath) }
        tvSliderNewsTitle.text = results?.title
    }
}
