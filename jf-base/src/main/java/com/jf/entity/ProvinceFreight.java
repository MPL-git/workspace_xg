package com.jf.entity;

import java.math.BigDecimal;
import java.util.Date;

public class ProvinceFreight {
    private Integer id;

    private Integer mchtId;

    private Integer freightTemplateId;

    private String areaIds;

    private BigDecimal firstFreight;

    private BigDecimal additionalFreight;

    private String isFullReductionFreight;

    private Integer fullNumber;

    private BigDecimal fullReductionFreight;

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

    public Integer getFreightTemplateId() {
        return freightTemplateId;
    }

    public void setFreightTemplateId(Integer freightTemplateId) {
        this.freightTemplateId = freightTemplateId;
    }

    public String getAreaIds() {
        return areaIds;
    }

    public void setAreaIds(String areaIds) {
        this.areaIds = areaIds == null ? null : areaIds.trim();
    }

    public BigDecimal getFirstFreight() {
        return firstFreight;
    }

    public void setFirstFreight(BigDecimal firstFreight) {
        this.firstFreight = firstFreight;
    }

    public BigDecimal getAdditionalFreight() {
        return additionalFreight;
    }

    public void setAdditionalFreight(BigDecimal additionalFreight) {
        this.additionalFreight = additionalFreight;
    }

    public String getIsFullReductionFreight() {
        return isFullReductionFreight;
    }

    public void setIsFullReductionFreight(String isFullReductionFreight) {
        this.isFullReductionFreight = isFullReductionFreight == null ? null : isFullReductionFreight.trim();
    }

    public Integer getFullNumber() {
        return fullNumber;
    }

    public void setFullNumber(Integer fullNumber) {
        this.fullNumber = fullNumber;
    }

    public BigDecimal getFullReductionFreight() {
        return fullReductionFreight;
    }

    public void setFullReductionFreight(BigDecimal fullReductionFreight) {
        this.fullReductionFreight = fullReductionFreight;
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