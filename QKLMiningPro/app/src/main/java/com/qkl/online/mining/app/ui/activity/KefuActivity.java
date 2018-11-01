package com.qkl.online.mining.app.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.qkl.online.mining.app.R;
import com.qkl.online.mining.app.application.AccountManager;
import com.qkl.online.mining.app.data.entity.BannerBean;
import com.qkl.online.mining.app.data.entity.HomeNews;
import com.qkl.online.mining.app.mvp.presenter.HomePresenter;
import com.qkl.online.mining.app.mvp.view.IHomeView;
import com.qkl.online.mining.app.ui.BaseActivity;
import com.qkl.online.mining.app.ui.adapter.IssueAdapter;
import com.qkl.online.mining.app.utils.CommonsUtils;
import com.qkl.online.mining.app.utils.IntentUtil;
import com.qkl.online.mining.app.utils.ToastUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author：oyb on 2018/9/5 02:51
 * 客服
 */
public class KefuActivity extends BaseActivity<HomePresenter> implements IHomeView, BaseQuickAdapter.OnItemClickListener {

    @BindView(R.id.kefu_issue_recyclerview)
    RecyclerView mRecyclerView;

    @BindView(R.id.kefu_issue_phone)
    TextView mKeFuEmailTxt;

    private IssueAdapter mIssueAdapter;

    @Override
    protected void initPresenter(Intent intent) {
        mPresenter = new HomePresenter(this, this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_kefu;
    }

    @Override
    protected void initView() {
        initTopBarOnlyTitle(R.id.activity_set_yun_headerview, getXmlString(R.string.issue_page_title));

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);
        //添加自定义分割线
        CommonsUtils.setDividerItemDecoration(mRecyclerView);
    }

    @Override
    protected void initData() {
        mPresenter.getIssues();

        String kfEmail = AccountManager.getInstance().getServiceEmail();
        if(!TextUtils.isEmpty(kfEmail)) {
            mKeFuEmailTxt.setText(CommonsUtils.getXmlString(this, R.string.issue_contact_phone_txt, kfEmail));
        } else {
            AccountManager.getInstance().getCommonsConfig();
        }
    }

    @Override
    public void resultBanner(BannerBean bannerBean) {
        // 无需处理
    }

    @Override
    public void resultNews(HomeNews homeNews) {
        if(homeNews != null) {
            List<HomeNews.ListEntity> homeNewsList = homeNews.getList();
            if(homeNewsList != null && homeNewsList.size() > 0) {
                mIssueAdapter = new IssueAdapter(homeNewsList);
                mRecyclerView.setAdapter(mIssueAdapter);
                mIssueAdapter.setOnItemClickListener(this);
            }
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        HomeNews.ListEntity entity = mIssueAdapter.getItem(position);
        if(entity != null) {
            IntentUtil.ToWebViewActivity(this, entity.getTitle(), entity.getUrl());
//            IntentUtil.ToNewsDetailActivity(this, entity);
        }
    }

    @OnClick(R.id.kefu_issue_phone)
    void openEmail() {
        String kfEmail = AccountManager.getInstance().getServiceEmail();
        if(!TextUtils.isEmpty(kfEmail)) {
            mKeFuEmailTxt.setText(CommonsUtils.getXmlString(this, R.string.issue_contact_phone_txt, kfEmail));

            openEmail(this, "", "", kfEmail);
        }
    }

    @Override
    public void resultRollNews(HomeNews homeNews) {
        // 无需处理
    }

    @Override
    public void resultPool(String yunAmount, String yuntHashrate) {
        // 无需处理
    }

    @Override
    public void resultYunExchangeRate(List lines) {
        // 无需处理
    }

    /**
     * 打开邮箱
     *
     * @param context
     * @param title
     * @param content
     * @param email
     */
    public void openEmail(Context context, String title, String content, String email) {
        try {
            Uri uri = Uri.parse("mailto:" + email);
            Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
            intent.putExtra(Intent.EXTRA_SUBJECT, title); // 主题
            intent.putExtra(Intent.EXTRA_TEXT, content); // 正文
            context.startActivity(Intent.createChooser(intent, getXmlString(R.string.share_start_select_email_txt)));
        } catch (Exception e) {
            e.printStackTrace();
            ToastUtils.showShort(R.string.share_start_email_txt);
        }
    }

}
