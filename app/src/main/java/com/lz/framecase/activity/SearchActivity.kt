package com.lz.framecase.activity

import android.databinding.ViewDataBinding
import android.view.View
import com.lz.fram.observer.CommonSubscriber
import com.lz.framecase.R
import com.lz.framecase.api.RequestApi
import com.lz.framecase.base.BaseActivity
import com.lz.framecase.bean.MultNewsBean
import com.lz.inject_annotation.InjectActivity
import com.lz.utilslib.interceptor.utils.ToastUtils
import com.vondear.rxtool.RxTimeTool
import kotlinx.android.synthetic.main.activity_search.*
import javax.inject.Inject

@InjectActivity
class SearchActivity : BaseActivity<ViewDataBinding>() {
    @Inject
    lateinit var mApi: RequestApi

    override fun getLayout(): Int {
        return R.layout.activity_search
    }

    override fun initViewData() {
        mApi.getNewLists("", (RxTimeTool.getCurTimeMills() / 1000).toString())?.`as`(bindLifecycle())?.subscribeWith(object : CommonSubscriber<MultNewsBean>() {
            override fun onNext(t: MultNewsBean?) {
                ToastUtils.show(t.toString())
            }

        })
        ibt_back.setOnClickListener(View.OnClickListener {
            onBackPressed()
        })
    }

}
