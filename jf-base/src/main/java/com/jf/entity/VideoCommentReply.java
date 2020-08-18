package com.jf.entity;

import java.util.Date;

public class VideoCommentReply {
    private Integer id;

    private Integer videoId;

    private Integer videoCommentId;

    private String replier;

    private Integer replierId;

    private String replierAvatar;

    private String replierNick;

    private String content;

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

    public Integer getVideoId() {
        return videoId;
    }

    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }

    public Integer getVideoCommentId() {
        return videoCommentId;
    }

    public void setVideoCommentId(Integer videoCommentId) {
        this.videoCommentId = videoCommentId;
    }

    public String getReplier() {
        return replier;
    }

    public void setReplier(String replier) {
        this.replier = replier == null ? null : replier.trim();
    }

    public Integer getReplierId() {
        return replierId;
    }

    public void setReplierId(Integer replierId) {
        this.replierId = replierId;
    }

    public String getReplierAvatar() {
        return replierAvatar;
    }

    public void setReplierAvatar(String replierAvatar) {
        this.replierAvatar = replierAvatar == null ? null : replierAvatar.trim();
    }

    public String getReplierNick() {
        return replierNick;
    }

    public void setReplierNick(String replierNick) {
        this.replierNick = replierNick == null ? null : replierNick.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
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