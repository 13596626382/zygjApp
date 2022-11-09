package com.tx.zygj.pop.shop

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lxj.xpopup.impl.PartShadowPopupView
import com.tx.zygj.R
import com.tx.zygj.bean.ShopBean


class ShopPopupView(context: Context) : PartShadowPopupView(context) {
    private val mAdapter by lazy { ShopPopAdapter() }
    override fun getImplLayoutId() = R.layout.pop_shop
    override fun onCreate() {
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter
        }
        val shopList = ArrayList<ShopBean>()
        for (i in 0..10) {
            shopList.add(ShopBean("XXX商品$i", i + 1))
        }
        mAdapter.setList(shopList)
    }
}