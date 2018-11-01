package com.qkl.online.mining.app.application;

import android.app.Activity;
import android.app.Application;
import android.content.res.Configuration;
import android.os.Bundle;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.cookie.CookieJarImpl;
import com.lzy.okgo.cookie.store.SPCookieStore;
import com.lzy.okgo.model.HttpHeaders;
import com.orhanobut.hawk.Hawk;
import com.qkl.online.mining.app.utils.ToastUtils;
import com.qkl.online.mining.app.utils.languagelib.MultiLanguageUtil;
import com.tencent.bugly.crashreport.CrashReport;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import skin.support.SkinCompatManager;
import skin.support.app.SkinCardViewInflater;
import skin.support.constraint.app.SkinConstraintViewInflater;
import skin.support.design.app.SkinMaterialViewInflater;

/**
 * author：oyb on 2018/7/4 15:26
 */
public class AppContext extends Application {

    private static AppContext mInstance;

    public static AppContext getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;

        //SharedPreferences
        Hawk.init(this).build();
        // 网络请求库
        initOkGo();
        // 换肤框架-夜日间切换
        initNightDay();
        // 多语言
        MultiLanguageUtil.init(this);

        CrashReport.initCrashReport(getApplicationContext(), "13a4a47be8", false);
    }

    private void initNightDay() {
        SkinCompatManager.withoutActivity(this)                         // 基础控件换肤初始化
                .addInflater(new SkinMaterialViewInflater())            // material design 控件换肤初始化[可选]
                .addInflater(new SkinConstraintViewInflater())          // ConstraintLayout 控件换肤初始化[可选]
                .addInflater(new SkinCardViewInflater())                // CardView v7 控件换肤初始化[可选]
                .setSkinStatusBarColorEnable(false)                     // 关闭状态栏换肤，默认打开[可选]
                .setSkinWindowBackgroundEnable(false)                   // 关闭windowBackground换肤，默认打开[可选]
                .loadSkin();
    }

    /**
     * 初始化网络请求库
     */
    private void initOkGo() {
        HttpHeaders headers = new HttpHeaders();
        headers.put("Content-Type", "application/json");
//        HttpParams params = new HttpParams();
//        params.put("commonParamsKey1", "commonParamsValue1");     //param支持中文,直接传,不要自己编码
//        params.put("commonParamsKey2", "这里支持中文参数");

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        //全局的读取超时时间
        builder.readTimeout(10000, TimeUnit.MILLISECONDS);
        //全局的写入超时时间
        builder.writeTimeout(10000, TimeUnit.MILLISECONDS);
        //全局的连接超时时间
        builder.connectTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);
        //使用sp保持cookie，如果cookie不过期，则一直有效
        builder.cookieJar(new CookieJarImpl(new SPCookieStore(this)));

        OkGo.getInstance().init(this)                       //必须调用初始化
                .setOkHttpClient(builder.build())               //建议设置OkHttpClient，不设置将使用默认的
                .setCacheMode(CacheMode.NO_CACHE)               //全局统一缓存模式，默认不使用缓存，可以不传
                .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)   //全局统一缓存时间，默认永不过期，可以不传
                .setRetryCount(2)                               //全局统一超时重连次数，默认为三次，那么最差的情况会请求4次(一次原始请求，三次重连请求)，不需要可以设置为0
                .addCommonHeaders(headers)                      //全局公共头
//                .addCommonParams(params)                       //全局公共参数
                ;

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        MultiLanguageUtil.getInstance().setConfiguration();
    }

}
