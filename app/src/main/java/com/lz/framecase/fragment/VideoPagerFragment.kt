package com.lz.framecase.fragment

import com.lz.fram.base.BasePresenter
import com.lz.framecase.R
import com.lz.framecase.base.BaseFragment
import com.lz.framecase.fragment.presenter.NewsListContract


/**
 * -------- ���� ---------- ά���� ------------ ������� --------
 */
class VideoPagerFragment : BaseFragment() {

    companion object {
        fun getInstance(): VideoPagerFragment {
            val videoPagerFragment = VideoPagerFragment()
            return videoPagerFragment
        }
    }

    override fun getLayout(): Int {
        return R.layout.fragment_video_pager
    }


    override fun init() {
    }

}
