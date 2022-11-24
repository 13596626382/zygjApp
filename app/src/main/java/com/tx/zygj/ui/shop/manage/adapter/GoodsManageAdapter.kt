package com.tx.zygj.ui.shop.manage.adapter

import com.llx.common.base.BaseAdapter
import com.tx.zygj.BR
import com.tx.zygj.R
import com.tx.zygj.bean.GoodsBean
import com.tx.zygj.databinding.ItemGoodsManageBinding

//商品
class GoodsManageAdapter : BaseAdapter<GoodsBean, ItemGoodsManageBinding>(R.layout.item_goods_manage, BR.goodsBean)