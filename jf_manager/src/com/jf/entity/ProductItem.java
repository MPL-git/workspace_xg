package com.jf.entity;

import java.math.BigDecimal;
import java.util.Date;

public class ProductItem {
    private Integer id;

    private Integer productId;

    private Integer cloudProductItemId;

    private String sku;

    private String propValId;

    private Integer stock;

    private Integer lockedAmount;

    private BigDecimal tagPrice;

    private BigDecimal mallPrice;

    private BigDecimal salePrice;

    private BigDecimal costPrice;

    private BigDecimal manageSelfFreight;

    private String pic;

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

    public Integer getCloudProductItemId() {
        return cloudProductItemId;
    }

    public void setCloudProductItemId(Integer cloudProductItemId) {
        this.cloudProductItemId = cloudProductItemId;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku == null ? null : sku.trim();
    }

    public String getPropValId() {
        return propValId;
    }

    public void setPropValId(String propValId) {
        this.propValId = propValId == null ? null : propValId.trim();
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getLockedAmount() {
        return lockedAmount;
    }

    public void setLockedAmount(Integer lockedAmount) {
        this.lockedAmount = lockedAmount;
    }

    public BigDecimal getTagPrice() {
        return tagPrice;
    }

    public void setTagPrice(BigDecimal tagPrice) {
        this.tagPrice = tagPrice;
    }

    public BigDecimal getMallPrice() {
        return mallPrice;
    }

    public void setMallPrice(BigDecimal mallPrice) {
        this.mallPrice = mallPrice;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    public BigDecimal getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(BigDecimal costPrice) {
        this.costPrice = costPrice;
    }

    public BigDecimal getManageSelfFreight() {
        return manageSelfFreight;
    }

    public void setManageSelfFreight(BigDecimal manageSelfFreight) {
        this.manageSelfFreight = manageSelfFreight;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic == null ? null : pic.trim();
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