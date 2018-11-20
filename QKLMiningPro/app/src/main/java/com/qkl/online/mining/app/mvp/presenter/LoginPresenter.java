package com.qkl.online.mining.app.mvp.presenter;

import android.app.Activity;
import android.text.TextUtils;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.orhanobut.hawk.Hawk;
import com.qkl.online.mining.app.R;
import com.qkl.online.mining.app.application.AccountManager;
import com.qkl.online.mining.app.application.AppContext;
import com.qkl.online.mining.app.data.commons.Constants;
import com.qkl.online.mining.app.data.commons.UrlConfig;
import com.qkl.online.mining.app.data.json.JsonCallback;
import com.qkl.online.mining.app.mvp.view.ILoginView;
import com.qkl.online.mining.app.utils.CommonsUtils;
import com.qkl.online.mining.app.utils.ToastUtils;
import com.qkl.online.mining.app.utils.languagelib.MultiLanguageUtil;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 登录
 */

public class LoginPresenter extends BasePresenter<ILoginView> {

    public LoginPresenter(final Activity context, ILoginView view) {
        super(context, view);
    }

    public void login(String email, String password) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("email", email);
            jsonObject.put("password", password);
            jsonObject.put("language", CommonsUtils.getLanguage());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mView.showLoading();
        OkGo.<JSONObject>post(UrlConfig.getLoginUrl())
                .upJson(jsonObject)
                .execute(new JsonCallback<JSONObject>() {
                    @Override
                    public void onSuccess(Response<JSONObject> response) {
                        final JSONObject object = response.body();
                        if(object != null) {
                            int code = object.optInt("code", -1);
                            if(code == 0) {
                                // 成功
                                // 判断是否有获取到getYunPlanetConfigUrl接口
                                if(AccountManager.getInstance().getAppCommonsConfig() == null) {
                                    AccountManager.getInstance().getYunPlanetConfig(new JsonCallback() {
                                        @Override
                                        public void onSuccess(Response response) {
                                            callLoginSuccess(object);
                                        }

                                        @Override
                                        public void onError(Response response) {
                                            super.onError(response);
                                            callLoginError(object);
                                            mView.hideLoading();
                                        }
                                    });
                                    return;
                                }

                                callLoginSuccess(object);
                                return;
                            } else {
                                callLoginError(object);
                            }
                        }
                        mView.hideLoading();
                    }

                    @Override
                    public void onError(Response<JSONObject> response) {
                        super.onError(response);
                        try {
                            ToastUtils.showShort(response.getException().getMessage());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        mView.hideLoading();
                    }
                });
    }

    private void callLoginSuccess(JSONObject object) {
        JSONObject dataObject = object.optJSONObject("data");
        String access_token = dataObject.optString("access_token");
        AccountManager.getInstance().saveUser(access_token);
        // 回调
        mView.loginSuccess();
        // 登录之后获取用户信息
        AccountManager.getInstance().getUserInfo();
        // 用户账户信息余额
        AccountManager.getInstance().getUserYUNTData();
        // 获取实时汇率接口
        AccountManager.getInstance().getYunEchangeRate();
    }

    private void callLoginError(JSONObject object) {
        String msg = object.optString("msg",
                CommonsUtils.getXmlString(mContext, R.string.login_failed));
        ToastUtils.showShort(msg);
    }

}
