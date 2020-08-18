package com.jf.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Coupon {
    private Integer id;

    private String name;

    private String rang;

    private String belong;

    private String couponType;

    private String typeIds;

    private String canSuperpose;

    private Integer activityAreaId;

    private Integer mchtId;

    private String preferentialType;

    private BigDecimal money;

    private BigDecimal discount;

    private BigDecimal maxDiscountMoney;

    private String conditionType;

    private BigDecimal minimum;

    private Integer minicount;

    private String status;

    private String recType;

    private String careShopCanRec;

    private Integer needIntegral;

    private Date recBeginDate;

    private Date recEndDate;

    private Integer minMemberGroup;

    private String expiryType;

    private Date expiryBeginDate;

    private Date expiryEndDate;

    private Integer expiryDays;

    private Integer grantQuantity;

    private Integer recQuantity;

    private String recLimitType;

    private Integer recEach;

    private String linkType;

    private String linkValue;

    private String definitionPrefix;

    private String isSupportCouponRain;

    private Integer limitCouponRainScore;

    private String activityType;

    private String isIntegralTurntable;

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

    public String getRang() {
        return rang;
    }

    public void setRang(String rang) {
        this.rang = rang == null ? null : rang.trim();
    }

    public String getBelong() {
        return belong;
    }

    public void setBelong(String belong) {
        this.belong = belong == null ? null : belong.trim();
    }

    public String getCouponType() {
        return couponType;
    }

    public void setCouponType(String couponType) {
        this.couponType = couponType == null ? null : couponType.trim();
    }

    public String getTypeIds() {
        return typeIds;
    }

    public void setTypeIds(String typeIds) {
        this.typeIds = typeIds == null ? null : typeIds.trim();
    }

    public String getCanSuperpose() {
        return canSuperpose;
    }

    public void setCanSuperpose(String canSuperpose) {
        this.canSuperpose = canSuperpose == null ? null : canSuperpose.trim();
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

    public String getPreferentialType() {
        return preferentialType;
    }

    public void setPreferentialType(String preferentialType) {
        this.preferentialType = preferentialType == null ? null : preferentialType.trim();
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getMaxDiscountMoney() {
        return maxDiscountMoney;
    }

    public void setMaxDiscountMoney(BigDecimal maxDiscountMoney) {
        this.maxDiscountMoney = maxDiscountMoney;
    }

    public String getConditionType() {
        return conditionType;
    }

    public void setConditionType(String conditionType) {
        this.conditionType = conditionType == null ? null : conditionType.trim();
    }

    public BigDecimal getMinimum() {
        return minimum;
    }

    public void setMinimum(BigDecimal minimum) {
        this.minimum = minimum;
    }

    public Integer getMinicount() {
        return minicount;
    }

    public void setMinicount(Integer minicount) {
        this.minicount = minicount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getRecType() {
        return recType;
    }

    public void setRecType(String recType) {
        this.recType = recType == null ? null : recType.trim();
    }

    public String getCareShopCanRec() {
        return careShopCanRec;
    }

    public void setCareShopCanRec(String careShopCanRec) {
        this.careShopCanRec = careShopCanRec == null ? null : careShopCanRec.trim();
    }

    public Integer getNeedIntegral() {
        return needIntegral;
    }

    public void setNeedIntegral(Integer needIntegral) {
        this.needIntegral = needIntegral;
    }

    public Date getRecBeginDate() {
        return recBeginDate;
    }

    public void setRecBeginDate(Date recBeginDate) {
        this.recBeginDate = recBeginDate;
    }

    public Date getRecEndDate() {
        return recEndDate;
    }

    public void setRecEndDate(Date recEndDate) {
        this.recEndDate = recEndDate;
    }

    public Integer getMinMemberGroup() {
        return minMemberGroup;
    }

    public void setMinMemberGroup(Integer minMemberGroup) {
        this.minMemberGroup = minMemberGroup;
    }

    public String getExpiryType() {
        return expiryType;
    }

    public void setExpiryType(String expiryType) {
        this.expiryType = expiryType == null ? null : expiryType.trim();
    }

    public Date getExpiryBeginDate() {
        return expiryBeginDate;
    }

    public void setExpiryBeginDate(Date expiryBeginDate) {
        this.expiryBeginDate = expiryBeginDate;
    }

    public Date getExpiryEndDate() {
        return expiryEndDate;
    }

    public void setExpiryEndDate(Date expiryEndDate) {
        this.expiryEndDate = expiryEndDate;
    }

    public Integer getExpiryDays() {
        return expiryDays;
    }

    public void setExpiryDays(Integer expiryDays) {
        this.expiryDays = expiryDays;
    }

    public Integer getGrantQuantity() {
        return grantQuantity;
    }

    public void setGrantQuantity(Integer grantQuantity) {
        this.grantQuantity = grantQuantity;
    }

    public Integer getRecQuantity() {
        return recQuantity;
    }

    public void setRecQuantity(Integer recQuantity) {
        this.recQuantity = recQuantity;
    }

    public String getRecLimitType() {
        return recLimitType;
    }

    public void setRecLimitType(String recLimitType) {
        this.recLimitType = recLimitType == null ? null : recLimitType.trim();
    }

    public Integer getRecEach() {
        return recEach;
    }

    public void setRecEach(Integer recEach) {
        this.recEach = recEach;
    }

    public String getLinkType() {
        return linkType;
    }

    public void setLinkType(String linkType) {
        this.linkType = linkType == null ? null : linkType.trim();
    }

    public String getLinkValue() {
        return linkValue;
    }

    public void setLinkValue(String linkValue) {
        this.linkValue = linkValue == null ? null : linkValue.trim();
    }

    public String getDefinitionPrefix() {
        return definitionPrefix;
    }

    public void setDefinitionPrefix(String definitionPrefix) {
        this.definitionPrefix = definitionPrefix == null ? null : definitionPrefix.trim();
    }

    public String getIsSupportCouponRain() {
        return isSupportCouponRain;
    }

    public void setIsSupportCouponRain(String isSupportCouponRain) {
        this.isSupportCouponRain = isSupportCouponRain == null ? null : isSupportCouponRain.trim();
    }

    public Integer getLimitCouponRainScore() {
        return limitCouponRainScore;
    }

    public void setLimitCouponRainScore(Integer limitCouponRainScore) {
        this.limitCouponRainScore = limitCouponRainScore;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType == null ? null : activityType.trim();
    }

    public String getIsIntegralTurntable() {
        return isIntegralTurntable;
    }

    public void setIsIntegralTurntable(String isIntegralTurntable) {
        this.isIntegralTurntable = isIntegralTurntable == null ? null : isIntegralTurntable.trim();
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