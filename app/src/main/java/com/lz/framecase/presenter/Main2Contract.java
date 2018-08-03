package com.lz.framecase.presenter;


import com.lz.fram.base.BasePresenter;
import com.lz.fram.base.BaseView;

/**
 * -------- 日期 ---------- 维护人 ------------ 变更内容 --------
 */
public interface Main2Contract {


    interface View extends BaseView {
    }

    interface Presenter extends BasePresenter<View> {
        void getNewLists();
    }


}
