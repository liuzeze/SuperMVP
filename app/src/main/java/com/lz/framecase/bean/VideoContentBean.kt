package com.lz.framecase.bean

/**
 * Created by Meiji on 2017/3/31.
 */

class VideoContentBean {

    /**
     * data : {"status":10,"user_id":"toutiao","video_id":"f2aeddda2a894e53bb3f2cf98994aadb","big_thumbs":[{"img_num":16,"img_url":"https://p1.pstatp.com/origin/19cc000499f3a6986d59","img_x_size":160,"img_y_size":90,"img_x_len":1,"img_y_len":16}],"video_duration":84.8,"video_list":{"video_1":{"backup_url_1":"aHR0cDovL3Y3LnBzdGF0cC5jb20vNWE2YzA4ZmJjZWQxZmIzY2ZhMDdkZGE3Zjg0ZTNkZDUvNThkZGJlMjAvdmlkZW8vbS8xMTQ0YzNiMDAwMDBjMWU1MWIwMWQ1ZjIyMGE0MzMyMTUyNGQwOTQ0MjU5MDI5NmU1NDI3Yjc5NGNlLw==","bitrate":369405,"definition":"360p","main_url":"aHR0cDovL3Y2LjM2NXlnLmNvbS92aWRlby9tLzExNDRjM2IwMDAwMGMxZTUxYjAxZDVmMjIwYTQzMzIxNTI0ZDA5NDQyNTkwMjk2ZTU0MjdiNzk0Y2UvP0V4cGlyZXM9MTQ5MDkzMDczNiZBV1NBY2Nlc3NLZXlJZD1xaDBoOVRkY0VNb1Myb1BqN2FLWCZTaWduYXR1cmU9WWNmakZDNnMxSHFhQ0NxeVZMd3ZkRWNlcXg0JTNE","preload_interval":25,"preload_max_step":10,"preload_min_step":5,"preload_size":327680,"size":4630326,"socket_buffer":221643000,"user_video_proxy":1,"vheight":360,"vtype":"mp4","vwidth":640},"video_2":{"backup_url_1":"aHR0cDovL3Y3LnBzdGF0cC5jb20vNTk0ZTZjNDZjMjliZDkxY2EwZWJhMTFkM2RlMGIxN2EvNThkZGJlMjAvdmlkZW8vbS8yMjBlNmU2MjY3Mjg4NDU0YzkwOTFjOGYyMTZlZThiOWEwMjExNDRiZjAwMDAwMDZmNTE3YjkxMTk1Lw==","bitrate":577524,"definition":"480p","main_url":"aHR0cDovL3Y2LjM2NXlnLmNvbS92aWRlby9tLzIyMGU2ZTYyNjcyODg0NTRjOTA5MWM4ZjIxNmVlOGI5YTAyMTE0NGJmMDAwMDAwNmY1MTdiOTExOTUvP0V4cGlyZXM9MTQ5MDkzMDczNiZBV1NBY2Nlc3NLZXlJZD1xaDBoOVRkY0VNb1Myb1BqN2FLWCZTaWduYXR1cmU9JTJGbEtPMld4QVVtcUtqUGo4TWw5cFpxaFNSeEklM0Q=","preload_interval":25,"preload_max_step":10,"preload_min_step":5,"preload_size":327680,"size":6836189,"socket_buffer":346514400,"user_video_proxy":1,"vheight":480,"vtype":"mp4","vwidth":854},"video_3":{"backup_url_1":"aHR0cDovL3Y3LnBzdGF0cC5jb20vNzM4NTJiOTYxNDgyMzc0MDY2NWIyN2VkODZhNWVhMTEvNThkZGJlMjAvdmlkZW8vbS8yMjA0NTViMDUyMjFjYTg0MDAxOWFjZjkwZDNmZGFmZTNhZDExNDQ4ZGMwMDAwMjRkOTFkNmZkNzZhLw==","bitrate":1274484,"definition":"720p","main_url":"aHR0cDovL3YzLjM2NXlnLmNvbS83Mzg1MmI5NjE0ODIzNzQwNjY1YjI3ZWQ4NmE1ZWExMS81OGRkYmUyMC92aWRlby9tLzIyMDQ1NWIwNTIyMWNhODQwMDE5YWNmOTBkM2ZkYWZlM2FkMTE0NDhkYzAwMDAyNGQ5MWQ2ZmQ3NmEv","preload_interval":25,"preload_max_step":10,"preload_min_step":5,"preload_size":327680,"size":14223659,"socket_buffer":764690400,"user_video_proxy":1,"vheight":720,"vtype":"mp4","vwidth":1280}}}
     * message : success
     * code : 0
     * total : 3
     */

