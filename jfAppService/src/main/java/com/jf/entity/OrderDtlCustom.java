package com.jf.entity;

import java.math.BigDecimal;
import java.util.Date;

public class OrderDtlCustom extends OrderDtl {
	// 订单明细id
	private Integer orderDtlId;
	// 商家id
	private Integer mchtId;
	// 总订单id
	private Integer combineOrderId;
	// svip订单id
	private Integer svipOrderId;
	// 子订单id
	private Integer subOrderId;
	// 订单编码
	private String subOrderCode;
	// 收件人
	private String receiverName;
	// 收件人电话
	private String receiverPhone;
	// 收件地址
	private String receiverAddress;
	// 支付时间
	private Date payDate;

	private Byte status;
	// 省
	private String provinceName;
	// 市
	private String cityName;
	// 村
	private String countyName;
	// <!-- 订单商品总金额 -->
	private BigDecimal amount;

	private String combineOrderCode;

	// 售后单号
	private String orderCode;

	// 售后状态
	private String customerServiceOrderStatus;

	// 售后进度状态
	private String proStatus;

	// 定金金额
	private BigDecimal depositAmount;

	// 售后单id
	private String customerServiceOrderId;

	/**
	 * 快递id
	 */
	private String expressId;

	/**
	 * 快递编号
	 */
	private String expressNo;

	/**
	 * 发货时间
	 */
	private Date deliveryDate;

	/**
	 * 子订单积分优惠金额
	 */
	private BigDecimal subIntegralPreferential;
	/**
	 * 子订单实付金额
	 */
	private BigDecimal subPayAmount;
	/**
	 * 总订单积分优惠金额
	 */
	private BigDecimal combineIntegralPreferential;
	/**
	 * 总订单实付金额
	 */
	private BigDecimal combinePayAmount;

	/**
	 * 总订单商品金额
	 */
	private BigDecimal combineAmount;
	/**
	 * 总订单状态，0 未付 1 已付 4 取消
	 */
	private String combineOrderStatus;
	/**
	 * 优惠券使用最低金额
	 */
	private BigDecimal minimum;
	/**
	 * 优惠券优惠金额
	 */
	private BigDecimal money;
	/**
	 * 是否评价过 0否 1是
	 */
	private String isComment;
	/**
	 * 确认收货时间
	 */
	private Date completeDate;
	/**
	 * 已收货时间
	 */
	private Date receiptDate;
	/**
	 * 总订单平台优惠金额
	 */
	private BigDecimal combineIntegralAmount;
	/**
	 * 销售类型\r\n1 品牌款 \r\n2 单品款
	 */
	private String saleType;
	/**
	 * 订单类型
	 */
	private String orderType;
	
	/**
	 * 取消时间
	 */
	private Date cancelDate;
	/**
	 * 子订单关闭时间
	 */
	private Date closeDate;
	/**
	 * 图片
	 */
	private String pic;
	/**
	 * 会员昵称
	 */
	private String nick;

	private String auditStatus;

	public Integer getSvipOrderId() {
		return svipOrderId;
	}

