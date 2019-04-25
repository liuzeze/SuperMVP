package com.lz.framecase.activity

import android.view.View
import com.lz.fram.observer.CommonObserver
import com.lz.framecase.R
import com.lz.framecase.api.RequestApi
import com.lz.framecase.base.NewsBaseActivity
import com.lz.utilslib.interceptor.utils.ToastUtils
import com.vondear.rxtool.RxTimeTool
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : NewsBaseActivity() {
    @JvmField
    internal var mApi: RequestApi? = null

    override fun getLayout(): Int {
        return R.layout.activity_search
    }

    override fun initViewData() {
        mApi!!.getNewLists("", (RxTimeTool.getCurTimeMills() / 1000).toString())
                ?.`as`(bindLifecycle())
                ?.subscribeWith(object : CommonObserver<String>() {
                    override fun onNext(t: String) {
                        ToastUtils.show(t)
                    }

                })
        ibt_back.setOnClickListener(View.OnClickListener {
            onBackPressed()
        })
    }

}
