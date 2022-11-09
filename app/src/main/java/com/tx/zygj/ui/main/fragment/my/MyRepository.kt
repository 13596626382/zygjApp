
package com.tx.zygj.ui.main.fragment.my

import com.tx.zygj.api.BaseRepository

class MyRepository  : BaseRepository() {

   suspend fun getStatistics() = retrofit.getStatistics()
}