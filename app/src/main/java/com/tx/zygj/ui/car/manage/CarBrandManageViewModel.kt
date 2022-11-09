package com.tx.zygj.ui.car.manage

import androidx.lifecycle.MutableLiveData
import com.llx.common.base.BaseViewModel
import com.tx.zygj.bean.CarBrandBean

class CarBrandManageViewModel : BaseViewModel() {
    private val repository by lazy { CarBrandManageRepository() }
    val carBrandBean = MutableLiveData<ArrayList<CarBrandBean>>()
    fun getCarNumber(phone: String) {
        launch {
            val data = repository.getCarNumber(phone)
            carBrandBean.value = data.getData()
        }
    }


}