package com.tx.zygj.ui.car.add

import androidx.activity.viewModels
import com.llx.common.CommonConstant
import com.llx.common.base.BaseActivity
import com.llx.common.util.*
import com.tx.zygj.R
import com.tx.zygj.bean.CarBrandBean
import com.tx.zygj.databinding.ActivityAddCarBrandBinding

/**
 * 添加/修改车牌信息
 */
class AddCarBrandActivity :
    BaseActivity<ActivityAddCarBrandBinding>(R.layout.activity_add_car_brand) {
    private var carBrandBean: CarBrandBean? = null
    private var title = ""
    private val model: AddCarBrandViewModel by viewModels()
    override fun initData() {
        binding.titleBar.setOnBack(this)
        title = intentStringExtras(CommonConstant.INTENT_TITLE)!!
        binding.titleBar.setTitle(title)
        carBrandBean = intentParcelableExtras(CommonConstant.CAR_BRAND_BEAN)
        if (carBrandBean == null) {
            carBrandBean = CarBrandBean(phone = intentStringExtras(CommonConstant.MEMBER_PHONE)!!)
            binding.titleBar.setImageGone()
        } else {
            binding.carBrandBean = carBrandBean
        }
        binding.carName.addAfterTextChanged {
            carBrandBean?.carName = this
        }
        binding.carNumber.addAfterTextChanged {
            carBrandBean?.carNumber = this
        }
        binding.state.setOnCheckedChangeListener { _, isChecked ->
            carBrandBean?.state = if (isChecked) 1 else 0
        }

        binding.save.setOnSingleClickListener {
            if (title == "修改车辆信息") {
                model.updateCarNumber(carBrandBean)
            } else {
                model.addCarNumber(carBrandBean)
            }
        }

        binding.titleBar.onImageClickListener = {
            showConfirmDialog("删除车牌","确认删除车牌信息") {
                model.deleteCarNumber(carBrandBean?.id)
            }
        }

        model.requestResult.observe(this) {
            onBack()
        }
    }
}