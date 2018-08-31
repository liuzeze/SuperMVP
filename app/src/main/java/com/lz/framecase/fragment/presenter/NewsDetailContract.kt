package com.lz.framecase.fragment.presenter

import com.lz.fram.base.BasePresenter
import com.lz.fram.base.BaseView
import com.lz.framecase.bean.NewsDataBean
import com.lz.framecase.bean.WendaArticleDataBean
import java.util.ArrayList

/**
 * -------- ���� ---------- ά���� ------------ ������� --------
 */
interface NewsDetailContract {


    interface View : BaseView {
        fun onSetWebView(r: String?, b: Boolean)
        fun onJumpPreview(url: String, list: ArrayList<String>)
    }

    interface Presenter : BasePresenter<View> {
        fun loadUrl(dataBean: NewsDataBean)
    }


}
