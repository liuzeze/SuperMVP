package com.lz.framecase.presenter;


import com.lz.fram.base.LpLoadDialog;
import com.lz.fram.base.RxPresenter;
import com.lz.framecase.api.RequestApi;


import javax.inject.Inject;


/**
 * -------- 日期 ---------- 维护人 ------------ 变更内容 --------
 */
public class Main2Presenter extends RxPresenter<Main2Contract.View> implements Main2Contract.Presenter {


    RequestApi mRequestApi;
    @Inject
    LpLoadDialog mLoadDialog;

    @Inject
    public Main2Presenter(RequestApi requestApi) {
        this.mRequestApi = requestApi;
    }

    /**
     * 登录
     */
    @Override
    public void login() {
       /* addSubscribe(
                "login",
                mRequestApi.login(new CommonSubscriber<BaseBean>(mView, mLPDialog) {
                    @Override
                    public void onNext(BaseBean s) {
                        mView.showLoginInfor(s);
                    }
                })
        );*/
    }
}