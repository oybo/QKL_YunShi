package com.qkl.online.mining.app.data.entity;

/**
 *  *  author : oyb
 *  *  date : 2018/9/27 19:02
 *  *  description : 
 *  
 */
public class DictConfig extends BaseBean {


    /**
     * appSummary : 快来云石星球玩游戏赚YUNT
     * servicePhone : 0755-12345312
     * drawMinAmount : 5000
     * yuntListUrl : http://47.75.190.211:8081/YuntList
     * appName : 云石星球
     * drawMaxFeeRate : 0.02
     * serviceMail : 12311@qq.com
     * startCover : http://yun-planet.oss-cn-shenzhen.aliyuncs.com/upload/20180916/8232c401292144b9a4978e8eeace9029.jpg
     * version : 1.1.1
     * tokenHolderUrl : http://47.75.190.211:8082/tokenHolder.html
     * drawMinFeeRate : 0.05
     */

    private String appSummary;
    private String servicePhone;
    private int drawMinAmount;
    private String yuntListUrl;
    private String appName;
    private double drawMaxFeeRate;
    private String serviceMail;
    private String startCover;
    private String version;
    private String updateUrl;
    private String tokenHolderUrl;
    private double drawMinFeeRate;
    private String reservationUrl;


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

    public int getDrawMinAmount() {
        return drawMinAmount;
    }

    public void setDrawMinAmount(int drawMinAmount) {
        this.drawMinAmount = drawMinAmount;
    }

    public String getYuntListUrl() {
        return yuntListUrl;
    }

    public void setYuntListUrl(String yuntListUrl) {
        this.yuntListUrl = yuntListUrl;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public double getDrawMaxFeeRate() {
        return drawMaxFeeRate;
    }

    public void setDrawMaxFeeRate(double drawMaxFeeRate) {
        this.drawMaxFeeRate = drawMaxFeeRate;
    }

    public String getServiceMail() {
        return serviceMail;
    }

    public void setServiceMail(String serviceMail) {
        this.serviceMail = serviceMail;
    }

    public String getStartCover() {
        return startCover;
    }

    public void setStartCover(String startCover) {
        this.startCover = startCover;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getTokenHolderUrl() {
        return tokenHolderUrl;
    }

    public void setTokenHolderUrl(String tokenHolderUrl) {
        this.tokenHolderUrl = tokenHolderUrl;
    }

    public double getDrawMinFeeRate() {
        return drawMinFeeRate;
    }

    public void setDrawMinFeeRate(double drawMinFeeRate) {
        this.drawMinFeeRate = drawMinFeeRate;
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

}
