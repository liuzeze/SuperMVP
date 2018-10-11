package com.lz.framecase.fragment

import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v7.widget.PopupMenu
import android.view.Gravity
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.google.gson.Gson
import com.lz.framecase.R
import com.lz.framecase.base.BaseFragment
import com.lz.framecase.bean.DataEntity
import com.lz.framecase.bean.MultNewsBean
import com.lz.framecase.bean.NewsDataBean
import com.lz.framecase.bean.WendaArticleDataBean
import com.lz.framecase.fragment.adapter.NewsListAdapter
import com.lz.framecase.fragment.adapter.WenDaListAdapter
import com.lz.framecase.fragment.presenter.NewsListContract
import com.lz.framecase.fragment.presenter.NewsListPresenter
import com.lz.framecase.fragment.presenter.WenDaListContract
import com.lz.framecase.fragment.presenter.WenDaListPresenter
import com.lz.framecase.presenter.NewsCommentPresenter
import com.lz.utilslib.interceptor.utils.ShareAction
import com.lz.utilslib.interceptor.utils.SnackbarUtils
import com.vondear.rxtool.RxTimeTool
import kotlinx.android.synthetic.main.fragment_news_list.*
import com.lz.inject_annotation.InjectFragment
import javax.inject.Inject
import com.lz.fram.scope.AttachView

/**
 * -----------作者----------日期----------变更内容-----
 * -          刘泽      2018-08-29       创建class
 */
@InjectFragment
class WenDaListFragment : BaseFragment<ViewDataBinding>(), WenDaListContract.View {


    @AttachView
    @Inject
    lateinit var mPresenter: WenDaListPresenter

    private var gson: Gson? = null
    private var category: String = ""
    private var mNewsBean = ArrayList<WendaArticleDataBean>()
    private var newsListAdapter: WenDaListAdapter? = null

    companion object {
        fun getInstance(s: String): WenDaListFragment {
            val newsListFragment = WenDaListFragment()
            val bundle = Bundle();
            bundle.putString("category", s)
            newsListFragment.arguments = bundle
            return newsListFragment
        }
    }

    override fun getLayout(): Int {
        return R.layout.fragment_news_list
    }

    override fun initViewData() {
        initValue()
        initListener()
        SwipeRefreshLayout.setRefreshing(true)

        mPresenter.getWenDaList()
    }

    private fun initListener() {
        SwipeRefreshLayout.setOnRefreshListener {
            mNewsBean.clear()
            mPresenter.dataList.clear()
            mPresenter.getWenDaList()
        }
        newsListAdapter?.setOnLoadMoreListener(BaseQuickAdapter.RequestLoadMoreListener {
            mPresenter.getWenDaList()
        })
        newsListAdapter?.setOnItemClickListener { adapter, view, position ->
            SnackbarUtils.show(view, "未开发")
        }
    }

    private fun initValue() {
        gson = Gson()
        category = arguments?.getString("category")!!
        newsListAdapter = WenDaListAdapter(mNewsBean)
        recyclerView.adapter = newsListAdapter


    }

    override fun getWenDaListSuccess(bean: List<WendaArticleDataBean>) {
        SwipeRefreshLayout.setRefreshing(false)
        mNewsBean.addAll(bean)
        val set = HashSet<WendaArticleDataBean>(mNewsBean)
        mNewsBean.clear()
        mNewsBean.addAll(set)
        newsListAdapter?.setNewData(mNewsBean)

    }
}
