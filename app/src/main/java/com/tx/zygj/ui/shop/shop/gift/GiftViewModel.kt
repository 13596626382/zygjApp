package com.tx.zygj.ui.shop.shop.gift

import androidx.lifecycle.MutableLiveData
import com.llx.common.base.BaseViewModel
import com.tx.zygj.bean.ShopBean

class GiftViewModel : BaseViewModel() {

    private val repository by lazy { GiftRepository() }

    val shopGiftBean = MutableLiveData<ArrayList<ShopBean.ShopClassifyBean>>()
    fun getGift() {
        launch {
            shopGiftBean.value = repository.getGift().getData().gift
        }
    }
}
