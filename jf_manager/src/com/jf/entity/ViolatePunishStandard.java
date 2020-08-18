package com.jf.entity;

import java.math.BigDecimal;
import java.util.Date;

public class ViolatePunishStandard {
    private Integer id;

    private String violateType;

    private String violateAction;

    private String content;

    private String punishStandard;

    private String punishOther;

    private String complainFlag;

    private String enableHours;

    private String jifenIntegralDesc;

    private Integer jifenIntegral;

    private Integer seqNo;

    private String paymentBreachModel;

    private BigDecimal breachDeductionQuota;

    private BigDecimal minDeductionQuota;

    private String integralCompensateModel;

    private BigDecimal integralCompensateRate;

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

    public String getPunishStandard() {
        return punishStandard;
    }

    public void setPunishStandard(String punishStandard) {
        this.punishStandard = punishStandard == null ? null : punishStandard.trim();
    }

    public String getPunishOther() {
        return punishOther;
    }

    public void setPunishOther(String punishOther) {
        this.punishOther = punishOther == null ? null : punishOther.trim();
    }

    public String getComplainFlag() {
        return complainFlag;
    }

    public void setComplainFlag(String complainFlag) {
        this.complainFlag = complainFlag == null ? null : complainFlag.trim();
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

    public Integer getJifenIntegral() {
        return jifenIntegral;
    }

    public void setJifenIntegral(Integer jifenIntegral) {
        this.jifenIntegral = jifenIntegral;
    }

    public Integer getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(Integer seqNo) {
        this.seqNo = seqNo;
    }

    public String getPaymentBreachModel() {
        return paymentBreachModel;
    }

    public void setPaymentBreachModel(String paymentBreachModel) {
        this.paymentBreachModel = paymentBreachModel == null ? null : paymentBreachModel.trim();
    }

    public BigDecimal getBreachDeductionQuota() {
        return breachDeductionQuota;
    }

    public void setBreachDeductionQuota(BigDecimal breachDeductionQuota) {
        this.breachDeductionQuota = breachDeductionQuota;
    }

    public BigDecimal getMinDeductionQuota() {
        return minDeductionQuota;
    }

    public void setMinDeductionQuota(BigDecimal minDeductionQuota) {
        this.minDeductionQuota = minDeductionQuota;
    }

    public String getIntegralCompensateModel() {
        return integralCompensateModel;
    }

    public void setIntegralCompensateModel(String integralCompensateModel) {
        this.integralCompensateModel = integralCompensateModel == null ? null : integralCompensateModel.trim();
    }

    public BigDecimal getIntegralCompensateRate() {
        return integralCompensateRate;
    }

    public void setIntegralCompensateRate(BigDecimal integralCompensateRate) {
        this.integralCompensateRate = integralCompensateRate;
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