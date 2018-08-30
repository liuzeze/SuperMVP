package com.lz.framecase.bean

import java.io.Serializable

/**
 * -----------作者----------日期----------变更内容-----
 * -          刘泽      2018-08-29       创建class
 */
class UserInfoEntity : Serializable {
    /**
     * avatar_url : http://p9.pstatp.com/thumb/9b1f0003e50e12ffc79f
     * follow : true
     * follower_count : 0
     * name : 找靓机二手机
     * user_id : 5856627767
     * user_auth_info :
     * user_verified : true
     * verified_content : 优质科技领域创作者
     */

    var avatar_url: String? = null
    var isFollow: Boolean = false
    var follower_count: Int = 0
    var name: String? = null
    var user_id: Long = 0
    private val user_auth_info: String? = null
    var isUser_verified: Boolean = false
    var verified_content: String? = null
}
