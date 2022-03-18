package com.lauter.androidappbases.module.home.search

import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.RecyclerView
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.lauter.androidappbases.base.extension.dp2px
import com.lauter.androidappbases.base.fragment.LazyVmFragment
import com.lauter.androidappbases.base.recyclerview.BaseDBAdapter
import com.lauter.androidappbases.base.recyclerview.SimpleDBAdapter
import com.lauter.androidappbases.base.utils.ColorUtil
import com.lauter.androidappbases.base.utils.CommonUtil
import com.lauter.androidappbases.module.home.BR
import com.lauter.androidappbases.module.home.R
import com.lauter.androidappbases.module.home.databinding.FragmentSearchBinding
import com.lauter.androidappbases.module.home.databinding.ItemSearchHistoryBinding
import com.lauter.androidappbases.module.home.deeplink.HomeDeeplink

class SearchFragment: LazyVmFragment<SearchViewModel,FragmentSearchBinding>() {

    override fun init(savedInstanceState: Bundle?) {
        super.init(savedInstanceState)
        binding.rvHistory.recyclerView.run {
            this.layoutManager = FlexboxLayoutManager(context).apply {
                flexDirection = FlexDirection.ROW
                justifyContent = JustifyContent.FLEX_START
            }
            this.adapter = historyAdapter
        }
        binding.rvHot.recyclerView.run {
            this.layoutManager = FlexboxLayoutManager(context).apply {
                flexDirection = FlexDirection.ROW
                justifyContent = JustifyContent.CENTER
            }
            this.adapter = hotAdapter
        }
    }

    private val historyAdapter by lazy {
        SimpleDBAdapter<ItemSearchHistoryBinding, String>(
            context,
            R.layout.item_search_history,
            BR.itemBean
        ).apply {
            onItemClickListener = {
                navToResult(data[it])
            }
        }
    }

    private val hotAdapter by lazy {
        object : BaseDBAdapter<ItemSearchHistoryBinding, String>(
            context,
            R.layout.item_search_history
        ) {
            override fun onBindViewHolder(db: ItemSearchHistoryBinding, position: Int) {
                db.run {
                    itemBean = data[position]
                    content.run {
                        val color = ColorUtil.randomColor()
                        setTextColor(color)
                        background = GradientDrawable().apply {
                            setStroke(context.dp2px(1f).toInt(),color)
                        }
                        updateLayoutParams<RecyclerView.LayoutParams> {
                            marginEnd = context.dp2px(20f).toInt()
                            bottomMargin = context.dp2px(15f).toInt()
                        }
                    }
                    root.setOnClickListener { navToResult(data[position]) }
                }
            }

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("bitch", "search fragment on create view")
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun getLayoutId(): Int = R.layout.fragment_search

    override fun lazyInit() {
        curVm.getHistoryData()
        curVm.getHotKey()
    }

    override fun setOnClick() {
        super.setOnClick()
        binding.toolbar.run {
            back.setOnClickListener {
                CommonUtil.hideSofiInput(requireActivity())
                nav().navigateUp()
            }
            tvSearch.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                }
                override fun afterTextChanged(s: Editable) {
                    binding.toolbar.ivClear.visibility = if (s.isEmpty()) View.GONE else View.VISIBLE
                }

            })
            ivClear.setOnClickListener {
                tvSearch.setText("")
            }
            btnSearch.setOnClickListener {
                navToResult(tvSearch.editableText.toString())
            }
        }
        binding.ivTrashcan.setOnClickListener { curVm.clearHistory() }
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
            }
        }
        curVm.hotKeyData.observe(this) {
            if (it.isEmpty()) {
                binding.groupHot.visibility = View.GONE
            } else {
                binding.groupHot.visibility = View.VISIBLE
                hotAdapter.submitData(it)
            }
        }
    }

    private fun navToResult(key: String) {
        if (key.isNotEmpty()) {
            curVm.getSearchResult(key)
            navWithAnim(HomeDeeplink.getSearchResultUri(key))
        }
    }
}