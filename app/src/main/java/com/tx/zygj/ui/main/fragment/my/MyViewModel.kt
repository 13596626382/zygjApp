package com.tx.zygj.ui.main.fragment.my

import androidx.lifecycle.MutableLiveData
import com.llx.common.base.BaseViewModel
import com.tx.zygj.bean.StatisticsBean

class MyViewModel : BaseViewModel() {
    private val repository by lazy { MyRepository() }
    val statisticsBean = MutableLiveData<StatisticsBean>()
    fun getStatistics() {
        launch {
            val data = repository.getStatistics()
            if (data.code == 0) {
                statisticsBean.value = data.getData()
            }
        }
    }

}