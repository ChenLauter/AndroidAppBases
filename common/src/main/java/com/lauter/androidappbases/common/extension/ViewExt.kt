package com.lauter.androidappbases.common.extension

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2

fun ViewPager2.initFragment(fragment: Fragment,list: List<Fragment>) {
    adapter = object : FragmentStateAdapter(fragment) {
        override fun getItemCount(): Int = list.size
        override fun createFragment(position: Int): Fragment = list[position]
    }
}