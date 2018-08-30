package com.lz.framecase.fragment.presenter

import com.lz.fram.base.BasePresenter
import com.lz.fram.base.BaseView
import com.lz.framecase.bean.WendaArticleDataBean

/**
 * -------- ���� ---------- ά���� ------------ ������� --------
 */
interface WenDaListContract {


    interface View : BaseView {
        fun getWenDaListSuccess(bean: List<WendaArticleDataBean>)
    }

    interface Presenter : BasePresenter<View> {
        //��¼
        fun getWenDaList()
    }


}
