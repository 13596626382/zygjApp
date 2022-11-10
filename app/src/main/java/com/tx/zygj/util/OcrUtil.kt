package com.tx.zygj.util

import android.app.Activity
import android.content.Context
import android.util.Log
import com.llx.common.util.fromJson
import com.tencent.ocr.sdk.common.*
import com.tx.zygj.bean.OcrCardBean

/**
 * 腾讯车牌识别
 */
object OcrUtil {

    fun init(context: Context) {
        // 启动参数配置
        val modeType = OcrModeType.OCR_DETECT_AUTO_MANUAL // 设置默认的业务识别模式自动 + 手动步骤模式

        val ocrType = OcrType.BankCardOCR // 设置默认的业务识别，银行卡

        val configBuilder =
            OcrSDKConfig.newBuilder(
                "AKID0uhKm5Fi8NQI1XwNnAOWf2IWbYN5etyr",
                "mNsTxJj3EPe7uX0XwEsbM0YwidUNLGia",
                null
            )
                .ocrType(ocrType)
                .setModeType(modeType)
                .build()
        // 初始化 SDK
//        OcrSDKKit.getInstance().initWithConfig(context, configBuilder)
    }

    fun startProcessOcr(
        activity: Activity,
        onSucceed: (OcrCardBean) -> Unit,
//        onError: ((String) -> Unit?)? = null
    ) {
        OcrSDKKit.getInstance().startProcessOcr(
            activity,
            OcrType.LicensePlateOCR,
            null,
            object : ISDKKitResultListener {
                override fun onProcessSucceed(
                    response: String,
                    srcBase64Image: String,
                    requestId: String
                ) {
                    Log.e("识别成功--->", "$response  $srcBase64Image  $requestId")
                    onSucceed.invoke(response.fromJson())
                }

                override fun onProcessFailed(
                    errorCode: String,
                    message: String,
                    requestId: String
                ) {
                    Log.e("识别失败--->", "$errorCode  $message  $requestId")
                }
            })
    }
}