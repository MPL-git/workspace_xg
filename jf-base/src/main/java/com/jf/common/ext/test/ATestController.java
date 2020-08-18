package com.jf.common.ext.test;

import com.alibaba.fastjson.JSON;
import com.jf.common.ext.util.HttpKit;
import com.jf.common.ext.util.StrKit;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 测试业务父类
 * 
 * @author Chen Wei
 * 
 */
public abstract class ATestController {

	public static Map<String, Object> postParamsMap = new HashMap<String, Object>();
	public static String data;
	public static Map<String, Object> getParamsMap = new HashMap<String, Object>();
	//public static Map<String, Object> cookieMap = new HashMap<String, Object>();
	//public static Map<String, String> filePathMap = new HashMap<String, String>();
	public static Map<String, String> headerMap = new HashMap<String, String>();

	public abstract void init() throws Exception;

	/**
	 * 测试
	 *
	 */
	public abstract void doTest();

	/**
	 * 获取基地址
	 *
	 * @return
	 */
	protected abstract BaseUrl getBaseUrl();

	/**
	 * 执行post操作
	 *
	 * @return
	 */
	public final String doPost() {
		Map<String, String> params = new HashMap<String, String>();
		if (getParamsMap != null) {
			Set<String> keySet = getParamsMap.keySet();
			for (String key : keySet) {
				params.put(key, getParamsMap.get(key) + "");
			}
		}

		return HttpKit.post(getBaseUrl().toString(), params , buildPostString(), headerMap);
		//return HttpUtils.doHttpPost(getBaseUrl(), getParamsMap, headerMap, postParamsMap, cookieMap, filePathMap);
	}

	/**
	 * 对json串进行格式化
	 *
	 * @param jsonString
	 * @return
	 */
	protected final String formatJson(String jsonString) {
		return JSON.toJSONString(JSON.parse(jsonString), true);
	}




	/**
	 * Build queryString of the url
	 */
	private static String buildPostString() {
		if (postParamsMap == null || postParamsMap.isEmpty())
			return "";

		StringBuilder sb = new StringBuilder("");
		boolean isFirst = true;
		for (Map.Entry<String, Object> entry : postParamsMap.entrySet()) {
			if (isFirst){
				isFirst = false;
			}else{
				sb.append("&");
			}

			String key = entry.getKey();
			String value = entry.getValue().toString();
			if (StrKit.notBlank(value))
				try {value = URLEncoder.encode(value, "UTF-8");} catch (UnsupportedEncodingException e) {throw new RuntimeException(e);}
			sb.append(key).append("=").append(value);
		}
		return sb.toString();
	}

}
