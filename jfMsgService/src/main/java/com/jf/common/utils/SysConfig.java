package com.jf.common.utils;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.PropertiesConfiguration;

public class SysConfig {
 public static String  signatureKey;
 public static String  wxServerUrl;
 public static List<String> permitIpList;
	/**
	 * 初始化系统配置
	 */
	static  {
		try {
			Configuration configuration = new PropertiesConfiguration("config.properties");
			String clientIps=configuration.getString("permitIps");
			permitIpList=Arrays.asList(clientIps.split(";"));
		} catch (Exception e) {
			throw new RuntimeException("[加载配置文件失败]", e);
		}
	}
	
}
