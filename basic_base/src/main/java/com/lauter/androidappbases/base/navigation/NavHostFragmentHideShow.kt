package com.lauter.androidappbases.base.navigation

import android.view.View
import androidx.navigation.NavHostController
import androidx.navigation.fragment.DialogFragmentNavigator
import androidx.navigation.fragment.NavHostFragment
import com.lauter.androidappbases.base.R

/**
 * 作者　: hegaojian
 * 时间　: 2021/6/29
 * 描述　: Hide - Show NavHostFragment
 */
class NavHostFragmentHideShow : NavHostFragment() {

    override fun onCreateNavHostController(navHostController: NavHostController) {
        navController.navigatorProvider.addNavigator(DialogFragmentNavigator(requireContext(),childFragmentManager))
        navController.navigatorProvider.addNavigator(FragmentNavigatorHideShow(requireContext(), childFragmentManager, containerId))
    }

    private val containerId: Int
        get() {
            val id = id
            return if (id != 0 && id != View.NO_ID) {
                id
                // Fallback to using our own ID if this Fragment wasn't added via
                // add(containerViewId, Fragment)
            } else R.id.nav_host_fragment_container
        }
}