package com.lz.framecase.bean

import java.io.Serializable

/**
 * -----------作者----------日期----------变更内容-----
 * -          刘泽      2018-08-30       创建class
 */
class DetailVideoLargeImageEntity : Serializable {
    var url: String? = null
    var width: Int = 0
    var uri: String? = null
    var height: Int = 0
    var url_list: List<MiddleImageEntity>? = null
}
