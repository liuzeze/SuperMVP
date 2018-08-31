package com.lz.framecase.activity.adapter

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.support.design.widget.Snackbar
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.lz.framecase.R
import com.lz.framecase.base.BaseActivity
import com.lz.framecase.bean.NewsCommentBean

/**
 * -----------作者----------日期----------变更内容-----
 * -          刘泽      2018-08-31       创建class
 */
class NewsCommentAdapter(data: List<NewsCommentBean.DataBean>?)
    : BaseQuickAdapter<NewsCommentBean.DataBean, BaseViewHolder>(R.layout.item_comment, data) {

    override fun convert(helper: BaseViewHolder, item: NewsCommentBean.DataBean) {
        try {
            val iv_avatar = item.comment?.user_profile_image_url
            val tv_username = item.comment?.user_name
            val tv_text = item.comment?.text
            val tv_likes = item.comment?.digg_count

            Glide.with(mContext).load(iv_avatar).into(helper.getView(R.id.iv_avatar))
            helper.setText(R.id.tv_username,tv_username)
            helper.setText(R.id.tv_text,tv_text)
            helper.setText(R.id.tv_likes,tv_likes.toString() + "赞")
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}
