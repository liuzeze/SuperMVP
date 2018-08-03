package com.lz.fram.base;


/**
 * -----------作者----------日期----------变更内容-----
 * -          刘泽      2018-06-28       绑定accitvty的基类presenter
 */
public interface BasePresenter<T extends BaseView> {

    void attachView(T view);

    void detachView();
}
