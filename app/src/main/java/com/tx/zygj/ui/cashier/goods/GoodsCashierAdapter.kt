package com.tx.zygj.ui.cashier.goods

import com.llx.common.base.BaseAdapter
import com.tx.zygj.BR
import com.tx.zygj.R
import com.tx.zygj.bean.GoodsBean
import com.tx.zygj.databinding.ItemGoodsCashierBinding

class GoodsCashierAdapter :
    BaseAdapter<GoodsBean, ItemGoodsCashierBinding>(R.layout.item_goods_cashier, BR.goodsBean)