package com.lauter.androidappbases.base.fragment

import android.os.Bundle
import androidx.databinding.ViewDataBinding
import com.lauter.androidappbases.base.viewmodel.BaseViewModel

abstract class LazyVmFragment<VM: BaseViewModel, BD: ViewDataBinding> : BaseVmFragment<VM, BD>() {

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

    override fun onDestroy() {
        super.onDestroy()
        isLoaded = false
    }

    abstract fun lazyInit()

}