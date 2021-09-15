package com.lauter.androidappbases.http

class ApiException(val errorMessage: String, val errorCode: Int) :
    Throwable()