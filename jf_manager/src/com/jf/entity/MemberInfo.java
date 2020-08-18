package com.jf.entity;

import java.util.Date;

public class MemberInfo {
    private Integer id;

    private String type;

    private String loginCode;

    private String loginPass;

    private Integer supId;

    private String nick;

    private Integer groupId;

    private String status;

    private String mobile;

    private String mVerfiyFlag;

    private String email;

    private String eVerfiyFlag;

    private String weixinId;

    private String weixinUnionid;

    private String sexType;

    private Date birthday;

    private Integer province;

    private Integer city;

    private Integer county;

    private String pic;

    private String weixinHead;

    private String sprChnl;

    private String regClient;

    private String reqMobileBrand;

    private String reqMobileModel;

    private String reqImei;

    private String regIp;

    private String regArea;

    private String isInfPerfect;

    private String isAcceptPush;

    private Integer weixinXcxSprDtlId;

    private Integer newWeixinXcxSprDtlId;

    private String weixinGdtVid;

    private String weixinAdinfo;

    private String cancelReason;

    private String limitFunction;

    private String blackReason;

    private String blackInnerRemarks;

    private String isSvip;

    private Date svipExpireDate;

    private String invitationCode;

    private Integer invitationMemberId;

    private Date invitationCodeBindTime;

    private Date novaProjectBeginDate;

    private Date novaProjectEndDate;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getLoginCode() {
        return loginCode;
    }

    public void setLoginCode(String loginCode) {
        this.loginCode = loginCode == null ? null : loginCode.trim();
    }

    public String getLoginPass() {
        return loginPass;
    }

    public void setLoginPass(String loginPass) {
        this.loginPass = loginPass == null ? null : loginPass.trim();
    }

    public Integer getSupId() {
        return supId;
    }

