package com.lauter.androidappbases.component

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.jaeger.library.StatusBarUtil
import com.lauter.androidappbases.base.extension.longToast

class ComponentDebugActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_component_debug)
        StatusBarUtil.setColor(this, Color.WHITE)
        NavGraphManager.setNavGraph(this)
    }
}