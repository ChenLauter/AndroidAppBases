package com.lauter.androidappbases.module.home.view

import android.content.Context
import android.util.AttributeSet
import com.lauter.androidappbases.base.extension.loadRadius
import com.lauter.androidappbases.module.home.bean.BannerBean
import com.youth.banner.Banner
import com.youth.banner.adapter.BannerImageAdapter
import com.youth.banner.holder.BannerImageHolder

class HomeBanner @JvmOverloads constructor(context: Context,
                                           attributeSet: AttributeSet?=null,
                                           defStyleAttr: Int=0) :
    Banner<BannerBean,BannerImageAdapter<BannerBean>>(context, attributeSet, defStyleAttr) {

    init {
        setAdapter(object : BannerImageAdapter<BannerBean>(null) {
            override fun onBindView(
                holder: BannerImageHolder,
                data: BannerBean,
                position: Int,
                size: Int
            ) {
                holder.imageView.loadRadius(data.imagePath, 12)
            }

        })
        setBannerGalleryEffect(22, 12)
    }
}