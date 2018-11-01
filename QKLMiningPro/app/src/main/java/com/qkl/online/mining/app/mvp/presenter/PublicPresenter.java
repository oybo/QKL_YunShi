package com.qkl.online.mining.app.mvp.presenter;

import android.app.Activity;
import android.text.TextUtils;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.qkl.online.mining.app.R;
import com.qkl.online.mining.app.application.AccountManager;
import com.qkl.online.mining.app.data.commons.Constants;
import com.qkl.online.mining.app.data.commons.UrlConfig;
import com.qkl.online.mining.app.data.entity.User;
import com.qkl.online.mining.app.data.event.EventBase;
import com.qkl.online.mining.app.data.json.JsonCallback;
import com.qkl.online.mining.app.mvp.view.IPublicView;
import com.qkl.online.mining.app.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 *  公共的
 */

public class PublicPresenter extends BasePresenter<IPublicView> {

    public PublicPresenter(Activity context, IPublicView view) {
        super(context, view);
    }

    /**
     * 提交个人信息
     * @param iconFile
     * @param nickName
     * @param mobile
     */
    public void updateUserInfo(String iconFile, String nickName, String mobile) {
        if(!TextUtils.isEmpty(iconFile)) {
            // 头像上传
            uploadIcon(iconFile);
        }

        if(TextUtils.isEmpty(nickName)) {
            return;
        }
        User user = AccountManager.getInstance().getUser();
        if(user != null && nickName.equals(user.getNickName())) {
            return;
        }
        setUserInfo(nickName, mobile);
    }

    /**
     * 上传头像
     * @param iconFile
     */
    private void uploadIcon(String iconFile) {
        mView.showLoading();
        List<File> files = new ArrayList<>();
        files.add(new File(iconFile));
        OkGo.<JSONObject>post(UrlConfig.getUploadProfileUrl(AccountManager.getInstance().getMemberId()))
                .tag(this)
                .headers("Authorization", AccountManager.getInstance().getAccountToken())
                .headers("Content-Type", "multipart/form-data")
                .addFileParams("file", files)
                .execute(new JsonCallback<JSONObject>() {
                    @Override
                    public void onSuccess(Response<JSONObject> response) {
                        mView.hideLoading();
                        JSONObject object = response.body();
                        if(object != null) {
                            int code = object.optInt("code", -1);
                            if(code == 0) {
                                // 成功
                                String url = object.optString("url");
                                if(!TextUtils.isEmpty(url)) {
                                    User user = AccountManager.getInstance().getUser();
                                    user.setProfilePicture(url);
                                    AccountManager.getInstance().setUser(user);

                                    EventBase eventBase = new EventBase();
                                    eventBase.setAction(Constants.REFRESH_USER_INFO_KEY);
                                    EventBus.getDefault().post(eventBase);
                                }
                            }
                        }
                    }

                    @Override
                    public void onError(Response<JSONObject> response) {
                        super.onError(response);
                        mView.hideLoading();
                    }
                });
    }

    /**
     * 设置昵称-手机号
     * @param nickName
     * @param mobile
     */
    public void setUserInfo(final String nickName, final String mobile) {
        mView.showLoading();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("email", AccountManager.getInstance().getEmail());
            if(!TextUtils.isEmpty(nickName)) {
                jsonObject.put("nickName", nickName);
            }
//            if(!TextUtils.isEmpty(mobile)) {
                jsonObject.put("mobile", "");
//            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkGo.<JSONObject>post(UrlConfig.getSetUserInfoUrl())
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

                                User user = AccountManager.getInstance().getUser();
                                user.setNickName(nickName);
                                user.setMobile(mobile);
                                AccountManager.getInstance().setUser(user);

                                EventBase eventBase = new EventBase();
                                eventBase.setAction(Constants.REFRESH_USER_INFO_KEY);
                                EventBus.getDefault().post(eventBase);
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

    public void setYunAddress(final String yunAddress, String payPassword) {
        mView.showLoading();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("email", AccountManager.getInstance().getEmail());
            jsonObject.put("yunAddress", yunAddress);
            jsonObject.put("payPassword", payPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }

        OkGo.<JSONObject>post(UrlConfig.getYunAddressUrl())
                .tag(this)
                .upJson(jsonObject)
                .headers("Authorization", AccountManager.getInstance().getAccountToken())
                .execute(new JsonCallback<JSONObject>() {
                    @Override
                    public void onSuccess(Response<JSONObject> response) {
                        mView.hideLoading();
                        JSONObject object = response.body();
                        if(object != null) {
                            int code = object.optInt("code", -1);
                            if(code == 0) {
                                // 成功
                                ToastUtils.showShort(R.string.set_success);
                                // 地址设置成功后刷新个人信息的我的地址
                                User user = AccountManager.getInstance().getUser();
                                user.setEtcAddress(yunAddress);
                                AccountManager.getInstance().setUser(user);
                                // 发送广播
                                EventBase eventBase = new EventBase();
                                eventBase.setAction(Constants.REFRESH_USER_INFO_KEY);
                                EventBus.getDefault().post(eventBase);
                            } else {
                                String msg = object.optString("msg");
                                ToastUtils.showShort(msg);
                            }
                        }
                    }

                    @Override
                    public void onError(Response<JSONObject> response) {
                        super.onError(response);
                        mView.hideLoading();
                    }
                });
    }

}
