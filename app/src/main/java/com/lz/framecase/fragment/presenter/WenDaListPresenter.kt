package com.lz.framecase.fragment.presenter


import android.text.TextUtils
import com.google.gson.Gson
import com.lz.fram.base.RxPresenter
import com.lz.fram.observer.CommonSubscriber
import com.lz.framecase.api.RequestApi
import com.lz.framecase.bean.MultNewsBean
import com.lz.framecase.bean.WendaArticleBean
import com.lz.framecase.bean.WendaArticleDataBean
import com.vondear.rxtool.RxTimeTool
import com.vondear.rxtool.view.RxToast
import java.util.ArrayList
import javax.inject.Inject

/**
 * -------- 日期 ---------- 维护人 ------------ 变更内容 --------
 */
class WenDaListPresenter @Inject
constructor(var mRequestApi: RequestApi)
    : RxPresenter<WenDaListContract.View>(), WenDaListContract.Presenter {
    var time: String = (RxTimeTool.getCurTimeMills() / 1000).toString()
    val dataList = ArrayList<WendaArticleDataBean>()

    /**
     * 登录
     */
    override fun getWenDaList() {
        val gson = Gson()
        val subscriber = object : CommonSubscriber<WendaArticleBean>(mBaseView) {
            override fun onNext(bean: WendaArticleBean) {
                val list = ArrayList<WendaArticleDataBean>()
                for (i in bean.data!!.indices) {
                    val contentBean = gson.fromJson(bean!!.data!!.get(i).content, WendaArticleDataBean::class.java)
                    if (TextUtils.isEmpty(contentBean.question)) {
                        continue
                    }

                    val extraBean = gson.fromJson(contentBean.extra, WendaArticleDataBean.ExtraBean::class.java)
                    val questionBean = gson.fromJson(contentBean.question, WendaArticleDataBean.QuestionBean::class.java)
                    val answerBean = gson.fromJson(contentBean.answer, WendaArticleDataBean.AnswerBean::class.java)
                    contentBean.extraBean = (extraBean)
                    contentBean.questionBean = (questionBean)
                    contentBean.answerBean = (answerBean)
                    time = contentBean.behot_time!!

                    if (null != contentBean!!.extraBean!!.wenda_image &&
                            null != contentBean.extraBean!!.wenda_image!!.three_image_list &&
                            contentBean.extraBean!!.wenda_image!!.three_image_list!!.size > 0) {
                        contentBean.itemType = WendaArticleDataBean.WENDAIMAGES
                    } else if (null != contentBean!!.extraBean!!.wenda_image &&
                            null != contentBean.extraBean!!.wenda_image!!.large_image_list &&
                            contentBean.extraBean!!.wenda_image!!.large_image_list!!.size > 0) {
                        contentBean.itemType = WendaArticleDataBean.WENDAIMG
                    } else {
                        contentBean.itemType = WendaArticleDataBean.WENDATEXT
                    }

                    var isHave = false
                    for (bean in dataList) {
                        if (contentBean.questionBean!!.title.equals(bean.questionBean!!.title)) {
                            isHave = true
                        }
                    }
                    if (!isHave) {
                        list.add(contentBean)
                        dataList.add(contentBean)
                    }

                }

                mBaseView.getWenDaListSuccess(dataList)
            }
        }
        mRequestApi.getWenDaLists(time)?.`as`(bindLifecycle())?.subscribeWith(subscriber)

    }
}