package com.lz.framecase.fragment.adapter

import android.support.v7.widget.PopupMenu
import android.text.TextUtils
import android.view.Gravity
import android.widget.TextView
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.lz.framecase.R
import com.lz.framecase.bean.DataEntity
import com.lz.framecase.bean.MultNewsBean
import com.lz.framecase.bean.NewsDataBean
import com.vondear.rxtool.RxTimeTool
import java.util.concurrent.TimeUnit

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

    override fun convert(helper: BaseViewHolder?, item: NewsDataBean?) {
        when (helper?.getItemViewType()) {
            NewsDataBean.NEWSTEXT -> {

                try {
                    if (null != item?.user_info) {
                        val avatar_url = item?.user_info?.avatar_url
                        if (!TextUtils.isEmpty(avatar_url)) {
                            // ImageLoader.loadCenterCrop(context, avatar_url, holder.iv_media, R.color.viewBackground)
                        }
                    }

                    val tv_comment_count = (item?.comment_count)
                    var tv_datetime = item?.behot_time.toString()
                   /* if (!TextUtils.isEmpty(tv_datetime)) {
                        //   tv_datetime = RxTimeTool.getTimeStampAgo(tv_datetime)
                    }*/

                    helper.setText(R.id.tv_title, item?.title)
                    helper.getView<TextView>(R.id.tv_title).setTextSize(16f)
                    helper.setText(R.id.tv_abstract, item?.abstractX)
                    helper.setText(R.id.tv_extra, item?.source + " - " + tv_comment_count + "评论 - " + tv_datetime)
                    /* helper.iv_dots.setOnClickListener({ view ->
                         val popupMenu = PopupMenu(context,
                                 holder.iv_dots, Gravity.END, 0, R.style.MyPopupMenu)
                         popupMenu.inflate(R.menu.menu_share)
                         popupMenu.setOnMenuItemClickListener { menu ->
                             val itemId = menu.itemId
                             if (itemId == R.id.action_share) {
                                 IntentAction.send(context, item.getTitle() + "\n" + item.getShare_url())
                             }
                             false
                         }
                         popupMenu.show()
                     })*/

                    /* RxView.clicks(holder.itemView)
                             .throttleFirst(1, TimeUnit.SECONDS)
                             .subscribe({ o -> NewsContentActivity.launch(item) })*/
                } catch (e: Exception) {
                    e.printStackTrace()
                }


            }
            NewsDataBean.NEWSIMG -> {

            }
            NewsDataBean.NEWSVIDEO -> {

            }
            else -> {
            }
        }



    }


}