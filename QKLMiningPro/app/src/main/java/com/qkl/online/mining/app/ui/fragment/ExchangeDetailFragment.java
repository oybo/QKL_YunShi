package com.qkl.online.mining.app.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lazy.viewpager.fragment.LazyFragmentLazy;
import com.qkl.online.mining.app.R;
import com.qkl.online.mining.app.data.entity.Exchange;
import com.qkl.online.mining.app.data.entity.ExchangeDetail;
import com.qkl.online.mining.app.mvp.presenter.ExchangeListPresenter;
import com.qkl.online.mining.app.mvp.view.IExchangeListView;
import com.qkl.online.mining.app.ui.adapter.ExchangeHistoryAdapter;
import com.qkl.online.mining.app.utils.CommonsUtils;
import com.qkl.online.mining.app.utils.IntentUtil;
import com.qkl.online.mining.app.utils.ToastUtils;

import java.util.List;

/**
 * author：oyb on 2018/8/27 11:25
 * 兑换记录
 */
public class ExchangeDetailFragment extends LazyFragmentLazy<ExchangeListPresenter> implements IExchangeListView, BaseQuickAdapter.OnItemClickListener {

    private RecyclerView mRecyclerView;
    private ExchangeHistoryAdapter mAdapter;

    private int currentPage = 1;
    private String exchangeType;

    private TextView mDataNullTxt;
    private LinearLayout mLoadingLayout;

    public static ExchangeDetailFragment newInstance(String exchangeType) {
        ExchangeDetailFragment fragment = new ExchangeDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString("exchangeType", exchangeType);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void initPresenter() {
        mPresenter = new ExchangeListPresenter(getActivity(), this);
    }

    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);
        setContentView(R.layout.fragment_exchange_detail);

        initView();
        initData();
    }

    private void initView() {
        mDataNullTxt = (TextView) findViewById(R.id.date_is_null_txt);
        mLoadingLayout = (LinearLayout) findViewById(R.id.include_loading_layout);

        mRecyclerView = (RecyclerView) findViewById(R.id.fragment_exchange_recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setHasFixedSize(true);

        //添加自定义分割线
        CommonsUtils.setDividerItemDecoration(mRecyclerView);
    }

    private void initData() {
        mAdapter = new ExchangeHistoryAdapter();
        mRecyclerView.setAdapter(mAdapter);
        // 加载更多
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                onLoadMore();
            }
        }, mRecyclerView);
        mAdapter.setOnItemClickListener(this);

        exchangeType = getArguments().getString("exchangeType");
        mPresenter.getExchangeList(exchangeType, currentPage);
    }

    @Override
    public void resultExchange(Exchange exchange) {
        if(exchange != null) {
            List<Exchange.ListBean> listBeans = exchange.getList();

            if(currentPage == 1) {
                mAdapter.setNewData(listBeans);
                mAdapter.loadMoreComplete();
                if(listBeans.size() < 10) {
                    mAdapter.loadMoreEnd(true);
                }
            } else {
                if(listBeans != null && listBeans.size() > 0) {
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

        if(mLoadingLayout.getVisibility() != View.GONE) {
            mLoadingLayout.setVisibility(View.GONE);
        }
        if(currentPage == 1 && mAdapter.getItemCount() == 0) {
            mDataNullTxt.setVisibility(View.VISIBLE);
        }

        currentPage++;
    }

    private void onLoadMore() {
        mPresenter.getExchangeList(exchangeType, currentPage);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Exchange.ListBean listBean = mAdapter.getItem(position);
        if(listBean != null) {
            if(TextUtils.isEmpty(listBean.getTxid())) {
                ToastUtils.showShort(R.string.yun_exchange_in_tips);
                return;
            }
            IntentUtil.ToExchangeDetailActivity(getActivity(), listBean, exchangeType);
        }
    }

    @Override
    public void resultExchangeDetail(ExchangeDetail exchangeDetail) {
        // 无需处理
    }

}
