package com.jf.common.ext.core;


import com.jf.common.ext.util.StrKit;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * BeanInjector
 */
public final class BeanInjector {
	
	@SuppressWarnings("unchecked")
	public static <T> T inject(Class<?> modelClass, HttpServletRequest request, boolean skipConvertError) {
		String modelName = modelClass.getSimpleName();
		return (T)inject(modelClass, StrKit.firstCharToLowerCase(modelName), request, skipConvertError);
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T inject(Class<?> modelClass, Map<String, Object> argsMap, boolean skipConvertError) {
		String modelName = modelClass.getSimpleName();
		return (T)inject(modelClass, StrKit.firstCharToLowerCase(modelName), argsMap, skipConvertError);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static final <T> T inject(Class<?> modelClass, String modelName, HttpServletRequest request, boolean skipConvertError) {
		Object model = null;
		try {
			model = modelClass.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		injectCommonModel(model, modelName, request, modelClass, skipConvertError);
		
		return (T)model;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static final <T> T inject(Class<?> modelClass, String modelName, Map<String, Object> argsMap, boolean skipConvertError) {
		Object model = null;
		try {
			model = modelClass.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		injectCommonModel(model, modelName, argsMap, modelClass, skipConvertError);
		
		return (T)model;
	}
	
	private static final void injectCommonModel(Object model, String modelName, HttpServletRequest request, Class<?> modelClass, boolean skipConvertError) {
		Method[] methods = modelClass.getMethods();
		for (Method method : methods) {
			String methodName = method.getName();
			if (methodName.startsWith("set") == false)	// only setter method
				continue;
			
			Class<?>[] types = method.getParameterTypes();
			if (types.length != 1)						// only one parameter
				continue;
			
			String attrName = methodName.substring(3);
			String value = request.getParameter(modelName + "." + StrKit.firstCharToLowerCase(attrName));
			if (value != null) {
				try {
					method.invoke(model, TypeConverter.convert(types[0], value));
				} catch (Exception e) {
					if (skipConvertError == false)
					throw new RuntimeException(e);
				}
			}
		}
	}
	
	private static final void injectCommonModel(Object model, String modelName, Map<String, Object> argsMap, Class<?> modelClass, boolean skipConvertError) {
		if (argsMap == null)
			throw new IllegalArgumentException("ArgMap should not be null");
		
		boolean hasPrefix = StrKit.notBlank(modelName);
		
		Method[] methods = modelClass.getMethods();
		for (Method method : methods) {
			String methodName = method.getName();
			if (methodName.startsWith("set") == false)	// only setter method
				continue;
			
			Class<?>[] types = method.getParameterTypes();
			if (types.length != 1)						// only one parameter
				continue;
			
			String attrName = methodName.substring(3);
			String key = hasPrefix ? modelName + "." + StrKit.firstCharToLowerCase(attrName) : StrKit.firstCharToLowerCase(attrName);
			Object value = argsMap.get(key);
			if (value != null) {
				try {
					method.invoke(model, TypeConverter.convert(types[0], value.toString()));
				} catch (Exception e) {
					if (skipConvertError == false)
					throw new RuntimeException(e);
				}
			}
		}
	}

}

