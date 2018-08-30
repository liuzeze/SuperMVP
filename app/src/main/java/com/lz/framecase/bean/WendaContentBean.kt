package com.lz.framecase.bean

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by Meiji on 2017/5/22.
 */

class WendaContentBean {

    /**
     * show_format : {"show_module":1,"font_size":"18","answer_full_context_color":0}
     * err_tips :
     * err_no : 0
     * offset : 10
     * candidate_invite_user : []
     * module_list : [{"day_icon_url":"http://p3.pstatp.com/origin/1bf50001abbc1c7f8dba","text":"更多问答","icon_type":2,"night_icon_url":"http://p3.pstatp.com/origin/1bf40001abebc0717135","schema":"sslocal://feed?category=question_and_answer&concern_id=6260258266329123329&type=4&name=%E9%97%AE%E7%AD%94&api_param=%7B%22source%22%3A%22question_brow%22%2C%22origin_from%22%3Anull%2C%22enter_from%22%3Anull%7D"}]
     * has_more : true
     * channel_data : {"open_url":"sslocal://webview?url=https%3A%2F%2Fic.snssdk.com%2Fwenda%2Fv1%2Fwaphome%2Fbrow%2F%3Frecommend_from%3Drecommend_question_brow&title=%E5%A4%B4%E6%9D%A1%E9%97%AE%E7%AD%94","text":"关注问答频道，聊天更有谈资！","pos":3,"button_text":"进入","recommend_image":{"url":"https://p.pstatp.com/origin/159f000460df3e3f850c","url_list":[{"url":"http://p3.pstatp.com/list/r90/13530005a010f7ce835d"},{"url":"http://pb9.pstatp.com/list/r90/13530005a010f7ce835d"},{"url":"http://pb3.pstatp.com/list/r90/13530005a010f7ce835d"}],"uri":"list/r90/13530005a010f7ce835d","height":90,"width":90,"type":1},"type":1}
     * question : {"concern_tag_list":[{"concern_id":"6213182495320443393","name":"火车","schema":"sslocal://concern?tab_sname=wenda&api_param=%7B%22wenda_api_param%22%3A%7B%22scope%22%3A%22toutiao_wenda%22%2C%22origin_from%22%3A%22click_headline%22%2C%22parent_enter_from%22%3A%22click_headline%22%2C%22enter_from%22%3A%22question%22%7D%7D&cid=6213182495320443393"}],"can_delete":false,"post_answer_url":"sslocal://wenda_post?qid=6420544946419269889&gd_ext_json=%7B%22enter_type%22%3A%22question_and_answer%22%2C%22ansid%22%3A6422088403512197378%7D&qTitle=%E5%8D%B0%E5%BA%A6%E6%9C%80%E5%BF%AB%E7%9A%84%E5%88%97%E8%BD%A6%E6%97%B6%E9%80%9F160%EF%BC%8C%E5%BD%93%E5%9C%B0%E4%BA%BA%E9%97%AE%E2%80%9C%E4%B8%AD%E5%9B%BD%E7%81%AB%E8%BD%A6%E6%9C%89%E8%BF%99%E4%B9%88%E5%BF%AB%E5%90%97%E2%80%9D%EF%BC%8C%E6%80%8E%E4%B9%88%E5%9B%9E%E7%AD%94%EF%BC%9F","is_follow":false,"nice_ans_count":73,"create_time":1494899612,"normal_ans_count":851,"user":{"user_intro":"","uname":"yuejiao19926","avatar_url":"http://p0.pstatp.com/origin/3795/3033762272","user_id":"6796383301","is_verify":0},"share_data":{"content":"非常推荐！","image_url":"http://p0.pstatp.com/medium/6399/2275149767","share_url":"https://wenda.toutiao.com/m/wapshare/question/brow/?qid=6420544946419269889&","title":"头条问答-印度最快的列车时速160，当地人问\u201c中国火车有这么快吗\u201d，怎么回答？(924个回答)"},"can_edit":false,"show_delete":false,"title":"印度最快的列车时速160，当地人问\u201c中国火车有这么快吗\u201d，怎么回答？","follow_count":497,"content":{"text":"\n","thumb_image_list":[{"url":"http://p9.pstatp.com/list/r640/1dcd000e3ba14e6e61f8","url_list":[{"url":"http://p9.pstatp.com/list/r640/1dcd000e3ba14e6e61f8"},{"url":"http://pb1.pstatp.com/list/r640/1dcd000e3ba14e6e61f8"},{"url":"http://pb3.pstatp.com/list/r640/1dcd000e3ba14e6e61f8"}],"uri":"1dcd000e3ba14e6e61f8","height":379,"width":640,"type":1}],"large_image_list":[{"url":"http://p9.pstatp.com/large/1dcd000e3ba14e6e61f8","url_list":[{"url":"http://p9.pstatp.com/large/1dcd000e3ba14e6e61f8"},{"url":"http://pb1.pstatp.com/large/1dcd000e3ba14e6e61f8"},{"url":"http://pb3.pstatp.com/large/1dcd000e3ba14e6e61f8"}],"uri":"1dcd000e3ba14e6e61f8","height":379,"width":640,"type":1}]},"show_edit":false,"qid":"6420544946419269889","fold_reason":{"open_url":"sslocal://detail?groupid=6293724675596402946","title":"为什么折叠？"}}
     * module_count : 1
     * question_type : 0
     * api_param : {"origin_from": null, "enter_from": null}
     * question_header_content_fold_max_count : 1
     */

