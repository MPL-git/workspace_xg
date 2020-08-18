package com.jf.common.utils;

import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
/**
 * 读取支付宝配置文件数据
 * @author chenwf
 *
 */
public class alipayConfigUtil{
	private static final Logger LOG = Logger.getLogger(alipayConfigUtil.class);

	private static Properties config = null;

	/**
	 * 返回系统config.properties配置信息
	 * @param key key值
	 * @return value值
	 */
	public static String getProperty(String key) {
		if (config == null) {
			synchronized (alipayConfigUtil.class) {
				if (null == config) {
					try {
						Resource resource = new ClassPathResource("pay/alipayConfig.properties");
						config = PropertiesLoaderUtils.loadProperties(resource);
					} catch (IOException e) {
						LOG.error(e.getMessage(), e);
					}
				}
			}
		}

		return config.getProperty(key);
	}
  
  
  public static void main(String[] args)
  {
	  System.out.println(getProperty("ALIPAY_APPID"));
  }
}
