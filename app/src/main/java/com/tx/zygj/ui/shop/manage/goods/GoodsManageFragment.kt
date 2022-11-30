package com.tx.zygj.ui.shop.manage.goods

import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.llx.common.CommonConstant
import com.llx.common.base.BaseFragment
import com.llx.common.util.bind
import com.llx.common.util.getCompatDrawable
import com.llx.common.util.withArguments
import com.lxj.xpopup.XPopup
import com.tx.zygj.R
import com.tx.zygj.bean.GoodsClassifyBean
import com.tx.zygj.databinding.FragmentGoodsManageBinding
import com.tx.zygj.databinding.ViewAdd1Binding
import com.tx.zygj.databinding.ViewAddBinding
import com.tx.zygj.dialog.shop.ManageShopDialogFragment
import com.tx.zygj.ui.shop.manage.adapter.ClassifyManageAdapter
import com.tx.zygj.ui.shop.manage.adapter.GoodsManageAdapter

//商品管理
class GoodsManageFragment :
    BaseFragment<FragmentGoodsManageBinding>(R.layout.fragment_goods_manage) {
    private val mClassifyManageAdapter by lazy { ClassifyManageAdapter() }
    private val mGoodsManageAdapter by lazy { GoodsManageAdapter() }
    private val model: GoodsManageViewModel by viewModels()
    private var goodsClassifyBean: ArrayList<GoodsClassifyBean>? = null
    override fun initData() {

        binding.sortRecyclerView.bind(mClassifyManageAdapter) {
            setOnItemClickListener { _, _, position ->
                if (data[position].isCheck) return@setOnItemClickListener
                data.forEach {
                    it.isCheck = false
                }
                data[position].isCheck = true
                notifyDataSetChanged()

                mGoodsManageAdapter.setList(arrayListOf())
                model.goodsClassifyId = data[position].id
                binding.refreshView.loaData()
            }
        }

        val viewAddBinding = DataBindingUtil.inflate<ViewAddBinding>(
            layoutInflater,
            R.layout.view_add,
            binding.sortRecyclerView,
            false
        )
        val viewAddBinding1 = DataBindingUtil.inflate<ViewAdd1Binding>(
            layoutInflater,
            R.layout.view_add_1,
            binding.sortRecyclerView,
            false
        )
        viewAddBinding1.root.background = getCompatDrawable(R.drawable.fillet_ffffff_4)

        mClassifyManageAdapter.addFooterView(viewAddBinding.root)
        mGoodsManageAdapter.addFooterView(viewAddBinding1.root)
        viewAddBinding.root.setOnClickListener {
            XPopup.Builder(mContext)
                .hasStatusBarShadow(false) //.dismissOnBackPressed(false)
                .isDestroyOnDismiss(true) //对于只使用一次的弹窗对象，推荐设置这个
                .autoOpenSoftInput(true)
                .isDarkTheme(false) //                        .isViewMode(true)
                .asInputConfirm(
                    "添加商品分类", "", null, "输入商品分类名称"
                ) {
                    model.addGoodsClassify(it)
                }
                .show()
        }
        viewAddBinding1.root.setOnClickListener {
            val dialogFragment = ManageShopDialogFragment()
            dialogFragment.withArguments(
                "goodsClassifyBean" to goodsClassifyBean,
                "goodsOrGift" to 1
            )
            dialogFragment.onResultSuccess = {
                binding.refreshView.loaData()
            }
            dialogFragment.show(childFragmentManager, CommonConstant.DIALOG_FRAGMENT)
        }
        mGoodsManageAdapter.setOnItemClickListener { _, _, position ->
            val dialogFragment = ManageShopDialogFragment()
            dialogFragment.withArguments(
                "goodsClassifyBean" to goodsClassifyBean,
                "goodsBean" to mGoodsManageAdapter.data[position],
                "goodsOrGift" to 1
            )
            dialogFragment.onResultSuccess = {
                binding.refreshView.loaData()
            }
            dialogFragment.show(childFragmentManager, CommonConstant.DIALOG_FRAGMENT)
        }
        binding.refreshView.apply {
            setViewModel(model)
            setLifecycleOwner(this@GoodsManageFragment)
            setAdapter(mGoodsManageAdapter)
        }
        model.apply {
            getGoodsClassify()
            goodsClassifyBean.observe(this@GoodsManageFragment) {
                mClassifyManageAdapter.isFirst = false
                mClassifyManageAdapter.setList(it)
                if (it.isNotEmpty()) {
                    model.goodsClassifyId = it[0].id
                }
                this@GoodsManageFragment.goodsClassifyBean = it
                binding.refreshView.loaData()
            }
        }
    }
}