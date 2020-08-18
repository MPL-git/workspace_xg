package com.jf.common.base;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.jf.common.utils.FileUtil;

public class BaseConfigProperties {
	public native String[] getDatabaseConfig();

	static{
		InputStream stream = FileUtil.class.getResourceAsStream("/config.properties");
		try {
			Properties properties = new Properties();
			properties.load(stream);
			System.load(properties.getProperty("jni.baseCnfFile"));
			stream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
