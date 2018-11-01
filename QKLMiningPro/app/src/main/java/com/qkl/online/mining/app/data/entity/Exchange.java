package com.qkl.online.mining.app.data.entity;

import java.io.Serializable;
import java.util.List;

/**
 * author：oyb on 2018/9/18 13:38
 * 兑换记录
 */
public class Exchange extends BaseBean {


    /**
     * totalCount : 2
     * pageSize : 10
     * totalPage : 1
     * currPage : 1
     * list : [{"billId":204851501456297984,"memberId":204550923337469952,"exchangeType":2001,"holdMoney":"yunt","exchangeMoney":"yun","exchangeRate":1.1,"holdAmount":100,"inputAmount":100,"exchangeAmount":110,"exchangeFee":0,"fromAddress":"0x200daB144A66aAB853760790E31dE5E9cD8bc13e","toAddress":"0xec8cd964eddc9ab15ffca16036e7c614d69f3251","txid":"0x5669642e9b5c530949a8d6f6d36d196c19ba5dfac6b33325abe062e3cd4ee1ee","exchangeStatus":3001,"addTime":1537952014},{"billId":205239488623022080,"memberId":204550923337469952,"exchangeType":2001,"holdMoney":"yunt","exchangeMoney":"yun","exchangeRate":1.1,"holdAmount":12,"inputAmount":12,"exchangeAmount":13.2,"exchangeFee":0,"fromAddress":"0x200daB144A66aAB853760790E31dE5E9cD8bc13e","toAddress":"0xec8cd964eddc9ab15ffca16036e7c614d69f3251","txid":"0xecab5491ee82279a3d6e15a2aee6821c97c433e6d607843731c4c46abb294fcc","exchangeStatus":3001,"addTime":1538044517}]
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

    public static class ListBean implements Serializable {
        /**
         * billId : 204851501456297984
         * memberId : 204550923337469952
         * exchangeType : 2001
         * holdMoney : yunt
         * exchangeMoney : yun
         * exchangeRate : 1.1
         * holdAmount : 100
         * inputAmount : 100
         * exchangeAmount : 110
         * exchangeFee : 0
         * fromAddress : 0x200daB144A66aAB853760790E31dE5E9cD8bc13e
         * toAddress : 0xec8cd964eddc9ab15ffca16036e7c614d69f3251
         * txid : 0x5669642e9b5c530949a8d6f6d36d196c19ba5dfac6b33325abe062e3cd4ee1ee
         * exchangeStatus : 3001
         * addTime : 1537952014
         */

        private String billId;
        private String memberId;
        private String exchangeType;
        private String holdMoney;
        private String exchangeMoney;
        private double exchangeRate;
        private String holdAmount;
        private String inputAmount;
        private String exchangeAmount;
        private String exchangeFee;
        private String fromAddress;
        private String toAddress;
        private String txid;
        private int exchangeStatus;
        private int addTime;

        public String getBillId() {
            return billId;
        }

        public void setBillId(String billId) {
            this.billId = billId;
        }

        public String getMemberId() {
            return memberId;
        }

        public void setMemberId(String memberId) {
            this.memberId = memberId;
        }

        public String getExchangeType() {
            return exchangeType;
        }

        public void setExchangeType(String exchangeType) {
            this.exchangeType = exchangeType;
        }

        public String getHoldMoney() {
            return holdMoney;
        }

        public void setHoldMoney(String holdMoney) {
            this.holdMoney = holdMoney;
        }

        public String getExchangeMoney() {
            return exchangeMoney;
        }

        public void setExchangeMoney(String exchangeMoney) {
            this.exchangeMoney = exchangeMoney;
        }

        public double getExchangeRate() {
            return exchangeRate;
        }

        public void setExchangeRate(double exchangeRate) {
            this.exchangeRate = exchangeRate;
        }

        public String getHoldAmount() {
            return holdAmount;
        }

        public void setHoldAmount(String holdAmount) {
            this.holdAmount = holdAmount;
        }

        public String getInputAmount() {
            return inputAmount;
        }

        public void setInputAmount(String inputAmount) {
            this.inputAmount = inputAmount;
        }

        public String getExchangeAmount() {
            return exchangeAmount;
        }

        public void setExchangeAmount(String exchangeAmount) {
            this.exchangeAmount = exchangeAmount;
        }

        public String getExchangeFee() {
            return exchangeFee;
        }

        public void setExchangeFee(String exchangeFee) {
            this.exchangeFee = exchangeFee;
        }

        public String getFromAddress() {
            return fromAddress;
        }

        public void setFromAddress(String fromAddress) {
            this.fromAddress = fromAddress;
        }

        public String getToAddress() {
            return toAddress;
        }

        public void setToAddress(String toAddress) {
            this.toAddress = toAddress;
        }

        public String getTxid() {
            return txid;
        }

        public void setTxid(String txid) {
            this.txid = txid;
        }

        public int getExchangeStatus() {
            return exchangeStatus;
        }

        public void setExchangeStatus(int exchangeStatus) {
            this.exchangeStatus = exchangeStatus;
        }

        public int getAddTime() {
            return addTime;
        }

        public void setAddTime(int addTime) {
            this.addTime = addTime;
        }
    }

}
