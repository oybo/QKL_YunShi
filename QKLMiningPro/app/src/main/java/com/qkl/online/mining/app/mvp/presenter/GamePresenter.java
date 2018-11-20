package com.qkl.online.mining.app.mvp.presenter;

import android.app.Activity;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.qkl.online.mining.app.application.AccountManager;
import com.qkl.online.mining.app.data.commons.UrlConfig;
import com.qkl.online.mining.app.data.entity.BannerBean;
import com.qkl.online.mining.app.data.entity.GameBean;
import com.qkl.online.mining.app.data.json.Convert;
import com.qkl.online.mining.app.data.json.JsonCallback;
import com.qkl.online.mining.app.data.json.LzyResponse;
import com.qkl.online.mining.app.mvp.view.IGameView;
import com.qkl.online.mining.app.utils.CommonsUtils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 游戏
 */

public class GamePresenter extends BasePresenter<IGameView> {

    public GamePresenter(Activity context, IGameView view) {
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
            jsonObject.put("groupId", "1002");
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

    /**
     * 获取游戏列表
     */
    public void getGameList(int currentPage) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("page", String.valueOf(currentPage));
            jsonObject.put("limit", "10");
            jsonObject.put("memberId", AccountManager.getInstance().getMemberId());
        } catch (Exception e) {
            e.printStackTrace();
        }

        OkGo.<JSONObject>post(UrlConfig.getGameList())
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
                                GameBean gameBean = Convert.forGameBean(object);
                                if (gameBean != null) {
                                    mView.resultGame(gameBean);
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
