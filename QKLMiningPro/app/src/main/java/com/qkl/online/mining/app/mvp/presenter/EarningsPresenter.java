package com.qkl.online.mining.app.mvp.presenter;

import android.app.Activity;
import android.text.TextUtils;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.qkl.online.mining.app.application.AccountManager;
import com.qkl.online.mining.app.data.commons.UrlConfig;
import com.qkl.online.mining.app.data.entity.Earnings;
import com.qkl.online.mining.app.data.json.Convert;
import com.qkl.online.mining.app.data.json.JsonCallback;
import com.qkl.online.mining.app.mvp.view.IEarningsView;

import org.json.JSONException;
import org.json.JSONObject;

/**
 *  收益详情
 */

public class EarningsPresenter extends BasePresenter<IEarningsView> {

    public static final String KJSY_KEY = "1002";
    public static final String JDSY_KEY = "1005";
    public static final String ZJDSY_KEY = "1003";

    public EarningsPresenter(Activity context, IEarningsView view) {
        super(context, view);
    }

    /**
     * 获取收益详情
     operateCode  1002 收入-挖矿收益
                  1003 收入-邀请购买矿机提成=总节点收益
                  1005 收入-邀请挖矿收益奖励=节点收益
     */
    public void getEarnings(String operateCode, int currentPage) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("page", String.valueOf(currentPage));
            jsonObject.put("limit", "10");
            jsonObject.put("memberId", AccountManager.getInstance().getMemberId());
            if(!TextUtils.isEmpty(operateCode)) {
                jsonObject.put("operateCode", operateCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        OkGo.<String>post(UrlConfig.getJieDianEarningsUrl())
                .tag(this)
                .upJson(jsonObject)
                .headers("Authorization", AccountManager.getInstance().getAccountToken())
                .execute(new JsonCallback<String>() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if(response != null) {
                            String result = response.body();
                            if(!TextUtils.isEmpty(result)) {
                                try {
                                    JSONObject object = new JSONObject(result);
                                    object = object.optJSONObject("data");
                                    Earnings earnings =  Convert.forEarnings(object);
                                    if(earnings != null) {
                                        mView.resultEarnings(earnings);
                                        return;
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        mView.resultEarnings(null);
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        mView.resultEarnings(null);
                    }
                });
    }

}
