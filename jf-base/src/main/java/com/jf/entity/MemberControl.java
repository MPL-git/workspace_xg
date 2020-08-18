package com.jf.entity;

import java.util.Date;

public class MemberControl {
    private Integer id;

    private Integer memberId;

    private Date lastSignInDate;

    private Date signBeHelpedDate;

    private Integer signBeHelpedCount;

    private Date lastHelpCutPriceDate;

    private Integer helpCutPriceCount;

    private Date lastCutPriceDate;

    private Integer cutPriceCount;

    private Date lastInviteDate;

    private Integer inviteCount;

    private Date lastRecSvipIntegralDate;

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

    public Date getLastSignInDate() {
        return lastSignInDate;
    }

    public void setLastSignInDate(Date lastSignInDate) {
        this.lastSignInDate = lastSignInDate;
    }

    public Date getSignBeHelpedDate() {
        return signBeHelpedDate;
    }

    public void setSignBeHelpedDate(Date signBeHelpedDate) {
        this.signBeHelpedDate = signBeHelpedDate;
    }

    public Integer getSignBeHelpedCount() {
        return signBeHelpedCount;
    }

    public void setSignBeHelpedCount(Integer signBeHelpedCount) {
        this.signBeHelpedCount = signBeHelpedCount;
    }

    public Date getLastHelpCutPriceDate() {
        return lastHelpCutPriceDate;
    }

    public void setLastHelpCutPriceDate(Date lastHelpCutPriceDate) {
        this.lastHelpCutPriceDate = lastHelpCutPriceDate;
    }

    public Integer getHelpCutPriceCount() {
        return helpCutPriceCount;
    }

    public void setHelpCutPriceCount(Integer helpCutPriceCount) {
        this.helpCutPriceCount = helpCutPriceCount;
    }

    public Date getLastCutPriceDate() {
        return lastCutPriceDate;
    }

    public void setLastCutPriceDate(Date lastCutPriceDate) {
        this.lastCutPriceDate = lastCutPriceDate;
    }

    public Integer getCutPriceCount() {
        return cutPriceCount;
    }

    public void setCutPriceCount(Integer cutPriceCount) {
        this.cutPriceCount = cutPriceCount;
    }

    public Date getLastInviteDate() {
        return lastInviteDate;
    }

    public void setLastInviteDate(Date lastInviteDate) {
        this.lastInviteDate = lastInviteDate;
    }

    public Integer getInviteCount() {
        return inviteCount;
    }

    public void setInviteCount(Integer inviteCount) {
        this.inviteCount = inviteCount;
    }

    public Date getLastRecSvipIntegralDate() {
        return lastRecSvipIntegralDate;
    }

    public void setLastRecSvipIntegralDate(Date lastRecSvipIntegralDate) {
        this.lastRecSvipIntegralDate = lastRecSvipIntegralDate;
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