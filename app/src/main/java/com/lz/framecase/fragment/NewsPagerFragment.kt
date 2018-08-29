package com.lz.framecase.fragment

import android.support.v4.app.Fragment
import com.lz.fram.base.BasePresenter
import com.lz.framecase.R
import com.lz.framecase.base.BaseFragment
import com.lz.framecase.fragment.adapter.NewsPagerAdapter
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
    }

    private fun intView() {

        val string = resources.getStringArray(R.array.mobile_news_name)
        val stringId = resources.getStringArray(R.array.mobile_news_id)
        val arrayList = ArrayList<Fragment>();
        for (s in stringId) {
            val instance = NewsListFragment.getInstance(s)
            arrayList.add(instance)
        }
        val newsPagerAdapter = NewsPagerAdapter(childFragmentManager, arrayList)
        newsPagerAdapter.setTitleList(string)
        news_viewpager.adapter = newsPagerAdapter
        TabLayout.setupWithViewPager(news_viewpager)
    }

}
