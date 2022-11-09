package com.tx.zygj.ui.achievement

import com.llx.common.CommonConstant
import com.tx.zygj.api.BaseRepository

class AchievementRepository : BaseRepository() {

    suspend fun getExchange() = retrofit.getExchange(CommonConstant.getUserInfo()?.phone)
}