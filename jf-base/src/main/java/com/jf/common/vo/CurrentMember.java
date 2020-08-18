package com.jf.common.vo;

import java.io.Serializable;

/**
 * @author ：huangdl
 * @description：当前登录会员账号
 * @date ：Created in 2019/8/16
 */
public class CurrentMember implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private int id;

    /**
     * 登录账号
     */
    private String loginCode;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 头像
     */
    private String pic;

    /**
     * 微信头像
     */
    private String weixinHead;

    /**
     * 昵称
     */
    private String nick;

    /**
     * 令牌
     */
    private String token;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLoginCode() {
        return loginCode;
    }

    public void setLoginCode(String loginCode) {
        this.loginCode = loginCode;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getWeixinHead() {
        return weixinHead;
    }

    public void setWeixinHead(String weixinHead) {
        this.weixinHead = weixinHead;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
