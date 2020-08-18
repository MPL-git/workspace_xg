package com.jf.entity;

import java.util.Date;

public class SeckillTime {
    private Integer id;

    private String seckillType;

    private String startHours;

    private String startMin;

    private String continueHours;

    private String continueMin;

    private String status;

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

    public String getSeckillType() {
        return seckillType;
    }

    public void setSeckillType(String seckillType) {
        this.seckillType = seckillType == null ? null : seckillType.trim();
    }

    public String getStartHours() {
        return startHours;
    }

    public void setStartHours(String startHours) {
        this.startHours = startHours == null ? null : startHours.trim();
    }

    public String getStartMin() {
        return startMin;
    }

    public void setStartMin(String startMin) {
        this.startMin = startMin == null ? null : startMin.trim();
    }

    public String getContinueHours() {
        return continueHours;
    }

    public void setContinueHours(String continueHours) {
        this.continueHours = continueHours == null ? null : continueHours.trim();
    }

    public String getContinueMin() {
        return continueMin;
    }

    public void setContinueMin(String continueMin) {
        this.continueMin = continueMin == null ? null : continueMin.trim();
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
}