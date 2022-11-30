package com.tx.zygj.pop.shop

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.llx.common.util.bind
import com.llx.common.util.setOnSingleClickListener
import com.lxj.xpopup.impl.PartShadowPopupView
import com.tx.zygj.R
import com.tx.zygj.bean.GoodsBean


class ShopCartPopupView(context: Context, private val goodsBean: MutableList<GoodsBean>) :
    PartShadowPopupView(context) {
    private val mAdapter by lazy { ShopCartPopAdapter() }
    lateinit var onChangeQuantityListener: (MutableList<GoodsBean>) -> Unit
    override fun getImplLayoutId() = R.layout.pop_shop
    override fun onCreate() {
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.bind(mAdapter) {
            setList(goodsBean)
            onChangeQuantityListener = {
                this@ShopCartPopupView.onChangeQuantityListener.invoke(it)
            }
            if (goodsBean.isEmpty()) dismiss()


        }
        findViewById<View>(R.id.clear).setOnSingleClickListener {
            goodsBean.forEach { it.quantity = 0 }
            onChangeQuantityListener.invoke(goodsBean)
            goodsBean.clear()
            dismiss()
        }
    }
}