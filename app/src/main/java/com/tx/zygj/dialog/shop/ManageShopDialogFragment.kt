package com.tx.zygj.dialog.shop

import android.view.Gravity
import android.view.WindowManager
import com.llx.common.base.BaseDialogFragment
import com.llx.common.custom.GlideEngine
import com.llx.common.util.dp
import com.llx.common.util.loadUrl
import com.luck.picture.lib.basic.PictureSelector
import com.luck.picture.lib.config.SelectMimeType
import com.luck.picture.lib.entity.LocalMedia
import com.luck.picture.lib.interfaces.OnResultCallbackListener
import com.tx.zygj.R
import com.tx.zygj.databinding.DialogManageShopBinding
import java.util.ArrayList

class ManageShopDialogFragment : BaseDialogFragment<DialogManageShopBinding>(R.layout.dialog_manage_shop) {
    override fun init() {
        binding.addImage.setOnClickListener {
            PictureSelector.create(this)
                .openGallery(SelectMimeType.ofImage())
                .setImageEngine(GlideEngine())
                .setMaxSelectNum(1)
                .setMinSelectNum(1)
                .forResult(object : OnResultCallbackListener<LocalMedia> {
                    override fun onResult(result: ArrayList<LocalMedia>?) {
                        binding.addImage.loadUrl(result!![0].path)
                    }

                    override fun onCancel() {

                    }
                })
        }
    }

    override fun getDialogHeight() = 600.dp.toInt()

    override fun getDialogWidth() = WindowManager.LayoutParams.MATCH_PARENT

    override fun getDialogGravity() = Gravity.BOTTOM
}