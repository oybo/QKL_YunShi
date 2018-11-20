package com.qkl.online.mining.app.mvp.presenter;

import android.app.Activity;
import android.text.TextUtils;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.qkl.online.mining.app.R;
import com.qkl.online.mining.app.application.AccountManager;
import com.qkl.online.mining.app.application.AppContext;
import com.qkl.online.mining.app.data.commons.UrlConfig;
import com.qkl.online.mining.app.data.json.JsonCallback;
import com.qkl.online.mining.app.mvp.view.IRegisterView;
import com.qkl.online.mining.app.ui.view.CountDownTextView;
import com.qkl.online.mining.app.utils.CommonsUtils;
import com.qkl.online.mining.app.utils.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 注册用户
 */

public class RegisterPresenter extends BasePresenter<IRegisterView> {

    public RegisterPresenter(Activity context, IRegisterView view) {
        super(context, view);
    }

    /**
     * 获取验证码
     * type
     * 1=注册
     * 2=找回密码
     * 3=找回支付密码
     */
    public void getCode(String email, int type, final CountDownTextView countDownTextView) {
        mView.showLoading();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("email", email);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String url;
        if (type == 1) {
            url = UrlConfig.getSendCodeUrl();
        } else if (type == 2) {
            url = UrlConfig.getSendCodeByForgetUrl();
        } else {
            url = UrlConfig.getSendCodeByForgetUrl();
        }
        OkGo.<JSONObject>post(url)
                .tag(url)
                .upJson(jsonObject)
                .headers("language", CommonsUtils.getLanguage())
                .execute(new JsonCallback<JSONObject>() {
                    @Override
                    public void onSuccess(Response<JSONObject> response) {
                        mView.hideLoading();
                        if (response != null) {
                            JSONObject object = response.body();
                            if (object != null) {
                                int code = object.optInt("code", -1);
                                if (code == 0) {
                                    // 成功
                                    ToastUtils.showShort(R.string.register_send_msg_success);
                                    if (countDownTextView != null) {
                                        countDownTextView.startTimer();
                                    }
                                } else {
                                    // 失败
                                    ToastUtils.showShort(R.string.register_send_msg_failed);
                                }
                            }
                        }
                    }

                    @Override
                    public void onError(Response<JSONObject> response) {
                        super.onError(response);
                        mView.hideLoading();
                        ToastUtils.showShort(R.string.register_send_msg_failed);
                    }
                });
    }

    /**
     * 提交注册
     *
     * @param email
     * @param msgCode
     * @param password
     */
    public void register(String email, String msgCode, String password, String tribute) {
        mView.showLoading();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("email", email);
            jsonObject.put("password", password);
            jsonObject.put("captcha", msgCode);
            jsonObject.put("tribute", tribute);
            jsonObject.put("imei", CommonsUtils.getDevId(AppContext.getInstance()));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkGo.<JSONObject>post(UrlConfig.getRegisterUserUrl())
                .tag(this)
                .headers("language", CommonsUtils.getLanguage())
                .upJson(jsonObject)
                .execute(new JsonCallback<JSONObject>() {
                    @Override
                    public void onSuccess(Response<JSONObject> response) {
                        mView.hideLoading();
                        if (response != null) {
                            JSONObject object = response.body();
                            int code = object.optInt("code", -1);
                            if (code == 0) {
                                // 成功
                                mView.result(true);
                                ToastUtils.showShort(R.string.register_success);
                            } else {
                                String msg = object.optString("msg");
                                // 失败
                                ToastUtils.showShort(!TextUtils.isEmpty(msg) ? msg :
                                        CommonsUtils.getXmlString(mContext, R.string.register_failed));
                            }
                        }
                    }

                    @Override
                    public void onError(Response<JSONObject> response) {
                        super.onError(response);
                        mView.hideLoading();
                        ToastUtils.showShort(response.getException().getMessage());
                    }
                });
    }

    /**
     * 忘记密码，重置
     *
     * @param email
     * @param code
     * @param newPassword
     */
    public void forgetPassword(String email, String code, String newPassword) {
        mView.showLoading();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("email", email);
            jsonObject.put("password", newPassword);
            jsonObject.put("captcha", code);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkGo.<JSONObject>post(UrlConfig.getForgetPasswordUrl())
                .tag(this)
                .upJson(jsonObject)
                .headers("language", CommonsUtils.getLanguage())
                .execute(new JsonCallback<JSONObject>() {
                    @Override
                    public void onSuccess(Response<JSONObject> response) {
                        mView.hideLoading();
                        JSONObject object = response.body();
                        if (object != null) {
                            int code = object.optInt("code", -1);
                            if (code == 0) {
                                ToastUtils.showShort(R.string.please_forget_pwd_update_success);
                                mView.result(true);
                            } else {
                                ToastUtils.showShort(R.string.please_forget_pwd_update_failed);
                            }
                        }
                    }

                    @Override
                    public void onError(Response<JSONObject> response) {
                        super.onError(response);
                        mView.hideLoading();
                        ToastUtils.showShort(response.getException().getMessage());
                    }
                });
    }

    /**
     * 忘记支付密码，重置
     *
     * @param email
     * @param code
     * @param newPassword
     */
    public void forgetPayPassword(String email, String code, String newPassword) {
        mView.showLoading();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("email", email);
            jsonObject.put("payPassword", newPassword);
            jsonObject.put("captcha", code);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkGo.<JSONObject>post(UrlConfig.getForgetPayPasswordUrl())
                .tag(this)
                .headers("Authorization", AccountManager.getInstance().getAccountToken())
                .upJson(jsonObject)
                .execute(new JsonCallback<JSONObject>() {
                    @Override
                    public void onSuccess(Response<JSONObject> response) {
                        mView.hideLoading();
                        JSONObject object = response.body();
                        if (object != null) {
                            int code = object.optInt("code", -1);
                            if (code == 0) {
                                ToastUtils.showShort(R.string.please_forget_pwd_update_success);
                                mView.result(true);
                            } else {
                                ToastUtils.showShort(R.string.please_forget_pwd_update_failed);
                            }
                        }
                    }

                    @Override
                    public void onError(Response<JSONObject> response) {
                        super.onError(response);
                        mView.hideLoading();
                        ToastUtils.showShort(response.getException().getMessage());
                    }
                });
    }

}
