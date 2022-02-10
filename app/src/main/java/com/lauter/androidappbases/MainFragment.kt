package com.lauter.androidappbases

import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.StyleSpan
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.lauter.androidappbases.base.fragment.BaseFragment
import com.lauter.androidappbases.common.constant.NavIndex.HOME
import com.lauter.androidappbases.common.constant.NavIndex.MINE
import com.lauter.androidappbases.databinding.FragmentMainBinding
import com.lauter.androidappbases.module.home.HomeFragment
import com.lauter.androidappbases.module.user.MineFragment

class MainFragment: BaseFragment<FragmentMainBinding>() {

    private val navFragments by lazy {
        listOf(
            HomeFragment(),
            MineFragment()
        )
    }

    override fun init(savedInstanceState: Bundle?) {
        initNavView()
        initPager()
    }

    override fun getLayoutId(): Int = R.layout.fragment_main

    private fun initNavView() {
        binding.navView.run {
            with(menu) {
                for (i in 0 until size()) {
                    getItem(i).let {
                        it.title = SpannableStringBuilder(it.title).apply {
                            setSpan(
                                StyleSpan(Typeface.BOLD),
                                0,
                                it.title.length,
                                Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
                        }
                    }
                }
            }
            setOnItemSelectedListener {
                when(it.itemId) {
                    R.id.navigation_home -> binding.viewPager.setCurrentItem(HOME, false)
                    R.id.navigation_mine -> binding.viewPager.setCurrentItem(MINE, false)
                }
                true
            }
        }

    }

    private fun initPager() {
        binding.viewPager.run {
            val size = navFragments.size
            isUserInputEnabled = false
            offscreenPageLimit = size
            adapter = object : FragmentStateAdapter(this@MainFragment) {
                override fun getItemCount(): Int = size

                override fun createFragment(position: Int): Fragment {
                    return navFragments[position]
                }

            }
        }

    }
}