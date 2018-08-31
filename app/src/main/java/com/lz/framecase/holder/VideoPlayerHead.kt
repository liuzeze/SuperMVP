package com.lz.framecase.holder

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Context
import android.text.TextUtils
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView

import com.bumptech.glide.Glide
import com.lz.framecase.R
import com.lz.framecase.base.BaseUtils
import com.lz.framecase.bean.NewsDataBean
import com.lz.framecase.widget.CircleImageView
import com.lz.utilslib.interceptor.utils.ShareAction
import com.lz.utilslib.interceptor.utils.SnackbarUtils

import java.util.concurrent.TimeUnit

import butterknife.BindView
import butterknife.ButterKnife
import com.lz.fram.base.BasePresenter

/**
 * -----------作者----------日期----------变更内容-----
 * -          刘泽      2018-08-31       创建class
 */
class VideoPlayerHead(context: Context) : BaseUtils<BasePresenter<*>>(context) {
    @BindView(R.id.tv_title)
    @JvmField
    internal var mTvTitle: TextView? = null
    @BindView(R.id.iv_menu)
    @JvmField
    internal var mIvMenu: ImageView? = null
    @BindView(R.id.rl_title)
    @JvmField
    internal var mRlTitle: RelativeLayout? = null
    @BindView(R.id.tv_tv_video_duration_str)
    @JvmField
    internal var mTvTvVideoDurationStr: TextView? = null
    @BindView(R.id.tv_abstract)
    @JvmField
    internal var mTvAbstract: TextView? = null
    @BindView(R.id.ll_desc)
    @JvmField
    internal var mLlDesc: LinearLayout? = null
    @BindView(R.id.ll_share)
    @JvmField
    internal var mLlShare: LinearLayout? = null
    @BindView(R.id.ll_dl)
    @JvmField
    internal var mLlDl: LinearLayout? = null
    @BindView(R.id.iv_media_avatar_url)
    @JvmField
    internal var mIvMediaAvatarUrl: CircleImageView? = null
    @BindView(R.id.tv_extra)
    @JvmField
    internal var mTvExtra: TextView? = null
    @BindView(R.id.media_layout)
    @JvmField
    internal var mMediaLayout: LinearLayout? = null

    override fun initView(): View? {
        val inflate = View.inflate(mContext, R.layout.item_video_content_header, null)
        ButterKnife.bind(this, inflate)
        return inflate
    }

    fun setData(item: NewsDataBean?) {
        try {
            val media_avatar_url = item!!.media_info!!.avatar_url
            if (!TextUtils.isEmpty(media_avatar_url)) {
                Glide.with(mContext).load(media_avatar_url).into(mIvMediaAvatarUrl!!)
            }

            val title = item.title
            val abstractX = item.abstractX
            val source = item.source

            val video_duration = item.video_duration
            val min = (video_duration / 60).toString()
            var second = (video_duration % 10).toString()
            if (Integer.parseInt(second) < 10) {
                second = "0$second"
            }
            val tv_video_time = "$min:$second"
            val tv_comment_count = item.comment_count.toString() + ""
            val media_id = item.media_info!!.media_id

            mTvTitle!!.text = title
            mTvTvVideoDurationStr!!.text = "时长 " + tv_video_time + " | " + tv_comment_count + "评论"
            mTvAbstract!!.text = abstractX
            mTvExtra!!.text = source

            mRlTitle!!.setOnClickListener {
                if (mLlDesc!!.alpha == 1f) {
                    mLlDesc!!.animate()
                            .setDuration(200)
                            .alpha(0f)
                            .translationY(mLlDesc!!.measuredHeight.toFloat())
                            .setListener(object : AnimatorListenerAdapter() {
                                override fun onAnimationEnd(animation: Animator) {
                                    super.onAnimationEnd(animation)
                                    mLlDesc!!.visibility = View.GONE
                                    mIvMenu!!.animate().rotation(180f)
                                }
                            }).start()
                } else {
                    mLlDesc!!.animate()
                            .setDuration(200)
                            .alpha(1f)
                            .translationY(0f)
                            .setListener(object : AnimatorListenerAdapter() {
                                override fun onAnimationEnd(animation: Animator) {
                                    super.onAnimationEnd(animation)
                                    mLlDesc!!.visibility = View.VISIBLE
                                    mIvMenu!!.animate().rotation(180f)
                                }
                            }).start()
                }
            }

            val videoTitle = item.title
            val shareUrl = item.display_url
            mLlShare!!.setOnClickListener { ShareAction.send(mContext, videoTitle + "\n" + shareUrl) }

            mLlDl!!.setOnClickListener { v -> SnackbarUtils.show(v, "未开发") }

            mMediaLayout!!.setOnClickListener { v -> SnackbarUtils.show(v, "跳转主页未开发") }

        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}
