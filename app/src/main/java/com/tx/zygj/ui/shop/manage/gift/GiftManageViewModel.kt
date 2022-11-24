package com.tx.zygj.ui.shop.manage.gift

import androidx.lifecycle.MutableLiveData
import com.llx.common.api.RefreshApiBean
import com.llx.common.base.BaseRefreshViewModel
import com.llx.common.util.toast
import com.tx.zygj.bean.GoodsBean
import com.tx.zygj.bean.GoodsClassifyBean

class GiftManageViewModel : BaseRefreshViewModel<GoodsBean>() {

    private val repository by lazy { GiftManageRepository() }
    var goodsClassifyId = 0
    val goodsClassifyBean = MutableLiveData<ArrayList<GoodsClassifyBean>>()

    fun getGoodsClassify() {
        launch {
            val data = repository.getGoodsClassify()
            if (data.code == 0) {
                goodsClassifyBean.value = data.getData()
            }
        }
    }


    fun addGoodsClassify(classifyName: String) {
        launch {
            val data = repository.addGoodsClassify(classifyName)
            if (data.code == 0) {
                toast(data.getData())
                getGoodsClassify()
            } else {
                toast(data.msg)
            }
        }
    }

    override suspend fun loadData(page: Int): RefreshApiBean<GoodsBean> =
        repository.getGoods(goodsClassifyId, page)
}