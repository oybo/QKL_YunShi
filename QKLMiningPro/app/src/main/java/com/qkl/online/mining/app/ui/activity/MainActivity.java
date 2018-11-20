package com.qkl.online.mining.app.ui.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import com.lazy.viewpager.adapter.LazyBottomTabAdapter;
import com.lazy.viewpager.view.indicator.FixedIndicatorView;
import com.lazy.viewpager.view.indicator.IndicatorViewPager;
import com.lazy.viewpager.view.indicator.transition.OnTransitionTextListener;
import com.lazy.viewpager.view.viewpager.SViewPager;
import com.qkl.online.mining.app.R;
import com.qkl.online.mining.app.application.AccountManager;
import com.qkl.online.mining.app.mvp.presenter.MainPresenter;
import com.qkl.online.mining.app.mvp.view.IMainView;
import com.qkl.online.mining.app.ui.BaseActivity;
import com.qkl.online.mining.app.ui.fragment.GameFragment;
import com.qkl.online.mining.app.ui.fragment.HomeFragment;
import com.qkl.online.mining.app.ui.fragment.StarFragment;
import com.qkl.online.mining.app.ui.fragment.UserInfoFragment;
import com.qkl.online.mining.app.utils.IntentUtil;
import com.qkl.online.mining.app.utils.ToastUtils;
import butterknife.BindView;

/**
 * 主界面
 */

public class MainActivity extends BaseActivity<MainPresenter> implements IMainView {

    private long firstPressedTime;

    private static final int[] tabIcons = new int[]{
            R.drawable.selector_main_tab_home,
            R.drawable.selector_main_tab_star,
            R.drawable.selector_main_tab_game,
            R.drawable.selector_main_tab_userinfo};

    private Fragment[] fragments;

    @BindView(R.id.fragment_main_viewPager) SViewPager mSViewPager;
    @BindView(R.id.fragment_main_indicator) FixedIndicatorView mIndicatorView;

    private IndicatorViewPager mIndicatorViewPager;
    private LazyBottomTabAdapter mIndicatorAdapter;

    @Override
    protected boolean isTransparencyStatusBar() {
        return true;
    }

    @Override
    protected void initPresenter(Intent intent) {
        mPresenter = new MainPresenter(this, this);
        mPresenter.init();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        AccountManager.getInstance().setContext(this);

        // 禁止viewpager的滑动事件
        mSViewPager.setCanScroll(false);
        // 设置viewpager保留界面不重新加载的页面数量
        mSViewPager.setOffscreenPageLimit(4);

        mIndicatorView.setOnTransitionListener(new OnTransitionTextListener().setColor(
                R.color.color_ffffff, R.color.main_tab_txt_color));
    }

    @Override
    protected void initData() {
        String[] tabNames = new String[] {
                getString(R.string.main_tab_home), getString(R.string.main_tab_star),
                getString(R.string.main_tab_game), getString(R.string.main_tab_userinfo) };

        fragments = new Fragment[] {new HomeFragment(), new StarFragment(), new GameFragment(), new UserInfoFragment()};

        mIndicatorViewPager = new IndicatorViewPager(mIndicatorView, mSViewPager);
        mIndicatorAdapter = new LazyBottomTabAdapter(getSupportFragmentManager(), tabNames, tabIcons, fragments);
        mIndicatorViewPager.setAdapter(mIndicatorAdapter);
    }

    @Override
    public void onBackPressed() {
        // exit
        if (System.currentTimeMillis() - firstPressedTime < 2000) {
            super.onBackPressed();
        } else {
            ToastUtils.showShort(R.string.main_exit_app_tips_tx);
            firstPressedTime = System.currentTimeMillis();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 清空user对象
        AccountManager.getInstance().clearCache();
    }
}
