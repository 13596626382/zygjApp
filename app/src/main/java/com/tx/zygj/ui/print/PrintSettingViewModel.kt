package com.tx.zygj.ui.print

import androidx.lifecycle.MutableLiveData
import com.llx.common.CommonConstant
import com.llx.common.base.BaseViewModel
import com.llx.common.bean.PrintBean
import com.llx.common.util.DatastoreUtil
import com.llx.common.util.toJson
import com.llx.common.util.toast
import com.llx.common.util.writeContent

class PrintSettingViewModel : BaseViewModel() {
    private val repository by lazy { PrintSettingRepository() }
    val printBean = MutableLiveData<PrintBean>()
    fun getPrintSettings() {
        launch {
            val data = repository.getPrintSettings()
            if (data.code == 0) {
                printBean.value = data.getData()
                CommonConstant.setPrintBean(data.getData())
                writeContent(DatastoreUtil.PRINT_SETTING, data.getData().toJson())
            } else {
                toast(data.message)
            }
            requestResult.value = data.code == 0
        }
    }

    fun updatePrintSettings(printBean: PrintBean?) {
        launch {
            val data = repository.updatePrintSettings(printBean)
            if (data.code == 0) {
                CommonConstant.setPrintBean(printBean)
                toast("修改成功")
            }

        }
    }
}