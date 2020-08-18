package com.jf.entity.pay;
/**
  * 支付宝参数实体类
  * @author  chenwf: 
  * @date 创建时间：2017年5月31日 下午6:23:51 
  * @version 1.0 
  * @parameter  
  * @return  
*/
public class AlipayModel {
	/**
	 * 支付宝分配给开发者的应用ID
	 */
	public String app_id;
	/**
	 * 接口名称
	 */
	public String method;
	/**
	 * 仅支持JSON
	 */
	public String format;
	/**
	 * 请求使用的编码格式，如utf-8,gbk,gb2312等
	 */
	public String charset;
	/**
	 * 商户生成签名字符串所使用的签名算法类型，目前支持RSA2和RSA，推荐使用RSA2
	 */
	public String sign_type;
	/**
	 * 商户请求参数的签名串
	 */
	public String sign;
	/**
	 * 发送请求的时间，格式"yyyy-MM-dd HH:mm:ss"
	 */
	public String timestamp;
	/**
	 * 调用的接口版本，固定为：1.0
	 */
	public String version;
	/**
	 * 支付宝服务器主动通知商户服务器里指定的页面http/https路径。建议商户使用https
	 */
	public String notify_url;
	/**
	 * 业务请求参数的集合
	 */
	public String biz_content;
	/**  
	 * 支付宝分配给开发者的应用ID  
	 * @return app_id 支付宝分配给开发者的应用ID  
	 */
	public String getApp_id() {
		return app_id;
	}
	
	/**  
	 * 支付宝分配给开发者的应用ID  
	 * @param app_id 支付宝分配给开发者的应用ID  
	 */
	public void setApp_id(String app_id) {
		this.app_id = app_id;
	}
	
	/**  
	 * 接口名称  
	 * @return method 接口名称  
	 */
	public String getMethod() {
		return method;
	}
	
	/**  
	 * 接口名称  
	 * @param method 接口名称  
	 */
	public void setMethod(String method) {
		this.method = method;
	}
	
	/**  
	 * 仅支持JSON  
	 * @return format 仅支持JSON  
	 */
	public String getFormat() {
		return format;
	}
	
	/**  
	 * 仅支持JSON  
	 * @param format 仅支持JSON  
	 */
	public void setFormat(String format) {
		this.format = format;
	}
	
	/**  
	 * 请求使用的编码格式，如utf-8gbkgb2312等  
	 * @return charset 请求使用的编码格式，如utf-8gbkgb2312等  
	 */
	public String getCharset() {
		return charset;
	}
	
	/**  
	 * 请求使用的编码格式，如utf-8gbkgb2312等  
	 * @param charset 请求使用的编码格式，如utf-8gbkgb2312等  
	 */
	public void setCharset(String charset) {
		this.charset = charset;
	}
	
	/**  
	 * 商户生成签名字符串所使用的签名算法类型，目前支持RSA2和RSA，推荐使用RSA2  
	 * @return sign_type 商户生成签名字符串所使用的签名算法类型，目前支持RSA2和RSA，推荐使用RSA2  
	 */
	public String getSign_type() {
		return sign_type;
	}
	
	/**  
	 * 商户生成签名字符串所使用的签名算法类型，目前支持RSA2和RSA，推荐使用RSA2  
	 * @param sign_type 商户生成签名字符串所使用的签名算法类型，目前支持RSA2和RSA，推荐使用RSA2  
	 */
	public void setSign_type(String sign_type) {
		this.sign_type = sign_type;
	}
	
	/**  
	 * 商户请求参数的签名串  
	 * @return sign 商户请求参数的签名串  
	 */
	public String getSign() {
		return sign;
	}
	
	/**  
	 * 商户请求参数的签名串  
	 * @param sign 商户请求参数的签名串  
	 */
	public void setSign(String sign) {
		this.sign = sign;
	}
	
	/**  
	 * 发送请求的时间，格式"yyyy-MM-ddHH:mm:ss"  
	 * @return timestamp 发送请求的时间，格式"yyyy-MM-ddHH:mm:ss"  
	 */
	public String getTimestamp() {
		return timestamp;
	}
	
	/**  
	 * 发送请求的时间，格式"yyyy-MM-ddHH:mm:ss"  
	 * @param timestamp 发送请求的时间，格式"yyyy-MM-ddHH:mm:ss"  
	 */
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	
	/**  
	 * 调用的接口版本，固定为：1.0  
	 * @return version 调用的接口版本，固定为：1.0  
	 */
	public String getVersion() {
		return version;
	}
	
	/**  
	 * 调用的接口版本，固定为：1.0  
	 * @param version 调用的接口版本，固定为：1.0  
	 */
	public void setVersion(String version) {
		this.version = version;
	}
	
	/**  
	 * 支付宝服务器主动通知商户服务器里指定的页面httphttps路径。建议商户使用https  
	 * @return notify_url 支付宝服务器主动通知商户服务器里指定的页面httphttps路径。建议商户使用https  
	 */
	public String getNotify_url() {
		return notify_url;
	}
	
	/**  
	 * 支付宝服务器主动通知商户服务器里指定的页面httphttps路径。建议商户使用https  
	 * @param notify_url 支付宝服务器主动通知商户服务器里指定的页面httphttps路径。建议商户使用https  
	 */
	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}
	
	/**  
	 * 业务请求参数的集合  
	 * @return biz_content 业务请求参数的集合  
	 */
	public String getBiz_content() {
		return biz_content;
	}
	
	/**  
	 * 业务请求参数的集合  
	 * @param biz_content 业务请求参数的集合  
	 */
	public void setBiz_content(String biz_content) {
		this.biz_content = biz_content;
	}
	
	
	
}
