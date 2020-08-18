package com.jf.entity;

import java.util.Date;

public class Video {
    private Integer id;

    private Integer mchtId;

    private Integer productTypeId;

    private String title;

    private String description;

    private String videoUrl;

    private String videoXcxCode;

    private String videoCover;

    private String videoThumbnails;

    private Integer videoTime;

    private Integer videoSize;

    private String auditStatus;

    private Date auditTime;

    private String auditRemark;

    private String status;

    private Integer seasonWeight;

    private Integer playWeight;

    private Integer likeWeicht;

    private Integer commentWeight;

    private Integer collectWeight;

    private Integer manualWeicht;

    private Integer totalWeicht;

    private Date weightTime;

    private String isrecommend;

    private Integer seqNo;

    private String videoSource;

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

    public Integer getMchtId() {
        return mchtId;
    }

    public void setMchtId(Integer mchtId) {
        this.mchtId = mchtId;
    }

    public Integer getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(Integer productTypeId) {
        this.productTypeId = productTypeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl == null ? null : videoUrl.trim();
    }

    public String getVideoXcxCode() {
        return videoXcxCode;
    }

    public void setVideoXcxCode(String videoXcxCode) {
        this.videoXcxCode = videoXcxCode == null ? null : videoXcxCode.trim();
    }

    public String getVideoCover() {
        return videoCover;
    }

    public void setVideoCover(String videoCover) {
        this.videoCover = videoCover == null ? null : videoCover.trim();
    }

    public String getVideoThumbnails() {
        return videoThumbnails;
    }

    public void setVideoThumbnails(String videoThumbnails) {
        this.videoThumbnails = videoThumbnails == null ? null : videoThumbnails.trim();
    }

    public Integer getVideoTime() {
        return videoTime;
    }

    public void setVideoTime(Integer videoTime) {
        this.videoTime = videoTime;
    }

    public Integer getVideoSize() {
        return videoSize;
    }

    public void setVideoSize(Integer videoSize) {
        this.videoSize = videoSize;
    }

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus == null ? null : auditStatus.trim();
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    public String getAuditRemark() {
        return auditRemark;
    }

    public void setAuditRemark(String auditRemark) {
        this.auditRemark = auditRemark == null ? null : auditRemark.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Integer getSeasonWeight() {
        return seasonWeight;
    }

    public void setSeasonWeight(Integer seasonWeight) {
        this.seasonWeight = seasonWeight;
    }

    public Integer getPlayWeight() {
        return playWeight;
    }

    public void setPlayWeight(Integer playWeight) {
        this.playWeight = playWeight;
    }

    public Integer getLikeWeicht() {
        return likeWeicht;
    }

    public void setLikeWeicht(Integer likeWeicht) {
        this.likeWeicht = likeWeicht;
    }

    public Integer getCommentWeight() {
        return commentWeight;
    }

    public void setCommentWeight(Integer commentWeight) {
        this.commentWeight = commentWeight;
    }

    public Integer getCollectWeight() {
        return collectWeight;
    }

    public void setCollectWeight(Integer collectWeight) {
        this.collectWeight = collectWeight;
    }

    public Integer getManualWeicht() {
        return manualWeicht;
    }

    public void setManualWeicht(Integer manualWeicht) {
        this.manualWeicht = manualWeicht;
    }

    public Integer getTotalWeicht() {
        return totalWeicht;
    }

    public void setTotalWeicht(Integer totalWeicht) {
        this.totalWeicht = totalWeicht;
    }

    public Date getWeightTime() {
        return weightTime;
    }

    public void setWeightTime(Date weightTime) {
        this.weightTime = weightTime;
    }

    public String getIsrecommend() {
        return isrecommend;
    }

    public void setIsrecommend(String isrecommend) {
        this.isrecommend = isrecommend == null ? null : isrecommend.trim();
    }

    public Integer getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(Integer seqNo) {
        this.seqNo = seqNo;
    }

    public String getVideoSource() {
        return videoSource;
    }

    public void setVideoSource(String videoSource) {
        this.videoSource = videoSource == null ? null : videoSource.trim();
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