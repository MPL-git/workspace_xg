package com.jf.entity;

import java.math.BigDecimal;
import java.util.Date;

public class ProductStatistics {
    private Integer id;

    private Integer productId;

    private Integer soldCount7Days;

    private BigDecimal soldAmount7Days;

    private Integer refundCount7Days;

    private BigDecimal refundAmount7Days;

    private Integer subOrderCount7Days;

    private Integer soldCount30Days;

    private BigDecimal soldAmount30Days;

    private Integer refundCount30Days;

    private BigDecimal refundAmount30Days;

    private Integer subOrderCount30Days;

    private Integer soldCount90Days;

    private BigDecimal soldAmount90Days;

    private Integer refundCount90Days;

    private BigDecimal refundAmount90Days;

    private Integer subOrderCount90Days;

    private BigDecimal applauseRate;

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

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getSoldCount7Days() {
        return soldCount7Days;
    }

    public void setSoldCount7Days(Integer soldCount7Days) {
        this.soldCount7Days = soldCount7Days;
    }

    public BigDecimal getSoldAmount7Days() {
        return soldAmount7Days;
    }

    public void setSoldAmount7Days(BigDecimal soldAmount7Days) {
        this.soldAmount7Days = soldAmount7Days;
    }

    public Integer getRefundCount7Days() {
        return refundCount7Days;
    }

    public void setRefundCount7Days(Integer refundCount7Days) {
        this.refundCount7Days = refundCount7Days;
    }

    public BigDecimal getRefundAmount7Days() {
        return refundAmount7Days;
    }

    public void setRefundAmount7Days(BigDecimal refundAmount7Days) {
        this.refundAmount7Days = refundAmount7Days;
    }

    public Integer getSubOrderCount7Days() {
        return subOrderCount7Days;
    }

    public void setSubOrderCount7Days(Integer subOrderCount7Days) {
        this.subOrderCount7Days = subOrderCount7Days;
    }

    public Integer getSoldCount30Days() {
        return soldCount30Days;
    }

    public void setSoldCount30Days(Integer soldCount30Days) {
        this.soldCount30Days = soldCount30Days;
    }

    public BigDecimal getSoldAmount30Days() {
        return soldAmount30Days;
    }

    public void setSoldAmount30Days(BigDecimal soldAmount30Days) {
        this.soldAmount30Days = soldAmount30Days;
    }

    public Integer getRefundCount30Days() {
        return refundCount30Days;
    }

    public void setRefundCount30Days(Integer refundCount30Days) {
        this.refundCount30Days = refundCount30Days;
    }

    public BigDecimal getRefundAmount30Days() {
        return refundAmount30Days;
    }

    public void setRefundAmount30Days(BigDecimal refundAmount30Days) {
        this.refundAmount30Days = refundAmount30Days;
    }

    public Integer getSubOrderCount30Days() {
        return subOrderCount30Days;
    }

    public void setSubOrderCount30Days(Integer subOrderCount30Days) {
        this.subOrderCount30Days = subOrderCount30Days;
    }

    public Integer getSoldCount90Days() {
        return soldCount90Days;
    }

    public void setSoldCount90Days(Integer soldCount90Days) {
        this.soldCount90Days = soldCount90Days;
    }

    public BigDecimal getSoldAmount90Days() {
        return soldAmount90Days;
    }

    public void setSoldAmount90Days(BigDecimal soldAmount90Days) {
        this.soldAmount90Days = soldAmount90Days;
    }

    public Integer getRefundCount90Days() {
        return refundCount90Days;
    }

    public void setRefundCount90Days(Integer refundCount90Days) {
        this.refundCount90Days = refundCount90Days;
    }

    public BigDecimal getRefundAmount90Days() {
        return refundAmount90Days;
    }

    public void setRefundAmount90Days(BigDecimal refundAmount90Days) {
        this.refundAmount90Days = refundAmount90Days;
    }

    public Integer getSubOrderCount90Days() {
        return subOrderCount90Days;
    }

    public void setSubOrderCount90Days(Integer subOrderCount90Days) {
        this.subOrderCount90Days = subOrderCount90Days;
    }

    public BigDecimal getApplauseRate() {
        return applauseRate;
    }

    public void setApplauseRate(BigDecimal applauseRate) {
        this.applauseRate = applauseRate;
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