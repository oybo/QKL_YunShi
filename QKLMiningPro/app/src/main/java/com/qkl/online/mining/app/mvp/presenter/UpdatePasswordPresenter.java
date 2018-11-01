package com.qkl.online.mining.app.mvp.presenter;

import android.app.Activity;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.qkl.online.mining.app.R;
import com.qkl.online.mining.app.application.AccountManager;
import com.qkl.online.mining.app.data.commons.UrlConfig;
import com.qkl.online.mining.app.data.json.JsonCallback;
import com.qkl.online.mining.app.mvp.view.IUpdatePasswordView;
import com.qkl.online.mining.app.utils.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 *  修改密码
 */

public class UpdatePasswordPresenter extends BasePresenter<IUpdatePasswordView> {

    public UpdatePasswordPresenter(Activity context, IUpdatePasswordView view) {
        super(context, view);
    }

    /**
     * 修改密码
     * @param oldPassword
     * @param newPassword
     */
    public void updatePassword(String oldPassword, String newPassword) {
        mView.showLoading();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("email", AccountManager.getInstance().getEmail());
            jsonObject.put("password", newPassword);
            jsonObject.put("oldPassword", oldPassword);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkGo.<JSONObject>post(UrlConfig.getChangePasswordUrl())
                .tag(this)
                .headers("Authorization", AccountManager.getInstance().getAccountToken())
                .upJson(jsonObject)
                .execute(new JsonCallback<JSONObject>() {
                    @Override
                    public void onSuccess(Response<JSONObject> response) {
                        mView.hideLoading();
                        JSONObject object = response.body();
                        if(object != null) {
                            int code = object.optInt("code", -1);
                            if(code == 0) {
                                // 修改成功
                                ToastUtils.showShort(R.string.please_forget_pwd_update_success);
                            } else {
                                String msg = object.optString("msg");
                                ToastUtils.showShort(msg);
                            }
                        }
                    }

                    @Override
                    public void onError(Response<JSONObject> response) {
                        super.onError(response);
                        ToastUtils.showShort(response.getException().getMessage());
                        mView.hideLoading();
                    }
                });
    }

    /**
     * 修改支付密码
     * @param oldPassword
     * @param newPassword
     */
    public void updatePayPassword(String oldPassword, String newPassword) {
        mView.showLoading();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("email", AccountManager.getInstance().getEmail());
            jsonObject.put("payPassword", newPassword);
            jsonObject.put("oldPayPass", oldPassword);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkGo.<JSONObject>post(UrlConfig.getChangePayPasswordUrl())
                .tag(this)
                .headers("Authorization", AccountManager.getInstance().getAccountToken())
                .upJson(jsonObject)
                .execute(new JsonCallback<JSONObject>() {
                    @Override
                    public void onSuccess(Response<JSONObject> response) {
                        mView.hideLoading();
                        JSONObject object = response.body();
                        if(object != null) {
                            int code = object.optInt("code", -1);
                            if(code == 0) {
                                // 修改成功
                                ToastUtils.showShort(R.string.please_forget_pwd_update_success);
                            } else {
                                String msg = object.optString("msg");
                                ToastUtils.showShort(msg);
                            }
                        }
                    }

                    @Override
                    public void onError(Response<JSONObject> response) {
                        super.onError(response);
                        ToastUtils.showShort(response.getException().getMessage());
                        mView.hideLoading();
                    }
                });
    }

}
