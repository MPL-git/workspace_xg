package com.jf.entity;

import java.util.Date;

public class ApprovalApplicationLog {
    private Integer id;

    private Integer approvalApplicationId;

    private String operation;

    private String approvalNode;

    private String approvalRemarks;

    private String status;

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

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation == null ? null : operation.trim();
    }

    public String getApprovalNode() {
        return approvalNode;
    }

    public void setApprovalNode(String approvalNode) {
        this.approvalNode = approvalNode == null ? null : approvalNode.trim();
    }

    public String getApprovalRemarks() {
        return approvalRemarks;
    }

    public void setApprovalRemarks(String approvalRemarks) {
        this.approvalRemarks = approvalRemarks == null ? null : approvalRemarks.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
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