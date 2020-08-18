package com.jf.entity;

import java.math.BigDecimal;
import java.util.Date;

public class ViolateOrder {
    private Integer id;

    private String orderCode;

    private Integer mchtId;

    private Integer subOrderId;

    private String violateType;

    private String violateAction;

    private String content;

    private BigDecimal money;

    private String punishMeans;

    private String platformRemarks;

    private String orderSource;

    private Date violateDate;

    private Date complainDate;

    private Date complainEndDate;

    private String status;

    private String auditStatus;

    private String auditRemarks;

    private Date auditDate;

    private Integer platformProcessor;

    private String complainInfoStatus;

    private String enclosure;

    private String jifenStatus;

    private Integer jifenIntegral;

    private String enableHours;

    private String jifenIntegralDesc;

    private String againAuditStatus;

    private Integer againAuditBy;

    private Date againAuditDate;

    private String againAuditRemarks;

    private Date statusDate;

    private String suspendedStatus;

    private String suspendedReason;

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

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode == null ? null : orderCode.trim();
    }

    public Integer getMchtId() {
        return mchtId;
    }

    public void setMchtId(Integer mchtId) {
        this.mchtId = mchtId;
    }

    public Integer getSubOrderId() {
        return subOrderId;
    }

    public void setSubOrderId(Integer subOrderId) {
        this.subOrderId = subOrderId;
    }

    public String getViolateType() {
        return violateType;
    }

    public void setViolateType(String violateType) {
        this.violateType = violateType == null ? null : violateType.trim();
    }

    public String getViolateAction() {
        return violateAction;
    }

    public void setViolateAction(String violateAction) {
        this.violateAction = violateAction == null ? null : violateAction.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getPunishMeans() {
        return punishMeans;
    }

    public void setPunishMeans(String punishMeans) {
        this.punishMeans = punishMeans == null ? null : punishMeans.trim();
    }

    public String getPlatformRemarks() {
        return platformRemarks;
    }

    public void setPlatformRemarks(String platformRemarks) {
        this.platformRemarks = platformRemarks == null ? null : platformRemarks.trim();
    }

    public String getOrderSource() {
        return orderSource;
    }

    public void setOrderSource(String orderSource) {
        this.orderSource = orderSource == null ? null : orderSource.trim();
    }

    public Date getViolateDate() {
        return violateDate;
    }

    public void setViolateDate(Date violateDate) {
        this.violateDate = violateDate;
    }

    public Date getComplainDate() {
        return complainDate;
    }

    public void setComplainDate(Date complainDate) {
        this.complainDate = complainDate;
    }

    public Date getComplainEndDate() {
        return complainEndDate;
    }

    public void setComplainEndDate(Date complainEndDate) {
        this.complainEndDate = complainEndDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus == null ? null : auditStatus.trim();
    }

    public String getAuditRemarks() {
        return auditRemarks;
    }

    public void setAuditRemarks(String auditRemarks) {
        this.auditRemarks = auditRemarks == null ? null : auditRemarks.trim();
    }

    public Date getAuditDate() {
        return auditDate;
    }

    public void setAuditDate(Date auditDate) {
        this.auditDate = auditDate;
    }

    public Integer getPlatformProcessor() {
        return platformProcessor;
    }

    public void setPlatformProcessor(Integer platformProcessor) {
        this.platformProcessor = platformProcessor;
    }

    public String getComplainInfoStatus() {
        return complainInfoStatus;
    }

    public void setComplainInfoStatus(String complainInfoStatus) {
        this.complainInfoStatus = complainInfoStatus == null ? null : complainInfoStatus.trim();
    }

    public String getEnclosure() {
        return enclosure;
    }

    public void setEnclosure(String enclosure) {
        this.enclosure = enclosure == null ? null : enclosure.trim();
    }

    public String getJifenStatus() {
        return jifenStatus;
    }

    public void setJifenStatus(String jifenStatus) {
        this.jifenStatus = jifenStatus == null ? null : jifenStatus.trim();
    }

    public Integer getJifenIntegral() {
        return jifenIntegral;
    }

    public void setJifenIntegral(Integer jifenIntegral) {
        this.jifenIntegral = jifenIntegral;
    }

    public String getEnableHours() {
        return enableHours;
    }

    public void setEnableHours(String enableHours) {
        this.enableHours = enableHours == null ? null : enableHours.trim();
    }

    public String getJifenIntegralDesc() {
        return jifenIntegralDesc;
    }

    public void setJifenIntegralDesc(String jifenIntegralDesc) {
        this.jifenIntegralDesc = jifenIntegralDesc == null ? null : jifenIntegralDesc.trim();
    }

    public String getAgainAuditStatus() {
        return againAuditStatus;
    }

    public void setAgainAuditStatus(String againAuditStatus) {
        this.againAuditStatus = againAuditStatus == null ? null : againAuditStatus.trim();
    }

    public Integer getAgainAuditBy() {
        return againAuditBy;
    }

    public void setAgainAuditBy(Integer againAuditBy) {
        this.againAuditBy = againAuditBy;
    }

    public Date getAgainAuditDate() {
        return againAuditDate;
    }

    public void setAgainAuditDate(Date againAuditDate) {
        this.againAuditDate = againAuditDate;
    }

    public String getAgainAuditRemarks() {
        return againAuditRemarks;
    }

    public void setAgainAuditRemarks(String againAuditRemarks) {
        this.againAuditRemarks = againAuditRemarks == null ? null : againAuditRemarks.trim();
    }

    public Date getStatusDate() {
        return statusDate;
    }

    public void setStatusDate(Date statusDate) {
        this.statusDate = statusDate;
    }

    public String getSuspendedStatus() {
        return suspendedStatus;
    }

    public void setSuspendedStatus(String suspendedStatus) {
        this.suspendedStatus = suspendedStatus == null ? null : suspendedStatus.trim();
    }

    public String getSuspendedReason() {
        return suspendedReason;
    }

    public void setSuspendedReason(String suspendedReason) {
        this.suspendedReason = suspendedReason == null ? null : suspendedReason.trim();
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