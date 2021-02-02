package com.kalay.core.extensions

import android.view.View
import android.widget.ImageView
import com.kalay.core.constants.AppConstants
import com.squareup.picasso.Picasso

fun View.gone() {
    visibility = View.GONE
}

fun View.visibile() {
    visibility = View.VISIBLE
}

fun ImageView.loadImage(url: String) {
    Picasso.with(this.context)
        .load(AppConstants.IMAGE_BASE_URL + AppConstants.IMAGE_W342 + url)
        .into(this)
}
