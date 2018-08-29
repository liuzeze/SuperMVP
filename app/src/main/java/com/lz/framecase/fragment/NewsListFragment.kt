package com.lz.framecase.fragment

import android.os.Bundle
import com.google.gson.Gson
import com.lz.framecase.R
import com.lz.framecase.base.BaseFragment
import com.lz.framecase.bean.DataEntity
import com.lz.framecase.bean.MultNewsBean
import com.lz.framecase.bean.NewsDataBean
import com.lz.framecase.fragment.adapter.NewsListAdapter
import com.lz.framecase.fragment.presenter.NewsListContract
import com.lz.framecase.fragment.presenter.NewsListPresenter
import com.lz.inject_annotation.InjectComponet
import kotlinx.android.synthetic.main.fragment_news_list.*

/**
 * -----------作者----------日期----------变更内容-----
 * -          刘泽      2018-08-29       创建class
 */
@InjectComponet
class NewsListFragment : BaseFragment<NewsListPresenter>(), NewsListContract.View {

    private var gson: Gson? = null
    private var category: String = ""
    private var mNewsBean = ArrayList<NewsDataBean>()
    private var newsListAdapter: NewsListAdapter? = null

    companion object {
        fun getInstance(s: String): NewsListFragment {
            val newsListFragment = NewsListFragment()
            val bundle = Bundle();
            bundle.putString("category", s)
            newsListFragment.arguments = bundle
            return newsListFragment
        }
    }

    override fun getLayout(): Int {
        return R.layout.fragment_news_list
    }

    override fun init() {
        initValue()
        initListener()


    }

    override fun onSupportVisible() {
        super.onSupportVisible()
        mPresenter.getNewLists(category)

    }

    private fun initListener() {
        SwipeRefreshLayout.setOnRefreshListener { mPresenter.getNewLists(category) }
    }

    private fun initValue() {
        gson = Gson()
        category = arguments?.getString("category")!!
        newsListAdapter = NewsListAdapter(mNewsBean)
        RecyclerView.adapter = newsListAdapter


    }

    override fun getNewsListSuccess(bean: MultNewsBean) {
        SwipeRefreshLayout.setRefreshing(false)
        val data = bean.data!!
        for (datum in data) {
            val element = gson?.fromJson(datum.content, NewsDataBean::class.java)


            if (element?.isHas_video!!) {
                element.itemType = NewsDataBean.NEWSVIDEO
            } else
                if (null != element.image_list && element.image_list!!.size > 0) {
                    element.itemType = NewsDataBean.NEWSIMG

                } else {
                    element.itemType = NewsDataBean.NEWSTEXT
                }

            element
            mNewsBean.add(element!!)
        }

        newsListAdapter?.notifyItemRangeChanged(newsListAdapter!!.itemCount, data.size)

    }
}
