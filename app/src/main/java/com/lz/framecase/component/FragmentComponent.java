
package com.lz.framecase.component;


import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lz.fram.component.AppComponent;
import com.lz.fram.scope.CustomizeScope;
import com.lz.framecase.R;
import com.lz.framecase.activity.MainActivity;
import com.lz.framecase.fragment.ImagePagerFragment;
import com.lz.framecase.fragment.NewsListFragment;
import com.lz.framecase.fragment.NewsPagerFragment;
import com.lz.framecase.fragment.NewsTitlePagerFragment;
import com.lz.framecase.fragment.VideoPagerFragment;
import com.lz.framecase.fragment.WenDaListFragment;

import dagger.Component;

@CustomizeScope
@Component(dependencies = AppComponent.class)
public interface FragmentComponent {
    /*void inject(NewsPagerFragment fragment);

    void inject(ImagePagerFragment fragment);

    void inject(VideoPagerFragment fragment);

    void inject(NewsTitlePagerFragment fragment);*/

    void inject(NewsListFragment fragment);

    void inject(WenDaListFragment fragment);


}
