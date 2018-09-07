package com.lz.framecase.component;

import com.lz.fram.scope.CustomizeScope;
import com.lz.framecase.activity.MainActivity;
import com.lz.framecase.activity.NewsCommentActivity;
import com.lz.framecase.activity.VideoPlayerActivity;
import com.lz.framecase.fragment.ImagePagerFragment;
import com.lz.framecase.fragment.NewsDetailFragment;
import com.lz.framecase.fragment.NewsListFragment;
import com.lz.framecase.fragment.NewsPagerFragment;
import com.lz.framecase.fragment.NewsTitlePagerFragment;
import com.lz.framecase.fragment.VideoPagerFragment;
import com.lz.framecase.fragment.WenDaListFragment;

import dagger.Module;
import dagger.android.AndroidInjector;
import dagger.android.ContributesAndroidInjector;


@Module(subcomponents = {
        BaseActivityComponent.class
})
public interface FragmentProvider {
    @CustomizeScope
    @ContributesAndroidInjector()
    NewsListFragment contributeNewsListFragmenttInjector();

    @CustomizeScope
    @ContributesAndroidInjector()
    WenDaListFragment contributeWenDaListFragmenttInjector();

    @CustomizeScope
    @ContributesAndroidInjector()
    NewsDetailFragment contributeNewsDetailFragmentInjector();

    @CustomizeScope
    @ContributesAndroidInjector()
    NewsPagerFragment contributeImagePagerFragmenInjector();

    @CustomizeScope
    @ContributesAndroidInjector()
    ImagePagerFragment contributeImagePagerFragmenInjetor();

    @CustomizeScope
    @ContributesAndroidInjector()
    VideoPagerFragment contributeImagePageFragmenInjetor();

    @CustomizeScope
    @ContributesAndroidInjector()
    NewsTitlePagerFragment contributeImgePageFragmenInjetor();


}
