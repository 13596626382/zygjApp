package com.tx.zygj.ui.handover

import androidx.lifecycle.MutableLiveData
import com.llx.common.CommonConstant
import com.llx.common.base.BaseViewModel
import com.llx.common.util.DatastoreUtil
import com.llx.common.util.startActivity
import com.llx.common.util.writeContent
import com.lxj.xpopup.impl.LoadingPopupView
import com.tx.zygj.bean.HandoverBean
import com.tx.zygj.ui.login.LoginActivity
import kotlinx.coroutines.delay

class HandoverViewModel : BaseViewModel() {
    private val repository by lazy { HandoverRepository() }
    val handoverBean = MutableLiveData<HandoverBean>()
    fun getExchange() {
        launch {
            val data = repository.getExchange()
            if (data.code == 0) {
                handoverBean.value = data.getData()
            }
        }
    }

    fun logout(popUpView: LoadingPopupView) {
        launch {
            delay(1000)
            CommonConstant.cleanUserInfo()
            writeContent(DatastoreUtil.USER_INFO, "")
            popUpView.dismiss()
            startActivity<LoginActivity>()
        }
    }
}