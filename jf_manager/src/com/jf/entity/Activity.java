package com.jf.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.jf.common.ext.core.Model;

@SuppressWarnings("serial")
public class Activity extends Model{
    private Integer id;

    private String name;

    private Integer activityAreaId;

    private Integer mchtId;

    private Integer productTypeId;

    private Integer productTypeSecondId;

    private String brandLimitType;

    private Integer productBrandId;

    private BigDecimal feeRate;

    private Date submitTime;

    private Date expectedStartTime;

    private String entryPic;
    
    private String brandteamPic;

    private String posterPic;

    private Integer posterLinkId;

    private String status;

    private Integer operateAuditBy;

    private String operateAuditStatus;

    private String operateAuditRemarks;

    private Integer designAuditBy;

    private String designAuditStatus;

    private String designAuditRemarks;

    private Integer lawAuditBy;

    private String lawAuditStatus;

    private String lawAuditRemarks;

    private Integer cooAuditBy;

    private String cooAuditStatus;

    private String cooAuditRemarks;

    private Integer seqNo;

    private Integer activityGroupId;

    private String isSign;

    private String preSellAuditStatus;

    private String preSellAuditRemarks;

    private Integer createBy;

    private Date createDate;

    private Integer updateBy;

    private Date updateDate;

    private String remarks;

    private String delFlag;

    private String designRejectReason;

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

    public Integer getActivityAreaId() {
        return activityAreaId;
    }

    public void setActivityAreaId(Integer activityAreaId) {
        this.activityAreaId = activityAreaId;
    }

    public Integer getMchtId() {
        return mchtId;
    }

    public void setMchtId(Integer mchtId) {
        this.mchtId = mchtId;
    }

    public Integer getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(Integer productTypeId) {
        this.productTypeId = productTypeId;
    }

    public Integer getProductTypeSecondId() {
        return productTypeSecondId;
    }

    public void setProductTypeSecondId(Integer productTypeSecondId) {
        this.productTypeSecondId = productTypeSecondId;
    }

    public String getBrandLimitType() {
        return brandLimitType;
    }

    public void setBrandLimitType(String brandLimitType) {
        this.brandLimitType = brandLimitType == null ? null : brandLimitType.trim();
    }

    public Integer getProductBrandId() {
        return productBrandId;
    }

    public void setProductBrandId(Integer productBrandId) {
        this.productBrandId = productBrandId;
    }

    public BigDecimal getFeeRate() {
        return feeRate;
    }

    public void setFeeRate(BigDecimal feeRate) {
        this.feeRate = feeRate;
    }

    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    public Date getExpectedStartTime() {
        return expectedStartTime;
    }

    public void setExpectedStartTime(Date expectedStartTime) {
        this.expectedStartTime = expectedStartTime;
    }

    public String getEntryPic() {
        return entryPic;
    }

    public void setEntryPic(String entryPic) {
        this.entryPic = entryPic == null ? null : entryPic.trim();
    }

    public String getPosterPic() {
        return posterPic;
    }

    public void setPosterPic(String posterPic) {
        this.posterPic = posterPic == null ? null : posterPic.trim();
    }

    public Integer getPosterLinkId() {
        return posterLinkId;
    }

    public void setPosterLinkId(Integer posterLinkId) {
        this.posterLinkId = posterLinkId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Integer getOperateAuditBy() {
        return operateAuditBy;
    }

    public void setOperateAuditBy(Integer operateAuditBy) {
        this.operateAuditBy = operateAuditBy;
    }

    public String getOperateAuditStatus() {
        return operateAuditStatus;
    }

    public void setOperateAuditStatus(String operateAuditStatus) {
        this.operateAuditStatus = operateAuditStatus == null ? null : operateAuditStatus.trim();
    }

    public String getOperateAuditRemarks() {
        return operateAuditRemarks;
    }

    public void setOperateAuditRemarks(String operateAuditRemarks) {
        this.operateAuditRemarks = operateAuditRemarks == null ? null : operateAuditRemarks.trim();
    }

    public Integer getDesignAuditBy() {
        return designAuditBy;
    }

    public void setDesignAuditBy(Integer designAuditBy) {
        this.designAuditBy = designAuditBy;
    }

    public String getDesignAuditStatus() {
        return designAuditStatus;
    }

    public void setDesignAuditStatus(String designAuditStatus) {
        this.designAuditStatus = designAuditStatus == null ? null : designAuditStatus.trim();
    }

    public String getDesignAuditRemarks() {
        return designAuditRemarks;
    }

    public void setDesignAuditRemarks(String designAuditRemarks) {
        this.designAuditRemarks = designAuditRemarks == null ? null : designAuditRemarks.trim();
    }

    public Integer getLawAuditBy() {
        return lawAuditBy;
    }

    public void setLawAuditBy(Integer lawAuditBy) {
        this.lawAuditBy = lawAuditBy;
    }

    public String getLawAuditStatus() {
        return lawAuditStatus;
    }

    public void setLawAuditStatus(String lawAuditStatus) {
        this.lawAuditStatus = lawAuditStatus == null ? null : lawAuditStatus.trim();
    }

    public String getLawAuditRemarks() {
        return lawAuditRemarks;
    }

    public void setLawAuditRemarks(String lawAuditRemarks) {
        this.lawAuditRemarks = lawAuditRemarks == null ? null : lawAuditRemarks.trim();
    }

    public Integer getCooAuditBy() {
        return cooAuditBy;
    }

    public void setCooAuditBy(Integer cooAuditBy) {
        this.cooAuditBy = cooAuditBy;
    }

    public String getCooAuditStatus() {
        return cooAuditStatus;
    }

    public void setCooAuditStatus(String cooAuditStatus) {
        this.cooAuditStatus = cooAuditStatus == null ? null : cooAuditStatus.trim();
    }

    public String getCooAuditRemarks() {
        return cooAuditRemarks;
    }

    public void setCooAuditRemarks(String cooAuditRemarks) {
        this.cooAuditRemarks = cooAuditRemarks == null ? null : cooAuditRemarks.trim();
    }

    public Integer getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(Integer seqNo) {
        this.seqNo = seqNo;
    }

    public Integer getActivityGroupId() {
        return activityGroupId;
    }

    public void setActivityGroupId(Integer activityGroupId) {
        this.activityGroupId = activityGroupId;
    }

    public String getIsSign() {
        return isSign;
    }

    public void setIsSign(String isSign) {
        this.isSign = isSign == null ? null : isSign.trim();
    }

    public String getPreSellAuditStatus() {
        return preSellAuditStatus;
    }

    public void setPreSellAuditStatus(String preSellAuditStatus) {
        this.preSellAuditStatus = preSellAuditStatus == null ? null : preSellAuditStatus.trim();
    }

    public String getPreSellAuditRemarks() {
        return preSellAuditRemarks;
    }

    public void setPreSellAuditRemarks(String preSellAuditRemarks) {
        this.preSellAuditRemarks = preSellAuditRemarks == null ? null : preSellAuditRemarks.trim();
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

    public String getDesignRejectReason() {
        return designRejectReason;
    }

    public void setDesignRejectReason(String designRejectReason) {
        this.designRejectReason = designRejectReason == null ? null : designRejectReason.trim();
    }

	public String getBrandteamPic() {
		return brandteamPic;
	}

	public void setBrandteamPic(String brandteamPic) {
		this.brandteamPic = brandteamPic;
	}
    
}