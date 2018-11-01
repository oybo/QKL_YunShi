package com.qkl.online.mining.app.ui.fragment;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.qkl.online.mining.app.R;
import com.qkl.online.mining.app.application.AccountManager;
import com.qkl.online.mining.app.data.commons.Constants;
import com.qkl.online.mining.app.data.entity.DictConfig;
import com.qkl.online.mining.app.data.entity.MyStar;
import com.qkl.online.mining.app.data.entity.MyYuntEntity;
import com.qkl.online.mining.app.data.entity.SSExchangerate;
import com.qkl.online.mining.app.data.entity.StarProduct;
import com.qkl.online.mining.app.data.event.EventBase;
import com.qkl.online.mining.app.mvp.presenter.StarPresenter;
import com.qkl.online.mining.app.mvp.view.IStarView;
import com.qkl.online.mining.app.ui.BaseFragment;
import com.qkl.online.mining.app.ui.adapter.MyStarAdapter;
import com.qkl.online.mining.app.ui.adapter.StarProductAdapter;
import com.qkl.online.mining.app.ui.view.CircleImageView;
import com.qkl.online.mining.app.ui.view.VpSwipeRefreshLayout;
import com.qkl.online.mining.app.utils.CommonsUtils;
import com.qkl.online.mining.app.utils.IntentUtil;
import com.qkl.online.mining.app.utils.ScreenUtils;
import com.qkl.online.mining.app.utils.TimeUtils;
import com.qkl.online.mining.app.utils.glide.GlideImageLoader;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author：oyb on 2018/7/4 16:46
 */
public class StarFragment extends BaseFragment<StarPresenter> implements IStarView, BaseQuickAdapter.OnItemChildClickListener {

    private static final long REFRESH_STAR_REST_NUMBER_TIME = 5 * 60 * 1000;
    private static Handler mHandler = new Handler();

    @BindView(R.id.fragment_star_refreshlayout)
    VpSwipeRefreshLayout mSwipeRefreshLayout;

    @BindView(R.id.fragment_star_titleview)
    View mBarView;
    @BindView(R.id.fragment_star_recyclerview)
    RecyclerView mRecyclerView;
    @BindView(R.id.fragment_star_viewpager)
    ViewPager mViewPager;
    @BindView(R.id.fragment_star_canbuy_number_txt)
    TextView mCanBuyTxt;
    @BindView(R.id.fragment_star_parities_txt)
    TextView mStarPriceTxt;
    @BindView(R.id.fragment_star_myyun_txt)
    TextView mMyYunTxt;
    @BindView(R.id.fragment_star_user_nickname)
    TextView mNickNameTxt;
    @BindView(R.id.fragment_star_user_icon)
    CircleImageView mUserIconView;
    @BindView(R.id.fragment_mystar_title_txt)
    TextView mMyStarTitleTxt;

    private MyStarAdapter mMyStarAdapter;

    private StarProductAdapter mStarProductAdapter;

