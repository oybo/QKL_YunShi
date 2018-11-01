package com.qkl.online.mining.app.ui.activity;

import android.content.Intent;
import android.widget.TextView;

import com.qkl.online.mining.app.R;
import com.qkl.online.mining.app.mvp.presenter.PublicPresenter;
import com.qkl.online.mining.app.mvp.view.IPublicView;
import com.qkl.online.mining.app.ui.BaseActivity;
import com.qkl.online.mining.app.utils.CommonsUtils;
import com.qkl.online.mining.app.utils.IntentUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author：oyb on 2018/9/1 02:32
 */
public class SetActivity extends BaseActivity<PublicPresenter> implements IPublicView {

    @BindView(R.id.set_version_txt)
    TextView mVersionTxt;

    @Override
    protected void initPresenter(Intent intent) {
        mPresenter = new PublicPresenter(this, this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_set;
    }

    @Override
    protected void initView() {
        initTopBarOnlyTitle(R.id.activity_set_headerview, getXmlString(R.string.act_set_title_txt));
    }

    @Override
    protected void initData() {
        mVersionTxt.setText(getXmlString(R.string.set_version_txt, CommonsUtils.getSoftVersionName(this)));
    }

    @OnClick(R.id.set_userinfo_layout)
    void setUserInfo() {
        IntentUtil.ToSetUserInfoActivity(this);
    }

    @OnClick(R.id.set_login_password_layout)
    void setLoginPassword() {
        IntentUtil.ToUpdatePasswordActivity(this);
    }

    @OnClick(R.id.set_pay_password_layout)
    void setPayPassword() {
        IntentUtil.ToUpdatePayPasswordActivity(this);
    }

    @OnClick(R.id.set_wallet_address_layout)
    void setWalletAddress() {
        IntentUtil.ToSetYunActivity(this);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
