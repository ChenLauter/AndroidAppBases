package com.lauter.androidappbases.ui.home

import android.os.Bundle
import com.lauter.androidappbases.R
import com.lauter.androidappbases.common.base.BaseLoadingFragment
import com.lauter.androidappbases.databinding.FragmentHomeBinding
import kotlinx.android.synthetic.main.fragment_mine.*

class HomeFragment : BaseLoadingFragment<FragmentHomeBinding>() {

    override fun getLayoutId(): Int = R.layout.fragment_home

    override fun lazyInit() {
        content.text = "$this"
    }

}