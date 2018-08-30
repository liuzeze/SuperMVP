package com.lz.framecase.bean

/**
 * Created by Meiji on 2017/5/20.
 */

class WendaArticleBean {

    /**
     * add_first_page : 1
     * has_more_to_refresh : true
     * login_status : 0
     * total_number : 10
     * extra :
     * has_more : true
     * message : success
     * api_param : {"origin_from":"","enter_from":""}
     * tips : {"display_info":"今日头条推荐引擎有10条更新","open_url":"","type":"app","display_duration":2,"app_name":"今日头条"}
     */

    var add_first_page: Int = 0
    var isHas_more_to_refresh: Boolean = false
    var login_status: Int = 0
    var total_number: Int = 0
    var extra: String? = null
    var isHas_more: Boolean = false
    var message: String? = null
    var api_param: String? = null
    var tips: TipsBean? = null
    var data: List<DataBean>? = null

    class TipsBean {
        /**
         * display_info : 今日头条推荐引擎有10条更新
         * open_url :
         * type : app
         * display_duration : 2
         * app_name : 今日头条
         */

        var display_info: String? = null
        var open_url: String? = null
        var type: String? = null
        var display_duration: Int = 0
        var app_name: String? = null
    }

    class DataBean {
        /**
         * content : {"cell_height":120,"data_url":"https:\/\/ic.snssdk.com\/wenda\/v1\/channel\/announcement\/","template_url":"http:\/\/ic.snssdk.com\/wenda\/v1\/native\/widget\/?api_param=%7B%22wd_version%22%3A5%7D","data_flag":true,"id":"50052410574","template_md5":"bc58854942d0559324kjl435298234fs","cell_type":25,"is_deleted":false,"behot_time":1495245397,"cursor":1,"refresh_interval":60,"data_callback":"window.refresh"}
         * code :
         */

        var content: String? = null
        var code: String? = null
    }
}
