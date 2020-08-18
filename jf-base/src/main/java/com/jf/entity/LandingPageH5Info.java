package com.jf.entity;

import java.util.Date;

public class LandingPageH5Info {
    private Integer id;

    private String type;

    private String ip;

    private String reqModel;

    private String systemVersion;

    private String androidChnl;

    private Integer iosActivityGroupId;

    private String iosActivityName;

    private Integer createBy;

    private Date createDate;

    private Integer updateBy;

    private Date updateDate;

    private String remarks;

    private String delFlag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public String getReqModel() {
        return reqModel;
    }

    public void setReqModel(String reqModel) {
        this.reqModel = reqModel == null ? null : reqModel.trim();
    }

    public String getSystemVersion() {
        return systemVersion;
    }

    public void setSystemVersion(String systemVersion) {
        this.systemVersion = systemVersion == null ? null : systemVersion.trim();
    }

    public String getAndroidChnl() {
        return androidChnl;
    }

    public void setAndroidChnl(String androidChnl) {
        this.androidChnl = androidChnl == null ? null : androidChnl.trim();
    }

    public Integer getIosActivityGroupId() {
        return iosActivityGroupId;
    }

    public void setIosActivityGroupId(Integer iosActivityGroupId) {
        this.iosActivityGroupId = iosActivityGroupId;
    }

    public String getIosActivityName() {
        return iosActivityName;
    }

    public void setIosActivityName(String iosActivityName) {
        this.iosActivityName = iosActivityName == null ? null : iosActivityName.trim();
    }

    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag == null ? null : delFlag.trim();
    }
}