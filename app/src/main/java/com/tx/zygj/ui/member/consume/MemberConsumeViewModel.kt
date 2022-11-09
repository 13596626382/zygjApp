package com.tx.zygj.ui.member.consume

import androidx.lifecycle.MutableLiveData
import com.llx.common.base.BaseViewModel
import com.tx.zygj.bean.ConsumeBean

class MemberConsumeViewModel : BaseViewModel() {
    private val repository by lazy { MemberConsumeRepository() }
    val consumeBean = MutableLiveData<ArrayList<ConsumeBean>>()
    val deletePosition = MutableLiveData<Int>()
    fun getOrderList(memberPhone: String?, years: String?) {
        launch {
            val data = repository.getOrderList(memberPhone, years)
            if (data.code == 0) {
                consumeBean.value = data.getData()
            }
        }
    }

    fun deleteOrder(id: Int, position: Int) {
        launch {
            val data = repository.deleteOrder(id)
            if (data.code == 0) {
                deletePosition.value = position
            }
        }
    }
}