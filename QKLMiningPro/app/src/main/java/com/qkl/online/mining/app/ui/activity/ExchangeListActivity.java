package com.qkl.online.mining.app.ui.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.lazy.viewpager.adapter.LazyTabAdapter;
import com.lazy.viewpager.view.indicator.FixedIndicatorView;
import com.lazy.viewpager.view.indicator.IndicatorViewPager;
import com.lazy.viewpager.view.indicator.slidebar.ColorBar;
import com.lazy.viewpager.view.indicator.transition.OnTransitionTextListener;
import com.qkl.online.mining.app.R;
import com.qkl.online.mining.app.data.entity.Exchange;
import com.qkl.online.mining.app.data.entity.ExchangeDetail;
import com.qkl.online.mining.app.mvp.presenter.ExchangeListPresenter;
import com.qkl.online.mining.app.mvp.view.IExchangeListView;
import com.qkl.online.mining.app.ui.BaseActivity;
import com.qkl.online.mining.app.ui.fragment.ExchangeDetailFragment;
import com.qkl.online.mining.app.utils.CommonsUtils;
import com.qkl.online.mining.app.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * author：oyb on 2018/9/16 22:18
 * 兑换记录
 */
public class ExchangeListActivity extends BaseActivity<ExchangeListPresenter> implements IExchangeListView {

    @BindView(R.id.activity_exchange_indicator)
    FixedIndicatorView mIndicator;
    @BindView(R.id.activity_exchange_viewPager)
    ViewPager mViewPager;

    private IndicatorViewPager indicatorViewPager;

    @Override
    protected void initPresenter(Intent intent) {
        mPresenter = new ExchangeListPresenter(this, this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_exchange;
    }

    @Override
    protected void initView() {
        initTopBarOnlyTitle(R.id.activity_exchange_headerview, getXmlString(R.string.act_exchange_page_title));

        float selectSize = 15;
        ColorBar colorBar = new ColorBar(getApplicationContext(), CommonsUtils.getXmlColor(R.color.color_9441EF), 5);
        colorBar.setWidth(ScreenUtils.dpToPxInt(60));
        mIndicator.setScrollBar(colorBar);
        mIndicator.setOnTransitionListener(new OnTransitionTextListener()
                .setColor(R.color.color_9441EF, R.color.color_000000)
                .setSize(selectSize, selectSize));

        mViewPager.setOffscreenPageLimit(3);
    }

    @Override
    protected void initData() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(ExchangeDetailFragment.newInstance(ExchangeListPresenter.YUNT2YUN));
        fragments.add(ExchangeDetailFragment.newInstance(ExchangeListPresenter.YUN2YUNT));

        String[] mTabTitles = new String[]{
                getString(R.string.act_exchange_yun_title),
                getString(R.string.act_exchange_yunt_title)};

        indicatorViewPager = new IndicatorViewPager(mIndicator, mViewPager);
        indicatorViewPager.setAdapter(new LazyTabAdapter(getSupportFragmentManager(), mTabTitles, fragments));

        int position = getIntent().getIntExtra("index", 0);
        indicatorViewPager.setCurrentItem(position, false);
    }

    @Override
    public void resultExchange(Exchange exchange) {
        // 无需处理
    }

    @Override
    public void resultExchangeDetail(ExchangeDetail exchangeDetail) {
        // 无需处理
    }

}
