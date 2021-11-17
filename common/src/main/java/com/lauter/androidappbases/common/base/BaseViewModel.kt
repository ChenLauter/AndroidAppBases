package com.lauter.androidappbases.common.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lauter.androidappbases.common.constant.CommonConst.NetWork.STATUS_ERROR
import com.lauter.androidappbases.common.extension.toast
import com.lauter.androidappbases.common.http.ApiException
import com.lauter.androidappbases.common.utils.LogUtil
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONException
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

open class BaseViewModel : ViewModel() {

    private val _errorLiveData = MutableLiveData<ApiException>()

    val errorLiveData: LiveData<ApiException> = _errorLiveData

    protected fun <T> launch(block: () -> T) {
        viewModelScope.launch {
            runCatching {
                block
            }.onFailure {
                it.printStackTrace()
                getApiException(it).apply {
                    _errorLiveData.value = this
                    withContext(Dispatchers.Main) {
                        toast(errorMessage)
                    }
                }
            }
        }
    }

    private fun getApiException(e: Throwable): ApiException {
        LogUtil.e(e.message.toString())
        return when (e) {
            is UnknownHostException -> {
                ApiException("网络异常", STATUS_ERROR)
            }
            is JSONException -> {//|| e is JsonParseException
                ApiException("数据异常", -100)
            }
            is SocketTimeoutException -> {
                ApiException("连接超时", STATUS_ERROR)
            }
            is ConnectException -> {
                ApiException("连接错误", STATUS_ERROR)
            }
            is HttpException -> {
                ApiException("http code ${e.code()}", STATUS_ERROR)
            }
            is ApiException -> {
                e
            }
            /**
             * 如果协程还在运行，个别机型退出当前界面时，viewModel会通过抛出CancellationException，
             * 强行结束协程，与java中InterruptException类似，所以不必理会,只需将toast隐藏即可
             */
            is CancellationException -> {
                ApiException("", -10)
            }
            else -> {
                ApiException("未知错误", -100)
            }
        }
    }
}