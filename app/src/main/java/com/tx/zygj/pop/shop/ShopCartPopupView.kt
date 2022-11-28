package com.tx.zygj.pop.shop

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.llx.common.util.bind
import com.llx.common.util.getCompatDrawable
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
        findViewById<RecyclerView>(R.id.recyclerView).bind(mAdapter) {
            setList(goodsBean)

            onBind {
                dataBinding!!.addQuantity.setOnSingleClickListener {
                    data[layoutPosition].quantity++
                    dataBinding!!.quantity.visibility = View.VISIBLE
                    dataBinding!!.reduceQuantity.visibility = View.VISIBLE
                    dataBinding!!.quantity.text = data[position].quantity.toString()
                    if (data[layoutPosition].stock == data[position].quantity) {
                        dataBinding!!.addQuantity.isEnabled = false
                        dataBinding!!.addQuantity.setImageDrawable(getCompatDrawable(R.drawable.icon_quantiy_no))
                    }
                    onChangeQuantityListener.invoke(data)
                }
                dataBinding!!.reduceQuantity.setOnSingleClickListener {
                    data[layoutPosition].quantity--
                    dataBinding!!.quantity.text = data[layoutPosition].quantity.toString()
                    if (!dataBinding!!.addQuantity.isEnabled) {
                        dataBinding!!.addQuantity.isEnabled = true
                        dataBinding!!.addQuantity.setImageDrawable(getCompatDrawable(R.drawable.icon_quantiy))
                    }
                    if (data[layoutPosition].quantity == 0) {
                        removeAt(layoutPosition)
                    }
                    onChangeQuantityListener.invoke(data)
                    if (data.isEmpty()) {
                        dismiss()
                    }
                }
            }
        }
        findViewById<View>(R.id.clear).setOnSingleClickListener {
            goodsBean.forEach { it.quantity = 0 }
            onChangeQuantityListener.invoke(goodsBean)
            goodsBean.clear()
            dismiss()
        }
    }
}