package com.qkl.online.mining.app.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lazy.viewpager.fragment.LazyFragmentLazy;
import com.qkl.online.mining.app.R;
import com.qkl.online.mining.app.application.AccountManager;
import com.qkl.online.mining.app.data.entity.BannerBean;
import com.qkl.online.mining.app.data.entity.DictConfig;
import com.qkl.online.mining.app.data.entity.HomeNews;
import com.qkl.online.mining.app.mvp.presenter.HomePresenter;
import com.qkl.online.mining.app.mvp.view.IHomeView;
import com.qkl.online.mining.app.ui.adapter.HomeNewsAdapter;
import com.qkl.online.mining.app.ui.view.FullyLinearLayoutManager;
import com.qkl.online.mining.app.ui.view.VpSwipeRefreshLayout;
import com.qkl.online.mining.app.ui.view.marqueeview.MarqueeView;
import com.qkl.online.mining.app.utils.CommonsUtils;
import com.qkl.online.mining.app.utils.IntentUtil;
import com.qkl.online.mining.app.utils.glide.BannerImageLoader;
import com.qkl.online.mining.app.utils.suitlines.SuitLines;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.OnClick;

/**
 * author：oyb on 2018/7/4 16:46
 * 首页
 */
public class HomeFragment extends LazyFragmentLazy<HomePresenter> implements IHomeView, BaseQuickAdapter.OnItemClickListener, OnBannerListener, MarqueeView.OnItemClickListener {

    private static Handler mHandler = new Handler();

    private VpSwipeRefreshLayout mSwipeRefreshLayout;
    private Banner mBanner;
    private MarqueeView mMarqueeView;
    private SuitLines mSuitLines;
    private RecyclerView mRecyclerView;
    private TextView mDumpEnergyTxt;
    private TextView mSuanLiTxt;

    private List<BannerBean.ListEntity> mBannerList;
    private List<HomeNews.ListEntity> mRollNewsList;
    private HomeNewsAdapter mHomeNewsAdapter;

