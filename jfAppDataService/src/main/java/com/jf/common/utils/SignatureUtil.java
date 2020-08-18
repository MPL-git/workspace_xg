package com.jf.common.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

/**
 * 工具类
 * 
 *
 * 
 */
public class SignatureUtil {
	
	public static final String key="335XI9CAZ2-1A389";
	
	public static boolean checkSignature(Map<String, String> dataMap,String signature){
		
		String result =createSignature(dataMap);
		//System.out.println(result);
		if(signature.equals(result)){
			return true;
		}else{
			return false;
		}
	}
	
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