    var show_format: ShowFormatBean? = null
    var err_tips: String? = null
    var err_no: Int = 0
    var offset: Int = 0
    var isHas_more: Boolean = false
    var channel_data: ChannelDataBean? = null
    var question: QuestionBean? = null
    var module_count: Int = 0
    var question_type: Int = 0
    var api_param: String? = null
    var question_header_content_fold_max_count: Int = 0
    var candidate_invite_user: List<*>? = null
    var module_list: List<ModuleListBean>? = null
    var ans_list: List<AnsListBean>? = null

    class ShowFormatBean {
        /**
         * show_module : 1
         * font_size : 18
         * answer_full_context_color : 0
         */

        var show_module: Int = 0
        var font_size: String? = null
        var answer_full_context_color: Int = 0
    }

    class ChannelDataBean {
        /**
         * open_url : sslocal://webview?url=https%3A%2F%2Fic.snssdk.com%2Fwenda%2Fv1%2Fwaphome%2Fbrow%2F%3Frecommend_from%3Drecommend_question_brow&title=%E5%A4%B4%E6%9D%A1%E9%97%AE%E7%AD%94
         * text : 关注问答频道，聊天更有谈资！
         * pos : 3
         * button_text : 进入
         * recommend_image : {"url":"https://p.pstatp.com/origin/159f000460df3e3f850c","url_list":[{"url":"http://p3.pstatp.com/list/r90/13530005a010f7ce835d"},{"url":"http://pb9.pstatp.com/list/r90/13530005a010f7ce835d"},{"url":"http://pb3.pstatp.com/list/r90/13530005a010f7ce835d"}],"uri":"list/r90/13530005a010f7ce835d","height":90,"width":90,"type":1}
         * type : 1
         */

        var open_url: String? = null
        var text: String? = null
        var pos: Int = 0
        var button_text: String? = null
        var recommend_image: RecommendImageBean? = null
        var type: Int = 0

        class RecommendImageBean {
            /**
             * url : https://p.pstatp.com/origin/159f000460df3e3f850c
             * url_list : [{"url":"http://p3.pstatp.com/list/r90/13530005a010f7ce835d"},{"url":"http://pb9.pstatp.com/list/r90/13530005a010f7ce835d"},{"url":"http://pb3.pstatp.com/list/r90/13530005a010f7ce835d"}]
             * uri : list/r90/13530005a010f7ce835d
             * height : 90
             * width : 90
             * type : 1
             */

            var url: String? = null
            var uri: String? = null
            var height: Int = 0
            var width: Int = 0
            var type: Int = 0
            var url_list: List<UrlListBean>? = null

            class UrlListBean {
                /**
                 * url : http://p3.pstatp.com/list/r90/13530005a010f7ce835d
                 */

                var url: String? = null
            }
        }
    }

