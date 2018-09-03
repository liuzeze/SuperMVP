package com.lz.framecase.activity.adapter.drag

import com.chad.library.adapter.base.BaseViewHolder
import com.lz.framecase.bean.TitleBean

/**
 * -----------作者----------日期----------变更内容-----
 * -          刘泽      2018-09-03       创建class
 */
class DragAdapter(data: List<TitleBean>?) : MultipleItemDragRvAdapter<TitleBean, BaseViewHolder>(data) {


    init {
        finishInitialize()
    }

    override fun getViewType(titleBean: TitleBean): Int {
        return titleBean.itemType
    }

    override fun registerItemProvider() {
        mProviderDelegate.registerProvider(MyTitleProvider())
        mProviderDelegate.registerProvider(MyTitleSubProvider())
        mProviderDelegate.registerProvider(OtherTitleProvider())
        mProviderDelegate.registerProvider(OtherTitleSubProvider())
    }

}
