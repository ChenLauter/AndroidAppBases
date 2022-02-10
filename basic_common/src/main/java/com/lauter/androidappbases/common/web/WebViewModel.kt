package com.lauter.androidappbases.common.web

import androidx.databinding.ObservableField
import com.lauter.androidappbases.base.viewmodel.BaseViewModel

/**
 * @date 2020/7/9
 * @author zs
 */
class WebViewModel: BaseViewModel() {

    /**
     * webView 进度
     */
    val progress = ObservableField<Int>()


    /**
     * 最大 进度
     */
    val maxProgress = ObservableField<Int>()

    /**
     * progress是否隐藏
     */
    val isVisible = ObservableField<Boolean>()

}