package com.lauter.androidappbases.common.extension

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions

fun ImageView.loadUrl(url: String?) {
    url?.let {
        Glide.with(this.context)
            .load(url)
            .into(this)
    }
}

fun ImageView.loadCircle(url: String?) {
    url?.let {
        Glide.with(this.context)
            .load(url)
            .apply(RequestOptions.bitmapTransform(CircleCrop()))
            .into(this)
    }
}