package com.tx.zygj.dialog.shop

import android.content.Intent
import androidx.fragment.app.viewModels
import com.llx.common.CommonConstant
import com.llx.common.base.BaseDialogFragment
import com.llx.common.custom.GlideEngine
import com.llx.common.util.*
import com.luck.picture.lib.basic.PictureSelector
import com.luck.picture.lib.config.SelectMimeType
import com.luck.picture.lib.entity.LocalMedia
import com.luck.picture.lib.interfaces.OnResultCallbackListener
import com.lxj.xpopup.impl.LoadingPopupView
import com.tx.zygj.R
import com.tx.zygj.bean.GoodsBean
import com.tx.zygj.bean.GoodsClassifyBean
import com.tx.zygj.databinding.DialogManageShopBinding
import com.tx.zygj.ui.scan.ScanCodeActivity
import java.io.File

class ManageShopDialogFragment :
    BaseDialogFragment<DialogManageShopBinding>(R.layout.dialog_manage_shop) {
    var onResultSuccess: (() -> Unit)? = null
    private val model: ManageShopViewModel by viewModels()
    private lateinit var loadDialog: LoadingPopupView
    private var goodsBean: GoodsBean? = null
    private var goodsClassifyBean: ArrayList<GoodsClassifyBean>? = null
    private var goodsOrGift = 0
    private var file: File? = null
    override fun init() {
        loadDialog = showLoadingDialog("上传中")
        goodsOrGift = argumentInt("goodsOrGift")!!
        goodsClassifyBean = argumentsParcelableArrayList("goodsClassifyBean")
        goodsBean = argumentParcelable("goodsBean")
        if (goodsBean == null) {
            goodsBean = GoodsBean(goodsOrGift = goodsOrGift)
        } else {
            binding.goodsBean = goodsBean
        }
        binding.dismiss.setOnSingleClickListener { dismiss() }
        binding.code.addAfterTextChanged { goodsBean?.barCode = this }
        binding.name.addAfterTextChanged { goodsBean?.goodsName = this }
        binding.price.addAfterTextChanged { goodsBean?.sellingPrice = this.toDoubleOrNull() }
        binding.unit.addAfterTextChanged { goodsBean?.goodsUnit = this }
        binding.marketPrice.addAfterTextChanged { goodsBean?.marketPrice = this.toDoubleOrNull() }
        binding.costPrice.addAfterTextChanged { goodsBean?.costPrice = this.toDoubleOrNull() }
        binding.integral.addAfterTextChanged { goodsBean?.bonusPoints = this.toIntOrNull() }
        binding.stock.addAfterTextChanged { goodsBean?.stock = this.toIntOrNull() }
        binding.describe.addAfterTextChanged { goodsBean?.goodsDescribe = this }
        binding.release.setOnCheckedChangeListener { _, isChecked ->
            goodsBean?.publishOrNot = if (isChecked) 1 else 0
        }
        binding.recommend.setOnCheckedChangeListener { _, isChecked ->
            goodsBean?.recommend = if (isChecked) 1 else 0
        }
        binding.hot.setOnCheckedChangeListener { _, isChecked ->
            goodsBean?.hotOrNot = if (isChecked) 1 else 0
        }
        binding.shopRelease.setOnCheckedChangeListener { _, isChecked ->
            goodsBean?.mallRelease = if (isChecked) 1 else 0
        }
        binding.release.setOnCheckedChangeListener { _, isChecked ->
            goodsBean?.publishOrNot = if (isChecked) 1 else 0
        }

        binding.addImage.setOnClickListener {
            PictureSelector.create(this)
                .openGallery(SelectMimeType.ofImage())
                .setImageEngine(GlideEngine())
                .setMaxSelectNum(1)
                .setMinSelectNum(1)
                .forResult(object : OnResultCallbackListener<LocalMedia> {
                    override fun onResult(result: ArrayList<LocalMedia>?) {
                        binding.addImage.loadUrl(result!![0].path)
                        file = File(result[0].path)
                    }

                    override fun onCancel() {
                    }
                })
        }

        binding.scanningCode.setOnSingleClickListener {
            startActivityForResult(
                Intent(mContext, ScanCodeActivity::class.java).putExtra(
                    CommonConstant.SCAN_TYPE, 0
                ), CommonConstant.REQUEST_CODE
            )
        }

        val list = arrayListOf<String>()
        goodsClassifyBean?.forEach {
            list.add(it.classifyName)
            if (it.id == goodsBean?.goodsClassifyId) {
                binding.classifyText.text = it.classifyName
            }
        }
        binding.classify.showDialog(list, requireActivity()) { s, i ->
            goodsBean?.goodsClassifyId = goodsClassifyBean?.get(i)?.id
            binding.classifyText.text = s
        }

        binding.save.setOnSingleClickListener {
            if (goodsBean?.barCode == "") {
                toast("输入商品条码")
                return@setOnSingleClickListener
            }
            if (goodsBean?.goodsName == "") {
                toast("输入商品名称")
                return@setOnSingleClickListener
            }
            if (goodsBean?.goodsClassifyId == 0) {
                toast("请选择商品分类")
                return@setOnSingleClickListener
            }
            if (goodsBean?.sellingPrice.contrast(0.00)) {
                toast("输入正确的销售价格")
                return@setOnSingleClickListener
            }
            if (goodsBean?.marketPrice.contrast(0.00)) {
                toast("输入正确的市场价格")
                return@setOnSingleClickListener
            }
            if (goodsBean?.costPrice.contrast(0.00)) {
                toast("输入正确的成本价格")
                return@setOnSingleClickListener
            }
            if (goodsBean?.goodsUnit == "") {
                toast("输入商品单位")
                return@setOnSingleClickListener
            }
            if (goodsBean?.stock == 0) {
                toast("输入库存数量")
                return@setOnSingleClickListener
            }
            loadDialog.show()
            if (binding.goodsBean == null) {
                if (file == null) {
                    toast("请上传图片")
                    return@setOnSingleClickListener
                }
                model.upload(file!!, goodsBean, goodsOrGift = goodsOrGift)
            } else {
                if (file != null) {
                    model.upload(file!!, goodsBean, true, goodsOrGift)
                } else {
                    model.updateGoods(goodsBean = goodsBean)
                }
            }
        }

        model.requestResult.observe(this) {
            loadDialog.dismiss()
            if (it) {
                onResultSuccess?.invoke()
                dismiss()
            }
        }
    }

    override fun getDialogHeight() = 550.dp.toInt()


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CommonConstant.REQUEST_CODE) {
            val code = data?.getStringExtra(CommonConstant.SCAN_CODE)
            if (code != null) {
                binding.code.setText(code)
                goodsBean?.barCode = code
            }
        }
    }
}