package com.jf.entity;

import java.util.List;


public class VideoCustom extends Video {

	private String videoStatusDesc;

	private String companyName;
	
	private String mchtCode;
	
	private String mchtProductTypeName;
	
	private Integer playCount;
	
	private Integer likeCount;
	
	private Integer commentCount;
	
	private Integer collectCount;

	private Integer fwStaffId;

	private Integer fwAssistantId;
	
	private List<VideoProductCustom> videoProductCustoms;

    private List<VideoAuthorizedAccessories> videoAuthorizedAccessoriesList;

    private Integer totalVisitorCount;

    private Integer totalVisitorCountTourist;

    private Integer totalPvCount;

    private Integer totalPvCountTourist;

    private Integer shoppingCartCount;

    private String addProductRate;

    private Integer subProductCount;

    private String submitOrderRate;

	private Integer payProductCount;

	private String paymentRate;

	public Integer getFwStaffId() {
		return fwStaffId;
	}

	public void setFwStaffId(Integer fwStaffId) {
		this.fwStaffId = fwStaffId;
	}

	public Integer getFwAssistantId() {
		return fwAssistantId;
	}

	public void setFwAssistantId(Integer fwAssistantId) {
		this.fwAssistantId = fwAssistantId;
	}

	public List<VideoAuthorizedAccessories> getVideoAuthorizedAccessoriesList() {
        return videoAuthorizedAccessoriesList;
    }

	public void setVideoAuthorizedAccessoriesList(List<VideoAuthorizedAccessories> videoAuthorizedAccessoriesList) {
        this.videoAuthorizedAccessoriesList = videoAuthorizedAccessoriesList;
    }

    public String getVideoStatusDesc() {
		return videoStatusDesc;
	}

	public void setVideoStatusDesc(String videoStatusDesc) {
		this.videoStatusDesc = videoStatusDesc;
	}

	public String getMchtCode() {
		return mchtCode;
	}

	public void setMchtCode(String mchtCode) {
		this.mchtCode = mchtCode;
	}

	public String getMchtProductTypeName() {
		return mchtProductTypeName;
	}

	public void setMchtProductTypeName(String mchtProductTypeName) {
		this.mchtProductTypeName = mchtProductTypeName;
	}

	public Integer getPlayCount() {
		return playCount;
	}

	public void setPlayCount(Integer playCount) {
		this.playCount = playCount;
	}

	public Integer getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(Integer likeCount) {
		this.likeCount = likeCount;
	}

	public Integer getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}

	public Integer getCollectCount() {
		return collectCount;
	}

	public void setCollectCount(Integer collectCount) {
		this.collectCount = collectCount;
	}

	public List<VideoProductCustom> getVideoProductCustoms() {
		return videoProductCustoms;
	}

	public void setVideoProductCustoms(List<VideoProductCustom> videoProductCustoms) {
		this.videoProductCustoms = videoProductCustoms;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Integer getTotalVisitorCount() {
		return totalVisitorCount;
	}

	public void setTotalVisitorCount(Integer totalVisitorCount) {
		this.totalVisitorCount = totalVisitorCount;
	}

	public Integer getTotalVisitorCountTourist() {
		return totalVisitorCountTourist;
	}

	public void setTotalVisitorCountTourist(Integer totalVisitorCountTourist) {
		this.totalVisitorCountTourist = totalVisitorCountTourist;
	}

	public Integer getTotalPvCount() {
		return totalPvCount;
	}

	public void setTotalPvCount(Integer totalPvCount) {
		this.totalPvCount = totalPvCount;
	}

	public Integer getTotalPvCountTourist() {
		return totalPvCountTourist;
	}

	public void setTotalPvCountTourist(Integer totalPvCountTourist) {
		this.totalPvCountTourist = totalPvCountTourist;
	}

	public Integer getShoppingCartCount() {
		return shoppingCartCount;
	}

	public void setShoppingCartCount(Integer shoppingCartCount) {
		this.shoppingCartCount = shoppingCartCount;
	}

	public String getAddProductRate() {
		return addProductRate;
	}

	public void setAddProductRate(String addProductRate) {
		this.addProductRate = addProductRate;
	}

	public Integer getSubProductCount() {
		return subProductCount;
	}

	public void setSubProductCount(Integer subProductCount) {
		this.subProductCount = subProductCount;
	}

	public String getSubmitOrderRate() {
		return submitOrderRate;
	}

	public void setSubmitOrderRate(String submitOrderRate) {
		this.submitOrderRate = submitOrderRate;
	}

	public Integer getPayProductCount() {
		return payProductCount;
	}

	public void setPayProductCount(Integer payProductCount) {
		this.payProductCount = payProductCount;
	}

	public String getPaymentRate() {
		return paymentRate;
	}

	public void setPaymentRate(String paymentRate) {
		this.paymentRate = paymentRate;
	}
}
