package com.jf.entity.pay;
/**
  * 微信红包请求参数 
  * @author  chenwf: 
  * @date 创建时间：2018年6月11日 下午4:44:43 
  * @version 1.0 
  * @parameter  
  * @return  
*/
public class WxRedPacketReq {
	/**
	 * Required=true
	 * 随机字符串，不长于32位
	 */
	private String nonce_str;
	/**
	 * Required=true
	 * 签名
	 */
	private String sign;
	/**
	 * Required=true
	 * 商户订单号
	 */
	private String mch_billno;
	/**
	 * Required=true
	 * 微信支付分配的商户号
	 */
	private String mch_id;
	/**
	 * Required=true
	 * 微信分配的公众账号ID（企业号corpid即为此appId）。在微信开放平台（open.weixin.qq.com）申请的移动应用appid无法使用该接口。
	 */
	private String wxappid;
	/**
	 * Required=true
	 * 红包发送者名称
	 */
	private String send_name;
	/**
	 * Required=true
	 * 接受红包的用户openid 
	 */
	private String re_openid;
	/**
	 * Required=true
	 * 付款金额，单位分
	 */
	private Integer total_amount;
	/**
	 * Required=true
	 * 红包发放总人数 total_num=1
	 */
	private Integer total_num;
	/**
	 * Required=true
	 * 红包祝福语
	 */
	private String wishing;
	/**
	 * Required=true
	 * 调用接口的机器Ip地址
	 */
	private String client_ip;
	/**
	 * Required=true
	 * 活动名称
	 */
	private String act_name;
	/**
	 * Required=true
	 * 备注信息 猜越多得越多，快来抢！
	 */
	private String remark;
	/**
	 * 发放红包使用场景，红包金额大于200或者小于1元时必传
	 *
	 *	PRODUCT_1:商品促销 
	 *	
	 *	PRODUCT_2:抽奖 
	 *	
	 *	PRODUCT_3:虚拟物品兑奖  
	 *	
	 *	PRODUCT_4:企业内部福利 
	 *	
	 *	PRODUCT_5:渠道分润 
	 *	
	 *	PRODUCT_6:保险回馈 
	 *	
	 *	PRODUCT_7:彩票派奖 
	 *	
	 *	PRODUCT_8:税务刮奖
	 *
	 */
	private String scene_id;
	/**
	 * 活动信息
	 */
	private String risk_info;
	/**
	 * 资金授权商户号服务商替特约商户发放时使用
	 */
	private String consume_mch_id;
	/**
	 * MCHT:通过商户订单号获取红包信息。(红包查询记录使用)
	 */
	private String bill_type;
	/**  
	 * Required=true随机字符串，不长于32位  
	 * @return nonce_str Required=true随机字符串，不长于32位  
	 */
	public String getNonce_str() {
		return nonce_str;
	}
	
