package com.lz.framecase.component;

import com.lz.fram.scope.CustomizeScope;
import com.lz.framecase.activity.CatgoryActivity;
import com.lz.framecase.activity.ImagePreviewActivity;
import com.lz.framecase.activity.MainActivity;
import com.lz.framecase.activity.NewDetailActivity;
import com.lz.framecase.activity.NewsCommentActivity;
import com.lz.framecase.activity.SearchActivity;
import com.lz.framecase.activity.VideoPlayerActivity;

import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.android.ContributesAndroidInjector;


@Module(subcomponents = {
        BaseActivityComponent.class
})
public interface ActivityProvider {
    @CustomizeScope
    @ContributesAndroidInjector()
    abstract MainActivity MainActivitytInjector();

    @CustomizeScope
    @ContributesAndroidInjector()
    abstract NewsCommentActivity NewsCommentActivitytInjector();

    @CustomizeScope
    @ContributesAndroidInjector()
    abstract NewDetailActivity NewsCommentAtivitytInjector();

    @CustomizeScope
    @ContributesAndroidInjector()
    abstract VideoPlayerActivity VideoPlayerActivitytInjector();

    @CustomizeScope
    @ContributesAndroidInjector()
    abstract CatgoryActivity CatgoryActivityInjector();

    @CustomizeScope
    @ContributesAndroidInjector()
    abstract ImagePreviewActivity ImagePreviewActivityInjector();

    @CustomizeScope
    @ContributesAndroidInjector()
    abstract SearchActivity SearchActivityInjector();


}
