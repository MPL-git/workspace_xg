package com.jf.entity;

import java.util.Date;

public class MemberPvDtl {
    private Long id;

    private String clientSource;

    private Integer memberId;

    private String deviceNumber;

    private String memberPvId;

    private String dtlItemType;

    private Integer dtlItemId;

    private String position;

    private Integer clickCount;

    private Integer createBy;

    private Date createDate;

    private Integer updateBy;

    private Date updateDate;

    private String remarks;

    private String delFlag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClientSource() {
        return clientSource;
    }

    public void setClientSource(String clientSource) {
        this.clientSource = clientSource == null ? null : clientSource.trim();
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getDeviceNumber() {
        return deviceNumber;
    }

    public void setDeviceNumber(String deviceNumber) {
        this.deviceNumber = deviceNumber == null ? null : deviceNumber.trim();
    }

    public String getMemberPvId() {
        return memberPvId;
    }

    public void setMemberPvId(String memberPvId) {
        this.memberPvId = memberPvId == null ? null : memberPvId.trim();
    }

    public String getDtlItemType() {
        return dtlItemType;
    }

    public void setDtlItemType(String dtlItemType) {
        this.dtlItemType = dtlItemType == null ? null : dtlItemType.trim();
    }

    public Integer getDtlItemId() {
        return dtlItemId;
    }

    public void setDtlItemId(Integer dtlItemId) {
        this.dtlItemId = dtlItemId;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position == null ? null : position.trim();
    }

    public Integer getClickCount() {
        return clickCount;
    }

    public void setClickCount(Integer clickCount) {
        this.clickCount = clickCount;
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