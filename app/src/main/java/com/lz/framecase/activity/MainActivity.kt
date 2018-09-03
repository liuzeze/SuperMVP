package com.lz.framecase.activity


import com.lz.framecase.R
import com.lz.framecase.base.BaseActivity
import com.lz.framecase.fragment.ImagePagerFragment
import com.lz.framecase.fragment.NewsPagerFragment
import com.lz.framecase.fragment.NewsTitlePagerFragment
import com.lz.framecase.fragment.VideoPagerFragment
import com.lz.framecase.presenter.Main2Contract
import com.lz.framecase.presenter.Main2Presenter
import kotlinx.android.synthetic.main.activity_main2.*

/**
 * -------- 日期 ---------- 维护人 ------------ 变更内容 --------
 */
class MainActivity : BaseActivity<Main2Presenter>(), Main2Contract.View {

    override fun getLayout(): Int {
        return R.layout.activity_main2
    }


    override fun onCreate() {
        initFragment()

    }

    private fun initFragment() {
        val newsPagerFragment = NewsPagerFragment.getInstance()
        val imagePagerFragment = ImagePagerFragment.getInstance()
        val videoPagerFragment = VideoPagerFragment.getInstance()
        val newsTitlePagerFragment = NewsTitlePagerFragment.getInstance()
        loadMultipleRootFragment(R.id.container, 0, newsPagerFragment,
                imagePagerFragment, videoPagerFragment, newsTitlePagerFragment
        )
    }


}
