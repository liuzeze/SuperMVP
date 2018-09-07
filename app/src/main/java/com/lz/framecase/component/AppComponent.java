/*
 * Copyright 2017 JessYan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.lz.framecase.component;


import android.app.Application;

import com.google.gson.Gson;
import com.lz.fram.base.LpLoadDialog;
import com.lz.fram.model.AppModule;
import com.lz.fram.model.ClientModule;
import com.lz.fram.model.GlobalConfigBuild;
import com.lz.framecase.logic.MyApplication;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import retrofit2.Retrofit;


/**
 * -------- 日期 ---------- 维护人 ------------ 变更内容 --------
 * 2017/12/26	9:24	    刘泽			    提供全局参数
 */
@Singleton
@Component(modules = {
        AndroidInjectionModule.class,
        AndroidSupportInjectionModule.class,
        ClientModule.class,
        GlobalConfigBuild.class,

        AppModule.class,
        FragmentProvider.class,
        ActivityProvider.class

})
public interface AppComponent extends AndroidInjector<MyApplication> {

    Application application();

    LpLoadDialog getLPDialog();

    Retrofit getRetrofit();

    //gson
    Gson gson();

    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<MyApplication> {
        @BindsInstance
        public abstract Builder application(Application application);

        public abstract Builder globalConfigModule(GlobalConfigBuild globalConfigModule);

    }
}
