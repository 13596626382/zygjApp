package com.tx.zygj.ui.oil

import androidx.activity.viewModels
import com.google.gson.Gson
import com.llx.common.CommonConstant
import com.llx.common.base.BaseActivity
import com.llx.common.util.bind
import com.llx.common.util.onBackActivity
import com.llx.common.util.setOnSingleClickListener
import com.llx.common.util.toast
import com.tx.zygj.R
import com.tx.zygj.databinding.ActivityOilSettingBinding
import com.tx.zygj.dialog.power.PowerDialogFragment

/**
 * 油价设置
 */
class OilSettingActivity : BaseActivity<ActivityOilSettingBinding>(R.layout.activity_oil_setting) {
    private val mAdapter by lazy { OilSettingAdapter() }
    private val model: OilSettingViewModel by viewModels()
    private var isNull = false //判断是否没有输入金额
    override fun initData() {
        binding.titleBar.setOnBack(this)
        binding.userInfoBean = CommonConstant.getUserInfo()
        model.getOleice(CommonConstant.getUserInfo()?.gasId)
        binding.recyclerView.bind(mAdapter) {
        }
        model.oilBean.observe(this) {
            mAdapter.setList(it)
        }
        binding.confirm.setOnSingleClickListener {
            val fragment = PowerDialogFragment()
            fragment.onPassWordCorrect = {
                isNull = false
                val list = ArrayList<String>()
                mAdapter.data.forEach {
                    it.oil.forEach { it1 ->
                        list.add(Gson().toJson(it1))
                        if (it1.price == 0.00) {
                            toast("${it1.name}价格不能为空")
                            isNull = true
                        }
                    }
                }
                if (!isNull) {
                    model.updateOleic(list)
                }
            }
            fragment.show(supportFragmentManager, CommonConstant.DIALOG_FRAGMENT)
        }
        model.requestResult.observe(this) {
            onBackActivity()
        }
    }
}