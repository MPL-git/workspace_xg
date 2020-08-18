package com.jf.entity;

import java.util.Date;

public class ImpeachMember {
    private Integer id;

    private String memberIds;

    private Integer commentId;

    private Integer mchtId;

    private String code;

    private String status;

    private String type;

    private String scene;

    private String subOrderIds;

    private String description;

    private String name;

    private String mobile;

    private String needLimit;

    private String limitMemberAction;

    private String rejectReason;

    private String caseCloseDesc;

    private Date receiverDate;

    private String commissionerInnerRemarks;

    private Integer commissionerAuditBy;

    private Date commissionerAuditDate;

    private String fwInnerRemarks;

    private String fwRejectReason;

    private Integer fwAuditBy;

    private Date fwAuditDate;

    private String endInnerRemarks;

    private String endRejectReason;

    private Integer endAuditBy;

    private Date endAuditDate;

    private Date lastEditDate;

    private String source;

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

    public String getMemberIds() {
        return memberIds;
    }

    public void setMemberIds(String memberIds) {
        this.memberIds = memberIds == null ? null : memberIds.trim();
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Integer getMchtId() {
        return mchtId;
    }

    public void setMchtId(Integer mchtId) {
        this.mchtId = mchtId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getScene() {
        return scene;
    }

    public void setScene(String scene) {
        this.scene = scene == null ? null : scene.trim();
    }

    public String getSubOrderIds() {
        return subOrderIds;
    }

    public void setSubOrderIds(String subOrderIds) {
        this.subOrderIds = subOrderIds == null ? null : subOrderIds.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getNeedLimit() {
        return needLimit;
    }

    public void setNeedLimit(String needLimit) {
        this.needLimit = needLimit == null ? null : needLimit.trim();
    }

    public String getLimitMemberAction() {
        return limitMemberAction;
    }

    public void setLimitMemberAction(String limitMemberAction) {
        this.limitMemberAction = limitMemberAction == null ? null : limitMemberAction.trim();
    }

    public String getRejectReason() {
        return rejectReason;
    }

    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason == null ? null : rejectReason.trim();
    }

    public String getCaseCloseDesc() {
        return caseCloseDesc;
    }

    public void setCaseCloseDesc(String caseCloseDesc) {
        this.caseCloseDesc = caseCloseDesc == null ? null : caseCloseDesc.trim();
    }

    public Date getReceiverDate() {
        return receiverDate;
    }

    public void setReceiverDate(Date receiverDate) {
        this.receiverDate = receiverDate;
    }

    public String getCommissionerInnerRemarks() {
        return commissionerInnerRemarks;
    }

    public void setCommissionerInnerRemarks(String commissionerInnerRemarks) {
        this.commissionerInnerRemarks = commissionerInnerRemarks == null ? null : commissionerInnerRemarks.trim();
    }

    public Integer getCommissionerAuditBy() {
        return commissionerAuditBy;
    }

    public void setCommissionerAuditBy(Integer commissionerAuditBy) {
        this.commissionerAuditBy = commissionerAuditBy;
    }

    public Date getCommissionerAuditDate() {
        return commissionerAuditDate;
    }

    public void setCommissionerAuditDate(Date commissionerAuditDate) {
        this.commissionerAuditDate = commissionerAuditDate;
    }

    public String getFwInnerRemarks() {
        return fwInnerRemarks;
    }

    public void setFwInnerRemarks(String fwInnerRemarks) {
        this.fwInnerRemarks = fwInnerRemarks == null ? null : fwInnerRemarks.trim();
    }

    public String getFwRejectReason() {
        return fwRejectReason;
    }

    public void setFwRejectReason(String fwRejectReason) {
        this.fwRejectReason = fwRejectReason == null ? null : fwRejectReason.trim();
    }

    public Integer getFwAuditBy() {
        return fwAuditBy;
    }

    public void setFwAuditBy(Integer fwAuditBy) {
        this.fwAuditBy = fwAuditBy;
    }

    public Date getFwAuditDate() {
        return fwAuditDate;
    }

    public void setFwAuditDate(Date fwAuditDate) {
        this.fwAuditDate = fwAuditDate;
    }

    public String getEndInnerRemarks() {
        return endInnerRemarks;
    }

    public void setEndInnerRemarks(String endInnerRemarks) {
        this.endInnerRemarks = endInnerRemarks == null ? null : endInnerRemarks.trim();
    }

    public String getEndRejectReason() {
        return endRejectReason;
    }

    public void setEndRejectReason(String endRejectReason) {
        this.endRejectReason = endRejectReason == null ? null : endRejectReason.trim();
    }

    public Integer getEndAuditBy() {
        return endAuditBy;
    }

    public void setEndAuditBy(Integer endAuditBy) {
        this.endAuditBy = endAuditBy;
    }

    public Date getEndAuditDate() {
        return endAuditDate;
    }

    public void setEndAuditDate(Date endAuditDate) {
        this.endAuditDate = endAuditDate;
    }

    public Date getLastEditDate() {
        return lastEditDate;
    }

    public void setLastEditDate(Date lastEditDate) {
        this.lastEditDate = lastEditDate;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
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