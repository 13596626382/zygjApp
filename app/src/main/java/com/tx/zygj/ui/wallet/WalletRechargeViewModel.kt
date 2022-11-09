package com.tx.zygj.ui.wallet

import androidx.lifecycle.MutableLiveData
import com.llx.common.CommonConstant
import com.llx.common.base.BaseViewModel
import com.tx.zygj.bean.OilerBean

class WalletRechargeViewModel : BaseViewModel() {
    private val repository by lazy { WalletRechargeRepository() }
    val oilerBean = MutableLiveData<ArrayList<OilerBean>>()
    fun getOiler() {
        launch {
            val data = repository.getOiler(CommonConstant.getUserInfo()?.gasId)
            oilerBean.value = data.getData()
        }
    }
}