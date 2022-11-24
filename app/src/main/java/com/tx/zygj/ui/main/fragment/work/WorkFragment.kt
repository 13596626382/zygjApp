package com.tx.zygj.ui.main.fragment.work

import android.view.View
import androidx.fragment.app.viewModels
import com.llx.common.CommonConstant
import com.llx.common.base.BaseFragment
import com.llx.common.util.logE
import com.llx.common.util.setOnSingleClickListener
import com.llx.common.util.startActivity
import com.llx.common.util.toast
import com.tx.zygj.R
import com.tx.zygj.databinding.FragmentWorkBinding
import com.tx.zygj.ui.cashier.fast.FastCashierActivity
import com.tx.zygj.ui.cashier.refueling.RefuelingCashierActivity
import com.tx.zygj.ui.handover.HandoverActivity
import com.tx.zygj.ui.member.manage.MemberManageActivity
import com.tx.zygj.ui.reconciliation.ReconciliationActivity
import com.tx.zygj.ui.scan.ScanCodeActivity
import com.tx.zygj.ui.search.SearchActivity
import com.tx.zygj.ui.shop.manage.ShopManageActivity
import com.tx.zygj.ui.shop.shop.ShopActivity
import com.tx.zygj.ui.wallet.WalletRechargeActivity
import com.tx.zygj.util.OcrUtil


class WorkFragment : BaseFragment<FragmentWorkBinding>(R.layout.fragment_work) {
    private val model: WorkViewModel by viewModels()
    override fun initData() {
        binding.userInfoBean = CommonConstant.getUserInfo()
        if (CommonConstant.getPrintBean() == null) {
            model.getPrintSettings()
        }
        binding.nfc.setOnSingleClickListener {
            toast("功能暂未开通")
            return@setOnSingleClickListener
        }
        binding.licensePlate.setOnSingleClickListener {
            OcrUtil.startProcessOcr(requireActivity()) {
                logE("车牌号${it.number}")
            }
        }
        binding.reconciliation.setOnSingleClickListener {
            startActivity<ReconciliationActivity>() //查看业绩
        }
        binding.shopManage.setOnSingleClickListener {
            startActivity<ShopManageActivity>() //商品管理
        }
        binding.search.setOnSingleClickListener {
            startActivity<SearchActivity>() //搜索
        }
        binding.scanCode.setOnSingleClickListener {
            startActivity<ScanCodeActivity>(CommonConstant.SCAN_TYPE to 1) //二维码
        }
        binding.cashier.setOnSingleClickListener {
            startActivity<RefuelingCashierActivity>() //加油收银
        }
        binding.handover.setOnSingleClickListener {
            startActivity<HandoverActivity>() //交班信息
        }
        binding.fastCashier.setOnSingleClickListener {
            startActivity<FastCashierActivity>() //快速收银
        }
        binding.shopCashier.setOnSingleClickListener {
            startActivity<ShopActivity>() //积分商品
        }
        binding.waller.setOnSingleClickListener {
            startActivity<WalletRechargeActivity>() //钱包充值
        }
        binding.memberManage.setOnSingleClickListener {
            startActivity<MemberManageActivity>() //会员管理
        }
        binding.writeOff.setOnSingleClickListener {
            //            startActivity<WriteOffActivity>() //卡券核销
            toast("功能开发中")
            return@setOnSingleClickListener
        }
        binding.integral.setOnClickListener {
            toast("功能开发中")
            return@setOnClickListener
        }

        model.getStatistics()
        model.statisticsBean.observe(this) {
            binding.statisticsBean = it
        }
        model.printBeanResult.observe(this) {
            binding.textView14.visibility = if (it) View.GONE else View.VISIBLE
            binding.progressBae.visibility = View.GONE
        }
        binding.textView14.setOnSingleClickListener {
            binding.progressBae.visibility = View.VISIBLE
            model.getPrintSettings()
        }

    }

    override fun update() {
        model.getStatistics()
        if (CommonConstant.getPrintBean() != null) {
            binding.textView14.visibility = View.GONE
        }
    }

    fun getStatistics() {
        model.getStatistics()
    }


}