package com.jf.entity.pay;
/**
  * 微信退款请求参数 
  * @author  chenwf: 
  * @date 创建时间：2017年7月10日 下午3:51:21 
  * @version 1.0 
  * @parameter  
  * @return  
*/
public class WxRefundModel {
	/**
	 * 微信分配的公众账号ID（企业号corpid即为此appId）
	 */
	private String appid;
	/**
	 * 微信支付分配的商户号
	 */
	private String mch_id;
	/**
	 * 随机字符串，不长于32位
	 */
	private String nonce_str;
	/**
	 * 签名
	 */
	private String sign;
	/**
	 * 签名类型，目前支持HMAC-SHA256和MD5，默认为MD5
	 */
	private String sign_type;
	/**
	 * 微信生成的订单号，在支付通知中有返回
	 */
	private String transaction_id;
	/**
	 * 商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一。
	 */
	private String out_trade_no;
	/**
	 * 商户系统内部的退款单号，商户系统内部唯一，只能是数字、大小写字母_-|*@ ，同一退款单号多次请求只退一笔。
	 */
	private String out_refund_no;
	/**
	 * 订单总金额，单位为分，只能为整数
	 */
	private int total_fee;
	/**
	 * 退款总金额，订单总金额，单位为分，只能为整数
	 */
	private int refund_fee;
	/**
	 * 货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY
	 */
	private String refund_fee_type;
	/**
	 * 若商户传入，会在下发给用户的退款消息中体现退款原因
	 */
	private String refund_desc;
	/**
	 *	仅针对老资金流商户使用
	 *	REFUND_SOURCE_UNSETTLED_FUNDS---未结算资金退款（默认使用未结算资金退款）
	 *	REFUND_SOURCE_RECHARGE_FUNDS---可用余额退款
	 */
	private String refund_account;
	/**
	 * 异步接收微信支付退款结果通知的回调地址，通知URL必须为外网可访问的url，不允许带参数
     * 如果参数中传了notify_url，则商户平台上配置的回调地址将不会生效。
	 */
	private String notify_url;
	/**  
	 * 微信分配的公众账号ID（企业号corpid即为此appId）  
	 * @return appid 微信分配的公众账号ID（企业号corpid即为此appId）  
	 */
	public String getAppid() {
		return appid;
	}
	
	/**  
	 * 微信分配的公众账号ID（企业号corpid即为此appId）  
	 * @param appid 微信分配的公众账号ID（企业号corpid即为此appId）  
	 */
	public void setAppid(String appid) {
		this.appid = appid;
	}
	
	/**  
	 * 微信支付分配的商户号  
	 * @return mch_id 微信支付分配的商户号  
	 */
	public String getMch_id() {
		return mch_id;
	}
	