    public void setSupId(Integer supId) {
        this.supId = supId;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick == null ? null : nick.trim();
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getmVerfiyFlag() {
        return mVerfiyFlag;
    }

    public void setmVerfiyFlag(String mVerfiyFlag) {
        this.mVerfiyFlag = mVerfiyFlag == null ? null : mVerfiyFlag.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String geteVerfiyFlag() {
        return eVerfiyFlag;
    }

    public void seteVerfiyFlag(String eVerfiyFlag) {
        this.eVerfiyFlag = eVerfiyFlag == null ? null : eVerfiyFlag.trim();
    }

    public String getWeixinId() {
        return weixinId;
    }

    public void setWeixinId(String weixinId) {
        this.weixinId = weixinId == null ? null : weixinId.trim();
    }

    public String getWeixinUnionid() {
        return weixinUnionid;
    }

    public void setWeixinUnionid(String weixinUnionid) {
        this.weixinUnionid = weixinUnionid == null ? null : weixinUnionid.trim();
    }

    public String getSexType() {
        return sexType;
    }

    public void setSexType(String sexType) {
        this.sexType = sexType == null ? null : sexType.trim();
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getProvince() {
        return province;
    }

    public void setProvince(Integer province) {
        this.province = province;
    }

    public Integer getCity() {
        return city;
    }

    public void setCity(Integer city) {
        this.city = city;
    }

    public Integer getCounty() {
        return county;
    }

    public void setCounty(Integer county) {
        this.county = county;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic == null ? null : pic.trim();
    }

    public String getWeixinHead() {
        return weixinHead;
    }

    public void setWeixinHead(String weixinHead) {
        this.weixinHead = weixinHead == null ? null : weixinHead.trim();
    }

    public String getSprChnl() {
        return sprChnl;
    }

    public void setSprChnl(String sprChnl) {
        this.sprChnl = sprChnl == null ? null : sprChnl.trim();
    }

    public String getRegClient() {
        return regClient;
    }

    public void setRegClient(String regClient) {
        this.regClient = regClient == null ? null : regClient.trim();
    }

    public String getReqMobileBrand() {
        return reqMobileBrand;
    }

    public void setReqMobileBrand(String reqMobileBrand) {
        this.reqMobileBrand = reqMobileBrand == null ? null : reqMobileBrand.trim();
    }

    public String getReqMobileModel() {
        return reqMobileModel;
    }

    public void setReqMobileModel(String reqMobileModel) {
        this.reqMobileModel = reqMobileModel == null ? null : reqMobileModel.trim();
    }

    public String getReqImei() {
        return reqImei;
    }

    public void setReqImei(String reqImei) {
        this.reqImei = reqImei == null ? null : reqImei.trim();
    }

    public String getRegIp() {
        return regIp;
    }

    public void setRegIp(String regIp) {
        this.regIp = regIp == null ? null : regIp.trim();
    }

    public String getRegArea() {
        return regArea;
    }

    public void setRegArea(String regArea) {
        this.regArea = regArea == null ? null : regArea.trim();
    }

    public String getIsInfPerfect() {
        return isInfPerfect;
    }

    public void setIsInfPerfect(String isInfPerfect) {
        this.isInfPerfect = isInfPerfect == null ? null : isInfPerfect.trim();
    }

    public String getIsAcceptPush() {
        return isAcceptPush;
    }

    public void setIsAcceptPush(String isAcceptPush) {
        this.isAcceptPush = isAcceptPush == null ? null : isAcceptPush.trim();
    }

    public Integer getWeixinXcxSprDtlId() {
        return weixinXcxSprDtlId;
    }

    public void setWeixinXcxSprDtlId(Integer weixinXcxSprDtlId) {
        this.weixinXcxSprDtlId = weixinXcxSprDtlId;
    }

    public Integer getNewWeixinXcxSprDtlId() {
        return newWeixinXcxSprDtlId;
    }

    public void setNewWeixinXcxSprDtlId(Integer newWeixinXcxSprDtlId) {
        this.newWeixinXcxSprDtlId = newWeixinXcxSprDtlId;
    }

    public String getWeixinGdtVid() {
        return weixinGdtVid;
    }

    public void setWeixinGdtVid(String weixinGdtVid) {
        this.weixinGdtVid = weixinGdtVid == null ? null : weixinGdtVid.trim();
    }

    public String getWeixinAdinfo() {
        return weixinAdinfo;
    }

    public void setWeixinAdinfo(String weixinAdinfo) {
        this.weixinAdinfo = weixinAdinfo == null ? null : weixinAdinfo.trim();
    }

    public String getCancelReason() {
        return cancelReason;
    }

    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason == null ? null : cancelReason.trim();
    }

    public String getLimitFunction() {
        return limitFunction;
    }

    public void setLimitFunction(String limitFunction) {
        this.limitFunction = limitFunction == null ? null : limitFunction.trim();
    }

    public String getBlackReason() {
        return blackReason;
    }

    public void setBlackReason(String blackReason) {
        this.blackReason = blackReason == null ? null : blackReason.trim();
    }

    public String getBlackInnerRemarks() {
        return blackInnerRemarks;
    }

    public void setBlackInnerRemarks(String blackInnerRemarks) {
        this.blackInnerRemarks = blackInnerRemarks == null ? null : blackInnerRemarks.trim();
    }

    public String getIsSvip() {
        return isSvip;
    }

    public void setIsSvip(String isSvip) {
        this.isSvip = isSvip == null ? null : isSvip.trim();
    }

    public Date getSvipExpireDate() {
        return svipExpireDate;
    }

    public void setSvipExpireDate(Date svipExpireDate) {
        this.svipExpireDate = svipExpireDate;
    }

    public String getInvitationCode() {
        return invitationCode;
    }

    public void setInvitationCode(String invitationCode) {
        this.invitationCode = invitationCode == null ? null : invitationCode.trim();
    }

    public Integer getInvitationMemberId() {
        return invitationMemberId;
    }

    public void setInvitationMemberId(Integer invitationMemberId) {
        this.invitationMemberId = invitationMemberId;
    }

    public Date getInvitationCodeBindTime() {
        return invitationCodeBindTime;
    }

    public void setInvitationCodeBindTime(Date invitationCodeBindTime) {
        this.invitationCodeBindTime = invitationCodeBindTime;
    }

    public Date getNovaProjectBeginDate() {
        return novaProjectBeginDate;
    }

    public void setNovaProjectBeginDate(Date novaProjectBeginDate) {
        this.novaProjectBeginDate = novaProjectBeginDate;
    }

    public Date getNovaProjectEndDate() {
        return novaProjectEndDate;
    }

    public void setNovaProjectEndDate(Date novaProjectEndDate) {
        this.novaProjectEndDate = novaProjectEndDate;
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