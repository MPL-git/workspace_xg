package com.jf.entity;

import java.util.Date;

public class MemberExtend {
    private Integer id;

    private Integer memberId;

    private String qqId;

    private String weiboId;

    private String signInRemind;

    private String newUser500CouponRemind;

    private Integer zsPlatformContactId;

    private Integer mchtSettledApplyId;

    private String sprChnl;

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

    public String getQqId() {
        return qqId;
    }

    public void setQqId(String qqId) {
        this.qqId = qqId == null ? null : qqId.trim();
    }

    public String getWeiboId() {
        return weiboId;
    }

    public void setWeiboId(String weiboId) {
        this.weiboId = weiboId == null ? null : weiboId.trim();
    }

    public String getSignInRemind() {
        return signInRemind;
    }

    public void setSignInRemind(String signInRemind) {
        this.signInRemind = signInRemind == null ? null : signInRemind.trim();
    }

    public String getNewUser500CouponRemind() {
        return newUser500CouponRemind;
    }

    public void setNewUser500CouponRemind(String newUser500CouponRemind) {
        this.newUser500CouponRemind = newUser500CouponRemind == null ? null : newUser500CouponRemind.trim();
    }

    public Integer getZsPlatformContactId() {
        return zsPlatformContactId;
    }

    public void setZsPlatformContactId(Integer zsPlatformContactId) {
        this.zsPlatformContactId = zsPlatformContactId;
    }

    public Integer getMchtSettledApplyId() {
        return mchtSettledApplyId;
    }

    public void setMchtSettledApplyId(Integer mchtSettledApplyId) {
        this.mchtSettledApplyId = mchtSettledApplyId;
    }

    public String getSprChnl() {
        return sprChnl;
    }

    public void setSprChnl(String sprChnl) {
        this.sprChnl = sprChnl == null ? null : sprChnl.trim();
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