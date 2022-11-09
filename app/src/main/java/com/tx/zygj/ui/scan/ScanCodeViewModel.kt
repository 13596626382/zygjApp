package com.tx.zygj.ui.scan

import androidx.lifecycle.MutableLiveData
import com.llx.common.base.BaseViewModel
import com.llx.common.util.toast
import com.tx.zygj.bean.MemberManageBean

class ScanCodeViewModel : BaseViewModel() {
    private val repository by lazy { ScanCodeRepository() }
    val memberManageBean = MutableLiveData<MemberManageBean>()
    fun findByPhone(phone: String) {
        launch {
            val data = repository.findByPhone(phone)
            if (data.code == 0) {
                memberManageBean.value = data.getData()
            } else {
                toast(data.message)
            }
        }
    }
}