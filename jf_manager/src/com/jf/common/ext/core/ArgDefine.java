package com.jf.common.ext.core;

/**
 * 头部参数定义
 * 
 * @author hdl
 *
 */
public interface ArgDefine {
	
	// 哪个客户端请求
	public static final String H_CLIENT_TYPE = "client-type"; // 10 Android 20 iOS
	
	// 客户端版本
	public static final String H_CLIENT_VERSION = "client-version"; // 1.0
	

	// 参照页
	public static final String H_REFERER = "referer";
	
	// mac
	public static final String H_MAC = "mac";

	// 网络类型
	public static final String H_NET_TYPE = "net-type";

}
