package com.jf.entity;

import java.util.Date;
import java.util.List;

public class SubOrderCustom extends SubOrder {
	private String statusDesc;
	private String mchtTypeDesc;
	private String isManageSelfDesc;
	private String shortName;
	private String shopName;
	private String mchtCode;
	private String companyName;
	private String artBrandGroup;
	private String paymentName;
	private Integer memberId;
	private String memberNick;
	private String combineOrderRemarks;
	private String receiverName;
	private String receiverPhone;
	private String receiverAddress;
	private String cancelReason;
	private Date cancelDate;
	private Date orderCreateDate;
	private Date orderPayDate;
	private String expressName;
	private String sourceClientDesc;
	private String combineOrderCode;
	private String activityIdGroup;
	private String activityAreaIdGroup;
	private String logRemarksColor;
	private String logRemarks;
	private Integer kdnId;
	private String ordercode;
	private Integer ProductQuantity;
	private Integer productid;
	private String brandCode;
	private String serviceOrderCode;
	private String overtimeFollowStatusDesc;
	private String shamFollowStatusDesc;
	private String violateorderid;
	private String followByName;
	private String productTypename;
	private String customerServiceOrderConcat;
	private String orderTypeDesc;
	private String orderType;
	private String reqImei;
	private String memberInfoStatus;
    private String brandProductName;
    private OrderDtl orderDtl;
    private String productName;
    private String productCode;
    private Integer productId;
    private String pic;
    private String memberStatus;
    private Integer suborderCount;
	private List<OrderDtl> orderDtlList;

	public Integer getSuborderCount() {
		return suborderCount;
	}

	public void setSuborderCount(Integer suborderCount) {
		this.suborderCount = suborderCount;
	}

	public String getMemberStatus() {
		return memberStatus;
	}

