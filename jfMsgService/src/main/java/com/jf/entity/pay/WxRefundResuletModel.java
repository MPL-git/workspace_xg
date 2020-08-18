package com.jf.entity.pay;
/**
  * 微信退款返回参数 
  * @author  chenwf: 
  * @date 创建时间：2017年7月10日 下午3:58:02 
  * @version 1.0 
  * @parameter  
  * @return  
*/
public class WxRefundResuletModel {
	/**
	 * 返回状态码 SUCCESS/FAIL
	 */
	private String return_code;
	/**
	 * 返回信息
	 */
	private String return_msg;
	/**
	 * 业务结果 SUCCESS/FAIL
	 */
	private String result_code;
	/**
	 * 错误代码
	 */
	private String err_code;
	/**
	 * 错误代码描述
	 */
	private String err_code_des;
	/**
	 * 公众账号ID
	 */
	private String appid;
	/**
	 * 商户号
	 */
	private String mch_id;
	/**
	 * 随机字符串
	 */
	private String nonce_str;
	/**
	 * 签名
	 */
	private String sign;
	/**
	 * 微信订单号
	 */
	private String transaction_id;
	/**
	 * 商户订单号
	 */
	private String out_trade_no;
	/**
	 * 商户退款单号
	 */
	private String out_refund_no;
	/**
	 * 微信退款单号
	 */
	private String refund_id;
	/**
	 * 退款金额
	 */
	private String refund_fee;
	/**
	 * 应结退款金额
	 */
	private String settlement_refund_fee;
	/**
	 * 标价金额
	 */
	private String total_fee;
	/**
	 * 应结订单金额 
	 */
	private String settlement_total_fee;
	/**
	 * 标价币种
	 */
	private String fee_type;
	/**
	 * 现金支付金额
	 */
	private String cash_fee;
	/**
	 * 现金支付币种
	 */
	private String cash_fee_type;
	/**
	 * 现金退款金额
	 */
	private String cash_refund_fee;
	/**
	 * 代金券类型 
	 */
	private String coupon_type_$n;
	/**
	 * 代金券退款总金额
	 */
	private String coupon_refund_fee;
	/**
	 * 单个代金券退款金额
	 */
	private String coupon_refund_fee_$n;
	/**
	 * 退款代金券使用数量
	 */
	private String coupon_refund_count;
	/**
	 * 退款代金券ID
	 */
	private String coupon_refund_id_$n;
	/**  
	 * 业务结果SUCCESSFAIL  
	 * @return result_code 业务结果SUCCESSFAIL  
	 */
	public String getResult_code() {
		return result_code;
	}
	
	/**  
	 * 业务结果SUCCESSFAIL  
	 * @param result_code 业务结果SUCCESSFAIL  
	 */
	public void setResult_code(String result_code) {
		this.result_code = result_code;
	}
	
	/**  
	 * 错误代码  
	 * @return err_code 错误代码  
	 */
	public String getErr_code() {
		return err_code;
	}
	
	/**  
	 * 错误代码  
	 * @param err_code 错误代码  
	 */
	public void setErr_code(String err_code) {
		this.err_code = err_code;
	}
	
	/**  
	 * 错误代码描述  
	 * @return err_code_des 错误代码描述  
	 */
	public String getErr_code_des() {
		return err_code_des;
	}
	
	/**  
	 * 错误代码描述  
	 * @param err_code_des 错误代码描述  
	 */
	public void setErr_code_des(String err_code_des) {
		this.err_code_des = err_code_des;
	}
	
	/**  
	 * 公众账号ID  
	 * @return appid 公众账号ID  
	 */
	public String getAppid() {
		return appid;
	}
	
	/**  
	 * 公众账号ID  
	 * @param appid 公众账号ID  
	 */
	public void setAppid(String appid) {
		this.appid = appid;
	}
	
	/**  
	 * 商户号  
	 * @return mch_id 商户号  
	 */
	public String getMch_id() {
		return mch_id;
	}
	
