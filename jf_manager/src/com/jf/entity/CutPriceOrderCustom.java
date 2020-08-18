package com.jf.entity;

import java.math.BigDecimal;
import java.util.Date;

public class CutPriceOrderCustom extends CutPriceOrder {

	private String productPic; // 主图
	private String productCode; // 商品ID
	private String shopName; // 店铺名
	private String cutPriceOrderDtlCount; // 此次砍价次数
	private String statusDesc; // 状态
	private String auditStatusDesc; // 审核状态
	private String cutPriceOrderCount; // 参与砍价次数
	private String cutPriceOrderSuccCount; // 成功砍价次数
	private String superCutPriceOrderCount; // 参与超级砍价次数
	private String superCutPriceOrderSuccCount; // 成功超级砍价次数
	private BigDecimal fixedAmount; // 助力一人减多少
	private Date payDate; // 付款时间
	private String combineOrderCode; // 总订单编号
	private String cutPriceOrderDtlSuccessCount; // 助力减价成功次数
	private String combineOrderStatus; // 是否付款
	private Integer maxInviteTimes; // 最多邀请人数（用于助力减价）

	public String getProductPic() {
		return productPic;
	}

	public void setProductPic(String productPic) {
		this.productPic = productPic;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getCutPriceOrderDtlCount() {
		return cutPriceOrderDtlCount;
	}

	public void setCutPriceOrderDtlCount(String cutPriceOrderDtlCount) {
		this.cutPriceOrderDtlCount = cutPriceOrderDtlCount;
	}

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	public String getCutPriceOrderCount() {
		return cutPriceOrderCount;
	}

	public void setCutPriceOrderCount(String cutPriceOrderCount) {
		this.cutPriceOrderCount = cutPriceOrderCount;
	}

	public String getCutPriceOrderSuccCount() {
		return cutPriceOrderSuccCount;
	}

	public void setCutPriceOrderSuccCount(String cutPriceOrderSuccCount) {
		this.cutPriceOrderSuccCount = cutPriceOrderSuccCount;
	}

	public String getSuperCutPriceOrderCount() {
		return superCutPriceOrderCount;
	}

	public void setSuperCutPriceOrderCount(String superCutPriceOrderCount) {
		this.superCutPriceOrderCount = superCutPriceOrderCount;
	}

	public String getSuperCutPriceOrderSuccCount() {
		return superCutPriceOrderSuccCount;
	}

	public void setSuperCutPriceOrderSuccCount(
			String superCutPriceOrderSuccCount) {
		this.superCutPriceOrderSuccCount = superCutPriceOrderSuccCount;
	}

	public String getAuditStatusDesc() {
		return auditStatusDesc;
	}

	public void setAuditStatusDesc(String auditStatusDesc) {
		this.auditStatusDesc = auditStatusDesc;
	}

	public BigDecimal getFixedAmount() {
		return fixedAmount;
	}

	public void setFixedAmount(BigDecimal fixedAmount) {
		this.fixedAmount = fixedAmount;
	}

	public Date getPayDate() {
		return payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}

	public String getCombineOrderCode() {
		return combineOrderCode;
	}

	public void setCombineOrderCode(String combineOrderCode) {
		this.combineOrderCode = combineOrderCode;
	}

	public String getCutPriceOrderDtlSuccessCount() {
		return cutPriceOrderDtlSuccessCount;
	}

	public void setCutPriceOrderDtlSuccessCount(
			String cutPriceOrderDtlSuccessCount) {
		this.cutPriceOrderDtlSuccessCount = cutPriceOrderDtlSuccessCount;
	}

	public String getCombineOrderStatus() {
		return combineOrderStatus;
	}

	public void setCombineOrderStatus(String combineOrderStatus) {
		this.combineOrderStatus = combineOrderStatus;
	}

	public Integer getMaxInviteTimes() {
		return maxInviteTimes;
	}

	public void setMaxInviteTimes(Integer maxInviteTimes) {
		this.maxInviteTimes = maxInviteTimes;
	}

}
