package com.tx.zygj.dialog.power

import com.llx.common.CommonConstant
import com.llx.common.base.BaseViewModel
import kotlinx.coroutines.delay

class PowerDialogViewModel : BaseViewModel() {
    private val repository by lazy { PowerDialogRepository() }
    fun handover(passWord: String) {
        launch {
            delay(1000)
            val data = repository.handover(CommonConstant.getUserInfo()?.phone, passWord)
            requestResult.value = data.code == 0
        }
    }
}