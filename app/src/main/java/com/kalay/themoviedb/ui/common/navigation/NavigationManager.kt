package com.kalay.themoviedb.ui.common.navigation

import android.content.Context
import android.content.Intent
import com.kalay.component.ui.carousel.MovieCarouselDTO
import com.kalay.component.ui.moviecard.MovieCardDTO
import com.kalay.core.enums.PageType
import com.kalay.core.enums.ParcelableData
import com.kalay.core.ui.recyclerview.DisplayItem
import com.kalay.data.response.dataclasses.Results
import com.kalay.themoviedb.ui.pages.detail.activity.DetailActivity

class NavigationManager {

    fun navigate(
        model: DisplayItem,
        context: Context? = null
    ) {

        when (model) {
            is MovieCardDTO -> {
                openDetailActivity(context = context, results = model.results)

            }
            is MovieCarouselDTO -> {
                openDetailActivity(context = context, results = model.results)

            }
        }
    }


    private fun openDetailActivity(
        context: Context?,
        results: Results?
    ) {
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra(
            ParcelableData.PAGE_TYPE.toString(),
            PageType.Other.toString()
        )
        intent.putExtra(ParcelableData.MOVIE.toString(), results)
        context?.startActivity(intent)
    }
}