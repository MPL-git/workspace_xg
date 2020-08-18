package com.jf.entity;

import java.math.BigDecimal;
import java.util.Date;

public class ActivityRuleConfiguration {
    private Integer id;

    private String type;

    private String priceRules;

    private String salesRules;

    private String stockRules;

    private BigDecimal favorableRate;

    private BigDecimal shopComment;

    private String salesCycleRules;

    private String otherRequirements;

    private String createBy;

    private Date createDate;

    private String updateBy;

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

    public String getPriceRules() {
        return priceRules;
    }

    public void setPriceRules(String priceRules) {
        this.priceRules = priceRules == null ? null : priceRules.trim();
    }

    public String getSalesRules() {
        return salesRules;
    }

    public void setSalesRules(String salesRules) {
        this.salesRules = salesRules == null ? null : salesRules.trim();
    }

    public String getStockRules() {
        return stockRules;
    }

    public void setStockRules(String stockRules) {
        this.stockRules = stockRules == null ? null : stockRules.trim();
    }

    public BigDecimal getFavorableRate() {
        return favorableRate;
    }

    public void setFavorableRate(BigDecimal favorableRate) {
        this.favorableRate = favorableRate;
    }

    public BigDecimal getShopComment() {
        return shopComment;
    }

    public void setShopComment(BigDecimal shopComment) {
        this.shopComment = shopComment;
    }

    public String getSalesCycleRules() {
        return salesCycleRules;
    }

    public void setSalesCycleRules(String salesCycleRules) {
        this.salesCycleRules = salesCycleRules == null ? null : salesCycleRules.trim();
    }

    public String getOtherRequirements() {
        return otherRequirements;
    }

    public void setOtherRequirements(String otherRequirements) {
        this.otherRequirements = otherRequirements == null ? null : otherRequirements.trim();
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
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