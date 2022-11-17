package com.tx.zygj.ui.achievement

import androidx.activity.viewModels
import com.llx.common.CommonConstant
import com.llx.common.base.BaseActivity
import com.llx.common.util.*
import com.lxj.xpopup.impl.LoadingPopupView
import com.tx.zygj.R
import com.tx.zygj.databinding.ActivityAchievementBinding
import com.tx.zygj.util.SunmiPrintHelper

/**
 * 业绩详情
 */
class AchievementActivity :
    BaseActivity<ActivityAchievementBinding>(R.layout.activity_achievement) {
    private val model: AchievementViewModel by viewModels()
    private val mAdapter by lazy { AchievementAdapter() }
    private lateinit var popUpView: LoadingPopupView
    override fun initData() {
        binding.titleBar.setOnBack(this)
        binding.oilName.text = CommonConstant.getUserInfo()?.gasName
        binding.operation.text = stringBuild("操作员：", CommonConstant.getUserInfo()?.name)
        binding.time.text = stringBuild(
            CommonConstant.getUserInfo()?.startTime?.toYMDHMSDate(),
            " 至 ",
            toCurrentYMDHMSDate()
        )
        popUpView = showLoadingDialog("打印小票")
        binding.shiftHandover.showConfirmDialog(content = "是否小票") {
            SunmiPrintHelper.sendShiftHandoverRawData(binding.handoverBean)
        }

        binding.recyclerView.bind(mAdapter) {
            setEmptyView(layoutInflater.inflate(R.layout.view_empty, binding.recyclerView, false))
        }
        model.getExchange()
        model.handoverBean.observe(this) {
            binding.handoverBean = it
            mAdapter.setList(it.oleicList)
        }

    }
}