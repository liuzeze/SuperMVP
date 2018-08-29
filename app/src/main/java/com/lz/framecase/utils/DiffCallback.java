package com.lz.framecase.utils;

import android.support.v7.util.DiffUtil;

/**
 * Created by Meiji on 2017/4/18.
 */

public class DiffCallback extends DiffUtil.Callback {
    @Override
    public int getOldListSize() {
        return 0;
    }

    @Override
    public int getNewListSize() {
        return 0;
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return false;
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return false;
    }

   /* private final Items mOldItems, mNewItems;

    private DiffCallback(Items oldItems, Items mNewItems) {
        this.mOldItems = oldItems;
        this.mNewItems = mNewItems;
    }

    public static void create(@NonNull Items oldList, @NonNull Items newList, @NonNull MultiTypeAdapter adapter) {
        DiffCallback diffCallback = new DiffCallback(oldList, newList);
        DiffUtil.DiffResult result = DiffUtil.calculateDiff(diffCallback, true);
        result.dispatchUpdatesTo(adapter);
    }

    @Override
    public int getOldListSize() {
        return mOldItems != null ? mOldItems.size() : 0;
    }

    @Override
    public int getNewListSize() {
        return mNewItems != null ? mNewItems.size() : 0;
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return mOldItems.get(oldItemPosition).equals(mNewItems.get(newItemPosition));
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return mOldItems.get(oldItemPosition).hashCode() == mNewItems.get(newItemPosition).hashCode();
    }*/
}
