package com.qkl.online.mining.app.data.entity;

import java.util.List;

/**
 * author：oyb on 2018/9/16 22:54
 */
public class Earnings extends BaseBean {


    /**
     * totalCount : 6
     * pageSize : 10
     * totalPage : 1
     * currPage : 1
     * list : [{"billId":207061903770521600,"memberId":205648937061847040,"email":"309191428@qq.com","contextId":207061903212679168,"contextName":"收益分配账单ID","operatorId":0,"operatorName":"系统","openingBalance":0,"billAmount":100,"closingBalance":0,"operateCode":1003,"operateName":"收益分配账单ID","txid":null,"description":"收益分配账单ID","addTime":1538479015,"updateTime":1538479015,"payStatus":1001},{"billId":207062270092644352,"memberId":205648937061847040,"email":"309191428@qq.com","contextId":207062269723545600,"contextName":"收益分配账单ID","operatorId":0,"operatorName":"系统","openingBalance":0,"billAmount":100,"closingBalance":0,"operateCode":1003,"operateName":"收益分配账单ID","txid":null,"description":"收益分配账单ID","addTime":1538479102,"updateTime":1538479262,"payStatus":2001},{"billId":207064968355516416,"memberId":205648937061847040,"email":"309191428@qq.com","contextId":207064967835422720,"contextName":"收益分配账单ID","operatorId":0,"operatorName":"系统","openingBalance":0,"billAmount":100,"closingBalance":0,"operateCode":1003,"operateName":"收益分配账单ID","txid":null,"description":"收益分配账单ID","addTime":1538479745,"updateTime":1538479892,"payStatus":2001},{"billId":207075503386005504,"memberId":205648937061847040,"email":"309191428@qq.com","contextId":207075502920437760,"contextName":"收益分配账单ID","operatorId":0,"operatorName":"系统","openingBalance":0,"billAmount":100,"closingBalance":0,"operateCode":1003,"operateName":"收益分配账单ID","txid":null,"description":"收益分配账单ID","addTime":1538482257,"updateTime":1538482352,"payStatus":2001},{"billId":207133156581707776,"memberId":205648937061847040,"email":"309191428@qq.com","contextId":207133156082585600,"contextName":"收益分配账单ID","operatorId":0,"operatorName":"系统","openingBalance":0,"billAmount":100,"closingBalance":0,"operateCode":1003,"operateName":"收益分配账单ID","txid":null,"description":"收益分配账单ID","addTime":1538496003,"updateTime":1538496152,"payStatus":2001},{"billId":207495543155462144,"memberId":205648937061847040,"email":"309191428@qq.com","contextId":207495542656339968,"contextName":"收益分配账单ID","operatorId":0,"operatorName":"系统","openingBalance":0,"billAmount":100,"closingBalance":0,"operateCode":1003,"operateName":"收益分配账单ID","txid":null,"description":"收益分配账单ID","addTime":1538582402,"updateTime":1538582552,"payStatus":2001}]
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
         * billId : 207061903770521600
         * memberId : 205648937061847040
         * email : 309191428@qq.com
         * contextId : 207061903212679168
         * contextName : 收益分配账单ID
         * operatorId : 0
         * operatorName : 系统
         * openingBalance : 0
         * billAmount : 100
         * closingBalance : 0
         * operateCode : 1003
         * operateName : 收益分配账单ID
         * txid : null
         * description : 收益分配账单ID
         * addTime : 1538479015
         * updateTime : 1538479015
         * payStatus : 1001
         */

        private String billId;
        private String memberId;
        private String email;
        private String contextId;
        private String contextName;
        private String operatorId;
        private String operatorName;
        private String openingBalance;
        private String billAmount;
        private String closingBalance;
        private String operateCode;
        private String operateName;
        private String txid;
        private String description;
        private int addTime;
        private int updateTime;
        private int payStatus;

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

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getContextId() {
            return contextId;
        }

        public void setContextId(String contextId) {
            this.contextId = contextId;
        }

        public String getContextName() {
            return contextName;
        }

        public void setContextName(String contextName) {
            this.contextName = contextName;
        }

        public String getOperatorId() {
            return operatorId;
        }

        public void setOperatorId(String operatorId) {
            this.operatorId = operatorId;
        }

        public String getOperatorName() {
            return operatorName;
        }

        public void setOperatorName(String operatorName) {
            this.operatorName = operatorName;
        }

        public String getOpeningBalance() {
            return openingBalance;
        }

        public void setOpeningBalance(String openingBalance) {
            this.openingBalance = openingBalance;
        }

        public String getBillAmount() {
            return billAmount;
        }

        public void setBillAmount(String billAmount) {
            this.billAmount = billAmount;
        }

        public String getClosingBalance() {
            return closingBalance;
        }

        public void setClosingBalance(String closingBalance) {
            this.closingBalance = closingBalance;
        }

        public String getOperateCode() {
            return operateCode;
        }

        public void setOperateCode(String operateCode) {
            this.operateCode = operateCode;
        }

        public String getOperateName() {
            return operateName;
        }

        public void setOperateName(String operateName) {
            this.operateName = operateName;
        }

        public String getTxid() {
            return txid;
        }

        public void setTxid(String txid) {
            this.txid = txid;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getAddTime() {
            return addTime;
        }

        public void setAddTime(int addTime) {
            this.addTime = addTime;
        }

        public int getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(int updateTime) {
            this.updateTime = updateTime;
        }

        public int getPayStatus() {
            return payStatus;
        }

        public void setPayStatus(int payStatus) {
            this.payStatus = payStatus;
        }
    }

}
