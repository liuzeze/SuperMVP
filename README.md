# 技术热点

1. Dagger 模式解耦 
2. Retrofit Rxjava RxAndroid    网络框架还管理以及响应式编程类库
3. Butterknife  (也可以使用kotlin  和databing 替换butterknife使用)
4. fragmention  fragment管理以及实现侧滑功能
5. rxpermissions 权限管理
6.  Skin 插件  鸿洋大神的换肤类库,自己再其基础上扩展了一些控件的兼容
7. 日志拦截可视化显示
8. 使用apt编译时生成代码  减少重复代码

## 使用方式

1. 依赖mvp核心类库

   ```
   //日志拦截库  是基于okhttp的拦截机制
   //1.调用 SuspensionView.getInstance().init(this)开启拦截功能,
   //2.需要给okhttp传入网络拦截builder.addNetworkInterceptor(newOkNetworkMonitorInterceptor());
   
   debugApi 'com.lz:intercept_debug:1.0.2'
   releaseApi 'com.lz:intercept_release:1.0.0'
   
   //封装了mvp的基础代码
   api 'com.lz:fram_lib:1.0.1'
   
   //注解库 ,用于减少dagger库连接acticity/fragment 的inject方式,
   需要在使用到dagger的类上使用InjectActivity/InjectFragment/InjectUtis 来注解当前class 
   会在编译时自动生成关联代码
   //注解库
   api 'com.lz:inject_annotations:1.0.4'
   //apt注解工具
   kapt 'com.lz:inject_compile:1.0.4'
   
   //fragment管理  和侧滑退出类库
   api 'me.yokeyword:fragmentation:1.3.5'
   api 'me.yokeyword:fragmentation-swipeback:1.3.6'
   
   //其他功能类库可自行添加
   
   
   ```

1. 配置框架全局参数  GlobalConfiguration 

   ```
   //在manifest里配置 全局参数配置class 路径   参数设置参照 GlobalConfiguration 这个类
   <!-- 网络框架配置 -->
   <meta-data
       android:name="com.lz.framecase.logic.GlobalConfiguration"
       android:value="ConfigModule" />
   ```
1. 创建Component 连接@module 注解类提供的资源对象
   ```
    @CustomizeScope
    @Component(dependencies = AppComponent.class)
    public interface FragmentComponent {
         void inject(ImagePagerFragment fragment);
     }

    ```
   

1. 继承BaseActivity/BaseFragment  基类  重写两个方法

   ```
   //Dagger 关联activity的注解
   @InjectActivity
   public class MainActivity2 extends BaseActivity {
   
       @Inject   //dagger注解
       @AttachView  // 为presenter 注入页面view的注解
       Main2Presenter mPresenter;
   
       @Override
       protected int getLayout() {
       //布局文件设置
           return R.layout.activity_main2;
       }
   
   
       @Override
       protected void init() {
       //初始化操作
           Button button = (Button) findViewById(R.id.button);
           button.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   mPresenter.getNewLists();
               }
           });
       }
   }
   ```
   
   
1. 基于databinding 实现 选择器以及圆角背景在xml中的动态设置 ,省去了自己创建select  和 shape的项目里文件

   ```
              <layout>
     <!--选择器使用方式-->
                    <ImageView
                           //默认颜色
                            bind_def_color="@{@color/common_app_red_ff0000}"
                            //选中原色
                            bind_sel_color="@{@color/common_app_green_37fc00}"
                            //圆角大小
                            bind_sel_radius="@{10}"
 
                            android:layout_width="wrap_content"
                            android:layout_height="wrap_content"
                            android:layout_gravity="right"
                            android:padding="@dimen/dp_10"
                            android:src="@drawable/ic_search"
                            android:transitionName="SearchView"
                            app:layout_constraintEnd_toEndOf="parent" />
    <!--选圆角背景使用-->
                          <ImageView
                           //颜色
                            bind_shape_color="@{@color/common_app_red_ff0000}"
                            //圆角大小
                            bind_shape_radius="@{10}"
              
                            android:layout_width="wrap_content"
                            android:layout_height="wrap_content"
                            android:layout_gravity="right"
                            android:padding="@dimen/dp_10"
                            android:src="@drawable/ic_search"
                            android:transitionName="SearchView"
                            app:layout_constraintEnd_toEndOf="parent" />
                </layout>
   ```


