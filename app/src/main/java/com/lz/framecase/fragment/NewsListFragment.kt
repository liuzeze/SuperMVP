package com.lz.framecase.fragment

import android.app.ActivityOptions
import android.content.Intent
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.Keep
import android.support.v7.widget.PopupMenu
import android.text.TextUtils
import android.view.Gravity
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseQuickAdapter.SLIDEIN_BOTTOM
import com.chad.library.adapter.base.BaseViewHolder
import com.google.gson.Gson
import com.lz.fram.scope.AttachView
import com.lz.fram.scope.CallBackAnnotion
import com.lz.framecase.R
import com.lz.framecase.activity.NewDetailActivity
import com.lz.framecase.activity.VideoPlayerActivity
import com.lz.framecase.base.BaseFragment
import com.lz.framecase.bean.NewsDataBean
import com.lz.framecase.fragment.adapter.NewsListAdapter
import com.lz.framecase.fragment.presenter.NewsListContract
import com.lz.framecase.fragment.presenter.NewsListPresenter
import com.lz.framecase.logic.MyApplication
import com.lz.inject_annotation.InjectFragment
import com.lz.utilslib.interceptor.utils.ShareAction
import kotlinx.android.synthetic.main.fragment_news_list.*
import java.util.*
import javax.inject.Inject
import kotlin.collections.HashSet

/**
 * -----------作者----------日期----------变更内容-----
 * -          刘泽      2018-08-29       创建class
 */
@InjectFragment
class NewsListFragment : BaseFragment<ViewDataBinding>(), NewsListContract.View {
    @AttachView
    @Inject
    lateinit var mPresenter: NewsListPresenter

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

    override fun initViewData() {
        setSwipeBackEnable(false) // 是否允许滑动
        gson = Gson()
        category = arguments?.getString("category")!!
        newsListAdapter = NewsListAdapter(mNewsBean)
        newsListAdapter?.openLoadAnimation(SLIDEIN_BOTTOM)
        recyclerView.adapter = newsListAdapter
        SwipeRefreshLayout.setRefreshing(true)
        mPresenter?.getNewLists(category)
    }

    override fun initLisenter() {
        super.initLisenter()
        SwipeRefreshLayout.setOnRefreshListener {

            mPresenter?.getNewLists(category)
        }

        newsListAdapter?.setOnItemChildClickListener { baseQuickAdapter: BaseQuickAdapter<Any, BaseViewHolder>, view: View, position: Int ->
            when (view.id) {
                R.id.tv_dots -> {
                    val popupMenu = PopupMenu(mContext,
                            view, Gravity.END)
                    popupMenu.inflate(R.menu.menu_share)
                    popupMenu.setOnMenuItemClickListener { menu ->
                        val itemId = menu.itemId
                        if (itemId == R.id.action_share) {
                            ShareAction.send(mContext, mNewsBean.get(position).title + "\n" + mNewsBean.get(position).share_url)
                        }
                        false
                    }
                    popupMenu.show()
                }
                else -> {
                }
            }
        }
        newsListAdapter?.setOnLoadMoreListener({
            mPresenter?.getNewLists(category)
        }, recyclerView)

        newsListAdapter?.setOnItemClickListener { adapter, view, position ->

            val item = mNewsBean.get(position)
            // view.transitionName = "SearchView"
            var intent: Intent? = null
            if (item.itemType === NewsDataBean.NEWSTEXT) {
                intent = Intent(MyApplication.mApplication, NewDetailActivity::class.java)
                intent?.putExtra(NewDetailActivity.TAG, item)
            }
            if (item.itemType === NewsDataBean.NEWSIMG) {
                var imgUrl = "http://p3.pstatp.com/"
                val image_list = item?.image_list
                if (image_list != null && image_list!!.size != 0) {
                    if (!TextUtils.isEmpty(image_list!!.get(0).uri)) {
                        imgUrl += image_list!!.get(0).uri?.replace("list", "large")
                    }
                }
                intent = Intent(MyApplication.mApplication, NewDetailActivity::class.java)
                intent?.putExtra(NewDetailActivity.TAG, item)
                intent?.putExtra(NewDetailActivity.IMG, imgUrl)
            }
            if (item.itemType === NewsDataBean.NEWSVIDEO) {
                intent = Intent(MyApplication.mApplication, VideoPlayerActivity::class.java)
                intent?.putExtra(NewDetailActivity.TAG, item)

            }
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(activity, view, "SearchView").toBundle())

        }
    }

    @CallBackAnnotion("getNewsListSuccess")
    override fun getNewsListSuccess(bean: java.util.ArrayList<NewsDataBean>, has_more_to_refresh: Boolean) {
        /*if (!has_more_to_refresh) {
            //数据全部加载完毕
            newsListAdapter?.loadMoreEnd()
        } else {
            SwipeRefreshLayout.setRefreshing(false)
            mNewsBean.addAll(bean)
            newsListAdapter?.notifyItemRangeChanged(newsListAdapter!!.itemCount, bean.size)
            //成功获取更多数据
            newsListAdapter?.loadMoreComplete();

        }*/
        SwipeRefreshLayout.setRefreshing(false)
        mNewsBean.addAll(bean)
        val set = HashSet<NewsDataBean>(mNewsBean)
        mNewsBean.clear()
        mNewsBean.addAll(set)
        newsListAdapter?.setNewData(mNewsBean)
        //成功获取更多数据
        newsListAdapter?.loadMoreComplete()


    }


    override fun showErrorMsg(msg: String?) {
        super.showErrorMsg(msg)
        SwipeRefreshLayout.setRefreshing(false)
        newsListAdapter?.loadMoreFail()
    }
}
