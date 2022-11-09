package com.tx.zygj.ui.car.manage

import com.llx.common.base.BaseAdapter
import com.tx.zygj.BR
import com.tx.zygj.R
import com.tx.zygj.bean.CarBrandBean
import com.tx.zygj.databinding.ItemCarBrandManageBinding

class CarBrandManageAdapter :
    BaseAdapter<CarBrandBean, ItemCarBrandManageBinding>(R.layout.item_car_brand_manage, BR.carBrandBean)