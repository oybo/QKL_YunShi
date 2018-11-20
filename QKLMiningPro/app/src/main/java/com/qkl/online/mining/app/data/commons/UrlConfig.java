package com.qkl.online.mining.app.data.commons;

import android.text.TextUtils;

import com.qkl.online.mining.app.application.AppContext;
import com.qkl.online.mining.app.data.entity.GameBean;
import com.qkl.online.mining.app.utils.CommonsUtils;
import com.qkl.online.mining.app.utils.languagelib.MultiLanguageUtil;

import java.util.Random;

/**
 * author：oyb on 2018/7/5 22:54
 * 接口地址
 */
public class UrlConfig {

    private static final String BASE_URL = "https://api2.yunplanet.net";

    /**
     * 发送验证码    -   注册
     *
     * @return
     */
    public static final String getSendCodeUrl() {
        return BASE_URL + "/api/open/sys/user/regcode";
    }

    /**
     * 发送验证码    -   找回密码
     *
     * @return
     */
    public static final String getSendCodeByForgetUrl() {
        return BASE_URL + "/api/open/sys/user/forgetpasscode";
    }

    /**
     * 提交注册
     *
     * @return
     */
    public static final String getRegisterUserUrl() {
        return BASE_URL + "/api/open/sys/user/reg";
    }

    /**
     * 忘记密码
     *
     * @return
     */
    public static final String getForgetPasswordUrl() {
        return BASE_URL + "/api/open/sys/user/resetpassword";
    }

    /**
     * 修改密码
     *
     * @return
     */
    public static final String getChangePasswordUrl() {
        return BASE_URL + "/api/user/sys/user/changepassword";
    }

    /**
     * 忘记支付密码
     *
     * @return
     */
    public static final String getForgetPayPasswordUrl() {
        return BASE_URL + "/api/user/sys/user/resetpaypass";
    }

    /**
     * 修改支付密码
     *
     * @return
     */
    public static final String getChangePayPasswordUrl() {
        return BASE_URL + "/api/user/sys/user/changepaypass";
    }

    /**
     * 登录
     *
     * @return
     */
    public static final String getLoginUrl() {
        return BASE_URL + "/api/open/sys/user/token";
    }

    /**
     * 注销
     *
     * @return
     */
    public static final String getLogOutUrl() {
        return BASE_URL + "/api/auth/logout";
    }

    /**
     * 首页banner
     *
     * @return
     */
    public static final String getHomeBanner() {
        return BASE_URL + "/api/open/sys/banner/list";
    }

    /**
     * 首页新闻公告/滚动信息/常见问题
     *
     * @return
     */
    public static final String getHomeNews() {
        return BASE_URL + "/api/open/sys/article/list";
    }

    /**
     * 首页获取YUN汇率列表-价格曲线图
     *
     * @return
     */
    public static final String getHomeYunExchangerateUrl() {
        return BASE_URL + "/api/finance/sys/exchangecoinrate/pricetrends";
    }

    /**
     * 获取实时汇率接口
     *
     * @return
     */
    public static final String getYunExchangerateUrl() {
        return BASE_URL + "/api/finance/sys/exchangecoinrate/yunrate";
    }

    /**
     * 个人信息
     *
     * @return
     */
    public static final String getUserInfoUrl(String memberId) {
        return BASE_URL + "/api/user/sys/user/" + memberId;
    }

    /**
     * YUNT提币接口
     *
     * @return
     */
    public static final String getYunt2YunUrl() {
        return BASE_URL + "/api/finance/sys/exchange/yunt2yun";
    }

    /**
     * YUNT--YUN兑换账单接口
     *
     * @return
     */
    public static final String getExchangeListUrl() {
        return BASE_URL + "/api/finance/sys/exchange/list";
    }

    /**
     * YUNT--YUN兑换账单接口-详情接口
     *
     * @return
     */
    public static final String getExchangeDetailUrl() {
        return BASE_URL + "/api/finance/sys/exchange/txthash";
    }

