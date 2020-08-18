package com.jf.entity.pay;
/** 加密信息参数
  * @author  chenwf: 
  * @date 创建时间：2018年3月15日 上午10:38:26 
  * @version 1.0 
  * @parameter  
  * @return  
*/
public class WxRefundResuletBackInfoModel {
	
	/**
	 * 微信订单号 
	 */
	private String transactionId;
	/**
	 * 商户订单号 
	 */
	private String outTradeNo;
	/**
	 * 微信退款单号 
	 */
	private String refundId;
	/**
	 * 商户退款单号
	 */
	private String outRefundNo;
	/**
	 * 订单金额
	 */
	private String totalfee;
	/**
	 * 应结订单金额
	 */
	private String settlementTotalFee;
	/**
	 * 申请退款金额 
	 */
	private String refundFee;
	/**
	 * 退款金额
	 */
	private String settlementRefundFee;
	/**
	 * 退款状态
	 */
	private String refundStatus;
	/**
	 * 退款成功时间
	 */
	private String successtime;
	/**
	 * 退款入账账户
	 */
	private String refundRecvAccout;
	/**
	 * 退款资金来源
	 */
	private String refundAccount;
	/**
	 * 退款发起来源
	 */
	private String refundRequestSource;
	/**  
	 * 微信订单号  
	 * @return transactionId 微信订单号  
	 */
	public String getTransactionId() {
		return transactionId;
	}
	
	/**  
	 * 微信订单号  
	 * @param transactionId 微信订单号  
	 */
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	
	/**  
	 * 商户订单号  
	 * @return outTradeNo 商户订单号  
	 */
	public String getOutTradeNo() {
		return outTradeNo;
	}
	
	/**  
	 * 商户订单号  
	 * @param outTradeNo 商户订单号  
	 */
	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}
	
	/**  
	 * 微信退款单号  
	 * @return refundId 微信退款单号  
	 */
	public String getRefundId() {
		return refundId;
	}
	
	/**  
	 * 微信退款单号  
	 * @param refundId 微信退款单号  
	 */
	public void setRefundId(String refundId) {
		this.refundId = refundId;
	}
	
	/**  
	 * 商户退款单号  
	 * @return outRefundNo 商户退款单号  
	 */
	public String getOutRefundNo() {
		return outRefundNo;
	}
	
	/**  
	 * 商户退款单号  
	 * @param outRefundNo 商户退款单号  
	 */
	public void setOutRefundNo(String outRefundNo) {
		this.outRefundNo = outRefundNo;
	}
	
	/**  
	 * 订单金额  
	 * @return totalfee 订单金额  
	 */
	public String getTotalfee() {
		return totalfee;
	}
	
	/**  
	 * 订单金额  
	 * @param totalfee 订单金额  
	 */
	public void setTotalfee(String totalfee) {
		this.totalfee = totalfee;
	}
	
	/**  
	 * 应结订单金额  
	 * @return settlementTotalFee 应结订单金额  
	 */
	public String getSettlementTotalFee() {
		return settlementTotalFee;
	}
	
	/**  
	 * 应结订单金额  
	 * @param settlementTotalFee 应结订单金额  
	 */
	public void setSettlementTotalFee(String settlementTotalFee) {
		this.settlementTotalFee = settlementTotalFee;
	}
	
	/**  
	 * 申请退款金额  
	 * @return refundFee 申请退款金额  
	 */
	public String getRefundFee() {
		return refundFee;
	}
	
	/**  
	 * 申请退款金额  
	 * @param refundFee 申请退款金额  
	 */
	public void setRefundFee(String refundFee) {
		this.refundFee = refundFee;
	}
	
	/**  
	 * 退款金额  
	 * @return settlementRefundFee 退款金额  
	 */
	public String getSettlementRefundFee() {
		return settlementRefundFee;
	}
	
	/**  
	 * 退款金额  
	 * @param settlementRefundFee 退款金额  
	 */
	public void setSettlementRefundFee(String settlementRefundFee) {
		this.settlementRefundFee = settlementRefundFee;
	}
	
	/**  
	 * 退款状态  
	 * @return refundStatus 退款状态  
	 */
	public String getRefundStatus() {
		return refundStatus;
	}
	
	/**  
	 * 退款状态  
	 * @param refundStatus 退款状态  
	 */
	public void setRefundStatus(String refundStatus) {
		this.refundStatus = refundStatus;
	}
	
	/**  
	 * 退款成功时间  
	 * @return successtime 退款成功时间  
	 */
	public String getSuccesstime() {
		return successtime;
	}
	
	/**  
	 * 退款成功时间  
	 * @param successtime 退款成功时间  
	 */
	public void setSuccesstime(String successtime) {
		this.successtime = successtime;
	}
	
	/**  
	 * 退款入账账户  
	 * @return refundRecvAccout 退款入账账户  
	 */
	public String getRefundRecvAccout() {
		return refundRecvAccout;
	}
	
	/**  
	 * 退款入账账户  
	 * @param refundRecvAccout 退款入账账户  
	 */
	public void setRefundRecvAccout(String refundRecvAccout) {
		this.refundRecvAccout = refundRecvAccout;
	}
	
	/**  
	 * 退款资金来源  
	 * @return refundAccount 退款资金来源  
	 */
	public String getRefundAccount() {
		return refundAccount;
	}
	
	/**  
	 * 退款资金来源  
	 * @param refundAccount 退款资金来源  
	 */
	public void setRefundAccount(String refundAccount) {
		this.refundAccount = refundAccount;
	}
	
	/**  
	 * 退款发起来源  
	 * @return refundRequestSource 退款发起来源  
	 */
	public String getRefundRequestSource() {
		return refundRequestSource;
	}
	
	/**  
	 * 退款发起来源  
	 * @param refundRequestSource 退款发起来源  
	 */
	public void setRefundRequestSource(String refundRequestSource) {
		this.refundRequestSource = refundRequestSource;
	}
}
