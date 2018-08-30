package com.lz.framecase.bean

import com.chad.library.adapter.base.entity.MultiItemEntity
import com.google.gson.annotations.SerializedName

/**
 * Created by Meiji on 2017/5/21.
 */

class WendaArticleDataBean : MultiItemEntity {
    private var itemType: Int = 1

    companion object {
        public val WENDATEXT: Int = 1
        public val WENDAIMG: Int = 2
        public val WENDAIMAGES: Int = 3
    }

    fun setItemType(type: Int) {
        itemType = type
    }

    override fun getItemType(): Int {
        return itemType
    }


    /**
     * cell_type : 36
     * extra : {"wenda_video":[],"show_answer":false,"video_large_card":false,"label_style":{"color_type":0,"name":""},"show_video":false,"video_source_name":"","wenda_image":{"three_image_list":[{"url":"http:\/\/p3.pstatp.com\/list\/300x196\/18a2000bcefb11e51664","url_list":[{"url":"http:\/\/p3.pstatp.com\/list\/300x196\/18a2000bcefb11e51664"},{"url":"http:\/\/pb9.pstatp.com\/list\/300x196\/18a2000bcefb11e51664"},{"url":"http:\/\/pb3.pstatp.com\/list\/300x196\/18a2000bcefb11e51664"}],"uri":"list\/300x196\/18a2000bcefb11e51664","height":576,"width":768,"type":1},{"url":"http:\/\/p3.pstatp.com\/list\/300x196\/e580008f01daabc4b83","url_list":[{"url":"http:\/\/p3.pstatp.com\/list\/300x196\/e580008f01daabc4b83"},{"url":"http:\/\/pb9.pstatp.com\/list\/300x196\/e580008f01daabc4b83"},{"url":"http:\/\/pb3.pstatp.com\/list\/300x196\/e580008f01daabc4b83"}],"uri":"list\/300x196\/e580008f01daabc4b83","height":295,"width":440,"type":1},{"url":"http:\/\/p9.pstatp.com\/list\/300x196\/12320005f73e2f519d5c","url_list":[{"url":"http:\/\/p9.pstatp.com\/list\/300x196\/12320005f73e2f519d5c"},{"url":"http:\/\/pb1.pstatp.com\/list\/300x196\/12320005f73e2f519d5c"},{"url":"http:\/\/pb3.pstatp.com\/list\/300x196\/12320005f73e2f519d5c"}],"uri":"list\/300x196\/12320005f73e2f519d5c","height":768,"width":431,"type":1}],"small_image_list":[],"large_image_list":[],"medium_image_list":[]},"schema":"sslocal:\/\/wenda_list?qid=6333792867344974082&gd_ext_json=%7B%22qid%22%3A%226333792867344974082%22%2C%22ansid%22%3A%226334054392425087233%22%2C%22enter_from%22%3A%22click_answer_hot%22%7D&video_auto_play=0&api_param=%7B%22scope%22%3A%22toutiao_wenda%22%2C%22origin_from%22%3A%22native_wenda_home%22%2C%22enter_ansid%22%3A%226334054392425087233%22%2C%22enter_from%22%3A%22answer_hot%22%7D"}
     * question : {"status":1,"op_status":0,"qid":6333792867344974082,"nice_ans_count":336,"uname":"","create_time":1474701070,"normal_ans_count":1227,"item_id":0,"user_id":5547793806,"title":"\u4f60\u4eec\u5f53\u5730\u90a3\u4e9b\u6df7\u9ed1\u793e\u4f1a\u7684\u4eba\u73b0\u5728\u90fd\u600e\u4e48\u6837\u4e86\uff1f","content":{"text":"\u6211\u77e5\u9053\u7684\u90fd\u51fa\u53bb\u6253\u5de5\u4e86\uff0c\u6709\u4e9b\u751a\u81f3\u8e72\u8fc7\u51e0\u5e74\u7262\uff0c\u603b\u4e4b\u5c31\u662f\u6ca1\u4ee5\u524d\u90a3\u4e48\u56a3\u5f20\u5f97\u610f\uff0c\u4f60\u4eec\u77e5\u9053\u7684\u5462\uff1f","pic_uri_list":[],"thumb_image_list":[],"large_image_list":[]},"group_id":null}
     * behot_time : 1495245397
     * cursor : 0
     * filter_words : [{"is_selected":false,"id":"8:0","show_dislike":true,"name":"重复、旧闻"},{"is_selected":false,"id":"9:1","show_dislike":true,"name":"内容质量差"},{"is_selected":false,"id":"3:306457840","show_dislike":true,"name":"黑社会"},{"is_selected":false,"id":"6:47778225","show_dislike":true,"name":"打黑除恶"}]
     * answer : {"status":1,"qid":6333792867344974082,"abstract":"\u8fd9\u4e2a\u4eba\u7269\u662f\u6211\u540c\u5b66\u7684\u8205\u8205\uff0c\u9053\u4e0a\u90fd\u53eb\u4ed6\u8363\u54e5\uff0c\u4e1c\u5317\u9ed1\u9053\u7684\uff0c\u66fe\u7ecf\u548c\u9ed1\u8001\u5927\u5218\u52c7\u6df7\u7684\uff0c\u73b0\u5728\u5feb60\u5de6\u53f3\u4e86\uff0c\u4e09\u756a\u4e94\u6b21\u8fdb\u5bab\uff0c\u7d2f\u8ba1\u5728\u76d1\u72f1\u5446\u4e86\u5c0f30\u5e74\uff0c2013\u5e74\u51fa\u6765\u4e00\u6b21\uff0c\u56e0\u4e3a\u624b\u4e0b\u4e00\u4e2a\u5c0f\u5f1f\u62ff\u67aa\u5931\u624b\u628a\u5bf9\u65b9\u6253\u6b7b\u4e86\uff0c\u5f53\u65f6\u8fd9\u5c0f\u5f1f\u5f00\u9762\u5305\u8f66\u53bb\u7684\uff0c\u628a\u4eba\u6253\u6b7b\u540e\u6ca1\u6765\u5f97\u53ca\u5f00\u8f66\u8d70\uff0c\u8363\u54e5\u77e5\u9053\u540e\u5927\u9a82\u4ed6\u4e00\u987f\uff0c\u544a\u8bc9\u4ed6\uff0c\u8f66\u4e0d\u80fd\u8981\u4e86\uff0c\u5c0f\u5f1f\u5fc3\u75bc\u8f66\uff0c\u4e8b\u53d1\u540e\u5341\u5929\uff0c\u770b\u6ca1\u5565\u4e8b\uff0c\u5c0f\u5f1f\u6df1\u591c\u53bb\u53d6\u8f66\uff0c\u88ab\u8b66\u5bdf\u5f53\u573a\u62ff\u4e0b\uff0c\u5c0f\u5f1f\u88ab\u6293\uff0c\u67aa\u662f\u8363\u54e5\u7684\uff0c\u6240\u4ee5\u8363\u54e5\u4e5f\u8fdb\u53bb\u4e86\uff0c\u8363\u54e5\u5224\u4e86\u4e0d\u5c11\u5e74\uff0c\u6700\u540e\u51cf\u52117\u5e74\u51fa\u6765\u4e86\uff0c\u9053\u4e0a\u670b\u53cb\u63a5\u98ce\u8bbe\u5bb4\u5f88\u8bb2\u7a76\uff0c\u6bcf\u4e2a\u4eba\u515c\u91cc\u90fd\u6709\u67aa\uff0c\u51fa\u6765\u4e00\u4e2a\u6708\u540e\u53c8\u56e0\u4e3a\u8d29\u6bd2\u8fdb\u53bb\u4e86\uff0c\u4e03\u514b\u6bd2\u54c1\uff0c\u542c\u8bf4\u8d85\u8fc7\u4e03\u514b\u7f6a\u8fc7\u5c31\u5927\u4e86\u3002\u8363\u54e5\u8bf4\uff0c\u4ed6\u8fd9\u7ea7\u522b\u7684\u72af\u4eba\u5728\u7262\u91cc\u6bd4\u5916\u9762\u81ea\u5728\uff0c\u5728\u7262\u91cc\u4e00\u6837\u6709\u5927\u7b14\u751f\u610f\u505a\uff0c\u4e00\u5e74\u4e5f\u80fd20\u6765\u4e07\uff0c\u73b0\u5728\u5f88\u591a\u5f53\u5e74\u8fd8\u4e0d\u9519\u7684\u5927\u54e5\u90fd\u5728\u7262\u91cc\u505a\u751f\u610f\uff0c\u51fa\u6765\u4e86\u4e5f\u60f3\u529e\u6cd5\u518d\u8fdb\u53bb\u3002","uname":"\u67cf\u94ed\u4e00","create_time":1474761961,"ansid":6334054392425087233,"user_id":6029773522,"bury_count":809,"display_status":2,"digg_count":2100,"can_comment":1}
     * id : 6334054392425087000
     */

