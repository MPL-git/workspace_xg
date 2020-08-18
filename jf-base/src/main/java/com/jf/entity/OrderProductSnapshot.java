package com.jf.entity;

import java.util.Date;

public class OrderProductSnapshot {
    private Integer id;

    private Integer orderDtlId;

    private String mainPicGroup;

    private String descPicGroup;

    private String activityDiscount;

    private String suitGroup;

    private String suitSex;

    private String season;

    private String serviceDesc;

    private String freightDesc;

    private Integer createBy;

    private Date createDate;

    private Integer updateBy;

    private Date updateDate;

    private String remarks;

    private String delFlag;

    private String productDesc;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderDtlId() {
        return orderDtlId;
    }

    public void setOrderDtlId(Integer orderDtlId) {
        this.orderDtlId = orderDtlId;
    }

    public String getMainPicGroup() {
        return mainPicGroup;
    }

    public void setMainPicGroup(String mainPicGroup) {
        this.mainPicGroup = mainPicGroup == null ? null : mainPicGroup.trim();
    }

    public String getDescPicGroup() {
        return descPicGroup;
    }

    public void setDescPicGroup(String descPicGroup) {
        this.descPicGroup = descPicGroup == null ? null : descPicGroup.trim();
    }

    public String getActivityDiscount() {
        return activityDiscount;
    }

    public void setActivityDiscount(String activityDiscount) {
        this.activityDiscount = activityDiscount == null ? null : activityDiscount.trim();
    }

    public String getSuitGroup() {
        return suitGroup;
    }

    public void setSuitGroup(String suitGroup) {
        this.suitGroup = suitGroup == null ? null : suitGroup.trim();
    }

    public String getSuitSex() {
        return suitSex;
    }

    public void setSuitSex(String suitSex) {
        this.suitSex = suitSex == null ? null : suitSex.trim();
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season == null ? null : season.trim();
    }

    public String getServiceDesc() {
        return serviceDesc;
    }

    public void setServiceDesc(String serviceDesc) {
        this.serviceDesc = serviceDesc == null ? null : serviceDesc.trim();
    }

    public String getFreightDesc() {
        return freightDesc;
    }

    public void setFreightDesc(String freightDesc) {
        this.freightDesc = freightDesc == null ? null : freightDesc.trim();
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

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc == null ? null : productDesc.trim();
    }
}