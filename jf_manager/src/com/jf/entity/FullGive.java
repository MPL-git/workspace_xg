package com.jf.entity;

import java.math.BigDecimal;
import java.util.Date;

public class FullGive {
    private Integer id;

    private String rang;

    private String belong;

    private Integer activityAreaId;

    private String type;

    private BigDecimal minimum;

    private String sumFlag;

    private String couponFlag;

    private String couponIdGroup;

    private String integralFlag;

    private Integer integral;

    private String productFlag;

    private Integer productId;

    private Integer productNum;

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

    public String getRang() {
        return rang;
    }

    public void setRang(String rang) {
        this.rang = rang == null ? null : rang.trim();
    }

    public String getBelong() {
        return belong;
    }

    public void setBelong(String belong) {
        this.belong = belong == null ? null : belong.trim();
    }

    public Integer getActivityAreaId() {
        return activityAreaId;
    }

    public void setActivityAreaId(Integer activityAreaId) {
        this.activityAreaId = activityAreaId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public BigDecimal getMinimum() {
        return minimum;
    }

    public void setMinimum(BigDecimal minimum) {
        this.minimum = minimum;
    }

    public String getSumFlag() {
        return sumFlag;
    }

    public void setSumFlag(String sumFlag) {
        this.sumFlag = sumFlag == null ? null : sumFlag.trim();
    }

    public String getCouponFlag() {
        return couponFlag;
    }

    public void setCouponFlag(String couponFlag) {
        this.couponFlag = couponFlag == null ? null : couponFlag.trim();
    }

    public String getCouponIdGroup() {
        return couponIdGroup;
    }

    public void setCouponIdGroup(String couponIdGroup) {
        this.couponIdGroup = couponIdGroup == null ? null : couponIdGroup.trim();
    }

    public String getIntegralFlag() {
        return integralFlag;
    }

    public void setIntegralFlag(String integralFlag) {
        this.integralFlag = integralFlag == null ? null : integralFlag.trim();
    }

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    public String getProductFlag() {
        return productFlag;
    }

    public void setProductFlag(String productFlag) {
        this.productFlag = productFlag == null ? null : productFlag.trim();
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getProductNum() {
        return productNum;
    }

    public void setProductNum(Integer productNum) {
        this.productNum = productNum;
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