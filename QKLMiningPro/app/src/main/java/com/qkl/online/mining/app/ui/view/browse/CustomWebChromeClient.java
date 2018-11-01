package com.qkl.online.mining.app.ui.view.browse;

import android.view.View;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

public class CustomWebChromeClient extends WebChromeClient {

    private NumberProgressBar mProgressBar;
    private final static int DEF = 95;

    public CustomWebChromeClient(NumberProgressBar progressBar) {
        this.mProgressBar = progressBar;
    }

    @Override
    public void onProgressChanged(WebView view, int newProgress) {
        if (newProgress >= DEF) {
            mProgressBar.setVisibility(View.GONE);
            if (mWebViewTitleListener != null) {
                mWebViewTitleListener.onLoadFinish();
            }
        } else {
            if (mProgressBar.getVisibility() == View.GONE) {
                mProgressBar.setVisibility(View.VISIBLE);
            }

            mProgressBar.setProgress(newProgress);
        }
        super.onProgressChanged(view, newProgress);
    }

    @Override
    public void onReceivedTitle(WebView view, String title) {
        super.onReceivedTitle(view, title);
        if (mWebViewTitleListener != null) {
            mWebViewTitleListener.onReceivedTitle(title);
        }
    }

    @Override
    public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
        return super.onJsAlert(view, url, message, result);
    }

    private ProgressBarWebView.OnWebViewListener mWebViewTitleListener;

    /**
     * 设置监听获取浏览器标题
     * @param listener
     */
    public void setWebViewTitleListener(ProgressBarWebView.OnWebViewListener listener) {
        mWebViewTitleListener = listener;
    }

}
