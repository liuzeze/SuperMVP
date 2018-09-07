package com.lz.framecase.activity

import com.lz.framecase.R
import com.lz.framecase.base.BaseActivity
import com.lz.framecase.bean.NewsDataBean
import com.lz.framecase.fragment.NewsDetailFragment
import com.lz.framecase.presenter.EmptyPresenter

class NewDetailActivity : BaseActivity<EmptyPresenter>() {


    companion object {
        public val TAG = "NewDetailActivity"
        public val IMG = "img"

    }

    override fun getLayout(): Int {
        return R.layout.container
    }

    override fun onCreate() {
        val intent = intent
        loadRootFragment(R.id.container,
                NewsDetailFragment.getInstance(intent.getSerializableExtra(TAG) as NewsDataBean, intent.getStringExtra(IMG)))


    }
}
