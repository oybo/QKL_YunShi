package com.qkl.online.mining.app.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;

import com.qkl.online.mining.app.data.entity.Exchange;
import com.qkl.online.mining.app.data.entity.HomeNews;
import com.qkl.online.mining.app.data.entity.StarProduct;
import com.qkl.online.mining.app.mvp.presenter.EarningsPresenter;
import com.qkl.online.mining.app.ui.activity.ExchangeDetailActivity;
import com.qkl.online.mining.app.ui.activity.ExchangeListActivity;
import com.qkl.online.mining.app.ui.activity.ForgetPasswordActivity;
import com.qkl.online.mining.app.ui.activity.ForgetPayPasswordActivity;
import com.qkl.online.mining.app.ui.activity.GameWebViewActivity;
import com.qkl.online.mining.app.ui.activity.InviteNodeActivity;
import com.qkl.online.mining.app.ui.activity.JieDianEarningsActivity;
import com.qkl.online.mining.app.ui.activity.KefuActivity;
import com.qkl.online.mining.app.ui.activity.LoginActivity;
import com.qkl.online.mining.app.ui.activity.MainActivity;
import com.qkl.online.mining.app.ui.activity.MyTeamActivity;
import com.qkl.online.mining.app.ui.activity.NewsDetailActivity;
import com.qkl.online.mining.app.ui.activity.PurchaseStarActivity;
import com.qkl.online.mining.app.ui.activity.RegisterUserActivity;
import com.qkl.online.mining.app.ui.activity.SetActivity;
import com.qkl.online.mining.app.ui.activity.SetLanguageActivity;
import com.qkl.online.mining.app.ui.activity.SetUserInfoActivity;
import com.qkl.online.mining.app.ui.activity.SetYunActivity;
import com.qkl.online.mining.app.ui.activity.UpdatePasswordActivity;
import com.qkl.online.mining.app.ui.activity.UpdatePayPasswordActivity;
import com.qkl.online.mining.app.ui.activity.WebViewActivity;

import java.io.Serializable;

/**
 * Created by oyb on 2018/7/2.
 * intent跳转类
 */

public class IntentUtil {

    public static final int ToSetLanguageActivityCode = 1001;

