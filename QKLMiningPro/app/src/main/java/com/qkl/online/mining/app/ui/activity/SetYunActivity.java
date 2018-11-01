package com.qkl.online.mining.app.ui.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.widget.EditText;

import com.qkl.online.mining.app.R;
import com.qkl.online.mining.app.application.AccountManager;
import com.qkl.online.mining.app.data.entity.User;
import com.qkl.online.mining.app.mvp.presenter.PublicPresenter;
import com.qkl.online.mining.app.mvp.view.IPublicView;
import com.qkl.online.mining.app.ui.BaseActivity;
import com.qkl.online.mining.app.utils.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author：oyb on 2018/9/5 02:51
 * 设置YUN地址
 */
public class SetYunActivity extends BaseActivity<PublicPresenter> implements IPublicView {

    @BindView(R.id.set_wallet_address_et)
    EditText mWalletAddressET;
    @BindView(R.id.set_input_code_et)
    EditText mPasswordCodeET;

    @Override
    protected void initPresenter(Intent intent) {
        mPresenter = new PublicPresenter(this, this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_set_yun;
    }

    @Override
    protected void initView() {
        initTopBarOnlyTitle(R.id.activity_set_yun_headerview, getXmlString(R.string.act_set_yun_title_txt));
    }

    @Override
    protected void initData() {
        User user = AccountManager.getInstance().getUser();
        if(user != null && !TextUtils.isEmpty(user.getEtcAddress())) {
            mWalletAddressET.setHint(user.getEtcAddress());
        }
    }

    @OnClick(R.id.set_save_update)
    void update() {
        String address = mWalletAddressET.getText().toString().trim();
        String code = mPasswordCodeET.getText().toString().trim();
        if(TextUtils.isEmpty(address)) {
            ToastUtils.showShort(R.string.act_set_wallet_address_txt);
            return;
        }
        if(TextUtils.isEmpty(code)) {
            ToastUtils.showShort(R.string.act_set_input_money_code_txt);
            return;
        }

        mPresenter.setYunAddress(address, code);
    }

    @Override
    public void showLoading() {
        showLoading(true);
    }

    @Override
    public void hideLoading() {
        showLoading(false);
    }

}
