package com.llx.common.dialog

import com.llx.common.BR
import com.llx.common.R
import com.llx.common.base.BaseAdapter
import com.llx.common.databinding.ItemDialogStringBinding

class StringDialogAdapter : BaseAdapter<String, ItemDialogStringBinding>(R.layout.item_dialog_string, BR.string)