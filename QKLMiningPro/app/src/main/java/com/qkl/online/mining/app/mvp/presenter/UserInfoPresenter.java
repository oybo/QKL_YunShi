package com.qkl.online.mining.app.mvp.presenter;

import android.app.Activity;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.qkl.online.mining.app.R;
import com.qkl.online.mining.app.application.AccountManager;
import com.qkl.online.mining.app.data.commons.UrlConfig;
import com.qkl.online.mining.app.data.json.JsonCallback;
import com.qkl.online.mining.app.mvp.view.IUserInfoView;
import com.qkl.online.mining.app.utils.ToastUtils;

import org.json.JSONObject;

/**
 *  个人信息中心
 */

public class UserInfoPresenter extends BasePresenter<IUserInfoView> {

    public UserInfoPresenter(Activity context, IUserInfoView view) {
        super(context, view);
    }

    /**
     * YUNT兑换YUN   --   YUNT提币接口
     memberId	是	long	用户ID
     inputAmount	是	decimal	输入的YUNT数量
     exchangeFee	是	decimal	手续费
     payPassword	是	string	支付密码
     */
    public void yunt2yun(String inputAmount, String exchangeFee, String payPassword) {
        mView.showLoading();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("memberId", AccountManager.getInstance().getMemberId());
            jsonObject.put("inputAmount", inputAmount);
            jsonObject.put("exchangeFee", exchangeFee);
            jsonObject.put("payPassword", payPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
        OkGo.<JSONObject>post(UrlConfig.getYunt2YunUrl())
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
                                ToastUtils.showShort(R.string.parities_ynu_success_txt);
                                // 兑换成功刷新我的钱包
                                AccountManager.getInstance().getUserYUNTData();
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
                        ToastUtils.showShort(response.getException().getMessage());
                    }
                });
    }

    public void logOut() {
        mView.showLoading();
        OkGo.<JSONObject>post(UrlConfig.getLogOutUrl())
                .tag(this)
                .headers("Authorization", AccountManager.getInstance().getAccountToken())
                .execute(new JsonCallback<JSONObject>() {
                    @Override
                    public void onSuccess(Response<JSONObject> response) {
                        mView.hideLoading();
                        mView.logOut();
                    }

                    @Override
                    public void onError(Response<JSONObject> response) {
                        super.onError(response);
                        mView.hideLoading();
                        mView.logOut();
                    }
                });
    }

}
