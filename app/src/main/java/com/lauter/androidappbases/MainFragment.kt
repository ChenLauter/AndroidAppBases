package com.lauter.androidappbases

import android.os.Bundle
import com.lauter.androidappbases.base.fragment.BaseFragment
import com.lauter.androidappbases.databinding.FragmentMainBinding

class MainFragment: BaseFragment<FragmentMainBinding>() {

    override fun init(savedInstanceState: Bundle?) {

    }

    override fun getLayoutId(): Int = R.layout.fragment_main
}