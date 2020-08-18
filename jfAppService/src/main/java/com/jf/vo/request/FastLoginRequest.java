package com.jf.vo.request;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author luoyb
 * Created on 2020/6/17
 */
public class FastLoginRequest {

    @NotBlank(message = "token 不能为空")
    private String token; //需要解析的凭证值
    private String authCode; //电信校验码，在电信获取号码时验证
    @NotBlank(message = "carrier 不能为空")
    private String carrier; //运营商（移动：mobile， 联通：unicom，电信：telecom）
    @NotBlank(message = "system 不能为空")
    private String system;  //起调系统：IOS、Android
    @NotBlank(message = "mobileBrand 不能为空")
    private String mobileBrand;
    @NotBlank(message = "mobileModel 不能为空")
    private String mobileModel;
    @NotBlank(message = "versionNum 不能为空")
    private String versionNum;
    @NotBlank(message = "version 不能为空")
    private String version;
    private String imei;
    @NotBlank(message = "deviceId 不能为空")
    private String deviceId;
    @NotBlank(message = "sprChnl 不能为空")
    private String sprChnl;

    public String getVersionNum() {
        return versionNum;
    }

    public void setVersionNum(String versionNum) {
        this.versionNum = versionNum;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public String getMobileBrand() {
        return mobileBrand;
    }

    public void setMobileBrand(String mobileBrand) {
        this.mobileBrand = mobileBrand;
    }

    public String getMobileModel() {
        return mobileModel;
    }

    public void setMobileModel(String mobileModel) {
        this.mobileModel = mobileModel;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getSprChnl() {
        return sprChnl;
    }

    public void setSprChnl(String sprChnl) {
        this.sprChnl = sprChnl;
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
}
