package com.lz.framecase.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.view.KeyEvent
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import com.bumptech.glide.Glide
import com.lz.framecase.R
import com.lz.framecase.activity.NewDetailActivity.Companion.IMG
import com.lz.framecase.activity.NewDetailActivity.Companion.TAG
import com.lz.framecase.base.BaseFragment
import com.lz.framecase.bean.NewsDataBean
import com.lz.framecase.fragment.presenter.NewsDetailContract
import com.lz.framecase.fragment.presenter.NewsDetailPresenter
import com.lz.inject_annotation.InjectComponet
import kotlinx.android.synthetic.main.fragment_news_detail.*
import android.provider.Telephony.MmsSms.PendingMessages.ERROR_TYPE
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.View
import com.gyf.barlibrary.ImmersionBar
import com.jakewharton.rxbinding2.widget.RxToolbar
import com.lz.framecase.R.id.*
import com.lz.framecase.activity.ImagePreviewActivity
import com.lz.framecase.activity.NewsCommentActivity
import com.lz.skinlibs.SkinManager
import com.lz.utilslib.interceptor.utils.ShareAction
import com.lz.utilslib.interceptor.utils.SnackbarUtils
import com.vondear.rxtool.RxWebViewTool
import java.util.ArrayList


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
    var mediaUrl = ""
    var mediaId = ""
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
        initView()
        initLIstener()
        initDarta()

    }

    private fun initDarta() {
        try {
            newsDataBean = arguments?.getSerializable(TAG) as NewsDataBean?

            shareUrl = newsDataBean?.share_url ?: newsDataBean?.display_url!!
            shareTitle = newsDataBean?.title!!
            mediaName = newsDataBean?.media_name!!
            mediaUrl = "http://toutiao.com/m" + newsDataBean?.media_info?.media_id
            mediaId = newsDataBean?.media_info?.media_id!!

            refresh_layout.setRefreshing(true)
            collapsing_toolbar.title = shareTitle
            mPresenter.loadUrl(newsDataBean!!)
            val img = arguments?.getString(IMG)
            if (!TextUtils.isEmpty(img)) {
                iv_image.visibility = View.VISIBLE
            } else {
                iv_image.visibility = View.GONE
                val instance = SkinManager.getInstance()
                app_bar_layout.setBackgroundColor(instance.resourceManager?.getColor("app_them")!!)
            }
            Glide.with(mContext).load(img).into(iv_image)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    @SuppressLint("JavascriptInterface")
    private fun initLIstener() {
        // 不调用第三方浏览器即可进行页面反应
        webview.setWebViewClient(object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                if (!TextUtils.isEmpty(url)) {
                    view.loadUrl(url)
                }
                return true
            }

            override fun onPageFinished(view: WebView, url: String) {
                refresh_layout?.setRefreshing(false)

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
                        refresh_layout.setRefreshing(false)

                    } else {
                        refresh_layout.setRefreshing(true)

                    }
                } catch (e: Exception) {
                }
            }
        })
        webview.addJavascriptInterface(mPresenter, "imageListener")
        refresh_layout.setOnRefreshListener {
            mPresenter.loadUrl(newsDataBean!!)
        }
        toolbar.setNavigationOnClickListener {
            activity?.onBackPressed()
        }
        toolbar.setOnMenuItemClickListener { item ->
            val id = item.itemId
            when (id) {
                R.id.action_open_comment -> {
                    val intent = Intent(mContext, NewsCommentActivity::class.java)
                    intent.putExtra("group_id", newsDataBean?.group_id.toString())
                    intent.putExtra("item_id", newsDataBean?.item_id)
                    startActivity(intent)
                }
                R.id.action_share -> ShareAction.send(mContext, shareTitle + "\n" + shareUrl)

                R.id.action_open_in_browser -> startActivity(Intent(Intent.ACTION_VIEW).setData(Uri.parse(shareUrl)))


                R.id.action_open_media_home -> {
                    SnackbarUtils.show(toolbar, "未开发")
//                MediaHomeActivity.launch(mediaId)
                }
            }
            false
        }
    }

    private fun initView() {
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

        toolbar.setTitleTextColor(Color.WHITE)
        toolbar.setNavigationIcon(R.drawable.ic_white_back)
        toolbar.inflateMenu(R.menu.menu_browser)
        activity?.actionBar?.setDisplayHomeAsUpEnabled(true)

    }

    override fun onSetWebView(url: String?, b: Boolean) {
        refresh_layout.setRefreshing(false)

// 是否为头条的网站
        if (b) {
            webview.loadDataWithBaseURL(null, url, "text/html", "utf-8", null)
        } else {

            if (shareUrl.contains("temai.snssdk.com")) {
                webview.getSettings().setUserAgentString(USER_AGENT_PC)
            }
            webview.loadUrl(shareUrl)
        }

    }

    override fun onJumpPreview(url: String, list: ArrayList<String>) {
        if (list.size == 0) {
            return
        }
        val intent = Intent(mContext, ImagePreviewActivity::class.java)
        intent.putExtra("url", url)
        intent.putStringArrayListExtra("urlList", list)
        startActivity(intent)


    }

    override fun showErrorMsg(msg: String?) {
        super.showErrorMsg(msg)
        refresh_layout.setRefreshing(false)

    }


}
