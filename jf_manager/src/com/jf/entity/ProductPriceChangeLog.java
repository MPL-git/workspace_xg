package com.jf.entity;

import java.math.BigDecimal;
import java.util.Date;

public class ProductPriceChangeLog {
    private Integer id;

    private Integer productId;

    private BigDecimal minMallPrice;

    private Integer minMallPriceItemId;

    private BigDecimal minSalePrice;

    private Integer minSalePriceItemId;

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

    public BigDecimal getMinMallPrice() {
        return minMallPrice;
    }

    public void setMinMallPrice(BigDecimal minMallPrice) {
        this.minMallPrice = minMallPrice;
    }

    public Integer getMinMallPriceItemId() {
        return minMallPriceItemId;
    }

    public void setMinMallPriceItemId(Integer minMallPriceItemId) {
        this.minMallPriceItemId = minMallPriceItemId;
    }

    public BigDecimal getMinSalePrice() {
        return minSalePrice;
    }

    public void setMinSalePrice(BigDecimal minSalePrice) {
        this.minSalePrice = minSalePrice;
    }

    public Integer getMinSalePriceItemId() {
        return minSalePriceItemId;
    }

    public void setMinSalePriceItemId(Integer minSalePriceItemId) {
        this.minSalePriceItemId = minSalePriceItemId;
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