    @Override
    protected void initPresenter() {
        mPresenter = new StarPresenter(getActivity(), this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_star;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

        setTitleBar(mBarView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                int topRowVerticalPosition = (recyclerView == null || recyclerView.getChildCount() == 0) ? 0 : recyclerView.getChildAt(0).getTop();
                mSwipeRefreshLayout.setEnabled(topRowVerticalPosition >= 0);
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
        });

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
                initData();
                AccountManager.getInstance().getUserYUNTData();
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

    @Override
    protected void initData() {
        // 我的星球
        mPresenter.getMyStar();
        // 获取矿机列表
        mPresenter.getMineList();
        // 今日剩余星球总数
        mPresenter.todayStarRestNumber();

        setUserInfo();
    }

    @Override
    protected void onEventCallback(EventBase eventBase) {
        super.onEventCallback(eventBase);
        String action = eventBase.getAction();
        if (Constants.REFRESH_USER_INFO_KEY.equals(action)) {
            // 刷新数据
            setUserInfo();
        } else if (Constants.REFRESH_MY_STAR_KEY.equals(action)) {
            // 刷新我的星球
            mPresenter.getMyStar();
        }
    }

    private void setUserInfo() {
        // 昵称
        mNickNameTxt.setText(AccountManager.getInstance().getNickName());
        // 头像
        if (!TextUtils.isEmpty(AccountManager.getInstance().getPicture())) {
            GlideImageLoader.loadImage(getActivity(), AccountManager.getInstance().getPicture(), mUserIconView);
        }
        // 实时汇率
        SSExchangerate ssExchangerate = AccountManager.getInstance().getSSExchangerate();
        if (ssExchangerate != null) {
            mStarPriceTxt.setText(getXmlString(R.string.yun_usdt_exchange_txt, String.valueOf(ssExchangerate.getYunToUsdt())));
        } else {
            mStarPriceTxt.setText(getXmlString(R.string.yun_usdt_exchange_txt, "0"));
        }
        // 我的yunt余额
        MyYuntEntity yuntEntity = AccountManager.getInstance().getMyYuntEntity();
        if (yuntEntity != null) {
            mMyYunTxt.setText(getXmlString(R.string.yunt_my_money_txt, yuntEntity.getBalance()));
        } else {
            mMyYunTxt.setText(getXmlString(R.string.yunt_my_money_txt, "0"));
        }
    }

    /**
     * 星球列表
     *
     * @param starProduct
     */
    @Override
    public void resultMine(StarProduct starProduct) {
        if (starProduct != null) {
            List<StarProduct.ListEntity> listEntities = starProduct.getList();
            if (listEntities != null && listEntities.size() > 0) {
                mStarProductAdapter = new StarProductAdapter(getActivity(), listEntities);
                mRecyclerView.setAdapter(mStarProductAdapter);
                mStarProductAdapter.setOnItemChildClickListener(this);
            }
        }
    }

    @Override
    public void resultMyStar(MyStar myStar) {
        if (myStar != null) {
            mMyStarTitleTxt.setText(getXmlString(R.string.main_star_mysytar_txt));
            AccountManager.getInstance().setStarResidueName(myStar.getMinerName());
            AccountManager.getInstance().setStarResidueDay(String.valueOf(myStar.getRemaining()));

            List<MyStar> starList = new ArrayList<>();
            starList.add(myStar);
            mMyStarAdapter = new MyStarAdapter(getActivity(), starList);
            mViewPager.setAdapter(mMyStarAdapter);
            // 刷新个人中心页面
            EventBase eventBase = new EventBase();
            eventBase.setAction(Constants.REFRESH_USER_INFO_KEY);
            EventBus.getDefault().post(eventBase);
        } else {
            // 暂无星球
            mMyStarTitleTxt.setText(getXmlString(R.string.main_star_mysytar_not_available_txt));

            myStar = new MyStar();
            myStar.setMinerName(getXmlString(R.string.purchase_star_title_null_txt));
            myStar.setMinerImg("");
            myStar.setRemaining(0);
            myStar.setAccumulated(0);
            myStar.setIsRun(-1);

            AccountManager.getInstance().setStarResidueName(myStar.getMinerName());
            AccountManager.getInstance().setStarResidueDay(String.valueOf(myStar.getRemaining()));

            List<MyStar> starList = new ArrayList<>();
            starList.add(myStar);
            mMyStarAdapter = new MyStarAdapter(getActivity(), starList);
            mViewPager.setAdapter(mMyStarAdapter);
        }
    }

    /**
     * 今日剩余星球总数
     * @param number
     */
    @Override
    public void resultStarRestNumber(int number) {
        mCanBuyTxt.setText(getXmlString(R.string.public_canbuy_number_txt, number));
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // 定时刷新今日剩余星球总数
                mPresenter.todayStarRestNumber();
                // 获取实时汇率接口
                AccountManager.getInstance().getYunEchangeRate();
            }
        }, REFRESH_STAR_REST_NUMBER_TIME);
    }

    @OnClick(R.id.fragment_star_make)
    void starMake() {
        // 星球预约
        DictConfig dictConfig = AccountManager.getInstance().getDictConfig();
        if(dictConfig != null) {
            String reservationUrl = dictConfig.getReservationUrl();
            if(!TextUtils.isEmpty(reservationUrl)) {
                IntentUtil.ToWebViewActivity(getActivity(), getXmlString(R.string.public_star_make_txt), reservationUrl, true);
            }
        }
    }

    @Override
    public void resultPurchaseStar() {
        // 无需处理
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        StarProduct.ListEntity listEntity = mStarProductAdapter.getItem(position);
        if (listEntity != null) {
            IntentUtil.ToPurchaseStarActivity(getActivity(), listEntity);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if(mMyStarAdapter != null) {
            mMyStarAdapter.onResume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if(mMyStarAdapter != null) {
            mMyStarAdapter.onPause();
        }
    }
}
