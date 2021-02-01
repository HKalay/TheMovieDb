package com.kalay.component.slider

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.kalay.component.R
import com.kalay.core.ui.recyclerview.DisplayItem
import com.kalay.core.ui.recyclerview.ViewHolder
import com.kalay.core.ui.recyclerview.ViewHolderBinder
import com.kalay.core.ui.recyclerview.ViewHolderFactory
import com.kenilt.loopingviewpager.widget.LoopingViewPager
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator
import javax.inject.Inject

class SliderViewHolder(var view: View) : ViewHolder<SliderListDTO>(view) {

	private var viewPager: LoopingViewPager = view.findViewById(R.id.viewPagerItemSlider)
	private val dotsIndicator: DotsIndicator = view.findViewById(R.id.pageIndicatorViewItemSlider)

	override fun bind(item: SliderListDTO) {
		val adapter = item.sliderList?.let {
			SliderViewpagerAdapter(view.context, item, false) { item, position ->
				itemClickListener?.invoke(item, position)
			}
		}
		viewPager.adapter = adapter
		viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
			override fun onPageScrollStateChanged(state: Int) {

			}

			override fun onPageScrolled(
				position: Int,
				positionOffset: Float,
				positionOffsetPixels: Int
			) {
			}

			override fun onPageSelected(position: Int) {
				item.sliderList?.forEachIndexed { index, _ ->
					dotsIndicator.refreshDotColor(index)

				}
			}

		})
		dotsIndicator.setViewPager(viewPager)
	}

	class HolderFactory @Inject constructor() : ViewHolderFactory {
		override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder =
			SliderViewHolder(
				LayoutInflater.from(parent.context).inflate(R.layout.item_slider, parent, false)
			)
	}

	class BinderFactory @Inject constructor() : ViewHolderBinder {
		override fun bind(holder: RecyclerView.ViewHolder, item: DisplayItem) {
			(holder as SliderViewHolder).bind(item as SliderListDTO)
		}
	}
}
