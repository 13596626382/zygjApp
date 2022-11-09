package com.tx.zygj.ui.coupon

import com.llx.common.base.BaseActivity
import com.llx.common.util.bind
import com.tx.zygj.R
import com.tx.zygj.databinding.ActivityCouponBinding

/**
 * 查看卡券
 */
class CouponActivity : BaseActivity<ActivityCouponBinding>(R.layout.activity_coupon) {

    override fun initData() {
        binding.titleBar.setOnBack(this)
        binding.recyclerView.bind(CouponAdapter()) {

        }.setList(listOf("","","",""))
    }
}