package com.jf.entity;

import java.math.BigDecimal;
import java.util.Date;

public class TypeColumnPvStatistical {
    private Integer id;

    private String type;

    private Integer valueId;

    private String statisticalDate;

    private String columnType;

    private Integer totalVisitorCount;

    private Integer totalVisitorCountTourist;

    private Integer totalPvCount;

    private Integer totalPvCountTourist;

    private Integer shoppingCartCount;

    private Integer subProductCount;

    private Integer payProductCount;

    private BigDecimal payAmountCount;

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

    public Integer getValueId() {
        return valueId;
    }

    public void setValueId(Integer valueId) {
        this.valueId = valueId;
    }

    public String getStatisticalDate() {
        return statisticalDate;
    }

    public void setStatisticalDate(String statisticalDate) {
        this.statisticalDate = statisticalDate == null ? null : statisticalDate.trim();
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType == null ? null : columnType.trim();
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

    public Integer getShoppingCartCount() {
        return shoppingCartCount;
    }

    public void setShoppingCartCount(Integer shoppingCartCount) {
        this.shoppingCartCount = shoppingCartCount;
    }

    public Integer getSubProductCount() {
        return subProductCount;
    }

    public void setSubProductCount(Integer subProductCount) {
        this.subProductCount = subProductCount;
    }

    public Integer getPayProductCount() {
        return payProductCount;
    }

    public void setPayProductCount(Integer payProductCount) {
        this.payProductCount = payProductCount;
    }

    public BigDecimal getPayAmountCount() {
        return payAmountCount;
    }

    public void setPayAmountCount(BigDecimal payAmountCount) {
        this.payAmountCount = payAmountCount;
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