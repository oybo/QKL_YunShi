package com.qkl.online.mining.app.data.entity;

import java.io.Serializable;
import java.util.List;

/**
 * author：oyb on 2018/8/27 18:37
 * 星球底部的星球列表
 */
public class StarProduct extends BaseBean {

    /**
     * totalCount : 0
     * pageSize : 10
     * totalPage : 0
     * currPage : 1
     * list : [{"minerId":1,"minerName":"流星","sellPrice":100,"hashrate":10,"outputAmount":10,"lifecycle":7,"minerImg":"http://pdwdjovty.bkt.clouddn.com/upload/20180905/5b4ef07f3f364cc49893a355e5314ff2.png","levelCode":1,"levelName":"初级","description":"流星","sellFlag":1,"buyLimit":1,"addTime":1536142214},{"minerId":2,"minerName":"卫星","sellPrice":500,"hashrate":50,"outputAmount":50,"lifecycle":7,"minerImg":"http://pdwdjovty.bkt.clouddn.com/upload/20180905/63852e2a0535432dbca7517b52d6e9fd.png","levelCode":2,"levelName":"中级","description":"卫星","sellFlag":1,"buyLimit":1,"addTime":1536142214},{"minerId":3,"minerName":"行星","sellPrice":1000,"hashrate":1000,"outputAmount":1000,"lifecycle":7,"minerImg":"http://pdwdjovty.bkt.clouddn.com/upload/20180905/ba03aafef02c4d9a950a7a43d5b03999.png","levelCode":3,"levelName":"高级","description":"行星","sellFlag":1,"buyLimit":1,"addTime":1536142214},{"minerId":4,"minerName":"恒星","sellPrice":5000,"hashrate":5000,"outputAmount":5000,"lifecycle":7,"minerImg":"http://pdwdjovty.bkt.clouddn.com/upload/20180905/5919aae051704d0b9fb500788302daa6.png","levelCode":4,"levelName":"特级","description":"恒星","sellFlag":1,"buyLimit":1,"addTime":1536142214}]
     */

    private int totalCount;
    private int pageSize;
    private int totalPage;
    private int currPage;
    private List<ListEntity> list;

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }

    public void setList(List<ListEntity> list) {
        this.list = list;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public int getCurrPage() {
        return currPage;
    }

    public List<ListEntity> getList() {
        return list;
    }

    public static class ListEntity implements Serializable {
        /**
         * minerId : 1
         * minerName : 流星
         * sellPrice : 100
         * hashrate : 10
         * outputAmount : 10
         * lifecycle : 7
         * minerImg : http://pdwdjovty.bkt.clouddn.com/upload/20180905/5b4ef07f3f364cc49893a355e5314ff2.png
         * levelCode : 1
         * levelName : 初级
         * description : 流星
         * sellFlag : 1
         * buyLimit : 1
         * addTime : 1536142214
         */

        private int minerId;
        private String minerName;
        private int sellPrice;
        private int hashrate;
        private int outputAmount;
        private int lifecycle;
        private String minerImg;
        private int levelCode;
        private String levelName;
        private String description;
        private int sellFlag;
        private int buyLimit;
        private int addTime;

        public void setMinerId(int minerId) {
            this.minerId = minerId;
        }

        public void setMinerName(String minerName) {
            this.minerName = minerName;
        }

        public void setSellPrice(int sellPrice) {
            this.sellPrice = sellPrice;
        }

        public void setHashrate(int hashrate) {
            this.hashrate = hashrate;
        }

        public void setOutputAmount(int outputAmount) {
            this.outputAmount = outputAmount;
        }

        public void setLifecycle(int lifecycle) {
            this.lifecycle = lifecycle;
        }

        public void setMinerImg(String minerImg) {
            this.minerImg = minerImg;
        }

        public void setLevelCode(int levelCode) {
            this.levelCode = levelCode;
        }

        public void setLevelName(String levelName) {
            this.levelName = levelName;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public void setSellFlag(int sellFlag) {
            this.sellFlag = sellFlag;
        }

        public void setBuyLimit(int buyLimit) {
            this.buyLimit = buyLimit;
        }

        public void setAddTime(int addTime) {
            this.addTime = addTime;
        }

        public int getMinerId() {
            return minerId;
        }

        public String getMinerName() {
            return minerName;
        }

        public int getSellPrice() {
            return sellPrice;
        }

        public int getHashrate() {
            return hashrate;
        }

        public int getOutputAmount() {
            return outputAmount;
        }

        public int getLifecycle() {
            return lifecycle;
        }

        public String getMinerImg() {
            return minerImg;
        }

        public int getLevelCode() {
            return levelCode;
        }

        public String getLevelName() {
            return levelName;
        }

        public String getDescription() {
            return description;
        }

        public int getSellFlag() {
            return sellFlag;
        }

        public int getBuyLimit() {
            return buyLimit;
        }

        public int getAddTime() {
            return addTime;
        }
    }
}
