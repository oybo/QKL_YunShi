package com.qkl.online.mining.app.ui.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.widget.EditText;

import com.qkl.online.mining.app.R;
import com.qkl.online.mining.app.mvp.presenter.UpdatePasswordPresenter;
import com.qkl.online.mining.app.mvp.view.IUpdatePasswordView;
import com.qkl.online.mining.app.ui.BaseActivity;
import com.qkl.online.mining.app.utils.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author：oyb on 2018/8/29 12:31
 * 修改密码
 */
public class UpdatePasswordActivity extends BaseActivity<UpdatePasswordPresenter> implements IUpdatePasswordView {

    @BindView(R.id.activity_update_password_old_et)
    EditText mOldPassowrdET;
    @BindView(R.id.activity_update_password_new_et)
    EditText mNewPassowrdET;
    @BindView(R.id.activity_update_password_agani_et)
    EditText mNewPassowrdAganiET;

    @Override
    protected void initPresenter(Intent intent) {
        mPresenter = new UpdatePasswordPresenter(this, this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_update_password;
    }

    @Override
    protected void initView() {
        initTopBarOnlyTitle(R.id.activity_update_password_headerview, getXmlString(R.string.update_password_title));
    }

    @Override
    protected void initData() {

    }

    @OnClick(R.id.activity_update_password_submit_bt)
    void updatePassword() {
        String oldPassword = mOldPassowrdET.getText().toString().trim();
        String newPassword = mNewPassowrdET.getText().toString().trim();
        String newAganiPassword = mNewPassowrdAganiET.getText().toString().trim();

        if(TextUtils.isEmpty(oldPassword)) {
            ToastUtils.showShort(R.string.update_password_old_pwd);
            return;
        }
        if(TextUtils.isEmpty(newPassword) || TextUtils.isEmpty(newAganiPassword)) {
            ToastUtils.showShort(R.string.update_password_new_pwd);
            return;
        }
        if(!newPassword.equals(newAganiPassword)) {
            ToastUtils.showShort(R.string.update_password_new_pwd_noequals);
            return;
        }

        mPresenter.updatePassword(oldPassword, newPassword);
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
