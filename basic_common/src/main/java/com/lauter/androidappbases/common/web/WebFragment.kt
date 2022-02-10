package com.lauter.androidappbases.common.web

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.OnBackPressedCallback
import com.lauter.androidappbases.base.fragment.BaseVmFragment
import com.lauter.androidappbases.base.utils.Param
import com.lauter.androidappbases.common.R
import com.lauter.androidappbases.common.databinding.FragmentWebBinding


/***
 * 注意配合 navigation 使用
 */
class WebFragment : BaseVmFragment<WebViewModel, FragmentWebBinding>() {

    @Param
    private var loadUrl: String? = null

    @Param
    private var title: String? = null

    override fun init(savedInstanceState: Bundle?) {
        initView()
    }

    private fun initView() {
        title?.let {
            binding.title.text = it
        }
        binding.btnBack.setOnClickListener { nav().navigateUp() }
        initWebView()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initWebView() {
        val webSettings: WebSettings = binding.webView.settings
        webSettings.javaScriptEnabled = true
        //自适应屏幕
        binding.webView.settings.layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN
        binding.webView.settings.loadWithOverviewMode = true

        //如果不设置WebViewClient，请求会跳转系统浏览器
        binding.webView.webViewClient = object : WebViewClient() {

            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                return !(url.startsWith("http://") || url.startsWith("https://"))
            }

        }

        loadUrl?.let {
            binding.webView.loadUrl(it)
        }

        //设置最大进度
        curVm.maxProgress.set(100)
        //webView加载成功回调
        binding.webView.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView, newProgress: Int) {
                super.onProgressChanged(view, newProgress)
                //进度小于100，显示进度条
                if (newProgress<100){
                    curVm.isVisible.set(true)
                }
                //等于100隐藏
                else if (newProgress==100){
                    curVm.isVisible.set(false)
                }
                //改变进度
                curVm.progress.set(newProgress)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //自定义返回
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (binding.webView.canGoBack()) {
                        //返回上个页面
                        binding.webView.goBack()
                    } else {
                        //退出H5界面
                        nav().navigateUp()
                    }

                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    override fun getLayoutId() = R.layout.fragment_web

}
