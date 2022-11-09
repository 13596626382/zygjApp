package com.tx.zygj.ui.member.information

import androidx.lifecycle.MutableLiveData
import com.llx.common.base.BaseViewModel
import com.tx.zygj.bean.MemberManageBean

class MemberInformationViewModel : BaseViewModel() {
    private val repository by lazy { MemberInformationRepository() }
    val memberManageBean = MutableLiveData<MemberManageBean>()
    fun getMemBerMsg(id: Int?) {
        launch {
           val data = repository.getMemBerMsg(id)
            memberManageBean.value = data.getData()
        }
    }
}