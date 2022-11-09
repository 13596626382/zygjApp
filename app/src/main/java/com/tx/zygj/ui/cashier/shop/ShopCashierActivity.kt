package com.tx.zygj.ui.cashier.shop

import com.llx.common.CommonConstant
import com.llx.common.base.BaseActivity
import com.llx.common.util.bind
import com.llx.common.util.intentStringExtras
import com.llx.common.util.setOnSingleClickListener
import com.llx.common.util.startActivity
import com.tx.zygj.R
import com.tx.zygj.bean.ShopBean
import com.tx.zygj.databinding.ActivityShopCashierBinding
import com.tx.zygj.ui.coupon.CouponActivity

/**
 * 商品收银
 */
class ShopCashierActivity :
    BaseActivity<ActivityShopCashierBinding>(R.layout.activity_shop_cashier) {
    override fun initData() {
        binding.titleBar.setOnBack(this)
        binding.titleBar.setTitle(intentStringExtras(CommonConstant.INTENT_TITLE)!!)
        binding.coupon.setOnSingleClickListener {
            startActivity<CouponActivity>()
        }
        binding.recyclerView.bind(ShopCashierAdapter()) {

        }.setList(listOf(ShopBean("1", 1),ShopBean("1", 1),ShopBean("1", 1)))
    }


}