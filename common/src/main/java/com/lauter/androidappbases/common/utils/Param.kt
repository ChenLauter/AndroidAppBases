package com.lauter.androidappbases.common.utils

@Target(AnnotationTarget.FIELD)
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class Param(val value: String = "")