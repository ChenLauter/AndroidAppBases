package com.lauter.androidappbases.plugin.applifecycle

internal object AppLifecycleConst {

    const val APP_LIFECYCLE_PLUGIN_NAME = "AppLifecyclePlugin"

    const val APP_LIFECYCLE_PROXY_SUFFIX = "_LauterProxy"

    const val APP_LIFECYCLE_CALLBACK = "com/lauter/androidappbases/apt/api/IAppLifecycleCallback"

    const val APP_LIFECYCLE_MANAGER = "com/lauter/androidappbases/apt/api/AppLifecycleManager"

    const val APP_LIFECYCLE_MANAGER_FILE = "$APP_LIFECYCLE_MANAGER.class"

    const val APP_LIFECYCLE_REGISTER_METHOD_NAME = "registerAppLifecycle"
}