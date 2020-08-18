package com.jf.common.constant;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.PropertiesConfiguration;

public class SysConfig {
 public static String msgServerUrl;
 public static String previewServerUrl;
	/**
	 * 初始化系统配置
	 */
	static  {
		try {
			Configuration configuration = new PropertiesConfiguration("config.properties");
			msgServerUrl = configuration.getString("msgServerUrl");
			previewServerUrl = configuration.getString("previewServerUrl");
		} catch (Exception e) {
			throw new RuntimeException("[加载配置文件失败]", e);
		}
	}
}
