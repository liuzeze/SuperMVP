package com.lz.fram.inject;


import com.lz.fram.base.BasePresenter;
import com.lz.fram.scope.AttachView;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * -------- 日期 ---------- 维护人 ------------ 变更内容 --------
 * 2018/7/30       刘泽
 */
public class PresenterProviders {

    private PresenterStore mPresenterStore = new PresenterStore<>();

    public static PresenterProviders inject(Object obj) {
        return new PresenterProviders(obj);
    }

    private PresenterProviders(Object obj) {
        checkNull(obj);
        for (Field field : obj.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            //获取字段上的注解
            Annotation[] anns = field.getDeclaredAnnotations();
            if (anns.length < 1) {
                continue;
            }
            for (Annotation ann : anns) {

                if (ann instanceof AttachView) {
                    String canonicalName = field.getType().getName();
                    try {
                        mPresenterStore.put(canonicalName, (BasePresenter) field.get(obj));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    break;
                }
            }
        }
    }


    private static boolean checkNull(Object obj) {
        if (obj == null) {
            return false;
        } else {
            return true;
        }
    }


    public PresenterStore getPresenterStore() {
        return mPresenterStore;
    }

    public PresenterDispatch presenterCreate() {
        PresenterDispatch presenterDispatch = new PresenterDispatch(this);
        return presenterDispatch;
    }
}
