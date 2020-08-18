package com.jf.controller;

import com.jf.common.base.ArgException;
import com.jf.common.base.ResponseMsg;
import com.jf.common.utils.JsonUtils;
import com.jf.common.utils.StringUtil;
import com.jf.entity.*;
import com.jf.service.*;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Date;

@Controller
public class SmsController {

	private static final Logger logger = Logger.getLogger(SmsController.class);

	@Resource
	private SmsService smsService;

	@Resource
	private SmsBlackIpService smsBlackIpService;

	@Resource
	private SmsBlackMobileService smsBlackMobileService;

	@Resource
	private SmsWhiteMobileService smsWhiteMobileService;//白名单
	
	@Resource
	private SysParamCfgService sysParamCfgService;

	@RequestMapping(value = "/api/sms/sendImmediately")
	@ResponseBody
	public ResponseMsg sendImmediately(HttpServletRequest request) {
		JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
		JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
		try {

			if (JsonUtils.isEmpty(reqDataJson, "mobile")) {
				throw new ArgException("手机号不能为空");
			}

			if (JsonUtils.isEmpty(reqDataJson, "content")) {
				throw new ArgException("信息内容不能为空");
			}

			if (!StringUtil.isMobile(reqDataJson.getString("mobile"))) {
				throw new ArgException("手机号格式错误");
			}

			if (JsonUtils.isEmpty(reqDataJson, "smsType")) {
				throw new ArgException("短信类型不能为空");
			}

			isCanGetSms(reqDataJson.getString("mobile"), reqDataJson.containsKey("ip") ? reqDataJson.getString("ip") : null);

			Sms sms = new Sms();
			sms.setMobile(reqDataJson.getString("mobile"));
			sms.setSmsType(reqDataJson.getString("smsType"));
			sms.setContent(reqDataJson.getString("content"));
			if (reqDataJson.containsKey("bizId")) {
				sms.setBizId(Integer.valueOf(reqDataJson.getString("bizId")));
			}
			if (reqDataJson.containsKey("memberId")) {
				sms.setMemberId(Integer.valueOf(reqDataJson.getString("memberId")));
			}

			if (reqDataJson.containsKey("sourceClient")) {
				sms.setSourceClient(reqDataJson.getString("sourceClient"));
			}

			if (reqDataJson.containsKey("ip")) {
				sms.setIp(reqDataJson.getString("ip"));
			}
			//STORY #1770
			if (reqDataJson.containsKey("smsPlatform")) {
				sms.setSmsPlatform(reqDataJson.getString("smsPlatform"));
			}
			smsService.sendImmediately(sms);

			if ("1".equals(sms.getSendStatus())) {
				return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
			} else {
				throw new ArgException("发送失败");
			}

		} catch (ArgException arge) {
			
			if ("不合理请求".equals(arge.getMessage())) {// 非法请求是提示前端发送成功
				logger.warn("-------------非法短信请求用户:" + reqDataJson.getString("mobile") + "---ip:" + (reqDataJson.containsKey("ip") ? reqDataJson.getString("ip") : "") + "---请求来源:" + (reqDataJson.containsKey("sourceClient") ? reqDataJson.getString("sourceClient") : ""));
				return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
			} else {
				arge.printStackTrace();
				return new ResponseMsg(ResponseMsg.ERROR, arge.getMessage());
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}

	}

	private boolean isCanGetSms(String mobile, String ip) throws ArgException {

		if (!StringUtil.isEmpty(ip)) {
			SmsBlackIpExample smsBlackIpExample = new SmsBlackIpExample();
			smsBlackIpExample.createCriteria().andIpEqualTo(ip).andDelFlagEqualTo("0");
			if (smsBlackIpService.countByExample(smsBlackIpExample) > 0) {
				logger.warn("-------------非法短信用户Ip:" + ip);
				throw new ArgException("不合理请求");
			}
		}

		SmsBlackMobileExample smsMobileExample = new SmsBlackMobileExample();
		smsMobileExample.createCriteria().andMobileEqualTo(mobile).andDelFlagEqualTo("0");
		SmsWhiteMobileExample smsWhiteMobileExample = new SmsWhiteMobileExample();
		smsWhiteMobileExample.createCriteria().andMobileEqualTo(mobile).andDelFlagEqualTo("0");
		if (smsBlackMobileService.countByExample(smsMobileExample) > 0 && smsWhiteMobileService.countByExample(smsWhiteMobileExample) <=0) {
			logger.warn("-------------非法短信用户mobile:" + mobile);
			throw new ArgException("不合理请求");
		}

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Date today = calendar.getTime();

		SmsExample smsExample = new SmsExample();
		if (!StringUtil.isEmpty(ip)) {// 同个ip一天只能获取25条，超过25调列入和名单
			smsExample = new SmsExample();
			smsExample.createCriteria().andDelFlagEqualTo("0").andCreateDateGreaterThan(today).andIpEqualTo(ip);
			if (smsService.countByExample(smsExample) > 25) {
				SmsBlackIp smsBlackIp = new SmsBlackIp();
				smsBlackIp.setIp(ip);
				smsBlackIp.setDelFlag("0");
				smsBlackIp.setCreateDate(new Date());
				smsBlackIp.setRemarks("mobile:" + mobile);
				smsBlackIpService.insertSelective(smsBlackIp);
				logger.warn("-------------短信用户ip:" + ip + "一天请求超过25次"+"mobile:"+mobile);
				throw new ArgException("不合理请求");
			}
		}

		smsExample = new SmsExample();
		smsExample.createCriteria().andMobileEqualTo(mobile).andDelFlagEqualTo("0").andCreateDateGreaterThan(today);
		int count = smsService.countByExample(smsExample);
		if (count >= 10) {
			logger.warn("-------------短信用户mobile:" + mobile + "一天请求超过10次"+"ip:"+ip);
			SmsBlackMobile smsBlackMobile = new SmsBlackMobile();
			smsBlackMobile.setMobile(mobile);
			smsBlackMobile.setDelFlag("0");
			smsBlackMobile.setCreateDate(new Date());
			smsBlackMobile.setRemarks("ip:" + ip);
			smsBlackMobileService.insertSelective(smsBlackMobile);
			if (smsWhiteMobileService.countByExample(smsWhiteMobileExample) <=0){  //白名单账号超十次被加入黑名单,此时也不终止发送
			throw new ArgException("不合理请求");
			}
		}

		return true;
	}

}
