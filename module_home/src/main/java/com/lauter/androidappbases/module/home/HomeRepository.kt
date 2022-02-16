package com.lauter.androidappbases.module.home

import com.lauter.androidappbases.base.repository.BaseRepository
import com.lauter.androidappbases.common.api.NetWorkApi
import com.lauter.androidappbases.module.home.api.HomeApiService

class HomeRepository: BaseRepository() {

    suspend fun getBanner() = withIO {
        NetWorkApi.INSTANCE.getApiService(HomeApiService::class.java).getBanner()
    }
}