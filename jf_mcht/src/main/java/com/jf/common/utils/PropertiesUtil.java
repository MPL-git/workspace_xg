package com.jf.common.utils;

import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Properties;

/**
 * @author Pengl
 * @create 2020-03-19 下午 2:53
 */
public class PropertiesUtil {

	private static final Logger logger = Logger.getLogger(PropertiesUtil.class);

	private static Properties config = null;

	/**
	 * 返回系统***.properties配置信息
	 * @param path
	 * @param key
	 * @return
	 */
	public static String getProperty(String path, String key) {
		if (config == null) {
			synchronized (PropertiesUtil.class) {
				if (config == null) {
					try {
						Resource resource = new ClassPathResource(path);
						config = PropertiesLoaderUtils.loadProperties(resource);
					} catch (IOException e) {
						logger.error(e.getMessage(), e);
					}
				}
			}
		}
		return config.getProperty(key);
	}


}