    class QuestionBean {
        /**
         * concern_tag_list : [{"concern_id":"6213182495320443393","name":"火车","schema":"sslocal://concern?tab_sname=wenda&api_param=%7B%22wenda_api_param%22%3A%7B%22scope%22%3A%22toutiao_wenda%22%2C%22origin_from%22%3A%22click_headline%22%2C%22parent_enter_from%22%3A%22click_headline%22%2C%22enter_from%22%3A%22question%22%7D%7D&cid=6213182495320443393"}]
         * can_delete : false
         * post_answer_url : sslocal://wenda_post?qid=6420544946419269889&gd_ext_json=%7B%22enter_type%22%3A%22question_and_answer%22%2C%22ansid%22%3A6422088403512197378%7D&qTitle=%E5%8D%B0%E5%BA%A6%E6%9C%80%E5%BF%AB%E7%9A%84%E5%88%97%E8%BD%A6%E6%97%B6%E9%80%9F160%EF%BC%8C%E5%BD%93%E5%9C%B0%E4%BA%BA%E9%97%AE%E2%80%9C%E4%B8%AD%E5%9B%BD%E7%81%AB%E8%BD%A6%E6%9C%89%E8%BF%99%E4%B9%88%E5%BF%AB%E5%90%97%E2%80%9D%EF%BC%8C%E6%80%8E%E4%B9%88%E5%9B%9E%E7%AD%94%EF%BC%9F
         * is_follow : false
         * nice_ans_count : 73
         * create_time : 1494899612
         * normal_ans_count : 851
         * user : {"user_intro":"","uname":"yuejiao19926","avatar_url":"http://p0.pstatp.com/origin/3795/3033762272","user_id":"6796383301","is_verify":0}
         * share_data : {"content":"非常推荐！","image_url":"http://p0.pstatp.com/medium/6399/2275149767","share_url":"https://wenda.toutiao.com/m/wapshare/question/brow/?qid=6420544946419269889&","title":"头条问答-印度最快的列车时速160，当地人问\u201c中国火车有这么快吗\u201d，怎么回答？(924个回答)"}
         * can_edit : false
         * show_delete : false
         * title : 印度最快的列车时速160，当地人问“中国火车有这么快吗”，怎么回答？
         * follow_count : 497
         * content : {"text":"\n","thumb_image_list":[{"url":"http://p9.pstatp.com/list/r640/1dcd000e3ba14e6e61f8","url_list":[{"url":"http://p9.pstatp.com/list/r640/1dcd000e3ba14e6e61f8"},{"url":"http://pb1.pstatp.com/list/r640/1dcd000e3ba14e6e61f8"},{"url":"http://pb3.pstatp.com/list/r640/1dcd000e3ba14e6e61f8"}],"uri":"1dcd000e3ba14e6e61f8","height":379,"width":640,"type":1}],"large_image_list":[{"url":"http://p9.pstatp.com/large/1dcd000e3ba14e6e61f8","url_list":[{"url":"http://p9.pstatp.com/large/1dcd000e3ba14e6e61f8"},{"url":"http://pb1.pstatp.com/large/1dcd000e3ba14e6e61f8"},{"url":"http://pb3.pstatp.com/large/1dcd000e3ba14e6e61f8"}],"uri":"1dcd000e3ba14e6e61f8","height":379,"width":640,"type":1}]}
         * show_edit : false
         * qid : 6420544946419269889
         * fold_reason : {"open_url":"sslocal://detail?groupid=6293724675596402946","title":"为什么折叠？"}
         */

        var isCan_delete: Boolean = false
        var post_answer_url: String? = null
        var isIs_follow: Boolean = false
        var nice_ans_count: Int = 0
        var create_time: Int = 0
        var normal_ans_count: Int = 0
        var user: UserBean? = null
        var share_data: ShareDataBean? = null
        var isCan_edit: Boolean = false
        var isShow_delete: Boolean = false
        var title: String? = null
        var follow_count: Int = 0
        var content: ContentBean? = null
        var isShow_edit: Boolean = false
        var qid: String? = null
        var fold_reason: FoldReasonBean? = null
        var concern_tag_list: List<ConcernTagListBean>? = null

        class UserBean {
            /**
             * user_intro :
             * uname : yuejiao19926
             * avatar_url : http://p0.pstatp.com/origin/3795/3033762272
             * user_id : 6796383301
             * is_verify : 0
             */

