package com.lz.framecase.activity


import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.animation.ValueAnimator.REVERSE
import android.content.Intent
import android.widget.ImageView
import cn.bingoogolapple.bgabanner.BGALocalImageSize
import com.lz.framecase.R
import com.lz.framecase.base.BaseActivity
import com.lz.utilslib.interceptor.utils.LzStatueBarUtils
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.activity_splash.*
import java.util.concurrent.TimeUnit


class SplashActivity : BaseActivity() {


    var subscribe: Disposable? = null
    override fun getLayout(): Int {
        return R.layout.activity_splash
    }

    override fun initViewData() {
        val ofFloat1 = PropertyValuesHolder.ofFloat("scaleY", 1.2f)
        val ofFloat2 = PropertyValuesHolder.ofFloat("scaleX", 1.2f)
        val animator = ObjectAnimator.ofPropertyValuesHolder(bgbanner, ofFloat1, ofFloat2)
        animator.setDuration(5000)
        animator.repeatMode = REVERSE
        animator.repeatCount = 1000
        animator.start()
//        bgbanner.animate().withLayer()  硬件加速
        subscribe = Observable
                .timer(5000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(Consumer {
                    if (!isDestroyed) {
                        startActivity(Intent(mActivity, MainActivity::class.java))
                        svgimage.stopAnimation()
                        lav_show.cancelAnimation()
                        finish()
                    }
                })
        val localImageSize = BGALocalImageSize(720, 1280, 320f, 640f)
        bgbanner.setData(localImageSize, ImageView.ScaleType.CENTER_CROP,
                R.mipmap.main_bg)
        bgbanner.setEnterSkipViewIdAndDelegate(R.id.tv_enter, R.id.tv_skip, {
            startActivity(Intent(mActivity, MainActivity::class.java))
            svgimage.stopAnimation()
            lav_show.cancelAnimation()
            finish()
        })

        LzStatueBarUtils.setSystemUIVisible(this, false)


/*
//lottie 动画
        LottieComposition.Factory.fromAssetFileName(this, "AndroidWave.json", OnCompositionLoadedListener {
            lav_show.setComposition(it!!);

        });*/


    }

    override fun onDestroy() {
        super.onDestroy()
        if (subscribe != null) {
            subscribe!!.dispose()
            subscribe = null
        }
    }
}
