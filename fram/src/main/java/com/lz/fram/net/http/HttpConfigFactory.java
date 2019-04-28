package com.lz.fram.net.http;

import android.content.Context;

import java.util.List;

/**
 * -----------作者----------日期----------变更内容-----
 * -          刘泽      2018-10-18       创建class
 */
public class HttpConfigFactory {

    private static HttpConfigFactory instance;
    private GlobalConfigBuild mConfigBuild;
    private ConfigModule[] mOptions;

    public static HttpConfigFactory getInstance() {
        if (instance == null) {
            synchronized (RetrofitFactory.class) {
                if (instance == null) {
                    instance = new HttpConfigFactory();
                }
            }
        }
        return instance;
    }


    public GlobalConfigBuild initConfig(Context context) {
        List<ConfigModule> parse = new ManifestParser(context).parse();
        GlobalConfigBuild.Builder builder = GlobalConfigBuild
                .builder();
        for (ConfigModule options : parse) {
            options.applyOptions(builder);
        }
        mConfigBuild = builder.build();
        return mConfigBuild;
    }

    public GlobalConfigBuild initConfig(ConfigModule... options) {
        mOptions = options;
        GlobalConfigBuild.Builder builder = GlobalConfigBuild
                .builder();
        if (options != null) {
            for (ConfigModule option : options) {
                option.applyOptions(builder);
            }
        }
        mConfigBuild = builder.build();
        return mConfigBuild;
    }

    public GlobalConfigBuild getConfigBuild() {
        if (mConfigBuild == null) {
            initConfig();
        }
        return mConfigBuild;
    }
}
