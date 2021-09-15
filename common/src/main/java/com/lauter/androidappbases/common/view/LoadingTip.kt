package com.lauter.androidappbases.common.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import com.airbnb.lottie.LottieAnimationView
import com.lauter.androidappbases.common.R

class LoadingTip : ConstraintLayout{

    private var emptyView: View? = null
    private var netErrorView: View? = null
    private var loadingView: View? = null
    private var loadingAnimation: LottieAnimationView? = null
    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initView()
    }

    private fun initView() {
        val view = View.inflate(context, R.layout.loading_tip, this)
        emptyView = view.findViewById(R.id.emptyView)
        loadingAnimation = view.findViewById(R.id.loadingAnimation)
        loadingView = view.findViewById(R.id.loadingView)
        netErrorView = view.findViewById(R.id.netErrorView)
        visibility = View.GONE
    }

    /**
     * 设置网络重连点击事件
     */
    fun setReloadListener(reload:(View)->Unit){
        netErrorView?.setOnClickListener {
            reload.invoke(it)
        }
    }

    /**
     * 显示空白页
     */
    fun showEmpty() {
        visibility = View.VISIBLE
        emptyView?.visibility = View.VISIBLE
        loadingAnimation?.visibility = View.GONE
        loadingView?.visibility = GONE
        loadingAnimation?.clearAnimation()
        netErrorView?.visibility = View.GONE
    }

    /**
     * 显示网络错误
     */
    fun showInternetError() {
        visibility = View.VISIBLE
        netErrorView?.visibility = View.VISIBLE
        emptyView?.visibility = View.GONE
        loadingView?.visibility = GONE
        loadingAnimation?.visibility = View.GONE
        loadingAnimation?.clearAnimation()
    }

    /**
     * 加载
     */
    fun loading() {
        visibility = View.VISIBLE
        loadingView?.visibility = VISIBLE
        loadingAnimation?.visibility = View.VISIBLE
        loadingAnimation?.playAnimation()
        netErrorView?.visibility = View.GONE
        emptyView?.visibility = View.GONE

    }

    /**
     * 隐藏loadingTip
     */
    fun dismiss() {
        loadingAnimation?.clearAnimation()
        visibility = View.GONE
    }

    /**
     * 销毁loadingTip
     */
    fun destroy() {
        parent?.let {
            loadingAnimation?.clearAnimation()
            visibility = View.GONE
            (it as ViewGroup).removeView(this)
        }
    }
}