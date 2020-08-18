package com.jf.entity;

import java.util.Date;

public class ProductWeight {
    private Integer id;

    private Integer productId;

    private Integer seasonWeight;

    private Integer saleWeight;

    private Integer saleAmountWeight;

    private Integer pvWeight;

    private Integer mchtGradeWeight;

    private Integer brandGradeWeight;

    private Integer commentWeight;

    private Integer manualWeight;

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

    public Integer getSeasonWeight() {
        return seasonWeight;
    }

    public void setSeasonWeight(Integer seasonWeight) {
        this.seasonWeight = seasonWeight;
    }

    public Integer getSaleWeight() {
        return saleWeight;
    }

    public void setSaleWeight(Integer saleWeight) {
        this.saleWeight = saleWeight;
    }

    public Integer getSaleAmountWeight() {
        return saleAmountWeight;
    }

    public void setSaleAmountWeight(Integer saleAmountWeight) {
        this.saleAmountWeight = saleAmountWeight;
    }

    public Integer getPvWeight() {
        return pvWeight;
    }

    public void setPvWeight(Integer pvWeight) {
        this.pvWeight = pvWeight;
    }

    public Integer getMchtGradeWeight() {
        return mchtGradeWeight;
    }

    public void setMchtGradeWeight(Integer mchtGradeWeight) {
        this.mchtGradeWeight = mchtGradeWeight;
    }

    public Integer getBrandGradeWeight() {
        return brandGradeWeight;
    }

    public void setBrandGradeWeight(Integer brandGradeWeight) {
        this.brandGradeWeight = brandGradeWeight;
    }

    public Integer getCommentWeight() {
        return commentWeight;
    }

    public void setCommentWeight(Integer commentWeight) {
        this.commentWeight = commentWeight;
    }

    public Integer getManualWeight() {
        return manualWeight;
    }

    public void setManualWeight(Integer manualWeight) {
        this.manualWeight = manualWeight;
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