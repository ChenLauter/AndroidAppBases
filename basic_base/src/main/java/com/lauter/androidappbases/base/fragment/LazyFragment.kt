package com.lauter.androidappbases.base.fragment

import android.os.Bundle
import androidx.databinding.ViewDataBinding

abstract class LazyFragment<BD: ViewDataBinding> : BaseFragment<BD>() {

    private var isLoaded = false
    override fun onResume() {
        super.onResume()
        //增加了Fragment是否可见的判断
        if (!isLoaded && !isHidden) {
            lazyInit()
            isLoaded = true
        }
    }

    override fun init(savedInstanceState: Bundle?) {
    }

    override fun onDestroyView() {
        super.onDestroyView()
        isLoaded = false
    }

    abstract fun lazyInit()
}