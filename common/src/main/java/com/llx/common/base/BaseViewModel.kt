package com.llx.common.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.llx.common.util.isNetworkAvailable
import com.llx.common.util.toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.net.ConnectException
import java.net.SocketTimeoutException

abstract class BaseViewModel : ViewModel() {
    var requestResult = MutableLiveData<Boolean>()
    var noNetwork = MutableLiveData<Boolean>() //没有网络
    var exception = MutableLiveData<Boolean>() //异常结束请求
    protected fun launch(block: suspend CoroutineScope.() -> Unit) {
        launch(block, null)
    }


   protected fun launch(
        block: suspend CoroutineScope.() -> Unit,
        error: (Throwable.() -> Unit)? = null,
    ) {
        viewModelScope.launch {
            try {
                block.invoke(this)
            } catch (e: Exception) {
                if (!isNetworkAvailable) {
                    toast("网络连接中断")
                    noNetwork.value = true
                }
                when(e) {
                    is SocketTimeoutException -> toast("网络连接超时")
                    is ConnectException -> toast("服务器异常")
                }
                requestResult.value = false
                error?.invoke(e)
            }
        }
    }
}