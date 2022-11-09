package com.tx.zygj.ui.change

import com.llx.common.base.BaseViewModel
import com.llx.common.util.toast

class ChangePassViewModel : BaseViewModel() {
    private val repository by lazy { ChangePassRepository() }

    fun changePass(phone: String?, passWord: String, newPassWord: String, confirmPassword: String) {
        launch {
            val data = repository.changePass(phone, passWord, newPassWord, confirmPassword)
            if (data.code == 0) {
                requestResult.value = true
            }
            toast(data.msg)
        }
    }
}