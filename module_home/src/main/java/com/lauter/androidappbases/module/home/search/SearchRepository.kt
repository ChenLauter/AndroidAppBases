package com.lauter.androidappbases.module.home.search

import com.lauter.androidappbases.base.repository.BaseRepository
import com.lauter.androidappbases.common.api.NetWorkApi
import com.lauter.androidappbases.module.home.api.HomeApiService

class SearchRepository : BaseRepository(){

    suspend fun getHotKey() = withIO {
        NetWorkApi.INSTANCE.getApiService(HomeApiService::class.java).getHotKey()
    }
}