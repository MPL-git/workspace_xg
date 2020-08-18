package com.jf.entity;

import java.util.Date;

public class MemberPvMiddleLog {
    private Integer id;

    private String batchDate;

    private String parseType;

    private Date parseStartDate;

    private Date parseEndDate;

    private String statisticsType;

    private Date statisticsStartDate;

    private Date statisticsEndDate;

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

    public String getBatchDate() {
        return batchDate;
    }

    public void setBatchDate(String batchDate) {
        this.batchDate = batchDate == null ? null : batchDate.trim();
    }

    public String getParseType() {
        return parseType;
    }

    public void setParseType(String parseType) {
        this.parseType = parseType == null ? null : parseType.trim();
    }

    public Date getParseStartDate() {
        return parseStartDate;
    }

    public void setParseStartDate(Date parseStartDate) {
        this.parseStartDate = parseStartDate;
    }

    public Date getParseEndDate() {
        return parseEndDate;
    }

    public void setParseEndDate(Date parseEndDate) {
        this.parseEndDate = parseEndDate;
    }

    public String getStatisticsType() {
        return statisticsType;
    }

    public void setStatisticsType(String statisticsType) {
        this.statisticsType = statisticsType == null ? null : statisticsType.trim();
    }

    public Date getStatisticsStartDate() {
        return statisticsStartDate;
    }

    public void setStatisticsStartDate(Date statisticsStartDate) {
        this.statisticsStartDate = statisticsStartDate;
    }

    public Date getStatisticsEndDate() {
        return statisticsEndDate;
    }

    public void setStatisticsEndDate(Date statisticsEndDate) {
        this.statisticsEndDate = statisticsEndDate;
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