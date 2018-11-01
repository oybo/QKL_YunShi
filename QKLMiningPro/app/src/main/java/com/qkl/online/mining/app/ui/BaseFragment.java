package com.qkl.online.mining.app.ui;


import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import com.qkl.online.mining.app.data.event.EventBase;
import com.qkl.online.mining.app.library.ToolBarUtils;
import com.qkl.online.mining.app.mvp.presenter.BasePresenter;
import com.qkl.online.mining.app.ui.view.HeaderView;
import com.qkl.online.mining.app.utils.CommonsUtils;
import com.qkl.online.mining.app.utils.DialogUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;

/**
 * author：oyb on 2018/7/4 17:46
 */

public abstract class BaseFragment<P extends BasePresenter> extends Fragment {

    protected P mPresenter;
    //初始化Presenter
    protected abstract void initPresenter();

    protected BaseActivity mActivity;

    protected abstract void initView(View view, Bundle savedInstanceState);

    protected abstract void initData();

    //获取布局文件ID
    protected abstract int getLayoutId();

    protected DialogUtils mDialogUtils;

    //获取宿主Activity
    protected BaseActivity getParentActivity() {
        return mActivity;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mActivity = (BaseActivity) activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        ButterKnife.bind(this, view);
        initPresenter();
        checkPresenterIsNull();
        initView(view, savedInstanceState);
        initData();
        EventBus.getDefault().register(this);

        return view;
    }

    private void checkPresenterIsNull() {
        if (mPresenter == null) {
            throw new IllegalStateException("please init mPresenter in initPresenter() method ");
        }
    }

    /**
     * 来作为EventBus接收处理函数
     *
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(EventBase eventBase) {
        onEventCallback(eventBase);
    }

    protected void onEventCallback(EventBase eventBase) {

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
        HeaderView headerView = (HeaderView) getView().findViewById(layoutId);
        initTopBarOnlyTitle(headerView, title);
    }

    protected void initTopBarOnlyTitle(HeaderView headerView, String title) {
        headerView.setTitile(title);
    }

    protected String getXmlString(int stringId) {
        return CommonsUtils.getXmlString(getActivity(), stringId);
    }

    protected String getXmlString(int stringId, Object... str) {
        return CommonsUtils.getXmlString(getActivity(), stringId, str);
    }

    protected void showLoading(boolean isShow) {
        if(isShow) {
            if(mDialogUtils == null) {
                mDialogUtils = new DialogUtils(getActivity());
            }
            mDialogUtils.showProgressDialog(true);
        } else {
            if(mDialogUtils != null) {
                mDialogUtils.showProgressDialog(false);
            }
        }
    }

    protected void showLoading(boolean isShow, String message) {
        if(isShow) {
            if(mDialogUtils == null) {
                mDialogUtils = new DialogUtils(getActivity());
            }
            mDialogUtils.showProgressDialog(true, message);
        } else {
            if(mDialogUtils != null) {
                mDialogUtils.showProgressDialog(false);
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

}