    var data: DataBean? = null
    var message: String? = null
    var code: Int = 0
    var total: Int = 0

    class DataBean {
        /**
         * status : 10
         * user_id : toutiao
         * video_id : f2aeddda2a894e53bb3f2cf98994aadb
         * big_thumbs : [{"img_num":16,"img_url":"https://p1.pstatp.com/origin/19cc000499f3a6986d59","img_x_size":160,"img_y_size":90,"img_x_len":1,"img_y_len":16}]
         * video_duration : 84.8
         * video_list : {"video_1":{"backup_url_1":"aHR0cDovL3Y3LnBzdGF0cC5jb20vNWE2YzA4ZmJjZWQxZmIzY2ZhMDdkZGE3Zjg0ZTNkZDUvNThkZGJlMjAvdmlkZW8vbS8xMTQ0YzNiMDAwMDBjMWU1MWIwMWQ1ZjIyMGE0MzMyMTUyNGQwOTQ0MjU5MDI5NmU1NDI3Yjc5NGNlLw==","bitrate":369405,"definition":"360p","main_url":"aHR0cDovL3Y2LjM2NXlnLmNvbS92aWRlby9tLzExNDRjM2IwMDAwMGMxZTUxYjAxZDVmMjIwYTQzMzIxNTI0ZDA5NDQyNTkwMjk2ZTU0MjdiNzk0Y2UvP0V4cGlyZXM9MTQ5MDkzMDczNiZBV1NBY2Nlc3NLZXlJZD1xaDBoOVRkY0VNb1Myb1BqN2FLWCZTaWduYXR1cmU9WWNmakZDNnMxSHFhQ0NxeVZMd3ZkRWNlcXg0JTNE","preload_interval":25,"preload_max_step":10,"preload_min_step":5,"preload_size":327680,"size":4630326,"socket_buffer":221643000,"user_video_proxy":1,"vheight":360,"vtype":"mp4","vwidth":640},"video_2":{"backup_url_1":"aHR0cDovL3Y3LnBzdGF0cC5jb20vNTk0ZTZjNDZjMjliZDkxY2EwZWJhMTFkM2RlMGIxN2EvNThkZGJlMjAvdmlkZW8vbS8yMjBlNmU2MjY3Mjg4NDU0YzkwOTFjOGYyMTZlZThiOWEwMjExNDRiZjAwMDAwMDZmNTE3YjkxMTk1Lw==","bitrate":577524,"definition":"480p","main_url":"aHR0cDovL3Y2LjM2NXlnLmNvbS92aWRlby9tLzIyMGU2ZTYyNjcyODg0NTRjOTA5MWM4ZjIxNmVlOGI5YTAyMTE0NGJmMDAwMDAwNmY1MTdiOTExOTUvP0V4cGlyZXM9MTQ5MDkzMDczNiZBV1NBY2Nlc3NLZXlJZD1xaDBoOVRkY0VNb1Myb1BqN2FLWCZTaWduYXR1cmU9JTJGbEtPMld4QVVtcUtqUGo4TWw5cFpxaFNSeEklM0Q=","preload_interval":25,"preload_max_step":10,"preload_min_step":5,"preload_size":327680,"size":6836189,"socket_buffer":346514400,"user_video_proxy":1,"vheight":480,"vtype":"mp4","vwidth":854},"video_3":{"backup_url_1":"aHR0cDovL3Y3LnBzdGF0cC5jb20vNzM4NTJiOTYxNDgyMzc0MDY2NWIyN2VkODZhNWVhMTEvNThkZGJlMjAvdmlkZW8vbS8yMjA0NTViMDUyMjFjYTg0MDAxOWFjZjkwZDNmZGFmZTNhZDExNDQ4ZGMwMDAwMjRkOTFkNmZkNzZhLw==","bitrate":1274484,"definition":"720p","main_url":"aHR0cDovL3YzLjM2NXlnLmNvbS83Mzg1MmI5NjE0ODIzNzQwNjY1YjI3ZWQ4NmE1ZWExMS81OGRkYmUyMC92aWRlby9tLzIyMDQ1NWIwNTIyMWNhODQwMDE5YWNmOTBkM2ZkYWZlM2FkMTE0NDhkYzAwMDAyNGQ5MWQ2ZmQ3NmEv","preload_interval":25,"preload_max_step":10,"preload_min_step":5,"preload_size":327680,"size":14223659,"socket_buffer":764690400,"user_video_proxy":1,"vheight":720,"vtype":"mp4","vwidth":1280}}
         */

