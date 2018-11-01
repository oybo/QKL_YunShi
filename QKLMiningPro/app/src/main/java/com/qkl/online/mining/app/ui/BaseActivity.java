package com.qkl.online.mining.app.ui;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;

import com.orhanobut.hawk.Hawk;
import com.qkl.online.mining.app.data.commons.Constants;
import com.qkl.online.mining.app.data.event.EventBase;
import com.qkl.online.mining.app.library.ToolBarUtils;
import com.qkl.online.mining.app.mvp.presenter.BasePresenter;
import com.qkl.online.mining.app.ui.view.HeaderView;
import com.qkl.online.mining.app.utils.CommonsUtils;
import com.qkl.online.mining.app.utils.DialogUtils;
import com.qkl.online.mining.app.utils.ToastUtils;
import com.qkl.online.mining.app.utils.languagelib.MultiLanguageUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Locale;

import butterknife.ButterKnife;


/**
 * 基类Activity
 */

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity {

    protected boolean isTrans;
    private ConnectivityManager manager;

    //未指定类型的Presenter
    protected P mPresenter;
    //初始化Presenter

    protected abstract void initPresenter(Intent intent);

    //设置布局
    protected abstract int getLayout();

    //初始化布局
    protected abstract void initView();

    //初始化数据
    protected abstract void initData();

    protected DialogUtils mDialogUtils;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if ((getIntent().getFlags() & Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0) {
            finish();
            return;
        }

        setContentView(getLayout());
        if (isTransparencyStatusBar() && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            initStatusBar(true);
        }
        //初始化ButterKnife
        ButterKnife.bind(this);
        initPresenter(getIntent());
        checkPresenterIsNull();
        initView();
        initData();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(MultiLanguageUtil.attachBaseContext(newBase));
    }

    /**
     * 是否可以使用沉浸式
     *
     * @return the boolean
     */
    protected boolean isTransparencyStatusBar() {
        return false;
    }

    private void checkPresenterIsNull() {
        if (mPresenter == null) {
            throw new IllegalStateException("please init mPresenter in initPresenter() method ");
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void initStatusBar(boolean isTransparent) {
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        if (isTransparent) {
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        } else {
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        isTrans = isTransparent;
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.TRANSPARENT);
    }

    /**
     * 来作为EventBus接收处理函数
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(EventBase eventBase) {
        onEventCallback(eventBase);
    }

    protected void onEventCallback(EventBase eventBase) {

    }

    //设置打印方法
    public void showToast(String text) {
        ToastUtils.showShort(text);
    }

    //设置打印方法
    public void showToast(int stringId) {
        ToastUtils.showShort(stringId);
    }

    /**
     * 检查网络
     *
     * @return 是否有网络
     */
    public boolean checkNetworkState() {
        boolean flag = false;
        manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (manager.getActiveNetworkInfo() != null) {
            flag = manager.getActiveNetworkInfo().isAvailable();
        }
        return flag;
    }


    /**
     * 重新绘制标题栏高度，解决状态栏与顶部重叠问题
     * Sets title bar.
     */
    protected void setTitleBar(final View titleBarView) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && titleBarView != null) {
            final ViewGroup.LayoutParams layoutParams = titleBarView.getLayoutParams();
            if (layoutParams.height == ViewGroup.LayoutParams.WRAP_CONTENT ||
                    layoutParams.height == ViewGroup.LayoutParams.MATCH_PARENT) {
                titleBarView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        titleBarView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        layoutParams.height = titleBarView.getPaddingTop() + ToolBarUtils.stateBarHeight;
                        titleBarView.setPadding(titleBarView.getPaddingLeft(),
                                ToolBarUtils.stateBarHeight,
                                titleBarView.getPaddingRight(),
                                titleBarView.getPaddingBottom());
                        titleBarView.setLayoutParams(layoutParams);
                    }
                });
            } else {
                layoutParams.height = titleBarView.getPaddingTop() + ToolBarUtils.stateBarHeight;
                titleBarView.setPadding(titleBarView.getPaddingLeft(),
                        ToolBarUtils.stateBarHeight,
                        titleBarView.getPaddingRight(),
                        titleBarView.getPaddingBottom());
                titleBarView.setLayoutParams(layoutParams);
            }
        }
    }

    protected void initTopBarOnlyTitle(int layoutId, String title) {
        HeaderView headerView = (HeaderView) findViewById(layoutId);
        initTopBarOnlyTitle(headerView, title);
    }

    protected void initTopBarOnlyTitle(HeaderView headerView, String title) {
        headerView.setTitile(title);
        headerView.getToolbar().setTitle("");
        setSupportActionBar(headerView.getToolbar());
    }

    //状态栏透明
    protected void setStatusTransparent() {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getWindow().setStatusBarColor(Color.TRANSPARENT);
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected String getXmlString(int stringId) {
        return CommonsUtils.getXmlString(this, stringId);
    }

    protected String getXmlString(int stringId, Object... str) {
        return CommonsUtils.getXmlString(this, stringId, str);
    }

    protected void showLoading(boolean isShow) {
        if (isShow) {
            if (mDialogUtils == null) {
                mDialogUtils = new DialogUtils(this);
            }
            mDialogUtils.showProgressDialog(true);
        } else {
            if (mDialogUtils != null) {
                mDialogUtils.showProgressDialog(false);
            }
        }
    }

    protected void showLoading(boolean isShow, String message) {
        if (isShow) {
            if (mDialogUtils == null) {
                mDialogUtils = new DialogUtils(this);
            }
            mDialogUtils.showProgressDialog(true, message);
        } else {
            if (mDialogUtils != null) {
                mDialogUtils.showProgressDialog(false);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
