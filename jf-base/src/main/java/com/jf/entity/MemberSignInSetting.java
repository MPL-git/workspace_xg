package com.jf.entity;

import java.util.Date;

public class MemberSignInSetting {
    private Integer id;

    private Integer memberId;

    private Integer signInSettingId;

    private Date signInTime;

    private String signInType;

    private String signInDateStr;

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

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Integer getSignInSettingId() {
        return signInSettingId;
    }

    public void setSignInSettingId(Integer signInSettingId) {
        this.signInSettingId = signInSettingId;
    }

    public Date getSignInTime() {
        return signInTime;
    }

    public void setSignInTime(Date signInTime) {
        this.signInTime = signInTime;
    }

    public String getSignInType() {
        return signInType;
    }

    public void setSignInType(String signInType) {
        this.signInType = signInType == null ? null : signInType.trim();
    }

    public String getSignInDateStr() {
        return signInDateStr;
    }

    public void setSignInDateStr(String signInDateStr) {
        this.signInDateStr = signInDateStr == null ? null : signInDateStr.trim();
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