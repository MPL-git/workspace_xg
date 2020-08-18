package com.jf.entity;

import java.util.Date;

public class SvipPracticeRecord {
    private Integer id;

    private Integer svipPracticeInfoId;

    private Integer memberId;

    private Date recTime;

    private Date practiceStartTime;

    private Date practiceEndTime;

    private Integer seqNo;

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

    public Integer getSvipPracticeInfoId() {
        return svipPracticeInfoId;
    }

    public void setSvipPracticeInfoId(Integer svipPracticeInfoId) {
        this.svipPracticeInfoId = svipPracticeInfoId;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Date getRecTime() {
        return recTime;
    }

    public void setRecTime(Date recTime) {
        this.recTime = recTime;
    }

    public Date getPracticeStartTime() {
        return practiceStartTime;
    }

    public void setPracticeStartTime(Date practiceStartTime) {
        this.practiceStartTime = practiceStartTime;
    }

    public Date getPracticeEndTime() {
        return practiceEndTime;
    }

    public void setPracticeEndTime(Date practiceEndTime) {
        this.practiceEndTime = practiceEndTime;
    }

    public Integer getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(Integer seqNo) {
        this.seqNo = seqNo;
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