package com.lz.framecase.bean

import java.io.Serializable

/**
 * -----------作者----------日期----------变更内容-----
 * -          刘泽      2018-08-31       创建class
 */
class MediaInfoBean : Serializable {
    var user_id: Long = 0
    var verified_content: String? = null
    var avatar_url: String? = null
    var media_id: String? = null
    var name: String? = null
    var recommend_type: Int = 0
    var follow: Boolean = false
    var recommend_reason: String? = null
    var is_star_user: Boolean = false
    var user_verified: Boolean = false

}
