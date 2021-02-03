package com.kalay.themoviedb.ui.common.navigation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.kalay.component.ui.carousel.MovieCarouselDTO
import com.kalay.component.ui.moviecard.MovieCardDTO
import com.kalay.core.enums.PageType
import com.kalay.core.enums.ParcelableData
import com.kalay.core.ui.recyclerview.DisplayItem
import com.kalay.themoviedb.ui.pages.detail.DetailActivity

class NavigationManager {

    fun navigate(
        model: DisplayItem,
        context: Context? = null
    ) {

        when (model) {
            is MovieCardDTO -> {
                openDetailActivity(context = context,movieId=model.results?.id)
            }
            is MovieCarouselDTO -> {
                openDetailActivity(context = context,movieId=model.results?.id)
            }
        }
    }


    private fun openDetailActivity(
        context: Context?,
        movieId:Int?
    ) {
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra(
            ParcelableData.PAGE_TYPE.toString(),
            PageType.Other.toString()
        )
        intent.putExtra(ParcelableData.MOVIE_ID.toString(), movieId)
        context?.startActivity(intent)
    }
}