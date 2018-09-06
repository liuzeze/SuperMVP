package com.lz.framecase.activity

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.graphics.Bitmap
import android.graphics.Color
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.text.TextUtils
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseQuickAdapter.SLIDEIN_BOTTOM
import com.chad.library.adapter.base.animation.SlideInBottomAnimation
import com.chad.library.adapter.base.listener.OnItemDragListener
import com.lz.fram.base.BasePresenter
import com.lz.framecase.R
import com.lz.framecase.R.id.recycler_view
import com.lz.framecase.activity.adapter.drag.DragAdapter
import com.lz.framecase.activity.adapter.drag.ItemTouchListCallBack
import com.lz.framecase.activity.adapter.drag.MyItemAnimator
import com.lz.framecase.base.BaseActivity
import com.lz.framecase.bean.TitleBean
import com.lz.utilslib.interceptor.utils.SnackbarUtils
import com.vondear.rxtool.RxSPTool
import kotlinx.android.synthetic.main.activity_catgory.*
import java.util.*
import kotlin.collections.ArrayList

class CatgoryActivity : BaseActivity<BasePresenter<*>>() {

    private val titles = ArrayList<TitleBean>()
    private var mDragAdapter: DragAdapter? = null
    private var mItemDragAndSwipeCallback: ItemTouchListCallBack? = null
    private var mItemTouchHelper: ItemTouchHelper? = null

    override fun getLayout(): Int {
        return R.layout.activity_catgory

    }

    override fun onCreate() {
        toolbar.title = "分类排序"
        toolbar.setTitleTextColor(Color.WHITE)
        toolbar.setNavigationIcon(R.drawable.ic_white_back)
        actionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            finish()
        }
        val gridLayoutManager = GridLayoutManager(mActivity, 4)
        recycler_view.layoutManager = gridLayoutManager

