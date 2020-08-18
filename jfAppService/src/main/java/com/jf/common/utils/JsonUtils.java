package com.jf.common.utils;

import java.util.Map;

import net.sf.json.JSONObject;

/**
 * Json工具类
 * 
 * @author gonghongqing01
 * @since 2016-10-31
 */

public class JsonUtils {

    /**
     * json字符串转javaBean
     * 
     * @param jsonStr json字符串
     * @param clazz javaBean的类对象
     * @return javaBean对象
     */
    @SuppressWarnings("unchecked")
    public static <T> T json2pojo(String jsonStr, Class<T> clazz) {
        JSONObject jsonObj = JSONObject.fromObject(jsonStr);
        T obj = (T) JSONObject.toBean(jsonObj, clazz);
        return obj;
    }

    /**
     * javaBean转json字符串
     * 
     * @param obj javaBean对象
     * @return json字符串
     */
    public static String pojo2json(Object obj) {
        return JSONObject.fromObject(obj).toString();
    }

    /**
     * json字符串转map
     * 
     * @param jsonStr json字符串
     * @return 以json字符串中的名称为key，值为value的map
     */
    @SuppressWarnings("unchecked")
    public static <T> Map<String, T> json2map(String jsonStr) {
        JSONObject jsonObj = JSONObject.fromObject(jsonStr);
        Map<String, T> map = (Map<String, T>) JSONObject.toBean(
                jsonObj, Map.class);
        return map;
    }

    /**
     * 获取json字符串中名称对应的value
     * 
     * @param jsonStr json字符串
     * @param name json字符串中的名称
     * @return 名称对应的值
     */
    public static <T> T extract(String jsonStr, String name) {
        Map<String, T> map = json2map(jsonStr);
        return map.get(name);
    }
    
    public static boolean isEmpty(JSONObject jsonObject,String key){
    	if(!jsonObject.has(key)||"".equals(jsonObject.get(key))){
    		return true;
    	}else{
    		return false;
    	}
    }
}
