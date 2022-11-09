package com.tx.zygj.ui.car.add

import com.tx.zygj.api.BaseRepository

class AddCarBrandRepository: BaseRepository() {

    suspend fun addCarNumber(
        carName: String?, carNumber: String?, phone: String?, state: Int?
    ) = retrofit.addCarNumber(carName, carNumber, phone, state)

    suspend fun updateCarNumber(
        id: Int?, carName: String?, carNumber: String?, phone: String?, state: Int?
    ) = retrofit.updateCarNumber(id, carName, carNumber, phone, state)

    suspend fun deleteCarNumber(id: Int?) = retrofit.deleteCarNumber(id)
}