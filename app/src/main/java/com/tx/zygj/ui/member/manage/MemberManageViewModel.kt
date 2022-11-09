package com.tx.zygj.ui.member.manage

import androidx.lifecycle.MutableLiveData
import com.llx.common.base.BaseViewModel
import com.tx.zygj.bean.MemberManageBean
import kotlinx.coroutines.flow.flow

class MemberManageViewModel : BaseViewModel() {
    private val repository by lazy { MemberManageRepository() }
    val memberManageBean = MutableLiveData<ArrayList<MemberManageBean>>()
    fun getMember() {
        launch {
            val data = repository.getMember()
            memberManageBean.value = data.getData()
        }
    }


    val foa = flow {
        emit(1)
    }
}