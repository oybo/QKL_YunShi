package com.qkl.online.mining.app.mvp.presenter;

import android.app.Activity;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.qkl.online.mining.app.application.AccountManager;
import com.qkl.online.mining.app.data.commons.UrlConfig;
import com.qkl.online.mining.app.data.entity.TeamBean;
import com.qkl.online.mining.app.data.entity.User;
import com.qkl.online.mining.app.data.json.JsonCallback;
import com.qkl.online.mining.app.data.json.LzyResponse;
import com.qkl.online.mining.app.mvp.view.IMyteamView;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 我的团队
 */

public class MyTeamPresenter extends BasePresenter<IMyteamView> {

    public MyTeamPresenter(Activity context, IMyteamView view) {
        super(context, view);
    }

    public void getMyTeam(int currentPage) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("page", String.valueOf(currentPage));
            jsonObject.put("limit", "10");
            User user = AccountManager.getInstance().getUser();
            if (user != null) {
                jsonObject.put("referralCode", user.getReferralCode());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkGo.<LzyResponse<TeamBean>>post(UrlConfig.getTeamUrl())
                .tag(this)
                .headers("Authorization", AccountManager.getInstance().getAccountToken())
                .upJson(jsonObject)
                .execute(new JsonCallback<LzyResponse<TeamBean>>() {
                    @Override
                    public void onSuccess(Response<LzyResponse<TeamBean>> response) {
                        if (response != null) {
                            LzyResponse lzyResponse = response.body();
                            if (lzyResponse != null) {
                                TeamBean teamBean = (TeamBean) lzyResponse.data;
                                if (teamBean != null) {
                                    mView.result(teamBean);
                                    return;
                                }
                            }
                        }
                        mView.result(null);
                    }

                    @Override
                    public void onError(Response<LzyResponse<TeamBean>> response) {
                        super.onError(response);
                        mView.result(null);
                    }
                });
    }

}