        var status: Int = 0
        var user_id: String? = null
        var video_id: String? = null
        var video_duration: Double = 0.toDouble()
        var video_list: VideoListBean? = null
        var big_thumbs: List<BigThumbsBean>? = null

        class VideoListBean {
            /**
             * video_1 : {"backup_url_1":"aHR0cDovL3Y3LnBzdGF0cC5jb20vNWE2YzA4ZmJjZWQxZmIzY2ZhMDdkZGE3Zjg0ZTNkZDUvNThkZGJlMjAvdmlkZW8vbS8xMTQ0YzNiMDAwMDBjMWU1MWIwMWQ1ZjIyMGE0MzMyMTUyNGQwOTQ0MjU5MDI5NmU1NDI3Yjc5NGNlLw==","bitrate":369405,"definition":"360p","main_url":"aHR0cDovL3Y2LjM2NXlnLmNvbS92aWRlby9tLzExNDRjM2IwMDAwMGMxZTUxYjAxZDVmMjIwYTQzMzIxNTI0ZDA5NDQyNTkwMjk2ZTU0MjdiNzk0Y2UvP0V4cGlyZXM9MTQ5MDkzMDczNiZBV1NBY2Nlc3NLZXlJZD1xaDBoOVRkY0VNb1Myb1BqN2FLWCZTaWduYXR1cmU9WWNmakZDNnMxSHFhQ0NxeVZMd3ZkRWNlcXg0JTNE","preload_interval":25,"preload_max_step":10,"preload_min_step":5,"preload_size":327680,"size":4630326,"socket_buffer":221643000,"user_video_proxy":1,"vheight":360,"vtype":"mp4","vwidth":640}
             * video_2 : {"backup_url_1":"aHR0cDovL3Y3LnBzdGF0cC5jb20vNTk0ZTZjNDZjMjliZDkxY2EwZWJhMTFkM2RlMGIxN2EvNThkZGJlMjAvdmlkZW8vbS8yMjBlNmU2MjY3Mjg4NDU0YzkwOTFjOGYyMTZlZThiOWEwMjExNDRiZjAwMDAwMDZmNTE3YjkxMTk1Lw==","bitrate":577524,"definition":"480p","main_url":"aHR0cDovL3Y2LjM2NXlnLmNvbS92aWRlby9tLzIyMGU2ZTYyNjcyODg0NTRjOTA5MWM4ZjIxNmVlOGI5YTAyMTE0NGJmMDAwMDAwNmY1MTdiOTExOTUvP0V4cGlyZXM9MTQ5MDkzMDczNiZBV1NBY2Nlc3NLZXlJZD1xaDBoOVRkY0VNb1Myb1BqN2FLWCZTaWduYXR1cmU9JTJGbEtPMld4QVVtcUtqUGo4TWw5cFpxaFNSeEklM0Q=","preload_interval":25,"preload_max_step":10,"preload_min_step":5,"preload_size":327680,"size":6836189,"socket_buffer":346514400,"user_video_proxy":1,"vheight":480,"vtype":"mp4","vwidth":854}
             * video_3 : {"backup_url_1":"aHR0cDovL3Y3LnBzdGF0cC5jb20vNzM4NTJiOTYxNDgyMzc0MDY2NWIyN2VkODZhNWVhMTEvNThkZGJlMjAvdmlkZW8vbS8yMjA0NTViMDUyMjFjYTg0MDAxOWFjZjkwZDNmZGFmZTNhZDExNDQ4ZGMwMDAwMjRkOTFkNmZkNzZhLw==","bitrate":1274484,"definition":"720p","main_url":"aHR0cDovL3YzLjM2NXlnLmNvbS83Mzg1MmI5NjE0ODIzNzQwNjY1YjI3ZWQ4NmE1ZWExMS81OGRkYmUyMC92aWRlby9tLzIyMDQ1NWIwNTIyMWNhODQwMDE5YWNmOTBkM2ZkYWZlM2FkMTE0NDhkYzAwMDAyNGQ5MWQ2ZmQ3NmEv","preload_interval":25,"preload_max_step":10,"preload_min_step":5,"preload_size":327680,"size":14223659,"socket_buffer":764690400,"user_video_proxy":1,"vheight":720,"vtype":"mp4","vwidth":1280}
             */

