package com.jf.entity;

import java.util.Date;

public class CouponRainRecord {
    private Integer id;

    private Integer couponRainId;

    private Integer memberId;

    private Integer recCount;

    private String memberCouponIds;

    private Integer integral;

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

    public Integer getCouponRainId() {
        return couponRainId;
    }

    public void setCouponRainId(Integer couponRainId) {
        this.couponRainId = couponRainId;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Integer getRecCount() {
        return recCount;
    }

    public void setRecCount(Integer recCount) {
        this.recCount = recCount;
    }

    public String getMemberCouponIds() {
        return memberCouponIds;
    }

    public void setMemberCouponIds(String memberCouponIds) {
        this.memberCouponIds = memberCouponIds == null ? null : memberCouponIds.trim();
    }

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
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