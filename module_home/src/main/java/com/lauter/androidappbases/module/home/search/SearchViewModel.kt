package com.lauter.androidappbases.module.home.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.lauter.androidappbases.base.viewmodel.BaseViewModel
import com.lauter.androidappbases.common.utils.CacheUtil

class SearchViewModel: BaseViewModel() {

    private val repo by lazy { SearchRepository() }

    private val _historyData = MutableLiveData<MutableList<String>>()
    val historyData: LiveData<MutableList<String>> = _historyData
    fun getHistoryData() {
        _historyData.value = getCacheHistory()
    }

    fun clearHistory() {
        val empty = mutableListOf<String>()
        _historyData.value = empty
        setHistory(empty)
    }

    private val _hotKeyData = MutableLiveData<MutableList<String>>()
    val hotKeyData: LiveData<MutableList<String>> = _hotKeyData
    fun getHotKey() {
        launch {
            val temp = mutableListOf<String>()
            repo.getHotKey().data?.let {
                for (item in it) {
                    temp.add(item.name)
                }
            }
            _hotKeyData.value = temp
        }
    }

    fun getSearchResult(key: String) {
        _historyData.value?.let {
            if (it.contains(key)) {
                it.remove(key)
            }
            it.add(0,key)
            setHistory(it)
        }
    }

    private fun getCacheHistory(): MutableList<String> {
        val string = CacheUtil.getCache("search_history","[]")
        return Gson().fromJson(string,object : TypeToken<MutableList<String>>(){}.type)
    }

    private fun setHistory(list: List<String>) {
        CacheUtil.putCache("search_history", Gson().toJson(list))
    }
}