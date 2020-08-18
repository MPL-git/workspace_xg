package com.jf.entity;

import java.math.BigDecimal;
import java.util.Date;

public class ActivityPvStatisticalCustom extends ActivityPvStatistical {
    private String name;
    private String productTypeName;
    private Integer totalExposure;
    private BigDecimal inversionRate;
    private BigDecimal exposureAmount;
    private Date preheatTime;
    private Date activityBeginTime;
    private Date activityEndTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProductTypeName() {
        return productTypeName;
    }

    public void setProductTypeName(String productTypeName) {
        this.productTypeName = productTypeName;
    }

    public Integer getTotalExposure() {
        return totalExposure;
    }

    public void setTotalExposure(Integer totalExposure) {
        this.totalExposure = totalExposure;
    }

    public BigDecimal getInversionRate() {
        return inversionRate;
    }

    public void setInversionRate(BigDecimal inversionRate) {
        this.inversionRate = inversionRate;
    }

    public Date getPreheatTime() {
        return preheatTime;
    }

    public void setPreheatTime(Date preheatTime) {
        this.preheatTime = preheatTime;
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

    public BigDecimal getExposureAmount() {
        return exposureAmount;
    }

    public void setExposureAmount(BigDecimal exposureAmount) {
        this.exposureAmount = exposureAmount;
    }
}