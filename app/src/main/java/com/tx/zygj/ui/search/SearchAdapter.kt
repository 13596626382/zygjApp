package com.tx.zygj.ui.search

import com.llx.common.base.BaseAdapter
import com.tx.zygj.BR
import com.tx.zygj.R
import com.tx.zygj.bean.MemberManageBean
import com.tx.zygj.databinding.ItemSearchBinding

class SearchAdapter : BaseAdapter<MemberManageBean, ItemSearchBinding>(R.layout.item_search, BR.memberManageBean)