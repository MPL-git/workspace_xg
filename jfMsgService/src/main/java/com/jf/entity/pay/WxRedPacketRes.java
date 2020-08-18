package com.jf.entity.pay;
/**
  * @author  chenwf: 
  * @date 创建时间：2018年6月12日 上午10:56:31 
  * @version 1.0 
  * @parameter  
  * @return  
*/
public class WxRedPacketRes {
	/**
	 * SUCCESS/FAIL此字段是通信标识，非交易标识，交易是否成功需要查看result_code来判断
	 */
	private String return_code;
	/**
	 * 返回信息，如非空，为错误原因 
	 */
	private String return_msg;
	/**
	 * 
	 */
	private String sign;
	/**
	 * SUCCESS/FAIL
	 */
	private String result_code;
	/**
	 * 错误码信息
	 */
	private String err_code;
	/**
	 * 商户订单号（每个订单号必须唯一）组成：mch_id+yyyymmdd+10位一天内不能重复的数字
	 */
	private String err_code_des;
	/**
	 * 微信支付分配的商户号
	 */
	private String mch_billno;
	/**
	 * 
	 */
	private String mch_id;
	/**
	 * 
	 */
	private String wxappid;
	/**
	 * 接受收红包的用户 用户在wxappid下的openid
	 */
	private String re_openid;
	/**
	 * 付款金额，单位分
	 */
	private Integer total_amount;
	/**
	 * 红包订单的微信单号
	 */
	private String send_listid;
	/**  
	 * SUCCESSFAIL此字段是通信标识，非交易标识，交易是否成功需要查看result_code来判断  
	 * @return return_code SUCCESSFAIL此字段是通信标识，非交易标识，交易是否成功需要查看result_code来判断  
	 */
	public String getReturn_code() {
		return return_code;
	}
	
	/**  
	 * SUCCESSFAIL此字段是通信标识，非交易标识，交易是否成功需要查看result_code来判断  
	 * @param return_code SUCCESSFAIL此字段是通信标识，非交易标识，交易是否成功需要查看result_code来判断  
	 */
	public void setReturn_code(String return_code) {
		this.return_code = return_code;
	}
	
	/**  
	 * 返回信息，如非空，为错误原因  
	 * @return return_msg 返回信息，如非空，为错误原因  
	 */
	public String getReturn_msg() {
		return return_msg;
	}
	
	/**  
	 * 返回信息，如非空，为错误原因  
	 * @param return_msg 返回信息，如非空，为错误原因  
	 */
	public void setReturn_msg(String return_msg) {
		this.return_msg = return_msg;
	}
	
	/**  
	 *   
	 * @return sign   
	 */
	public String getSign() {
		return sign;
	}
	
	/**  
	 *   
	 * @param sign   
	 */
	public void setSign(String sign) {
		this.sign = sign;
	}
	
	/**  
	 * SUCCESSFAIL  
	 * @return result_code SUCCESSFAIL  
	 */
	public String getResult_code() {
		return result_code;
	}
	
	/**  
	 * SUCCESSFAIL  
	 * @param result_code SUCCESSFAIL  
	 */
	public void setResult_code(String result_code) {
		this.result_code = result_code;
	}
	
	/**  
	 * 错误码信息  
	 * @return err_code 错误码信息  
	 */
	public String getErr_code() {
		return err_code;
	}
	
	/**  
	 * 错误码信息  
	 * @param err_code 错误码信息  
	 */
	public void setErr_code(String err_code) {
		this.err_code = err_code;
	}
	
	/**  
	 * 商户订单号（每个订单号必须唯一）组成：mch_id+yyyymmdd+10位一天内不能重复的数字  
	 * @return err_code_des 商户订单号（每个订单号必须唯一）组成：mch_id+yyyymmdd+10位一天内不能重复的数字  
	 */
	public String getErr_code_des() {
		return err_code_des;
	}
	
	/**  
	 * 商户订单号（每个订单号必须唯一）组成：mch_id+yyyymmdd+10位一天内不能重复的数字  
	 * @param err_code_des 商户订单号（每个订单号必须唯一）组成：mch_id+yyyymmdd+10位一天内不能重复的数字  
	 */
	public void setErr_code_des(String err_code_des) {
		this.err_code_des = err_code_des;
	}
	
	/**  
	 * 微信支付分配的商户号  
	 * @return mch_billno 微信支付分配的商户号  
	 */
	public String getMch_billno() {
		return mch_billno;
	}
	
	/**  
	 * 微信支付分配的商户号  
	 * @param mch_billno 微信支付分配的商户号  
	 */
	public void setMch_billno(String mch_billno) {
		this.mch_billno = mch_billno;
	}
	
	/**  
	 *   
	 * @return mch_id   
	 */
	public String getMch_id() {
		return mch_id;
	}
	
	/**  
	 *   
	 * @param mch_id   
	 */
	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}
	
	/**  
	 * 接受收红包的用户用户在wxappid下的openid  
	 * @return re_openid 接受收红包的用户用户在wxappid下的openid  
	 */
	public String getRe_openid() {
		return re_openid;
	}
	
	/**  
	 * 接受收红包的用户用户在wxappid下的openid  
	 * @param re_openid 接受收红包的用户用户在wxappid下的openid  
	 */
	public void setRe_openid(String re_openid) {
		this.re_openid = re_openid;
	}
	
	/**  
	 * 付款金额，单位分  
	 * @return total_amount 付款金额，单位分  
	 */
	public Integer getTotal_amount() {
		return total_amount;
	}
	
	/**  
	 * 付款金额，单位分  
	 * @param total_amount 付款金额，单位分  
	 */
	public void setTotal_amount(Integer total_amount) {
		this.total_amount = total_amount;
	}
	
	/**  
	 * 红包订单的微信单号  
	 * @return send_listid 红包订单的微信单号  
	 */
	public String getSend_listid() {
		return send_listid;
	}
	
	/**  
	 * 红包订单的微信单号  
	 * @param send_listid 红包订单的微信单号  
	 */
	public void setSend_listid(String send_listid) {
		this.send_listid = send_listid;
	}

	/**  
	 *   
	 * @return wxappid   
	 */
	public String getWxappid() {
		return wxappid;
	}
	

	/**  
	 *   
	 * @param wxappid   
	 */
	public void setWxappid(String wxappid) {
		this.wxappid = wxappid;
	}
	
	
}
