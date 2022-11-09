package com.tx.zygj.ui.main.fragment.my

import androidx.fragment.app.viewModels
import com.llx.common.CommonConstant
import com.llx.common.base.BaseFragment
import com.llx.common.util.startActivity
import com.llx.common.util.toast
import com.tx.zygj.R
import com.tx.zygj.databinding.FragmentMyBinding
import com.tx.zygj.ui.above.AboveActivity
import com.tx.zygj.ui.achievement.AchievementActivity
import com.tx.zygj.ui.change.ChangePassActivity
import com.tx.zygj.ui.oil.OilSettingActivity
import com.tx.zygj.ui.personal.PersonalActivity
import com.tx.zygj.ui.print.PrintSettingActivity


class MyFragment : BaseFragment<FragmentMyBinding>(R.layout.fragment_my) {
    private val model: MyViewModel by viewModels()
    override fun initData() {
        userInfo()
        model.getStatistics()
        binding.my.setOnClickListener {
            startActivity<PersonalActivity>() //我的
        }
        binding.change.setOnClickListener {
            startActivity<ChangePassActivity>() //修改密码
        }
        binding.oil.setOnClickListener {
            startActivity<OilSettingActivity>() //油价设置
        }
        binding.print.setOnClickListener {
            startActivity<PrintSettingActivity>() //打印设置
        }
        binding.kpi.setOnClickListener {
            toast("功能开发中")
            return@setOnClickListener
//            startActivity<KpiSettingActivity>() //kpi设置
        }
        binding.level.setOnClickListener {
//            startActivity<MemberLevelActivity>() //会员等级
            toast("功能开发中")
            return@setOnClickListener
        }
        binding.achievement.setOnClickListener {
            startActivity<AchievementActivity>() //业绩详情
        }
        binding.achievements.setOnClickListener {
//            startActivity<AchievementsActivity>() //我的绩效
            toast("功能开发中")
            return@setOnClickListener
        }

        binding.above.setOnClickListener {
            startActivity<AboveActivity>()
        }

        model.getStatistics()
        model.statisticsBean.observe(this) {
            binding.statisticsBean = it
        }
    }

    override fun update() {
        userInfo()
        model.getStatistics()
    }

    private fun userInfo() {
        val userInfo = CommonConstant.getUserInfo()
        binding.userBean = userInfo
    }

    fun getStatistics() {
        model.getStatistics()
    }
}