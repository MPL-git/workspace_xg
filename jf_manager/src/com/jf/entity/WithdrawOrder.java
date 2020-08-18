package com.jf.entity;

import java.math.BigDecimal;
import java.util.Date;

public class WithdrawOrder {
    private Integer id;

    private String orderCode;

    private Integer memberId;

    private Integer accId;

    private BigDecimal amount;

    private String status;

    private String withdrawType;

    private Integer couponId;

    private Integer memberCouponId;

    private Integer withdrawCnfId;

    private String realName;

    private String alipayAccount;

    private String withdrawMethod;

    private Integer yyAuditBy;

    private String yyAuditStatus;

    private Date yyAuditDate;

    private String yyRejectReason;

    private String yyInnerRemarks;

    private Integer cwAuditBy;

    private String cwAuditStatus;

    private Date cwAuditDate;

    private String cwRejectReason;

    private String cwInnerRemarks;

    private BigDecimal novaBalance;

    private Integer bankBranchId;

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

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Integer getAccId() {
        return accId;
    }

    public void setAccId(Integer accId) {
        this.accId = accId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getWithdrawType() {
        return withdrawType;
    }

    public void setWithdrawType(String withdrawType) {
        this.withdrawType = withdrawType == null ? null : withdrawType.trim();
    }

    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    public Integer getMemberCouponId() {
        return memberCouponId;
    }

    public void setMemberCouponId(Integer memberCouponId) {
        this.memberCouponId = memberCouponId;
    }

    public Integer getWithdrawCnfId() {
        return withdrawCnfId;
    }

    public void setWithdrawCnfId(Integer withdrawCnfId) {
        this.withdrawCnfId = withdrawCnfId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    public String getAlipayAccount() {
        return alipayAccount;
    }

    public void setAlipayAccount(String alipayAccount) {
        this.alipayAccount = alipayAccount == null ? null : alipayAccount.trim();
    }

    public String getWithdrawMethod() {
        return withdrawMethod;
    }

    public void setWithdrawMethod(String withdrawMethod) {
        this.withdrawMethod = withdrawMethod == null ? null : withdrawMethod.trim();
    }

    public Integer getYyAuditBy() {
        return yyAuditBy;
    }

    public void setYyAuditBy(Integer yyAuditBy) {
        this.yyAuditBy = yyAuditBy;
    }

    public String getYyAuditStatus() {
        return yyAuditStatus;
    }

    public void setYyAuditStatus(String yyAuditStatus) {
        this.yyAuditStatus = yyAuditStatus == null ? null : yyAuditStatus.trim();
    }

    public Date getYyAuditDate() {
        return yyAuditDate;
    }

    public void setYyAuditDate(Date yyAuditDate) {
        this.yyAuditDate = yyAuditDate;
    }

    public String getYyRejectReason() {
        return yyRejectReason;
    }

    public void setYyRejectReason(String yyRejectReason) {
        this.yyRejectReason = yyRejectReason == null ? null : yyRejectReason.trim();
    }

    public String getYyInnerRemarks() {
        return yyInnerRemarks;
    }

    public void setYyInnerRemarks(String yyInnerRemarks) {
        this.yyInnerRemarks = yyInnerRemarks == null ? null : yyInnerRemarks.trim();
    }

    public Integer getCwAuditBy() {
        return cwAuditBy;
    }

    public void setCwAuditBy(Integer cwAuditBy) {
        this.cwAuditBy = cwAuditBy;
    }

    public String getCwAuditStatus() {
        return cwAuditStatus;
    }

    public void setCwAuditStatus(String cwAuditStatus) {
        this.cwAuditStatus = cwAuditStatus == null ? null : cwAuditStatus.trim();
    }

    public Date getCwAuditDate() {
        return cwAuditDate;
    }

    public void setCwAuditDate(Date cwAuditDate) {
        this.cwAuditDate = cwAuditDate;
    }

    public String getCwRejectReason() {
        return cwRejectReason;
    }

    public void setCwRejectReason(String cwRejectReason) {
        this.cwRejectReason = cwRejectReason == null ? null : cwRejectReason.trim();
    }

    public String getCwInnerRemarks() {
        return cwInnerRemarks;
    }

    public void setCwInnerRemarks(String cwInnerRemarks) {
        this.cwInnerRemarks = cwInnerRemarks == null ? null : cwInnerRemarks.trim();
    }

    public BigDecimal getNovaBalance() {
        return novaBalance;
    }

    public void setNovaBalance(BigDecimal novaBalance) {
        this.novaBalance = novaBalance;
    }

    public Integer getBankBranchId() {
        return bankBranchId;
    }

    public void setBankBranchId(Integer bankBranchId) {
        this.bankBranchId = bankBranchId;
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