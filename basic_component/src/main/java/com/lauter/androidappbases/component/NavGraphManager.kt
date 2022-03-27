package com.lauter.androidappbases.component

import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.lauter.androidappbases.base.extension.longToast

object NavGraphManager {

    private const val NAV_NAME_MANAGER = "com.lauter.androidappbases.component.NavGraphResource"
    private const val FIELD_NAME = "navName"

    fun setNavGraph(activity: AppCompatActivity) {
        activity.run {
            supportFragmentManager.findFragmentById(R.id.host_fragment)?.let {
                val name = getNavName() ?: packageName.run {
                    "nav_graph_${substring(indexOf("_") + 1)}"
                }
                val graphId = resources.getIdentifier(name,"navigation",packageName)
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

    private fun getNavName(): String? {
        try {
            val navClass = Class.forName(NAV_NAME_MANAGER)
            val instance = navClass.getConstructor().newInstance()
            val field = navClass.getDeclaredField(FIELD_NAME)
            field.isAccessible = true
            return field.get(instance) as String
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }
}