            var video_1: Video1Bean? = null
            var video_2: Video2Bean? = null
            var video_3: Video3Bean? = null

            class Video1Bean {
                /**
                 * backup_url_1 : aHR0cDovL3Y3LnBzdGF0cC5jb20vNWE2YzA4ZmJjZWQxZmIzY2ZhMDdkZGE3Zjg0ZTNkZDUvNThkZGJlMjAvdmlkZW8vbS8xMTQ0YzNiMDAwMDBjMWU1MWIwMWQ1ZjIyMGE0MzMyMTUyNGQwOTQ0MjU5MDI5NmU1NDI3Yjc5NGNlLw==
                 * bitrate : 369405
                 * definition : 360p
                 * main_url : aHR0cDovL3Y2LjM2NXlnLmNvbS92aWRlby9tLzExNDRjM2IwMDAwMGMxZTUxYjAxZDVmMjIwYTQzMzIxNTI0ZDA5NDQyNTkwMjk2ZTU0MjdiNzk0Y2UvP0V4cGlyZXM9MTQ5MDkzMDczNiZBV1NBY2Nlc3NLZXlJZD1xaDBoOVRkY0VNb1Myb1BqN2FLWCZTaWduYXR1cmU9WWNmakZDNnMxSHFhQ0NxeVZMd3ZkRWNlcXg0JTNE
                 * preload_interval : 25
                 * preload_max_step : 10
                 * preload_min_step : 5
                 * preload_size : 327680
                 * size : 4630326
                 * socket_buffer : 221643000
                 * user_video_proxy : 1
                 * vheight : 360
                 * vtype : mp4
                 * vwidth : 640
                 */

                var backup_url_1: String? = null
                var bitrate: Int = 0
                var definition: String? = null
                var main_url: String? = null
                var preload_interval: Int = 0
                var preload_max_step: Int = 0
                var preload_min_step: Int = 0
                var preload_size: Int = 0
                var size: Int = 0
                var socket_buffer: Int = 0
                var user_video_proxy: Int = 0
                var vheight: Int = 0
                var vtype: String? = null
                var vwidth: Int = 0
            }

