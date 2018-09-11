package com.lz.framecase.activity

import android.databinding.ViewDataBinding
import android.text.TextUtils
import cn.jzvd.JZVideoPlayer
import cn.jzvd.JZVideoPlayerStandard
import com.bumptech.glide.Glide
import com.lz.fram.scope.AttachView
import com.lz.framecase.R
import com.lz.framecase.R.id.*
import com.lz.framecase.activity.adapter.NewsCommentAdapter
import com.lz.framecase.base.BaseActivity
import com.lz.framecase.bean.NewsCommentBean
import com.lz.framecase.bean.NewsDataBean
import com.lz.framecase.holder.VideoPlayerHead
import com.lz.framecase.presenter.NewsCommentContract
import com.lz.framecase.presenter.NewsCommentPresenter
import com.lz.framecase.presenter.VideoPlayerContract
import com.lz.framecase.presenter.VideoPlayerPresenter
import kotlinx.android.synthetic.main.activity_video_player.*
import javax.inject.Inject
import com.lz.inject_annotation.InjectActivity


@InjectActivity
class VideoPlayerActivity : BaseActivity<ViewDataBinding>(), VideoPlayerContract.View, NewsCommentContract.View {

    @AttachView
    @Inject
    lateinit var mPresenter2: NewsCommentPresenter

    @AttachView
    @Inject
    lateinit var mPresenter: VideoPlayerPresenter

    var newsDataBean: NewsDataBean? = null
    var commentList = ArrayList<NewsCommentBean.DataBean>()

    var newsCommentAdapter: NewsCommentAdapter? = null

    override fun getLayout(): Int {
        return R.layout.activity_video_player
    }

    override fun init() {
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
        mPresenter.getVideoUrl(newsDataBean?.video_id!!)


        initHeadView()
    }

    private fun initHeadView() {

        val videoPlayerHead = VideoPlayerHead(mActivity)
        videoPlayerHead.setData(newsDataBean)
        newsCommentAdapter?.addHeaderView(videoPlayerHead.holderView)
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
