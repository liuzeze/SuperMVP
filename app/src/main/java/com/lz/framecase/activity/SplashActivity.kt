package com.lz.framecase.activity

import android.animation.ObjectAnimator
import android.animation.ValueAnimator.RESTART
import android.animation.ValueAnimator.REVERSE
import android.content.Intent
import android.databinding.ViewDataBinding
import android.os.Build
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.ImageView
import cn.bingoogolapple.bgabanner.BGALocalImageSize
import com.lz.framecase.R


import com.lz.framecase.base.BaseActivity
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.activity_splash.*
import org.reactivestreams.Subscriber
import java.util.concurrent.TimeUnit
import android.view.View.SYSTEM_UI_FLAG_FULLSCREEN
import android.view.View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
import android.view.View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
import android.os.Build.VERSION.SDK_INT
import android.view.View
import com.lz.framecase.R.id.bgbanner
import com.lz.framecase.R.id.svgimage
import com.lz.utilslib.interceptor.utils.LzStatueBarUtils


class SplashActivity : BaseActivity<ViewDataBinding>() {


    var subscribe: Disposable? = null
    override fun getLayout(): Int {
        return R.layout.activity_splash
    }

    override fun initViewData() {
        val ofFloat = ObjectAnimator.ofFloat(bgbanner, "scaleY", 1f, 1.2f)
        ofFloat.setDuration(5000)
        ofFloat.repeatMode = REVERSE
        ofFloat.repeatCount = 1000
        ofFloat.start()
        val ofFloat2 = ObjectAnimator.ofFloat(bgbanner, "scaleX", 1f, 1.2f)
        ofFloat2.setDuration(5000)
        ofFloat2.repeatMode = REVERSE
        ofFloat2.repeatCount = 1000
        ofFloat2.start()

        window
        subscribe = Observable
                .timer(5000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(Consumer {
                    if (!isDestroyed) {
                        startActivity(Intent(mActivity, MainActivity::class.java))
                        svgimage.stopAnimation()
                        finish()
                    }
                })
        val localImageSize = BGALocalImageSize(720, 1280, 320f, 640f)
        bgbanner.setData(localImageSize, ImageView.ScaleType.CENTER_CROP,
                R.mipmap.main_bg)
        bgbanner.setEnterSkipViewIdAndDelegate(R.id.tv_enter, R.id.tv_skip, {
            startActivity(Intent(mActivity, MainActivity::class.java))
            svgimage.stopAnimation()
            finish()
        })

        LzStatueBarUtils.setSystemUIVisible(this, false)
    }

    override fun onDestroy() {
        super.onDestroy()
        if (subscribe != null) {
            subscribe!!.dispose()
            subscribe = null
        }
    }
}
