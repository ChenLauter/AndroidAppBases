package com.lauter.androidappbases.base.fragment

import androidx.databinding.ViewDataBinding
import com.lauter.androidappbases.base.viewmodel.BaseViewModel
import java.lang.reflect.ParameterizedType

abstract class BaseVmFragment<VM : BaseViewModel, BD : ViewDataBinding> : BaseFragment<BD>() {

    // 默认生命周期跟随fragment
    protected lateinit var curVm: VM

    override fun initFragmentViewModel() {
        curVm = getFragmentViewModel(
            (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<VM>
        )
    }
}