package com.jf.service;

import org.springframework.stereotype.Service;

import com.jf.common.utils.CommonUtil;
import com.jf.common.utils.Config;
import com.jf.common.utils.HttpUtil;

import net.sf.json.JSONObject;

@Service
public class SendSmsThread implements Runnable{
	private String nick;
	private String mobile;
	private String ip;
	private String content;
	private String type;
	
	
	public void setNick(String nick) {
		this.nick = nick;
	}
	
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	

	public SendSmsThread(String nick,String ip,String mobile,String type,String content){
		this.ip = ip;
		this.nick = nick;
		this.mobile = mobile;
		this.type = type;
		this.content = content;
	}
	@Override
	public void run() {
		if("1".equals(type)){
			sendSms(mobile,content,"4","1",ip);
		}
		
	}
	
	public static void main(String[] args) {
		SendSmsThread sms = new SendSmsThread("", "127.0.0.1", "13655082627", "1", "shdiashdias");
		Thread thread = new Thread(sms);
		thread.start();
	}
	
	public JSONObject sendSms(String mobile, String content, String smsType, String sourceClient, String ip) {
		JSONObject result = new JSONObject();
		try {
			JSONObject param=new JSONObject();
			JSONObject reqData=new JSONObject();
			reqData.put("mobile", mobile);
			reqData.put("content", content);
			reqData.put("smsType", smsType);
			reqData.put("sourceClient", sourceClient);
			reqData.put("ip", ip);
			param.put("reqData", reqData);
			//调用短信接口
			result=JSONObject.fromObject(HttpUtil.sendPostRequest(Config.getProperty("msgServerUrl")+"/api/sms/sendImmediately", CommonUtil.createReqData(param).toString()));
		} catch (Exception e) {
			
		}
		return result;
	}
}
