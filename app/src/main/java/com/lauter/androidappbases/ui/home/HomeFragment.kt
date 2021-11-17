package com.lauter.androidappbases.ui.home

import android.os.Bundle
import com.lauter.androidappbases.R
import com.lauter.androidappbases.common.base.BaseLoadingFragment
import com.lauter.androidappbases.common.extension.loadUrl
import com.lauter.androidappbases.common.utils.LogUtil
import com.lauter.androidappbases.databinding.FragmentHomeBinding
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_mine.*
import kotlinx.android.synthetic.main.fragment_mine.content

class HomeFragment : BaseLoadingFragment<FragmentHomeBinding>() {

    override fun getLayoutId(): Int = R.layout.fragment_home

    private val imageUrl = "https://wallpaper-cdn.magicgame001.com/prod/image/2021-09-17/d2b6ad19a795d68c7d38e03528678230.jpg"
    override fun lazyInit() {
        content.text = "$this"
        homeImage.loadUrl(imageUrl) {
            LogUtil.d("加载完成")
        }
    }

}