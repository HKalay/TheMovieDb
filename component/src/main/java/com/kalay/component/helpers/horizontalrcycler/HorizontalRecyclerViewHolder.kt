package com.kalay.component.helpers.horizontalrcycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bekawestberg.loopinglayout.library.LoopingLayoutManager
import com.kalay.component.R
import com.kalay.component.constant.RecyclerviewAdapterConstant
import com.kalay.core.extensions.setup
import com.kalay.core.ui.recyclerview.*
import javax.inject.Inject

class HorizontalRecyclerViewHolder(var view: View) : ViewHolder<HorizontalRecyclerDTO>(view) {

	private var rcView: RecyclerView = view.findViewById(R.id.rvItemHorizontalRecyclerview)
	private val layoutManager =
		LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)

	private var viewManager: RecyclerView.LayoutManager =
		LoopingLayoutManager(view.context, LoopingLayoutManager.HORIZONTAL, false)


	private var isAdapterSet = false

	val adapter = RecyclerViewAdapter(
		itemComperator = DefaultDisplayItemComperator(),
		viewBinderFactoryMap = RecyclerviewAdapterConstant().binderFactoryMap,
		viewHolderFactoryMap = RecyclerviewAdapterConstant().holderFactoryMap
	)


	override fun bind(item: HorizontalRecyclerDTO) {

		ViewCompat.setNestedScrollingEnabled(rcView, false)
		rcView.setHasFixedSize(true)
		rcView.isNestedScrollingEnabled = false

		if (item.horizontalRecyclerlist.isNullOrEmpty())
			return

		if (!adapter.hasObservers())
			adapter.setHasStableIds(true)

		if (!isAdapterSet) {
			isAdapterSet = true
			if (item.isCircleLooping) {
				rcView.setup(adapter, viewManager, true)
				//rcView.layoutManager = viewManager
				//rcView.adapter = adapter
			} else {
				rcView.setup(adapter, layoutManager, true)
			}
		}

		rcView.setHasFixedSize(true)

		adapter.itemClickListener = { _item, _position ->
			itemClickListener?.invoke(_item, _position)
		}

		adapter.updateAllItems(item.horizontalRecyclerlist)
	}

	override fun onAttachAdapter() {
	}

	override fun onDetachAdapter() {
	}

	class HolderFactory @Inject constructor() : ViewHolderFactory {
		override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
			val holder = HorizontalRecyclerViewHolder(
				LayoutInflater.from(parent.context).inflate(
					R.layout.item_horizontal_recycler,
					parent,
					false
				)
			)
			holder.setIsRecyclable(false)
			return holder
		}
	}

	class BinderFactory @Inject constructor() : ViewHolderBinder {
		override fun bind(holder: RecyclerView.ViewHolder, item: DisplayItem) {
			(holder as HorizontalRecyclerViewHolder).bind(item as HorizontalRecyclerDTO)
		}
	}
}
