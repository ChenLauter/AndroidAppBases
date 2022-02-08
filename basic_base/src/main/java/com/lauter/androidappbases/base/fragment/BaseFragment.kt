package com.lauter.androidappbases.base.fragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment
import com.lauter.androidappbases.base.utils.ParamUtil
import com.lauter.androidappbases.base.utils.StatusBarUtil

abstract class BaseFragment<BD: ViewDataBinding> : Fragment() {

    private var fragmentProvider: ViewModelProvider? = null
    private var activityProvider: ViewModelProvider? = null
    protected lateinit var binding: BD

    override fun onAttach(context: Context) {
        super.onAttach(context)
        // 必须要在Activity与Fragment绑定后，因为如果Fragment可能获取的是Activity中ViewModel
        // 必须在onCreateView之前初始化viewModel，因为onCreateView中需要通过ViewModel与DataBinding绑定
        initViewModel()
        ParamUtil.initParam(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //由于同一个fragment对象可能被activity attach多次(比如viewPager中)
        //所以fragmentViewModel不能放在onAttach初始化，否则会产生多个fragmentViewModel
        initFragmentViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        getLayoutId()?.let {
            //获取ViewDataBinding
            binding = DataBindingUtil.inflate(inflater, it, container, false)
            //将ViewDataBinding生命周期与Fragment绑定
            binding.lifecycleOwner = viewLifecycleOwner
            //设置状态栏高度
            val id = resources.getIdentifier("fake_status_bar","id",context?.packageName)
            binding.root.findViewById<View>(id)?.let { bar ->
                bar.layoutParams.height = StatusBarUtil.getStatusBarHeight()
            }
            return binding.root
        }
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(savedInstanceState)
        //observe一定要在初始化最后，因为observe会收到黏性事件，随后对ui做处理
        observe()
        setOnClick()
    }

    /**
     * 初始化viewModel
     * 之所以没有设计为抽象，是因为部分简单activity可能不需要viewModel
     * observe同理
     */
    open fun initViewModel() {

    }

    open fun initFragmentViewModel() {

    }

    /**
     * 注册观察者
     */
    open fun observe() {

    }

    /**
     * 通过activity获取viewModel，跟随activity生命周期
     */
    protected fun <T : ViewModel?> getActivityViewModel(modelClass: Class<T>): T {
        if (activityProvider == null) {
            activityProvider = ViewModelProvider(requireActivity())
        }
        return activityProvider!!.get(modelClass)
    }

    /**
     * 通过fragment获取viewModel，跟随fragment生命周期
     */
    protected open fun <T : ViewModel?> getFragmentViewModel(modelClass: Class<T>): T {
        if (fragmentProvider == null) {
            fragmentProvider = ViewModelProvider(this)
        }
        return fragmentProvider!!.get(modelClass)
    }

    /**
     * fragment跳转
     */
    protected fun nav(): NavController {
        return NavHostFragment.findNavController(this)
    }

    // 防抖
    private var lastNavTime = 0L

    /***
     * 带有默认动画的deep link跳转
     */
    protected fun navigate(url: String, navBuilder: NavOptions.Builder?=null) {
        try {
            val now = System.currentTimeMillis()
            if (now - lastNavTime < 2000) {
                return
            }
            val builder = navBuilder ?: NavOptions.Builder()
//            builder.setEnterAnim(R.anim.right_in)
//                .setExitAnim(R.anim.left_out)
//                .setPopExitAnim(R.anim.right_out)
//                .setPopEnterAnim(R.anim.left_in)
            nav().navigate(Uri.parse(url),builder.build())
            lastNavTime = now
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * 点击事件
     */
    open fun setOnClick() {

    }

    abstract fun init(savedInstanceState: Bundle?)

    abstract fun getLayoutId(): Int?
}