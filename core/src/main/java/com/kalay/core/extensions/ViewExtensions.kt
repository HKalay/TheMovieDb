package com.kalay.core.extensions

import android.view.View
import android.widget.ImageView
import com.squareup.picasso.Picasso

fun View.gone() {
    visibility = View.GONE
}

fun View.visibile() {
    visibility = View.VISIBLE
}

fun ImageView.loadImage(url: String) {
    Picasso.with(this.context)
        .load(url)
        .into(this)
}
