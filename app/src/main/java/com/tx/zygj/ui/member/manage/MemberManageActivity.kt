package com.tx.zygj.ui.member.manage

import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.llx.common.CommonConstant
import com.llx.common.base.BaseActivity
import com.llx.common.util.bind
import com.llx.common.util.setOnSingleClickListener
import com.llx.common.util.startActivity
import com.tx.zygj.R
import com.tx.zygj.bean.MemberManageBean
import com.tx.zygj.custom.TitleItemDecoration
import com.tx.zygj.databinding.ActivityMemberManageBinding
import com.tx.zygj.ui.member.information.MemberInformationActivity
import com.tx.zygj.ui.member.register.MemberRegisterActivity
import com.tx.zygj.util.PinyinComparator
import com.tx.zygj.util.getPingYin
import java.util.*

/**
 * 会员管理
 */
class MemberManageActivity :
    BaseActivity<ActivityMemberManageBinding>(R.layout.activity_member_manage) {
    private val mAdapter by lazy { MemberManageAdapter() }
    private val model: MemberManageViewModel by viewModels()

    private lateinit var mLayoutManager: LinearLayoutManager
    private lateinit var mDateList: List<MemberManageBean>
    private lateinit var mDecoration: TitleItemDecoration
    override fun initData() {
        binding.titleBar.setOnBack(this)
        model.getMember()

        model.memberManageBean.observe(this) {
            binding.progressBae.visibility = View.GONE
            binding.recyclerView.visibility = View.VISIBLE
            mDateList = filledData(it)
            // 根据a-z进行排序源数据
            Collections.sort(mDateList, PinyinComparator())
            mDecoration = TitleItemDecoration(mContext, mDateList)
            binding.recyclerView.addItemDecoration(mDecoration)
            mAdapter.setList(mDateList)
        }
        mLayoutManager = LinearLayoutManager(mContext)
        binding.recyclerView.bind(mAdapter) {
//            setOnItemClickListener { _, _, position ->
//                startActivity<MemberInformationActivity>(CommonConstant.MEMBER_ID to data[position].id)
//            }
            onBind {
                dataBinding!!.imageView9.setOnSingleClickListener {
                    startActivity<MemberInformationActivity>(CommonConstant.MEMBER_ID to data[layoutPosition].id)
                }
            }
            setEmptyView(layoutInflater.inflate(R.layout.view_empty, null, false))
        }

        binding.titleBar.onImageClickListener = {
            startActivity<MemberRegisterActivity>(CommonConstant.INTENT_TITLE to "会员登记")
        }
    }

    private fun filledData(memberManageBean: ArrayList<MemberManageBean>): List<MemberManageBean> {
        val mSortList: MutableList<MemberManageBean> = ArrayList()
        for (date in memberManageBean) {
            //汉字转换成拼音
            val pinyin = getPingYin(date.nickName)
            if (pinyin == "") {
                date.letters = "@"
            } else {
                val sortString = pinyin.substring(0, 1).uppercase(Locale.getDefault())
                // 正则表达 式，判断首字母是否是英文字母
                if (sortString.matches(Regex("[A-Z]"))) {
                    date.letters = sortString.uppercase(Locale.getDefault())
                } else {
                    date.letters = "@"
                }
            }
            mSortList.add(date)
        }

        return mSortList
    }

}