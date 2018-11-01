package com.qkl.online.mining.app.ui.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.qkl.online.mining.app.R;
import com.qkl.online.mining.app.data.entity.Exchange;
import com.qkl.online.mining.app.data.entity.TeamBean;
import com.qkl.online.mining.app.mvp.presenter.MyTeamPresenter;
import com.qkl.online.mining.app.mvp.view.IMyteamView;
import com.qkl.online.mining.app.ui.BaseActivity;
import com.qkl.online.mining.app.ui.adapter.MyTeamAdapter;
import com.qkl.online.mining.app.utils.ToastUtils;

import java.util.List;

import butterknife.BindView;

/**
 * author：oyb on 2018/9/9 02:43
 * 我的团队
 */
public class MyTeamActivity extends BaseActivity<MyTeamPresenter> implements IMyteamView, BaseQuickAdapter.OnItemClickListener {

    @BindView(R.id.activity_my_team_recyclerview)
    RecyclerView mRecyclerView;
    @BindView(R.id.date_is_null_txt)
    TextView mDataNullTxt;
    @BindView(R.id.include_loading_layout)
    LinearLayout mLoadingLayout;

    private int currentPage = 1;
    private MyTeamAdapter mAdapter;

    @Override
    protected void initPresenter(Intent intent) {
        mPresenter = new MyTeamPresenter(this, this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_my_team;
    }

    @Override
    protected void initView() {
        initTopBarOnlyTitle(R.id.activity_my_team_headerview, getXmlString(R.string.fragment_myteam_page_title));

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);
    }

    @Override
    protected void initData() {
        mAdapter = new MyTeamAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        // 加载更多
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                mPresenter.getMyTeam(currentPage);
            }
        }, mRecyclerView);
        mAdapter.setOnItemClickListener(this);

        mPresenter.getMyTeam(currentPage);
    }

    @Override
    public void result(TeamBean teamBean) {
        if(teamBean != null) {
            List<TeamBean.ListBean> listBeans = teamBean.getList();

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

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//        TeamBean.ListBean listEntity = mMyTeamAdapter.getItem(position);
//        if(listEntity != null) {
//            ToastUtils.showShort(listEntity.getEmail());
//        }
    }

}