	/**  
	 * Required=true随机字符串，不长于32位  
	 * @param nonce_str Required=true随机字符串，不长于32位  
	 */
	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}
	
	/**  
	 * Required=true签名  
	 * @return sign Required=true签名  
	 */
	public String getSign() {
		return sign;
	}
	
	/**  
	 * Required=true签名  
	 * @param sign Required=true签名  
	 */
	public void setSign(String sign) {
		this.sign = sign;
	}
	
	/**  
	 * Required=true商户订单号  
	 * @return mch_billno Required=true商户订单号  
	 */
	public String getMch_billno() {
		return mch_billno;
	}
	
	/**  
	 * Required=true商户订单号  
	 * @param mch_billno Required=true商户订单号  
	 */
	public void setMch_billno(String mch_billno) {
		this.mch_billno = mch_billno;
	}
	
	/**  
	 * Required=true微信支付分配的商户号  
	 * @return mch_id Required=true微信支付分配的商户号  
	 */
	public String getMch_id() {
		return mch_id;
	}
	
	/**  
	 * Required=true微信支付分配的商户号  
	 * @param mch_id Required=true微信支付分配的商户号  
	 */
	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}
	
	/**  
	 * Required=true微信分配的公众账号ID（企业号corpid即为此appId）。在微信开放平台（open.weixin.qq.com）申请的移动应用appid无法使用该接口。  
	 * @return wxappid Required=true微信分配的公众账号ID（企业号corpid即为此appId）。在微信开放平台（open.weixin.qq.com）申请的移动应用appid无法使用该接口。  
	 */
	public String getWxappid() {
		return wxappid;
	}
	
	/**  
	 * Required=true微信分配的公众账号ID（企业号corpid即为此appId）。在微信开放平台（open.weixin.qq.com）申请的移动应用appid无法使用该接口。  
	 * @param wxappid Required=true微信分配的公众账号ID（企业号corpid即为此appId）。在微信开放平台（open.weixin.qq.com）申请的移动应用appid无法使用该接口。  
	 */
	public void setWxappid(String wxappid) {
		this.wxappid = wxappid;
	}
	
	/**  
	 * Required=true红包发送者名称  
	 * @return send_name Required=true红包发送者名称  
	 */
	public String getSend_name() {
		return send_name;
	}
	
	/**  
	 * Required=true红包发送者名称  
	 * @param send_name Required=true红包发送者名称  
	 */
	public void setSend_name(String send_name) {
		this.send_name = send_name;
	}
	
	/**  
	 * Required=true接受红包的用户openid  
	 * @return re_openid Required=true接受红包的用户openid  
	 */
	public String getRe_openid() {
		return re_openid;
	}
	
	/**  
	 * Required=true接受红包的用户openid  
	 * @param re_openid Required=true接受红包的用户openid  
	 */
	public void setRe_openid(String re_openid) {
		this.re_openid = re_openid;
	}
	
	/**  
	 * Required=true付款金额，单位分  
	 * @return total_amount Required=true付款金额，单位分  
	 */
	public Integer getTotal_amount() {
		return total_amount;
	}
	
	/**  
	 * Required=true付款金额，单位分  
	 * @param total_amount Required=true付款金额，单位分  
	 */
	public void setTotal_amount(Integer total_amount) {
		this.total_amount = total_amount;
	}
	
	/**  
	 * Required=true红包发放总人数total_num=1  
	 * @return total_num Required=true红包发放总人数total_num=1  
	 */
	public Integer getTotal_num() {
		return total_num;
	}
	
	/**  
	 * Required=true红包发放总人数total_num=1  
	 * @param total_num Required=true红包发放总人数total_num=1  
	 */
	public void setTotal_num(Integer total_num) {
		this.total_num = total_num;
	}
	
	/**  
	 * Required=true红包祝福语  
	 * @return wishing Required=true红包祝福语  
	 */
	public String getWishing() {
		return wishing;
	}
	
	/**  
	 * Required=true红包祝福语  
	 * @param wishing Required=true红包祝福语  
	 */
	public void setWishing(String wishing) {
		this.wishing = wishing;
	}
	
	/**  
	 * Required=true调用接口的机器Ip地址  
	 * @return client_ip Required=true调用接口的机器Ip地址  
	 */
	public String getClient_ip() {
		return client_ip;
	}
	
	/**  
	 * Required=true调用接口的机器Ip地址  
	 * @param client_ip Required=true调用接口的机器Ip地址  
	 */
	public void setClient_ip(String client_ip) {
		this.client_ip = client_ip;
	}
	
	/**  
	 * Required=true活动名称  
	 * @return act_name Required=true活动名称  
	 */
	public String getAct_name() {
		return act_name;
	}
	
	/**  
	 * Required=true活动名称  
	 * @param act_name Required=true活动名称  
	 */
	public void setAct_name(String act_name) {
		this.act_name = act_name;
	}
	
	/**  
	 * Required=true备注信息猜越多得越多，快来抢！  
	 * @return remark Required=true备注信息猜越多得越多，快来抢！  
	 */
	public String getRemark() {
		return remark;
	}
	
	/**  
	 * Required=true备注信息猜越多得越多，快来抢！  
	 * @param remark Required=true备注信息猜越多得越多，快来抢！  
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	/**  
	 * 发放红包使用场景，红包金额大于200或者小于1元时必传PRODUCT_1:商品促销PRODUCT_2:抽奖PRODUCT_3:虚拟物品兑奖PRODUCT_4:企业内部福利PRODUCT_5:渠道分润PRODUCT_6:保险回馈PRODUCT_7:彩票派奖PRODUCT_8:税务刮奖  
	 * @return scene_id 发放红包使用场景，红包金额大于200或者小于1元时必传PRODUCT_1:商品促销PRODUCT_2:抽奖PRODUCT_3:虚拟物品兑奖PRODUCT_4:企业内部福利PRODUCT_5:渠道分润PRODUCT_6:保险回馈PRODUCT_7:彩票派奖PRODUCT_8:税务刮奖  
	 */
	public String getScene_id() {
		return scene_id;
	}
	
	/**  
	 * 发放红包使用场景，红包金额大于200或者小于1元时必传PRODUCT_1:商品促销PRODUCT_2:抽奖PRODUCT_3:虚拟物品兑奖PRODUCT_4:企业内部福利PRODUCT_5:渠道分润PRODUCT_6:保险回馈PRODUCT_7:彩票派奖PRODUCT_8:税务刮奖  
	 * @param scene_id 发放红包使用场景，红包金额大于200或者小于1元时必传PRODUCT_1:商品促销PRODUCT_2:抽奖PRODUCT_3:虚拟物品兑奖PRODUCT_4:企业内部福利PRODUCT_5:渠道分润PRODUCT_6:保险回馈PRODUCT_7:彩票派奖PRODUCT_8:税务刮奖  
	 */
	public void setScene_id(String scene_id) {
		this.scene_id = scene_id;
	}
	
	/**  
	 * 活动信息  
	 * @return risk_info 活动信息  
	 */
	public String getRisk_info() {
		return risk_info;
	}
	
	/**  
	 * 活动信息  
	 * @param risk_info 活动信息  
	 */
	public void setRisk_info(String risk_info) {
		this.risk_info = risk_info;
	}
	
	/**  
	 * 资金授权商户号服务商替特约商户发放时使用  
	 * @return consume_mch_id 资金授权商户号服务商替特约商户发放时使用  
	 */
	public String getConsume_mch_id() {
		return consume_mch_id;
	}
	
	/**  
	 * 资金授权商户号服务商替特约商户发放时使用  
	 * @param consume_mch_id 资金授权商户号服务商替特约商户发放时使用  
	 */
	public void setConsume_mch_id(String consume_mch_id) {
		this.consume_mch_id = consume_mch_id;
	}

	/**  
	 * MCHT:通过商户订单号获取红包信息。(红包查询记录使用)  
	 * @return bill_type MCHT:通过商户订单号获取红包信息。(红包查询记录使用)  
	 */
	public String getBill_type() {
		return bill_type;
	}
	

	/**  
	 * MCHT:通过商户订单号获取红包信息。(红包查询记录使用)  
	 * @param bill_type MCHT:通过商户订单号获取红包信息。(红包查询记录使用)  
	 */
	public void setBill_type(String bill_type) {
		this.bill_type = bill_type;
	}
	
	
}
