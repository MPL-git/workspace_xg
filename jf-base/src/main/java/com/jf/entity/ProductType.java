package com.jf.entity;

import java.math.BigDecimal;
import java.util.Date;

public class ProductType {
    private Integer id;

    private String name;

    private Integer parentId;

    private String pic;

    private String status;

    private Integer seqNo;

    private String suitSex;

    private String suitGroup;

    private BigDecimal deposit;

    private BigDecimal feeRate;

    private Integer tLevel;

    private Integer principalStaffId;

    private BigDecimal individualDeposit;

    private BigDecimal individualFeeRate;

    private BigDecimal enterpriseActivitiesDsr;

    private Integer enterpriseTurnover;

    private Integer enterpriseActivitySales;

    private String isReturn7days;

    private String isSmallware;

    private Integer createBy;

    private Date createDate;

    private Integer updateBy;

    private Date updateDate;

    private String remarks;

    private String delFlag;

    private String brandAptitude;

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

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic == null ? null : pic.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
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

    public String getSuitGroup() {
        return suitGroup;
    }

    public void setSuitGroup(String suitGroup) {
        this.suitGroup = suitGroup == null ? null : suitGroup.trim();
    }

    public BigDecimal getDeposit() {
        return deposit;
    }

    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
    }

    public BigDecimal getFeeRate() {
        return feeRate;
    }

    public void setFeeRate(BigDecimal feeRate) {
        this.feeRate = feeRate;
    }

    public Integer gettLevel() {
        return tLevel;
    }

    public void settLevel(Integer tLevel) {
        this.tLevel = tLevel;
    }

    public Integer getPrincipalStaffId() {
        return principalStaffId;
    }

    public void setPrincipalStaffId(Integer principalStaffId) {
        this.principalStaffId = principalStaffId;
    }

    public BigDecimal getIndividualDeposit() {
        return individualDeposit;
    }

    public void setIndividualDeposit(BigDecimal individualDeposit) {
        this.individualDeposit = individualDeposit;
    }

    public BigDecimal getIndividualFeeRate() {
        return individualFeeRate;
    }

    public void setIndividualFeeRate(BigDecimal individualFeeRate) {
        this.individualFeeRate = individualFeeRate;
    }

    public BigDecimal getEnterpriseActivitiesDsr() {
        return enterpriseActivitiesDsr;
    }

    public void setEnterpriseActivitiesDsr(BigDecimal enterpriseActivitiesDsr) {
        this.enterpriseActivitiesDsr = enterpriseActivitiesDsr;
    }

    public Integer getEnterpriseTurnover() {
        return enterpriseTurnover;
    }

    public void setEnterpriseTurnover(Integer enterpriseTurnover) {
        this.enterpriseTurnover = enterpriseTurnover;
    }

    public Integer getEnterpriseActivitySales() {
        return enterpriseActivitySales;
    }

    public void setEnterpriseActivitySales(Integer enterpriseActivitySales) {
        this.enterpriseActivitySales = enterpriseActivitySales;
    }

    public String getIsReturn7days() {
        return isReturn7days;
    }

    public void setIsReturn7days(String isReturn7days) {
        this.isReturn7days = isReturn7days == null ? null : isReturn7days.trim();
    }

    public String getIsSmallware() {
        return isSmallware;
    }

    public void setIsSmallware(String isSmallware) {
        this.isSmallware = isSmallware == null ? null : isSmallware.trim();
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

    public String getBrandAptitude() {
        return brandAptitude;
    }

    public void setBrandAptitude(String brandAptitude) {
        this.brandAptitude = brandAptitude == null ? null : brandAptitude.trim();
    }
}