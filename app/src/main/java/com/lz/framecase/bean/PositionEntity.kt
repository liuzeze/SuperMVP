package com.lz.framecase.bean

import java.io.Serializable

/**
 * -----------作者----------日期----------变更内容-----
 * -          刘泽      2018-08-29       创建class
 */
class PositionEntity : Serializable {
    /**
     * latitude : 0
     * longitude : 0
     * position :
     */

    var latitude: Double = 0.0
    var longitude: Double = 0.0
    var position: String? = null
}
