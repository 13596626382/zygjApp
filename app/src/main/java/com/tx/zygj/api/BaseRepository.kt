package com.tx.zygj.api

import com.llx.common.api.RetrofitUtil

abstract class BaseRepository {
    protected val retrofit by lazy { RetrofitUtil.create(Api::class.java) }
}