	public void setSvipOrderId(Integer svipOrderId) {
		this.svipOrderId = svipOrderId;
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

	public Date getPayDate() {
		return payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCountyName() {
		return countyName;
	}

	public void setCountyName(String countyName) {
		this.countyName = countyName;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getCombineOrderCode() {
		return combineOrderCode;
	}

	public void setCombineOrderCode(String combineOrderCode) {
		this.combineOrderCode = combineOrderCode;
	}

	public Integer getCombineOrderId() {
		return combineOrderId;
	}

	public void setCombineOrderId(Integer combineOrderId) {
		this.combineOrderId = combineOrderId;
	}

	public Integer getSubOrderId() {
		return subOrderId;
	}

	public void setSubOrderId(Integer subOrderId) {
		this.subOrderId = subOrderId;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public String getCustomerServiceOrderStatus() {
		return customerServiceOrderStatus;
	}

	public void setCustomerServiceOrderStatus(String customerServiceOrderStatus) {
		this.customerServiceOrderStatus = customerServiceOrderStatus;
	}

	public String getProStatus() {
		return proStatus;
	}

	public void setProStatus(String proStatus) {
		this.proStatus = proStatus;
	}

	public String getCustomerServiceOrderId() {
		return customerServiceOrderId;
	}

	public void setCustomerServiceOrderId(String customerServiceOrderId) {
		this.customerServiceOrderId = customerServiceOrderId;
	}

	public Integer getOrderDtlId() {
		return orderDtlId;
	}

	public void setOrderDtlId(Integer orderDtlId) {
		this.orderDtlId = orderDtlId;
	}

	public Integer getMchtId() {
		return mchtId;
	}

	public void setMchtId(Integer mchtId) {
		this.mchtId = mchtId;
	}

	/**
	 * 子订单积分优惠金额
	 * 
	 * @return subIntegralPreferential 子订单积分优惠金额
	 */
	public BigDecimal getSubIntegralPreferential() {
		return subIntegralPreferential;
	}

	/**
	 * 子订单积分优惠金额
	 * 
	 * @param subIntegralPreferential
	 *            子订单积分优惠金额
	 */
	public void setSubIntegralPreferential(BigDecimal subIntegralPreferential) {
		this.subIntegralPreferential = subIntegralPreferential;
	}

	/**
	 * 子订单实付金额
	 * 
	 * @return subPayAmount 子订单实付金额
	 */
	public BigDecimal getSubPayAmount() {
		return subPayAmount;
	}

	/**
	 * 子订单实付金额
	 * 
	 * @param subPayAmount
	 *            子订单实付金额
	 */
	public void setSubPayAmount(BigDecimal subPayAmount) {
		this.subPayAmount = subPayAmount;
	}

	/**
	 * 总订单积分优惠金额
	 * 
	 * @return combineIntegralPreferential 总订单积分优惠金额
	 */
	public BigDecimal getCombineIntegralPreferential() {
		return combineIntegralPreferential;
	}

	/**
	 * 总订单积分优惠金额
	 * 
	 * @param combineIntegralPreferential
	 *            总订单积分优惠金额
	 */
	public void setCombineIntegralPreferential(
			BigDecimal combineIntegralPreferential) {
		this.combineIntegralPreferential = combineIntegralPreferential;
	}

	/**
	 * 总订单实付金额
	 * 
	 * @return combinePayAmount 总订单实付金额
	 */
	public BigDecimal getCombinePayAmount() {
		return combinePayAmount;
	}

	/**
	 * 总订单实付金额
	 * 
	 * @param combinePayAmount
	 *            总订单实付金额
	 */
	public void setCombinePayAmount(BigDecimal combinePayAmount) {
		this.combinePayAmount = combinePayAmount;
	}

	/**
	 * 总订单商品金额
	 * 
	 * @return combineAmount 总订单商品金额
	 */
	public BigDecimal getCombineAmount() {
		return combineAmount;
	}

	/**
	 * 总订单商品金额
	 * 
	 * @param combineAmount
	 *            总订单商品金额
	 */
	public void setCombineAmount(BigDecimal combineAmount) {
		this.combineAmount = combineAmount;
	}

	/**
	 * 快递id
	 * 
	 * @return expressId 快递id
	 */
	public String getExpressId() {
		return expressId;
	}

	/**
	 * 快递id
	 * 
	 * @param expressId
	 *            快递id
	 */
	public void setExpressId(String expressId) {
		this.expressId = expressId;
	}

	/**
	 * 快递编号
	 * 
	 * @return expressNo 快递编号
	 */
	public String getExpressNo() {
		return expressNo;
	}

	/**
	 * 快递编号
	 * 
	 * @param expressNo
	 *            快递编号
	 */
	public void setExpressNo(String expressNo) {
		this.expressNo = expressNo;
	}

	/**
	 * 发货时间
	 * 
	 * @return deliveryDate 发货时间
	 */
	public Date getDeliveryDate() {
		return deliveryDate;
	}

	/**
	 * 发货时间
	 * 
	 * @param deliveryDate
	 *            发货时间
	 */
	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	/**
	 * 总订单状态，0未付1已付4取消
	 * 
	 * @return combineOrderStatus 总订单状态，0未付1已付4取消
	 */
	public String getCombineOrderStatus() {
		return combineOrderStatus;
	}

	/**
	 * 总订单状态，0未付1已付4取消
	 * 
	 * @param combineOrderStatus
	 *            总订单状态，0未付1已付4取消
	 */
	public void setCombineOrderStatus(String combineOrderStatus) {
		this.combineOrderStatus = combineOrderStatus;
	}

	public BigDecimal getMinimum() {
		return minimum;
	}

	public void setMinimum(BigDecimal minimum) {
		this.minimum = minimum;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public BigDecimal getCombineIntegralAmount() {
		return combineIntegralAmount;
	}

	public void setCombineIntegralAmount(BigDecimal combineIntegralAmount) {
		this.combineIntegralAmount = combineIntegralAmount;
	}

	/**
	 * 是否评价过0否1是
	 * 
	 * @return isComment 是否评价过0否1是
	 */
	public String getIsComment() {
		return isComment;
	}

	/**
	 * 是否评价过0否1是
	 * 
	 * @param isComment
	 *            是否评价过0否1是
	 */
	public void setIsComment(String isComment) {
		this.isComment = isComment;
	}

	/**
	 * 确认收货时间
	 * 
	 * @return completeDate 确认收货时间
	 */
	public Date getCompleteDate() {
		return completeDate;
	}

	/**
	 * 确认收货时间
	 * 
	 * @param completeDate
	 *            确认收货时间
	 */
	public void setCompleteDate(Date completeDate) {
		this.completeDate = completeDate;
	}

	public Date getReceiptDate() {
		return receiptDate;
	}

	public void setReceiptDate(Date receiptDate) {
		this.receiptDate = receiptDate;
	}

	public String getSaleType() {
		return saleType;
	}

	public void setSaleType(String saleType) {
		this.saleType = saleType;
	}

	public BigDecimal getDepositAmount() {
		return depositAmount;
	}

	public void setDepositAmount(BigDecimal depositAmount) {
		this.depositAmount = depositAmount;
	}

	public Date getCancelDate() {
		return cancelDate;
	}
	

	public void setCancelDate(Date cancelDate) {
		this.cancelDate = cancelDate;
	}

	public String getOrderType() {
		return orderType;
	}
	

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public Date getCloseDate() {
		return closeDate;
	}
	

	public void setCloseDate(Date closeDate) {
		this.closeDate = closeDate;
	}

	public String getPic() {
		return pic;
	}
	

	public void setPic(String pic) {
		this.pic = pic;
	}
	

	public String getNick() {
		return nick;
	}
	

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}
}