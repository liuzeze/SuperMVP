package com.lz.framecase.api;


import com.lz.framecase.bean.MultNewsBean;
import com.lz.framecase.bean.NewsCommentBean;
import com.lz.framecase.bean.NewsContentBean;
import com.lz.framecase.bean.VideoContentBean;
import com.lz.framecase.bean.WendaArticleBean;
import com.lz.framecase.bean.WendaArticleDataBean;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * API 管理器服务
 */
public interface ApiService {

    //    @Headers({DOMAIN_NAME_HEADER + DOUBAN_DOMAIN_NAME})
    @GET("news/latest")
    Flowable<String> getNewLists();

    @GET("http://is.snssdk.com/api/news/feed/v62/?iid=5034850950&device_id=6096495334&refer=1&count=20&aid=13")
    Flowable<MultNewsBean> getNewsArticle(
            @Query("category") String category,
            @Query("max_behot_time") String maxBehotTime);

    @GET("http://lf.snssdk.com/api/news/feed/v62/?iid=12507202490&device_id=37487219424&refer=1&count=20&aid=13")
    Flowable<MultNewsBean> getNewsArticle2(
            @Query("category") String category,
            @Query("max_behot_time") String maxBehotTime);


    /**
     * 获取头条问答标题等信息
     * http://is.snssdk.com/wenda/v1/native/feedbrow/?category=question_and_answer&wd_version=5&count=20&max_behot_time=1495245397?iid=10344168417&device_id=36394312781
     *
     * @param maxBehotTime 时间轴
     */
    @GET("http://is.snssdk.com/wenda/v1/native/feedbrow/?iid=10344168417&device_id=36394312781&category=question_and_answer&wd_version=5&count=20&aid=13")
    Flowable<WendaArticleBean> getWendaArticle(
            @Query("max_behot_time") String maxBehotTime);

    @GET
    Observable<NewsContentBean> getNewsContent(@Url String url);

    @GET("http://is.snssdk.com/article/v62/tab_comments/")
    Flowable<NewsCommentBean> getNewsComment(
            @Query("group_id") String groupId,
            @Query("offset") long offset);


    /**
     * 获取视频信息
     * <p>
     * http://ib.365yg.com/video/urls/v/1/toutiao/mp4/视频ID?r=17位随机数&s=加密结果
     */
    @GET
    Flowable<VideoContentBean> getVideoContent(@Url String url);
}
