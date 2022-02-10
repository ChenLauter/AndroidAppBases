package com.lauter.androidappbases.module.user.login

import com.lauter.androidappbases.base.fragment.LazyFragment
import com.lauter.androidappbases.module.user.R
import com.lauter.androidappbases.module.user.databinding.FragmentLoginBinding

class LoginFragment: LazyFragment<FragmentLoginBinding>() {

    override fun getLayoutId(): Int = R.layout.fragment_login

    override fun lazyInit() {

    }
}