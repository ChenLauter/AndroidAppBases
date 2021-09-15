package com.lauter.androidappbases.ui.main

import android.os.Bundle
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.lauter.androidappbases.R
import com.lauter.androidappbases.common.base.BaseVmFragment
import com.lauter.androidappbases.databinding.FragmentMainBinding
import com.lauter.androidappbases.ui.home.HomeFragment
import com.lauter.androidappbases.ui.mine.MineFragment
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : BaseVmFragment<FragmentMainBinding>() {

    companion object {
        private const val INDEX_HOME = 0
        private const val INDEX_MINE = 1
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
            adapter = object : FragmentStateAdapter(this@MainFragment) {
                override fun getItemCount(): Int = fragmentList.size
                override fun createFragment(position: Int): Fragment = fragmentList[position]
            }
            isUserInputEnabled = false


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

}