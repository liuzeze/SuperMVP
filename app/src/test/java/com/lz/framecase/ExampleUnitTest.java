package com.lz.framecase;

import android.widget.Button;
import android.widget.TextView;

import com.lz.fram.app.FrameApplication;
import com.lz.fram.app.ManifestParser;
import com.lz.fram.component.AppComponent;
import com.lz.fram.component.DaggerAppComponent;
import com.lz.fram.model.AppModule;
import com.lz.fram.model.ConfigModule;
import com.lz.fram.model.GlobalConfigBuild;
import com.lz.framecase.activity.MainActivity;
import com.lz.framecase.api.RequestApi;
import com.lz.framecase.component.DaggerFragmentComponent;
import com.lz.framecase.fragment.NewsListFragment;
import com.lz.framecase.fragment.presenter.NewsListPresenter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.android.controller.FragmentController;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.support.v4.SupportFragmentTestUtil;

import java.util.logging.Logger;

import retrofit2.Retrofit;

import static org.junit.Assert.*;

/**
 * ExampleActivity local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE, application = FrameApplication.class)
public class ExampleUnitTest {

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }


    /**
     * 在测试前的数据初始化
     */
    @Before
    public void setUp() { //Mockito的初始化


    }


    @Test
    public void request() throws Exception {
        NewsListFragment sampleFragment = new NewsListFragment();
        //此api可以主动添加Fragment到Activity中，因此会触发Fragment的onCreateView()


        GlobalConfigBuild.Builder builder = GlobalConfigBuild
                .builder();
        for (ConfigModule module : new ManifestParser(FrameApplication.mApplication).parse()) {
            // 给全局配置 GlobalConfigBuild 添加参数
            module.applyOptions(builder);
        }
        ;
        AppComponent build = DaggerAppComponent
                .builder()
                //提供application
                .application(FrameApplication.mApplication)
                //全局配置
                .globalConfigModule(builder.build())
                .build();

        DaggerFragmentComponent.builder()
                .appComponent(build)
                .build()
                .inject(sampleFragment);
        sampleFragment.mPresenter.getNewLists("");
    }
}