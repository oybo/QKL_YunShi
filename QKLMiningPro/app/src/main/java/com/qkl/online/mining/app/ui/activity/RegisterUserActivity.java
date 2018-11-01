package com.qkl.online.mining.app.ui.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.widget.CheckBox;
import android.widget.EditText;

import com.qkl.online.mining.app.R;
import com.qkl.online.mining.app.data.commons.UrlConfig;
import com.qkl.online.mining.app.mvp.presenter.RegisterPresenter;
import com.qkl.online.mining.app.mvp.view.IRegisterView;
import com.qkl.online.mining.app.ui.BaseActivity;
import com.qkl.online.mining.app.ui.view.CountDownTextView;
import com.qkl.online.mining.app.utils.IntentUtil;
import com.qkl.online.mining.app.utils.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author：oyb on 2018/8/29 12:31
 */
public class RegisterUserActivity extends BaseActivity<RegisterPresenter> implements IRegisterView {

    @BindView(R.id.register_email_txt)
    EditText mEmailET;
    @BindView(R.id.register_msg_code_txt)
    EditText mMsgCodeET;
    @BindView(R.id.register_password_txt)
    EditText mPasswordET;
    @BindView(R.id.register_tribute_txt)
    EditText mTributeET;

    @BindView(R.id.register_xieyi_checkbox)
    CheckBox mXieYiCheckBox;

    @BindView(R.id.register_get_code_txt)
    CountDownTextView mCountDownTextView;

    @Override
    protected void initPresenter(Intent intent) {
        mPresenter = new RegisterPresenter(this, this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_register_user;
    }

    @Override
    protected void initView() {
        initTopBarOnlyTitle(R.id.activity_register_user_headerview, getXmlString(R.string.register_page_title));

    }

    @Override
    protected void initData() {

    }

    @OnClick(R.id.register_get_code_txt)
    void getMsgCode() {
        // 获取验证码
        String email = mEmailET.getText().toString().trim();
        if(TextUtils.isEmpty(email)) {
            showToast(R.string.register_email_unable_null);
            return;
        }

        if(!mCountDownTextView.isCountDown()) {
            mCountDownTextView.startTimer();
            mPresenter.getCode(email, 1);
        }
    }

    @OnClick(R.id.register_execute_view)
    void goRegister() {
        // 执行注册
        String email = mEmailET.getText().toString().trim();
        String msgCode = mMsgCodeET.getText().toString().trim();
        String password = mPasswordET.getText().toString().trim();
        String tribute = mTributeET.getText().toString().trim();

        if(TextUtils.isEmpty(email)) {
            showToast(R.string.register_email_unable_null);
            return;
        }
        if(TextUtils.isEmpty(msgCode)) {
            showToast(R.string.register_code_unable_null);
            return;
        }
        if(TextUtils.isEmpty(password)) {
            showToast(R.string.register_password_unable_null);
            return;
        }
//        if(TextUtils.isEmpty(tribute)) {
//            showToast(R.string.register_input_tribute);
//            return;
//        }
        if(!mXieYiCheckBox.isChecked()) {
            showToast(R.string.register_xieyi_select);
            return;
        }

        mPresenter.register(email, msgCode, password, tribute);
    }

    @OnClick(R.id.register_xieyi)
    void xieyi() {
        IntentUtil.ToWebViewActivity(this, getXmlString(R.string.register_xieyi_tip), UrlConfig.getRegisterXieYi());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCountDownTextView.onDestroy();
    }

    @Override
    public void result(boolean success) {
        finish();
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
