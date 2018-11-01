package com.lz.framecase.fragment.adapter

import android.text.TextUtils
import android.widget.TextView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.lz.framecase.R
import com.lz.framecase.bean.WendaArticleDataBean
import com.lz.utilslib.interceptor.utils.TimeUtil

/**
 *-----------作者----------日期----------变更内容-----
 *-          刘泽      2018-08-29       创建class
 */
class WenDaListAdapter(arrayList: ArrayList<WendaArticleDataBean>)
    : BaseMultiItemQuickAdapter<WendaArticleDataBean, BaseViewHolder>(arrayList) {
    init {
        addItemType(WendaArticleDataBean.WENDATEXT, R.layout.item_wenda_text)
        addItemType(WendaArticleDataBean.WENDAIMG, R.layout.item_wenda_one_img)
        addItemType(WendaArticleDataBean.WENDAIMAGES, R.layout.item_wenda_three_img)
    }

    override fun convert(helper: BaseViewHolder, item: WendaArticleDataBean) {
        when (helper?.getItemViewType()) {
            WendaArticleDataBean.WENDATEXT -> {

                try {
                    val tv_title = item.questionBean?.title
                    val tv_answer_count = item.questionBean?.normal_ans_count.toString() + item.questionBean!!.nice_ans_count.toString() + "回答"
                    var tv_datetime = item.questionBean?.create_time.toString()
                    if (!TextUtils.isEmpty(tv_datetime)) {
                        tv_datetime = TimeUtil.getTimeStampAgo(tv_datetime)
                    }
                    val tv_content = item.answerBean?.abstractX
                    helper.setText(R.id.tv_title, tv_title)
                    helper.getView<TextView>(R.id.tv_title).setTextSize(16f)
                    helper.setText(R.id.tv_answer_count, tv_answer_count)
                    helper.setText(R.id.tv_time, tv_datetime)
                    helper.setText(R.id.tv_content, tv_content)

                    /* RxView.clicks(holder.itemView)
                             .throttleFirst(1, TimeUnit.SECONDS)
                             .subscribe({ o -> WendaContentActivity.launch(item.getQuestionBean().getQid() + "") })*/
                } catch (e: Exception) {
                    e.printStackTrace()
                }


            }
            WendaArticleDataBean.WENDAIMG -> {
                try {
                    val url = item.extraBean?.wenda_image?.large_image_list?.get(0)?.url
                    Glide.with(mContext).load(url).into(helper.getView(R.id.iv_image_big))

                    val tv_title = item.questionBean?.title
                    val tv_answer_count = item.questionBean?.normal_ans_count.toString() + item.questionBean?.nice_ans_count.toString() + "回答"
                    var tv_datetime = item.questionBean?.create_time.toString() + ""
                    if (!TextUtils.isEmpty(tv_datetime)) {
                        tv_datetime = TimeUtil.getTimeStampAgo(tv_datetime)
                    }
                    helper.setText(R.id.tv_title, tv_title)
                    helper.getView<TextView>(R.id.tv_title).setTextSize(16f)
                    helper.setText(R.id.tv_answer_count, tv_answer_count)
                    helper.setText(R.id.tv_time, tv_datetime)

                    /* RxView.clicks(holder.itemView)
                             .throttleFirst(1, TimeUnit.SECONDS)
                             .subscribe({ o -> WendaContentActivity.launch(item.getQuestionBean().getQid() + "") })*/
                } catch (e: Exception) {
                    e.printStackTrace()
                }


            }
            WendaArticleDataBean.WENDAIMAGES -> {

                try {
                    val size = item.extraBean?.wenda_image?.three_image_list?.size!!
                    val ivs = arrayOfNulls<String>(size)
                    for (i in 0 until size) {
                        ivs[i] = item.extraBean!!.wenda_image!!.three_image_list!!.get(i).url
                    }
                    when (ivs.size) {
                        1 -> {
                            Glide.with(mContext).load(ivs[0]).into(helper.getView(R.id.iv_0))
                        }
                        2
                        -> {
                            Glide.with(mContext).load(ivs[0]).into(helper.getView(R.id.iv_0))
                            Glide.with(mContext).load(ivs[1]).into(helper.getView(R.id.iv_1))

                        }
                        3 -> {
                            Glide.with(mContext).load(ivs[0]).into(helper.getView(R.id.iv_0))
                            Glide.with(mContext).load(ivs[1]).into(helper.getView(R.id.iv_1))
                            Glide.with(mContext).load(ivs[2]).into(helper.getView(R.id.iv_2))
                        }
                    }

                    val tv_title = item.questionBean?.title
                    val tv_answer_count = item.questionBean?.normal_ans_count.toString() + item.questionBean?.nice_ans_count.toString() + "回答"
                    var tv_datetime = item.questionBean?.create_time.toString() + ""
                    if (!TextUtils.isEmpty(tv_datetime)) {
                        tv_datetime = TimeUtil.getTimeStampAgo(tv_datetime)
                    }
                    helper.setText(R.id.tv_title, tv_title)
                    helper.getView<TextView>(R.id.tv_title).setTextSize(16f)
                    helper.setText(R.id.tv_answer_count, tv_answer_count)
                    helper.setText(R.id.tv_time, tv_datetime)


                    /*  RxView.clicks(holder.itemView)
                              .throttleFirst(1, TimeUnit.SECONDS)
                              .subscribe({ o -> WendaContentActivity.launch(item.getQuestionBean().getQid() + "") })*/
                } catch (e: Exception) {
                    e.printStackTrace()
                }


            }
            else -> {
            }
        }


    }


}