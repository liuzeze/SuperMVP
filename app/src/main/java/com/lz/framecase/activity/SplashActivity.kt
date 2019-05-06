package com.lz.framecase.activity


import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.animation.ValueAnimator.REVERSE
import android.content.Intent
import android.support.v7.app.AppCompatDelegate
import android.widget.ImageView
import cn.bingoogolapple.bgabanner.BGALocalImageSize
import com.github.moduth.blockcanary.BlockCanary
import com.github.moduth.blockcanary.BlockCanaryContext
import com.lz.fram.net.RxRequestUtils
import com.lz.framecase.BuildConfig
import com.lz.framecase.R
import com.lz.framecase.base.NewsBaseActivity
import com.lz.framecase.logic.AttrChangeLisnter
import com.lz.framecase.logic.GlobalConfiguration
import com.lz.framecase.utils.SettingUtils
import com.lz.skinlibs.SkinManager
import com.lz.utilslib.interceptor.utils.LzAppUtils
import com.lz.utilslib.interceptor.utils.LzStatueBarUtils
import com.tencent.bugly.Bugly
import com.tencent.bugly.beta.Beta
import com.vondear.rxtool.RxTool
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.activity_splash.*
import lz.com.acatch.CatchHandler
import me.ele.uetool.UETool
import java.util.concurrent.TimeUnit


class SplashActivity : NewsBaseActivity() {


    var subscribe: Disposable? = null
    override fun getLayout(): Int {
        init()
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
        /*subscribe = Observable
                .timer(5000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(Consumer {
                    if (!isDestroyed) {
                        startActivity(Intent(mActivity, MainActivity::class.java))
                        svgimage.stopAnimation()
                        lav_show.cancelAnimation()
                        finish()
                    }
                })*/
        val localImageSize = BGALocalImageSize(720, 1280, 320f, 640f)
        bgbanner.setData(localImageSize, ImageView.ScaleType.CENTER_CROP,
                R.mipmap.main_bg, R.mipmap.main_bg)
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


    fun init() {
        //        Debug.startMethodTracing(Environment.getExternalStorageState() + "tracing");
        RxTool.init(this)
        //配置网络请求参数
        RxRequestUtils.initConfig(GlobalConfiguration())

        SkinManager.getInstance().init(this, AttrChangeLisnter())
        if (SettingUtils.getNightMode()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

        Beta.initDelay = (3 * 1000).toLong()
        Beta.canShowUpgradeActs.add(MainActivity::class.java)
        Bugly.init(this, BuildConfig.BUGGLY_APPID, true)
        if (BuildConfig.DEBUG) {
            CatchHandler.Builder(applicationContext)
                    .setUrl("钉钉机器人网址")
                    .build()
            if (LzAppUtils.isCurrentProcess()) {
                UETool.showUETMenu()
            }
            // GTRController.initConfig(this);
            BlockCanary.install(this, BlockCanaryContext()).start()
        }
        Beta.installTinker()
        //        Debug.stopMethodTracing();
    }


}
