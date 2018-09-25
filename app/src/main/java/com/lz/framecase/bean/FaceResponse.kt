package com.lz.framecase.bean

/**
 * -----------作者----------日期----------变更内容-----
 * -          刘泽      2018-09-25       创建class
 */
class FaceResponse {
    var error_code: Int = 0
    var log_id: Long = 0
    var timestamp: Long = 0
    var cached: Int = 0
    var error_msg: String = ""
    var result: FaceppBean? = null
}