            var user_intro: String? = null
            var uname: String? = null
            var avatar_url: String? = null
            var user_id: String? = null
            var is_verify: Int = 0
        }

        class ShareDataBean {
            /**
             * content : 非常推荐！
             * image_url : http://p0.pstatp.com/medium/6399/2275149767
             * share_url : https://wenda.toutiao.com/m/wapshare/question/brow/?qid=6420544946419269889&
             * title : 头条问答-印度最快的列车时速160，当地人问“中国火车有这么快吗”，怎么回答？(924个回答)
             */

            var content: String? = null
            var image_url: String? = null
            var share_url: String? = null
            var title: String? = null
        }

        class ContentBean {
            /**
             * text :
             *
             *
             * thumb_image_list : [{"url":"http://p9.pstatp.com/list/r640/1dcd000e3ba14e6e61f8","url_list":[{"url":"http://p9.pstatp.com/list/r640/1dcd000e3ba14e6e61f8"},{"url":"http://pb1.pstatp.com/list/r640/1dcd000e3ba14e6e61f8"},{"url":"http://pb3.pstatp.com/list/r640/1dcd000e3ba14e6e61f8"}],"uri":"1dcd000e3ba14e6e61f8","height":379,"width":640,"type":1}]
             * large_image_list : [{"url":"http://p9.pstatp.com/large/1dcd000e3ba14e6e61f8","url_list":[{"url":"http://p9.pstatp.com/large/1dcd000e3ba14e6e61f8"},{"url":"http://pb1.pstatp.com/large/1dcd000e3ba14e6e61f8"},{"url":"http://pb3.pstatp.com/large/1dcd000e3ba14e6e61f8"}],"uri":"1dcd000e3ba14e6e61f8","height":379,"width":640,"type":1}]
             */

            var text: String? = null
            var thumb_image_list: List<ThumbImageListBean>? = null
            var large_image_list: List<LargeImageListBean>? = null

            class ThumbImageListBean {
                /**
                 * url : http://p9.pstatp.com/list/r640/1dcd000e3ba14e6e61f8
                 * url_list : [{"url":"http://p9.pstatp.com/list/r640/1dcd000e3ba14e6e61f8"},{"url":"http://pb1.pstatp.com/list/r640/1dcd000e3ba14e6e61f8"},{"url":"http://pb3.pstatp.com/list/r640/1dcd000e3ba14e6e61f8"}]
                 * uri : 1dcd000e3ba14e6e61f8
                 * height : 379
                 * width : 640
                 * type : 1
                 */

                var url: String? = null
                var uri: String? = null
                var height: Int = 0
                var width: Int = 0
                var type: Int = 0
                var url_list: List<UrlListBeanX>? = null

                class UrlListBeanX {
                    /**
                     * url : http://p9.pstatp.com/list/r640/1dcd000e3ba14e6e61f8
                     */

                    var url: String? = null
                }
            }

            class LargeImageListBean {
                /**
                 * url : http://p9.pstatp.com/large/1dcd000e3ba14e6e61f8
                 * url_list : [{"url":"http://p9.pstatp.com/large/1dcd000e3ba14e6e61f8"},{"url":"http://pb1.pstatp.com/large/1dcd000e3ba14e6e61f8"},{"url":"http://pb3.pstatp.com/large/1dcd000e3ba14e6e61f8"}]
                 * uri : 1dcd000e3ba14e6e61f8
                 * height : 379
                 * width : 640
                 * type : 1
                 */

                var url: String? = null
                var uri: String? = null
                var height: Int = 0
                var width: Int = 0
                var type: Int = 0
                var url_list: List<UrlListBeanXX>? = null

                class UrlListBeanXX {
                    /**
                     * url : http://p9.pstatp.com/large/1dcd000e3ba14e6e61f8
                     */

                    var url: String? = null
                }
            }
        }

        class FoldReasonBean {
            /**
             * open_url : sslocal://detail?groupid=6293724675596402946
             * title : 为什么折叠？
             */

            var open_url: String? = null
            var title: String? = null
        }

