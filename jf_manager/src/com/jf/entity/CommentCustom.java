package com.jf.entity;

public class CommentCustom extends Comment {

	private String subOrderCode; // 订单编号
	private String receiverName; // 收货人
	private String productName; // 商品名称
	private String brandName; // 品牌名称
	private String artNo; // 货号
	private String productPropDesc; // 商品属性描述
	private String sku; // sku编码
	private String skuPic; // 商品sku图片
	private String commentPic; // 评价图片（多张，最多六张）
	private Integer mchtScore; // 卖家服务得分
	private Integer wuliuScore; // 物流服务得分
	private String appendCommentContent; // 追加评价内容
	private String appendCommentPic; // 追加评价图片
	private String mchtCommentPic; // 商家回复图片
	private Integer mchtReplyDay; // 商家回复时间差
	private Integer appendReplyDate; // 追加评价时间差
	private Integer appendCommentId; // 追加评价id
	private String isShowDesc; // 是否显示
	private String productCode; // 商品长ID

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

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
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

	public String getSkuPic() {
		return skuPic;
	}

	public void setSkuPic(String skuPic) {
		this.skuPic = skuPic;
	}

	public String getCommentPic() {
		return commentPic;
	}

	public void setCommentPic(String commentPic) {
		this.commentPic = commentPic;
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

	public String getAppendCommentContent() {
		return appendCommentContent;
	}

	public void setAppendCommentContent(String appendCommentContent) {
		this.appendCommentContent = appendCommentContent;
	}

	public String getAppendCommentPic() {
		return appendCommentPic;
	}

	public void setAppendCommentPic(String appendCommentPic) {
		this.appendCommentPic = appendCommentPic;
	}

	public String getMchtCommentPic() {
		return mchtCommentPic;
	}

	public void setMchtCommentPic(String mchtCommentPic) {
		this.mchtCommentPic = mchtCommentPic;
	}

	public Integer getMchtReplyDay() {
		return mchtReplyDay;
	}

	public void setMchtReplyDay(Integer mchtReplyDay) {
		this.mchtReplyDay = mchtReplyDay;
	}

	public Integer getAppendReplyDate() {
		return appendReplyDate;
	}

	public void setAppendReplyDate(Integer appendReplyDate) {
		this.appendReplyDate = appendReplyDate;
	}

	public Integer getAppendCommentId() {
		return appendCommentId;
	}

	public void setAppendCommentId(Integer appendCommentId) {
		this.appendCommentId = appendCommentId;
	}

	public String getIsShowDesc() {
		return isShowDesc;
	}

	public void setIsShowDesc(String isShowDesc) {
		this.isShowDesc = isShowDesc;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	
}
