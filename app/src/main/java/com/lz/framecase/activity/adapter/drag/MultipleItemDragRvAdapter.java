package com.lz.framecase.activity.adapter.drag;

import android.support.annotation.Nullable;
import android.util.SparseArray;
import android.view.View;

import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.chad.library.adapter.base.util.MultiTypeDelegate;
import com.chad.library.adapter.base.util.ProviderDelegate;

import java.util.List;

/**
 * -----------作者----------日期----------变更内容-----
 * -          刘泽      2018-09-03       创建class
 */
public abstract class MultipleItemDragRvAdapter<T, V extends BaseViewHolder> extends BaseItemDraggableAdapter<T, V> {


    private SparseArray<BaseItemProvider> mItemProviders;
    protected ProviderDelegate mProviderDelegate;

    public MultipleItemDragRvAdapter(@Nullable List<T> data) {
        super(data);
    }

    /**
     * 用于adapter构造函数完成参数的赋值后调用
     * Called after the assignment of the argument to the adapter constructor
     */
    public void finishInitialize() {
        mProviderDelegate = new ProviderDelegate();

        setMultiTypeDelegate(new MultiTypeDelegate<T>() {

            @Override
            protected int getItemType(T t) {
                return getViewType(t);
            }
        });

        registerItemProvider();

        mItemProviders = mProviderDelegate.getItemProviders();

        for (int i = 0; i < mItemProviders.size(); i++) {
            int key = mItemProviders.keyAt(i);
            BaseItemProvider provider = mItemProviders.get(key);
            provider.mData = mData;
            getMultiTypeDelegate().registerItemType(key, provider.layout());
        }
    }

    protected abstract int getViewType(T t);

    public abstract void registerItemProvider();

    @Override
    protected void convert(V helper, T item) {
        int itemViewType = helper.getItemViewType();
        BaseItemProvider provider = mItemProviders.get(itemViewType);

        provider.mContext = helper.itemView.getContext();

        int position = helper.getLayoutPosition() - getHeaderLayoutCount();
        provider.convert(helper, item, position);

        bindClick(helper, item, position, provider);
    }

    private void bindClick(final V helper, final T item, final int position, final BaseItemProvider provider) {
        OnItemClickListener clickListener = getOnItemClickListener();
        OnItemLongClickListener longClickListener = getOnItemLongClickListener();

        if (clickListener != null && longClickListener != null) {
            //如果已经设置了子条目点击监听和子条目长按监听
            // If you have set up a sub-entry click monitor and sub-entries long press listen
            return;
        }

        View itemView = helper.itemView;

        if (clickListener == null) {
            //如果没有设置点击监听，则回调给itemProvider
            //Callback to itemProvider if no click listener is set
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    provider.onClick(helper, item, position);
                }
            });
        }

        if (longClickListener == null) {
            //如果没有设置长按监听，则回调给itemProvider
            // If you do not set a long press listener, callback to the itemProvider
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    return provider.onLongClick(helper, item, position);
                }
            });
        }
    }
}
