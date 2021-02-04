package com.kalay.themoviedb.ui.pages.detail.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.kalay.component.ui.slider.SliderListDTO
import com.kalay.core.enums.ParcelableData
import com.kalay.themoviedb.ui.pages.moviedetail.MovieDetailFragment

class MovieDetailPagerAdapter(
    fragmentManager: FragmentManager,
    private val sliderListDTO: SliderListDTO
) : FragmentPagerAdapter(fragmentManager, BEHAVIOR_SET_USER_VISIBLE_HINT) {

    override fun getItem(position: Int): Fragment {
        val results = sliderListDTO.sliderList?.get(position)?.results
        val bundle = Bundle()

        bundle.putInt(ParcelableData.MOVIE_ID.toString(), results?.id!!)

        return  MovieDetailFragment.newInstance(bundle)
    }

    override fun getCount(): Int {
        return sliderListDTO.sliderList?.size!!
    }
}
