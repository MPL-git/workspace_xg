package com.jf.vo.request;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * @author luoyb
 * Created on 2020/6/18
 */
public class FastBindMobileRequest {

    @NotBlank(message = "token 不能为空")
    private String token; //需要解析的凭证值
    private String authCode; //电信校验码，在电信获取号码时验证
    @NotBlank(message = "carrier 不能为空")
    private String carrier; //运营商（移动：mobile， 联通：unicom，电信：telecom）

    @NotNull(message = "memberId 不能为空")
    private Integer memberId;
    @NotBlank(message = "deviceId 不能为空")
    private String deviceId;
    @NotBlank(message = "system 不能为空")
    private String system; //起调系统：IOS、Android

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
}
