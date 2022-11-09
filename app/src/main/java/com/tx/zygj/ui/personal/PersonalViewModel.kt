package com.tx.zygj.ui.personal

import com.google.gson.Gson
import com.llx.common.CommonConstant
import com.llx.common.base.BaseViewModel
import com.llx.common.bean.UserInfoBean
import com.llx.common.util.DatastoreUtil
import com.llx.common.util.toast
import com.llx.common.util.writeContent

class PersonalViewModel : BaseViewModel() {
    private val repository by lazy { PersonalRepository() }


    fun updatePersonal(userInfoBean: UserInfoBean?) {
        launch {
            val data = repository.updatePersonal(
                userInfoBean?.id,
                userInfoBean?.userName,
                userInfoBean?.name,
                userInfoBean?.phone,
                userInfoBean?.workNumber
            )
            if (data.code == 0) {
                CommonConstant.setUserInfo(userInfoBean)
                writeContent(DatastoreUtil.USER_INFO, Gson().toJson(userInfoBean))
                requestResult.value = true
            }
            toast(data.msg)
        }
    }
}