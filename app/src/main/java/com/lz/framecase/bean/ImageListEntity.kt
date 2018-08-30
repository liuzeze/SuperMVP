package com.lz.framecase.bean

import java.io.Serializable

/**
 * -----------作者----------日期----------变更内容-----
 * -          刘泽      2018-08-29       创建class
 */
class ImageListEntity : Serializable {

    /**
     * height : 360
     * uri : list/aae5000932f5c747ec43
     * url : http://p3.pstatp.com/list/300x196/aae5000932f5c747ec43.webp
     * url_list : [{"url":"http://p3.pstatp.com/list/300x196/aae5000932f5c747ec43.webp"},{"url":"http://pb9.pstatp.com/list/300x196/aae5000932f5c747ec43.webp"},{"url":"http://pb1.pstatp.com/list/300x196/aae5000932f5c747ec43.webp"}]
     * width : 640
     */

    var height: Int = 0
    var uri: String? = null
    var url: String? = null
    var width: Int = 0
    var url_list: List<UrlListEntity>? = null
}
