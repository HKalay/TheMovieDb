package com.kalay.themoviedb.ui.pages.detail.fragment

import android.os.Bundle
import android.view.View
import com.kalay.component.ui.slider.SliderListDTO
import com.kalay.core.enums.ParcelableData
import com.kalay.themoviedb.R
import com.kalay.themoviedb.ui.base.fragment.BaseFragment
import kotlinx.android.synthetic.main.fragment_moviedetail_master.*

class MovieDetailMasterFragment : BaseFragment() {

    override val layoutViewRes = R.layout.fragment_moviedetail_master

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sliderDTO = arguments?.getParcelable(ParcelableData.SLIDER_LIST.toString()) as SliderListDTO
        val position = arguments?.getInt(ParcelableData.SLIDER_POSITION.toString())

        viewpagerMaster?.apply {
            adapter = MovieDetailPagerAdapter(childFragmentManager, sliderDTO)
            currentItem = position ?: 0
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(bundle: Bundle?) = MovieDetailMasterFragment().apply { arguments = bundle }
    }
}
