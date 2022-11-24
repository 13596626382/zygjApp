package com.tx.zygj.ui.exchange.gift

import com.llx.common.base.BaseAdapter
import com.tx.zygj.BR
import com.tx.zygj.R
import com.tx.zygj.bean.GoodsBean
import com.tx.zygj.databinding.ItemGiftExchangeBinding

class GiftExchangeAdapter :
    BaseAdapter<GoodsBean, ItemGiftExchangeBinding>(R.layout.item_gift_exchange, BR.goodsBean)