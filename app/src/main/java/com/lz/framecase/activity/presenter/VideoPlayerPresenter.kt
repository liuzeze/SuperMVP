package com.lz.framecase.presenter


import com.lz.fram.base.RxPresenter
import com.lz.fram.observer.CommonSubscriber
import com.lz.framecase.api.RequestApi
import java.util.*
import java.util.zip.CRC32
import javax.inject.Inject


/**
 * -------- 日期 ---------- 维护人 ------------ 变更内容 --------
 */
class VideoPlayerPresenter @Inject
constructor(internal var mRequestApi: RequestApi) : RxPresenter<VideoPlayerContract.View>(), VideoPlayerContract.Presenter {

    /**
     * 登录
     */
    override fun getVideoUrl(groupId: String) {
        val subscriber = object : CommonSubscriber<String>(mView) {
            override fun onNext(s: String) {
                mView.getVideoUrlSuccess(s)
            }
        }
        addSubscribe("getVideoUrl", mRequestApi.getVideoUrl(getVideoContentApi(groupId), subscriber))

    }

    private fun getVideoContentApi(videoid: String): String {
        val VIDEO_HOST = "http://ib.365yg.com"
        val VIDEO_URL = "/video/urls/v/1/toutiao/mp4/%s?r=%s"
        val r = Random()
        val s = String.format(VIDEO_URL, videoid, r)
        // 将/video/urls/v/1/toutiao/mp4/{videoid}?r={Math.random()} 进行crc32加密
        val crc32 = CRC32()
        crc32.update(s.toByteArray())
        val crcString = crc32.value.toString() + ""
        return "$VIDEO_HOST$s&s=$crcString"
    }
}