    var cell_type: Int = 0
    var extra: String? = null
    var question: String? = null
    var behot_time: String? = null
    var cursor: Int = 0
    var answer: String? = null
    var id: Long = 0
    var extraBean: ExtraBean? = null
    var questionBean: QuestionBean? = null
    var answerBean: AnswerBean? = null

    class ExtraBean {

        /**
         * wenda_video : []
         * show_answer : false
         * video_large_card : false
         * label_style : {"color_type":0,"name":""}
         * show_video : false
         * video_source_name :
         * wenda_image : {"three_image_list":[{"url":"http://p3.pstatp.com/list/300x196/18a2000bcefb11e51664","url_list":[{"url":"http://p3.pstatp.com/list/300x196/18a2000bcefb11e51664"},{"url":"http://pb9.pstatp.com/list/300x196/18a2000bcefb11e51664"},{"url":"http://pb3.pstatp.com/list/300x196/18a2000bcefb11e51664"}],"uri":"list/300x196/18a2000bcefb11e51664","height":576,"width":768,"type":1},{"url":"http://p3.pstatp.com/list/300x196/e580008f01daabc4b83","url_list":[{"url":"http://p3.pstatp.com/list/300x196/e580008f01daabc4b83"},{"url":"http://pb9.pstatp.com/list/300x196/e580008f01daabc4b83"},{"url":"http://pb3.pstatp.com/list/300x196/e580008f01daabc4b83"}],"uri":"list/300x196/e580008f01daabc4b83","height":295,"width":440,"type":1},{"url":"http://p9.pstatp.com/list/300x196/12320005f73e2f519d5c","url_list":[{"url":"http://p9.pstatp.com/list/300x196/12320005f73e2f519d5c"},{"url":"http://pb1.pstatp.com/list/300x196/12320005f73e2f519d5c"},{"url":"http://pb3.pstatp.com/list/300x196/12320005f73e2f519d5c"}],"uri":"list/300x196/12320005f73e2f519d5c","height":768,"width":431,"type":1}],"small_image_list":[],"large_image_list":[],"medium_image_list":[]}
         * schema : sslocal://wenda_list?qid=6333792867344974082&gd_ext_json=%7B%22qid%22%3A%226333792867344974082%22%2C%22ansid%22%3A%226334054392425087233%22%2C%22enter_from%22%3A%22click_answer_hot%22%7D&video_auto_play=0&api_param=%7B%22scope%22%3A%22toutiao_wenda%22%2C%22origin_from%22%3A%22native_wenda_home%22%2C%22enter_ansid%22%3A%226334054392425087233%22%2C%22enter_from%22%3A%22answer_hot%22%7D
         */

