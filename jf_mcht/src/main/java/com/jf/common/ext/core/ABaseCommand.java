package com.jf.common.ext.core;

import com.jf.common.ext.util.SpringUtil;
import com.jf.common.ext.util.StrKit;
import com.jf.controller.BaseController;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Base Command
 * 
 * @author hdl
 *
 */
public abstract class ABaseCommand {
	
	protected BaseController context;

	protected Map<String, Object> argsMap;
	protected Map<String, Object> data;

	public ABaseCommand() {
		Field[] fields = this.getClass().getDeclaredFields();
		for (Field field : fields) {
			Object bean = null;
			if (field.isAnnotationPresent(Resource.class)) {
				//bean = SpringUtil.getBean(field.getType());
				bean = SpringUtil.getBean(field.getName());
			} else {
				continue;
			}

			try {
				if (bean != null) {
					field.setAccessible(true);
					field.set(this, bean);
				}

			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		
		data = new HashMap<String, Object>();
	}
	
	/**
	 * 设置context
	 * 
	 * @param context
	 */
	public void initContext(BaseController context) {
		this.context = context;
	}
	
	/**
	 * 初始化参数Map
	 * 
	 * @param argsMap
	 */
	public void initArgs(Map<String, Object> argsMap) {
		this.argsMap = argsMap;
	}
	
	/**
	 * 参数初始化，以及验证
	 */
	public abstract void init();
	
	/**
	 * 执行服务
	 * 
	 * @return 执行结果
	 */
	public abstract void doCommand();
	
	/**
	 * 做收尾工作
	 * 
	 * @throws Exception
	 */
	public void doFinally() throws Exception {}
	

	/**
	 * 获取请求参数，将它转为int<br>
	 * 不存在返回0
	 * 
	 * @param name
	 * @return
	 */
	public int getParaToInt(String name) {
		return toInt(getArgForStr(name), 0);
	}
	
	/**
	 * 获取请求参数，将它转为int<br>
	 * 不存在返回默认值
	 * 
	 * @param name
	 * @param defaultValue
	 * @return
	 */
	public Integer getParaToInt(String name, Integer defaultValue) {
		return toInt(getArgForStr(name), defaultValue);
	}
	
	private Integer toInt(String value, Integer defaultValue) {
		if (value == null || "".equals(value.trim()) || "null".equalsIgnoreCase(value.trim()))
			return defaultValue;
		if (value.startsWith("N") || value.startsWith("n"))
			return -Integer.parseInt(value.substring(1));
		return Integer.parseInt(value);
	}
	
	/**
	 * 默认为0
	 * 
	 * @param name
	 * @return
	 */
	public long getParaToLong(String name) {
		return toLong(getArgForStr(name), 0L);
	}
	
	public Long getParaToLong(String name, Long defaultValue) {
		return toLong(getArgForStr(name), defaultValue);
	}
	
	private Long toLong(String value, Long defaultValue) {
		if (value == null || "".equals(value.trim()) || "null".equalsIgnoreCase(value.trim()))
			return defaultValue;
		if (value.startsWith("N") || value.startsWith("n"))
			return -Long.parseLong(value.substring(1));
		return Long.parseLong(value);
	}
	
	public float getParaToFloat(String name) {
		return toFloat(getArgForStr(name), 0f);
	}
	
	public float getParaToFloat(String name, float defaultValue) {
		return toFloat(getArgForStr(name), defaultValue);
	}
	
	private Float toFloat(String value, Float defaultValue) {
		if (StrKit.isBlank(value)) {
			return defaultValue;
		}
		
		return Float.parseFloat(value);
	}
	
	public double getParaToDouble(String name) {
		return toDouble(getArgForStr(name), 0d);
	}
	
	public double getParaToDouble(String name, double defaultValue) {
		return toDouble(getArgForStr(name), defaultValue);
	}
	
	private Double toDouble(String value, Double defaultValue) {
		if (StrKit.isBlank(value)) {
			return defaultValue;
		}
		
		return Double.parseDouble(value);
	}
	
	/**
	 * 获取请求值
	 * 
	 * @param name
	 * @return
	 */
	public String getPara(String name) {
		return getArgForStr(name);
	}
	
	/**
	 * 获取请求值，如果不存在，则设置为默认值
	 * 
	 * @param name
	 * @param defaultValue
	 * @return
	 */
	public String getPara(String name, String defaultValue) {
		String result = getArgForStr(name);
		
		return StrKit.isBlank(result) ? defaultValue : result;
	}
	
	/**
	 * 获取请求参数，将它转为boolean<br>
	 * 不存在返回默认值
	 * 
	 * @param name
	 * @param defaultValue
	 * @return
	 */
	public Boolean getParaToBoolean(String name, Boolean defaultValue) {
		return toBoolean(getArgForStr(name), defaultValue);
	}

	/**
	 * 获取请求参数，将它转为boolean<br>
	 * 不存在返回false
	 * 
	 * @param name
	 * @return
	 */
	public boolean getParaToBoolean(String name) {
		return toBoolean(getArgForStr(name), false);
	}
	
	private Boolean toBoolean(String value, Boolean defaultValue) {
		if (value == null || "".equals(value.trim()))
			return defaultValue;
		value = value.trim().toLowerCase();
		if ("1".equals(value) || "true".equals(value))
			return Boolean.TRUE;
		else if ("0".equals(value) || "false".equals(value))
			return Boolean.FALSE;
		throw new RuntimeException("Can not parse the parameter \"" + value + "\" to boolean value.");
	}
	
	/**
	 * 获取时间
	 * 
	 * @param name
	 * @return
	 */
	public Date getParaToDate(String name) {
		return toDate(getArgForStr(name), null);
	}
	
	public Date getParaToDate(String name, Date defaultValue) {
		return toDate(getArgForStr(name), defaultValue);
	}
	
	private Date toDate(String value, Date defaultValue) {
		try {
			if (value == null || "".equals(value.trim()))
				return defaultValue;
			return new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(value.trim());
		
		} catch (Exception e) {
			throw new RuntimeException("Can not parse the parameter \"" + value + "\" to Date value.");
		}
	}
	
	private String getArgForStr(String name) {
		return (String) argsMap.get(name);
	}

	public Map<String, Object> getData() {
		return data;
	}

	public <T> T getBean(Class<T> modelClass, String modelName) {
		return context.getBean(modelClass, modelName, argsMap);
	}

}