            class Video2Bean {
                /**
                 * backup_url_1 : aHR0cDovL3Y3LnBzdGF0cC5jb20vNTk0ZTZjNDZjMjliZDkxY2EwZWJhMTFkM2RlMGIxN2EvNThkZGJlMjAvdmlkZW8vbS8yMjBlNmU2MjY3Mjg4NDU0YzkwOTFjOGYyMTZlZThiOWEwMjExNDRiZjAwMDAwMDZmNTE3YjkxMTk1Lw==
                 * bitrate : 577524
                 * definition : 480p
                 * main_url : aHR0cDovL3Y2LjM2NXlnLmNvbS92aWRlby9tLzIyMGU2ZTYyNjcyODg0NTRjOTA5MWM4ZjIxNmVlOGI5YTAyMTE0NGJmMDAwMDAwNmY1MTdiOTExOTUvP0V4cGlyZXM9MTQ5MDkzMDczNiZBV1NBY2Nlc3NLZXlJZD1xaDBoOVRkY0VNb1Myb1BqN2FLWCZTaWduYXR1cmU9JTJGbEtPMld4QVVtcUtqUGo4TWw5cFpxaFNSeEklM0Q=
                 * preload_interval : 25
                 * preload_max_step : 10
                 * preload_min_step : 5
                 * preload_size : 327680
                 * size : 6836189
                 * socket_buffer : 346514400
                 * user_video_proxy : 1
                 * vheight : 480
                 * vtype : mp4
                 * vwidth : 854
                 */

                var backup_url_1: String? = null
                var bitrate: Int = 0
                var definition: String? = null
                var main_url: String? = null
                var preload_interval: Int = 0
                var preload_max_step: Int = 0
                var preload_min_step: Int = 0
                var preload_size: Int = 0
                var size: Int = 0
                var socket_buffer: Int = 0
                var user_video_proxy: Int = 0
                var vheight: Int = 0
                var vtype: String? = null
                var vwidth: Int = 0
            }

            class Video3Bean {
                /**
                 * backup_url_1 : aHR0cDovL3Y3LnBzdGF0cC5jb20vNzM4NTJiOTYxNDgyMzc0MDY2NWIyN2VkODZhNWVhMTEvNThkZGJlMjAvdmlkZW8vbS8yMjA0NTViMDUyMjFjYTg0MDAxOWFjZjkwZDNmZGFmZTNhZDExNDQ4ZGMwMDAwMjRkOTFkNmZkNzZhLw==
                 * bitrate : 1274484
                 * definition : 720p
                 * main_url : aHR0cDovL3YzLjM2NXlnLmNvbS83Mzg1MmI5NjE0ODIzNzQwNjY1YjI3ZWQ4NmE1ZWExMS81OGRkYmUyMC92aWRlby9tLzIyMDQ1NWIwNTIyMWNhODQwMDE5YWNmOTBkM2ZkYWZlM2FkMTE0NDhkYzAwMDAyNGQ5MWQ2ZmQ3NmEv
                 * preload_interval : 25
                 * preload_max_step : 10
                 * preload_min_step : 5
                 * preload_size : 327680
                 * size : 14223659
                 * socket_buffer : 764690400
                 * user_video_proxy : 1
                 * vheight : 720
                 * vtype : mp4
                 * vwidth : 1280
                 */

                var backup_url_1: String? = null
                var bitrate: Int = 0
                var definition: String? = null
                var main_url: String? = null
                var preload_interval: Int = 0
                var preload_max_step: Int = 0
                var preload_min_step: Int = 0
                var preload_size: Int = 0
                var size: Int = 0
                var socket_buffer: Int = 0
                var user_video_proxy: Int = 0
                var vheight: Int = 0
                var vtype: String? = null
                var vwidth: Int = 0
            }
        }

        class BigThumbsBean {
            /**
             * img_num : 16
             * img_url : https://p1.pstatp.com/origin/19cc000499f3a6986d59
             * img_x_size : 160
             * img_y_size : 90
             * img_x_len : 1
             * img_y_len : 16
             */

            var img_num: Int = 0
            var img_url: String? = null
            var img_x_size: Int = 0
            var img_y_size: Int = 0
            var img_x_len: Int = 0
            var img_y_len: Int = 0
        }
    }
}