        var isShow_answer: Boolean = false
        var isVideo_large_card: Boolean = false
        var label_style: LabelStyleBean? = null
        var isShow_video: Boolean = false
        var video_source_name: String? = null
        var wenda_image: WendaImageBean? = null
        var schema: String? = null
        var wenda_video: List<*>? = null

        class LabelStyleBean {
            /**
             * color_type : 0
             * name :
             */

            var color_type: Int = 0
            var name: String? = null
        }

        class WendaImageBean {
            var three_image_list: List<ThreeImageListBean>? = null
            var small_image_list: List<*>? = null
            var large_image_list: List<LargeImageList>? = null
            var medium_image_list: List<*>? = null

            class ThreeImageListBean {
                /**
                 * url : http://p3.pstatp.com/list/300x196/18a2000bcefb11e51664
                 * url_list : [{"url":"http://p3.pstatp.com/list/300x196/18a2000bcefb11e51664"},{"url":"http://pb9.pstatp.com/list/300x196/18a2000bcefb11e51664"},{"url":"http://pb3.pstatp.com/list/300x196/18a2000bcefb11e51664"}]
                 * uri : list/300x196/18a2000bcefb11e51664
                 * height : 576
                 * width : 768
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
                     * url : http://p3.pstatp.com/list/300x196/18a2000bcefb11e51664
                     */

