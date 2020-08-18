package com.jf.entity;

import java.math.BigDecimal;
import java.util.Date;


public class OrderDtlCustom extends OrderDtl{
	
	private String subOrderStatus;
	
	private String subOrderCode;
	
	private String expressName;
	
	private String expressNo;
	
	private String receiverName;
	
	private String receiverPhone;
	
	private String province;
	
	private String city;
	
	private String county;
	
	private String receiverAddress;
	
	private String paymentDesc;
	
	private Date paymentDate;
	
	private Date deliveryDate;
	
	private Date completeDate;
	
	private String customerServiceStatus;
	
	private String productStatusDesc;
	
	private String customerServiceType;//售后类型:退款，退货...
	
	private String customerServiceStatusDesc;//售后状态:进行中 ,已完成...
	
	private String mchtRemarks;
	
	private String eachDay;
	
	private int productCount;
	
	private BigDecimal productAmount;
	
	private String activityAreaSource;
	
	private String activityAreaType;
	
	private String singleProductActivityType;
	
	private String singleProductActivityTypeDesc;
	
	private String memberStatus;
	
	private Integer subDepositOrderId;
	
	private String templetName;
	
	private String memberRemarks;
	
	private String dtlExpressName;
	
	private Date deliveryLastDate;
	
	private String remarksColor;

	private Date auditDate;

	private String preferentialType;

	private BigDecimal manageSelfFreight;

	private String mchtType;

	private String isManageSelf;

	public String getMchtType() {
		return mchtType;
	}

	public void setMchtType(String mchtType) {
		this.mchtType = mchtType;
	}

	public String getIsManageSelf() {
		return isManageSelf;
	}

	public void setIsManageSelf(String isManageSelf) {
		this.isManageSelf = isManageSelf;
	}

	public BigDecimal getManageSelfFreight() {
		return manageSelfFreight;
	}

	public void setManageSelfFreight(BigDecimal manageSelfFreight) {
		this.manageSelfFreight = manageSelfFreight;
	}

	public String getPreferentialType() {
		return preferentialType;
	}

	public void setPreferentialType(String preferentialType) {
		this.preferentialType = preferentialType;
	}

	public String getRemarksColor() {
		return remarksColor;
	}

	public void setRemarksColor(String remarksColor) {
		this.remarksColor = remarksColor;
	}

	public Date getDeliveryLastDate() {
		return deliveryLastDate;
	}

	public void setDeliveryLastDate(Date deliveryLastDate) {
		this.deliveryLastDate = deliveryLastDate;
	}

	private Integer salesVolume;
	
	private Long salesAmount;

	public Integer getSalesVolume() {
		return salesVolume;
	}

	public void setSalesVolume(Integer salesVolume) {
		this.salesVolume = salesVolume;
	}
	
	public Long getSalesAmount() {
		return salesAmount;
	}

	public void setSalesAmount(Long salesAmount) {
		this.salesAmount = salesAmount;
	}

	public String getSubOrderStatus() {
		return subOrderStatus;
	}

	public void setSubOrderStatus(String subOrderStatus) {
		this.subOrderStatus = subOrderStatus;
	}

	public String getCustomerServiceType() {
		return customerServiceType;
	}

	public void setCustomerServiceType(String customerServiceType) {
		this.customerServiceType = customerServiceType;
	}

	public String getCustomerServiceStatusDesc() {
		return customerServiceStatusDesc;
	}

	public void setCustomerServiceStatusDesc(String customerServiceStatusDesc) {
		this.customerServiceStatusDesc = customerServiceStatusDesc;
	}

	public String getSubOrderCode() {
		return subOrderCode;
	}

	public void setSubOrderCode(String subOrderCode) {
		this.subOrderCode = subOrderCode;
	}

	public String getExpressName() {
		return expressName;
	}

	public void setExpressName(String expressName) {
		this.expressName = expressName;
	}

	public String getExpressNo() {
		return expressNo;
	}

	public void setExpressNo(String expressNo) {
		this.expressNo = expressNo;
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

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getReceiverAddress() {
		return receiverAddress;
	}

	public void setReceiverAddress(String receiverAddress) {
		this.receiverAddress = receiverAddress;
	}

	public String getPaymentDesc() {
		return paymentDesc;
	}

	public void setPaymentDesc(String paymentDesc) {
		this.paymentDesc = paymentDesc;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public Date getCompleteDate() {
		return completeDate;
	}

	public void setCompleteDate(Date completeDate) {
		this.completeDate = completeDate;
	}

	public String getProductStatusDesc() {
		return productStatusDesc;
	}

	public void setProductStatusDesc(String productStatusDesc) {
		this.productStatusDesc = productStatusDesc;
	}

	public String getCustomerServiceStatus() {
		return customerServiceStatus;
	}

	public void setCustomerServiceStatus(String customerServiceStatus) {
		this.customerServiceStatus = customerServiceStatus;
	}

	public String getMchtRemarks() {
		return mchtRemarks;
	}

	public void setMchtRemarks(String mchtRemarks) {
		this.mchtRemarks = mchtRemarks;
	}

	public String getEachDay() {
		return eachDay;
	}

	public void setEachDay(String eachDay) {
		this.eachDay = eachDay;
	}

	public int getProductCount() {
		return productCount;
	}

	public void setProductCount(int productCount) {
		this.productCount = productCount;
	}

	public BigDecimal getProductAmount() {
		return productAmount;
	}

	public void setProductAmount(BigDecimal productAmount) {
		this.productAmount = productAmount;
	}

	public String getActivityAreaSource() {
		return activityAreaSource;
	}

	public void setActivityAreaSource(String activityAreaSource) {
		this.activityAreaSource = activityAreaSource;
	}

	public String getActivityAreaType() {
		return activityAreaType;
	}

	public void setActivityAreaType(String activityAreaType) {
		this.activityAreaType = activityAreaType;
	}

	public String getSingleProductActivityType() {
		return singleProductActivityType;
	}

	public void setSingleProductActivityType(String singleProductActivityType) {
		this.singleProductActivityType = singleProductActivityType;
	}

	public String getSingleProductActivityTypeDesc() {
		return singleProductActivityTypeDesc;
	}

	public void setSingleProductActivityTypeDesc(
			String singleProductActivityTypeDesc) {
		this.singleProductActivityTypeDesc = singleProductActivityTypeDesc;
	}

	public String getMemberStatus() {
		return memberStatus;
	}

	public void setMemberStatus(String memberStatus) {
		this.memberStatus = memberStatus;
	}

	public Integer getSubDepositOrderId() {
		return subDepositOrderId;
	}

	public void setSubDepositOrderId(Integer subDepositOrderId) {
		this.subDepositOrderId = subDepositOrderId;
	}

	public String getTempletName() {
		return templetName;
	}

	public void setTempletName(String templetName) {
		this.templetName = templetName;
	}

	public String getMemberRemarks() {
		return memberRemarks;
	}

	public void setMemberRemarks(String memberRemarks) {
		this.memberRemarks = memberRemarks;
	}

	public String getDtlExpressName() {
		return dtlExpressName;
	}

	public void setDtlExpressName(String dtlExpressName) {
		this.dtlExpressName = dtlExpressName;
	}

	public Date getAuditDate() { return auditDate; }

	public void setAuditDate(Date auditDate) { this.auditDate = auditDate; }



}