        class ConcernTagListBean {
            /**
             * concern_id : 6213182495320443393
             * name : 火车
             * schema : sslocal://concern?tab_sname=wenda&api_param=%7B%22wenda_api_param%22%3A%7B%22scope%22%3A%22toutiao_wenda%22%2C%22origin_from%22%3A%22click_headline%22%2C%22parent_enter_from%22%3A%22click_headline%22%2C%22enter_from%22%3A%22question%22%7D%7D&cid=6213182495320443393
             */

            var concern_id: String? = null
            var name: String? = null
            var schema: String? = null
        }
    }

    class ModuleListBean {
        /**
         * day_icon_url : http://p3.pstatp.com/origin/1bf50001abbc1c7f8dba
         * text : 更多问答
         * icon_type : 2
         * night_icon_url : http://p3.pstatp.com/origin/1bf40001abebc0717135
         * schema : sslocal://feed?category=question_and_answer&concern_id=6260258266329123329&type=4&name=%E9%97%AE%E7%AD%94&api_param=%7B%22source%22%3A%22question_brow%22%2C%22origin_from%22%3Anull%2C%22enter_from%22%3Anull%7D
         */

        var day_icon_url: String? = null
        var text: String? = null
        var icon_type: Int = 0
        var night_icon_url: String? = null
        var schema: String? = null
    }

    class AnsListBean  {
        /**
         * content_abstract : {"text":"我去过印度，觉得印度人有时也太可爱了，在他们眼里，印度几乎就是唯一的，他们接受新事物的能力似乎非常的有限。但真心是想不到，印度居然还是IT大国。去过印度的人通常都会从导游那里知道：从新德里出发到阿格拉的泰姬陵之间的一趟列车，时速最高的时候达到了160公里/每小时，被印度人称为当地最快的火车。因为印度人非常的热情，看到中国游客就会用蹩脚的汉语跟中国人搭讪，甚至会问：\u201c中国有没有这样快的火车呀？\u201d，这让人尴尬不已，不知道如何回答是好。我在想如下回答，如何？----对不起，中国没有时速160的火车，只有时速360的动车。----我们中国的火车坐的人少，拉轻，印度的火车超载了，跑不快，所以中国的火车要快一点。----你们印度人是坐在车外面的，所以感觉很快，我们的高铁是坐里面的，所以感觉不到快。","thumb_image_list":[{"url":"http://p1.pstatp.com/list/r498/216d000c29349bc2648f","url_list":[{"url":"http://p1.pstatp.com/list/r498/216d000c29349bc2648f"},{"url":"http://pb3.pstatp.com/list/r498/216d000c29349bc2648f"},{"url":"http://pb3.pstatp.com/list/r498/216d000c29349bc2648f"}],"uri":"list/r498/216d000c29349bc2648f","height":350,"width":498,"type":1}],"large_image_list":[{"url":"http://p1.pstatp.com/large/216d000c29349bc2648f","url_list":[{"url":"http://p1.pstatp.com/large/216d000c29349bc2648f"},{"url":"http://pb3.pstatp.com/large/216d000c29349bc2648f"},{"url":"http://pb3.pstatp.com/large/216d000c29349bc2648f"}],"uri":"large/216d000c29349bc2648f","height":350,"width":498,"type":1}],"video_list":[]}
         * create_time : 1494936653
         * user : {"uname":"媒体人杨壮波的落脚地","avatar_url":"http://p9.pstatp.com/thumb/1787/4062932054","user_id":"3747947486","is_verify":0,"create_time":1419220894,"user_intro":"","user_auth_info":"","schema":"sslocal://profile?uid=3747947486&refer=wenda"}
         * share_data : {"content":"我去过印度，觉得印度人有时也太可爱了，在他们眼里，印度几乎就是唯一的，他们接受新事物的能力似乎非常的有限。但真心是想不到，印度居然还是IT大国。去过印度的人通常都会从导游那里知道：从新德里出发到阿格拉的泰姬陵之间的一趟列车，时速最高的时候达到了160公里/每小时，被印度人称为当地最快的火车。因为印度人非常的热情，看到中国游客就会用蹩脚的汉语跟中国人搭讪，甚至会问：\u201c中国有没有这样快的火车呀？\u201d，这让人尴尬不已，不知道如何回答是好。我在想如下回答，如何？----对不起，中国没有时速160的火车，只有时速360的动车。----我们中国的火车坐的人少，拉轻，印度的火车超载了，跑不快，所以中国的火车要快一点。----你们印度人是坐在车外面的，所以感觉很快，我们的高铁是坐里面的，所以感觉不到快。","image_url":"http://p1.pstatp.com/list/r498/216d000c29349bc2648f","share_url":"https://wenda.toutiao.com/m/wapshare/answer/brow/?ansid=6420704033253622018&","title":"头条问答-印度最快的列车时速160，当地人问\u201c中国火车有这么快吗\u201d，怎么回答？"}
         * ans_url : https://ic.snssdk.com/wenda/v1/wapanswer/content/?ansid=6420704033253622018
         * ansid : 6420704033253622018
         * is_show_bury : true
         * is_buryed : false
         * bury_count : 34
         * title :
         * digg_count : 566
         * is_digg : false
         * schema : sslocal://wenda_detail?gd_ext_json=%7B%22ansid%22%3A6420704033253622018%7D&ansid=6420704033253622018&api_param=%7B%22in_offset%22%3A0%2C%22has_more%22%3Atrue%2C%22next_offset%22%3A10%2C%22answer_list%22%3A%5B6420704033253622018%2C6420545734315081985%2C6420999813550047490%2C6420564644980588801%2C6420722026490626306%2C6420724394624041217%2C6420874208393298177%2C6420766146428928258%2C6420914953204531457%2C6422088403512197378%5D%2C%22answer_type%22%3A%22nice_answer%22%7D
         */

