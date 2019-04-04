package com.lz.framecase.fragment.presenter


import android.text.TextUtils
import com.google.gson.Gson
import com.lz.fram.base.RxPresenter
import com.lz.fram.observer.CommonSubscriber
import com.lz.framecase.api.RequestApi
import com.lz.framecase.bean.MultNewsBean
import com.lz.framecase.bean.NewsDataBean
import com.vondear.rxtool.RxTimeTool
import java.util.*

/**
 * -------- 日期 ---------- 维护人 ------------ 变更内容 --------
 */
class NewsListPresenter
    : RxPresenter<NewsListContract.View>(), NewsListContract.Presenter {
    val dataList = ArrayList<NewsDataBean>()
    var mRequestApi: RequestApi

    init {
        mRequestApi = RequestApi();
    }

    /**
     * 登录
     */
    override fun getNewLists(category: String?) {

        val gson = Gson()
        mRequestApi.getNewLists(category, (RxTimeTool.getCurTimeMills() / 1000).toString())
                .`as`(bindLifecycle())
                .subscribeWith(object : CommonSubscriber<String>(mBaseView) {
                    override fun onNext(beanstr: String) {

                        var bean=Gson().fromJson(beanstr,MultNewsBean::class.java)
                        val list = ArrayList<NewsDataBean>()
                        com.orhanobut.logger.Logger.e("呵呵大小" + list.size)

                        val data = bean.data!!
                        for (datum in data) {
                            try {
                                val element = gson?.fromJson(datum.content, NewsDataBean::class.java)


                                if (TextUtils.isEmpty(element?.source)) {
                                    continue
                                }
                                // 过滤头条问答新闻
                                if (element?.source!!.contains("头条问答")
                                        || element?.tag!!.contains("ad")
                                        || element?.source!!.contains("悟空问答")) {
                                    continue
                                }
                                // 过滤头条问答新闻
                                if (element?.read_count === 0 || TextUtils.isEmpty(element.media_name)) {
                                    val title = element?.title
                                    if (title?.lastIndexOf("？") == title!!.length - 1) {
                                        continue
                                    }
                                }



                                if (element?.has_video!!) {
                                    element.itemType = NewsDataBean.NEWSVIDEO
                                } else if (null != element.image_list && element.image_list!!.size > 0) {
                                    element.itemType = NewsDataBean.NEWSIMG
                                } else {
                                    element.itemType = NewsDataBean.NEWSTEXT
                                }

                                // 过滤重复新闻(与上次刷新的数据比较)
                                var isHave = false
                                for (bean in dataList) {
                                    if (bean.title.equals(element.title)) {
                                        isHave = true
                                        break
                                    }
                                }
                                if (!isHave) {
                                    list.add(0, element!!)
                                }
                            } catch (e: Exception) {
                                e.printStackTrace()
                            }
                            val l = System.currentTimeMillis()
//                            callBack("getNewsListSuccess", list, bean.isHas_more_to_refresh)
                              mBaseView.getNewsListSuccess(list, bean.isHas_more_to_refresh)
                            val l1 = System.currentTimeMillis()
                            println("刘泽====" + (l1 - l))
                        }
                    }
                })

    }

}