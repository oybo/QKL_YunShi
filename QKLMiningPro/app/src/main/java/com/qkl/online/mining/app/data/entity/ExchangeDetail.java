package com.qkl.online.mining.app.data.entity;

/**
 * author：oyb on 2018/9/18 13:38
 * 兑换记录
 */
public class ExchangeDetail extends BaseBean {


    /**
     * id :
     * txtId :
     * blockId :
     * blockNumber :
     * fromAddress :
     * toAddress :
     * value :
     * status :
     * date :
     * blockHigh :
     */

    private String id;
    private String txtId;
    private String blockId;
    private String blockNumber;
    private String fromAddress;
    private String toAddress;
    private String value;
    private String status;
    private String date;
    private String blockHigh;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTxtId() {
        return txtId;
    }

    public void setTxtId(String txtId) {
        this.txtId = txtId;
    }

    public String getBlockId() {
        return blockId;
    }

    public void setBlockId(String blockId) {
        this.blockId = blockId;
    }

    public String getBlockNumber() {
        return blockNumber;
    }

    public void setBlockNumber(String blockNumber) {
        this.blockNumber = blockNumber;
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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBlockHigh() {
        return blockHigh;
    }

    public void setBlockHigh(String blockHigh) {
        this.blockHigh = blockHigh;
    }
}
