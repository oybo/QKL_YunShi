package com.qkl.online.mining.app.mvp.presenter;

import android.app.Activity;
import android.text.TextUtils;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.qkl.online.mining.app.application.AccountManager;
import com.qkl.online.mining.app.data.commons.UrlConfig;
import com.qkl.online.mining.app.data.entity.Exchange;
import com.qkl.online.mining.app.data.entity.ExchangeDetail;
import com.qkl.online.mining.app.data.json.Convert;
import com.qkl.online.mining.app.data.json.JsonCallback;
import com.qkl.online.mining.app.mvp.view.IExchangeListView;

import org.json.JSONObject;

/**
 * 收益详情
 */

public class ExchangeListPresenter extends BasePresenter<IExchangeListView> {

    public static final String YUN2YUNT = "1001";
    public static final String YUNT2YUN = "2001";

    public ExchangeListPresenter(Activity context, IExchangeListView view) {
        super(context, view);
    }

    /**
     * YUNT--YUN兑换账单接口
     */
    public void getExchangeList(String exchangeType, int currentPage) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("page", String.valueOf(currentPage));
            jsonObject.put("limit", "10");
            jsonObject.put("memberId", AccountManager.getInstance().getMemberId());
            jsonObject.put("exchangeType", exchangeType); // 1001 yun兑换yunt，2001 yunt兑换yun
        } catch (Exception e) {
            e.printStackTrace();
        }
        OkGo.<JSONObject>post(UrlConfig.getExchangeListUrl())
                .tag(this)
                .upJson(jsonObject)
                .headers("Authorization", AccountManager.getInstance().getAccountToken())
                .execute(new JsonCallback<JSONObject>() {
                    @Override
                    public void onSuccess(Response<JSONObject> response) {
                        if (response != null) {
                            JSONObject object = response.body();
                            if (object != null) {
                                object = object.optJSONObject("data");
                                Exchange exchange = Convert.forExchange(object);
                                if (exchange != null) {
                                    mView.resultExchange(exchange);
                                    return;
                                }
                            }
                        }
                        mView.resultExchange(null);
                    }

                    @Override
                    public void onError(Response<JSONObject> response) {
                        super.onError(response);
                        mView.resultExchange(null);
                    }
                });
    }

    /**
     * YUNT--YUN兑换账单接口-详情
     */
    public void getExchangeDetail(String txtId) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("txtId", TextUtils.isEmpty(txtId) ? "" : txtId);
            jsonObject.put("coinType", "yun");
        } catch (Exception e) {
            e.printStackTrace();
        }
        OkGo.<JSONObject>post(UrlConfig.getExchangeDetailUrl())
                .tag(this)
                .upJson(jsonObject)
                .headers("Authorization", AccountManager.getInstance().getAccountToken())
                .execute(new JsonCallback<JSONObject>() {
                    @Override
                    public void onSuccess(Response<JSONObject> response) {
                        if (response != null) {
                            JSONObject object = response.body();
                            if (object != null) {
                                object = object.optJSONObject("data");
                                ExchangeDetail exchangeDetail = Convert.forExchangeDetail(object);
                                if (exchangeDetail != null) {
                                    mView.resultExchangeDetail(exchangeDetail);
                                    return;
                                }
                            }
                        }
                        mView.resultExchangeDetail(null);
                    }

                    @Override
                    public void onError(Response<JSONObject> response) {
                        super.onError(response);
                        mView.resultExchangeDetail(null);
                    }
                });
    }

}
