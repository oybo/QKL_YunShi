package com.qkl.online.mining.app.data.entity;

import java.util.List;

/**
 * author：oyb on 2018/8/28 17:18
 */
public class GameBean extends BaseBean {


    /**
     * totalCount : 364
     * pageSize : 10
     * totalPage : 37
     * currPage : 1
     * list : [{"taskId":25833010376999187,"gameId":1002,"clientId":"ldz","memberId":207729075362598912,"beginTime":1539792000,"endTime":1539878399,"betCount":0,"betAmount":0,"taskStatus":0,"clickFee":1,"bitFee":0.1,"betMin":5,"ruleId":2,"ruleStatus":1,"mustFlag":1,"gameName":"鬥地主","gameImg":"http://yun-planet.oss-cn-shenzhen.aliyuncs.com/upload/20180919/49ddc1a68e494ff788b95105fe59d557.jpg","gameDesc":"鬥地主","language":"zh-TW","ruleName":"斗地主繁体游戏","ruleDesc":"玩一局","webServerRedirectUrl":"http://119.23.221.89/index.html"},{"taskId":0,"gameId":1003,"clientId":null,"memberId":0,"beginTime":0,"endTime":0,"betCount":0,"betAmount":0,"taskStatus":1,"clickFee":0,"bitFee":0,"betMin":0,"ruleId":0,"ruleStatus":0,"mustFlag":0,"gameName":"五子棋","gameImg":"http://yun-planet.oss-cn-shenzhen.aliyuncs.com/upload/20181011/f964bb2de679479b9aaf1c7e1bb2fc55.png","gameDesc":"","language":null,"ruleName":"","ruleDesc":"","webServerRedirectUrl":"http://www.zzfriend.com/demo/youxi/m/wzq/"},{"taskId":0,"gameId":1004,"clientId":null,"memberId":0,"beginTime":0,"endTime":0,"betCount":0,"betAmount":0,"taskStatus":1,"clickFee":0,"bitFee":0,"betMin":0,"ruleId":0,"ruleStatus":0,"mustFlag":0,"gameName":"切水果","gameImg":"http://yun-planet.oss-cn-shenzhen.aliyuncs.com/upload/20181011/0fe682d750194529a11e92e16f088a72.png","gameDesc":"","language":null,"ruleName":"","ruleDesc":"","webServerRedirectUrl":"http://www.zzfriend.com/demo/youxi/m/qsg/"},{"taskId":0,"gameId":1005,"clientId":null,"memberId":0,"beginTime":0,"endTime":0,"betCount":0,"betAmount":0,"taskStatus":1,"clickFee":0,"bitFee":0,"betMin":0,"ruleId":0,"ruleStatus":0,"mustFlag":0,"gameName":"消滅星星","gameImg":"http://yun-planet.oss-cn-shenzhen.aliyuncs.com/upload/20181011/3a0c9ac835f6406b838e04a5c50105d8.jpg","gameDesc":"","language":null,"ruleName":"","ruleDesc":"","webServerRedirectUrl":"http://www.zzfriend.com/demo/youxi/m/xmxx/"},{"taskId":0,"gameId":1006,"clientId":null,"memberId":0,"beginTime":0,"endTime":0,"betCount":0,"betAmount":0,"taskStatus":1,"clickFee":0,"bitFee":0,"betMin":0,"ruleId":0,"ruleStatus":0,"mustFlag":0,"gameName":"蓋樓","gameImg":"http://yun-planet.oss-cn-shenzhen.aliyuncs.com/upload/20181011/4a051d783f1647ea84fee6d0aa6c5505.jpg","gameDesc":"","language":null,"ruleName":"","ruleDesc":"","webServerRedirectUrl":"http://www.zzfriend.com/demo/youxi/m/tower/one.html"}]
     */

