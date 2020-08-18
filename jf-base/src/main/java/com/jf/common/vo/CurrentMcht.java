package com.jf.common.vo;

import java.io.Serializable;

/**
 * @author ：huangdl
 * @description：当前登录商户账号
 * @date ：Created in 2019/8/16
 */
public class CurrentMcht implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private int id;

    /**
     * 登录账号
     */
    private String userCode;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 昵称
     */
    private String userName;

    /**
     * 是否主账号
     */
    private String isPrimary;


    // 商户相关信息
    private int mchtId;
    private String mchtCode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getIsPrimary() {
        return isPrimary;
    }

    public void setIsPrimary(String isPrimary) {
        this.isPrimary = isPrimary;
    }

    public int getMchtId() {
        return mchtId;
    }

    public void setMchtId(int mchtId) {
        this.mchtId = mchtId;
    }

    public String getMchtCode() {
        return mchtCode;
    }

    public void setMchtCode(String mchtCode) {
        this.mchtCode = mchtCode;
    }
}
