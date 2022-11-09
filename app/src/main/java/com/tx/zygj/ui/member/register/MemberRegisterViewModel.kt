package com.tx.zygj.ui.member.register

import androidx.lifecycle.MutableLiveData
import com.llx.common.base.BaseViewModel
import com.llx.common.util.toast
import com.tx.zygj.bean.MemberManageBean

class MemberRegisterViewModel : BaseViewModel() {
    private val repository by lazy { MemberRegisterRepository() }


    fun addMember(memberManageBean: MemberManageBean) {
        launch {
            val data = repository.addMember(
                memberManageBean.nickName,
                memberManageBean.phone,
                memberManageBean.birthday,
                memberManageBean.memberType,
                memberManageBean.carNumber,
                memberManageBean.firm,
                memberManageBean.gender,
                memberManageBean.state,
                memberManageBean.password,
                memberManageBean.recommend,
                "", ""
            )
            toast(data.msg)

        }
    }

    fun updateMember(memberManageBean: MemberManageBean) {
        launch {
            val data = repository.updateMember(
                memberManageBean.id,
                memberManageBean.nickName,
                memberManageBean.phone,
                memberManageBean.birthday,
                memberManageBean.memberType,
                memberManageBean.carNumber,
                memberManageBean.firm,
                memberManageBean.gender,
                memberManageBean.state,
                memberManageBean.password,
                memberManageBean.recommend,
                "", ""
            )
            if (data.code == 0) {
                requestResult.value = true
            }
            toast(data.msg)

        }
    }
}