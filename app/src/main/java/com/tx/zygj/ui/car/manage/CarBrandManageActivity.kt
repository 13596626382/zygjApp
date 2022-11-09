package com.tx.zygj.ui.car.manage

import androidx.activity.viewModels
import com.llx.common.CommonConstant
import com.llx.common.base.BaseActivity
import com.llx.common.util.*
import com.tx.zygj.R
import com.tx.zygj.databinding.ActivityCarBrandManageBinding
import com.tx.zygj.ui.car.add.AddCarBrandActivity

/**
 * 车牌管理
 */
class CarBrandManageActivity :
    BaseActivity<ActivityCarBrandManageBinding>(R.layout.activity_car_brand_manage) {
    private val model: CarBrandManageViewModel by viewModels()
    private val mAdapter by lazy { CarBrandManageAdapter() }
    private var memberPhone = ""
    override fun initData() {
        binding.titleBar.setOnBack(this)
        memberPhone = intentStringExtras(CommonConstant.MEMBER_PHONE)!!
        binding.recyclerView.bind(mAdapter) {
            setOnItemClickListener { _, _, position ->
                logE(data[position].toString())
                startActivity<AddCarBrandActivity>(
                    CommonConstant.INTENT_TITLE to "修改车辆信息",
                    CommonConstant.CAR_BRAND_BEAN to data[position]
                )
            }
            setEmptyView(R.layout.view_empty)
        }

        binding.addCar.setOnSingleClickListener {
            if (mAdapter.data.size >= 5) {
                toast("最多添加五个车牌")
                return@setOnSingleClickListener
            }
            startActivity<AddCarBrandActivity>(
                CommonConstant.INTENT_TITLE to "添加车辆",
                CommonConstant.MEMBER_PHONE to memberPhone
            )
        }

        model.getCarNumber(memberPhone)
        model.carBrandBean.observe(this) {
            mAdapter.setList(it)
        }
    }

    override fun update() {
        model.getCarNumber(memberPhone)
    }
}