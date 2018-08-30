package com.lz.framecase.bean

/**
 * -----------作者----------日期----------变更内容-----
 * -          刘泽      2018-08-30       创建class
 */
class VideoDetailInfoEntity {
   var group_flags: Int = 0
   var video_type: Int = 0
   var video_preloading_flag: Int = 0
   var direct_play: Int = 0
   var detail_video_large_image: DetailVideoLargeImageEntity? = null
   var show_pgc_subscribe: Int = 0
   var video_third_monitor_url: String? = null
   var video_id: String? = null
   var video_watching_count: Int = 0
   var video_watch_count: Int = 0
   var video_url: List<*>? = null
}