    @Override
    protected void initPresenter() {
        mPresenter = new HomePresenter(getActivity(), this);
    }

    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);
        setContentView(R.layout.fragment_home);

        initView();
        initData();
    }

    protected void initView() {
        mSwipeRefreshLayout = (VpSwipeRefreshLayout) findViewById(R.id.fragment_home_refreshlayout);
        mBanner = (Banner) findViewById(R.id.fragment_home_banner);
        mMarqueeView = (MarqueeView) findViewById(R.id.fragment_home_marqueeview);
        mSuitLines = (SuitLines) findViewById(R.id.fragment_home_ynm_chartline);
        mRecyclerView = (RecyclerView) findViewById(R.id.fragment_home_recyclerview);
        mDumpEnergyTxt = (TextView) findViewById(R.id.fragment_home_kc_dump_energy_txt);
        mSuanLiTxt = (TextView) findViewById(R.id.fragment_home_kc_suanli_txt);

        mRecyclerView.setLayoutManager(new FullyLinearLayoutManager(getActivity()));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setNestedScrollingEnabled(false);
        //添加自定义分割线
        CommonsUtils.setDividerItemDecoration(mRecyclerView, R.drawable.custom_divider);

        // 设置转动颜色变化
        mSwipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_dark,
                android.R.color.holo_red_light,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light);

        // 刷新监听
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override public void onRefresh() {
                // 开始转动
                mSwipeRefreshLayout.setRefreshing(true);
                initData();
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // 停止转动
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                }, 2000);
            }
        });
    }

    private void initData() {
        // 获取banner
        mPresenter.getBanner();
        // 矿池实时数据接口
        mPresenter.getPoolConfig();
        // ynm价格曲线
        mPresenter.getYunExchangerate();
        // 获取新闻公告
        mPresenter.getNews();
        // 滚动消息
        mPresenter.getRollNews();
    }

    // 首页Banner回调
    @Override
    public void resultBanner(BannerBean bannerBean) {
        if(bannerBean != null) {
            mBannerList = bannerBean.getList();
            if(mBannerList != null) {
                List<String> images = new ArrayList<>();
                for(BannerBean.ListEntity banner : mBannerList) {
                    images.add(banner.getImage());
                }
                mBanner.setImages(images)
                        .setIndicatorGravity(BannerConfig.RIGHT)
                        .setDelayTime(10000)
                        .setImageLoader(new BannerImageLoader())
                        .setOnBannerListener(this)
                        .start();
            }
        }
    }

    // 首页Banner点击
    @Override
    public void OnBannerClick(int position) {
        if(mBannerList != null && mBannerList.size() > 0) {
            BannerBean.ListEntity listEntity = mBannerList.get(position);
            String url = listEntity.getUrl();
            if(!TextUtils.isEmpty(url)) {
                String title = listEntity.getTitle();
                IntentUtil.ToWebViewActivity(getActivity(), title, url);
            }
        }
    }

    // 首页矿池能量以及总算力
    @Override
    public void resultPool(String yunAmount, String yuntHashrate) {
        mDumpEnergyTxt.setText(yunAmount);
        mSuanLiTxt.setText(yuntHashrate);
    }

    @OnClick(R.id.fragment_home_kc_dump_energy_layout)
    void kCEnergy() {
        // 矿池储量
        DictConfig dictConfig = AccountManager.getInstance().getDictConfig();
        if(dictConfig != null && !TextUtils.isEmpty(dictConfig.getTokenHolderUrl())) {
            IntentUtil.ToWebViewActivity(getActivity(),
                    "YUNT data",
                    dictConfig.getTokenHolderUrl());
        }
    }

    @OnClick(R.id.fragment_home_kc_suanli_layout)
    void kCSuanLi() {
        // 矿池总算力
        DictConfig dictConfig = AccountManager.getInstance().getDictConfig();
        if(dictConfig != null && !TextUtils.isEmpty(dictConfig.getYuntListUrl())) {
            IntentUtil.ToWebViewActivity(getActivity(),
                    "YUNT data",
                    dictConfig.getYuntListUrl());
        }
    }

    // 首页新闻
    @Override
    public void resultNews(HomeNews homeNews) {
        List<HomeNews.ListEntity> homeNewsList = homeNews.getList();
        if(homeNewsList != null) {
            mHomeNewsAdapter = new HomeNewsAdapter();
            mRecyclerView.setAdapter(mHomeNewsAdapter);
            mHomeNewsAdapter.setNewData(homeNewsList);
            mHomeNewsAdapter.setOnItemClickListener(this);
        }
    }

    // 首页新闻点击
    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        HomeNews.ListEntity listEntity = mHomeNewsAdapter.getItem(position);
        if(listEntity != null) {
            IntentUtil.ToWebViewActivity(getActivity(), listEntity.getTitle(), listEntity.getUrl());
        }
    }

    // 首页滚动通知
    @Override
    public void resultRollNews(HomeNews homeNews) {
        //设置集合
        List<String> items = new ArrayList<>();
        if(homeNews != null) {
            mRollNewsList = homeNews.getList();
            if(mRollNewsList != null && mRollNewsList.size() > 0) {
                for(HomeNews.ListEntity news : mRollNewsList) {
                    items.add(news.getTitle());
                }
            }
        }
        mMarqueeView.setOnItemClickListener(this);
        mMarqueeView.startWithList(items);
    }

    // 首页滚动通知点击
    @Override
    public void onItemClick(int position, TextView textView) {
        HomeNews.ListEntity listEntity = mRollNewsList.get(position);
        if(listEntity != null) {
            IntentUtil.ToWebViewActivity(getActivity(), listEntity.getTitle(), listEntity.getUrl());
        }
    }

    // 首页价格曲线图-YNU汇率
    @Override
    public void resultYunExchangeRate(List lines) {
        mSuitLines.feed(lines);
    }

    @OnClick(R.id.home_kefu_view)
    public void kefu() {
        IntentUtil.ToKefuActivity(getActivity());
    }

}
