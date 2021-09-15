package com.lauter.androidappbases.ui.mine

import com.lauter.androidappbases.R
import com.lauter.androidappbases.common.base.LazyVmFragment
import com.lauter.androidappbases.databinding.FragmentMineBinding
import kotlinx.android.synthetic.main.fragment_mine.*

class MineFragment : LazyVmFragment<FragmentMineBinding>() {

    override fun getLayoutId(): Int = R.layout.fragment_mine

    override fun lazyInit() {
        content.text = "$this"
    }
}