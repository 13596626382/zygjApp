package com.tx.zygj.ui.order.wallet

import androidx.lifecycle.MutableLiveData
import com.llx.common.CommonConstant
import com.llx.common.base.BaseViewModel
import com.llx.common.util.plus1
import com.llx.common.util.toast
import com.tx.zygj.bean.PaySuccessBean
import com.tx.zygj.bean.WalletRechargeBean

class WalletOrderViewModel : BaseViewModel() {
    private val repository by lazy { WalletOrderRepository() }
    var requestOrderNo = MutableLiveData<String>()
    var paySuccessBean = MutableLiveData<PaySuccessBean>()
    var walletRechargeBean = MutableLiveData<WalletRechargeBean>()

    fun findOrderNo(
        cardType: Int,
        memberPhone: String?,
        money: Double?,
        give: Double?,
        giveIntegral: Int?,
    ) {
        launch {
            val data = repository.findOrderNo(
                cardType, memberPhone, money, CommonConstant.getUserInfo()?.gasId,
                CommonConstant.getUserInfo()?.gasName, give, money?.plus1(give), giveIntegral ?: 0
            )
            if (data.code == 0) {
                requestOrderNo.value = data.getData()
                CommonConstant.setOrderNo(data.getData())
            } else {
                toast(data.msg)
            }
        }
    }

    fun getRechargeActivity(memberId: Int?) {
        launch {
            val data = repository.getRechargeActivity(memberId)
            if (data.code == 0) {
                walletRechargeBean.value = data.getData()
            }
        }
    }

    fun getRechargeActivityDetails(giftId: Int?, money: String) {
        launch {
            val data = repository.getRechargeActivityDetails(giftId, money.toDouble())
            if (data.code == 0) {
                walletRechargeBean.value = data.getData()
            }
        }
    }

    fun play(authCode: String, tradeNo: String, total: Double) {
        launch {
            val data = repository.play(authCode, tradeNo, total)
            if (data.status == 200) {
                paySuccessBean.value = data.getData()
            } else {
                toast(data.message)
            }
            requestResult.value = data.status == 200
        }
    }

}