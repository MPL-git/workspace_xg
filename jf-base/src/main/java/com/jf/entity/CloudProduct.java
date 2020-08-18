package com.jf.entity;

import java.math.BigDecimal;
import java.util.Date;

public class CloudProduct {
    private Integer id;

    private Integer spOfficeId;

    private Integer cloudProductAfterTempletId;

    private Integer supplierProductBrandId;

    private String artNo;

    private BigDecimal sellingPrice;

    private String skuPic;

    private String status;

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

    public Integer getSpOfficeId() {
        return spOfficeId;
    }

    public void setSpOfficeId(Integer spOfficeId) {
        this.spOfficeId = spOfficeId;
    }

    public Integer getCloudProductAfterTempletId() {
        return cloudProductAfterTempletId;
    }

    public void setCloudProductAfterTempletId(Integer cloudProductAfterTempletId) {
        this.cloudProductAfterTempletId = cloudProductAfterTempletId;
    }

    public Integer getSupplierProductBrandId() {
        return supplierProductBrandId;
    }

    public void setSupplierProductBrandId(Integer supplierProductBrandId) {
        this.supplierProductBrandId = supplierProductBrandId;
    }

    public String getArtNo() {
        return artNo;
    }

    public void setArtNo(String artNo) {
        this.artNo = artNo == null ? null : artNo.trim();
    }

    public BigDecimal getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(BigDecimal sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public String getSkuPic() {
        return skuPic;
    }

    public void setSkuPic(String skuPic) {
        this.skuPic = skuPic == null ? null : skuPic.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
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