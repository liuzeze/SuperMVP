package com.lz.framecase.api;


import android.util.Base64;

import com.lz.fram.base.LpLoadDialog;
import com.lz.fram.observer.Transformer;
import com.lz.framecase.BuildConfig;
import com.lz.framecase.bean.FaceResponse;
import com.lz.framecase.bean.MultNewsBean;
import com.lz.framecase.bean.NewsCommentBean;
import com.lz.framecase.bean.NewsContentBean;
import com.lz.framecase.bean.TokenBean;
import com.lz.framecase.bean.VideoContentBean;
import com.lz.framecase.bean.WendaArticleBean;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * 网络请求
 */
public class RequestApi {
    @Inject
    Retrofit mRetrofit;
    @Inject
    LpLoadDialog mLpLoadDialog;

    @Inject
    public RequestApi() {
    }


    public Flowable<MultNewsBean> getNewLists(String category, String time) {

        int i = new Random().nextInt(10);
        if (i % 2 == 0) {
            return
                    mRetrofit.create(ApiService.class)
                            .getNewsArticle(category, time)
                            .compose(Transformer.<MultNewsBean>switchSchedulers());
        } else {
            return
                    mRetrofit.create(ApiService.class)
                            .getNewsArticle2(category, time)
                            .compose(Transformer.<MultNewsBean>switchSchedulers());
        }


    }

    public Flowable<WendaArticleBean> getWenDaLists(String time) {
        return
                mRetrofit.create(ApiService.class)
                        .getWendaArticle(time)
                        .compose(Transformer.<WendaArticleBean>switchSchedulers());

    }

    @Nullable
    public Flowable<NewsContentBean> getNewsContent(@NotNull String s) {
        return
                mRetrofit.create(ApiService.class)
                        .getNewsContent(s);

    }


    @Nullable
    public Flowable<NewsCommentBean> getNewsComment(@NotNull String groupId, long itemId) {
        return
                mRetrofit.create(ApiService.class)
                        .getNewsComment(groupId, itemId)
                        .compose(Transformer.<NewsCommentBean>switchSchedulers(mLpLoadDialog));


    }

    @Nullable
    public Flowable<String> getVideoUrl(@NotNull String url) {
        return
                mRetrofit.create(ApiService.class)
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
        return mRetrofit.create(ApiService.class)
                .getFaceInfo(img, "BASE64", des, 10, "LIVE", token)
                .compose(Transformer.<FaceResponse>switchSchedulers(mLpLoadDialog));
    }


    public Flowable<TokenBean> token() {
        return mRetrofit.create(ApiService.class)
                .token("client_credentials", BuildConfig.FACE_API_KEY, BuildConfig.FACE_API_SECRET)
                .compose(Transformer.<TokenBean>switchSchedulers(mLpLoadDialog));

    }
}
