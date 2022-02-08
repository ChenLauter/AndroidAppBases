package com.lauter.androidappbases.module.home

import android.os.Bundle
import com.lauter.androidappbases.base.fragment.BaseVmFragment
import com.lauter.androidappbases.module.home.databinding.FragmentHomeBinding

class HomeFragment: BaseVmFragment<HomeViewModel,FragmentHomeBinding>() {

    override fun init(savedInstanceState: Bundle?) {

    }

    override fun getLayoutId(): Int = R.layout.fragment_home
}