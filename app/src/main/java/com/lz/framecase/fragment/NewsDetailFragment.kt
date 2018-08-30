package com.lz.framecase.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.TextUtils
import android.view.KeyEvent
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import com.lz.framecase.R
import com.lz.framecase.activity.NewDetailActivity.Companion.IMG
import com.lz.framecase.activity.NewDetailActivity.Companion.TAG
import com.lz.framecase.base.BaseFragment
import com.lz.framecase.bean.NewsDataBean
import com.lz.framecase.fragment.presenter.NewsDetailContract
import com.lz.framecase.fragment.presenter.NewsDetailPresenter
import com.lz.inject_annotation.InjectComponet
import kotlinx.android.synthetic.main.fragment_news_detail.*

/**
 * -----------作者----------日期----------变更内容-----
 * -          刘泽      2018-08-30       创建class
 */
@InjectComponet
class NewsDetailFragment : BaseFragment<NewsDetailPresenter>(), NewsDetailContract.View {
    var newsDataBean: NewsDataBean? = null

    var shareUrl = ""
    var shareTitle = ""
    var mediaName = ""
    /**
     * 这段js函数的功能就是，遍历所有的img几点，并添加onclick函数，函数的功能是在图片点击的时候调用本地java接口并传递url过去
     * [com.meiji.toutiao.module.news.content.NewsContentPresenter.openImage]
     */
    val JS_INJECT_IMG = ("javascript:(function(){" +
            "var objs = document.getElementsByTagName(\"img\"); " +
            "for(var i=0;i<objs.length;i++)  " +
            "{"
            + "    objs[i].onclick=function()  " +
            "    {  "
            + "        window.imageListener.openImage(this.src);  " +
            "    }  " +
            "}" +
            "})()")
    val USER_AGENT_PC = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36"

    override fun getLayout(): Int {
        return R.layout.fragment_news_detail
    }

    companion object {
        fun getInstance(bean: NewsDataBean, s: String?): NewsDetailFragment {
            val newsListFragment = NewsDetailFragment()
            val bundle = Bundle();
            bundle.putSerializable(TAG, bean)
            if (s != null) {
                bundle.putString(IMG, s)
            }
            newsListFragment.arguments = bundle
            return newsListFragment
        }
    }

    @SuppressLint("JavascriptInterface")
    override fun init() {

        val settings = webview.getSettings()
        settings.setJavaScriptEnabled(true)
        // 缩放,设置为不能缩放可以防止页面上出现放大和缩小的图标
        settings.setBuiltInZoomControls(false)
        // 缓存
        settings.setCacheMode(WebSettings.LOAD_DEFAULT)
        // 开启DOM storage API功能
        settings.setDomStorageEnabled(true)
        // 开启application Cache功能
        settings.setAppCacheEnabled(true)
        // 判断是否为无图模式
//        settings.setBlockNetworkImage(SettingUtil.getInstance().getIsNoPhotoMode())
        // 不调用第三方浏览器即可进行页面反应
        webview.setWebViewClient(object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                if (!TextUtils.isEmpty(url)) {
                    view.loadUrl(url)
                }
                return true
            }

            override fun onPageFinished(view: WebView, url: String) {
                pb_progress.hide()
                // 注入 js 函数监听
                view.loadUrl(JS_INJECT_IMG)
                view.loadUrl("javascript:next()");
                super.onPageFinished(view, url)
            }
        })

        webview.setOnKeyListener({ view, i, keyEvent ->
            if (keyEvent.getKeyCode() == KeyEvent.KEYCODE_BACK && webview.canGoBack()) {
                webview.goBack()
                true
            }
            false
        })

        webview.setWebChromeClient(object : WebChromeClient() {
            override fun onProgressChanged(view: WebView, newProgress: Int) {
                super.onProgressChanged(view, newProgress)
                try {
                    if (newProgress >= 90) {
                        pb_progress.hide()
                    } else {
                        pb_progress.show()
                    }
                } catch (e: Exception) {
                }
            }
        })
        webview.addJavascriptInterface(mPresenter, "imageListener")



        try {
            newsDataBean = arguments?.getSerializable(TAG) as NewsDataBean?

            shareUrl = newsDataBean?.share_url ?: newsDataBean?.display_url!!
            shareTitle = newsDataBean?.title!!
            mediaName = newsDataBean?.media_name!!

            mPresenter.doLoadData(newsDataBean!!)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    override fun onSetWebView(url: String?, b: Boolean) {
// 是否为头条的网站
        if (b) {
            webview.loadDataWithBaseURL(null, url, "text/html", "utf-8", null)
        } else {
            /*
               ScrollView 嵌套 WebView, 导致部分网页无法正常加载
               如:https://temai.snssdk.com/article/feed/index/?id=11754971
               最佳做法是去掉 ScrollView, 或使用 NestedScrollWebView
             */
            if (shareUrl.contains("temai.snssdk.com")) {
                webview.getSettings().setUserAgentString(USER_AGENT_PC)
            }
            webview.loadUrl(shareUrl)
        }
        // webview.loadUrl(newsDataBean.share_url ?: newsDataBean.display_url)

    }


}
