package com.jf.common.ext.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 请求参数工具
 * 
 * @author David Chen
 *
 */
public class ArgKit {
	
	private static ThreadLocal<Map<String, Object>> threadLocal = new ThreadLocal<Map<String,Object>>();
	
	/**
	 * 获取argsMap
	 * 
	 * @return
	 */
	public static Map<String, Object> getArgsMap() {
		/*if (threadLocal.get() == null) {
			return new HashMap<String, Object>();
		}*/
		
		return threadLocal.get();
	}
	
	/**
	 * 设置参数map。如果有，则融合。
	 * 
	 * @param argsMap
	 */
	public static void setArgsMap(Map<String, Object> argsMap) {
		if (getArgsMap() == null) {
			threadLocal.set(argsMap);
			
//			System.out.println(threadLocal.get());
			
			return;
		}
		
		getArgsMap().putAll(argsMap);
	}
	
	/**
	 * 清楚当前线程的参数
	 */
	public static void removeArgsMap() {
		threadLocal.remove();
	}
	
	/**
	 * 清空参数
	 */
	public static void clear() {
		getArgsMap().clear();
	}
	
	/**
	 * 设置参数
	 * 
	 * @param key
	 * @param value
	 */
	public static void set(String key, Object value) {
		if (getArgsMap() == null) {
			setArgsMap(new HashMap<String, Object>());
		}
		
		getArgsMap().put(key, value);
	}
	
	/**
	 * 获取参数值
	 * 
	 * @param key
	 * @return
	 */
	public static Object get(String key) {
		return getArgsMap().get(key);
	}

}
