package com.lz.framecase.bean

/**
 * -----------作者----------日期----------变更内容-----
 * -          刘泽      2018-08-29       创建class
 */
 class LargeImageListEntity {
    /**
     * height : 1706
     * type : 1
     * uri : mosaic-legacy/a14b000370a4732d02c1
     * url : http://sf3-ttcdn-tos.pstatp.com/img/mosaic-legacy/a14b000370a4732d02c1~960x1706_noop.jpeg
     * url_list : [{"url":"http://sf3-ttcdn-tos.pstatp.com/img/mosaic-legacy/a14b000370a4732d02c1~960x1706_noop.jpeg"},{"url":"http://sf6-ttcdn-tos.pstatp.com/img/mosaic-legacy/a14b000370a4732d02c1~960x1706_noop.jpeg"},{"url":"http://sf1-ttcdn-tos.pstatp.com/img/mosaic-legacy/a14b000370a4732d02c1~960x1706_noop.jpeg"}]
     * width : 960
     */

    var height: Int = 0
    var type: Int = 0
    var uri: String? = null
    var url: String? = null
    var width: Int = 0
    var url_list: List<UrlListEntity>? = null
}
