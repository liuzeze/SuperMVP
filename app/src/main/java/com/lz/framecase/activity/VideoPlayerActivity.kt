package com.lz.framecase.activity

import android.text.TextUtils
import cn.jzvd.JZVideoPlayer
import cn.jzvd.JZVideoPlayerStandard
import com.bumptech.glide.Glide
import com.lz.fram.scope.AttachView
import com.lz.framecase.R
import com.lz.framecase.activity.adapter.NewsCommentAdapter
import com.lz.framecase.base.BaseActivity
import com.lz.framecase.bean.NewsCommentBean
import com.lz.framecase.bean.NewsDataBean
import com.lz.framecase.holder.VideoPlayerHead
import com.lz.framecase.presenter.NewsCommentContract
import com.lz.framecase.presenter.NewsCommentPresenter
import com.lz.framecase.presenter.VideoPlayerContract
import com.lz.framecase.presenter.VideoPlayerPresenter
import com.lz.inject_annotation.InjectActivity
import kotlinx.android.synthetic.main.activity_video_player.*


class VideoPlayerActivity : BaseActivity(), VideoPlayerContract.View, NewsCommentContract.View {

    @AttachView
    @JvmField
    internal var mPresenter2: NewsCommentPresenter? = null

    @AttachView
    @JvmField
    internal var mPresenter: VideoPlayerPresenter? = null

    @AttachView
    @JvmField
    internal var mUiPresenter: VideoPlayerHead? = null

    var newsDataBean: NewsDataBean? = null
    var commentList = ArrayList<NewsCommentBean.DataBean>()

    var newsCommentAdapter: NewsCommentAdapter? = null

    override fun getLayout(): Int {
        return R.layout.activity_video_player
    }

    override fun initViewData() {
        initData()

    }

    private fun initData() {
        newsDataBean = intent.getSerializableExtra(NewDetailActivity.TAG) as NewsDataBean

        if (null != newsDataBean?.video_detail_info) {
            if (null != newsDataBean?.video_detail_info?.detail_video_large_image) {
                val image = newsDataBean?.video_detail_info?.detail_video_large_image?.url
                if (!TextUtils.isEmpty(image)) {
                    Glide.with(mActivity).load(image).into(jc_video.thumbImageView)
                }
            }
        }

        newsCommentAdapter = NewsCommentAdapter(commentList)
        newsCommentAdapter?.openLoadAnimation()
        recycler_view.adapter = newsCommentAdapter
        refresh_layout.setOnRefreshListener {
            mPresenter2?.getNewCommentLists(newsDataBean?.group_id.toString(), 0)
        }


        mPresenter2?.getNewCommentLists(newsDataBean?.group_id.toString(), 0)
        mPresenter!!.getVideoUrl(newsDataBean?.video_id!!)
        initHeadView()

    }

    private fun initHeadView() {
        mUiPresenter!!.creatLayout()

        mUiPresenter!!.setData(newsDataBean)
        newsCommentAdapter?.addHeaderView(mUiPresenter!!.holderView)
    }

    override fun getNewsCommentSuccess(data: ArrayList<NewsCommentBean.DataBean>) {
        refresh_layout.setRefreshing(false)

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
        refresh_layout.setRefreshing(false)

    }

    override fun getVideoUrlSuccess(url: String) {
        jc_video.setUp(url, JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL, newsDataBean?.title)

    }

    override fun onBackPressedSupport() {
        if (JZVideoPlayer.backPress()) {
            return
        }
        super.onBackPressedSupport()
    }

    override fun onPause() {
        super.onPause()
        JZVideoPlayer.releaseAllVideos()
        JZVideoPlayer.setJzUserAction(null)
        JZVideoPlayer.releaseAllVideos()
    }
}
