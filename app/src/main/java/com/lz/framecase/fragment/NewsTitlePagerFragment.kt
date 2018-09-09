package com.lz.framecase.fragment

import com.lz.fram.base.BasePresenter
import com.lz.framecase.R
import com.lz.framecase.base.BaseFragment
import com.lz.framecase.fragment.presenter.NewsListContract


/**
 * -------- ���� ---------- ά���� ------------ ������� --------
 */
class NewsTitlePagerFragment : BaseFragment() {

    companion object {
        fun getInstance(): NewsTitlePagerFragment {
            val newsTitlePagerFragment = NewsTitlePagerFragment()
            return newsTitlePagerFragment
        }
    }

    override fun getLayout(): Int {
        return R.layout.fragment_news_title_pager
    }


    override fun init() {
    }

}
