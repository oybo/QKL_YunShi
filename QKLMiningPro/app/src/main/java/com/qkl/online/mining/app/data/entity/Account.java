package com.qkl.online.mining.app.data.entity;

import java.util.List;

/**
 * author：oyb on 2018/7/8 23:13
 */
public class Account {

    /**
     * username : 944533800@qq.com
     * email : 944533800@qq.com
     * memberId : 197241602622754816
     * nickName : null
     * profilePicture : null
     * yuntAddress : null
     * qrcodeImg : null
     * lockedFlag : 0
     * addTime : 1536137673
     * authorities : ["MINER","COMMON","GAME","USER","LOG"]
     */

    private String username;
    private String email;
    private String memberId;
    private String nickName;
    private String profilePicture;
    private String yuntAddress;
    private String qrcodeImg;
    private int lockedFlag;
    private int addTime;
    private List<String> authorities;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public void setYuntAddress(String yuntAddress) {
        this.yuntAddress = yuntAddress;
    }

    public void setQrcodeImg(String qrcodeImg) {
        this.qrcodeImg = qrcodeImg;
    }

    public void setLockedFlag(int lockedFlag) {
        this.lockedFlag = lockedFlag;
    }

    public void setAddTime(int addTime) {
        this.addTime = addTime;
    }

    public void setAuthorities(List<String> authorities) {
        this.authorities = authorities;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getMemberId() {
        return memberId;
    }

    public String getNickName() {
        return nickName;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public String getYuntAddress() {
        return yuntAddress;
    }

    public String getQrcodeImg() {
        return qrcodeImg;
    }

    public int getLockedFlag() {
        return lockedFlag;
    }

    public int getAddTime() {
        return addTime;
    }

    public List<String> getAuthorities() {
        return authorities;
    }

}