        getData()
        initAdapter()
        initListener()
    }

    private fun CatgoryActivity.initListener() {
        mDragAdapter?.setOnItemChildClickListener { adapter, view, position ->
            if (view.id == R.id.tv_finish) {
                val bean = titles.get(position)
                val tempB = bean.isEditer
                if (tempB) {
                    mDragAdapter?.disableDragItem()
                } else {
                    mDragAdapter?.enableDragItem(mItemTouchHelper!!)
                }
                for (title in titles) {
                    title.isEditer = if (tempB) false else true
                }
                mDragAdapter?.notifyDataSetChanged()
            }
        }
        mDragAdapter?.setOnItemLongClickListener(BaseQuickAdapter.OnItemLongClickListener { adapter, view, position ->
            if (titles.get(position).itemTypes == TitleBean.MYTITLESub) {
                mDragAdapter?.enableDragItem(mItemTouchHelper!!)
                for (title in titles) {
                    title.isEditer = true
                }
                mDragAdapter?.notifyDataSetChanged()
            }
            false
        })
        mDragAdapter?.setOnItemClickListener(BaseQuickAdapter.OnItemClickListener { adapter, view, position ->
            if (titles.get(position).name.equals("推荐") ||
                    titles.get(position).name.equals("视频")) {
                SnackbarUtils.show(view, "不可取消")
                return@OnItemClickListener
            }
            var count2 = 0
            for (title in titles) {

                if (title.itemType === TitleBean.MYTITLESub) {
                    count2++
                }
            }

            val titleBean = titles.get(position)


            var isRun = false
            if (titleBean?.itemTypes === TitleBean.ORTHERTITLESUB) {
                val imageView = addMirrorView(recycler_view.getParent() as ViewGroup, recycler_view, view)

                titleBean.itemTypes = TitleBean.MYTITLESub
                titles.removeAt(position)
                titles.add(count2 + 1, titleBean)
                mDragAdapter?.notifyItemRangeChanged(count2, titles.size - 1)

                imageView.setTranslationX(view.left.toFloat())
                imageView.setTranslationY(view.top.toFloat())
                imageView.animate().translationX((view.measuredWidth * (count2 % 4)).toFloat()).setDuration(500).start()

                imageView.animate().translationY((view.measuredHeight * if (count2 / 4 == 0) count2 / 4 else count2 / 4 + 1).toFloat() + 50).setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        super.onAnimationEnd(animation)
                        (recycler_view.getParent() as ViewGroup).removeView(imageView)

                    }
                }).setDuration(500).start()
                isRun = true

            }

            if (mDragAdapter?.isItemDraggable!! && titleBean.itemType === TitleBean.MYTITLESub && !isRun) {
                val imageView = addMirrorView(recycler_view.getParent() as ViewGroup, recycler_view, view)
                titleBean?.itemTypes = TitleBean.ORTHERTITLESUB
                titles.removeAt(position)
                titles.add(count2 + 2, titleBean)
                mDragAdapter?.notifyItemRangeChanged(position, titles.size - 1)
                imageView.setTranslationX(view.left.toFloat())
                imageView.setTranslationY(view.top.toFloat() + 50)
                imageView.animate().translationX(0f).setDuration(500).start()
                val i = (count2 - 1) / 4
                imageView.animate().translationY((view.measuredHeight * (if (i == 0) i + 2 else i + 3)).toFloat() + 50)
                        .setListener(object : AnimatorListenerAdapter() {
                            override fun onAnimationEnd(animation: Animator) {
                                super.onAnimationEnd(animation)
                                (recycler_view.getParent() as ViewGroup).removeView(imageView)

                            }
                        }).setDuration(500).start()
            }


            var catgorys = ""
            for (title in titles) {
                if (title.itemTypes === TitleBean.MYTITLESub) {
                    if (TextUtils.isEmpty(catgorys)) {
                        catgorys = title.name + "|" + title.newsId
                    } else {
                        catgorys = catgorys + "&" + title.name + "|" + title.newsId
                    }
                }
            }
            RxSPTool.putString(mActivity, NEWSCATGORY, catgorys)

        })
    }

    /**
     * 添加需要移动的 镜像View
     */
    private fun addMirrorView(parent: ViewGroup, recyclerView: RecyclerView, view: View): ImageView {

        view.destroyDrawingCache()
        view.isDrawingCacheEnabled = true
        val mirrorView = ImageView(recyclerView.context)
        val bitmap = Bitmap.createBitmap(view.drawingCache)
        mirrorView.setImageBitmap(bitmap)
        mirrorView.setBackgroundResource(R.color.common_app_red_ff0000)
        view.isDrawingCacheEnabled = false
        val locations = IntArray(2)
        view.getLocationOnScreen(locations)
        val parenLocations = IntArray(2)
        recyclerView.getLocationOnScreen(parenLocations)
        val params = FrameLayout.LayoutParams(bitmap.width, bitmap.height)
        params.setMargins(locations[0], locations[1] - parenLocations[1], 0, 0)
        parent.addView(mirrorView, params)

        return mirrorView
    }


    private fun initAdapter() {
        val gridLayoutManager = GridLayoutManager(mActivity, 4)
        recycler_view.setLayoutManager(gridLayoutManager)
        mDragAdapter = DragAdapter(titles)
        mDragAdapter?.openLoadAnimation(SLIDEIN_BOTTOM)
        mItemDragAndSwipeCallback = ItemTouchListCallBack(mDragAdapter!!)
        mItemTouchHelper = ItemTouchHelper(mItemDragAndSwipeCallback)
        mItemTouchHelper?.attachToRecyclerView(recycler_view)
        (recycler_view.getItemAnimator() as DefaultItemAnimator).supportsChangeAnimations = false
        recycler_view.setItemAnimator(MyItemAnimator())
        mItemDragAndSwipeCallback?.setDragMoveFlags(ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT or ItemTouchHelper.UP or ItemTouchHelper.DOWN)

        recycler_view.setAdapter(mDragAdapter)
        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                val itemViewType = mDragAdapter?.getItemViewType(position)
                return if (itemViewType == TitleBean.MYTITLE || itemViewType == TitleBean.ORTHERTITLE) 4 else 1
            }
        }
    }

    companion object {
        var NEWSCATGORY = "catgorynews"
    }

    private fun getData() {
        try {
            val newsName = resources.getStringArray(R.array.mobile_news_name)
            val newsId = resources.getStringArray(R.array.mobile_news_id)

            val category = RxSPTool.getContent(mActivity, NEWSCATGORY)

            val titleBean = TitleBean()
            titleBean.itemTypes = TitleBean.MYTITLE
            titleBean.name = "我的频道  长按编辑 拖拽"
            titles.add(titleBean)

            var tempList = ArrayList<TitleBean>()
            for (index in newsName.indices) {
                var s = newsName.get(index)
                val titleBean = TitleBean()
                titleBean.name = s
                titleBean.newsId = newsId.get(index)

                if (TextUtils.isEmpty(category)) {
                    if (index < 6) {
                        titleBean.itemTypes = TitleBean.MYTITLESub
                        titles.add(titleBean)

                    } else {
                        titleBean.itemTypes = TitleBean.ORTHERTITLESUB
                        tempList.add(titleBean)
                    }
                } else {
                    if (s in category) {
                        titleBean.itemTypes = TitleBean.MYTITLESub
                        titles.add(titleBean)
                    } else {
                        titleBean.itemTypes = TitleBean.ORTHERTITLESUB
                        tempList.add(titleBean)
                    }
                }

            }
            val titleBean1 = TitleBean()
            titleBean1.name = "隐藏频道"
            titleBean1.itemTypes = TitleBean.ORTHERTITLE
            titles.add(titleBean1)
            titles.addAll(tempList)
        } catch (e: Exception) {
        }


    }
}
