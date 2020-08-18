package com.jf.common.constant;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.jf.common.utils.FileUtil;


public class SysConfig {
 public static String msgServerUrl;
 public static String defaultPath;
	/**
	 * 系统配置
	 */
	  static {
	    InputStream stream = FileUtil.class.getResourceAsStream("/base_config.properties");
	    try
	    {
	      Properties properties = new Properties();
	      properties.load(stream);
	      msgServerUrl = properties.getProperty("msgServerUrl");
	      defaultPath = properties.getProperty("annex.filePath");
	      stream.close();
	    }
	    catch (IOException e)
	    {
	      e.printStackTrace();
	    }
	  }
	  
	
}
