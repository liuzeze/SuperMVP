package com.lz.framecase.fragment

import com.lz.framecase.R
import com.lz.framecase.base.BaseFragment
import com.lz.framecase.presenter.EmptyPresenter


/**
 * -------- ���� ---------- ά���� ------------ ������� --------
 */
class ImagePagerFragment : BaseFragment<EmptyPresenter>() {

    companion object {
        fun getInstance(): ImagePagerFragment {
            val imagePagerFragment = ImagePagerFragment()
            return imagePagerFragment
        }
    }

    override fun getLayout(): Int {
        return R.layout.fragment_image_pager
    }


    override fun init() {
    }

}
