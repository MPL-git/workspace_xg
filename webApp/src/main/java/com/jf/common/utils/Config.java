package com.jf.common.utils;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
/**
 * 读取支付宝配置文件数据
 * @author chenwf
 *
 */

@Component
public class Config{
	private static final Logger LOG = Logger.getLogger(Config.class);

	private static Properties config = null;

	/**
	 * 返回系统config.properties配置信息
	 * @param key key值
	 * @return value值
	 */
	public static String getProperty(String key) {
		
		if("mUrl".equals(key)){
			HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
			if(request.getServerPort()==80||request.getServerPort()==443){
				return request.getScheme()+"://"+request.getServerName()+request.getContextPath();
			}else{
				return request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
			}
		}
		
		if (config == null) {
			synchronized (Config.class) {
				if (null == config) {
					try {
						Resource resource = new ClassPathResource("config.properties");
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
