package com.jf.entity;

import java.math.BigDecimal;
import java.util.Date;

public class MemberSignInDtl {
    private Integer id;

    private Integer memberId;

    private Integer memberSignIn;

    private String rewardType;

    private BigDecimal amount;

    private BigDecimal extraAmount;

    private Integer signInCnfId;

    private Integer signInCnfDtl;

    private Integer currentSourceMemberId;

    private String isHelp;

    private BigDecimal helpAmount;

    private String isHelpAmountGet;

    private String signInClient;

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

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Integer getMemberSignIn() {
        return memberSignIn;
    }

    public void setMemberSignIn(Integer memberSignIn) {
        this.memberSignIn = memberSignIn;
    }

    public String getRewardType() {
        return rewardType;
    }

    public void setRewardType(String rewardType) {
        this.rewardType = rewardType == null ? null : rewardType.trim();
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getExtraAmount() {
        return extraAmount;
    }

    public void setExtraAmount(BigDecimal extraAmount) {
        this.extraAmount = extraAmount;
    }

    public Integer getSignInCnfId() {
        return signInCnfId;
    }

    public void setSignInCnfId(Integer signInCnfId) {
        this.signInCnfId = signInCnfId;
    }

    public Integer getSignInCnfDtl() {
        return signInCnfDtl;
    }

    public void setSignInCnfDtl(Integer signInCnfDtl) {
        this.signInCnfDtl = signInCnfDtl;
    }

    public Integer getCurrentSourceMemberId() {
        return currentSourceMemberId;
    }

    public void setCurrentSourceMemberId(Integer currentSourceMemberId) {
        this.currentSourceMemberId = currentSourceMemberId;
    }

    public String getIsHelp() {
        return isHelp;
    }

    public void setIsHelp(String isHelp) {
        this.isHelp = isHelp == null ? null : isHelp.trim();
    }

    public BigDecimal getHelpAmount() {
        return helpAmount;
    }

    public void setHelpAmount(BigDecimal helpAmount) {
        this.helpAmount = helpAmount;
    }

    public String getIsHelpAmountGet() {
        return isHelpAmountGet;
    }

    public void setIsHelpAmountGet(String isHelpAmountGet) {
        this.isHelpAmountGet = isHelpAmountGet == null ? null : isHelpAmountGet.trim();
    }

    public String getSignInClient() {
        return signInClient;
    }

    public void setSignInClient(String signInClient) {
        this.signInClient = signInClient == null ? null : signInClient.trim();
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