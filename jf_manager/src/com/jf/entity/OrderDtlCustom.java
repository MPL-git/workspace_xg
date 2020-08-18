package com.jf.entity;

import java.math.BigDecimal;
import java.util.Date;

@SuppressWarnings("serial")
public class OrderDtlCustom extends OrderDtl {
	private String productPic;
	private String subOrderCode;
	private Date subOrderCreateDate;
	private Date payDate;
	private Date deliveryDate;
	private Date completeDate;
	private String subStatusDesc;
	private String serviceTypeDesc;
	private String serviceStatusDesc;
	private String productBrandName;
	private String productName;
	private String productCode;
	private String productArtNo;
	private String isManageSelf;
	private String isManageSelfDesc;
	private Integer mchtId;
	private String mchtShopName;
	private BigDecimal balanceMoney;
	private BigDecimal commissionMoney;
	private BigDecimal needPayMoeny;
	private BigDecimal profitMoeny;
	private Integer appealOrderId;
	private String appealStatusDesc;
	private String productStatusDesc;
	private String receiverName;
	private Integer memberId;
	private String paymentName;
	private String mchtType;
	private String mchtCode;
	private String mchtShortName;
	private String companyName;
	private String payStatusDesc;
	private String eachDay;
	private BigDecimal eachDayPayAmount;
	private BigDecimal eachDayPreferentialAmount;
	private BigDecimal eachDayPreferentialRate;
	private BigDecimal eachDayCompletePayAmount;
	private BigDecimal eachDayCompletePreferentialAmount;
	private BigDecimal eachDayCompletePreferentialRate;

	private int acceptQuantity;
	private int refundQuantity;
	private BigDecimal acceptPayAmount;
	private BigDecimal refundPayAmount;
	private BigDecimal acceptSettleAmount;
	private BigDecimal refundSettleAmount;
	private BigDecimal acceptCommissionAmount;
	private BigDecimal refundCommissionAmount;
	private BigDecimal needSettleAmount;
	private BigDecimal totalCommissionAmount;

	private BigDecimal totalSaleAmount;
	private Integer totalQuantity;
	private Integer popTotalQuantity;
	private Integer poolTotalQuantity;
	private BigDecimal popTotalSettleAmount;
	private BigDecimal poolTotalSettleAmount;
	private BigDecimal totalSettleAmount;

	private int buyUserCount;
	private int orderCount;
	private int productCount;
	private BigDecimal productSalePrice;
	private BigDecimal totalMchtPreferential;
	private BigDecimal totalPlatformPreferential;
	private BigDecimal totalPayAmount;
	private BigDecimal unitPrice;
	private BigDecimal mchtDiscountRate;
	private BigDecimal platDiscountRate;
	private BigDecimal grossProfitRate;

	private String productTypeName;
	private BigDecimal averagePrice;
	private String sourceClientDesc;
	private String areaName;
	private String combineOrderStatusDesc;
	private String firstProductTypeName;
	private String secondProductTypeName;

	private String eachWeek;

	private String mobileBrand;
	private int subOrderCount;
	private int consumeCount;
	private float conversionRate;
	private BigDecimal totalIntegralPreferential;
	private String eachHour;
	private int activeCount;
	private String subOrderStatus;

	private Integer customerServiceId;
	private String serviceProStatus;
	private String code;
	private String singleProductActivityType;
	private Integer interventionOrderId;
	private Integer orderProductSnapshotId;

	private String producttypeName1;
	private String producttypeName2;
	private String producttypeName3;

	private String receiverPhone;
	private String receiverAddress;
	private String combineOrdercode;
	private String exName;
	private String expressNo;
	private String orderDtlExpressName;
	
	private String promotionType;
	
	//根据状态判断商品来源
	private String activityAreaSource;
	private String activityAreaName;
	private String singleActivityName;
	//店长权益金额
	private String shopownerAmount;
	//订单、预售订单
	private String orderType;
	//自营运费
	private BigDecimal manageSelfFreight;

	public BigDecimal getManageSelfFreight() {
		return manageSelfFreight;
	}

