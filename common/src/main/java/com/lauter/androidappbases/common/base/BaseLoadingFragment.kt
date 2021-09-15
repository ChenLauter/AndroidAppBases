package com.lauter.androidappbases.common.base

import android.content.Context
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import com.lauter.androidappbases.common.view.LoadingTip

abstract class BaseLoadingFragment<VDB: ViewDataBinding> : LazyVmFragment<VDB>() {

    protected var loadingTip: LoadingTip? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseLoadingActivity) {
            loadingTip = context.loadingTip
        }
    }

    protected fun setLoadingMargin(topMargin: Int, bottomMargin: Int) {
        val params = loadingTip?.layoutParams as ViewGroup.MarginLayoutParams
        params.topMargin = topMargin
        params.bottomMargin = bottomMargin
        loadingTip?.layoutParams = params
    }

}