package com.lz.framecase.api;


import android.util.Base64;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.lz.fram.base.LpLoadDialog;
import com.lz.fram.net.RxRequestUtils;
import com.lz.fram.observer.Transformer;
import com.lz.framecase.BuildConfig;
import com.lz.framecase.bean.FaceResponse;
import com.lz.framecase.bean.MultNewsBean;
import com.lz.framecase.bean.NewsCommentBean;
import com.lz.framecase.bean.NewsContentBean;
import com.lz.framecase.bean.TokenBean;
import com.lz.framecase.bean.VideoContentBean;
import com.lz.framecase.bean.WendaArticleBean;
import com.lz.framecase.logic.MyApplication;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * 网络请求
 */
public class RequestApi {

    private final LpLoadDialog mLpLoadDialog;

    public RequestApi() {
        mLpLoadDialog = new LpLoadDialog(MyApplication.mApplication);
    }

    public Flowable<String> getNewLists(String category, String time) {

        int i = new Random().nextInt(10);
        if (i % 2 == 0) {
            return
                    RxRequestUtils
                            .create(ApiService.class)
                            .getNewsArticle2(category, time)
                            .compose(Transformer.switchSchedulers());
        } else {
            return
                    RxRequestUtils
                            .create(ApiService.class)
                            .getNewsArticle3(category, time)
                            .compose(Transformer.switchSchedulers());
        }


    }
    public Flowable<WendaArticleBean> getWenDaLists(String time) {

        Flowable<WendaArticleBean> compose = RxRequestUtils
                .create(ApiService.class)
                .getWendaArticle(time)
                .compose(Transformer.<WendaArticleBean>switchSchedulers());


        return compose;
    }

    @Nullable
    public Flowable<NewsContentBean> getNewsContent(@NotNull String s) {
        return
                RxRequestUtils
                        .create(ApiService.class)
                        .getNewsContent(s);

    }


    @Nullable
    public Flowable<NewsCommentBean> getNewsComment(@NotNull String groupId, long itemId) {
        return
                RxRequestUtils
                        .create(ApiService.class)
                        .getNewsComment(groupId, itemId)
                        .compose(Transformer.switchSchedulers(mLpLoadDialog));


    }

    @Nullable
    public Flowable<String> getVideoUrl(@NotNull String url) {
        return
                RxRequestUtils
                        .create(ApiService.class)
                        .getVideoContent(url)
                        .subscribeOn(Schedulers.io())
                        .map(new Function<VideoContentBean, String>() {
                            @Override
                            public String apply(VideoContentBean videoContentBean) throws Exception {

                                VideoContentBean.DataBean.VideoListBean videoList = videoContentBean.getData().getVideo_list();
                                if (videoList.getVideo_3() != null) {
                                    String base64 = videoList.getVideo_3().getMain_url();
                                    String url1 = (new String(Base64.decode(base64.getBytes(), Base64.DEFAULT)));
                                    return url1;
                                }

                                if (videoList.getVideo_2() != null) {
                                    String base64 = videoList.getVideo_2().getMain_url();
                                    String url1 = (new String(Base64.decode(base64.getBytes(), Base64.DEFAULT)));
                                    return url1;
                                }

                                if (videoList.getVideo_1() != null) {
                                    String base64 = videoList.getVideo_1().getMain_url();
                                    String url1 = (new String(Base64.decode(base64.getBytes(), Base64.DEFAULT)));
                                    return url1;
                                }
                                return null;
                            }
                        })
                        .compose(Transformer.<String>switchSchedulers(mLpLoadDialog));


    }

    public Flowable<FaceResponse> getNewPicture(String img, String token, String des) {
        return RxRequestUtils
                .create(ApiService.class)
                .getFaceInfo(img, "BASE64", des, 10, "LIVE", token)
                .compose(Transformer.<FaceResponse>switchSchedulers(mLpLoadDialog));
    }


    public Flowable<TokenBean> token() {
        return RxRequestUtils
                .create(ApiService.class)
                .token("client_credentials", BuildConfig.FACE_API_KEY, BuildConfig.FACE_API_SECRET)
                .compose(Transformer.<TokenBean>switchSchedulers(mLpLoadDialog));

    }
}
