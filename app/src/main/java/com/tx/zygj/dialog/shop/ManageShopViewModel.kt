package com.tx.zygj.dialog.shop

import com.llx.common.base.BaseViewModel
import com.llx.common.util.toast
import com.tx.zygj.bean.GoodsBean
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class ManageShopViewModel : BaseViewModel() {
    private val repository by lazy { ManageShopRepository() }

    fun upload(file: File, goodsBean: GoodsBean?, isUpdate: Boolean = false, goodsOrGift: Int) {
        launch {
            val requestBody = file.asRequestBody("image/jpeg".toMediaTypeOrNull())
            val body = MultipartBody.Part.createFormData("file", file.name, requestBody)
            val data = repository.uploadImage(body)
            if (data.code == 0) {
                if (!isUpdate) {
                    addGoods(data.getData(), goodsBean, goodsOrGift)
                } else {
                    updateGoods(data.getData(), goodsBean)
                }
            } else {
                toast(data.msg)
                requestResult.value = false
            }
        }
    }

    private fun addGoods(
        path: String, goodsBean: GoodsBean?, goodsOrGift: Int
    ) {
        launch {
            val data = repository.addGoods(
                path,
                goodsBean?.barCode,
                goodsBean?.goodsName,
                goodsBean?.goodsClassifyId,
                goodsBean?.sellingPrice,
                goodsBean?.marketPrice,
                goodsBean?.costPrice,
                goodsBean?.goodsUnit,
                goodsBean?.bonusPoints,
                goodsBean?.stock,
                goodsBean?.publishOrNot,
                goodsBean?.mallRelease,
                goodsBean?.recommend,
                goodsBean?.hotOrNot,
                goodsBean?.goodsDescribe,
                goodsOrGift
            )
            if (data.code == 0) {
                toast(data.getData())
            } else {
                toast(data.msg)
            }
            requestResult.value = data.code == 0
        }
    }

    fun updateGoods(path: String = "", goodsBean: GoodsBean?) {
        launch {
            val data = repository.updateGoods(
                goodsBean?.id,
                if (path == "") goodsBean?.goodsPicture else path,
                goodsBean?.barCode,
                goodsBean?.goodsName,
                goodsBean?.goodsClassifyId,
                goodsBean?.sellingPrice,
                goodsBean?.marketPrice,
                goodsBean?.costPrice,
                goodsBean?.goodsUnit,
                goodsBean?.bonusPoints,
                goodsBean?.stock,
                goodsBean?.publishOrNot,
                goodsBean?.mallRelease,
                goodsBean?.recommend,
                goodsBean?.hotOrNot,
                goodsBean?.goodsDescribe,
                goodsBean?.goodsOrGift!!
            )
            if (data.code == 0) {
                toast(data.getData())
            } else {
                toast(data.msg)
            }
            requestResult.value = data.code == 0
        }
    }
}