	/**  
	 * 商户号  
	 * @param mch_id 商户号  
	 */
	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}
	
	/**  
	 * 随机字符串  
	 * @return nonce_str 随机字符串  
	 */
	public String getNonce_str() {
		return nonce_str;
	}
	
	/**  
	 * 随机字符串  
	 * @param nonce_str 随机字符串  
	 */
	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}
	
	/**  
	 * 签名  
	 * @return sign 签名  
	 */
	public String getSign() {
		return sign;
	}
	
	/**  
	 * 签名  
	 * @param sign 签名  
	 */
	public void setSign(String sign) {
		this.sign = sign;
	}
	
	/**  
	 * 微信订单号  
	 * @return transaction_id 微信订单号  
	 */
	public String getTransaction_id() {
		return transaction_id;
	}
	
	/**  
	 * 微信订单号  
	 * @param transaction_id 微信订单号  
	 */
	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}
	
	/**  
	 * 商户订单号  
	 * @return out_trade_no 商户订单号  
	 */
	public String getOut_trade_no() {
		return out_trade_no;
	}
	
	/**  
	 * 商户订单号  
	 * @param out_trade_no 商户订单号  
	 */
	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}
	
	/**  
	 * 商户退款单号  
	 * @return out_refund_no 商户退款单号  
	 */
	public String getOut_refund_no() {
		return out_refund_no;
	}
	
	/**  
	 * 商户退款单号  
	 * @param out_refund_no 商户退款单号  
	 */
	public void setOut_refund_no(String out_refund_no) {
		this.out_refund_no = out_refund_no;
	}
	
	/**  
	 * 微信退款单号  
	 * @return refund_id 微信退款单号  
	 */
	public String getRefund_id() {
		return refund_id;
	}
	
	/**  
	 * 微信退款单号  
	 * @param refund_id 微信退款单号  
	 */
	public void setRefund_id(String refund_id) {
		this.refund_id = refund_id;
	}
	
	/**  
	 * 退款金额  
	 * @return refund_fee 退款金额  
	 */
	public String getRefund_fee() {
		return refund_fee;
	}
	
	/**  
	 * 退款金额  
	 * @param refund_fee 退款金额  
	 */
	public void setRefund_fee(String refund_fee) {
		this.refund_fee = refund_fee;
	}
	
	/**  
	 * 应结退款金额  
	 * @return settlement_refund_fee 应结退款金额  
	 */
	public String getSettlement_refund_fee() {
		return settlement_refund_fee;
	}
	
	/**  
	 * 应结退款金额  
	 * @param settlement_refund_fee 应结退款金额  
	 */
	public void setSettlement_refund_fee(String settlement_refund_fee) {
		this.settlement_refund_fee = settlement_refund_fee;
	}
	
	/**  
	 * 标价金额  
	 * @return total_fee 标价金额  
	 */
	public String getTotal_fee() {
		return total_fee;
	}
	
	/**  
	 * 标价金额  
	 * @param total_fee 标价金额  
	 */
	public void setTotal_fee(String total_fee) {
		this.total_fee = total_fee;
	}
	
	/**  
	 * 应结订单金额  
	 * @return settlement_total_fee 应结订单金额  
	 */
	public String getSettlement_total_fee() {
		return settlement_total_fee;
	}
	
	/**  
	 * 应结订单金额  
	 * @param settlement_total_fee 应结订单金额  
	 */
	public void setSettlement_total_fee(String settlement_total_fee) {
		this.settlement_total_fee = settlement_total_fee;
	}
	
	/**  
	 * 标价币种  
	 * @return fee_type 标价币种  
	 */
	public String getFee_type() {
		return fee_type;
	}
	
	/**  
	 * 标价币种  
	 * @param fee_type 标价币种  
	 */
	public void setFee_type(String fee_type) {
		this.fee_type = fee_type;
	}
	
	/**  
	 * 现金支付金额  
	 * @return cash_fee 现金支付金额  
	 */
	public String getCash_fee() {
		return cash_fee;
	}
	
	/**  
	 * 现金支付金额  
	 * @param cash_fee 现金支付金额  
	 */
	public void setCash_fee(String cash_fee) {
		this.cash_fee = cash_fee;
	}
	
	/**  
	 * 现金支付币种  
	 * @return cash_fee_type 现金支付币种  
	 */
	public String getCash_fee_type() {
		return cash_fee_type;
	}
	
	/**  
	 * 现金支付币种  
	 * @param cash_fee_type 现金支付币种  
	 */
	public void setCash_fee_type(String cash_fee_type) {
		this.cash_fee_type = cash_fee_type;
	}
	
	/**  
	 * 现金退款金额  
	 * @return cash_refund_fee 现金退款金额  
	 */
	public String getCash_refund_fee() {
		return cash_refund_fee;
	}
	
	/**  
	 * 现金退款金额  
	 * @param cash_refund_fee 现金退款金额  
	 */
	public void setCash_refund_fee(String cash_refund_fee) {
		this.cash_refund_fee = cash_refund_fee;
	}
	
	/**  
	 * coupon_type_$n  
	 * @return coupon_type_$n coupon_type_$n  
	 */
	public String getCoupon_type_$n() {
		return coupon_type_$n;
	}
	
	/**  
	 * coupon_type_$n  
	 * @param coupon_type_$n coupon_type_$n  
	 */
	public void setCoupon_type_$n(String coupon_type_$n) {
		this.coupon_type_$n = coupon_type_$n;
	}
	
	/**  
	 * 代金券退款总金额  
	 * @return coupon_refund_fee 代金券退款总金额  
	 */
	public String getCoupon_refund_fee() {
		return coupon_refund_fee;
	}
	
	/**  
	 * 代金券退款总金额  
	 * @param coupon_refund_fee 代金券退款总金额  
	 */
	public void setCoupon_refund_fee(String coupon_refund_fee) {
		this.coupon_refund_fee = coupon_refund_fee;
	}
	
	/**  
	 * coupon_refund_fee_$n  
	 * @return coupon_refund_fee_$n coupon_refund_fee_$n  
	 */
	public String getCoupon_refund_fee_$n() {
		return coupon_refund_fee_$n;
	}
	
	/**  
	 * coupon_refund_fee_$n  
	 * @param coupon_refund_fee_$n coupon_refund_fee_$n  
	 */
	public void setCoupon_refund_fee_$n(String coupon_refund_fee_$n) {
		this.coupon_refund_fee_$n = coupon_refund_fee_$n;
	}
	
	/**  
	 * 退款代金券使用数量  
	 * @return coupon_refund_count 退款代金券使用数量  
	 */
	public String getCoupon_refund_count() {
		return coupon_refund_count;
	}
	
	/**  
	 * 退款代金券使用数量  
	 * @param coupon_refund_count 退款代金券使用数量  
	 */
	public void setCoupon_refund_count(String coupon_refund_count) {
		this.coupon_refund_count = coupon_refund_count;
	}
	
	/**  
	 * coupon_refund_id_$n  
	 * @return coupon_refund_id_$n coupon_refund_id_$n  
	 */
	public String getCoupon_refund_id_$n() {
		return coupon_refund_id_$n;
	}
	
	/**  
	 * coupon_refund_id_$n  
	 * @param coupon_refund_id_$n coupon_refund_id_$n  
	 */
	public void setCoupon_refund_id_$n(String coupon_refund_id_$n) {
		this.coupon_refund_id_$n = coupon_refund_id_$n;
	}

	/**  
	 * 返回状态码SUCCESSFAIL  
	 * @return return_code 返回状态码SUCCESSFAIL  
	 */
	public String getReturn_code() {
		return return_code;
	}
	

	/**  
	 * 返回状态码SUCCESSFAIL  
	 * @param return_code 返回状态码SUCCESSFAIL  
	 */
	public void setReturn_code(String return_code) {
		this.return_code = return_code;
	}
	

	/**  
	 * 返回信息  
	 * @return return_msg 返回信息  
	 */
	public String getReturn_msg() {
		return return_msg;
	}
	

	/**  
	 * 返回信息  
	 * @param return_msg 返回信息  
	 */
	public void setReturn_msg(String return_msg) {
		this.return_msg = return_msg;
	}
	
	
}
