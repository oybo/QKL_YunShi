package com.qkl.online.mining.app.mvp.presenter;

import android.app.Activity;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.qkl.online.mining.app.R;
import com.qkl.online.mining.app.application.AccountManager;
import com.qkl.online.mining.app.data.commons.UrlConfig;
import com.qkl.online.mining.app.data.entity.MyStar;
import com.qkl.online.mining.app.data.entity.StarProduct;
import com.qkl.online.mining.app.data.json.Convert;
import com.qkl.online.mining.app.data.json.JsonCallback;
import com.qkl.online.mining.app.data.json.LzyResponse;
import com.qkl.online.mining.app.mvp.view.IStarView;
import com.qkl.online.mining.app.utils.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 *  首页
 */

public class StarPresenter extends BasePresenter<IStarView> {

    public StarPresenter(Activity context, IStarView view) {
        super(context, view);
    }

    /**
     * 获取我的星球
     */
    public void getMyStar() {
        OkGo.<JSONObject>post(UrlConfig.getMyStarListUrl(AccountManager.getInstance().getMemberId()))
                .tag(this)
                .headers("Authorization", AccountManager.getInstance().getAccountToken())
                .execute(new JsonCallback<JSONObject>() {
                    @Override
                    public void onSuccess(Response<JSONObject> response) {
                        JSONObject jsonObject = response.body();
                        if(jsonObject != null) {
                            jsonObject = jsonObject.optJSONObject("data");
                            MyStar myStar = Convert.forMyStar(jsonObject);
                            if(myStar != null) {
                                mView.resultMyStar(myStar);
                                return;
                            }
                        }
                        mView.resultMyStar(null);
                    }

                    @Override
                    public void onError(Response<JSONObject> response) {
                        super.onError(response);
                        mView.resultMyStar(null);
                    }
                });
    }

    /**
     * 获取矿机列表
     */
    public void getMineList() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("page", "1");
            jsonObject.put("limit", "10");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkGo.<LzyResponse<StarProduct>>post(UrlConfig.getMinerListUrl())
                .tag(this)
                .upJson(jsonObject)
                .headers("Authorization", AccountManager.getInstance().getAccountToken())
                .execute(new JsonCallback<LzyResponse<StarProduct>>() {
                    @Override
                    public void onSuccess(Response<LzyResponse<StarProduct>> response) {
                        if(response != null) {
                            LzyResponse lzyResponse = response.body();
                            if(lzyResponse != null) {
                                StarProduct starProduct = (StarProduct) lzyResponse.data;
                                if(starProduct != null) {
                                    mView.resultMine(starProduct);
                                }
                            }
                        }
                    }

                    @Override
                    public void onError(Response<LzyResponse<StarProduct>> response) {
                        super.onError(response);
                    }
                });
    }

    /**
     * 今日剩余星球数
     */
    public void todayStarRestNumber() {
        OkGo.<JSONObject>post(UrlConfig.getTodayStarRestNumberUrl())
                .tag(this)
                .headers("Authorization", AccountManager.getInstance().getAccountToken())
                .execute(new JsonCallback<JSONObject>() {
                    @Override
                    public void onSuccess(Response<JSONObject> response) {
                        JSONObject object = response.body();
                        if(object != null) {
                            int code = object.optInt("code", -1);
                            if(code == 0) {
                                int data = object.optInt("data");
                                mView.resultStarRestNumber(data);
                                return;
                            }
                        }
                        mView.resultStarRestNumber(0);
                    }

                    @Override
                    public void onError(Response<JSONObject> response) {
                        super.onError(response);
                        mView.resultStarRestNumber(0);
                    }
                });
    }

    /**
     * 购买星球
     */
    public void purchaseStar(String minerId) {
        mView.showLoading();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("memberId", String.valueOf(AccountManager.getInstance().getMemberId()));
            jsonObject.put("minerId", minerId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkGo.<JSONObject>post(UrlConfig.getPurchaseStarUrl())
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
                                // 购买成功
                                ToastUtils.showShort(R.string.purchase_star_puchase_success_bt);
                                mView.resultPurchaseStar();
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
