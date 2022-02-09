package com.lauter.androidappbases.module.home.api

import com.lauter.androidappbases.common.api.BaseApiResponse
import com.lauter.androidappbases.module.home.bean.BannerBean
import retrofit2.http.GET

interface HomeApiService {

    @GET("/banner/json")
    suspend fun getBanner(): BaseApiResponse<List<BannerBean>>
}