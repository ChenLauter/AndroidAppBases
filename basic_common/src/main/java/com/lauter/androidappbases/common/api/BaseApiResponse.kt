package com.lauter.androidappbases.common.api

class BaseApiResponse<T> {
    val errorCode = -1
    val errorMsg = ""
    val data: T? = null
}