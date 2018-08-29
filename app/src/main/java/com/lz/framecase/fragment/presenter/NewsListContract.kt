package com.lz.framecase.fragment.presenter

import com.lz.fram.base.BasePresenter
import com.lz.fram.base.BaseView
import com.lz.framecase.bean.MultNewsBean

/**
 * -------- ���� ---------- ά���� ------------ ������� --------
 */
interface NewsListContract {


    interface View : BaseView {
        fun getNewsListSuccess(bean: MultNewsBean)
    }

    interface Presenter : BasePresenter<View> {
        //��¼
        fun getNewLists(category: String?)
    }


}
