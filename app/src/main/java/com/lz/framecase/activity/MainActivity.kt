package com.lz.framecase.activity


import android.app.ActivityManager
import android.app.ActivityOptions
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v4.widget.DrawerLayout
import android.view.View
import android.view.WindowManager
import com.gyf.barlibrary.ImmersionBar
import com.jakewharton.rxbinding2.support.design.widget.RxNavigationView
import com.jakewharton.rxbinding2.view.RxView
import com.lz.framecase.R
import com.lz.framecase.R.id.iv_search
import com.lz.framecase.base.BaseActivity
import com.lz.framecase.fragment.ImagePagerFragment
import com.lz.framecase.fragment.NewsPagerFragment
import com.lz.framecase.fragment.NewsTitlePagerFragment
import com.lz.framecase.fragment.VideoPagerFragment
import com.lz.framecase.presenter.Main2Contract
import com.lz.framecase.presenter.Main2Presenter
import com.zhy.changeskin.SkinManager
import com.zhy.changeskin.utils.PrefUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.activity_main2.*
import java.util.concurrent.TimeUnit

/**
 * -------- 日期 ---------- 维护人 ------------ 变更内容 --------
 */
class MainActivity : BaseActivity<Main2Presenter>(), Main2Contract.View {

    override fun getLayout(): Int {
        return R.layout.activity_main2
    }


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate() {
        initFragment()
        initlIstener()

    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun initlIstener() {
        RxView.clicks(iv_search)
                .debounce(500, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(Consumer {
                    val intent = Intent(mActivity, SearchActivity::class.java)

                    startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(mActivity, iv_search, "SearchView").toBundle())

                })

        RxNavigationView.itemSelections(nav_view).debounce(500, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(Consumer {
                    when (it.itemId) {
                        R.id.nav_switch_night_mode -> {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                if (mActivity.getWindow().navigationBarColor == Color.BLUE) {
                                    mActivity.getWindow().setNavigationBarColor(Color.BLACK)
                                } else {
                                    mActivity.getWindow().setNavigationBarColor(Color.BLUE)
                                }

                            }
                            drawerlayout.closeDrawers()

                        }
                        R.id.nav_setting -> {
                            val suffix = PrefUtils(mActivity).suffix
                            SkinManager.getInstance().changeSkin(if (suffix.equals("red")) "blue" else "red")
                            ImmersionBar.with(this)
                                    //同时自定义状态栏和导航栏颜色，不写默认状态栏为透明色，导航栏为黑色
                                    .barColor(if (suffix === "blue") R.color.app_them_red else R.color.app_them_blue)
                                    //状态栏和导航栏变色后的颜色
                                    //  .barColorTransform(R.color.orange)
                                    //必须调用方可沉浸式
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


}
