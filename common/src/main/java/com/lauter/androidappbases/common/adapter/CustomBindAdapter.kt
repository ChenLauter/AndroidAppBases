package com.lauter.androidappbases.common.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.lauter.androidappbases.common.extension.dip2px
import com.lauter.androidappbases.common.extension.loadCircle
import com.lauter.androidappbases.common.extension.loadUrl

object CustomBindAdapter {

    /**
     * 加载圆形图片
     */
    @BindingAdapter(value = ["imgUriCircle"])
    @JvmStatic
    fun imgUriCircle(view: ImageView, url: String) {
        view.loadCircle(url)
    }

    /**
     * 加载圆角图片
     */
    @BindingAdapter(value= ["imgUriRadius", "imgRadius"])
    @JvmStatic
    fun imgUriRadius(view: ImageView, url: String?, imgRadius: Float) {
        Glide.with(view)
            .load(url)
            .apply(RequestOptions().transform(CenterCrop(),RoundedCorners(dip2px(imgRadius))))
            .into(view)
    }

    @BindingAdapter(value= ["imgUrl"])
    @JvmStatic
    fun imgUrl(view: ImageView, url: String?) {
        view.loadUrl(url)
    }


}