package com.tx.zygj.ui.oil

import androidx.lifecycle.MutableLiveData
import com.llx.common.base.BaseViewModel
import com.llx.common.util.toast
import com.tx.zygj.bean.OilBean
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody

class OilSettingViewModel : BaseViewModel() {
    private val repository by lazy { OilSettingRepository() }
    val oilBean = MutableLiveData<ArrayList<OilBean>>()
    fun getOleice(id: Int?) {
        launch {
            val data = repository.getOleice(id)
            oilBean.value = data.getData()
        }
    }

    fun updateOleic(list: ArrayList<String>) {
        val body = list.toString().toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())
        launch {
            val data = repository.updateOleic(body)
            if (data.code == 0) {
                requestResult.value = true
            }
            toast(data.msg)
        }
    }
}