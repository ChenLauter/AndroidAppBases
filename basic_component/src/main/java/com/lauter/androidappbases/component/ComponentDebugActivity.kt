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
        supportFragmentManager.findFragmentById(R.id.host_fragment)?.let {
            val name = packageName.run {
                substring(indexOf("_") + 1)
            }
            val graphId = resources.getIdentifier("nav_graph_$name","navigation",packageName)
            if (graphId != 0) {
                (it as NavHostFragment).navController.apply {
                    setGraph(graphId)
                }
            } else {
                this.longToast("未能找到NavGraph")
            }
        }
    }
}