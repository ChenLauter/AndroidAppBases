package com.lauter.androidappbases.common.adapter

import android.content.Context
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding

class SimpleDataBindingAdapter<VDB: ViewDataBinding, T>(context: Context?,
                                                        @LayoutRes layoutId: Int,
                                                        var beanId: Int,
                                                        data: List<T>?=null) :
    BaseDataBindingAdapter<VDB,T>(context, layoutId, data){

    override fun onBindViewHolder(viewDataBinding: VDB,position: Int) {
        viewDataBinding.setVariable(beanId, data?.get(position))
    }

}