package com.lauter.androidappbases.module.home.search

import com.lauter.androidappbases.base.fragment.LazyVmFragment
import com.lauter.androidappbases.module.home.R
import com.lauter.androidappbases.module.home.databinding.FragmentSearchBinding

class SearchFragment: LazyVmFragment<SearchViewModel,FragmentSearchBinding>() {

    override fun getLayoutId(): Int = R.layout.fragment_search

    override fun lazyInit() {

    }
}