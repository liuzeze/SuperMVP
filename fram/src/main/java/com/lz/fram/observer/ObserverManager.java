package com.lz.fram.observer;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class ObserverManager {


    public static HashMap<Object, CompositeDisposable> mMaps;



    public ObserverManager() {
        mMaps = new HashMap<>();
    }

    public void add(Object tag, Disposable disposable) {
        if (null == tag) {
            return;
        }
        cancel(tag);
        CompositeDisposable compositeDisposableNew = new CompositeDisposable();
        compositeDisposableNew.add(disposable);
        mMaps.put(tag, compositeDisposableNew);

    }


    public void cancel(Object tag) {
        if (null == tag) {
            return;
        }
        if (mMaps.isEmpty()) {
            return;
        }
        if (null == mMaps.get(tag)) {
            return;
        }
        if (!mMaps.get(tag).isDisposed()) {
            mMaps.get(tag).dispose();
            mMaps.remove(tag);
        }
    }

    public void cancel(Object... tags) {
        if (null == tags) {
            return;
        }
        for (Object tag : tags) {
            cancel(tag);
        }
    }

    public void cancelAll() {
        if (mMaps.isEmpty()) {
            return;
        }
        Iterator<Map.Entry<Object, CompositeDisposable>> it = mMaps.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Object, CompositeDisposable> entry = it.next();
            CompositeDisposable disposable = entry.getValue();
            //如果直接使用map的remove方法会报这个错误java.util.ConcurrentModificationException
            //所以要使用迭代器的方法remove
            if (null != disposable) {
                if (!disposable.isDisposed()) {
                    disposable.dispose();
                    it.remove();
                }
            }
        }
    }
}
