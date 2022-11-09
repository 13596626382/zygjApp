package com.tx.zygj.ui.handover

import com.llx.common.CommonConstant
import com.tx.zygj.api.BaseRepository

class HandoverRepository : BaseRepository() {

    suspend fun getExchange() = retrofit.getExchange(CommonConstant.getUserInfo()?.phone)
}