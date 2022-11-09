package com.llx.common.base

import androidx.lifecycle.MutableLiveData
import com.llx.common.api.RefreshApiBean

abstract class BaseRefreshViewModel<T> : BaseViewModel() {
    val data = MutableLiveData<RefreshApiBean<T>>()

    fun openLoadData(page: Int) {
        launch {
            val data = loadData(page)
            this@BaseRefreshViewModel.data.value = data
        }
    }

    protected abstract suspend fun loadData(page: Int): RefreshApiBean<T>
}