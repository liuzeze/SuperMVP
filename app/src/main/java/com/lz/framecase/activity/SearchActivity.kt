package com.lz.framecase.activity

import com.lz.fram.base.BasePresenter
import com.lz.framecase.R
import com.lz.framecase.base.BaseActivity
import com.lz.framecase.presenter.EmptyPresenter

class SearchActivity : BaseActivity<EmptyPresenter>() {
    override fun getLayout(): Int {
        return R.layout.activity_search
    }

    override fun onCreate() {
    }

}
