package com.tx.zygj.ui.car.add

import com.llx.common.base.BaseViewModel
import com.llx.common.util.toast
import com.tx.zygj.bean.CarBrandBean

class AddCarBrandViewModel : BaseViewModel() {
    private val repository by lazy { AddCarBrandRepository() }

    fun addCarNumber(carBrandBean: CarBrandBean?) {
        launch {
            val data = repository.addCarNumber(
                carBrandBean?.carName,
                carBrandBean?.carNumber,
                carBrandBean?.phone,
                carBrandBean?.state
            )
            if (data.code == 0) {
                requestResult.value = true
            }
            toast(data.msg)
        }
    }

    fun updateCarNumber(carBrandBean: CarBrandBean?) {
        launch {
            val data = repository.updateCarNumber(
                carBrandBean?.id,
                carBrandBean?.carName,
                carBrandBean?.carNumber,
                carBrandBean?.phone,
                carBrandBean?.state
            )
            if (data.code == 0) {
                requestResult.value = true
            }
            toast(data.msg)
        }
    }

    fun deleteCarNumber(id: Int?) {
        launch {
            val data = repository.deleteCarNumber(id)
            if (data.code == 0) {
                requestResult.value = true
            }
            toast(data.msg)
        }
    }
}