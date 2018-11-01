package com.qkl.online.mining.app.ui.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.qkl.online.mining.app.R;
import com.qkl.online.mining.app.data.entity.Earnings;
import com.qkl.online.mining.app.mvp.presenter.EarningsPresenter;
import com.qkl.online.mining.app.mvp.view.IEarningsView;
import com.qkl.online.mining.app.ui.BaseActivity;
import com.qkl.online.mining.app.ui.adapter.JieDianEarningsAdapter;
import com.qkl.online.mining.app.utils.CommonsUtils;

import java.util.List;

import butterknife.BindView;

/**
 * author：oyb on 2018/9/16 22:18
 * 节点收益详情
 */
public class JieDianEarningsActivity extends BaseActivity<EarningsPresenter> implements IEarningsView, BaseQuickAdapter.OnItemClickListener {

    @BindView(R.id.activity_earnings_recyclerview)
    RecyclerView mRecyclerView;
    @BindView(R.id.date_is_null_txt)
    TextView mDataNullTxt;
    @BindView(R.id.include_loading_layout)
    LinearLayout mLoadingLayout;

    private int currentPage = 1;
    private JieDianEarningsAdapter mAdapter;

    @Override
    protected void initPresenter(Intent intent) {
        mPresenter = new EarningsPresenter(this, this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_jiedian_earnings;
    }

    @Override
    protected void initView() {
        initTopBarOnlyTitle(R.id.activity_earnings_headerview, getXmlString(R.string.act_jiedian_earnings_page_title));

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //添加自定义分割线
        CommonsUtils.setDividerItemDecoration(mRecyclerView);
    }

    @Override
    protected void initData() {
        mAdapter = new JieDianEarningsAdapter();
        mRecyclerView.setAdapter(mAdapter);
        // 加载更多
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                onLoadMore();
            }
        }, mRecyclerView);
        mAdapter.setOnItemClickListener(this);

        String operateCode = getIntent().getStringExtra("operateCode");
        mPresenter.getEarnings(operateCode, currentPage);
    }

    @Override
    public void resultEarnings(Earnings earnings) {
        if(earnings != null) {
            List<Earnings.ListBean> listBeans = earnings.getList();

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
        mPresenter.getEarnings("", currentPage);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

    }

}
