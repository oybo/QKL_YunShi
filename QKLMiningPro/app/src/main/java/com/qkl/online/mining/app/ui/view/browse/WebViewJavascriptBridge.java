package com.qkl.online.mining.app.ui.view.browse;


public interface WebViewJavascriptBridge {

    public void send(String data);

    public void send(String data, CallBackFunction responseCallback);

}
