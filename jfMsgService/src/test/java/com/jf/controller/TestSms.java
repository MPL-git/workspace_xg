package com.jf.controller;

import net.sf.json.JSONObject;

import org.junit.Test;

import com.jf.common.utils.CommonUtil;
import com.jf.common.utils.HttpUtil;

public class TestSms {

	@Test
	public void sendImmediately() {
		
		JSONObject param=new JSONObject();
		
		
		
		//特定参数
		JSONObject reqData=new JSONObject();
		reqData.put("mobile", "15006039376");
		reqData.put("content", "告诉你个秘密，你的验证码是877999，千万不要告诉别人哟！");
		reqData.put("smsType", "1");
		param.put("reqData", reqData);
		
		JSONObject result=JSONObject.fromObject(HttpUtil.sendPostRequest("http://msgtest.xfbuy.cn/msgService/api/sms/sendImmediately", CommonUtil.createReqData(param).toString()));
		System.out.println(result.toString());
	}
}
