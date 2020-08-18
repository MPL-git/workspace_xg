package com.jf.entity;

import java.util.Date;

public class ApprovalApplicationNode {
    private Integer id;

    private Integer approvalApplicationId;

    private String name;

    private String type;

    private String approverType;

    private String approvers;

    private Integer seqNo;

    private String needApproval;

    private String approvalStatus;

    private String approvalRemarks;

    private Integer steps;

    private Integer createBy;

    private Date createDate;

    private Integer updateBy;

    private Date updateDate;

    private String remarks;

    private String delFlag;

    private String approvalPic;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getApprovalApplicationId() {
        return approvalApplicationId;
    }

    public void setApprovalApplicationId(Integer approvalApplicationId) {
        this.approvalApplicationId = approvalApplicationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getApproverType() {
        return approverType;
    }

    public void setApproverType(String approverType) {
        this.approverType = approverType == null ? null : approverType.trim();
    }

    public String getApprovers() {
        return approvers;
    }

    public void setApprovers(String approvers) {
        this.approvers = approvers == null ? null : approvers.trim();
    }

    public Integer getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(Integer seqNo) {
        this.seqNo = seqNo;
    }

    public String getNeedApproval() {
        return needApproval;
    }

    public void setNeedApproval(String needApproval) {
        this.needApproval = needApproval == null ? null : needApproval.trim();
    }

    public String getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(String approvalStatus) {
        this.approvalStatus = approvalStatus == null ? null : approvalStatus.trim();
    }

    public String getApprovalRemarks() {
        return approvalRemarks;
    }

    public void setApprovalRemarks(String approvalRemarks) {
        this.approvalRemarks = approvalRemarks == null ? null : approvalRemarks.trim();
    }

    public Integer getSteps() {
        return steps;
    }

    public void setSteps(Integer steps) {
        this.steps = steps;
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

    public String getApprovalPic() {
        return approvalPic;
    }

    public void setApprovalPic(String approvalPic) {
        this.approvalPic = approvalPic == null ? null : approvalPic.trim();
    }
}