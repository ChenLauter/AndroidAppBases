package com.lauter.androidappbases.common.http

class ApiException(val errorMessage: String, val errorCode: Int) :
    Throwable()