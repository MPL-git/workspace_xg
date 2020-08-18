package com.jf.entity;

import java.math.BigDecimal;

public class OrderDtlCustom extends OrderDtl{
	/**
	 * 支付方式
	 * 1 微信
	 * 2 支付宝
	 */
	private Integer paymentId;
	/**
	 * 支付渠道交易号
	 */
    private String paymentNo;
    /**
     * 总实付金额
     */
    private BigDecimal combinePayAmount;
    /**
     * 子订单的实付金额
     */
    private BigDecimal subPayAmount;
    /**
	 * 退款理由
	 */
    private String reason;
    /**
	 * 总订单编号
	 */
    private String combineOrderCode;
    /**
	 * 子订单编号
	 */
    private String subOrderCode;
    
    /**
     * 商家Id
     */
    private Integer mchtId;
    
    
    /**
     * 商家Id
     */
    private String mchtType;
    /**
     * 售后主状态
     */
    private String customerStatus;
    
    /**
     *	距权重更新时间30天内的销售量
     */
    private Integer salesVolume;
    
    /**
     *  距权重更新时间30天内的销售额
     */
    private Integer salesDegree;
    
    /**
     *	距权重更新时间300天内的销售量
     */
    private Integer sumQuantity;
    
    /**
     *  距权重更新时间300天内的销售额
     */
    private BigDecimal sumAmount;

	/**
	 *  自营运费
	 */
	private BigDecimal manageSelfFreight;

	public BigDecimal getManageSelfFreight() {
		return manageSelfFreight;
	}

	public void setManageSelfFreight(BigDecimal manageSelfFreight) {
		this.manageSelfFreight = manageSelfFreight;
	}

	public Integer getSalesVolume() {
		return salesVolume;
	}

	public void setSalesVolume(Integer salesVolume) {
		this.salesVolume = salesVolume;
	}

	public Integer getSalesDegree() {
		return salesDegree;
	}

	public void setSalesDegree(Integer salesDegree) {
		this.salesDegree = salesDegree;
	}
	
	public Integer getSumQuantity() {
		return sumQuantity;
	}

	public void setSumQuantity(Integer sumQuantity) {
		this.sumQuantity = sumQuantity;
	}

	

	public BigDecimal getSumAmount() {
		return sumAmount;
	}

	public void setSumAmount(BigDecimal sumAmount) {
		this.sumAmount = sumAmount;
	}

	/**  
	 * 支付方式1微信2支付宝  
	 * @return paymentId 支付方式1微信2支付宝  
	 */
	public Integer getPaymentId() {
		return paymentId;
	}
	
	/**  
	 * 支付方式1微信2支付宝  
	 * @param paymentId 支付方式1微信2支付宝  
	 */
	public void setPaymentId(Integer paymentId) {
		this.paymentId = paymentId;
	}
	
	/**  
	 * 支付渠道交易号  
	 * @return paymentNo 支付渠道交易号  
	 */
	public String getPaymentNo() {
		return paymentNo;
	}
	
	/**  
	 * 支付渠道交易号  
	 * @param paymentNo 支付渠道交易号  
	 */
	public void setPaymentNo(String paymentNo) {
		this.paymentNo = paymentNo;
	}
	
	/**  
	 * 总实付金额  
	 * @return combinePayAmount 总实付金额  
	 */
	public BigDecimal getCombinePayAmount() {
		return combinePayAmount;
	}
	
	/**  
	 * 总实付金额  
	 * @param combinePayAmount 总实付金额  
	 */
	public void setCombinePayAmount(BigDecimal combinePayAmount) {
		this.combinePayAmount = combinePayAmount;
	}
	
	/**  
	 * 子订单的实付金额  
	 * @return subPayAmount 子订单的实付金额  
	 */
	public BigDecimal getSubPayAmount() {
		return subPayAmount;
	}
	
	/**  
	 * 子订单的实付金额  
	 * @param subPayAmount 子订单的实付金额  
	 */
	public void setSubPayAmount(BigDecimal subPayAmount) {
		this.subPayAmount = subPayAmount;
	}

	/**  
	 * 退款理由  
	 * @return reason 退款理由  
	 */
	public String getReason() {
		return reason;
	}
	

	/**  
	 * 退款理由  
	 * @param reason 退款理由  
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}

	/**  
	 * 总订单编号  
	 * @return combineOrderCode 总订单编号  
	 */
	public String getCombineOrderCode() {
		return combineOrderCode;
	}
	

	/**  
	 * 总订单编号  
	 * @param combineOrderCode 总订单编号  
	 */
	public void setCombineOrderCode(String combineOrderCode) {
		this.combineOrderCode = combineOrderCode;
	}
	

	/**  
	 * 子订单编号  
	 * @return subOrderCode 子订单编号  
	 */
	public String getSubOrderCode() {
		return subOrderCode;
	}
	

	/**  
	 * 子订单编号  
	 * @param subOrderCode 子订单编号  
	 */
	public void setSubOrderCode(String subOrderCode) {
		this.subOrderCode = subOrderCode;
	}

	public Integer getMchtId() {
		return mchtId;
	}

	public void setMchtId(Integer mchtId) {
		this.mchtId = mchtId;
	}

	public String getMchtType() {
		return mchtType;
	}

	public void setMchtType(String mchtType) {
		this.mchtType = mchtType;
	}

	/**  
	 * 售后主状态  
	 * @return customerStatus 售后主状态  
	 */
	public String getCustomerStatus() {
		return customerStatus;
	}
	

	/**  
	 * 售后主状态  
	 * @param customerStatus 售后主状态  
	 */
	public void setCustomerStatus(String customerStatus) {
		this.customerStatus = customerStatus;
	}
	
	
}