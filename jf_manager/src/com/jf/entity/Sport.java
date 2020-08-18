package com.jf.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Sport {
    private Integer id;

    private Integer sportType;

    private String sportName;

    private Integer homeTeam;

    private Integer awayTeam;

    private BigDecimal homeRate;

    private BigDecimal awaysRate;

    private BigDecimal drawRate;

    private Date beginTime;

    private Integer homeScore;

    private Integer awaysScore;

    private String result;

    private String auditStatus;

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

    public Integer getSportType() {
        return sportType;
    }

    public void setSportType(Integer sportType) {
        this.sportType = sportType;
    }

    public String getSportName() {
        return sportName;
    }

    public void setSportName(String sportName) {
        this.sportName = sportName == null ? null : sportName.trim();
    }

    public Integer getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Integer homeTeam) {
        this.homeTeam = homeTeam;
    }

    public Integer getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(Integer awayTeam) {
        this.awayTeam = awayTeam;
    }

    public BigDecimal getHomeRate() {
        return homeRate;
    }

    public void setHomeRate(BigDecimal homeRate) {
        this.homeRate = homeRate;
    }

    public BigDecimal getAwaysRate() {
        return awaysRate;
    }

    public void setAwaysRate(BigDecimal awaysRate) {
        this.awaysRate = awaysRate;
    }

    public BigDecimal getDrawRate() {
        return drawRate;
    }

    public void setDrawRate(BigDecimal drawRate) {
        this.drawRate = drawRate;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Integer getHomeScore() {
        return homeScore;
    }

    public void setHomeScore(Integer homeScore) {
        this.homeScore = homeScore;
    }

    public Integer getAwaysScore() {
        return awaysScore;
    }

    public void setAwaysScore(Integer awaysScore) {
        this.awaysScore = awaysScore;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result == null ? null : result.trim();
    }

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus == null ? null : auditStatus.trim();
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