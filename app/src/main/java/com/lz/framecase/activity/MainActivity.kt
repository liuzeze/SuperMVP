package com.lz.framecase.activity


import android.app.ActivityManager
import android.app.ActivityOptions
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import android.support.annotation.RequiresApi
import android.view.View
import com.lz.framecase.R
import com.lz.framecase.base.BaseActivity
import com.lz.framecase.fragment.ImagePagerFragment
import com.lz.framecase.fragment.NewsPagerFragment
import com.lz.framecase.fragment.NewsTitlePagerFragment
import com.lz.framecase.fragment.VideoPagerFragment
import com.lz.framecase.presenter.Main2Contract
import com.lz.framecase.presenter.Main2Presenter
import kotlinx.android.synthetic.main.activity_main2.*

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
        iv_search.setOnClickListener(View.OnClickListener {
            val intent = Intent(mActivity, SearchActivity::class.java)

            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(mActivity, iv_search, "SearchView").toBundle())

        })
        nav_view.setNavigationItemSelectedListener {
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
            false
        }
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
