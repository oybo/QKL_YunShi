package com.qkl.online.mining.app.ui.view.browse;


import android.net.http.SslError;
import android.support.annotation.NonNull;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;

import java.util.Map;

public abstract class CustomWebViewClient extends BridgeWebViewClient {

    public CustomWebViewClient(BridgeWebView webView) {
        super(webView);
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        if (onPageHeaders(url) != null) {
            view.loadUrl(url, onPageHeaders(url));
        }
        return super.shouldOverrideUrlLoading(view, url);
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
    }

    @Override
    public void onReceivedError(WebView view, int errorCode,
                                String description, String failingUrl) {
        view.loadUrl(onPageError(failingUrl));
    }

    @Override
    public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
        handler.proceed();  // 接受所有网站的证书
        super.onReceivedSslError(view, handler, error);
    }

    /**
     * return errorUrl
     * @param url
     * @return
     */
    public abstract String onPageError(String url);

    /**
     * HttpHeaders
     * return
     * @return
     */
    @NonNull
    public abstract Map<String, String> onPageHeaders(String url);

}
