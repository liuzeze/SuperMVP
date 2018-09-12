package com.lz.framecase.activity

import android.animation.ObjectAnimator
import android.animation.ValueAnimator.RESTART
import android.animation.ValueAnimator.REVERSE
import android.content.Intent
import android.databinding.ViewDataBinding
import android.widget.ImageView
import cn.bingoogolapple.bgabanner.BGALocalImageSize
import com.lz.framecase.R
import com.lz.framecase.R.id.*
import com.lz.framecase.base.BaseActivity
import kotlinx.android.synthetic.main.activity_splash.*


class SplashActivity : BaseActivity<ViewDataBinding>() {
    override fun getLayout(): Int {
        return R.layout.activity_splash
    }

    override fun initViewData() {
        val ofFloat = ObjectAnimator.ofFloat(root_layout, "scaleY",  1f, 1.2f)
        ofFloat.setDuration(5000)
        ofFloat.repeatMode = REVERSE
        ofFloat.repeatCount = 1000
        ofFloat.start()
        val ofFloat2 = ObjectAnimator.ofFloat(root_layout, "scaleX",  1f, 1.2f)
        ofFloat2.setDuration(5000)
        ofFloat2.repeatMode = REVERSE
        ofFloat2.repeatCount = 1000
        ofFloat2.start()



        val localImageSize = BGALocalImageSize(720, 1280, 320f, 640f)
        bgbanner.setData(localImageSize, ImageView.ScaleType.CENTER_CROP,
                R.mipmap.main_bg)
        bgbanner.setEnterSkipViewIdAndDelegate(R.id.tv_enter, R.id.tv_skip, {
            startActivity(Intent(mActivity, MainActivity::class.java))
            svgimage.stopAnimation()
            finish()
        })

    }
}
