package com.lz.framecase.fragment.presenter


import android.text.TextUtils
import android.webkit.JavascriptInterface
import com.lz.fram.base.RxPresenter
import com.lz.framecase.api.RequestApi
import com.lz.framecase.bean.*
import com.lz.framecase.utils.SettingUtils
import com.lz.utilslib.interceptor.utils.ToastUtils
import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import java.util.ArrayList
import java.util.regex.Pattern
import javax.inject.Inject

/**
 * -------- 日期 ---------- 维护人 ------------ 变更内容 --------
 */
class NewsDetailPresenter @Inject
constructor(var mRequestApi: RequestApi)
    : RxPresenter<NewsDetailContract.View>(), NewsDetailContract.Presenter {


    override fun loadUrl(dataBean: NewsDataBean) {
        val url = dataBean.display_url

        val subscribe: Any = Observable
                .create(ObservableOnSubscribe<String> { emitter ->
                    // https://toutiao.com/group/6530252650288513540/
                    // https://m.toutiao.com/i6530252650288513540/info/
                    val api = url?.replace("www.", "")
                            ?.replace("toutiao", "m.toutiao")
                            ?.replace("group/", "i") + "info/"
                    emitter.onNext(api)
                })
                .subscribeOn(Schedulers.io())
                .switchMap<NewsContentBean> { s -> mRequestApi.getNewsContent(s) }
                .map { o -> getHTML(o) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(Consumer<String?> { r -> mView?.onSetWebView(r, true) }, Consumer<Throwable> { throwable ->
                    mView?.onSetWebView(null, false)
                })
    }

    /**
     * js 通信接口，定义供 JavaScript 调用的交互接口
     * 点击图片启动新的 Activity，并传入点击图片对应的 url 和页面所有图片
     * 对应的 url
     *
     * @param url 点击图片对应的 url
     */
    @JavascriptInterface
    fun openImage(url: String) {
        if (!TextUtils.isEmpty(url)) {
            val list = getAllImageUrlFromHtml(html)
            if (list.size > 0) {
                ToastUtils.info("webview中的图片数量" + list.size)
                mView.onJumpPreview(url, list)
            }
        }
    }

    /***
     * 获取页面所有图片对应的地址对象，
     * 例如 <img src="http://sc1.hao123img.com/data/f44d0aab7bc35b8767de3c48706d429e"></img>
     */
    private val IMAGE_URL_REGEX = "<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>"

    private fun getAllImageUrlFromHtml(html: String): ArrayList<String> {
        val matcher = Pattern.compile(IMAGE_URL_REGEX).matcher(html)
        val imgUrlList = ArrayList<String>()
        while (matcher.find()) {
            val count = matcher.groupCount()
            if (count >= 1) {
                imgUrlList.add(matcher.group(1))
            }
        }
        return imgUrlList
    }

    private var html: String = ""

    fun getHTML(bean: NewsContentBean): String? {
        val title = bean.data?.title
        val content = bean.data?.content
        if (content != null) {

            var css = "<link rel=\"stylesheet\" href=\"file:///android_asset/toutiao_light.css\" type=\"text/css\">"
            if (SettingUtils.getNightMode()) {
                css = css.replace("toutiao_light", "toutiao_dark")
            }

            html = "<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">" +
                    css +
                    "<body>\n" +
                    "<article class=\"article-container\">\n" +
                    "    <div class=\"article__content article-content\">" +
                    "<h1 class=\"article-title\">" +
                    title +
                    "</h1>" +
                    content +
                    "    </div>\n" +
                    "</article>\n" +
                    "</body>\n" +
                    "</html>"

            return html
        } else {
            return null
        }
    }
}