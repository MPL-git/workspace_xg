package com.jf.entity;

import java.math.BigDecimal;
import java.util.Date;

public class CouponRainSetup {
    private Integer id;

    private String title;

    private String includeIntegral;

    private BigDecimal includeIntegralPercent;

    private Integer includeIntegralMin;

    private Integer includeIntegralMax;

    private String includeProductCoupon;

    private BigDecimal includeProductCouponPercent;

    private Date recBeginDate;

    private Date recEndDate;

    private Integer limitRecCount;

    private String pic;

    private String includeSale;

    private BigDecimal includeSalePercent;

    private String includePlatformCoupon;

    private BigDecimal includePlatformCouponPercent;

    private Integer seqNo;

    private String redEnvelopeType;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getIncludeIntegral() {
        return includeIntegral;
    }

    public void setIncludeIntegral(String includeIntegral) {
        this.includeIntegral = includeIntegral == null ? null : includeIntegral.trim();
    }

    public BigDecimal getIncludeIntegralPercent() {
        return includeIntegralPercent;
    }

    public void setIncludeIntegralPercent(BigDecimal includeIntegralPercent) {
        this.includeIntegralPercent = includeIntegralPercent;
    }

    public Integer getIncludeIntegralMin() {
        return includeIntegralMin;
    }

    public void setIncludeIntegralMin(Integer includeIntegralMin) {
        this.includeIntegralMin = includeIntegralMin;
    }

    public Integer getIncludeIntegralMax() {
        return includeIntegralMax;
    }

    public void setIncludeIntegralMax(Integer includeIntegralMax) {
        this.includeIntegralMax = includeIntegralMax;
    }

    public String getIncludeProductCoupon() {
        return includeProductCoupon;
    }

    public void setIncludeProductCoupon(String includeProductCoupon) {
        this.includeProductCoupon = includeProductCoupon == null ? null : includeProductCoupon.trim();
    }

    public BigDecimal getIncludeProductCouponPercent() {
        return includeProductCouponPercent;
    }

    public void setIncludeProductCouponPercent(BigDecimal includeProductCouponPercent) {
        this.includeProductCouponPercent = includeProductCouponPercent;
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

    public Integer getLimitRecCount() {
        return limitRecCount;
    }

    public void setLimitRecCount(Integer limitRecCount) {
        this.limitRecCount = limitRecCount;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic == null ? null : pic.trim();
    }

    public String getIncludeSale() {
        return includeSale;
    }

    public void setIncludeSale(String includeSale) {
        this.includeSale = includeSale == null ? null : includeSale.trim();
    }

    public BigDecimal getIncludeSalePercent() {
        return includeSalePercent;
    }

    public void setIncludeSalePercent(BigDecimal includeSalePercent) {
        this.includeSalePercent = includeSalePercent;
    }

    public String getIncludePlatformCoupon() {
        return includePlatformCoupon;
    }

    public void setIncludePlatformCoupon(String includePlatformCoupon) {
        this.includePlatformCoupon = includePlatformCoupon == null ? null : includePlatformCoupon.trim();
    }

    public BigDecimal getIncludePlatformCouponPercent() {
        return includePlatformCouponPercent;
    }

    public void setIncludePlatformCouponPercent(BigDecimal includePlatformCouponPercent) {
        this.includePlatformCouponPercent = includePlatformCouponPercent;
    }

    public Integer getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(Integer seqNo) {
        this.seqNo = seqNo;
    }

    public String getRedEnvelopeType() {
        return redEnvelopeType;
    }

    public void setRedEnvelopeType(String redEnvelopeType) {
        this.redEnvelopeType = redEnvelopeType == null ? null : redEnvelopeType.trim();
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