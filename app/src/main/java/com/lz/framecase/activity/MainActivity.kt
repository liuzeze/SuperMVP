package com.lz.framecase.activity


import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.AppCompatDelegate
import android.view.Gravity
import android.view.View
import android.widget.Toast
import com.gyf.barlibrary.ImmersionBar
import com.jakewharton.rxbinding2.support.design.widget.RxNavigationView
import com.jakewharton.rxbinding2.view.RxView
import com.lz.framecase.R
import com.lz.framecase.R.id.drawerlayout
import com.lz.framecase.R.id.nav_view


import com.lz.framecase.base.BaseActivity
import com.lz.framecase.databinding.ActivityMain2Binding
import com.lz.framecase.fragment.ImagePagerFragment
import com.lz.framecase.fragment.NewsPagerFragment
import com.lz.framecase.fragment.NewsTitlePagerFragment
import com.lz.framecase.fragment.VideoPagerFragment
import com.lz.framecase.logic.MyApplication
import com.lz.framecase.utils.SettingUtils
import com.lz.skinlibs.SkinManager
import com.lz.skinlibs.utils.PrefUtils
import com.lz.utilslib.interceptor.utils.SnackbarUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.activity_main2.*
import java.util.concurrent.TimeUnit

/**
 * -------- 日期 ---------- 维护人 ------------ 变更内容 --------
 */
class MainActivity : BaseActivity<ActivityMain2Binding>() {

    override fun getLayout(): Int {
        return R.layout.activity_main2
    }


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun initViewData() {
        initFragment()
        initlIstener()
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun initlIstener() {
        RxView.clicks(mBind.ivSearch)
                .debounce(500, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(Consumer {
                    val intent = Intent(mActivity, FaceActivity::class.java)
                    startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(mActivity, mBind.ivSearch, "SearchView").toBundle())
                    Toast.makeText(MyApplication.mApplication, "修复成功", Toast.LENGTH_SHORT).show()
                })
        RxView.clicks(mBind.ivTitle)
                .debounce(500, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(Consumer {
                    drawerlayout.openDrawer(Gravity.LEFT)

                })

        RxNavigationView.itemSelections(nav_view).debounce(500, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(Consumer {
                    when (it.itemId) {
                        R.id.nav_switch_night_mode -> {
                            val isNight = !SettingUtils.getSlideBackMode()
                            SettingUtils.saveSlideBackMode(isNight)
                            SnackbarUtils.show(nav_view, (if (isNight) "开启" else "关闭") + "滑动返回")
                            drawerlayout.closeDrawers()

                        }
                        R.id.nav_setting -> {
                            val suffix = PrefUtils(mActivity).suffix
                            SkinManager.getInstance().changeSkin(if (suffix.equals("red")) "blue" else "red")
                            ImmersionBar.with(this)
                                    .barColor(SkinManager.getInstance().themColor)
                                    .init()
                            drawerlayout.closeDrawers()
                        }
                        R.id.nav_share -> {
                            val shareIntent = Intent()
                                    .setAction(Intent.ACTION_SEND)
                                    .setType("text/plain")
                                    .putExtra(Intent.EXTRA_TEXT, "我要分享这个应用")
                            startActivity(Intent.createChooser(shareIntent, "分享"))
                            drawerlayout.closeDrawers()
                        }
                        R.id.nav_setting2 -> {
                            if (SettingUtils.getNightMode()) {
                                SettingUtils.saveNightMode(false)
                                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                            } else {
                                SettingUtils.saveNightMode(true)
                                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                            }
                            recreate()
                        }
                        else -> {
                        }
                    }
                })


        drawerlayout.addDrawerListener(object : DrawerLayout.DrawerListener {
            override fun onDrawerStateChanged(newState: Int) {}

            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                val mContent = drawerlayout.getChildAt(0)
                val scale = 1 - slideOffset
                if (drawerView.tag == "LEFT") {

                    drawerView.scaleX = slideOffset
                    drawerView.scaleY = slideOffset
                    drawerView.alpha = slideOffset

                    mContent.pivotX = 0F;
                    mContent.pivotY = mContent.getMeasuredHeight() / 2f
                    mContent.invalidate()
                    mContent.scaleY = if (scale > 0.8) scale else 0.8f
                    mContent.scaleX = if (scale > 0.8) scale else 0.8f
                    mContent.translationX = drawerView.measuredWidth * 0.8f * slideOffset

                }
            }

            override fun onDrawerOpened(drawerView: View) {}

            override fun onDrawerClosed(drawerView: View) {}
        })
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


    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
    }
}
