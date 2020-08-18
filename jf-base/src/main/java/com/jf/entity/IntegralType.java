package com.jf.entity;

import java.util.Date;

public class IntegralType {
    private Integer id;

    private String name;

    private String tallyMode;

    private String intType;

    private Integer integral;

    private Integer iCharge;

    private String growType;

    private Integer gValue;

    private Integer gCharge;

    private String status;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getTallyMode() {
        return tallyMode;
    }

    public void setTallyMode(String tallyMode) {
        this.tallyMode = tallyMode == null ? null : tallyMode.trim();
    }

    public String getIntType() {
        return intType;
    }

    public void setIntType(String intType) {
        this.intType = intType == null ? null : intType.trim();
    }

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    public Integer getiCharge() {
        return iCharge;
    }

    public void setiCharge(Integer iCharge) {
        this.iCharge = iCharge;
    }

    public String getGrowType() {
        return growType;
    }

    public void setGrowType(String growType) {
        this.growType = growType == null ? null : growType.trim();
    }

    public Integer getgValue() {
        return gValue;
    }

    public void setgValue(Integer gValue) {
        this.gValue = gValue;
    }

    public Integer getgCharge() {
        return gCharge;
    }

    public void setgCharge(Integer gCharge) {
        this.gCharge = gCharge;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
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