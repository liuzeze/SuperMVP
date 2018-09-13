package com.lz.framecase.fragment.presenter

import com.lz.fram.base.BasePresenter
import com.lz.fram.base.BaseView
import com.lz.framecase.bean.NewsDataBean
import java.util.ArrayList

/**
 * -------- ���� ---------- ά���� ------------ ������� --------
 */
interface NewsListContract {


    interface View : BaseView {
        fun getNewsListSuccess(bean: ArrayList<NewsDataBean>, has_more_to_refresh: Boolean)
    }

    interface Presenter : BasePresenter{
        //��¼
        fun getNewLists(category: String?)
    }


}