                    var url: String? = null
                }
            }

            class LargeImageList {

                var url: String? = null
            }
        }
    }

    class QuestionBean {

        /**
         * status : 1
         * op_status : 0
         * qid : 6333792867344974000
         * nice_ans_count : 336
         * uname :
         * create_time : 1474701070
         * normal_ans_count : 1227
         * item_id : 0
         * user_id : 5547793806
         * title : 你们当地那些混黑社会的人现在都怎么样了？
         * content : {"text":"我知道的都出去打工了，有些甚至蹲过几年牢，总之就是没以前那么嚣张得意，你们知道的呢？","pic_uri_list":[],"thumb_image_list":[],"large_image_list":[]}
         * group_id : null
         */

        var status: Int = 0
        var op_status: Int = 0
        var qid: Long = 0
        var nice_ans_count: Int = 0
        var uname: String? = null
        var create_time: Int = 0
        var normal_ans_count: Int = 0
        var item_id: Int = 0
        var user_id: Long = 0
        var title: String? = null
        var content: ContentBean? = null
        var group_id: Any? = null

        override fun equals(o: Any?): Boolean {
            if (this === o)
                return true
            if (o == null || javaClass != o.javaClass)
                return false

            val that = o as QuestionBean?

            return if (title != that!!.title) false else content == that.content
        }

        override fun hashCode(): Int {
            var result = title!!.hashCode()
            result = 31 * result + content!!.hashCode()
            return result
        }

        class ContentBean {
            /**
             * text : 我知道的都出去打工了，有些甚至蹲过几年牢，总之就是没以前那么嚣张得意，你们知道的呢？
             * pic_uri_list : []
             * thumb_image_list : []
             * large_image_list : []
             */

            var text: String? = null
            var pic_uri_list: List<*>? = null
            var thumb_image_list: List<*>? = null
            var large_image_list: List<*>? = null
        }
    }

    class AnswerBean {

        /**
         * status : 1
         * qid : 6355230839860625000
         * abstract : 很多人喜欢在院子里种树，但有些树适合在院子里种，有些树不适合。那么，院子里中出的风水有什么禁忌呢？哪些树可以种，哪些不可以呢？下面和大家一起看看。院子里种树的风水有什么禁忌种树也有风水讲究你知道院子里不能种什么树吗院子中央不宜放植栽院子中心不宜种树，树种树风水院子里种树风水知识植物有阴阳属性。喜阳的植物，如植(置)于阴湿的环境，则体弱，无花，无果或死亡。如：白兰、玫瑰、茉莉、梅花、牡丹、芍药、杜鹃、菊花等。这类植物，须得一千八百个勒克斯光照度，才能正常发能。而文竹，龟背竹、万年青、绿萝、蓬莱松、巴西铁等，在一百个勒克斯光照度条件下，亦能正常生长。此类植物可长期置于室内或阴暗处。属于阴性植物。院子里种树风水注意事项风水树知识根据古代风水理论，中式风格的别墅院子前院可以栽种桂亦主桃花、多生女儿，此时可在向上施以遮形、通气之法。艮方有山而形恶者相同 。植物作为一种活物，其本身存在的风水能量就要比一些摆件或者装饰之类的死物更强，因此，在进行庭院绿化的过程中必须要关注下各种不同植物的风水问题。庭院绿化过程中要注意植物的风水禁忌在这里我们要为各位介绍的就是关于植物的风水禁忌问题，在庭院绿化的过程中，一定要尽可能的避免触犯这些风水禁忌。1、任何植物不可与门相对，因为大门乃是住宅的纳气口，所以庭院大门和住宅大门必须相通，中间不可以有任何阻碍，如果中间出现了什么植物挡住了从大门进入的气场，必然会对住宅的气场环境带来一定不利影响，所以这一点大家必须重视。2、庭院中心位置不可有植物，因为在风水学中认为中心的位置乃是风水气运最强的地方，所以最好将自己的住宅放在庭院的中心，这样对于住宅的气运才会有所帮助。如果在中心位置有植物，特别是大树，将会严重破坏住宅的风水气场，所以最好是在庭院中心处建造一个水池、喷泉之类与水有关的事物，这样可以旺盛财运。3、带有尖刺的植物不宜种植，在庭院绿化的过程中种植的植物一定要谨慎选择，由于风水学中比较忌讳带有尖刺的事物，所以植物如果有尖刺，最好也不要种在庭院里，那样对于住宅的风水气场会带来不利影响。此外，如果植物的相貌丑恶，最好也不要种。这些就是庭院绿化过程中，大家必须注意的与植物相关的风水禁忌问题。一个家宅中，除了大厅、卧室，庭院是每个人的必经之处，所以庭院的风水布局也是住宅最重要的部分之一，合理的布置庭院，不仅能让庭院看起来美观大方，还能形成良好的气场。我们可以通过庭院绿化来做好庭院布局。有关庭院布局，大家可以看看下面的讲解。庭院绿化时需要注意的一些风水布局问题不宜种植易斜的树：庭院绿化最重要的是绿化植物的选择，选择绿化植物的时候尽量选择树干笔直、生命力旺盛的植物，这样会旺子孙，一定不要选择难成活、遇风雨容易倾斜的植物，这样的植物不利于房屋气场的通行，影响家人的身体健康。庭院不要铺很多碎石：泥土是很多植物的最佳种植方式，但是近年来，有人不断追求新潮，喜欢用碎石来种植植物，以增加庭院的韵味，但是庭院绿化很忌讳道路铺碎石。另外，庭院道路上铺碎石，不方便人行走，走的时候容易发生磕磕绊绊，尤其是家中有老人的，老人摔跤严重时会有生命危险。设计假山、喷泉，风水更佳：所谓风水布局，有风来，七亨通；有水到，水到渠成。庭院绿化的时候，房主可考虑修建一些假山或喷泉，利用绿化、水体造型，不仅能让庭院更美观，水气加湿空气湿度，让家人身体健康。庭院绿化的布置，与家人的运势息息相关，这里提醒大家，在开工前一定要先充分了解庭院绿化风水的有关知识，否则破坏原有的风水就不好了。家里不能种石榴树，这要看你石榴的大小了，石榴本来有多子多福的寓意，土栽盆栽都有。既美化了环境，又可以吃到美味的果实，一举两得。但是如果种植位置不对，或者树形大小不合适，如此情况是不建议在家种植的。但是如果说不能在家种植，似乎有些武断了！看看哪些情况下不适合在家种：风水禁忌院子或客厅中央不宜放。首先在院子中间种植石榴树，树形较大，容易挡光，不利于采光。另外，如果是落叶树经常掉叶，难于清扫，也有碍美观和卫生。另外，认为家中不能种植石榴树的人，大多是因为“石榴裙下死”这个故事的传说，容易对家庭造成不和谐。大家都生活在现代社会，所谓的这些故事传说，并不能得到验证，没有说服力。如果你对植物家居风水深信不疑，那还是不要在家种石榴！养护环境的差异许多人在家种石榴盆栽，但只开花不结果，不是什么风水问题，是家里的环境有关，还有品种，以及种植方法不恰当的影响。1.品种石榴品种很多，主要分二大类，即花石榴和果石榴（果石榴多单瓣花），如果选择的仅供赏花的花石榴，就可能只开花不结果，即使结果，也只能结直径仅2—3厘米的小果实，不能食用，也就是看看而已。现有一种红花重瓣石榴，花艳而结果，既能赏花又能食果。2.环境要求种植石榴的土壤要求疏松、肥沃、排水良好；光照应充足，生长期要求全日照，并且光照越充足，花越多越鲜艳，光照不足时，可能只长叶不开花；适宜生长温度15-20℃。而家庭种最大的问题就是光照，除非是庭院，无遮挡阳光充足，否则很难达到石榴开花结果的要求。3.日常管理浇水、施肥、适度剪枝、病虫害防治也很重要，充分注意才能花好果丰。家里是可以种石榴的，在风水上还是有一定好处的：石榴花的风水价值石榴花花朵表面光滑，颜色鲜红艳丽，象征着富贵，成熟的美丽。如果石榴花摆放位置不同那么它所代表的风水也不同首先因为石榴花的果实鲜美肥硕，而且色彩鲜艳光亮，所以表示了喜庆，如果把石榴花放在客厅、阳台、院子里，则会让家庭多子多孙，多福寿。其次石榴花的果子香甜口口，常被人们代表为繁荣、昌盛、团圆、和睦，是人们喜爱的一种吉祥之果，在民间人们会把石榴花的果实切开，会出来丰满饱实的果粒，则用来表示多多生子，以及农家丰收满满。最后石榴也会作为一种中秋佳节赠送的礼品，象征吉祥，又因为石榴花带一个榴字，可以谐音为留，所以也表示了留下之意，“送榴传谊”就延伸出来了。石榴花的价值当你把石榴花摆在家里的阳台上或者卧室里时，不仅可以带来吉祥美好的祝福，也会祝福你多子多孙多福寿，而且石榴花的价值也不少呢石榴花摆在室内美化空间，等石榴花的果实成熟以后，也可以食用，药用。石榴花的果实可以炒菜，作为一道美味的小菜入口，甜润爽口。石榴花的果实也可以入药，治疗腹泻、杀早、止咳等。所以石榴花不仅风水好，它的价值也不容易小觑，不妨你试试，绝对让你赞不绝口，会想一直种植石榴花呢！石榴呢，在书上一般记载为落叶灌木或小乔木。石榴树树冠丛状自然圆头形。树干呈灰褐色，上面有丑丑的瘤状突起，树根是黄褐色。石榴树高可达4-5米以上，但是也有分矮生石榴，长成的石榴树只有1米左右。石榴的叶子是针状枝，叶呈长倒卵形或长椭圆形。石榴花是朱红色的，花期是5、6月份。石榴是一个圆圆的小红果，到9月10月的时候就会像一个个红彤彤的小灯笼一样，挂在树上。石榴作为一种常见的水果，也是常见的观赏植物。可谓全身是宝，果皮、根、花皆可入药。石榴晶莹剔透，如红宝石般的果粒，是很多女性都会喜欢的。但是很多人不喜欢石榴外表黑红，有些丑丑的果皮，千万不要小看了石榴的果皮哦，其果皮中含有苹果酸、鞣质、生物碱等成分。所以说，石榴可是实力和内在兼具的水果。
         * uname : 用户58700537490
         * create_time : 1490869217
         * ansid : 6403234528361447000
         * user_id : 58700537490
         * bury_count : 39
         * display_status : 2
         * digg_count : 998
         * can_comment : 1
         */

        var status: Int = 0
        var qid: Long = 0
        @SerializedName("abstract")
        var abstractX: String? = null
        var uname: String? = null
        var create_time: Int = 0
        var ansid: Long = 0
        var user_id: Long = 0
        var bury_count: Int = 0
        var display_status: Int = 0
        var digg_count: Int = 0
        var can_comment: Int = 0
    }

}