	public void setManageSelfFreight(BigDecimal manageSelfFreight) {
		this.manageSelfFreight = manageSelfFreight;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getShopownerAmount() {
		return shopownerAmount;
	}

	public void setShopownerAmount(String shopownerAmount) {
		this.shopownerAmount = shopownerAmount;
	}

	public String getProductPic() {
		return productPic;
	}

	public void setProductPic(String productPic) {
		this.productPic = productPic;
	}

	public String getSubOrderCode() {
		return subOrderCode;
	}

	public void setSubOrderCode(String subOrderCode) {
		this.subOrderCode = subOrderCode;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getSubStatusDesc() {
		return subStatusDesc;
	}

	public void setSubStatusDesc(String subStatusDesc) {
		this.subStatusDesc = subStatusDesc;
	}

	public String getServiceTypeDesc() {
		return serviceTypeDesc;
	}

	public void setServiceTypeDesc(String serviceTypeDesc) {
		this.serviceTypeDesc = serviceTypeDesc;
	}

	public String getServiceStatusDesc() {
		return serviceStatusDesc;
	}

	public void setServiceStatusDesc(String serviceStatusDesc) {
		this.serviceStatusDesc = serviceStatusDesc;
	}

	public String getProductBrandName() {
		return productBrandName;
	}

	public void setProductBrandName(String productBrandName) {
		this.productBrandName = productBrandName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductArtNo() {
		return productArtNo;
	}

	public void setProductArtNo(String productArtNo) {
		this.productArtNo = productArtNo;
	}

	public String getIsManageSelf() {
		return isManageSelf;
	}

	public void setIsManageSelf(String isManageSelf) {
		this.isManageSelf = isManageSelf;
	}

	public String getIsManageSelfDesc() {
		return isManageSelfDesc;
	}

	public void setIsManageSelfDesc(String isManageSelfDesc) {
		this.isManageSelfDesc = isManageSelfDesc;
	}

	public Integer getMchtId() {
		return mchtId;
	}

	public void setMchtId(Integer mchtId) {
		this.mchtId = mchtId;
	}

	public String getMchtShopName() {
		return mchtShopName;
	}

	public void setMchtShopName(String mchtShopName) {
		this.mchtShopName = mchtShopName;
	}

	public BigDecimal getBalanceMoney() {
		return balanceMoney;
	}

	public void setBalanceMoney(BigDecimal balanceMoney) {
		this.balanceMoney = balanceMoney;
	}

	public BigDecimal getCommissionMoney() {
		return commissionMoney;
	}

	public void setCommissionMoney(BigDecimal commissionMoney) {
		this.commissionMoney = commissionMoney;
	}

	public BigDecimal getNeedPayMoeny() {
		return needPayMoeny;
	}

	public void setNeedPayMoeny(BigDecimal needPayMoeny) {
		this.needPayMoeny = needPayMoeny;
	}

	public BigDecimal getProfitMoeny() {
		return profitMoeny;
	}

	public void setProfitMoeny(BigDecimal profitMoeny) {
		this.profitMoeny = profitMoeny;
	}

	public Integer getAppealOrderId() {
		return appealOrderId;
	}

	public void setAppealOrderId(Integer appealOrderId) {
		this.appealOrderId = appealOrderId;
	}

	public String getAppealStatusDesc() {
		return appealStatusDesc;
	}

	public void setAppealStatusDesc(String appealStatusDesc) {
		this.appealStatusDesc = appealStatusDesc;
	}

	public Date getSubOrderCreateDate() {
		return subOrderCreateDate;
	}

	public void setSubOrderCreateDate(Date subOrderCreateDate) {
		this.subOrderCreateDate = subOrderCreateDate;
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

	public Date getPayDate() {
		return payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public String getPaymentName() {
		return paymentName;
	}

	public void setPaymentName(String paymentName) {
		this.paymentName = paymentName;
	}

	public String getMchtType() {
		return mchtType;
	}

	public void setMchtType(String mchtType) {
		this.mchtType = mchtType;
	}

	public String getMchtCode() {
		return mchtCode;
	}

	public void setMchtCode(String mchtCode) {
		this.mchtCode = mchtCode;
	}

	public String getMchtShortName() {
		return mchtShortName;
	}

	public void setMchtShortName(String mchtShortName) {
		this.mchtShortName = mchtShortName;
	}

	public String getPayStatusDesc() {
		return payStatusDesc;
	}

	public void setPayStatusDesc(String payStatusDesc) {
		this.payStatusDesc = payStatusDesc;
	}

	public String getEachDay() {
		return eachDay;
	}

	public void setEachDay(String eachDay) {
		this.eachDay = eachDay;
	}

	public BigDecimal getEachDayPayAmount() {
		return eachDayPayAmount;
	}

	public void setEachDayPayAmount(BigDecimal eachDayPayAmount) {
		this.eachDayPayAmount = eachDayPayAmount;
	}

	public BigDecimal getEachDayPreferentialAmount() {
		return eachDayPreferentialAmount;
	}

	public void setEachDayPreferentialAmount(
			BigDecimal eachDayPreferentialAmount) {
		this.eachDayPreferentialAmount = eachDayPreferentialAmount;
	}

	public BigDecimal getEachDayPreferentialRate() {
		return eachDayPreferentialRate;
	}

	public void setEachDayPreferentialRate(BigDecimal eachDayPreferentialRate) {
		this.eachDayPreferentialRate = eachDayPreferentialRate;
	}

	public BigDecimal getEachDayCompletePayAmount() {
		return eachDayCompletePayAmount;
	}

	public void setEachDayCompletePayAmount(BigDecimal eachDayCompletePayAmount) {
		this.eachDayCompletePayAmount = eachDayCompletePayAmount;
	}

	public BigDecimal getEachDayCompletePreferentialAmount() {
		return eachDayCompletePreferentialAmount;
	}

	public void setEachDayCompletePreferentialAmount(
			BigDecimal eachDayCompletePreferentialAmount) {
		this.eachDayCompletePreferentialAmount = eachDayCompletePreferentialAmount;
	}

	public BigDecimal getEachDayCompletePreferentialRate() {
		return eachDayCompletePreferentialRate;
	}

	public void setEachDayCompletePreferentialRate(
			BigDecimal eachDayCompletePreferentialRate) {
		this.eachDayCompletePreferentialRate = eachDayCompletePreferentialRate;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public int getAcceptQuantity() {
		return acceptQuantity;
	}

	public void setAcceptQuantity(int acceptQuantity) {
		this.acceptQuantity = acceptQuantity;
	}

	public int getRefundQuantity() {
		return refundQuantity;
	}

	public void setRefundQuantity(int refundQuantity) {
		this.refundQuantity = refundQuantity;
	}

	public BigDecimal getAcceptPayAmount() {
		return acceptPayAmount;
	}

	public void setAcceptPayAmount(BigDecimal acceptPayAmount) {
		this.acceptPayAmount = acceptPayAmount;
	}

	public BigDecimal getRefundPayAmount() {
		return refundPayAmount;
	}

	public void setRefundPayAmount(BigDecimal refundPayAmount) {
		this.refundPayAmount = refundPayAmount;
	}

	public BigDecimal getAcceptSettleAmount() {
		return acceptSettleAmount;
	}

	public void setAcceptSettleAmount(BigDecimal acceptSettleAmount) {
		this.acceptSettleAmount = acceptSettleAmount;
	}

	public BigDecimal getRefundSettleAmount() {
		return refundSettleAmount;
	}

	public void setRefundSettleAmount(BigDecimal refundSettleAmount) {
		this.refundSettleAmount = refundSettleAmount;
	}

	public BigDecimal getAcceptCommissionAmount() {
		return acceptCommissionAmount;
	}

	public void setAcceptCommissionAmount(BigDecimal acceptCommissionAmount) {
		this.acceptCommissionAmount = acceptCommissionAmount;
	}

	public BigDecimal getRefundCommissionAmount() {
		return refundCommissionAmount;
	}

	public void setRefundCommissionAmount(BigDecimal refundCommissionAmount) {
		this.refundCommissionAmount = refundCommissionAmount;
	}

	public BigDecimal getNeedSettleAmount() {
		return needSettleAmount;
	}

	public void setNeedSettleAmount(BigDecimal needSettleAmount) {
		this.needSettleAmount = needSettleAmount;
	}

	public BigDecimal getTotalCommissionAmount() {
		return totalCommissionAmount;
	}

	public void setTotalCommissionAmount(BigDecimal totalCommissionAmount) {
		this.totalCommissionAmount = totalCommissionAmount;
	}

	public BigDecimal getTotalSaleAmount() {
		return totalSaleAmount;
	}

	public void setTotalSaleAmount(BigDecimal totalSaleAmount) {
		this.totalSaleAmount = totalSaleAmount;
	}

	public Integer getTotalQuantity() {
		return totalQuantity;
	}

	public void setTotalQuantity(Integer totalQuantity) {
		this.totalQuantity = totalQuantity;
	}

	public Integer getPopTotalQuantity() {
		return popTotalQuantity;
	}

	public void setPopTotalQuantity(Integer popTotalQuantity) {
		this.popTotalQuantity = popTotalQuantity;
	}

	public Integer getPoolTotalQuantity() {
		return poolTotalQuantity;
	}

	public void setPoolTotalQuantity(Integer poolTotalQuantity) {
		this.poolTotalQuantity = poolTotalQuantity;
	}

	public BigDecimal getPopTotalSettleAmount() {
		return popTotalSettleAmount;
	}

	public void setPopTotalSettleAmount(BigDecimal popTotalSettleAmount) {
		this.popTotalSettleAmount = popTotalSettleAmount;
	}

	public BigDecimal getPoolTotalSettleAmount() {
		return poolTotalSettleAmount;
	}

	public void setPoolTotalSettleAmount(BigDecimal poolTotalSettleAmount) {
		this.poolTotalSettleAmount = poolTotalSettleAmount;
	}

	public BigDecimal getTotalSettleAmount() {
		return totalSettleAmount;
	}

	public void setTotalSettleAmount(BigDecimal totalSettleAmount) {
		this.totalSettleAmount = totalSettleAmount;
	}

	public int getBuyUserCount() {
		return buyUserCount;
	}

	public void setBuyUserCount(int buyUserCount) {
		this.buyUserCount = buyUserCount;
	}

	public int getOrderCount() {
		return orderCount;
	}

	public void setOrderCount(int orderCount) {
		this.orderCount = orderCount;
	}

	public int getProductCount() {
		return productCount;
	}

	public void setProductCount(int productCount) {
		this.productCount = productCount;
	}

	public BigDecimal getProductSalePrice() {
		return productSalePrice;
	}

	public void setProductSalePrice(BigDecimal productSalePrice) {
		this.productSalePrice = productSalePrice;
	}

	public BigDecimal getTotalMchtPreferential() {
		return totalMchtPreferential;
	}

	public void setTotalMchtPreferential(BigDecimal totalMchtPreferential) {
		this.totalMchtPreferential = totalMchtPreferential;
	}

	public BigDecimal getTotalPlatformPreferential() {
		return totalPlatformPreferential;
	}

	public void setTotalPlatformPreferential(
			BigDecimal totalPlatformPreferential) {
		this.totalPlatformPreferential = totalPlatformPreferential;
	}

	public BigDecimal getTotalPayAmount() {
		return totalPayAmount;
	}

	public void setTotalPayAmount(BigDecimal totalPayAmount) {
		this.totalPayAmount = totalPayAmount;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public BigDecimal getMchtDiscountRate() {
		return mchtDiscountRate;
	}

	public void setMchtDiscountRate(BigDecimal mchtDiscountRate) {
		this.mchtDiscountRate = mchtDiscountRate;
	}

	public BigDecimal getPlatDiscountRate() {
		return platDiscountRate;
	}

	public void setPlatDiscountRate(BigDecimal platDiscountRate) {
		this.platDiscountRate = platDiscountRate;
	}

	public BigDecimal getGrossProfitRate() {
		return grossProfitRate;
	}

	public void setGrossProfitRate(BigDecimal grossProfitRate) {
		this.grossProfitRate = grossProfitRate;
	}

	public String getProductTypeName() {
		return productTypeName;
	}

	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
	}

	public BigDecimal getAveragePrice() {
		return averagePrice;
	}

	public void setAveragePrice(BigDecimal averagePrice) {
		this.averagePrice = averagePrice;
	}

	public String getSourceClientDesc() {
		return sourceClientDesc;
	}

	public void setSourceClientDesc(String sourceClientDesc) {
		this.sourceClientDesc = sourceClientDesc;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getCombineOrderStatusDesc() {
		return combineOrderStatusDesc;
	}

	public void setCombineOrderStatusDesc(String combineOrderStatusDesc) {
		this.combineOrderStatusDesc = combineOrderStatusDesc;
	}

	public String getFirstProductTypeName() {
		return firstProductTypeName;
	}

	public void setFirstProductTypeName(String firstProductTypeName) {
		this.firstProductTypeName = firstProductTypeName;
	}

	public String getSecondProductTypeName() {
		return secondProductTypeName;
	}

	public void setSecondProductTypeName(String secondProductTypeName) {
		this.secondProductTypeName = secondProductTypeName;
	}

	public String getEachWeek() {
		return eachWeek;
	}

	public void setEachWeek(String eachWeek) {
		this.eachWeek = eachWeek;
	}

	public String getMobileBrand() {
		return mobileBrand;
	}

	public void setMobileBrand(String mobileBrand) {
		this.mobileBrand = mobileBrand;
	}

	public int getSubOrderCount() {
		return subOrderCount;
	}

	public void setSubOrderCount(int subOrderCount) {
		this.subOrderCount = subOrderCount;
	}

	public int getConsumeCount() {
		return consumeCount;
	}

	public void setConsumeCount(int consumeCount) {
		this.consumeCount = consumeCount;
	}

	public float getConversionRate() {
		return conversionRate;
	}

	public void setConversionRate(float conversionRate) {
		this.conversionRate = conversionRate;
	}

	public BigDecimal getTotalIntegralPreferential() {
		return totalIntegralPreferential;
	}

	public void setTotalIntegralPreferential(
			BigDecimal totalIntegralPreferential) {
		this.totalIntegralPreferential = totalIntegralPreferential;
	}

	public String getEachHour() {
		return eachHour;
	}

	public void setEachHour(String eachHour) {
		this.eachHour = eachHour;
	}

	public int getActiveCount() {
		return activeCount;
	}

	public void setActiveCount(int activeCount) {
		this.activeCount = activeCount;
	}

	public Integer getCustomerServiceId() {
		return customerServiceId;
	}

	public void setCustomerServiceId(Integer customerServiceId) {
		this.customerServiceId = customerServiceId;
	}

	public String getSubOrderStatus() {
		return subOrderStatus;
	}

	public void setSubOrderStatus(String subOrderStatus) {
		this.subOrderStatus = subOrderStatus;
	}

	public String getServiceProStatus() {
		return serviceProStatus;
	}

	public void setServiceProStatus(String serviceProStatus) {
		this.serviceProStatus = serviceProStatus;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getSingleProductActivityType() {
		return singleProductActivityType;
	}

	public void setSingleProductActivityType(String singleProductActivityType) {
		this.singleProductActivityType = singleProductActivityType;
	}

	public Integer getInterventionOrderId() {
		return interventionOrderId;
	}

	public void setInterventionOrderId(Integer interventionOrderId) {
		this.interventionOrderId = interventionOrderId;
	}

	public Integer getOrderProductSnapshotId() {
		return orderProductSnapshotId;
	}

	public void setOrderProductSnapshotId(Integer orderProductSnapshotId) {
		this.orderProductSnapshotId = orderProductSnapshotId;
	}

	public String getProducttypeName1() {
		return producttypeName1;
	}

	public void setProducttypeName1(String producttypeName1) {
		this.producttypeName1 = producttypeName1;
	}

	public String getProducttypeName2() {
		return producttypeName2;
	}

	public void setProducttypeName2(String producttypeName2) {
		this.producttypeName2 = producttypeName2;
	}

	public String getProducttypeName3() {
		return producttypeName3;
	}

	public void setProducttypeName3(String producttypeName3) {
		this.producttypeName3 = producttypeName3;
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

	public String getCombineOrdercode() {
		return combineOrdercode;
	}

	public void setCombineOrdercode(String combineOrdercode) {
		this.combineOrdercode = combineOrdercode;
	}

	public String getExName() {
		return exName;
	}

	public void setExName(String exName) {
		this.exName = exName;
	}

	public String getExpressNo() {
		return expressNo;
	}

	public void setExpressNo(String expressNo) {
		this.expressNo = expressNo;
	}

	public String getOrderDtlExpressName() {
		return orderDtlExpressName;
	}

	public void setOrderDtlExpressName(String orderDtlExpressName) {
		this.orderDtlExpressName = orderDtlExpressName;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getActivityAreaSource() {
		return activityAreaSource;
	}

	public void setActivityAreaSource(String activityAreaSource) {
		this.activityAreaSource = activityAreaSource;
	}

	public String getActivityAreaName() {
		return activityAreaName;
	}

	public void setActivityAreaName(String activityAreaName) {
		this.activityAreaName = activityAreaName;
	}

	public String getSingleActivityName() {
		return singleActivityName;
	}

	public void setSingleActivityName(String singleActivityName) {
		this.singleActivityName = singleActivityName;
	}

	public String getPromotionType() {
		return promotionType;
	}

	public void setPromotionType(String promotionType) {
		this.promotionType = promotionType;
	}

}