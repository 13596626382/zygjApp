package com.tx.zygj.ui.member.reset

import androidx.lifecycle.MutableLiveData
import com.llx.common.base.BaseViewModel
import com.tx.zygj.bean.MemberRechargeRecordBean

class MemberRechargeRecordViewModel : BaseViewModel() {
    private val repository by lazy { MemberRechargeRecordRepository() }
    val memberRechargeRecordBean = MutableLiveData<ArrayList<MemberRechargeRecordBean>>()
    val deletePosition = MutableLiveData<Int>()
    fun findRecharges(memberPhone: String?) {
        launch {
            val data = repository.findRecharges(memberPhone)
            if (data.code == 0) {
                memberRechargeRecordBean.value = data.getData()
            }
        }
    }

    fun deleteRecharges(id: Int, position: Int) {
        launch {
           val data = repository.deleteRecharges(id)
            if (data.code == 0) {
                deletePosition.value = position
            }
        }
    }
}