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
package com.lz.fram.app;

import android.app.Application;
import android.content.Context;


import com.lz.fram.component.AppComponent;
import com.lz.fram.component.DaggerAppComponent;
import com.lz.fram.model.ConfigModule;
import com.lz.fram.model.GlobalConfigBuild;

import java.util.List;

import io.reactivex.annotations.NonNull;

/**
 * -----------作者----------日期----------变更内容-----
 * -          刘泽      2018-06-28       application的代理类
 */

public class ConfigDelegate implements App {

    private List<ConfigModule> mModules;
    private AppComponent mAppComponent;

    public ConfigDelegate(Context base) {
        mModules = new ManifestParser(base).parse();

    }

    public void init(@NonNull Application application) {
        //dagger 初始化
        initComponet(application);
    }

    private void initComponet(@NonNull Application application) {
        mAppComponent = DaggerAppComponent
                .builder()
                //提供application
                .application(application)
                //全局配置
                .globalConfigModule(getGlobalConfigModule(mModules))
                .build();
    }

    /**
     * 设置全局参数  包括网络请求层
     *
     * @param modules
     * @return GlobalConfigBuild
     */
    private static GlobalConfigBuild getGlobalConfigModule(List<ConfigModule> modules) {
        GlobalConfigBuild.Builder builder = GlobalConfigBuild
                .builder();
        for (ConfigModule module : modules) {
            // 给全局配置 GlobalConfigBuild 添加参数
            module.applyOptions(builder);
        }
        return builder.build();
    }

    /**
     * 获取component
     *
     * @return
     */
    @NonNull
    @Override
    public AppComponent getAppComponent() {
        return mAppComponent;
    }

}

