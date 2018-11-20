package com.qkl.online.mining.app.data.entity;

import java.util.List;

/**
 * author：oyb on 2018/9/16 22:54
 */
public class Earnings extends BaseBean {


    /**
     * totalCount : 13
     * pageSize : 10
     * totalPage : 2
     * currPage : 1
     * list : [{"billId":219980082633117696,"memberId":218667740888698880,"email":"fan1@qq.com","contextId":219980082545037312,"contextName":"邀请星球奖励","operatorId":0,"operatorName":"系统","openingBalance":0,"billAmount":50,"closingBalance":0,"operateCode":1005,"operateName":"邀請星球獎勵","txid":null,"description":"邀请星球奖励-流水单ID","addTime":1541558948,"updateTime":1541558960,"payStatus":2001,"unit":"YUNT","state":"完成"},{"billId":219852609002016768,"memberId":218667740888698880,"email":null,"contextId":219852603889160200,"contextName":"斗地主游戏","operatorId":0,"operatorName":"ldz","openingBalance":0,"billAmount":9,"closingBalance":0,"operateCode":1007,"operateName":"游戲勝利","txid":null,"description":"游戏胜利","addTime":1541528556,"updateTime":1541528556,"payStatus":1001,"unit":"YUNT","state":"進行中"},{"billId":219816744406093824,"memberId":218667740888698880,"email":"fan1@qq.com","contextId":219816744313819136,"contextName":null,"operatorId":0,"operatorName":null,"openingBalance":0,"billAmount":5,"closingBalance":0,"operateCode":1003,"operateName":"星球獎勵","txid":"","description":"星球奖励","addTime":1541520006,"updateTime":1541520928,"payStatus":2001,"unit":"YUNT","state":"完成"},{"billId":219816725544308736,"memberId":218667740888698880,"email":"fan1@qq.com","contextId":219816725510754304,"contextName":null,"operatorId":0,"operatorName":null,"openingBalance":0,"billAmount":50,"closingBalance":0,"operateCode":1002,"operateName":"星球收益","txid":"","description":"星球收益","addTime":1541520001,"updateTime":1541521205,"payStatus":2001,"unit":"YUNT","state":"完成"},{"billId":219669445071736832,"memberId":218667740888698880,"email":"fan1@qq.com","contextId":219669444585197568,"contextName":"邀请星球奖励","operatorId":0,"operatorName":"系统","openingBalance":0,"billAmount":25,"closingBalance":0,"operateCode":1005,"operateName":"邀請星球獎勵","txid":null,"description":"邀请星球奖励-流水单ID","addTime":1541484887,"updateTime":1541484900,"payStatus":2001,"unit":"YUNT","state":"完成"},{"billId":219454353923248128,"memberId":218667740888698880,"email":"fan1@qq.com","contextId":219454353881305088,"contextName":null,"operatorId":0,"operatorName":null,"openingBalance":0,"billAmount":50,"closingBalance":0,"operateCode":1002,"operateName":"星球收益","txid":"","description":"星球收益","addTime":1541433605,"updateTime":1541433670,"payStatus":2001,"unit":"YUNT","state":"完成"},{"billId":219410343682772992,"memberId":218667740888698880,"email":"fan1@qq.com","contextId":219410343640829952,"contextName":null,"operatorId":0,"operatorName":null,"openingBalance":0,"billAmount":50,"closingBalance":0,"operateCode":1002,"operateName":"星球收益","txid":"","description":"星球收益","addTime":1541423112,"updateTime":1541423525,"payStatus":2001,"unit":"YUNT","state":"完成"},{"billId":218974426652348416,"memberId":218667740888698880,"email":null,"contextId":218974425482137600,"contextName":"游戏胜利","operatorId":0,"operatorName":"ldz","openingBalance":0,"billAmount":72,"closingBalance":0,"operateCode":1007,"operateName":"游戲勝利","txid":null,"description":"游戏胜利","addTime":1541319181,"updateTime":1541319181,"payStatus":1001,"unit":"YUNT","state":"進行中"},{"billId":218730191839694848,"memberId":218667740888698880,"email":"fan1@qq.com","contextId":218730191592230912,"contextName":"星球收益","operatorId":0,"operatorName":"系统","openingBalance":0,"billAmount":55,"closingBalance":0,"operateCode":1002,"operateName":"星球收益","txid":null,"description":"星球收益","addTime":1541260951,"updateTime":1541260960,"payStatus":2001,"unit":"YUNT","state":"完成"},{"billId":218680297913454592,"memberId":218667740888698880,"email":null,"contextId":218680297045233660,"contextName":"游戏胜利","operatorId":0,"operatorName":"ldz","openingBalance":0,"billAmount":36,"closingBalance":0,"operateCode":1007,"operateName":"游戲勝利","txid":null,"description":"游戏胜利","addTime":1541249056,"updateTime":1541249056,"payStatus":1001,"unit":"YUNT","state":"進行中"}]
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
         * billId : 219980082633117696
         * memberId : 218667740888698880
         * email : fan1@qq.com
         * contextId : 219980082545037312
         * contextName : 邀请星球奖励
         * operatorId : 0
         * operatorName : 系统
         * openingBalance : 0.0
         * billAmount : 50.0
         * closingBalance : 0.0
         * operateCode : 1005
         * operateName : 邀請星球獎勵
         * txid : null
         * description : 邀请星球奖励-流水单ID
         * addTime : 1541558948
         * updateTime : 1541558960
         * payStatus : 2001
         * unit : YUNT
         * state : 完成
         */

        private long billId;
        private long memberId;
        private String email;
        private long contextId;
        private String contextName;
        private int operatorId;
        private String operatorName;
        private double openingBalance;
        private double billAmount;
        private double closingBalance;
        private int operateCode;
        private String operateName;
        private Object txid;
        private String description;
        private int addTime;
        private int updateTime;
        private int payStatus;
        private String unit;
        private String state;

        public long getBillId() {
            return billId;
        }

        public void setBillId(long billId) {
            this.billId = billId;
        }

        public long getMemberId() {
            return memberId;
        }

        public void setMemberId(long memberId) {
            this.memberId = memberId;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public long getContextId() {
            return contextId;
        }

        public void setContextId(long contextId) {
            this.contextId = contextId;
        }

        public String getContextName() {
            return contextName;
        }

        public void setContextName(String contextName) {
            this.contextName = contextName;
        }

        public int getOperatorId() {
            return operatorId;
        }

        public void setOperatorId(int operatorId) {
            this.operatorId = operatorId;
        }

        public String getOperatorName() {
            return operatorName;
        }

        public void setOperatorName(String operatorName) {
            this.operatorName = operatorName;
        }

        public double getOpeningBalance() {
            return openingBalance;
        }

        public void setOpeningBalance(double openingBalance) {
            this.openingBalance = openingBalance;
        }

        public double getBillAmount() {
            return billAmount;
        }

        public void setBillAmount(double billAmount) {
            this.billAmount = billAmount;
        }

        public double getClosingBalance() {
            return closingBalance;
        }

        public void setClosingBalance(double closingBalance) {
            this.closingBalance = closingBalance;
        }

        public int getOperateCode() {
            return operateCode;
        }

        public void setOperateCode(int operateCode) {
            this.operateCode = operateCode;
        }

        public String getOperateName() {
            return operateName;
        }

        public void setOperateName(String operateName) {
            this.operateName = operateName;
        }

        public Object getTxid() {
            return txid;
        }

        public void setTxid(Object txid) {
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

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }
    }
}
