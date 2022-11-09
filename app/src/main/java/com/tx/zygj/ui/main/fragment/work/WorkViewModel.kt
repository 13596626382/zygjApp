package com.tx.zygj.ui.main.fragment.work

import androidx.lifecycle.MutableLiveData
import com.llx.common.CommonConstant
import com.llx.common.base.BaseViewModel
import com.llx.common.util.DatastoreUtil
import com.llx.common.util.toJson
import com.llx.common.util.writeContent
import com.tx.zygj.bean.StatisticsBean

class WorkViewModel : BaseViewModel() {
    private val repository by lazy { WorkRepository() }
    val statisticsBean = MutableLiveData<StatisticsBean>()
    val printBeanResult = MutableLiveData<Boolean>()

    fun getPrintSettings() {
        launch {
            val data = repository.getPrintSettings()
            if (data.code == 0) {
                CommonConstant.setPrintBean(data.getData())
                writeContent(DatastoreUtil.PRINT_SETTING, data.getData().toJson())
            }
            printBeanResult.value = data.code == 0
        }
    }

    fun getStatistics() {
        launch {
            val data = repository.getStatistics()
            if (data.code == 0) {
                statisticsBean.value = data.getData()
            }
        }
    }
}