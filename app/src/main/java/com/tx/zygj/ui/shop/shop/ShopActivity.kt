package com.tx.zygj.ui.shop.shop

import android.content.Intent
import android.view.View
import com.llx.common.CommonConstant
import com.llx.common.base.BaseActivity
import com.llx.common.custom.ViewPagerAdapter
import com.llx.common.util.intentParcelableExtras
import com.llx.common.util.setOnSingleClickListener
import com.tx.zygj.R
import com.tx.zygj.bean.MemberManageBean
import com.tx.zygj.databinding.ActivityShopMamageBinding
import com.tx.zygj.ui.search.SearchActivity
import com.tx.zygj.ui.shop.shop.gift.GiftFragment
import com.tx.zygj.ui.shop.shop.goods.GoodsFragment

/**
 * 积分商品
 */
class ShopActivity :
    BaseActivity<ActivityShopMamageBinding>(R.layout.activity_shop_mamage) {
    var memberManageBean: MemberManageBean? = null //会员

    override fun initData() {
        binding.titleBar.setOnBack(this)
        binding.viewPager2.apply {
            adapter = ViewPagerAdapter(
                this@ShopActivity,
                arrayListOf(GoodsFragment(), GiftFragment())
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
        binding.search.setOnSingleClickListener {
            startActivityForResult(
                Intent(mContext, SearchActivity::class.java).putExtra(
                    CommonConstant.SEARCH_TYPE, true
                ), CommonConstant.REQUEST_CODE
            )
        }
    }

    fun clearMember() {
        memberManageBean = null
        binding.titleBar.setRightText("")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == CommonConstant.REQUEST_CODE) {
            memberManageBean = data?.intentParcelableExtras(CommonConstant.MEMBER_BEAN)
            binding.titleBar.setRightText(memberManageBean?.nickName)
        }
    }

}