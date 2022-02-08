package com.lauter.androidappbases.base.utils

@Target(AnnotationTarget.FIELD)
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class Param(val value: String = "")