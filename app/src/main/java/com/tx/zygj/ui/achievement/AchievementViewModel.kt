package com.tx.zygj.ui.achievement

import androidx.lifecycle.MutableLiveData
import com.llx.common.base.BaseViewModel
import com.tx.zygj.bean.HandoverBean

class AchievementViewModel : BaseViewModel() {
    private val repository by lazy { AchievementRepository() }
    val handoverBean = MutableLiveData<HandoverBean>()
    fun getExchange() {
        launch {
            val data = repository.getExchange()
            if (data.code == 0) {
                handoverBean.value = data.getData()
            }
        }
    }
}