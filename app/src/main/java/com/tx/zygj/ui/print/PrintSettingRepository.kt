package com.tx.zygj.ui.print

import com.llx.common.bean.PrintBean
import com.tx.zygj.api.BaseRepository

class PrintSettingRepository : BaseRepository() {

    suspend fun getPrintSettings() = retrofit.getPrintSettings()

    suspend fun updatePrintSettings(printBean: PrintBean?) =
        retrofit
            .updatePrintSettings(
                printBean?.id,
                printBean?.addressStatus,
                printBean?.balanceStatus,
                printBean?.carNumberStatus,
                printBean?.contactStatus,
                printBean?.footnoteStatus,
                printBean?.footnote,
                printBean?.integralStatus,
                printBean?.logo,
                printBean?.logoStatus,
                printBean?.memberNameStatus,
                printBean?.memberTypeStatus,
                printBean?.operationStatus,
                printBean?.operatorStatus,
                printBean?.orderNoStatus,
                printBean?.phoneStatus,
                printBean?.remarksStatus,
                printBean?.shopStatus,
                printBean?.titleContent,
                printBean?.titleStatus,
                printBean?.twoCode,
                printBean?.twoCodeStatus,
                printBean?.typeStatus
            )
}