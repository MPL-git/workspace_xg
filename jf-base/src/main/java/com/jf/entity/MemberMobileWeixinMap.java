package com.jf.entity;

import java.util.Date;

public class MemberMobileWeixinMap {
    private Integer id;

    private Integer mobileMemberId;

    private Integer weixinMemberId;

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

    public Integer getMobileMemberId() {
        return mobileMemberId;
    }

    public void setMobileMemberId(Integer mobileMemberId) {
        this.mobileMemberId = mobileMemberId;
    }

    public Integer getWeixinMemberId() {
        return weixinMemberId;
    }

    public void setWeixinMemberId(Integer weixinMemberId) {
        this.weixinMemberId = weixinMemberId;
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