        var content_abstract: ContentAbstractBean? = null
        var create_time: Int = 0
        var user: UserBeanX? = null
        var share_data: ShareDataBeanX? = null
        var ans_url: String? = null
        var ansid: String? = null
        var qid: String? = null
        var isIs_show_bury: Boolean = false
        var isIs_buryed: Boolean = false
        var bury_count: Int = 0
        var title: String? = null
        var digg_count: Int = 0
        var isIs_digg: Boolean = false
        var schema: String? = null



        class ContentAbstractBean {
            /**
             * text : 我去过印度，觉得印度人有时也太可爱了，在他们眼里，印度几乎就是唯一的，他们接受新事物的能力似乎非常的有限。但真心是想不到，印度居然还是IT大国。去过印度的人通常都会从导游那里知道：从新德里出发到阿格拉的泰姬陵之间的一趟列车，时速最高的时候达到了160公里/每小时，被印度人称为当地最快的火车。因为印度人非常的热情，看到中国游客就会用蹩脚的汉语跟中国人搭讪，甚至会问：“中国有没有这样快的火车呀？”，这让人尴尬不已，不知道如何回答是好。我在想如下回答，如何？----对不起，中国没有时速160的火车，只有时速360的动车。----我们中国的火车坐的人少，拉轻，印度的火车超载了，跑不快，所以中国的火车要快一点。----你们印度人是坐在车外面的，所以感觉很快，我们的高铁是坐里面的，所以感觉不到快。
             * thumb_image_list : [{"url":"http://p1.pstatp.com/list/r498/216d000c29349bc2648f","url_list":[{"url":"http://p1.pstatp.com/list/r498/216d000c29349bc2648f"},{"url":"http://pb3.pstatp.com/list/r498/216d000c29349bc2648f"},{"url":"http://pb3.pstatp.com/list/r498/216d000c29349bc2648f"}],"uri":"list/r498/216d000c29349bc2648f","height":350,"width":498,"type":1}]
             * large_image_list : [{"url":"http://p1.pstatp.com/large/216d000c29349bc2648f","url_list":[{"url":"http://p1.pstatp.com/large/216d000c29349bc2648f"},{"url":"http://pb3.pstatp.com/large/216d000c29349bc2648f"},{"url":"http://pb3.pstatp.com/large/216d000c29349bc2648f"}],"uri":"large/216d000c29349bc2648f","height":350,"width":498,"type":1}]
             * video_list : []
             */

            var text: String? = null
            var thumb_image_list: List<ThumbImageListBeanX>? = null
            var large_image_list: List<LargeImageListBeanX>? = null
            var video_list: List<*>? = null

