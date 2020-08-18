package com.jf.common.ext.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jf.common.ext.util.HttpKit;
import com.jf.common.ext.util.RandomUtil;
import com.jf.common.ext.util.SecurityUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 测试业务父类【带签名】
 * 
 * @author hdl
 * 
 */
public abstract class ATestSignatureController {

	public JSONObject topParamsMap = new JSONObject();
	public JSONObject postParamsMap = new JSONObject();

	public abstract void init();

	/**
	 * 测试
	 */
	public abstract void doTest();

	/**
	 * 执行post操作
	 *
	 * @return
	 */
	public String doPost(String url) {
		init();
		String timeStamp = String.valueOf(new Date().getTime());
		String token = topParamsMap.getString("token");
		String nonceStr = RandomUtil.randomStr(16);

		JSONObject postData = new JSONObject();
		postData.put("token", token);
		postData.put("timeStamp", timeStamp);
		postData.put("nonceStr", nonceStr);
		postData.put("reqData", postParamsMap);

		String signature = createSignature(postData);
		postData.put("signature", signature);
		postData.putAll(topParamsMap);
		return HttpKit.post(url, postData.toJSONString());
	}

	/**
	 * 对json串进行格式化
	 * 
	 * @param jsonString
	 * @return
	 */
	protected String formatJson(String jsonString) {
		return JSON.toJSONString(JSON.parse(jsonString), true);
	}

	public static String createSignature(JSONObject paramJson){
		Map<String, String> paramMap=new HashMap<String, String>();
		paramMap.put("token", paramJson.getString("token"));
		paramMap.put("timeStamp", paramJson.getString("timeStamp"));
		paramMap.put("nonceStr", paramJson.getString("nonceStr"));
		paramMap.put("reqData", paramJson.getString("reqData"));
		return createSignature(paramMap);
	}


	public static final String key="3BDB71CA-11A3-49";

	public static String createSignature(Map<String, String> dataMap){
		String reqData=dataMap.get("reqData");
		dataMap.remove("reqData");
		dataMap.put("key", key);
		ArrayList<String> list = new ArrayList<String>();
		for(String key:dataMap.keySet()){
			if(dataMap.get(key)!=null&&!"".equals(dataMap.get(key))){
				list.add(dataMap.get(key));
			}
		}
		int size = list.size();
		String [] arrayToSort = list.toArray(new String[size]);
		Arrays.sort(arrayToSort);
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < size; i ++) {
			sb.append(arrayToSort[i]);
		}
		String result = sb.toString();
		result += reqData;
//		System.out.println(result);
		result = SecurityUtil.md5Encode(result);
		return result;
	}

}
