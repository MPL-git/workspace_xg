package com.jf.entity;

import java.util.Date;

public class MallCategorySub {
    private Integer id;

    private String name;

    private Integer productType1;

    private String productType2Ids;

    private String productType3Ids;

    private String productBrandIds;

    private String status;

    private Integer decorateInfoId;

    private Integer seqNo;

    private String suitSex;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getProductType1() {
        return productType1;
    }

    public void setProductType1(Integer productType1) {
        this.productType1 = productType1;
    }

    public String getProductType2Ids() {
        return productType2Ids;
    }

    public void setProductType2Ids(String productType2Ids) {
        this.productType2Ids = productType2Ids == null ? null : productType2Ids.trim();
    }

    public String getProductType3Ids() {
        return productType3Ids;
    }

    public void setProductType3Ids(String productType3Ids) {
        this.productType3Ids = productType3Ids == null ? null : productType3Ids.trim();
    }

    public String getProductBrandIds() {
        return productBrandIds;
    }

    public void setProductBrandIds(String productBrandIds) {
        this.productBrandIds = productBrandIds == null ? null : productBrandIds.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Integer getDecorateInfoId() {
        return decorateInfoId;
    }

    public void setDecorateInfoId(Integer decorateInfoId) {
        this.decorateInfoId = decorateInfoId;
    }

    public Integer getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(Integer seqNo) {
        this.seqNo = seqNo;
    }

    public String getSuitSex() {
        return suitSex;
    }

    public void setSuitSex(String suitSex) {
        this.suitSex = suitSex == null ? null : suitSex.trim();
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