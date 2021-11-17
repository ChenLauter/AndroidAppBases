package com.lauter.androidappbases.common.extension

import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target

fun ImageView.loadUrl(url: String?) {
    url?.let {
        Glide.with(this.context)
            .load(url)
            .into(this)
    }
}

fun ImageView.loadUrl(url: String?,callback: () -> Unit) {
    url?.let {
        Glide.with(this)
            .load(url)
            .listener(object : RequestListener<Drawable>{
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    callback.invoke()
                    return false
                }

            })
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