package com.qkl.online.mining.app.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.orhanobut.hawk.Hawk;
import com.qkl.online.mining.app.R;
import com.qkl.online.mining.app.application.AccountManager;
import com.qkl.online.mining.app.data.commons.Constants;
import com.qkl.online.mining.app.data.event.EventBase;
import com.qkl.online.mining.app.mvp.presenter.LoginPresenter;
import com.qkl.online.mining.app.mvp.view.ILoginView;
import com.qkl.online.mining.app.ui.BaseActivity;
import com.qkl.online.mining.app.utils.CommonsUtils;
import com.qkl.online.mining.app.utils.IntentUtil;
import com.qkl.online.mining.app.utils.ToastUtils;
import com.qkl.online.mining.app.utils.languagelib.LanguageType;
import com.qkl.online.mining.app.utils.languagelib.MultiLanguageUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author：oyb on 2018/8/29 12:31
 */
public class LoginActivity extends BaseActivity<LoginPresenter> implements ILoginView {

    @BindView(R.id.activity_login_headerview)
    View mBarView;
    @BindView(R.id.login_loginname_et)
    EditText mLoginNameET;
    @BindView(R.id.login_password_et)
    EditText mPasswordET;
    @BindView(R.id.login_see_password_view)
    ImageView mSeePasswordET;

    @BindView(R.id.login_set_language_txt)
    TextView mSetLanguageTxt;

    @Override
    protected void initPresenter(Intent intent) {
        mPresenter = new LoginPresenter(this, this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        setTitleBar(mBarView);
        setStatusTransparent();

        if (Hawk.contains(Constants.LOGIN_NAME_KEY)) {
            String loginName = Hawk.get(Constants.LOGIN_NAME_KEY).toString();
            mLoginNameET.setText(loginName);
            mLoginNameET.setSelection(loginName.length());
        }

        mSetLanguageTxt.setText(getLanguageValue());
    }

    @Override
    protected void initData() {
//        mLoginNameET.setText("944533800@qq.com");
//        mPasswordET.setText("111111");
//        mLoginNameET.setText("309191428@qq.com");
//        mPasswordET.setText("123456");
//        mLoginNameET.setText("jone988@protonmail.com");
//        mPasswordET.setText("123456");
//        mLoginNameET.setText("32339623@qq.com");
//        mPasswordET.setText("123456");

        AccountManager.getInstance().checkAppUpdate(this);
    }

    @Override
    protected void onEventCallback(EventBase eventBase) {
        super.onEventCallback(eventBase);
        String action = eventBase.getAction();
        if(Constants.UPDATE_KEY.equals(action)) {
            AccountManager.getInstance().checkAppUpdate(this);
        }
    }

    @OnClick(R.id.login_go_login)
    void goLogin() {
        String loginName = mLoginNameET.getText().toString().trim();
        String password = mPasswordET.getText().toString().trim();

        if (TextUtils.isEmpty(loginName)) {
            showToast(R.string.login_input_loginname);
            return;
        }
        if (TextUtils.isEmpty(password)) {
            showToast(R.string.login_input_password);
            return;
        }

        mPresenter.login(loginName, password);
    }

    private boolean isVisible;

    @OnClick(R.id.login_see_password_view)
    void seePassword() {
        if (isVisible) {
            mPasswordET.setTransformationMethod(PasswordTransformationMethod.getInstance());
        } else {
            mPasswordET.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        }
        mPasswordET.setSelection(mPasswordET.getText().toString().length());
        isVisible = !isVisible;
        checkYan();
    }

    @OnClick(R.id.login_to_forgetpassword_view)
    void forgetPassword() {
        // 忘记密码
        IntentUtil.ToForgetPasswordActivity(this);
    }

    @OnClick(R.id.login_to_register_view)
    void register() {
        // 注册
        IntentUtil.ToRegisterUserActivity(this);
    }

    @OnClick(R.id.login_set_language_txt)
    void setLanguage() {
        IntentUtil.ToSetLanguageActivity(this);
    }

    private void checkYan() {
        if (isVisible) {
            mSeePasswordET.setImageResource(R.drawable.login_icon_zhengyan);
        } else {
            mSeePasswordET.setImageResource(R.drawable.login_icon_biyan);
        }
    }

    @Override
    public void showLoading() {
        showLoading(true, getXmlString(R.string.login_loading_in));
    }

    @Override
    public void hideLoading() {
        showLoading(false);
    }

    @Override
    public void loginSuccess() {
        // 保存用户名
        Hawk.put(Constants.LOGIN_NAME_KEY, mLoginNameET.getText().toString().trim());
        Hawk.put(Constants.LOGIN_PASSWORD_KEY, mPasswordET.getText().toString().trim());

        hideLoading();
        IntentUtil.ToMainActivity(this);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == IntentUtil.ToSetLanguageActivityCode && resultCode == Activity.RESULT_OK) {
            recreate();
//            restartAct();
        }
    }

    private String getLanguageValue() {
        String str = "";
        int languageType = MultiLanguageUtil.getInstance().getLanguageType();
        switch (languageType) {
            case 1:
                str = getXmlString(R.string.language_type_simple_cn);
                break;
            case 2:
                str = getXmlString(R.string.language_type_traditional_cn);
                break;
            case 3:
                str = getXmlString(R.string.language_type_english);
                break;
        }
        return str;
    }

    /**
     * 重启当前Activity
     */
    private void restartAct() {
        Intent _Intent = new Intent(this, LoginActivity.class);
        startActivity(_Intent);        //清除Activity退出和进入的动画
        overridePendingTransition(0, 0);
        finish();
    }

}
