package com.jf.entity;

import java.util.Date;

public class Comment {
    private Integer id;

    private Integer mchtId;

    private Integer subOrderId;

    private Integer orderDtlId;

    private Integer productId;

    private Integer memberId;

    private Integer productItemId;

    private String productPropDesc;

    private String isAppendComment;

    private Integer productScore;

    private String content;

    private String hideContent;

    private String mchtReply;

    private Date mchtReplyDate;

    private String isDraw;

    private Integer commentWeight;

    private String commentSource;

    private String isShow;

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

    public Integer getSubOrderId() {
        return subOrderId;
    }

    public void setSubOrderId(Integer subOrderId) {
        this.subOrderId = subOrderId;
    }

    public Integer getOrderDtlId() {
        return orderDtlId;
    }

    public void setOrderDtlId(Integer orderDtlId) {
        this.orderDtlId = orderDtlId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Integer getProductItemId() {
        return productItemId;
    }

    public void setProductItemId(Integer productItemId) {
        this.productItemId = productItemId;
    }

    public String getProductPropDesc() {
        return productPropDesc;
    }

    public void setProductPropDesc(String productPropDesc) {
        this.productPropDesc = productPropDesc == null ? null : productPropDesc.trim();
    }

    public String getIsAppendComment() {
        return isAppendComment;
    }

    public void setIsAppendComment(String isAppendComment) {
        this.isAppendComment = isAppendComment == null ? null : isAppendComment.trim();
    }

    public Integer getProductScore() {
        return productScore;
    }

    public void setProductScore(Integer productScore) {
        this.productScore = productScore;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getHideContent() {
        return hideContent;
    }

    public void setHideContent(String hideContent) {
        this.hideContent = hideContent == null ? null : hideContent.trim();
    }

    public String getMchtReply() {
        return mchtReply;
    }

    public void setMchtReply(String mchtReply) {
        this.mchtReply = mchtReply == null ? null : mchtReply.trim();
    }

    public Date getMchtReplyDate() {
        return mchtReplyDate;
    }

    public void setMchtReplyDate(Date mchtReplyDate) {
        this.mchtReplyDate = mchtReplyDate;
    }

    public String getIsDraw() {
        return isDraw;
    }

    public void setIsDraw(String isDraw) {
        this.isDraw = isDraw == null ? null : isDraw.trim();
    }

    public Integer getCommentWeight() {
        return commentWeight;
    }

    public void setCommentWeight(Integer commentWeight) {
        this.commentWeight = commentWeight;
    }

    public String getCommentSource() {
        return commentSource;
    }

    public void setCommentSource(String commentSource) {
        this.commentSource = commentSource == null ? null : commentSource.trim();
    }

    public String getIsShow() {
        return isShow;
    }

    public void setIsShow(String isShow) {
        this.isShow = isShow == null ? null : isShow.trim();
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