package com.jf.entity;

import java.math.BigDecimal;
import java.util.Date;

public class MchtPvStatistical {
    private Integer id;

    private Integer mchtId;

    private String statisticalDate;

    private Integer totalVisitorCount;

    private Integer totalVisitorCountTourist;

    private Integer totalPvCount;

    private Integer totalPvCountTourist;

    private BigDecimal payAmount;

    private Integer payCombineOrderCount;

    private Integer paySubOrderCount;

    private Integer payProductCount;

    private Integer payMemberCount;

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

    public Integer getMchtId() {
        return mchtId;
    }

    public void setMchtId(Integer mchtId) {
        this.mchtId = mchtId;
    }

    public String getStatisticalDate() {
        return statisticalDate;
    }

    public void setStatisticalDate(String statisticalDate) {
        this.statisticalDate = statisticalDate == null ? null : statisticalDate.trim();
    }

    public Integer getTotalVisitorCount() {
        return totalVisitorCount;
    }

    public void setTotalVisitorCount(Integer totalVisitorCount) {
        this.totalVisitorCount = totalVisitorCount;
    }

    public Integer getTotalVisitorCountTourist() {
        return totalVisitorCountTourist;
    }

    public void setTotalVisitorCountTourist(Integer totalVisitorCountTourist) {
        this.totalVisitorCountTourist = totalVisitorCountTourist;
    }

    public Integer getTotalPvCount() {
        return totalPvCount;
    }

    public void setTotalPvCount(Integer totalPvCount) {
        this.totalPvCount = totalPvCount;
    }

    public Integer getTotalPvCountTourist() {
        return totalPvCountTourist;
    }

    public void setTotalPvCountTourist(Integer totalPvCountTourist) {
        this.totalPvCountTourist = totalPvCountTourist;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    public Integer getPayCombineOrderCount() {
        return payCombineOrderCount;
    }

    public void setPayCombineOrderCount(Integer payCombineOrderCount) {
        this.payCombineOrderCount = payCombineOrderCount;
    }

    public Integer getPaySubOrderCount() {
        return paySubOrderCount;
    }

    public void setPaySubOrderCount(Integer paySubOrderCount) {
        this.paySubOrderCount = paySubOrderCount;
    }

    public Integer getPayProductCount() {
        return payProductCount;
    }

    public void setPayProductCount(Integer payProductCount) {
        this.payProductCount = payProductCount;
    }

    public Integer getPayMemberCount() {
        return payMemberCount;
    }

    public void setPayMemberCount(Integer payMemberCount) {
        this.payMemberCount = payMemberCount;
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