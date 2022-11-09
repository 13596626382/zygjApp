package com.tx.zygj.ui.cashier.refueling

import androidx.lifecycle.MutableLiveData
import com.llx.common.CommonConstant
import com.llx.common.base.BaseViewModel
import com.tx.zygj.bean.OilBean
import com.tx.zygj.bean.OilGunBean
import com.tx.zygj.bean.OilerBean

class RefuelingCashierViewModel : BaseViewModel() {
    private val repository by lazy { RefuelingCashierRepository() }
    val oilBean = MutableLiveData<ArrayList<OilBean>>()
    val oilGunBean = MutableLiveData<ArrayList<OilGunBean>>()
    val oilerBean = MutableLiveData<ArrayList<OilerBean>>()
    fun getOleice() {
        launch {
            val data = repository.getOleice(CommonConstant.getUserInfo()?.gasId)
            oilBean.value = data.getData()
        }
    }

    fun getOilGun(id: Int) {
        launch {
            val data = repository.getOilGun(id)
            oilGunBean.value = if (data.getData() != null) {
                data.getData()
            } else {
                arrayListOf()
            }

        }
    }

    fun getOiler(id: Int?) {
        launch {
            val data = repository.getOiler(id)
            oilerBean.value = data.getData()
        }
    }


}