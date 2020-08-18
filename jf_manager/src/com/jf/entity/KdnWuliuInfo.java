package com.jf.entity;

import com.jf.common.ext.core.Model;

import java.util.Date;

public class KdnWuliuInfo extends Model{

    private Integer id;

    private Integer expressId;

    private String logisticCode;

    private String subscribeStatus;

    private String subscribeFailedReason;

    private Date subscribeTime;

    private Integer tryTimes;

    private String tractInfo;

    private String tractInfoSource;

    private String zzyTaskName;

    private String status;

    private Integer subOrderId;

    private String merchantCode;

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

    public Integer getExpressId() {
        return expressId;
    }

    public void setExpressId(Integer expressId) {
        this.expressId = expressId;
    }

    public String getLogisticCode() {
        return logisticCode;
    }

    public void setLogisticCode(String logisticCode) {
        this.logisticCode = logisticCode == null ? null : logisticCode.trim();
    }

    public String getSubscribeStatus() {
        return subscribeStatus;
    }

    public void setSubscribeStatus(String subscribeStatus) {
        this.subscribeStatus = subscribeStatus == null ? null : subscribeStatus.trim();
    }

    public String getSubscribeFailedReason() {
        return subscribeFailedReason;
    }

    public void setSubscribeFailedReason(String subscribeFailedReason) {
        this.subscribeFailedReason = subscribeFailedReason == null ? null : subscribeFailedReason.trim();
    }

    public Date getSubscribeTime() {
        return subscribeTime;
    }

    public void setSubscribeTime(Date subscribeTime) {
        this.subscribeTime = subscribeTime;
    }

    public Integer getTryTimes() {
        return tryTimes;
    }

    public void setTryTimes(Integer tryTimes) {
        this.tryTimes = tryTimes;
    }

    public String getTractInfo() {
        return tractInfo;
    }

    public void setTractInfo(String tractInfo) {
        this.tractInfo = tractInfo == null ? null : tractInfo.trim();
    }

    public String getTractInfoSource() {
        return tractInfoSource;
    }

    public void setTractInfoSource(String tractInfoSource) {
        this.tractInfoSource = tractInfoSource == null ? null : tractInfoSource.trim();
    }

    public String getZzyTaskName() {
        return zzyTaskName;
    }

    public void setZzyTaskName(String zzyTaskName) {
        this.zzyTaskName = zzyTaskName == null ? null : zzyTaskName.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Integer getSubOrderId() {
        return subOrderId;
    }

    public void setSubOrderId(Integer subOrderId) {
        this.subOrderId = subOrderId;
    }

    public String getMerchantCode() {
        return merchantCode;
    }

    public void setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode == null ? null : merchantCode.trim();
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