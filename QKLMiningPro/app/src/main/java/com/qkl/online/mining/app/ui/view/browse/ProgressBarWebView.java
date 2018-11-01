package com.qkl.online.mining.app.ui.view.browse;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.WebSettings;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.Map;

/**
 * 带有进度条的 WebView
 */
public class ProgressBarWebView extends RelativeLayout {

    private NumberProgressBar mProgressBar;
    private BridgeWebView mWebView;
    private CustomWebChromeClient mCustomWebChromeClient;

    public ProgressBarWebView(Context context) {
        super(context);
        init(context, null);
    }

    public ProgressBarWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ProgressBarWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
//        setOrientation(LinearLayout.VERTICAL);

        // 初始化进度条
        if (mProgressBar == null) {
            mProgressBar = new NumberProgressBar(context, attrs);
        }

        // 初始化webview
        if (mWebView == null) {
            mWebView = new BridgeWebView(context);
        }

        mCustomWebChromeClient = new CustomWebChromeClient(mProgressBar);
        mWebView.setWebChromeClient(mCustomWebChromeClient);
        WebSettings webviewSettings = mWebView.getSettings();
        // 判断系统版本是不是5.0或之上
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //让系统不屏蔽混合内容和第三方Cookie
            CookieManager.getInstance().setAcceptThirdPartyCookies(mWebView, true);
            webviewSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        // 不支持缩放
        webviewSettings.setSupportZoom(false);

        // 自适应屏幕大小
        webviewSettings.setUseWideViewPort(true);
        webviewSettings.setLoadWithOverviewMode(true);
        mWebView.setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return true;
            }
        });
        //
        mWebView.setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK && mWebView.canGoBack()) {
                        mWebView.goBack();
                        return true;
                    }
                }
                return false;
            }
        });

        addView(mWebView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        addView(mProgressBar);
    }

    /**
     * 返回上一级
     * @return  是否可以返回
     */
    public boolean goBack() {
        if (mWebView.canGoBack()) {
            mWebView.goBack();
            return true;
        }
        return false;
    }

    public NumberProgressBar getProgressBar() {
        return mProgressBar;
    }


    public BridgeWebView getWebView() {
        return mWebView;
    }

    /**
     * Loads the given URL.
     *
     * @param url the URL of the resource to load
     */
    public void loadUrl(String url) {
      loadUrl(url, null);
    }

    /**
     * Loads the given URL with the specified additional HTTP headers.
     *
     * @param url                   the URL of the resource to load
     * @param additionalHttpHeaders the additional headers to be used in the
     *                              HTTP request for this URL, specified as a map from name to
     *                              value. Note that if this map contains any of the headers
     *                              that are set by default by this WebView, such as those
     *                              controlling caching, accept types or the User-Agent, their
     *                              values may be overriden by this WebView's defaults.
     */
    public void loadUrl(String url, Map<String, String> additionalHttpHeaders) {
        loadUrl(url, additionalHttpHeaders, null);
    }

    /**
     * Loads the given URL with the specified additional HTTP headers.
     *
     * @param url                   the URL of the resource to load
     * @param additionalHttpHeaders the additional headers to be used in the
     *                              HTTP request for this URL, specified as a map from name to
     *                              value. Note that if this map contains any of the headers
     *                              that are set by default by this WebView, such as those
     *                              controlling caching, accept types or the User-Agent, their
     *                              values may be overriden by this WebView's defaults.
     * @param returnCallback        the CallBackFunction to be Used call js registerHandler Function,
     *                              rerurn response data.
     */
    public void loadUrl(String url, Map<String, String> additionalHttpHeaders, CallBackFunction returnCallback) {
        mWebView.loadUrl(url, additionalHttpHeaders, returnCallback);
    }

    public void setWebViewClient(CustomWebViewClient client) {
        mWebView.setWebViewClient(client);
    }

    public void setWebChromeClient(CustomWebChromeClient chromeClient) {
        mWebView.setWebChromeClient(chromeClient);
    }

    /**
     * @param handler default handler,handle messages send by js without assigned handler name,
     *                if js message has handler name, it will be handled by named handlers registered by native
     */
    public void setDefaultHandler(BridgeHandler handler) {
        mWebView.setDefaultHandler(handler);
    }

    public void send(String data) {
        mWebView.send(data);
    }

    public void send(String data, CallBackFunction responseCallback) {
        mWebView.send(data, responseCallback);
    }

    /**
     * 注册本地java方法，以供js端调用
     *
     * @param handlerName 方法名称
     * @param handler     回调接口
     */
    public void registerHandler(final String handlerName, final JsHandler handler) {
        mWebView.registerHandler(handlerName, new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                if (handler != null) {
                    handler.OnHandler(handlerName, data, function);
                }
            }
        });
    }

    /**
     * 批量注册本地java方法，以供js端调用
     *
     * @param handlerNames 方法名称数组
     * @param handler      回调接口
     */
    public void registerHandlers(final ArrayList<String> handlerNames, final JsHandler handler) {
        if (handler != null) {
            for (final String handlerName : handlerNames) {
                mWebView.registerHandler(handlerName, new BridgeHandler() {
                    @Override
                    public void handler(String data, CallBackFunction function) {
                        handler.OnHandler(handlerName, data, function);
                    }
                });
            }
        }
    }

    /**
     * 调用js端已经注册好的方法
     *
     * @param handlerName 方法名称
     * @param javaData    本地端传递给js端的参数，json字符串
     * @param handler     回调接口
     */
    public void callHandler(final String handlerName, String javaData, final JavaCallHandler handler) {
        mWebView.callHandler(handlerName, javaData, new CallBackFunction() {
            @Override
            public void onCallBack(String data) {
                if (handler != null) {
                    handler.OnHandler(handlerName, data);
                }
            }
        });
    }

    /**
     * 批量调用js端已经注册好的方法
     *
     * @param handlerInfos 方法名称与参数的map，方法名为key
     * @param handler      回调接口
     */
    public void callHandler(final Map<String, String> handlerInfos, final JavaCallHandler handler) {
        if (handler != null) {
            for (final Map.Entry<String, String> entry : handlerInfos.entrySet()) {
                mWebView.callHandler(entry.getKey(), entry.getValue(), new CallBackFunction() {
                    @Override
                    public void onCallBack(String data) {
                        handler.OnHandler(entry.getKey(), data);
                    }
                });
            }
        }
    }

    /**
     * 设置是否拦截新开一个浏览器页面
     * @param intercept
     */
    public void setInterceptUrl(boolean intercept) {
        if(mWebView != null) {
            mWebView.setInterceptUrl(intercept);
        }
    }

    /**
     * 设置监听文件下载
     * @param listener
     */
    public void setDownloadListener(OnWebViewDownloadListener listener) {
        if(listener != null) {
            mWebView.setWebViewListener(listener);
        }
    }

    /**
     * 设置监听获取浏览器标题
     * @param listener
     */
    public void setWebViewTitleListener(OnWebViewListener listener) {
        if(listener != null) {
            mCustomWebChromeClient.setWebViewTitleListener(listener);
        }
    }

    /**
     * webview文件下载
     */
    public interface OnWebViewDownloadListener {
        void onDownloadRequested(String url, String suggestedFilename, String mimeType, long contentLength, String contentDisposition, String userAgent);
    }

    /**
     * webview各事件监听
     */
    public interface OnWebViewListener {
        void onReceivedTitle(String title);
        void onLoadFinish();
    }

}
