package com.jf.entity;

import java.math.BigDecimal;
import java.util.Date;

public class MchtSettleCompare {
    private Integer id;

    private String settleMonth;

    private Integer mchtId;

    private BigDecimal beginNotSettle;

    private BigDecimal currentMonthSettle;

    private BigDecimal crossMonthSettle;

    private BigDecimal currentMonthNeedSettle;

    private BigDecimal currentMonthNotSettle;

    private BigDecimal currentSettleOrderAmount;

    private BigDecimal currentRefundAmount;

    private BigDecimal endNotSettle;

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

    public String getSettleMonth() {
        return settleMonth;
    }

    public void setSettleMonth(String settleMonth) {
        this.settleMonth = settleMonth == null ? null : settleMonth.trim();
    }

    public Integer getMchtId() {
        return mchtId;
    }

    public void setMchtId(Integer mchtId) {
        this.mchtId = mchtId;
    }

    public BigDecimal getBeginNotSettle() {
        return beginNotSettle;
    }

    public void setBeginNotSettle(BigDecimal beginNotSettle) {
        this.beginNotSettle = beginNotSettle;
    }

    public BigDecimal getCurrentMonthSettle() {
        return currentMonthSettle;
    }

    public void setCurrentMonthSettle(BigDecimal currentMonthSettle) {
        this.currentMonthSettle = currentMonthSettle;
    }

    public BigDecimal getCrossMonthSettle() {
        return crossMonthSettle;
    }

    public void setCrossMonthSettle(BigDecimal crossMonthSettle) {
        this.crossMonthSettle = crossMonthSettle;
    }

    public BigDecimal getCurrentMonthNeedSettle() {
        return currentMonthNeedSettle;
    }

    public void setCurrentMonthNeedSettle(BigDecimal currentMonthNeedSettle) {
        this.currentMonthNeedSettle = currentMonthNeedSettle;
    }

    public BigDecimal getCurrentMonthNotSettle() {
        return currentMonthNotSettle;
    }

    public void setCurrentMonthNotSettle(BigDecimal currentMonthNotSettle) {
        this.currentMonthNotSettle = currentMonthNotSettle;
    }

    public BigDecimal getCurrentSettleOrderAmount() {
        return currentSettleOrderAmount;
    }

    public void setCurrentSettleOrderAmount(BigDecimal currentSettleOrderAmount) {
        this.currentSettleOrderAmount = currentSettleOrderAmount;
    }

    public BigDecimal getCurrentRefundAmount() {
        return currentRefundAmount;
    }

    public void setCurrentRefundAmount(BigDecimal currentRefundAmount) {
        this.currentRefundAmount = currentRefundAmount;
    }

    public BigDecimal getEndNotSettle() {
        return endNotSettle;
    }

    public void setEndNotSettle(BigDecimal endNotSettle) {
        this.endNotSettle = endNotSettle;
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