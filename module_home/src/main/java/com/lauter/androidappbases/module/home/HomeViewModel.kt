package com.lauter.androidappbases.module.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lauter.androidappbases.base.viewmodel.BaseViewModel
import com.lauter.androidappbases.module.home.bean.BannerBean

class HomeViewModel : BaseViewModel() {

    private val repo by lazy { HomeRepository() }

    private val _banner = MutableLiveData<List<BannerBean>>()
    val banner: LiveData<List<BannerBean>> = _banner
    fun getBanner() {
        launch {
            _banner.value = repo.getBanner().data
        }
    }
}