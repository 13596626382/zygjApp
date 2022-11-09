package com.tx.zygj.ui.login

import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.llx.common.CommonConstant
import com.llx.common.base.BaseViewModel
import com.llx.common.util.DatastoreUtil
import com.llx.common.util.toast
import com.llx.common.util.writeContent

class LoginViewModel : BaseViewModel() {
    private val repository by lazy { LoginRepository() }

    val login = MutableLiveData<Boolean>()
    fun login(phone: String, passWord: String) {
        launch {
            val data = repository.login(phone, passWord)
            if (data.status == 500) {
                toast("系统异常")
                login.value = false
                return@launch
            }
            if (data.code == 0) {
                login.value = true
                CommonConstant.setUserInfo(data.getData())
                writeContent(DatastoreUtil.USER_INFO, Gson().toJson(data.getData()))
                toast("登录成功")
            } else {
                toast(data.msg)
                login.value = false
            }
        }
    }
}