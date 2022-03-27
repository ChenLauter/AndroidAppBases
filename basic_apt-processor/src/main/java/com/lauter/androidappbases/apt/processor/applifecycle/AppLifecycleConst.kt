package com.lauter.androidappbases.apt.processor.applifecycle

internal object AppLifecycleConst {

    const val CALLBACK_PACKAGE = "com.lauter.androidappbases.apt.api"

    const val CALLBACK_NAME = "IAppLifecycleCallback"

    const val CALLBACK_QUALIFIED_NAME = "$CALLBACK_PACKAGE.$CALLBACK_NAME"

    const val PROXY_SUFFIX = "_LauterProxy"

    const val CONTEXT = "android.content.Context"
}