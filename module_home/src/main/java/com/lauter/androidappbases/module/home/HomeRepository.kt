package com.lauter.androidappbases.module.home

import com.lauter.androidappbases.common.api.NetWorkApi
import com.lauter.androidappbases.module.home.api.HomeApiService

class HomeRepository {

    suspend fun getBanner() = NetWorkApi.INSTANCE.getApiService(HomeApiService::class.java).getBanner()
}