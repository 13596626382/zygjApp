package com.tx.zygj.ui.shop.shop.goods

import androidx.lifecycle.MutableLiveData
import com.llx.common.base.BaseViewModel
import com.tx.zygj.bean.ShopBean

class GoodsViewModel : BaseViewModel() {
    private val repository by lazy { GoodsRepository() }

    val shopGoodsBean = MutableLiveData<ArrayList<ShopBean.ShopClassifyBean>>()
    fun getGoods() {
        launch {
            shopGoodsBean.value = repository.getGoods().getData().goods
        }
    }
}