package com.jf.entity.pay;
/**
  * 微信退款回调请求参数 
  * @author  chenwf: 
  * @date 创建时间：2018年3月15日 上午10:22:23 
  * @version 1.0 
  * @parameter  
  * @return  
*/
public class WxRefundResuletBackModel {
	/**
	 * 返回状态码
	 */
	private String returnCode;
	/**
	 * 返回信息
	 */
	private String returnMsg;
	/**
	 * appid应用ID 
	 */
	private String appid;
	/**
	 * 退款的商户号 
	 */
	private String mchId;
	/**
	 * 随机字符串
	 */
	private String nonce_str;
	/**
	 * 加密信息 
	 */
	private String req_info;
	/**  
	 * 返回状态码  
	 * @return returnCode 返回状态码  
	 */
	public String getReturnCode() {
		return returnCode;
	}
	
	/**  
	 * 返回状态码  
	 * @param returnCode 返回状态码  
	 */
	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}
	
	/**  
	 * 返回信息  
	 * @return returnMsg 返回信息  
	 */
	public String getReturnMsg() {
		return returnMsg;
	}
	
	/**  
	 * 返回信息  
	 * @param returnMsg 返回信息  
	 */
	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
	}
	
	/**  
	 * appid应用ID  
	 * @return appid appid应用ID  
	 */
	public String getAppid() {
		return appid;
	}
	
	/**  
	 * appid应用ID  
	 * @param appid appid应用ID  
	 */
	public void setAppid(String appid) {
		this.appid = appid;
	}
	
	/**  
	 * 退款的商户号  
	 * @return mchId 退款的商户号  
	 */
	public String getMchId() {
		return mchId;
	}
	
	/**  
	 * 退款的商户号  
	 * @param mchId 退款的商户号  
	 */
	public void setMchId(String mchId) {
		this.mchId = mchId;
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
	 * 加密信息  
	 * @return req_info 加密信息  
	 */
	public String getReq_info() {
		return req_info;
	}
	
	/**  
	 * 加密信息  
	 * @param req_info 加密信息  
	 */
	public void setReq_info(String req_info) {
		this.req_info = req_info;
	}
	
}
