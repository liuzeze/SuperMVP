package com.lz.framecase.fragment.adapter

import android.text.TextUtils
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.lz.framecase.R
import com.lz.framecase.bean.NewsDataBean
import com.lz.utilslib.interceptor.utils.TimeUtil

/**
 *-----------作者----------日期----------变更内容-----
 *-          刘泽      2018-08-29       创建class
 */
class NewsListAdapter(arrayList: ArrayList<NewsDataBean>)
    : BaseMultiItemQuickAdapter<NewsDataBean, BaseViewHolder>(arrayList) {
    init {
        addItemType(NewsDataBean.NEWSTEXT, R.layout.item_news_text)
        addItemType(NewsDataBean.NEWSIMG, R.layout.item_news_img)
        addItemType(NewsDataBean.NEWSVIDEO, R.layout.item_news_video)
    }

    override fun convert(helper: BaseViewHolder, item: NewsDataBean) {
        when (helper?.getItemViewType()) {
            NewsDataBean.NEWSTEXT -> {

                try {
                    if (null != item?.user_info) {
                        val avatar_url = item?.user_info?.avatar_url
                        if (!TextUtils.isEmpty(avatar_url)) {
                            Glide.with(mContext)
                                    .load(avatar_url)
                                    .into(helper.getView<ImageView>(R.id.iv_media));
                        }
                    }

                    val tv_comment_count = (item?.comment_count)
                    var tv_datetime = item?.behot_time.toString()
                    if (!TextUtils.isEmpty(tv_datetime)) {
                        tv_datetime = TimeUtil.getTimeStampAgo(tv_datetime)
                    }

                    helper.setText(R.id.tv_title, item?.title)
                    helper.getView<TextView>(R.id.tv_title).setTextSize(16f)
                    helper.setText(R.id.tv_abstract, item?.abstractX)
                    helper.setText(R.id.tv_extra, item?.source + " - " + tv_comment_count + "评论 - " + tv_datetime)
                    helper.addOnClickListener(R.id.tv_dots)

                } catch (e: Exception) {
                    e.printStackTrace()
                }


            }
            NewsDataBean.NEWSIMG -> {
                try {
                    var imgUrl = "http://p3.pstatp.com/"
                    val image_list = item?.image_list
                    if (image_list != null && image_list!!.size != 0) {
                        val url = image_list!!.get(0).url
                        Glide.with(mContext).load(url).into(helper.getView(R.id.iv_image))
                        if (!TextUtils.isEmpty(image_list!!.get(0).uri)) {
                            imgUrl += image_list!!.get(0).uri?.replace("list", "large")
                        }
                    }

                    if (null != item?.user_info) {
                        val avatar_url = item?.user_info?.avatar_url
                        if (!TextUtils.isEmpty(avatar_url)) {
                            Glide.with(mContext).load(avatar_url).into(helper.getView(R.id.iv_media))
                        }
                    }


                    val tv_comment_count = item.comment_count.toString() + "评论"
                    var tv_datetime = item.behot_time.toString() + ""
                    if (!TextUtils.isEmpty(tv_datetime)) {
                        tv_datetime = TimeUtil.getTimeStampAgo(tv_datetime)
                    }
                    helper.setText(R.id.tv_title, item?.title)
                    helper.getView<TextView>(R.id.tv_title).setTextSize(16f)
                    helper.setText(R.id.tv_abstract, item?.abstractX)
                    helper.setText(R.id.tv_extra, item?.source + " - " + tv_comment_count + " - " + tv_datetime)
                    helper.addOnClickListener(R.id.tv_dots)

                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }
            NewsDataBean.NEWSVIDEO -> {

                try {
                    if (null != item.video_detail_info) {
                        if (null != item.video_detail_info?.detail_video_large_image) {
                            val image = item.video_detail_info?.detail_video_large_image?.url
                            if (!TextUtils.isEmpty(image)) {
                                Glide.with(mContext).load(image).into(helper.getView(R.id.iv_video_image))
                            }
                        }
                    } else {
                        helper.setImageResource(R.id.iv_video_image, R.drawable.ic_img_default)
                    }

                    if (null != item.user_info) {
                        val avatar_url = item.user_info?.avatar_url
                        if (!TextUtils.isEmpty(avatar_url)) {
                            Glide.with(mContext).load(avatar_url).into(helper.getView(R.id.iv_media))
                        }
                    }

                    val tv_comment_count = item.comment_count.toString() + "评论"
                    var tv_datetime = item.behot_time.toString() + ""
                    if (!TextUtils.isEmpty(tv_datetime)) {
                        tv_datetime = TimeUtil.getTimeStampAgo(tv_datetime)
                    }
                    val video_duration = item.video_duration
                    val min = (video_duration / 60).toString()
                    var second = (video_duration % 10).toString()
                    if (Integer.parseInt(second) < 10) {
                        second = "0$second"
                    }
                    val tv_video_time = min + ":" + second

                    helper.setText(R.id.tv_title, item?.title)
                    helper.getView<TextView>(R.id.tv_title).setTextSize(16f)
                    helper.setText(R.id.tv_extra, item?.source + " - " + tv_comment_count + " - " + tv_datetime)
                    helper.setText(R.id.tv_video_time, tv_video_time)
                    helper.addOnClickListener(R.id.tv_dots)

                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }
            else -> {
            }
        }


    }


}