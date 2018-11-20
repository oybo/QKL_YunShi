package com.qkl.online.mining.app.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lazy.viewpager.fragment.LazyFragmentLazy;
import com.qkl.online.mining.app.R;
import com.qkl.online.mining.app.data.commons.Constants;
import com.qkl.online.mining.app.data.commons.UrlConfig;
import com.qkl.online.mining.app.data.entity.BannerBean;
import com.qkl.online.mining.app.data.entity.Exchange;
import com.qkl.online.mining.app.data.entity.GameBean;
import com.qkl.online.mining.app.data.event.EventBase;
import com.qkl.online.mining.app.mvp.presenter.GamePresenter;
import com.qkl.online.mining.app.mvp.view.IGameView;
import com.qkl.online.mining.app.ui.adapter.GameAdapter;
import com.qkl.online.mining.app.ui.view.FullyLinearLayoutManager;
import com.qkl.online.mining.app.ui.view.VpSwipeRefreshLayout;
import com.qkl.online.mining.app.utils.CommonsUtils;
import com.qkl.online.mining.app.utils.IntentUtil;
import com.qkl.online.mining.app.utils.glide.BannerImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

/**
 * author：oyb on 2018/7/4 16:46
 */
public class GameFragment extends LazyFragmentLazy<GamePresenter> implements IGameView, BaseQuickAdapter.OnItemClickListener, OnBannerListener {

    private static Handler mHandler = new Handler();
    private static final long REFRESH_TIME = 5 * 60000;
    private boolean startOnly;

    private VpSwipeRefreshLayout mSwipeRefreshLayout;
    private View mHeaderView;

    private Banner mBanner;
    private List<BannerBean.ListEntity> mBannerList;

    private RecyclerView mRecyclerView;
    private GameAdapter mAdapter;

    private int currentPage = 1;

    @Override
    protected void initPresenter() {
        mPresenter = new GamePresenter(getActivity(), this);
    }

    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);
        setContentView(R.layout.fragment_game);

        initView();
        initData();
    }

    private void initView() {
        setTitleBar(findViewById(R.id.fragment_game_titleview));

        mHeaderView = View.inflate(getApplicationContext(), R.layout.fragment_header_game, null);

        mBanner = (Banner) mHeaderView.findViewById(R.id.fragment_game_banner);
        mRecyclerView = (RecyclerView) findViewById(R.id.fragment_game_recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setHasFixedSize(true);

        //添加自定义分割线
        CommonsUtils.setDividerItemDecoration(mRecyclerView);

        mSwipeRefreshLayout = (VpSwipeRefreshLayout) findViewById(R.id.fragment_game_refreshlayout);
        // 设置转动颜色变化
        mSwipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_dark,
                android.R.color.holo_red_light,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light);

        // 刷新监听
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // 开始转动
                mSwipeRefreshLayout.setRefreshing(true);
                // 获取游戏列表
                currentPage = 1;
                mPresenter.getGameList(currentPage);
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
        mAdapter = new GameAdapter(getActivity());
        mAdapter.addHeaderView(mHeaderView);

        mRecyclerView.setAdapter(mAdapter);
        // 加载更多
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                mPresenter.getGameList(currentPage);
            }
        }, mRecyclerView);
        mAdapter.setOnItemClickListener(this);

        // banner
        mPresenter.getBanner();
        // 获取游戏列表
        mPresenter.getGameList(currentPage);
    }

    @Override
    protected void onEventCallback(EventBase eventBase) {
        super.onEventCallback(eventBase);
        String action = eventBase.getAction();
        if(Constants.REFRESH_GAME_LIST_KEY.equals(action)) {
            // 退出游戏重新登录，需要重新刷新游戏列表
            mPresenter.getGameList(currentPage);
        }
    }

    // 首页Banner回调
    @Override
    public void resultBanner(BannerBean bannerBean) {
        if (bannerBean != null) {
            mBannerList = bannerBean.getList();
            if (mBannerList != null) {
                List<String> images = new ArrayList<>();
                for (BannerBean.ListEntity banner : mBannerList) {
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
        if (mBannerList != null && mBannerList.size() > 0) {
            BannerBean.ListEntity listEntity = mBannerList.get(position);
            String url = listEntity.getUrl();
            if (!TextUtils.isEmpty(url)) {
                String title = listEntity.getTitle();
                IntentUtil.ToWebViewActivity(getActivity(), title, url);
            }
        }
    }

    /**
     * 游戏任务列表
     *
     * @param gameBean
     */
    @Override
    public void resultGame(GameBean gameBean) {
        if (gameBean != null) {
            List<GameBean.ListBean> listBeans = gameBean.getList();

            if (currentPage == 1) {
                mAdapter.setNewData(listBeans);
                mAdapter.loadMoreComplete();
                if (listBeans.size() < 10) {
                    mAdapter.loadMoreEnd(true);
                }
            } else {
                if (listBeans != null && listBeans.size() > 0) {
                    mAdapter.addData(listBeans);
                    mAdapter.loadMoreComplete();
                } else {
                    mAdapter.loadMoreEnd();
                }
            }
        } else {
            mAdapter.loadMoreFail();
            currentPage--;
        }

        currentPage++;

        if (!startOnly) {
            startOnly = true;
            mHandler.postDelayed(refreshRunnable, REFRESH_TIME);
        }
    }

    private Runnable refreshRunnable = new Runnable() {
        @Override
        public void run() {
            refreshGameList();
            mHandler.postDelayed(refreshRunnable, REFRESH_TIME);
        }
    };

    public void refreshGameList() {
        // 获取游戏列表
        currentPage = 1;
        mPresenter.getGameList(currentPage);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        GameBean.ListBean item = mAdapter.getItem(position);
        if (item != null) {
            IntentUtil.ToGameWebViewActivity(getActivity(), item.getGameName(), UrlConfig.getGameDefultUrl(item));
        }
    }

}
