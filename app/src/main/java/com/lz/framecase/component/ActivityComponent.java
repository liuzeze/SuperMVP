package com.lz.framecase.component;


import com.lz.fram.component.AppComponent;
import com.lz.fram.scope.CustomizeScope;
import com.lz.framecase.activity.MainActivity;
import com.lz.framecase.activity.NewsCommentActivity;
import com.lz.framecase.activity.SearchActivity;
import com.lz.framecase.activity.VideoPlayerActivity;

import dagger.Component;

/**
 * -----------作者----------日期----------变更内容-----
 * -          刘泽      2018-06-26       创建class
 */

@CustomizeScope
@Component(dependencies = AppComponent.class)
public interface ActivityComponent {


    void inject(MainActivity activity);

    void inject(NewsCommentActivity activity);

    void inject(VideoPlayerActivity activity);

    void inject(SearchActivity activity);
}
