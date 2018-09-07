package com.lz.framecase.component;

import com.lz.framecase.base.BaseActivity;
import com.lz.framecase.base.BaseFragment;

import dagger.Subcomponent;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Created by QingMei on 2017/8/14.
 * desc:
 */
@Subcomponent(modules = {
        AndroidSupportInjectionModule.class
})
public interface BaseFragmentComponent extends AndroidInjector<BaseFragment> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<BaseFragment> {
    }

}
