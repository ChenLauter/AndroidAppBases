package com.lauter.androidappbases.module.home.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lauter.androidappbases.base.viewmodel.BaseViewModel

class SearchViewModel: BaseViewModel() {

    private val _historyData = MutableLiveData<MutableList<String>>()
    val historyData: LiveData<MutableList<String>> = _historyData
    fun getHistoryData() {
        _historyData.value = mutableListOf(
                "哈利波特",
                "罗恩",
                "赫敏",
                "啊",
                "abs",
                "感到尴尬",
                "给特工",
                "大大大大哥",
                "a",
                "bigch",
                "哈利波特",
                "you and i are just made to love each now forever and a day",
                "hhhhhhhhhhhhhhhhhhhhhhhhhhhhh"
            )
    }

    fun getSearchResult(key: String) {
//        _historyData.value?.let {
//            if (!it.contains(key)) {
//                it.add(0,key)
//            }
//        }
    }
}