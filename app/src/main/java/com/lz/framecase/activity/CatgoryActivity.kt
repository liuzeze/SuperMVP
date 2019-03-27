package com.lz.framecase.activity

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.databinding.ViewDataBinding
import android.databinding.adapters.ViewGroupBindingAdapter.setListener
import android.graphics.Bitmap
import android.graphics.Color
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.text.TextUtils
import android.transition.Explode
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseQuickAdapter.SLIDEIN_BOTTOM
import com.lz.framecase.R
import com.lz.framecase.activity.adapter.drag.DragAdapter
import com.lz.framecase.activity.adapter.drag.ItemTouchListCallBack
import com.lz.framecase.activity.adapter.drag.MyItemAnimator
import com.lz.framecase.base.BaseActivity
import com.lz.framecase.bean.TitleBean
import com.lz.utilslib.interceptor.utils.SnackbarUtils
import com.vondear.rxtool.RxSPTool
import kotlinx.android.synthetic.main.activity_catgory.*

class CatgoryActivity : BaseActivity() {

    private val titles = ArrayList<TitleBean>()
    private var mDragAdapter: DragAdapter? = null
    private var mItemDragAndSwipeCallback: ItemTouchListCallBack? = null
    private var mItemTouchHelper: ItemTouchHelper? = null

    override fun getLayout(): Int {

        return R.layout.activity_catgory

    }

    override fun initViewData() {
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
//        throw NullPointerException()
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
        mDragAdapter?.onItemLongClickListener = BaseQuickAdapter.OnItemLongClickListener { adapter, view, position ->
            if (titles.get(position).itemTypes == TitleBean.MYTITLESub) {
                mDragAdapter?.enableDragItem(mItemTouchHelper!!)
                for (title in titles) {
                    title.isEditer = true
                }
                mDragAdapter?.notifyDataSetChanged()
            }
            false
        }
        mDragAdapter?.onItemClickListener = BaseQuickAdapter.OnItemClickListener { adapter, view, position ->
            slidView(position, view)
        }
    }

    @Synchronized
    fun slidView(position: Int, view: View) {
        try {
            if (titles.get(position).name.equals("推荐") ||
                    titles.get(position).name.equals("视频")) {
                SnackbarUtils.show(view, "不可取消")
                return
            }
            val titleBean = titles.get(position)
            if (titleBean?.itemTypes == TitleBean.ORTHERTITLE ||
                    titleBean?.itemTypes == TitleBean.MYTITLE) {
                return
            }
            if (!titles.get(position).isEditer
                    && titleBean?.itemTypes == TitleBean.MYTITLESub) {
                return
            }
            titles.removeAt(position)
            var count2 = 0
            var otherPosition = 0
            titles.forEachIndexed { index, titleBean ->

                if (titleBean.itemType === TitleBean.MYTITLESub) {
                    count2++
                }
                if (titleBean.itemType === TitleBean.ORTHERTITLE) {
                    otherPosition = index
                }
            }


            var outLocation = IntArray(2)
            view.getLocationOnScreen(outLocation)


            var isRun = false
            //上移
            if (titleBean?.itemTypes === TitleBean.ORTHERTITLESUB) {
                val imageView = addMirrorView(recycler_view.getParent() as ViewGroup, recycler_view, view)

                titleBean.itemTypes = TitleBean.MYTITLESub
                titles.add(count2 + 1, titleBean)
                mDragAdapter?.notifyItemRangeChanged(count2, titles.size - 1)


                imageView.setTranslationX(outLocation[0].toFloat())
                imageView.setTranslationY(outLocation[1].toFloat())

                var outLocation2 = IntArray(2)
                val itemView = recycler_view.findViewHolderForAdapterPosition(otherPosition).itemView
                itemView.getLocationOnScreen(outLocation2)

                imageView.animate().translationX((view.measuredWidth * (count2 % 4)).toFloat())
                        .setDuration(500)
                        .start()

                imageView.animate().translationY(outLocation2[1].toFloat() + if (count2 % 4 == 0) -100 else -200)
                        .setListener(object : AnimatorListenerAdapter() {
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
                titles.add(count2 + 2, titleBean)
                mDragAdapter?.notifyItemRangeChanged(position, titles.size - 1)
                imageView.setTranslationX(outLocation[0].toFloat())
                imageView.setTranslationY(outLocation[1].toFloat())

                var outLocation2 = IntArray(2)
                val itemView = recycler_view.findViewHolderForAdapterPosition(otherPosition).itemView
                itemView.getLocationOnScreen(outLocation2)
                imageView.animate().translationX(0f).setDuration(500).start()
                val i = (count2 - 1) / 4
                imageView.animate().translationY((outLocation2[1].toFloat() + if (count2 % 4 == 0) 0 else view.measuredHeight) + 50)
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
        } catch (e: Exception) {
            e.printStackTrace()
        }
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
