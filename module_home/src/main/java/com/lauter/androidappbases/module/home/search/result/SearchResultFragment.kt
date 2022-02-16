package com.lauter.androidappbases.module.home.search.result

import com.lauter.androidappbases.base.fragment.LazyFragment
import com.lauter.androidappbases.base.utils.Param
import com.lauter.androidappbases.module.home.R
import com.lauter.androidappbases.module.home.databinding.FragmentSearchResultBinding

class SearchResultFragment: LazyFragment<FragmentSearchResultBinding>() {

    override fun getLayoutId(): Int = R.layout.fragment_search_result

    @Param
    private val key: String = ""

    override fun lazyInit() {
        binding.key.text = key
    }
}