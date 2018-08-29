package com.lz.framecase.bean

/**
 * -----------作者----------日期----------变更内容-----
 * -          刘泽      2018-08-29       创建class
 */
 class ShareEntity {
    /**
     * share_cover : {"uri":"","url_list":["http://p1.pstatp.com/thumb/97e000ecbe7608d7653"]}
     * share_desc : 雷科技：由于达到了苹果董事会的业绩要求，库克获得了56万股苹果股票。获得了大概5800万美元（约合人民币3.95亿元）的收入。苹果的股权相当分散，巴菲特的公司手握大概3%的苹果股票，但已经是它的第四大股东。苹果最大股东的持股比例也只有6.7%。库克把苹果带上了新的高度，下一个又会是谁？http://www.leikeji.com/article/20629
     * share_title : 微头条
     * share_url : https://toutiao.com/ugc/share/thread/1610117113409603/?app=&iid=5034850950&target_app=13
     * share_weibo_desc : null
     */

    var share_cover: ShareCoverEntity? = null
    var share_desc: String? = null
    var share_title: String? = null
    var share_url: String? = null
    var share_weibo_desc: Any? = null
}