    /**
     * 登录页面
     * @param context
     */
    public static void ToLoginActivity(Activity context){
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    /**
     * 跳转到主页面
     * @param context
     */
    public static void ToMainActivity(Activity context){
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    /**
     * 跳转到注册用户界面
     * @param context
     */
    public static void ToRegisterUserActivity(Activity context){
        Intent intent = new Intent(context, RegisterUserActivity.class);
        context.startActivity(intent);
    }

    /**
     * 忘记密码
     * @param context
     */
    public static void ToForgetPasswordActivity(Activity context){
        Intent intent = new Intent(context, ForgetPasswordActivity.class);
        context.startActivity(intent);
    }

    /**
     * 忘记支付密码
     * @param context
     */
    public static void ToForgetPayPasswordActivity(Activity context){
        Intent intent = new Intent(context, ForgetPayPasswordActivity.class);
        context.startActivity(intent);
    }

    /**
     * 浏览器页面
     * @param context
     * @param title
     * @param url
     */
    public static void ToWebViewActivity(Activity context, String title, String url){
        ToWebViewActivity(context, title, url, false);
    }

    /**
     * 浏览器页面
     * @param context
     * @param title
     * @param url
     */
    public static void ToWebViewActivity(Activity context, String title, String url, boolean header){
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("url", url);
        if(header) {
            intent.putExtra("header", header);
        }
        context.startActivity(intent);
    }

    /**
     * 跳转到浏览器
     * @param context
     * @param url
     */
    public static void goBrowser(Context context, String url) {
        if(!TextUtils.isEmpty(url)) {
            try {
                // 系统自带浏览器
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                intent.setData(Uri.parse(url));
                intent.setClassName("com.android.browser", "com.android.browser.BrowserActivity");
                context.startActivity(intent);
            } catch (Exception e) {
                e.printStackTrace();
                // 自选
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                intent.setData(Uri.parse(url));
                context.startActivity(intent);
            }
        }
    }

    /**
     * 浏览器页面-游戏
     * @param context
     * @param title
     * @param url
     */
    public static void ToGameWebViewActivity(Activity context, String title, String url){
        Intent intent = new Intent(context, GameWebViewActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("url", url);
        context.startActivity(intent);
    }

    /**
     * 个人信息设置
     * @param context
     */
    public static void ToSetUserInfoActivity(Activity context){
        Intent intent = new Intent(context, SetUserInfoActivity.class);
        context.startActivity(intent);
    }

    /**
     * 邀请节点页面
     * @param context
     */
    public static void ToInviteNodeActivity(Activity context){
        Intent intent = new Intent(context, InviteNodeActivity.class);
        context.startActivity(intent);
    }

    /**
     * 我的团队页面
     * @param context
     */
    public static void ToMyTeamActivity(Activity context){
        Intent intent = new Intent(context, MyTeamActivity.class);
        context.startActivity(intent);
    }

    /**
     * 购买星球页面
     * @param context
     */
    public static void ToPurchaseStarActivity(Activity context, StarProduct.ListEntity listEntity){
        Intent intent = new Intent(context, PurchaseStarActivity.class);
        intent.putExtra("listEntity", (Serializable) listEntity);
        context.startActivity(intent);
    }

    /**
     * 修改密码
     * @param context
     */
    public static void ToUpdatePasswordActivity(Activity context){
        Intent intent = new Intent(context, UpdatePasswordActivity.class);
        context.startActivity(intent);
    }

    /**
     * 修改支付密码
     * @param context
     */
    public static void ToUpdatePayPasswordActivity(Activity context){
        Intent intent = new Intent(context, UpdatePayPasswordActivity.class);
        context.startActivity(intent);
    }

    /**
     * 设置页面
     * @param context
     */
    public static void ToSetActivity(Activity context){
        Intent intent = new Intent(context, SetActivity.class);
        context.startActivity(intent);
    }

    /**
     * 设置语言页面
     * @param context
     */
    public static void ToSetLanguageActivity(Activity context){
        Intent intent = new Intent(context, SetLanguageActivity.class);
        context.startActivityForResult(intent, ToSetLanguageActivityCode);
    }

    /**
     * 创建钱包地址
     * @param context
     */
    public static void ToSetYunActivity(Activity context){
        Intent intent = new Intent(context, SetYunActivity.class);
        context.startActivity(intent);
    }

    /**
     * 兑换记录
     * @param context
     */
    public static void ToExchangeListActivity(Activity context, int index){
        Intent intent = new Intent(context, ExchangeListActivity.class);
        intent.putExtra("index", index);
        context.startActivity(intent);
    }

    /**
     * 兑换详情记录
     * @param context
     */
    public static void ToExchangeDetailActivity(Activity context, Exchange.ListBean listBean, String type){
        Intent intent = new Intent(context, ExchangeDetailActivity.class);
        intent.putExtra("listBean", listBean);
        intent.putExtra("type", type);
        context.startActivity(intent);
    }

    /**
     * 矿机收益详情
     * @param context
     */
    public static void ToKJEarningsActivity(Activity context){
        Intent intent = new Intent(context, JieDianEarningsActivity.class);
        intent.putExtra("operateCode", EarningsPresenter.KJSY_KEY);
        context.startActivity(intent);
    }

    /**
     * 节点收益详情
     * @param context
     */
    public static void ToJieDianEarningsActivity(Activity context){
        Intent intent = new Intent(context, JieDianEarningsActivity.class);
        intent.putExtra("operateCode", EarningsPresenter.JDSY_KEY);
        context.startActivity(intent);
    }

    /**
     * 总节点收益详情
     * @param context
     */
    public static void ToZongJieDianEarningsActivity(Activity context){
        Intent intent = new Intent(context, JieDianEarningsActivity.class);
        intent.putExtra("operateCode", EarningsPresenter.ZJDSY_KEY);
        context.startActivity(intent);
    }

    /**
     * 总节点收益详情
     * @param context
     */
    public static void ToStarEarningsActivity(Activity context){
        Intent intent = new Intent(context, JieDianEarningsActivity.class);
        context.startActivity(intent);
    }

    /**
     * 客服页面
     * @param context
     */
    public static void ToKefuActivity(Activity context){
        Intent intent = new Intent(context, KefuActivity.class);
        context.startActivity(intent);
    }

    /**
     * 新闻详情页面
     * @param context
     */
    public static void ToNewsDetailActivity(Activity context, HomeNews.ListEntity entity){
        if(entity != null) {
            ToNewsDetailActivity(context, entity.getGroupName(), entity.getTitle(),
                    TimeUtils.millis2String(entity.getUpdateTime()), entity.getContent());
        }
    }

    /**
     * 新闻详情页面
     * @param context
     */
    public static void ToNewsDetailActivity(Activity context,
                                            String groupName,
                                            String title,
                                            String date,
                                            String content){
        Intent intent = new Intent(context, NewsDetailActivity.class);
        intent.putExtra("groupName", groupName);
        intent.putExtra("title", title);
        intent.putExtra("date", date);
        intent.putExtra("content", content);
        context.startActivity(intent);
    }

}