	/**  
	 * 微信支付分配的商户号  
	 * @param mch_id 微信支付分配的商户号  
	 */
	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}
	
	/**  
	 * 随机字符串，不长于32位  
	 * @return nonce_str 随机字符串，不长于32位  
	 */
	public String getNonce_str() {
		return nonce_str;
	}
	
	/**  
	 * 随机字符串，不长于32位  
	 * @param nonce_str 随机字符串，不长于32位  
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
	 * 签名类型，目前支持HMAC-SHA256和MD5，默认为MD5  
	 * @return sign_type 签名类型，目前支持HMAC-SHA256和MD5，默认为MD5  
	 */
	public String getSign_type() {
		return sign_type;
	}
	
	/**  
	 * 签名类型，目前支持HMAC-SHA256和MD5，默认为MD5  
	 * @param sign_type 签名类型，目前支持HMAC-SHA256和MD5，默认为MD5  
	 */
	public void setSign_type(String sign_type) {
		this.sign_type = sign_type;
	}
	
	/**  
	 * 微信生成的订单号，在支付通知中有返回  
	 * @return transaction_id 微信生成的订单号，在支付通知中有返回  
	 */
	public String getTransaction_id() {
		return transaction_id;
	}
	
	/**  
	 * 微信生成的订单号，在支付通知中有返回  
	 * @param transaction_id 微信生成的订单号，在支付通知中有返回  
	 */
	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}
	
	/**  
	 * 商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|@，且在同一个商户号下唯一。  
	 * @return out_trade_no 商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|@，且在同一个商户号下唯一。  
	 */
	public String getOut_trade_no() {
		return out_trade_no;
	}
	
	/**  
	 * 商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|@，且在同一个商户号下唯一。  
	 * @param out_trade_no 商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|@，且在同一个商户号下唯一。  
	 */
	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}
	
	/**  
	 * 商户系统内部的退款单号，商户系统内部唯一，只能是数字、大小写字母_-|@，同一退款单号多次请求只退一笔。  
	 * @return out_refund_no 商户系统内部的退款单号，商户系统内部唯一，只能是数字、大小写字母_-|@，同一退款单号多次请求只退一笔。  
	 */
	public String getOut_refund_no() {
		return out_refund_no;
	}
	
	/**  
	 * 商户系统内部的退款单号，商户系统内部唯一，只能是数字、大小写字母_-|@，同一退款单号多次请求只退一笔。  
	 * @param out_refund_no 商户系统内部的退款单号，商户系统内部唯一，只能是数字、大小写字母_-|@，同一退款单号多次请求只退一笔。  
	 */
	public void setOut_refund_no(String out_refund_no) {
		this.out_refund_no = out_refund_no;
	}
	
	/**  
	 * 订单总金额，单位为分，只能为整数  
	 * @return total_fee 订单总金额，单位为分，只能为整数  
	 */
	public int getTotal_fee() {
		return total_fee;
	}
	
	/**  
	 * 订单总金额，单位为分，只能为整数  
	 * @param total_fee 订单总金额，单位为分，只能为整数  
	 */
	public void setTotal_fee(int total_fee) {
		this.total_fee = total_fee;
	}
	
	/**  
	 * 退款总金额，订单总金额，单位为分，只能为整数  
	 * @return refund_fee 退款总金额，订单总金额，单位为分，只能为整数  
	 */
	public int getRefund_fee() {
		return refund_fee;
	}
	
	/**  
	 * 退款总金额，订单总金额，单位为分，只能为整数  
	 * @param refund_fee 退款总金额，订单总金额，单位为分，只能为整数  
	 */
	public void setRefund_fee(int refund_fee) {
		this.refund_fee = refund_fee;
	}
	
	/**  
	 * 货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY  
	 * @return refund_fee_type 货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY  
	 */
	public String getRefund_fee_type() {
		return refund_fee_type;
	}
	
	/**  
	 * 货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY  
	 * @param refund_fee_type 货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY  
	 */
	public void setRefund_fee_type(String refund_fee_type) {
		this.refund_fee_type = refund_fee_type;
	}
	
	/**  
	 * 若商户传入，会在下发给用户的退款消息中体现退款原因  
	 * @return refund_desc 若商户传入，会在下发给用户的退款消息中体现退款原因  
	 */
	public String getRefund_desc() {
		return refund_desc;
	}
	
	/**  
	 * 若商户传入，会在下发给用户的退款消息中体现退款原因  
	 * @param refund_desc 若商户传入，会在下发给用户的退款消息中体现退款原因  
	 */
	public void setRefund_desc(String refund_desc) {
		this.refund_desc = refund_desc;
	}
	
	/**  
	 * 仅针对老资金流商户使用REFUND_SOURCE_UNSETTLED_FUNDS---未结算资金退款（默认使用未结算资金退款）REFUND_SOURCE_RECHARGE_FUNDS---可用余额退款  
	 * @return refund_account 仅针对老资金流商户使用REFUND_SOURCE_UNSETTLED_FUNDS---未结算资金退款（默认使用未结算资金退款）REFUND_SOURCE_RECHARGE_FUNDS---可用余额退款  
	 */
	public String getRefund_account() {
		return refund_account;
	}
	
	/**  
	 * 仅针对老资金流商户使用REFUND_SOURCE_UNSETTLED_FUNDS---未结算资金退款（默认使用未结算资金退款）REFUND_SOURCE_RECHARGE_FUNDS---可用余额退款  
	 * @param refund_account 仅针对老资金流商户使用REFUND_SOURCE_UNSETTLED_FUNDS---未结算资金退款（默认使用未结算资金退款）REFUND_SOURCE_RECHARGE_FUNDS---可用余额退款  
	 */
	public void setRefund_account(String refund_account) {
		this.refund_account = refund_account;
	}

	/**  
	 * 异步接收微信支付退款结果通知的回调地址，通知URL必须为外网可访问的url，不允许带参数如果参数中传了notify_url，则商户平台上配置的回调地址将不会生效。  
	 * @return notify_url 异步接收微信支付退款结果通知的回调地址，通知URL必须为外网可访问的url，不允许带参数如果参数中传了notify_url，则商户平台上配置的回调地址将不会生效。  
	 */
	public String getNotify_url() {
		return notify_url;
	}
	

	/**  
	 * 异步接收微信支付退款结果通知的回调地址，通知URL必须为外网可访问的url，不允许带参数如果参数中传了notify_url，则商户平台上配置的回调地址将不会生效。  
	 * @param notify_url 异步接收微信支付退款结果通知的回调地址，通知URL必须为外网可访问的url，不允许带参数如果参数中传了notify_url，则商户平台上配置的回调地址将不会生效。  
	 */
	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}
}
