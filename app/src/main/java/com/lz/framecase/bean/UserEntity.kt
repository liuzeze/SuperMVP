package com.lz.framecase.bean

/**
 * -----------作者----------日期----------变更内容-----
 * -          刘泽      2018-08-29       创建class
 */
class UserEntity {
    /**
     * avatar_url : http://p1.pstatp.com/thumb/97e000ecbe7608d7653
     * desc : 雷科技，关注智能生活和前沿科技，遇见全球科技数码发烧友。
     * id : 3282616029
     * is_blocked : 0
     * is_blocking : 0
     * is_followed : 0
     * is_following : 1
     * is_friend : 0
     * medals : []
     * name : 雷科技
     * remark_name : null
     * schema : sslocal://profile?uid=3282616029&refer=dongtai
     * screen_name : 雷科技
     * user_auth_info : {"auth_type": "0", "auth_info": "雷科技官方帐号"}
     * user_decoration :
     * user_id : 3282616029
     * user_verified : 1
     * verified_content : 雷科技官方帐号
     */

    var avatar_url: String? = null
    var desc: String? = null
    var id: Long = 0
    var is_blocked: Int = 0
    var is_blocking: Int = 0
    var is_followed: Int = 0
    var is_following: Int = 0
    var is_friend: Int = 0
    var name: String? = null
    var remark_name: Any? = null
    var schema: String? = null
    var screen_name: String? = null
    var user_auth_info: String? = null
    var user_decoration: String? = null
    var user_id: Long = 0
    var user_verified: Int = 0
    var verified_content: String? = null
    var medals: List<*>? = null
}
