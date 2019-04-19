package com.lz.framecase.activity

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.graphics.Color
import android.support.design.widget.Snackbar
import android.support.v7.widget.DividerItemDecoration
import android.transition.Explode
import android.view.View
import android.widget.LinearLayout.VERTICAL
import com.chad.library.adapter.base.BaseQuickAdapter
import com.lz.fram.scope.AttachView
import com.lz.framecase.R
import com.lz.framecase.activity.adapter.NewsCommentAdapter
import com.lz.framecase.base.BaseActivity
import com.lz.framecase.bean.NewsCommentBean
import com.lz.framecase.presenter.NewsCommentContract
import com.lz.framecase.presenter.NewsCommentPresenter
import com.lz.inject_annotation.InjectActivity
import com.lz.utilslib.interceptor.utils.LpDialogUtils
import com.lz.utilslib.interceptor.utils.ShareAction
import kotlinx.android.synthetic.main.activity_comment_list.*

/**
 * -----------作者----------日期----------变更内容-----
 * -          刘泽      2018-08-31       创建class
 */
class NewsCommentActivity : BaseActivity(), NewsCommentContract.View {

    @AttachView
    @JvmField
    internal var mPresenter: NewsCommentPresenter?=null


    var commentList = ArrayList<NewsCommentBean.DataBean>()
    var newsCommentAdapter: NewsCommentAdapter? = null
    var groupId = ""
    var itemId: Long = 0
    override fun getLayout(): Int {
        window.enterTransition = Explode()
        window.exitTransition = Explode()

        return R.layout.activity_comment_list
    }

    override fun initViewData() {
        initView()
        initData()
        initLisrtener()

    }


    var offset: Long = 0
    private fun initLisrtener() {

        swiperefreshlayout.setOnRefreshListener {
            commentList.clear()
            offset = 0;
            mPresenter!!.getNewCommentLists(groupId, offset)
        }
        /* newsCommentAdapter?.setOnLoadMoreListener(BaseQuickAdapter.RequestLoadMoreListener {
             // mPresenter.getNewCommentLists(groupId, itemId)
         }, comment_list)*/
        newsCommentAdapter?.onItemClickListener = BaseQuickAdapter.OnItemClickListener { adapter, view, position ->
            val text = commentList.get(position).comment?.text!!
            LpDialogUtils.alertDialog(mActivity, "提示", text,"复制", View.OnClickListener {
                val copy = mActivity.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                val clipData = ClipData.newPlainText("text", text)
                copy.primaryClip = clipData
                Snackbar.make(comment_list, "复制成功", Snackbar.LENGTH_SHORT).show()
            }, "分享", View.OnClickListener {
                ShareAction.send(mActivity, text)
            }, true)

        }

    }

    private fun initData() {
        groupId = intent.getStringExtra("group_id")
        itemId = intent.getLongExtra("item_id", 0)
        mPresenter!!.getNewCommentLists(groupId, 0)
    }

    private fun initView() {
        comment_toobar.title = "评论"
        comment_toobar.setTitleTextColor(Color.WHITE)
        comment_toobar.setNavigationIcon(R.drawable.ic_white_back)
        actionBar?.setDisplayHomeAsUpEnabled(true)

        comment_toobar.setNavigationOnClickListener {
            finish()
        }
        comment_list.addItemDecoration(DividerItemDecoration(mActivity, VERTICAL))
        newsCommentAdapter = NewsCommentAdapter(commentList)
        comment_list.adapter = newsCommentAdapter
        newsCommentAdapter?.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM)

    }

    override fun getNewsCommentSuccess(data: ArrayList<NewsCommentBean.DataBean>) {
        swiperefreshlayout.isRefreshing = false
        if (data.size == 0) {
            newsCommentAdapter?.loadMoreEnd()
            return
        }
        commentList.addAll(data)
        newsCommentAdapter?.setNewData(commentList)
        newsCommentAdapter?.loadMoreComplete()

    }

    override fun showErrorMsg(msg: String?) {
        super.showErrorMsg(msg)
        swiperefreshlayout.isRefreshing = false


    }
}
