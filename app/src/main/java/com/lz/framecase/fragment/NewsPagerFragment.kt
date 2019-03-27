package com.lz.framecase.fragment

import android.app.ActivityOptions
import android.content.Intent
import android.support.v4.app.Fragment
import com.jakewharton.rxbinding2.view.RxView
import com.lz.framecase.R
import com.lz.framecase.activity.CatgoryActivity
import com.lz.framecase.base.BaseFragment
import com.lz.framecase.fragment.adapter.NewsPagerAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.fragment_news_pager.*
import java.util.concurrent.TimeUnit


/**
 * -------- ���� ---------- ά���� ------------ ������� --------
 */
class NewsPagerFragment : BaseFragment() {
    var newsPagerAdapter: NewsPagerAdapter? = null

    companion object {
        fun getInstance(): NewsPagerFragment {
            val newsPagerFragment = NewsPagerFragment()
            return newsPagerFragment
        }
    }

    override fun getLayout(): Int {
        return R.layout.fragment_news_pager
    }


    override fun initViewData() {
        newsPagerAdapter = NewsPagerAdapter(childFragmentManager, arrayList)
        newsPagerAdapter?.setTitleList(string)
        news_viewpager.adapter = newsPagerAdapter
        TabLayout.setupWithViewPager(news_viewpager)
      //  news_viewpager.setPageTransformer(false, ZoomPageTransformer())
        intView()
        initLIstener()
    }

    private fun initLIstener() {
        RxView.clicks(iv_catgory).debounce(500, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(Consumer {
                    val intent = Intent(mContext, CatgoryActivity::class.java)
                    this@NewsPagerFragment.startActivityForResult(intent, 1000)


                })
    }

    val string = ArrayList<String>()
    val stringId = ArrayList<String>()
    val arrayList = ArrayList<Fragment>();
    private fun intView() {

        string.clear()
        stringId.clear()
        arrayList.clear()


            for (s in resources.getStringArray(R.array.mobile_news_name)) {
                string.add(s)
                if (string.size == 6) {
                    break
                }

            }
            for (s in resources.getStringArray(R.array.mobile_news_id)) {
                stringId.add(s)
                if (stringId.size == 6) {
                    break
                }

            }


        for (s in stringId.indices) {

            when (stringId.get(s)) {
                "question_and_answer" -> {
                    val instance = WenDaListFragment.getInstance(stringId.get(s))
                    arrayList.add(instance)
                }
                else -> {
                    val instance = NewsListFragment.getInstance(stringId.get(s))
                    arrayList.add(instance)
                }
            }
        }
        newsPagerAdapter?.notifyDataSetChanged()
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1000) {
            intView()
        }
    }
}
