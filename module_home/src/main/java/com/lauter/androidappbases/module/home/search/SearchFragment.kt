package com.lauter.androidappbases.module.home.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.lauter.androidappbases.base.fragment.LazyVmFragment
import com.lauter.androidappbases.base.recyclerview.SimpleDBAdapter
import com.lauter.androidappbases.base.utils.CommonUtil
import com.lauter.androidappbases.module.home.BR
import com.lauter.androidappbases.module.home.R
import com.lauter.androidappbases.module.home.databinding.FragmentSearchBinding
import com.lauter.androidappbases.module.home.databinding.ItemSearchHistoryBinding
import com.lauter.androidappbases.module.home.deeplink.HomeDeeplink

class SearchFragment: LazyVmFragment<SearchViewModel,FragmentSearchBinding>() {

    private val historyAdapter by lazy {
        SimpleDBAdapter<ItemSearchHistoryBinding, String>(
            context,
            R.layout.item_search_history,
            BR.itemBean
        )
    }

    override fun init(savedInstanceState: Bundle?) {
        super.init(savedInstanceState)
        binding.rvHistory.recyclerView.run {
            this.layoutManager = FlexboxLayoutManager(context).apply {
                flexDirection = FlexDirection.ROW
                justifyContent = JustifyContent.FLEX_START
            }
            this.adapter = historyAdapter
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_search

    override fun lazyInit() {
        curVm.getHistoryData()
    }

    override fun setOnClick() {
        super.setOnClick()
        binding.toolbar.run {
            back.setOnClickListener {
                CommonUtil.hideSofiInput(requireActivity())
                nav().navigateUp()
            }
            tvSearch.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun afterTextChanged(p0: Editable) {
                    binding.toolbar.ivClear.visibility = if (p0.isEmpty()) View.GONE else View.VISIBLE
                }

            })
            ivClear.setOnClickListener {
                tvSearch.setText("")
            }
            btnSearch.setOnClickListener {
                val key = tvSearch.editableText.toString()
                if (key.isNotEmpty()) {
                    curVm.getSearchResult(key)
                    navWithAnim(HomeDeeplink.getSearchResultUri())
                }
            }
        }
    }

    override fun observe() {
        super.observe()
        Log.d("bitch", "search fragment on observe")
        curVm.historyData.observe(viewLifecycleOwner) {
            Log.d("bitch", "search fragment history on observer ${it.size}")
            if (it.isEmpty()) {
                binding.groupHistory.visibility = View.GONE
            } else {
                binding.groupHistory.visibility = View.VISIBLE
                historyAdapter.submitData(it)
                Log.d("bitch", "history submit data")
            }
        }
    }
}