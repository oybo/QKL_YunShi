package com.qkl.online.mining.app.data.entity;

/**
 * author：oyb on 2018/9/8 18:41
 * 获取用户YUNT账户信息
 */
public class MyYuntEntity {


    /**
     * income : 7299
     * balance : 6198
     * miningBonus : 0
     * miningIncome : 0
     * expense : -1101
     * minerBonus : 0
     * maxFee : 1859.4
     * minFee : 9.6883
     */

    private String income;
    private String balance;
    private String miningBonus;
    private String miningIncome;
    private String expense;
    private String minerBonus;
    private double maxFee;
    private double minFee;

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getMiningBonus() {
        return miningBonus;
    }

    public void setMiningBonus(String miningBonus) {
        this.miningBonus = miningBonus;
    }

    public String getMiningIncome() {
        return miningIncome;
    }

    public void setMiningIncome(String miningIncome) {
        this.miningIncome = miningIncome;
    }

    public String getExpense() {
        return expense;
    }

    public void setExpense(String expense) {
        this.expense = expense;
    }

    public String getMinerBonus() {
        return minerBonus;
    }

    public void setMinerBonus(String minerBonus) {
        this.minerBonus = minerBonus;
    }

    public double getMaxFee() {
        return maxFee;
    }

    public void setMaxFee(double maxFee) {
        this.maxFee = maxFee;
    }

    public double getMinFee() {
        return minFee;
    }

    public void setMinFee(double minFee) {
        this.minFee = minFee;
    }
}
