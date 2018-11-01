package com.qkl.online.mining.app.mvp.presenter;

import android.app.Activity;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.qkl.online.mining.app.application.AccountManager;
import com.qkl.online.mining.app.data.commons.UrlConfig;
import com.qkl.online.mining.app.data.entity.BannerBean;
import com.qkl.online.mining.app.data.entity.HomeNews;
import com.qkl.online.mining.app.data.json.JsonCallback;
import com.qkl.online.mining.app.data.json.LzyResponse;
import com.qkl.online.mining.app.mvp.view.IHomeView;
import com.qkl.online.mining.app.utils.TimeUtils;
import com.qkl.online.mining.app.utils.suitlines.Unit;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 首页
 */

public class HomePresenter extends BasePresenter<IHomeView> {

    public HomePresenter(Activity context, IHomeView view) {
        super(context, view);
    }

    /**
     * banner
     * groupId:
     * 1001 = 首页Banner
     * 1002 = 游戏Banner
     */
    public void getBanner() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("page", "1");
            jsonObject.put("limit", "10");
            jsonObject.put("groupId", "1001");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkGo.<LzyResponse<BannerBean>>post(UrlConfig.getHomeBanner())
                .tag(this)
                .upJson(jsonObject)
                .headers("Authorization", AccountManager.getInstance().getAccountToken())
                .execute(new JsonCallback<LzyResponse<BannerBean>>() {
                    @Override
                    public void onSuccess(Response<LzyResponse<BannerBean>> response) {
                        if (response != null) {
                            LzyResponse lzyResponse = response.body();
                            if (lzyResponse != null) {
                                BannerBean bannerBean = (BannerBean) lzyResponse.data;
                                if (bannerBean != null) {
                                    mView.resultBanner(bannerBean);
                                }
                            }
                        }
                    }

                    @Override
                    public void onError(Response<LzyResponse<BannerBean>> response) {
                        super.onError(response);
                    }
                });
    }

    public void getPoolConfig() {
        OkGo.<JSONObject>post(UrlConfig.getPoolConfigUrl())
                .tag(this)
                .headers("Authorization", AccountManager.getInstance().getAccountToken())
                .execute(new JsonCallback<JSONObject>() {
                    @Override
                    public void onSuccess(Response<JSONObject> response) {
                        JSONObject object = response.body();
                        if (object != null) {
                            object = object.optJSONObject("data");
                            if (object != null) {
                                String yunAmount = object.optString("yunAmount");
                                String yuntHashrate = object.optString("yuntHashrate");
                                mView.resultPool(yunAmount, yuntHashrate);
                            }
                        }
                    }

                    @Override
                    public void onError(Response<JSONObject> response) {
                        super.onError(response);
                    }
                });
    }

    /**
     * 1001新闻公告，1002滚动信息，1003常见问题
     */
    public void getNews() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("page", "1");
            jsonObject.put("limit", "10");
            jsonObject.put("groupId", "1001");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkGo.<LzyResponse<HomeNews>>post(UrlConfig.getHomeNews())
                .tag(this)
                .upJson(jsonObject)
                .headers("Authorization", AccountManager.getInstance().getAccountToken())
                .execute(new JsonCallback<LzyResponse<HomeNews>>() {
                    @Override
                    public void onSuccess(Response<LzyResponse<HomeNews>> response) {
                        if (response != null) {
                            LzyResponse lzyResponse = response.body();
                            if (lzyResponse != null) {
                                HomeNews homeNews = (HomeNews) lzyResponse.data;
                                if (homeNews != null) {
                                    mView.resultNews(homeNews);
                                }
                            }
                        }
                    }

                    @Override
                    public void onError(Response<LzyResponse<HomeNews>> response) {
                        super.onError(response);
                    }
                });
    }

    /**
     * 1001新闻公告，1002滚动信息，1003常见问题
     */
    public void getRollNews() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("page", "1");
            jsonObject.put("limit", "10");
            jsonObject.put("groupId", "1002");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkGo.<LzyResponse<HomeNews>>post(UrlConfig.getHomeNews())
                .tag(this)
                .upJson(jsonObject)
                .headers("Authorization", AccountManager.getInstance().getAccountToken())
                .execute(new JsonCallback<LzyResponse<HomeNews>>() {
                    @Override
                    public void onSuccess(Response<LzyResponse<HomeNews>> response) {
                        if (response != null) {
                            LzyResponse lzyResponse = response.body();
                            if (lzyResponse != null) {
                                HomeNews homeNews = (HomeNews) lzyResponse.data;
                                if (homeNews != null) {
                                    mView.resultRollNews(homeNews);
                                }
                            }
                        }
                    }

                    @Override
                    public void onError(Response<LzyResponse<HomeNews>> response) {
                        super.onError(response);
                    }
                });
    }

    /**
     * 1001新闻公告，1002滚动信息，1003常见问题
     */
    public void getIssues() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("page", "1");
            jsonObject.put("limit", "10");
            jsonObject.put("groupId", "1003");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkGo.<LzyResponse<HomeNews>>post(UrlConfig.getHomeNews())
                .tag(this)
                .upJson(jsonObject)
                .headers("Authorization", AccountManager.getInstance().getAccountToken())
                .execute(new JsonCallback<LzyResponse<HomeNews>>() {
                    @Override
                    public void onSuccess(Response<LzyResponse<HomeNews>> response) {
                        if (response != null) {
                            LzyResponse lzyResponse = response.body();
                            if (lzyResponse != null) {
                                HomeNews homeNews = (HomeNews) lzyResponse.data;
                                if (homeNews != null) {
                                    mView.resultNews(homeNews);
                                }
                            }
                        }
                    }

                    @Override
                    public void onError(Response<LzyResponse<HomeNews>> response) {
                        super.onError(response);
                    }
                });
    }

    public void getYunExchangerate() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("page", "1");
            jsonObject.put("limit", "7");
            jsonObject.put("coin", "yun");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkGo.<JSONObject>post(UrlConfig.getHomeYunExchangerateUrl())
                .tag(this)
                .upJson(jsonObject)
                .headers("Authorization", AccountManager.getInstance().getAccountToken())
                .execute(new JsonCallback<JSONObject>() {
                    @Override
                    public void onSuccess(Response<JSONObject> response) {
                        JSONObject object = response.body();
                        if (object != null) {
                            object = object.optJSONObject("data");
                            if (object != null) {
                                JSONArray jsonArray = object.optJSONArray("list");
                                if(jsonArray != null && jsonArray.length() > 0) {
                                    int len = jsonArray.length();
                                    List<Unit> lines = new ArrayList<>();
                                    for (int i = 0; i < len; i++) {
                                        JSONObject lineObject = jsonArray.optJSONObject(i);
                                        String usdt = lineObject.optString("usdt");
                                        String addTime = lineObject.optString("addTime");

                                        Unit unit = new Unit(Float.parseFloat(usdt), addTime);
                                        lines.add(unit);
                                    }

                                    if (lines.size() > 0) {
                                        // 反序
                                        Collections.reverse(lines);
                                        mView.resultYunExchangeRate(lines);
                                    }
                                }
                            }
                        }
                    }

                    @Override
                    public void onError(Response<JSONObject> response) {
                        super.onError(response);
                    }
                });
    }

}