            class ThumbImageListBeanX {
                /**
                 * url : http://p1.pstatp.com/list/r498/216d000c29349bc2648f
                 * url_list : [{"url":"http://p1.pstatp.com/list/r498/216d000c29349bc2648f"},{"url":"http://pb3.pstatp.com/list/r498/216d000c29349bc2648f"},{"url":"http://pb3.pstatp.com/list/r498/216d000c29349bc2648f"}]
                 * uri : list/r498/216d000c29349bc2648f
                 * height : 350
                 * width : 498
                 * type : 1
                 */

                var url: String? = null
                var uri: String? = null
                var height: Int = 0
                var width: Int = 0
                var type: Int = 0
                var url_list: List<UrlListBeanXXX>? = null

                class UrlListBeanXXX {
                    /**
                     * url : http://p1.pstatp.com/list/r498/216d000c29349bc2648f
                     */

                    var url: String? = null
                }
            }

            class LargeImageListBeanX {
                /**
                 * url : http://p1.pstatp.com/large/216d000c29349bc2648f
                 * url_list : [{"url":"http://p1.pstatp.com/large/216d000c29349bc2648f"},{"url":"http://pb3.pstatp.com/large/216d000c29349bc2648f"},{"url":"http://pb3.pstatp.com/large/216d000c29349bc2648f"}]
                 * uri : large/216d000c29349bc2648f
                 * height : 350
                 * width : 498
                 * type : 1
                 */

                var url: String? = null
                var uri: String? = null
                var height: Int = 0
                var width: Int = 0
                var type: Int = 0
                var url_list: List<UrlListBeanXXXX>? = null

                class UrlListBeanXXXX {
                    /**
                     * url : http://p1.pstatp.com/large/216d000c29349bc2648f
                     */

                    var url: String? = null
                }
            }
        }

        class UserBeanX  {
            /**
             * uname : 媒体人杨壮波的落脚地
             * avatar_url : http://p9.pstatp.com/thumb/1787/4062932054
             * user_id : 3747947486
             * is_verify : 0
             * create_time : 1419220894
             * user_intro :
             * user_auth_info :
             * schema : sslocal://profile?uid=3747947486&refer=wenda
             */

            var uname: String? = null
            var avatar_url: String? = null
            var user_id: String? = null
            var is_verify: Int = 0
            var create_time: Int = 0
            var user_intro: String? = null
            var user_auth_info: String? = null
            var schema: String? = null

            constructor(`in`: Parcel) {
                uname = `in`.readString()
                avatar_url = `in`.readString()
                user_id = `in`.readString()
                is_verify = `in`.readInt()
                create_time = `in`.readInt()
                user_intro = `in`.readString()
                user_auth_info = `in`.readString()
                schema = `in`.readString()
            }


        }

        class ShareDataBeanX  {
            /**
             * content : 我去过印度，觉得印度人有时也太可爱了，在他们眼里，印度几乎就是唯一的，他们接受新事物的能力似乎非常的有限。但真心是想不到，印度居然还是IT大国。去过印度的人通常都会从导游那里知道：从新德里出发到阿格拉的泰姬陵之间的一趟列车，时速最高的时候达到了160公里/每小时，被印度人称为当地最快的火车。因为印度人非常的热情，看到中国游客就会用蹩脚的汉语跟中国人搭讪，甚至会问：“中国有没有这样快的火车呀？”，这让人尴尬不已，不知道如何回答是好。我在想如下回答，如何？----对不起，中国没有时速160的火车，只有时速360的动车。----我们中国的火车坐的人少，拉轻，印度的火车超载了，跑不快，所以中国的火车要快一点。----你们印度人是坐在车外面的，所以感觉很快，我们的高铁是坐里面的，所以感觉不到快。
             * image_url : http://p1.pstatp.com/list/r498/216d000c29349bc2648f
             * share_url : https://wenda.toutiao.com/m/wapshare/answer/brow/?ansid=6420704033253622018&
             * title : 头条问答-印度最快的列车时速160，当地人问“中国火车有这么快吗”，怎么回答？
             */

            var content: String? = null
            var image_url: String? = null
            var share_url: String? = null
            var title: String? = null

            constructor(`in`: Parcel) {
                content = `in`.readString()
                image_url = `in`.readString()
                share_url = `in`.readString()
                title = `in`.readString()
            }


        }
    }
}
