package com.tx.zygj.ui.above

import com.llx.common.CommonConstant
import com.llx.common.base.BaseActivity
import com.llx.common.util.setOnSingleClickListener
import com.llx.common.util.startActivity
import com.lxj.xpopup.XPopup
import com.tx.zygj.R
import com.tx.zygj.databinding.ActivityAboveBinding
import com.tx.zygj.ui.voice.VoicePageActivity

class AboveActivity : BaseActivity<ActivityAboveBinding>(R.layout.activity_above) {
    override fun initData() {
        binding.titleBarView.setOnBack(this)
        binding.contactMy.setOnSingleClickListener {
            XPopup.Builder(mContext)
                .isDestroyOnDismiss(true)
                .asConfirm(
                    "联系我们", CommonConstant.getUserInfo()?.gasPhone,
                    "取消", "确定", {
//                        val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${binding.userBean?.gasPhone}"))
//                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
//                        startActivity(intent)
                    }, null, false
                )
                .show()
        }
      binding.voice.setOnSingleClickListener {
          startActivity<VoicePageActivity>()
      }
    }



}