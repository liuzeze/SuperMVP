package com.lz.framecase.fragment.presenter

import com.lz.fram.base.BasePresenter
import com.lz.fram.base.BaseView
import com.lz.framecase.bean.WendaArticleDataBean

/**
 * -------- ���� ---------- ά���� ------------ ������� --------
 */
interface NewsDetailContract {


    interface View : BaseView {
        fun onSetWebView(r: String?, b: Boolean)
    }

    interface Presenter : BasePresenter<View> {

    }


}
