package com.jf.entity;

import com.jf.common.ext.core.Model;

import java.util.Date;

public class ActivityArea extends Model {
    private Integer id;

    private String name;

    private String source;

    private String type;

    private Integer mchtId;

    private String entryPic;

    private String pic;

    private Date preheatTime;

    private String preheatFlag;

    private Integer preheatSeqNo;

    private Date enrollBeginTime;

    private Date enrollEndTime;

    private Date activityBeginTime;

    private Date activityEndTime;

    private String description;

    private Integer limitMchtNum;

    private String benefitPoint;

    private Integer minMemberGroup;

    private String pushMchtType;

    private String mchtIdGroup;

    private String productTypeGroup;

    private String preferentialType;

    private String preferentialIdGroup;

    private String urlSuffix;

    private String status;

    private String topPic;

    private String templetType;

    private String templetSuffix;

    private Integer activityGroupId;

    private String isSign;

    private String areaLabel;

    private String areaLabelPic;

    private String purchaseLimits;

    private Integer purchaseLimitsQuantity;

    private String isPreSell;

    private String shareIntegralType;

    private Integer minShareIntegral;

    private Integer maxShareIntegral;

    private String activitySharePic;

    private String activityShareDesc;

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

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Integer getMchtId() {
        return mchtId;
    }

    public void setMchtId(Integer mchtId) {
        this.mchtId = mchtId;
    }

    public String getEntryPic() {
        return entryPic;
    }

    public void setEntryPic(String entryPic) {
        this.entryPic = entryPic == null ? null : entryPic.trim();
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic == null ? null : pic.trim();
    }

    public Date getPreheatTime() {
        return preheatTime;
    }

    public void setPreheatTime(Date preheatTime) {
        this.preheatTime = preheatTime;
    }

    public String getPreheatFlag() {
        return preheatFlag;
    }

    public void setPreheatFlag(String preheatFlag) {
        this.preheatFlag = preheatFlag == null ? null : preheatFlag.trim();
    }

    public Integer getPreheatSeqNo() {
        return preheatSeqNo;
    }

    public void setPreheatSeqNo(Integer preheatSeqNo) {
        this.preheatSeqNo = preheatSeqNo;
    }

    public Date getEnrollBeginTime() {
        return enrollBeginTime;
    }

    public void setEnrollBeginTime(Date enrollBeginTime) {
        this.enrollBeginTime = enrollBeginTime;
    }

    public Date getEnrollEndTime() {
        return enrollEndTime;
    }

    public void setEnrollEndTime(Date enrollEndTime) {
        this.enrollEndTime = enrollEndTime;
    }

    public Date getActivityBeginTime() {
        return activityBeginTime;
    }

    public void setActivityBeginTime(Date activityBeginTime) {
        this.activityBeginTime = activityBeginTime;
    }

    public Date getActivityEndTime() {
        return activityEndTime;
    }

    public void setActivityEndTime(Date activityEndTime) {
        this.activityEndTime = activityEndTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Integer getLimitMchtNum() {
        return limitMchtNum;
    }

    public void setLimitMchtNum(Integer limitMchtNum) {
        this.limitMchtNum = limitMchtNum;
    }

    public String getBenefitPoint() {
        return benefitPoint;
    }

    public void setBenefitPoint(String benefitPoint) {
        this.benefitPoint = benefitPoint == null ? null : benefitPoint.trim();
    }

    public Integer getMinMemberGroup() {
        return minMemberGroup;
    }

    public void setMinMemberGroup(Integer minMemberGroup) {
        this.minMemberGroup = minMemberGroup;
    }

    public String getPushMchtType() {
        return pushMchtType;
    }

    public void setPushMchtType(String pushMchtType) {
        this.pushMchtType = pushMchtType == null ? null : pushMchtType.trim();
    }

    public String getMchtIdGroup() {
        return mchtIdGroup;
    }

    public void setMchtIdGroup(String mchtIdGroup) {
        this.mchtIdGroup = mchtIdGroup == null ? null : mchtIdGroup.trim();
    }

    public String getProductTypeGroup() {
        return productTypeGroup;
    }

    public void setProductTypeGroup(String productTypeGroup) {
        this.productTypeGroup = productTypeGroup == null ? null : productTypeGroup.trim();
    }

    public String getPreferentialType() {
        return preferentialType;
    }

    public void setPreferentialType(String preferentialType) {
        this.preferentialType = preferentialType == null ? null : preferentialType.trim();
    }

    public String getPreferentialIdGroup() {
        return preferentialIdGroup;
    }

    public void setPreferentialIdGroup(String preferentialIdGroup) {
        this.preferentialIdGroup = preferentialIdGroup == null ? null : preferentialIdGroup.trim();
    }

    public String getUrlSuffix() {
        return urlSuffix;
    }

    public void setUrlSuffix(String urlSuffix) {
        this.urlSuffix = urlSuffix == null ? null : urlSuffix.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getTopPic() {
        return topPic;
    }

    public void setTopPic(String topPic) {
        this.topPic = topPic == null ? null : topPic.trim();
    }

    public String getTempletType() {
        return templetType;
    }

    public void setTempletType(String templetType) {
        this.templetType = templetType == null ? null : templetType.trim();
    }

    public String getTempletSuffix() {
        return templetSuffix;
    }

    public void setTempletSuffix(String templetSuffix) {
        this.templetSuffix = templetSuffix == null ? null : templetSuffix.trim();
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

    public String getAreaLabel() {
        return areaLabel;
    }

    public void setAreaLabel(String areaLabel) {
        this.areaLabel = areaLabel == null ? null : areaLabel.trim();
    }

    public String getAreaLabelPic() {
        return areaLabelPic;
    }

    public void setAreaLabelPic(String areaLabelPic) {
        this.areaLabelPic = areaLabelPic == null ? null : areaLabelPic.trim();
    }

    public String getPurchaseLimits() {
        return purchaseLimits;
    }

    public void setPurchaseLimits(String purchaseLimits) {
        this.purchaseLimits = purchaseLimits == null ? null : purchaseLimits.trim();
    }

    public Integer getPurchaseLimitsQuantity() {
        return purchaseLimitsQuantity;
    }

    public void setPurchaseLimitsQuantity(Integer purchaseLimitsQuantity) {
        this.purchaseLimitsQuantity = purchaseLimitsQuantity;
    }

    public String getIsPreSell() {
        return isPreSell;
    }

    public void setIsPreSell(String isPreSell) {
        this.isPreSell = isPreSell == null ? null : isPreSell.trim();
    }

    public String getShareIntegralType() {
        return shareIntegralType;
    }

    public void setShareIntegralType(String shareIntegralType) {
        this.shareIntegralType = shareIntegralType == null ? null : shareIntegralType.trim();
    }

    public Integer getMinShareIntegral() {
        return minShareIntegral;
    }

    public void setMinShareIntegral(Integer minShareIntegral) {
        this.minShareIntegral = minShareIntegral;
    }

    public Integer getMaxShareIntegral() {
        return maxShareIntegral;
    }

    public void setMaxShareIntegral(Integer maxShareIntegral) {
        this.maxShareIntegral = maxShareIntegral;
    }

    public String getActivitySharePic() {
        return activitySharePic;
    }

    public void setActivitySharePic(String activitySharePic) {
        this.activitySharePic = activitySharePic == null ? null : activitySharePic.trim();
    }

    public String getActivityShareDesc() {
        return activityShareDesc;
    }

    public void setActivityShareDesc(String activityShareDesc) {
        this.activityShareDesc = activityShareDesc == null ? null : activityShareDesc.trim();
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