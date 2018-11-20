package com.qkl.online.mining.app.data.entity;

/**
 *  *  author : oyb
 *  *  date : 2018/11/19 21:24
 *  *  description : 
 *  
 */
public class AppCommonsConfig {


    /**
     * inviteStartListUrl : http://h6.yunplanet.net/invitelist
     * appSummary : 快來雲石星球玩遊戲賺雲石，
     * servicePhone :
     * appName : 雲石星球
     * serviceMail : yunplanet9@gmail.com
     * tokenHolderUrl : http://h6.yunplanet.net/yunlist
     * yuntListUrl : http://h6.yunplanet.net/yuntlist
     * startCover : http://yun-planet.oss-cn-shenzhen.aliyuncs.com/upload/20181104/05e5b13856d44a6d95db67f90551d5af.jpg
     * drawMinAmount : 5000
     * drawMaxFeeRate : 0.05
     * drawMinFeeRate : 0.02
     * minerDefaultImg : http://yun-planet.oss-cn-shenzhen.aliyuncs.com/upload/20181014/d11469edfea54075990d8b3dca6295c4.png
     * updateUrl : http://down.yunplanet.net
     * reservationUrl : http://h6.yunplanet.net/reservation
     * version : 2.1.0
     * iosVersion : 2.1.3
     * regprotocol : http://h6.yunplanet.net/protocol
     */

    private String inviteStartListUrl;
    private String appSummary;
    private String servicePhone;
    private String appName;
    private String serviceMail;
    private String tokenHolderUrl;
    private String yuntListUrl;
    private String startCover;
    private int drawMinAmount;
    private double drawMaxFeeRate;
    private double drawMinFeeRate;
    private String minerDefaultImg;
    private String updateUrl;
    private String reservationUrl;
    private String version;
    private String iosVersion;
    private String regprotocol;

    public String getInviteStartListUrl() {
        return inviteStartListUrl;
    }

    public void setInviteStartListUrl(String inviteStartListUrl) {
        this.inviteStartListUrl = inviteStartListUrl;
    }

    public String getAppSummary() {
        return appSummary;
    }

    public void setAppSummary(String appSummary) {
        this.appSummary = appSummary;
    }

    public String getServicePhone() {
        return servicePhone;
    }

    public void setServicePhone(String servicePhone) {
        this.servicePhone = servicePhone;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getServiceMail() {
        return serviceMail;
    }

    public void setServiceMail(String serviceMail) {
        this.serviceMail = serviceMail;
    }

    public String getTokenHolderUrl() {
        return tokenHolderUrl;
    }

    public void setTokenHolderUrl(String tokenHolderUrl) {
        this.tokenHolderUrl = tokenHolderUrl;
    }

    public String getYuntListUrl() {
        return yuntListUrl;
    }

    public void setYuntListUrl(String yuntListUrl) {
        this.yuntListUrl = yuntListUrl;
    }

    public String getStartCover() {
        return startCover;
    }

    public void setStartCover(String startCover) {
        this.startCover = startCover;
    }

    public int getDrawMinAmount() {
        return drawMinAmount;
    }

    public void setDrawMinAmount(int drawMinAmount) {
        this.drawMinAmount = drawMinAmount;
    }

    public double getDrawMaxFeeRate() {
        return drawMaxFeeRate;
    }

    public void setDrawMaxFeeRate(double drawMaxFeeRate) {
        this.drawMaxFeeRate = drawMaxFeeRate;
    }

    public double getDrawMinFeeRate() {
        return drawMinFeeRate;
    }

    public void setDrawMinFeeRate(double drawMinFeeRate) {
        this.drawMinFeeRate = drawMinFeeRate;
    }

    public String getMinerDefaultImg() {
        return minerDefaultImg;
    }

    public void setMinerDefaultImg(String minerDefaultImg) {
        this.minerDefaultImg = minerDefaultImg;
    }

    public String getUpdateUrl() {
        return updateUrl;
    }

    public void setUpdateUrl(String updateUrl) {
        this.updateUrl = updateUrl;
    }

    public String getReservationUrl() {
        return reservationUrl;
    }

    public void setReservationUrl(String reservationUrl) {
        this.reservationUrl = reservationUrl;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getIosVersion() {
        return iosVersion;
    }

    public void setIosVersion(String iosVersion) {
        this.iosVersion = iosVersion;
    }

    public String getRegprotocol() {
        return regprotocol;
    }

    public void setRegprotocol(String regprotocol) {
        this.regprotocol = regprotocol;
    }
}
