package com.jf.common.utils;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.jf.common.constant.Const;
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

		
		
		String resutValue= config.getProperty(key);
		
		if("mUrl".equals(key)){
			HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
	        if(request.getAttribute("reqPRM")!=null){
	          	 JSONObject paramJson=(JSONObject)request.getAttribute("reqPRM");
	       		//安卓版本号<40 webView 无法同时兼容https和http同时存在
	          	if(!paramJson.containsKey("system")||!paramJson.containsKey("version")){
	          		resutValue=resutValue.replace("https", "http");
	          	}
	       		if(paramJson.containsKey("system") && !StringUtil.isBlank(paramJson.getString("system")) && paramJson.getString("system").equals(Const.ANDROID)){
	       			if(paramJson.containsKey("version") && !StringUtil.isBlank(paramJson.getString("version"))){
	       				String version = paramJson.getString("version");
	       				if(Integer.valueOf(version).intValue()<40){
	       					resutValue=resutValue.replace("https", "http");
	       				}
	       			}
	       		} 
	           }
		}
		

		return resutValue;
		
	}
  
  public static void setProperty(String key,String value){	
	  config.setProperty(key, value);
  }
  
  public static void main(String[] args)
  {
	  System.out.println(getProperty("ALIPAY_APPID"));
  }
}
