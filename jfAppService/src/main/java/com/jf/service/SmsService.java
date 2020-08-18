package com.jf.service;

import com.jf.common.base.ArgException;
import com.jf.common.ext.exception.BizException;
import com.jf.common.utils.CommonUtil;
import com.jf.common.utils.Config;
import com.jf.common.utils.HttpUtil;
import com.jf.entity.MobileVerificationCode;
import net.sf.json.JSONObject;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 短信服务
 *
 * @author     ：huangdl
 * @date       ：Created in 2019/7/24
 */
@Service
public class SmsService{

	private static Logger logger = LoggerFactory.getLogger(SmsService.class);

	@Autowired
	private MobileVerificationCodeService mobileVerificationCodeService;


	/**
	 * 直接发送短信
	 *
	 * @param mobile
	 * @param content
	 * @param smsType
	 * @param ip
	 * @return: void  
	 */
	public boolean send(String mobile, String content, String smsType, String ip){
		JSONObject param = new JSONObject();
		JSONObject reqData = new JSONObject();
		reqData.put("mobile", mobile);
		reqData.put("content", content);
		reqData.put("smsType", smsType);
		reqData.put("sourceClient", "1");	//app
		reqData.put("ip", ip);
		param.put("reqData", reqData);
		String res = HttpUtil.sendPostRequest(Config.getProperty("msgServerUrl")+"/api/sms/sendImmediately", CommonUtil.createReqData(param).toString());
		JSONObject result = JSONObject.fromObject(res);
		if("0000".equals(result.optString("returnCode"))){
			return true;
		}

		logger.info("短信发送失败！！手机:{}，内容:{}", mobile, content);
		return false;
	}

	/**
	 * 异步发送短信
	 *
	 * @param mobile
	 * @param content
	 * @param smsType
	 * @param ip
	 * @return: void
	 */
	public void asyncSend(String mobile, String content, String smsType, String ip) {
		SmsRunnable sms = new SmsRunnable(mobile, content, smsType, ip);
		Thread thread = new Thread(sms);
		thread.start();
	}

	/**
	 * 发送验证码
	 *
	 * @param mobile
	 * @param verifyCode
	 * @param content
	 * @param ip
	 * @return: void
	 */
	public void sendVerifyCode(String mobile, String verifyCode, String content, String ip){
		MobileVerificationCode mobileVerificationCode = mobileVerificationCodeService.findModelByMobile(mobile);
		if(mobileVerificationCode != null){
			if(DateTime.now().minusSeconds(60).getMillis() < mobileVerificationCode.getCreateDate().getTime()){
				throw new ArgException("60秒内不能重复获取验证码,请稍后在尝试");
			}
		}

		if(!send(mobile, content, "1", ip)){
			throw new BizException("发送失败，请重新获取!");
		}

		MobileVerificationCode model =new MobileVerificationCode();
		model.setMobile(mobile);
		model.setVerificationCode(verifyCode);
		model.setCreateDate(new Date());
		mobileVerificationCodeService.insertSelective(model);
	}

	/**
	 * 校验验证码
	 *
	 * @param mobile
	 * @param verifyCode
	 * @return: boolean
	 */
	public boolean checkVerifyCode(String mobile, String verifyCode){
		return mobileVerificationCodeService.verify(mobile, verifyCode);
	}



	class SmsRunnable implements Runnable{

		private String mobile;
		private String content;
		private String smsType;
		private String ip;

		public SmsRunnable(String mobile, String content, String smsType, String ip) {
			this.mobile = mobile;
			this.content = content;
			this.smsType = smsType;
			this.ip = ip;
		}

		public void run() {
			send(mobile, content, smsType, ip);
		}
	}

}
