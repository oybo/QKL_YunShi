package com.qkl.online.mining.app.ui.activity;

import android.content.Intent;
import android.widget.TextView;

import com.qkl.online.mining.app.R;
import com.qkl.online.mining.app.mvp.presenter.PublicPresenter;
import com.qkl.online.mining.app.mvp.view.IPublicView;
import com.qkl.online.mining.app.ui.BaseActivity;

import butterknife.BindView;

/**
 * author：oyb on 2018/9/14 15:05
 * 新闻公共的详情页
 */
public class NewsDetailActivity extends BaseActivity<PublicPresenter> implements IPublicView {

    @BindView(R.id.activity_news_detail_title)
    TextView mTitleTxt;
    @BindView(R.id.activity_news_detail_date)
    TextView mDateTxt;
    @BindView(R.id.activity_news_detail_content)
    TextView mContentTxt;

    private String mGroupName, mTitle, mDate, mContent;

    @Override
    protected void initPresenter(Intent intent) {
        mPresenter = new PublicPresenter(this, this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_news_detail;
    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        mGroupName = intent.getStringExtra("groupName");
        mTitle = intent.getStringExtra("title");
        mDate = intent.getStringExtra("date");
        mContent = intent.getStringExtra("content");

        initTopBarOnlyTitle(R.id.activity_news_detail_headerview, mGroupName);
    }

    @Override
    protected void initData() {
        mTitleTxt.setText(mTitle);
        mDateTxt.setText(mDate);
        mContentTxt.setText(mContent);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

}
