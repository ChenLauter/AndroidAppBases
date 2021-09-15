package com.lauter.androidappbases.common.base

import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.lauter.androidappbases.common.view.LoadingTip

abstract class BaseLoadingActivity : AppCompatActivity() {

    var loadingTip: LoadingTip? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getLayoutId()?.let { setContentView(it) }
        val frameLayout = window.decorView as FrameLayout
        val params = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.MATCH_PARENT,
            FrameLayout.LayoutParams.MATCH_PARENT
        )
        loadingTip = LoadingTip(this)
        frameLayout.addView(loadingTip, params)
    }


    abstract fun init(savedInstanceState: Bundle?)

    abstract fun getLayoutId(): Int?
}