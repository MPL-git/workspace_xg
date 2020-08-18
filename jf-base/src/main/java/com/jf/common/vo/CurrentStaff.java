package com.jf.common.vo;

import java.io.Serializable;

/**
 * @author ：huangdl
 * @description：当前登录平台账号
 * @date ：Created in 2019/8/16
 */
public class CurrentStaff implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private int id;

    /**
     * 登录账号
     */
    private String staffCode;

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
    private String staffName;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStaffCode() {
        return staffCode;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode;
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

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }
}
