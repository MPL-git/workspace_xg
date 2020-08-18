package com.jf.entity;

import java.util.Date;
import java.util.List;


public class CommentCustom extends Comment{
	private String subOrderCode;
	private String receiverName;
	private String skuPic;
	private String artNo;
	private String brandName;
	private String productName;
	private String productPropDesc;
	private String sku;
	private Date orderCreateDate;
	private List<CommentPic> commentPics;
	private long betweenDays;
	private String appendContent;
	private List<CommentPic> appendCommentPics;
	private Integer productScoreStarWidth;
	private String isAllowModifyComment;
	private Integer mchtScore;
	private Integer wuliuScore;
	private Integer appendCommentId;
	private Date appendCreateDate;
	private long mchtReplyBetweenDays;
	private String impeachStatus;

	public String getImpeachStatus() {
		return impeachStatus;
	}

	public void setImpeachStatus(String impeachStatus) {
		this.impeachStatus = impeachStatus;
	}

	public String getSubOrderCode() {
		return subOrderCode;
	}
	public void setSubOrderCode(String subOrderCode) {
		this.subOrderCode = subOrderCode;
	}
	public String getReceiverName() {
		return receiverName;
	}
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	public String getSkuPic() {
		return skuPic;
	}
	public void setSkuPic(String skuPic) {
		this.skuPic = skuPic;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductPropDesc() {
		return productPropDesc;
	}
	public void setProductPropDesc(String productPropDesc) {
		this.productPropDesc = productPropDesc;
	}
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public List<CommentPic> getCommentPics() {
		return commentPics;
	}
	public void setCommentPics(List<CommentPic> commentPics) {
		this.commentPics = commentPics;
	}
	public Date getOrderCreateDate() {
		return orderCreateDate;
	}
	public void setOrderCreateDate(Date orderCreateDate) {
		this.orderCreateDate = orderCreateDate;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getArtNo() {
		return artNo;
	}
	public void setArtNo(String artNo) {
		this.artNo = artNo;
	}
	public long getBetweenDays() {
		return betweenDays;
	}
	public void setBetweenDays(long betweenDays) {
		this.betweenDays = betweenDays;
	}
	public String getAppendContent() {
		return appendContent;
	}
	public void setAppendContent(String appendContent) {
		this.appendContent = appendContent;
	}
	public List<CommentPic> getAppendCommentPics() {
		return appendCommentPics;
	}
	public void setAppendCommentPics(List<CommentPic> appendCommentPics) {
		this.appendCommentPics = appendCommentPics;
	}
	public Integer getProductScoreStarWidth() {
		return productScoreStarWidth;
	}
	public void setProductScoreStarWidth(Integer productScoreStarWidth) {
		this.productScoreStarWidth = productScoreStarWidth;
	}
	public String getIsAllowModifyComment() {
		return isAllowModifyComment;
	}
	public void setIsAllowModifyComment(String isAllowModifyComment) {
		this.isAllowModifyComment = isAllowModifyComment;
	}
	public Integer getMchtScore() {
		return mchtScore;
	}
	public void setMchtScore(Integer mchtScore) {
		this.mchtScore = mchtScore;
	}
	public Integer getWuliuScore() {
		return wuliuScore;
	}
	public void setWuliuScore(Integer wuliuScore) {
		this.wuliuScore = wuliuScore;
	}
	public Integer getAppendCommentId() {
		return appendCommentId;
	}
	public void setAppendCommentId(Integer appendCommentId) {
		this.appendCommentId = appendCommentId;
	}
	public Date getAppendCreateDate() {
		return appendCreateDate;
	}
	public void setAppendCreateDate(Date appendCreateDate) {
		this.appendCreateDate = appendCreateDate;
	}
	public long getMchtReplyBetweenDays() {
		return mchtReplyBetweenDays;
	}
	public void setMchtReplyBetweenDays(long mchtReplyBetweenDays) {
		this.mchtReplyBetweenDays = mchtReplyBetweenDays;
	}
	
}
