package com.tx.zygj.ui.shop

import android.view.View
import com.llx.common.CommonConstant
import com.llx.common.base.BaseActivity
import com.llx.common.custom.ViewPagerAdapter
import com.llx.common.util.intentIntExtras
import com.llx.common.util.setOnSingleClickListener
import com.tx.zygj.R
import com.tx.zygj.databinding.ActivityShopBinding
import com.tx.zygj.ui.shop.fragment.integral.IntegralFragment
import com.tx.zygj.ui.shop.fragment.shop.ShopFragment

/**
 * 积分商品
 */
class ShopActivity :
    BaseActivity<ActivityShopBinding>(R.layout.activity_shop) {

    override fun initData() {
        binding.titleBar.setOnBack(this)
        val type = intentIntExtras(CommonConstant.SHOP_TYPE)

        if (type == 1) {
            binding.shopText.text = "商品列表"
            binding.integralText.text = "积分列表"
            binding.titleBar.setTitle("积分商品")
        } else {
            binding.titleBar.setTitle("商品管理")
        }
        binding.viewPager2.apply {
            currentItem = 0
            adapter = ViewPagerAdapter(this@ShopActivity, arrayListOf(ShopFragment(),
                IntegralFragment()
            ))
            isUserInputEnabled = false
        }

        binding.shop.setOnSingleClickListener {
            if (binding.viewPager2.currentItem == 0) return@setOnSingleClickListener
            binding.viewPager2.setCurrentItem(0, false)
            binding.shopView.visibility = View.VISIBLE
            binding.integralView.visibility = View.GONE

        }
        binding.integral.setOnSingleClickListener {
            if (binding.viewPager2.currentItem == 1) return@setOnSingleClickListener
            binding.viewPager2.setCurrentItem(1, false)
            binding.shopView.visibility = View.GONE
            binding.integralView.visibility = View.VISIBLE
        }
    }
}