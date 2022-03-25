package com.lauter.androidappbases.module.user

import android.os.Bundle
import com.lauter.androidappbases.base.fragment.BaseFragment
import com.lauter.androidappbases.common.CommonDeeplink
import com.lauter.androidappbases.module.user.databinding.FragmentMineBinding

class MineFragment : BaseFragment<FragmentMineBinding>(){

    override fun init(savedInstanceState: Bundle?) {

    }

    override fun getLayoutId(): Int = R.layout.fragment_mine

    override fun setOnClick() {
        super.setOnClick()
        binding.btnLogin.setOnClickListener { navWithAnim(CommonDeeplink.loginUri) }
        binding.btnBaidu.setOnClickListener {
            navWithAnim(CommonDeeplink.getWebUri(
                "http://www.oppo.com",
                "百度一下"
            ))
        }
    }
}