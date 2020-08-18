package com.jf.entity;

import java.math.BigDecimal;
import java.util.Date;

public class TaskSms {
    private Integer id;

    private Integer taskId;

    private String sendStatus;

    private Integer sendMemberCount;

    private Integer sendCount;

    private BigDecimal totalSendAmount;

    private String isGiveCoupon;

    private Integer couponId;

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

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(String sendStatus) {
        this.sendStatus = sendStatus == null ? null : sendStatus.trim();
    }

    public Integer getSendMemberCount() {
        return sendMemberCount;
    }

    public void setSendMemberCount(Integer sendMemberCount) {
        this.sendMemberCount = sendMemberCount;
    }

    public Integer getSendCount() {
        return sendCount;
    }

    public void setSendCount(Integer sendCount) {
        this.sendCount = sendCount;
    }

    public BigDecimal getTotalSendAmount() {
        return totalSendAmount;
    }

    public void setTotalSendAmount(BigDecimal totalSendAmount) {
        this.totalSendAmount = totalSendAmount;
    }

    public String getIsGiveCoupon() {
        return isGiveCoupon;
    }

    public void setIsGiveCoupon(String isGiveCoupon) {
        this.isGiveCoupon = isGiveCoupon == null ? null : isGiveCoupon.trim();
    }

    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
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