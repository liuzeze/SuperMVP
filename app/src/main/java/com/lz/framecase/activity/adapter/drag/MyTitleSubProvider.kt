package com.lz.framecase.activity.adapter.drag

import android.graphics.Color

import com.chad.library.adapter.base.BaseViewHolder
import com.chad.library.adapter.base.provider.BaseItemProvider
import com.lz.framecase.R
import com.lz.framecase.bean.TitleBean


/**
 * -----------作者----------日期----------变更内容-----
 * -          刘泽      2018-09-03       创建class
 */
class MyTitleSubProvider : BaseItemProvider<TitleBean, BaseViewHolder>() {
    override fun layout(): Int {
        return R.layout.item_drag_view
    }

    override fun viewType(): Int {
        return TitleBean.MYTITLESub
    }

    override fun convert(helper: BaseViewHolder, data: TitleBean, position: Int) {
        helper.setGone(R.id.iv_cancle, if (data.isEditer) true else false)
        helper.setText(R.id.tv_name, data.name)
    }


}
