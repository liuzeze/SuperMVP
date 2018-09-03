package com.lz.framecase.bean

import com.chad.library.adapter.base.entity.MultiItemEntity

/**
 * -----------作者----------日期----------变更内容-----
 * -          刘泽      2018-09-03       创建class
 */
class TitleBean : MultiItemEntity {
    override fun getItemType(): Int {
        return itemTypes
    }

    var itemTypes = 1
    var name = ""
    var newsId = ""
    var isEditer: Boolean = false


    companion object {

        val MYTITLE = 1
        val MYTITLESub = 2
        val ORTHERTITLE = 3
        val ORTHERTITLESUB = 4
    }
}
