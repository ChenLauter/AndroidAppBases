package com.lauter.androidappbases.ui.main

import android.os.Bundle
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.lauter.androidappbases.R
import com.lauter.androidappbases.common.base.BaseVmFragment
import com.lauter.androidappbases.common.extension.initFragment
import com.lauter.androidappbases.databinding.FragmentMainBinding
import com.lauter.androidappbases.ui.home.HomeFragment
import com.lauter.androidappbases.ui.mine.MineFragment
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : BaseVmFragment<FragmentMainBinding>() {

    companion object {
        const val INDEX_HOME = 0
        const val INDEX_MINE = 1
    }


    private val fragmentList = mutableListOf<Fragment>()

    private val homeFragment by lazy { HomeFragment() }

    private val mineFragment by lazy { MineFragment() }

    init {
        fragmentList.apply {
            add(homeFragment)
            add(mineFragment)
        }
    }
    override fun init(savedInstanceState: Bundle?) {
        viewPager.run {
            initFragment(this@MainFragment,fragmentList)
            isUserInputEnabled = false
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    navView.menu.getItem(position).isChecked = true
                }
            })
        }
        navView.run {
            setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.navigation_home -> viewPager.setCurrentItem(INDEX_HOME,false)
                    R.id.navigation_mine -> viewPager.setCurrentItem(INDEX_MINE,false)
                }
                true
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback {
            requireActivity().moveTaskToBack(false)
        }

    }

    override fun getLayoutId(): Int = R.layout.fragment_main

    fun switchToPosition(position: Int) {
        viewPager.setCurrentItem(position,true)
    }

}