    private int totalCount;
    private int pageSize;
    private int totalPage;
    private int currPage;
    private List<ListBean> list;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurrPage() {
        return currPage;
    }

    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * taskId : 25833010376999187
         * gameId : 1002
         * clientId : ldz
         * memberId : 207729075362598912
         * beginTime : 1539792000
         * endTime : 1539878399
         * betCount : 0
         * betAmount : 0
         * taskStatus : 0
         * clickFee : 1
         * bitFee : 0.1
         * betMin : 5
         * ruleId : 2
         * ruleStatus : 1
         * mustFlag : 1
         * gameName : 鬥地主
         * gameImg : http://yun-planet.oss-cn-shenzhen.aliyuncs.com/upload/20180919/49ddc1a68e494ff788b95105fe59d557.jpg
         * gameDesc : 鬥地主
         * language : zh-TW
         * ruleName : 斗地主繁体游戏
         * ruleDesc : 玩一局
         * webServerRedirectUrl : http://119.23.221.89/index.html
         */

        private long taskId;
        private int gameId;
        private String clientId;
        private long memberId;
        private int beginTime;
        private int endTime;
        private int betCount;
        private int betAmount;
        private int taskStatus;
        private int clickFee;
        private double bitFee;
        private int betMin;
        private int ruleId;
        private int ruleStatus;
        private int mustFlag;
        private String gameName;
        private String gameImg;
        private String gameDesc;
        private String language;
        private String ruleName;
        private String ruleDesc;
        private String webServerRedirectUrl;

        public long getTaskId() {
            return taskId;
        }

        public void setTaskId(long taskId) {
            this.taskId = taskId;
        }

        public int getGameId() {
            return gameId;
        }

        public void setGameId(int gameId) {
            this.gameId = gameId;
        }

        public String getClientId() {
            return clientId;
        }

        public void setClientId(String clientId) {
            this.clientId = clientId;
        }

        public long getMemberId() {
            return memberId;
        }

        public void setMemberId(long memberId) {
            this.memberId = memberId;
        }

        public int getBeginTime() {
            return beginTime;
        }

        public void setBeginTime(int beginTime) {
            this.beginTime = beginTime;
        }

        public int getEndTime() {
            return endTime;
        }

        public void setEndTime(int endTime) {
            this.endTime = endTime;
        }

        public int getBetCount() {
            return betCount;
        }

        public void setBetCount(int betCount) {
            this.betCount = betCount;
        }

        public int getBetAmount() {
            return betAmount;
        }

        public void setBetAmount(int betAmount) {
            this.betAmount = betAmount;
        }

        public int getTaskStatus() {
            return taskStatus;
        }

        public void setTaskStatus(int taskStatus) {
            this.taskStatus = taskStatus;
        }

        public int getClickFee() {
            return clickFee;
        }

        public void setClickFee(int clickFee) {
            this.clickFee = clickFee;
        }

        public double getBitFee() {
            return bitFee;
        }

        public void setBitFee(double bitFee) {
            this.bitFee = bitFee;
        }

        public int getBetMin() {
            return betMin;
        }

        public void setBetMin(int betMin) {
            this.betMin = betMin;
        }

        public int getRuleId() {
            return ruleId;
        }

        public void setRuleId(int ruleId) {
            this.ruleId = ruleId;
        }

        public int getRuleStatus() {
            return ruleStatus;
        }

        public void setRuleStatus(int ruleStatus) {
            this.ruleStatus = ruleStatus;
        }

        public int getMustFlag() {
            return mustFlag;
        }

        public void setMustFlag(int mustFlag) {
            this.mustFlag = mustFlag;
        }

        public String getGameName() {
            return gameName;
        }

        public void setGameName(String gameName) {
            this.gameName = gameName;
        }

        public String getGameImg() {
            return gameImg;
        }

        public void setGameImg(String gameImg) {
            this.gameImg = gameImg;
        }

        public String getGameDesc() {
            return gameDesc;
        }

        public void setGameDesc(String gameDesc) {
            this.gameDesc = gameDesc;
        }

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }

        public String getRuleName() {
            return ruleName;
        }

        public void setRuleName(String ruleName) {
            this.ruleName = ruleName;
        }

        public String getRuleDesc() {
            return ruleDesc;
        }

        public void setRuleDesc(String ruleDesc) {
            this.ruleDesc = ruleDesc;
        }

        public String getWebServerRedirectUrl() {
            return webServerRedirectUrl;
        }

        public void setWebServerRedirectUrl(String webServerRedirectUrl) {
            this.webServerRedirectUrl = webServerRedirectUrl;
        }
    }

}