	public void setMemberStatus(String memberStatus) {
		this.memberStatus = memberStatus;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public OrderDtl getOrderDtl() {
		return orderDtl;
	}

	public void setOrderDtl(OrderDtl orderDtl) {
		this.orderDtl = orderDtl;
	}

	public List<OrderDtl> getOrderDtlList() {
		return orderDtlList;
	}

	public void setOrderDtlList(List<OrderDtl> orderDtlList) {
		this.orderDtlList = orderDtlList;
	}

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	public String getMchtTypeDesc() {
		return mchtTypeDesc;
	}

	public void setMchtTypeDesc(String mchtTypeDesc) {
		this.mchtTypeDesc = mchtTypeDesc;
	}

	public String getIsManageSelfDesc() {
		return isManageSelfDesc;
	}

	public void setIsManageSelfDesc(String isManageSelfDesc) {
		this.isManageSelfDesc = isManageSelfDesc;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getMchtCode() {
		return mchtCode;
	}

	public void setMchtCode(String mchtCode) {
		this.mchtCode = mchtCode;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getArtBrandGroup() {
		return artBrandGroup;
	}

	public void setArtBrandGroup(String artBrandGroup) {
		this.artBrandGroup = artBrandGroup;
	}

	public String getPaymentName() {
		return paymentName;
	}

	public void setPaymentName(String paymentName) {
		this.paymentName = paymentName;
	}

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public String getMemberNick() {
		return memberNick;
	}

	public void setMemberNick(String memberNick) {
		this.memberNick = memberNick;
	}

	public String getCombineOrderRemarks() {
		return combineOrderRemarks;
	}

	public void setCombineOrderRemarks(String combineOrderRemarks) {
		this.combineOrderRemarks = combineOrderRemarks;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getReceiverPhone() {
		return receiverPhone;
	}

	public void setReceiverPhone(String receiverPhone) {
		this.receiverPhone = receiverPhone;
	}

	public String getReceiverAddress() {
		return receiverAddress;
	}

	public void setReceiverAddress(String receiverAddress) {
		this.receiverAddress = receiverAddress;
	}

	public String getCancelReason() {
		return cancelReason;
	}

	public void setCancelReason(String cancelReason) {
		this.cancelReason = cancelReason;
	}

	public Date getCancelDate() {
		return cancelDate;
	}

	public void setCancelDate(Date cancelDate) {
		this.cancelDate = cancelDate;
	}

	public Date getOrderCreateDate() {
		return orderCreateDate;
	}

	public void setOrderCreateDate(Date orderCreateDate) {
		this.orderCreateDate = orderCreateDate;
	}

	public Date getOrderPayDate() {
		return orderPayDate;
	}

	public void setOrderPayDate(Date orderPayDate) {
		this.orderPayDate = orderPayDate;
	}

	public String getExpressName() {
		return expressName;
	}

	public void setExpressName(String expressName) {
		this.expressName = expressName;
	}

	public String getSourceClientDesc() {
		return sourceClientDesc;
	}

	public void setSourceClientDesc(String sourceClientDesc) {
		this.sourceClientDesc = sourceClientDesc;
	}

	public String getCombineOrderCode() {
		return combineOrderCode;
	}

	public void setCombineOrderCode(String combineOrderCode) {
		this.combineOrderCode = combineOrderCode;
	}

	public String getActivityIdGroup() {
		return activityIdGroup;
	}

	public void setActivityIdGroup(String activityIdGroup) {
		this.activityIdGroup = activityIdGroup;
	}

	public String getActivityAreaIdGroup() {
		return activityAreaIdGroup;
	}

	public void setActivityAreaIdGroup(String activityAreaIdGroup) {
		this.activityAreaIdGroup = activityAreaIdGroup;
	}

	public String getLogRemarksColor() {
		return logRemarksColor;
	}

	public void setLogRemarksColor(String logRemarksColor) {
		this.logRemarksColor = logRemarksColor;
	}

	public String getLogRemarks() {
		return logRemarks;
	}

	public void setLogRemarks(String logRemarks) {
		this.logRemarks = logRemarks;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public Integer getKdnId() {
		return kdnId;
	}

	public void setKdnId(Integer kdnId) {
		this.kdnId = kdnId;
	}

	public String getOrdercode() {
		return ordercode;
	}

	public void setOrdercode(String ordercode) {
		this.ordercode = ordercode;
	}

	public Integer getProductQuantity() {
		return ProductQuantity;
	}

	public void setProductQuantity(Integer productQuantity) {
		ProductQuantity = productQuantity;
	}

	public Integer getProductid() {
		return productid;
	}

	public void setProductid(Integer productid) {
		this.productid = productid;
	}

	public String getBrandCode() {
		return brandCode;
	}

	public void setBrandCode(String brandCode) {
		this.brandCode = brandCode;
	}

	public String getServiceOrderCode() {
		return serviceOrderCode;
	}

	public void setServiceOrderCode(String serviceOrderCode) {
		this.serviceOrderCode = serviceOrderCode;
	}

	public String getOvertimeFollowStatusDesc() {
		return overtimeFollowStatusDesc;
	}

	public void setOvertimeFollowStatusDesc(String overtimeFollowStatusDesc) {
		this.overtimeFollowStatusDesc = overtimeFollowStatusDesc;
	}

	public String getShamFollowStatusDesc() {
		return shamFollowStatusDesc;
	}

	public void setShamFollowStatusDesc(String shamFollowStatusDesc) {
		this.shamFollowStatusDesc = shamFollowStatusDesc;
	}

	public String getViolateorderid() {
		return violateorderid;
	}

	public void setViolateorderid(String violateorderid) {
		this.violateorderid = violateorderid;
	}

	public String getFollowByName() {
		return followByName;
	}

	public void setFollowByName(String followByName) {
		this.followByName = followByName;
	}

	public String getProductTypename() {
		return productTypename;
	}

	public void setProductTypename(String productTypename) {
		this.productTypename = productTypename;
	}

	public String getCustomerServiceOrderConcat() {
		return customerServiceOrderConcat;
	}

	public void setCustomerServiceOrderConcat(String customerServiceOrderConcat) {
		this.customerServiceOrderConcat = customerServiceOrderConcat;
	}

	public String getOrderTypeDesc() {
		return orderTypeDesc;
	}

	public void setOrderTypeDesc(String orderTypeDesc) {
		this.orderTypeDesc = orderTypeDesc;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getReqImei() {
		return reqImei;
	}

	public void setReqImei(String reqImei) {
		this.reqImei = reqImei;
	}

	public String getMemberInfoStatus() {
		return memberInfoStatus;
	}

	public void setMemberInfoStatus(String memberInfoStatus) {
		this.memberInfoStatus = memberInfoStatus;
	}

	public String getBrandProductName() {
		return brandProductName;
	}

	public void setBrandProductName(String brandProductName) {
		this.brandProductName = brandProductName;
	}
	

}
