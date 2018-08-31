package com.lz.framecase.bean

/**
 * Created by Meiji on 2017/5/18.
 */

class NewsCommentBean {

    /**
     * detail_no_comment : 0
     * total_number : 59
     * ban_comment : false
     * has_more : true
     * go_topic_detail : 1
     * stick_total_number : 0
     * tab_info : {"tabs":["热度","时间"],"current_tab_index":0}
     * fold_comment_count : 0
     * show_add_forum : 1
     * stable : true
     * stick_has_more : false
     * message : success
     */

    var detail_no_comment: Int = 0
    var total_number: Int = 0
    var isBan_comment: Boolean = false
    var isHas_more: Boolean = false
    var go_topic_detail: Int = 0
    var stick_total_number: Int = 0
    var tab_info: TabInfoBean? = null
    var fold_comment_count: Int = 0
    var show_add_forum: Int = 0
    var isStable: Boolean = false
    var isStick_has_more: Boolean = false
    var message: String? = null
    var data: List<DataBean>? = null

    class TabInfoBean {
        /**
         * tabs : ["热度","时间"]
         * current_tab_index : 0
         */

        var current_tab_index: Int = 0
        var tabs: List<String>? = null
    }

    class DataBean {
        /**
         * comment : {"is_followed":0,"text":"我们的未来有希望了，","reply_count":0,"is_following":0,"reply_list":[],"user_verified":false,"is_blocking":0,"user_id":50022511998,"bury_count":0,"author_badge":[],"id":50029624449,"verified_reason":"","platform":"feifei","score":0,"user_name":"WolfRoad124180199","user_profile_image_url":"http://p1.pstatp.com/thumb/729001d88c6944f970b","user_bury":0,"user_digg":0,"is_blocked":0,"user_relation":0,"user_auth_info":"","digg_count":33,"create_time":1470145059}
         * cell_type : 1
         */

        var comment: CommentBean? = null
        var cell_type: Int = 0

        class CommentBean {
            /**
             * is_followed : 0
             * text : 我们的未来有希望了，
             * reply_count : 0
             * is_following : 0
             * reply_list : []
             * user_verified : false
             * is_blocking : 0
             * user_id : 50022511998
             * bury_count : 0
             * author_badge : []
             * id : 50029624449
             * verified_reason :
             * platform : feifei
             * score : 0
             * user_name : WolfRoad124180199
             * user_profile_image_url : http://p1.pstatp.com/thumb/729001d88c6944f970b
             * user_bury : 0
             * user_digg : 0
             * is_blocked : 0
             * user_relation : 0
             * user_auth_info :
             * digg_count : 33
             * create_time : 1470145059
             */

            var is_followed: Int = 0
            var text: String? = null
            var reply_count: Int = 0
            var is_following: Int = 0
            var isUser_verified: Boolean = false
            var is_blocking: Int = 0
            var user_id: Long = 0
            var bury_count: Int = 0
            var id: Long = 0
            var verified_reason: String? = null
            var platform: String? = null
            //            private long score;
            //            public long getScore() {
            //                return score;
            //            }

            //            public void setScore(int score) {
            //                this.score = score;
            //            }

            var user_name: String? = null
            var user_profile_image_url: String? = null
            var user_bury: Int = 0
            var user_digg: Int = 0
            var is_blocked: Int = 0
            var user_relation: Int = 0
            var user_auth_info: String? = null
            var digg_count: Int = 0
            var create_time: Int = 0
            var reply_list: List<*>? = null
            var author_badge: List<*>? = null

            override fun equals(o: Any?): Boolean {
                if (this === o)
                    return true
                if (o == null || javaClass != o.javaClass)
                    return false

                val that = o as CommentBean?

                return if (create_time != that!!.create_time) false else text == that.text
            }

            override fun hashCode(): Int {
                var result = text!!.hashCode()
                result = 31 * result + create_time
                return result
            }
        }
    }
}
