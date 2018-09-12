package com.lz.framecase.activity

import android.content.Intent
import android.databinding.ViewDataBinding
import android.widget.ImageView
import cn.bingoogolapple.bgabanner.BGABanner
import cn.bingoogolapple.bgabanner.BGALocalImageSize
import com.lz.framecase.R
import com.lz.framecase.base.BaseActivity
import kotlinx.android.synthetic.main.activity_splash.*


class SplashActivity : BaseActivity<ViewDataBinding>() {
    override fun getLayout(): Int {
        return R.layout.activity_splash
    }

    override fun initViewData() {

        val localImageSize = BGALocalImageSize(720, 1280, 320f, 640f)
        bgbanner.setData(localImageSize, ImageView.ScaleType.CENTER_CROP,
                R.mipmap.main_bg,
                R.mipmap.main_bg)
        bgbanner.setEnterSkipViewIdAndDelegate(R.id.tv_enter, R.id.tv_skip, BGABanner.GuideDelegate {

            startActivity(Intent(mActivity, MainActivity::class.java))
            finish()
        })

    }
}
