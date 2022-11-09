package com.tx.zygj.ui.shop.fragment.shop

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.llx.common.CommonConstant
import com.llx.common.base.BaseFragment
import com.llx.common.util.intentIntExtras
import com.llx.common.util.showPopupView
import com.llx.common.util.startActivity
import com.lxj.xpopup.XPopup
import com.tx.zygj.R
import com.tx.zygj.bean.ShopBean
import com.tx.zygj.bean.ShopSortBean
import com.tx.zygj.databinding.FragmentShopIntegralBinding
import com.tx.zygj.databinding.ViewAddBinding
import com.tx.zygj.dialog.shop.ManageShopDialogFragment
import com.tx.zygj.pop.shop.ShopPopupView
import com.tx.zygj.ui.cashier.shop.ShopCashierActivity
import com.tx.zygj.ui.shop.fragment.adapter.ShopAdapter
import com.tx.zygj.ui.shop.fragment.adapter.ShopSortAdapter

/**
 * 商品
 */
class ShopFragment : BaseFragment<FragmentShopIntegralBinding>(R.layout.fragment_shop_integral) {
    private val mSortAdapter by lazy { ShopSortAdapter() }
    private val mShopAdapter by lazy { ShopAdapter() }
    private var shopQuantity = 0
    override fun initData() {
        val type = requireActivity().intentIntExtras(CommonConstant.SHOP_TYPE)
        val dialogFragment = ManageShopDialogFragment()
        binding.sortRecyclerView.apply {
            layoutManager = LinearLayoutManager(mContext)
            adapter = mSortAdapter
        }
        binding.shopRecyclerView.apply {
            layoutManager = LinearLayoutManager(mContext)
            adapter = mShopAdapter
        }

        if (type == 0 && binding.linearLayout6.visibility != View.GONE) {
            binding.linearLayout6.visibility = View.GONE
            val viewAddBinding = DataBindingUtil.inflate<ViewAddBinding>(
                layoutInflater,
                R.layout.view_add,
                binding.sortRecyclerView,
                false
            )
            val viewAddBinding1 = DataBindingUtil.inflate<ViewAddBinding>(
                layoutInflater,
                R.layout.view_add,
                binding.sortRecyclerView,
                false
            )
            mSortAdapter.addFooterView(viewAddBinding.root)
            mShopAdapter.addFooterView(viewAddBinding1.root)
            viewAddBinding.root.setOnClickListener {
                XPopup.Builder(context)
                    .hasStatusBarShadow(false) //.dismissOnBackPressed(false)
                    .isDestroyOnDismiss(true) //对于只使用一次的弹窗对象，推荐设置这个
                    .autoOpenSoftInput(true)
                    .isDarkTheme(false) //                        .isViewMode(true)
                    .asInputConfirm(
                        "添加商品分类", "", null, "输入商品分类名称"
                    ) {}
                    .show()
            }

            viewAddBinding1.root.setOnClickListener {
                dialogFragment.show(childFragmentManager, CommonConstant.DIALOG_FRAGMENT)
            }
            mShopAdapter.setOnItemClickListener { _, _, _ ->
                dialogFragment.show(childFragmentManager, CommonConstant.DIALOG_FRAGMENT)
            }
        }
        val list = ArrayList<ShopSortBean>()
        val shopList = ArrayList<ShopBean>()
        for (i in 0..3) {
            list.add(ShopSortBean("商品$i", i == 0))
            shopList.add(ShopBean("XXX商品$i", 0))
        }
        mSortAdapter.setList(list)
        mShopAdapter.setList(shopList)
        mShopAdapter.onQuantityChangeListener = {
            shopQuantity = if (it) shopQuantity + 1 else shopQuantity - 1
            if (shopQuantity < 1) {
                binding.shopCount.visibility = View.GONE
            } else {
                binding.shopCount.visibility = View.VISIBLE
                binding.shopCount.text = shopQuantity.toString()
            }

        }
        binding.shop.setOnClickListener {
            mContext.showPopupView(binding.linearLayout6, ShopPopupView(mContext))
        }
        binding.settle.setOnClickListener {
            startActivity<ShopCashierActivity>(CommonConstant.INTENT_TITLE to "商品收银")
        }
    }
}