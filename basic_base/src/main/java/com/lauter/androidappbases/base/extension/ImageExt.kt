package com.lauter.androidappbases.base.extension

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

fun ImageView.loadUrl(url: String) {
    Glide.with(context)
        .load(url)
        .into(this)
}

fun ImageView.loadRadius(url: String, radius: Int) {
    Glide.with(context)
        .load(url)
        .apply(
            RequestOptions().transform(
                RoundedCorners(radius)
            )
        )
        .into(this)
}