package com.jf.common.utils;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jf.common.constant.SysConfig;

import sun.misc.BASE64Encoder;

public class SmsUtil {
	private static Logger logger = LoggerFactory.getLogger(SmsUtil.class);
	
	public static void main(String[] args) {
		Map<String, String> headers=new HashMap<String, String>();
		BASE64Encoder base64Encoder=new BASE64Encoder();
		String user=base64Encoder.encode("jvmai:jvmai2017".getBytes());
		headers.put("Authorization", "Basic "+user);
		
//		http://public.webpowerchina.cn/sms/
		String result=HttpUtil.sendGetRequest("http://public.webpowerchina.cn/sms/rest/v1/reports?campaignID=8&nextID=0", headers);
		System.out.println(result);
		
		
//		JSONObject sms=new JSONObject();
//		sms.put("mobile", "15006039376");
//		sms.put("campaignID", "8");
//		sms.put("content", "您的验证码为：87847，验证码5分钟内有效。");
//
//		
//		String result=HttpUtil.sendPostRequest("http://public.webpowerchina.cn/sms/rest/v1/sms",sms.toString(),null, headers);
////		String result=HttpUtil.sendPostRequest("http://test.webpowerchina.cn/sms/rest/v1/sms",sms.toString(),null, headers);
//		System.out.println(result);
	}
	
	public static void send(String mobile, String content, String msgType){
		JSONObject paramToMcht = new JSONObject();
		JSONObject reqDataToMcht = new JSONObject();
		reqDataToMcht.put("mobile", mobile);
		reqDataToMcht.put("content", content);
		reqDataToMcht.put("smsType", msgType);
		paramToMcht.put("reqData", reqDataToMcht);
		JSONObject resultToMcht = JSONObject.fromObject(HttpUtil.sendPostRequest(SysConfig.msgServerUrl+"/api/sms/sendImmediately", CommonUtil.createReqData(paramToMcht).toString()));
		if(!"0000".equals(resultToMcht.getString("returnCode"))){
			logger.info("短信发送失败！！内容为{}", content);
		}
	}
}
