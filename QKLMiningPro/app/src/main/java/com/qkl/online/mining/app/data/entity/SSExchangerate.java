package com.qkl.online.mining.app.data.entity;

/**
 *  *  author : oyb
 *  *  date : 2018/10/16 18:10
 *  *  description : 
 * 实时汇率
 *  
 */
public class SSExchangerate extends BaseBean {

    /**
     * yunToUsdt : 0.1
     * yunToYunt : 10
     */

    private double yunToUsdt;
    private int yunToYunt;

    public double getYunToUsdt() {
        return yunToUsdt;
    }

    public void setYunToUsdt(double yunToUsdt) {
        this.yunToUsdt = yunToUsdt;
    }

    public int getYunToYunt() {
        return yunToYunt;
    }

    public void setYunToYunt(int yunToYunt) {
        this.yunToYunt = yunToYunt;
    }

}
