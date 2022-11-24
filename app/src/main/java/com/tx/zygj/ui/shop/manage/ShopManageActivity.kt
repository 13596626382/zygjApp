package com.tx.zygj.ui.shop.manage

import android.view.View
import com.llx.common.base.BaseActivity
import com.llx.common.custom.ViewPagerAdapter
import com.llx.common.util.setOnSingleClickListener
import com.tx.zygj.R
import com.tx.zygj.databinding.ActivityShopMamageBinding
import com.tx.zygj.ui.shop.manage.gift.GiftManageFragment
import com.tx.zygj.ui.shop.manage.goods.GoodsManageFragment

/**
 * 商城管理
 */
class ShopManageActivity :
    BaseActivity<ActivityShopMamageBinding>(R.layout.activity_shop_mamage) {

    override fun initData() {
        binding.titleBar.setOnBack(this)
        binding.viewPager2.apply {
            adapter = ViewPagerAdapter(
                this@ShopManageActivity,
                arrayListOf(GoodsManageFragment(), GiftManageFragment())
            )
            isUserInputEnabled = false
        }
        binding.goods.setOnSingleClickListener {
            if (binding.viewPager2.currentItem == 0) return@setOnSingleClickListener
            binding.viewPager2.currentItem = 0
            binding.goodsView.visibility = View.VISIBLE
            binding.giftView.visibility = View.INVISIBLE
        }
        binding.gift.setOnSingleClickListener {
            if (binding.viewPager2.currentItem == 1) return@setOnSingleClickListener
            binding.viewPager2.currentItem = 1
            binding.giftView.visibility = View.VISIBLE
            binding.goodsView.visibility = View.INVISIBLE
        }
    }
}