    /**
     * 兑换详情- 检查Txid
     *
     * @return
     */
    public static final String getCheckTxidUrl(String txid) {
        return "https://etherscan.io/tx/" + txid;
    }

    /**
     * 获取用户YUNT账户信息
     *
     * @return
     */
    public static final String getUserYuntDataUrl(String memberId) {
        return BASE_URL + "/api/finance/sys/yunt/account/" + memberId;
    }

    /**
     * YUNT账单流水接口   -   收益详情
     *
     * @return
     */
    public static final String getJieDianEarningsUrl() {
        return BASE_URL + "/api/finance/sys/yunt/list";
    }

    /**
     * 上传头像
     *
     * @return
     */
    public static final String getUploadProfileUrl(String memberId) {
        return BASE_URL + "/api/user/sys/user/profileup/" + memberId;
    }

    /**
     * 上传头像
     *
     * @return
     */
    public static final String getSetUserInfoUrl() {
        return BASE_URL + "/api/user/sys/user/resetuserinfo";
    }

    /**
     * 获取矿机列表接口
     *
     * @return
     */
    public static final String getMinerListUrl() {
        return BASE_URL + "/api/miner/sys/minermachine/list";
    }

    /**
     * 我的星球接口
     *
     * @return
     */
    public static final String getMyStarListUrl(String memberId) {
        return BASE_URL + "/api/miner/sys/minerorder/user/" + memberId;
    }

    /**
     * 今日剩余星球数
     *
     * @return
     */
    public static final String getTodayStarRestNumberUrl() {
        return BASE_URL + "/api/miner/sys/minermachine/totalbuylimit";
    }

    /**
     * 购买星球=矿机
     *
     * @return
     */
    public static final String getPurchaseStarUrl() {
        return BASE_URL + "/api/miner/sys/minerorder/submit";
    }

    /**
     * 获取游戏列表接口
     *
     * @return
     */
    public static final String getGameList() {
        return BASE_URL + "/api/client/sys/gametask/list";
    }

    /**
     * 设置YUN钱包地址接口
     *
     * @return
     */
    public static final String getYunAddressUrl() {
        return BASE_URL + "/api/user/sys/user/resetyunaddress";
    }

    /**
     * 获得用户列表/我的团队接口
     *
     * @return
     */
    public static final String getTeamUrl() {
        return BASE_URL + "/api/user/sys/user/list";
    }

    /**
     * 获取系统配置字典接口
     *
     * @return
     */
    public static final String getCommonsConfigUrl() {
        return BASE_URL + "/api/open/sys/config/dict";
    }

    /**
     * 获取系统配置字典接口
     *
     * @return
     */
    public static final String getYunPlanetConfigUrl() {
        String version = CommonsUtils.getSoftVersionName(AppContext.getInstance());
        return "http://config.yunplanet.net/config.aspx?v="
                + version + "&from=android&t="
                + System.currentTimeMillis() + "&mode=ali";
    }

    /**
     * 矿池实时数据接口
     *
     * @return
     */
    public static final String getPoolConfigUrl() {
        return BASE_URL + "/api/open/sys/config/pool";
    }

    public static final String getGameDefultUrl(GameBean.ListBean gameEntity) {
        String language = "zh-CN";
        int languageType = MultiLanguageUtil.getInstance().getLanguageType();
        switch (languageType) {
            case 1:
                language = "zh-CN";
                break;
            case 2:
                language = "zh-TW";
                break;
            case 3:
                language = "en";
                break;
        }

        String url = BASE_URL + "/api/auth/oauth/authorize?client_id={{client_id}}&response_type=code&state={{state}}&scope={{webclient}}&redirect_uri={{redirect_uri}}";
        return url.replace("{{client_id}}", !TextUtils.isEmpty(gameEntity.getClientId()) ? gameEntity.getClientId() : "")
                .replace("{{state}}", language)
                .replace("{{webclient}}", "webclient")
                .replace("{{redirect_uri}}", gameEntity.getWebServerRedirectUrl());
    }

    public static String getRegisterXieYi() {
        return "http://h5.yunplanet.net/protocol";
    }

}
