package com.lz.framecase.fragment

import android.content.Intent
import android.support.v4.app.Fragment
import android.text.TextUtils
import android.view.View
import com.lz.fram.base.BasePresenter
import com.lz.framecase.R
import com.lz.framecase.activity.CatgoryActivity
import com.lz.framecase.base.BaseFragment
import com.lz.framecase.fragment.adapter.NewsPagerAdapter
import com.vondear.rxtool.RxSPTool
import kotlinx.android.synthetic.main.fragment_news_pager.*


/**
 * -------- ���� ---------- ά���� ------------ ������� --------
 */
class NewsPagerFragment : BaseFragment<BasePresenter<*>>() {

    companion object {
        fun getInstance(): NewsPagerFragment {
            val newsPagerFragment = NewsPagerFragment()
            return newsPagerFragment
        }
    }

    override fun getLayout(): Int {
        return R.layout.fragment_news_pager
    }


    override fun init() {
        intView()
        initLIstener()
    }

    private fun initLIstener() {
        iv_catgory.setOnClickListener(View.OnClickListener {
            val intent = Intent(mContext, CatgoryActivity::class.java)
            this@NewsPagerFragment.startActivityForResult(intent, 1000)
        })
    }

    private fun intView() {

        val string = ArrayList<String>()
        val stringId = ArrayList<String>()
        val arrayList = ArrayList<Fragment>();
        val category = RxSPTool.getContent(mContext, CatgoryActivity.NEWSCATGORY)

        if (!TextUtils.isEmpty(category)) {

            val nameId = category.split("&")
            for (s in nameId) {
                val split = s.split("|")
                val s1 = split[0]
                val s2 = split[1]
                string.add(s1)
                stringId.add(s2)
            }
        } else {
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
        }

        for (s in stringId.indices) {
            if (TextUtils.isEmpty(category) && s == 6) {
                break
            }
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
        val newsPagerAdapter = NewsPagerAdapter(childFragmentManager, arrayList)
        newsPagerAdapter.setTitleList(string)
        news_viewpager.adapter = newsPagerAdapter
        TabLayout.setupWithViewPager(news_viewpager)

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1000) {
            intView()
        }
    }
}
