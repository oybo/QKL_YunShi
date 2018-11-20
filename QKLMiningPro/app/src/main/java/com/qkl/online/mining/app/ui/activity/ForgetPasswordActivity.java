package com.qkl.online.mining.app.ui.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.widget.EditText;

import com.qkl.online.mining.app.R;
import com.qkl.online.mining.app.mvp.presenter.RegisterPresenter;
import com.qkl.online.mining.app.mvp.view.IRegisterView;
import com.qkl.online.mining.app.ui.BaseActivity;
import com.qkl.online.mining.app.ui.view.CountDownTextView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author：oyb on 2018/8/31 17:30
 * 忘记密码-找回
 */
public class ForgetPasswordActivity extends BaseActivity<RegisterPresenter> implements IRegisterView {

    @BindView(R.id.forget_password_get_code_txt)
    CountDownTextView mCountDownTextView;

    @BindView(R.id.forget_pay_pwd_email_et)
    EditText mEmailEditText;
    @BindView(R.id.forget_pay_pwd_code_et)
    EditText mCodeEditText;
    @BindView(R.id.forget_pay_pwd_new_et)
    EditText mNewPwdEditText;
    @BindView(R.id.forget_pay_pwd_new_again_et)
    EditText mAgainEditText;

    @Override
    protected void initPresenter(Intent intent) {
        mPresenter = new RegisterPresenter(this, this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_forget_password;
    }

    @Override
    protected void initView() {
        initTopBarOnlyTitle(R.id.activity_forget_pwd_headerview, getXmlString(R.string.forget_pwd_page_title));
    }

    @Override
    protected void initData() {

    }

    @OnClick(R.id.forget_password_get_code_txt)
    void getMsgCode() {
        // 获取验证码
        String email = mEmailEditText.getText().toString().trim();
        if(TextUtils.isEmpty(email)) {
            showToast(R.string.register_email_unable_null);
            return;
        }

        if(!mCountDownTextView.isCountDown()) {
            mPresenter.getCode(email, 2, mCountDownTextView);
        }
    }

    @OnClick(R.id.forget_pay_pwd_update_view)
    void updatePassword() {
        // 执行修改
        String email = mEmailEditText.getText().toString().trim();
        if(TextUtils.isEmpty(email)) {
            showToast(R.string.register_email_unable_null);
            return;
        }
        String code = mCodeEditText.getText().toString().trim();
        if(TextUtils.isEmpty(code)) {
            showToast(R.string.register_input_code);
            return;
        }
        String newPwd = mNewPwdEditText.getText().toString().trim();
        if(TextUtils.isEmpty(newPwd)) {
            showToast(R.string.please_forget_pwd_input_new_pwd);
            return;
        }
        String newPwdAgain = mAgainEditText.getText().toString().trim();
        if(!newPwd.equals(newPwdAgain)) {
            showToast(R.string.please_forget_pwd_pwd_error);
            return;
        }
        mPresenter.forgetPassword(email, code, newPwd);
    }

    @Override
    public void showLoading() {
        showLoading(true);
    }

    @Override
    public void hideLoading() {
        showLoading(false);
    }

    @Override
    public void result(boolean success) {
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCountDownTextView.onDestroy();
    }
}
