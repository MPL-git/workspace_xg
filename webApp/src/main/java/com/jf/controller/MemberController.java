package com.jf.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jf.common.base.ArgException;
import com.jf.common.base.ResponseMsg;
import com.jf.common.constant.BaseDefine;
import com.jf.common.constant.Const;
import com.jf.common.utils.CollectionUtil;
import com.jf.common.utils.CommonUtil;
import com.jf.common.utils.Config;
import com.jf.common.utils.DataDicUtil;
import com.jf.common.utils.HttpUtil;
import com.jf.common.utils.JsonUtils;
import com.jf.common.utils.StringUtil;
import com.jf.entity.AppLoginLog;
import com.jf.entity.MemberInfo;
import com.jf.entity.MemberInfoExample;
import com.jf.entity.MobileVerificationCode;
import com.jf.entity.SysAppMessage;
import com.jf.entity.SysAppMessageCustom;
import com.jf.entity.SysParamCfg;
import com.jf.service.AppAccessTokenService;
import com.jf.service.AppLoginLogService;
import com.jf.service.GrowthDtlService;
import com.jf.service.IntegralDtlService;
import com.jf.service.IntegralTypeService;
import com.jf.service.MemberAccountService;
import com.jf.service.MemberGroupService;
import com.jf.service.MemberInfoService;
import com.jf.service.MobileVerificationCodeService;
import com.jf.service.ProductTypeService;
import com.jf.service.SysAppMessageService;

import net.sf.json.JSONObject;

@SuppressWarnings("serial")
@Controller
public class MemberController extends BaseController{

	@Resource
	private MemberInfoService memberInfoService;
	
	@Resource
	private MobileVerificationCodeService mobileVerificationCodeService;
	
	@Resource
	private MemberAccountService memberAccountService;
	
	@Resource
	private AppAccessTokenService appAccessTokenService;

	@Resource
	private AppLoginLogService appLoginLogService;
	
	@Resource
	private ProductTypeService productTypeService;
	
	@Resource
	private IntegralTypeService integralTypeService;
	
	@Resource
	private MemberGroupService memberGroupService;
	@Resource
	private GrowthDtlService growthDtlService;
	@Resource
	private IntegralDtlService integralDtlService;
	@Resource
	private SysAppMessageService sysAppMessageService;
	/**
	 * 
	 * 方法描述 ：注册的时候使用
	 * @author  chenwf: 
	 * @date 创建时间：2017年8月24日 上午9:15:28 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/n/getMobileVerificationCode4Register")
	@ResponseBody
	public ResponseMsg getMobileVerificationCode4Register(HttpServletRequest request) {
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数

			if (JsonUtils.isEmpty(reqDataJson, "mobile")) {
				throw new ArgException("请输入手机号");
			}
			if(!StringUtil.isMobile(reqDataJson.getString("mobile"))){
				throw new ArgException("请输入正确的手机号");
			}
			
			if(!reqDataJson.has("type")){
				if(JsonUtils.isEmpty(reqDataJson, "validCode")){
					throw new ArgException("图形验证码不能为空");
				}
				String validCode = reqDataJson.getString("validCode");
				
				if(request.getSession().getAttribute(BaseDefine.CAPTCHA)==null){
					throw new ArgException("请刷新图形验证码");
				}
				
				String captcha = (String) request.getSession().getAttribute(BaseDefine.CAPTCHA);
				if (!captcha.toLowerCase().equals(validCode.toLowerCase())) {
					throw new ArgException("图形验证码错误");
				}else{
					request.getSession().removeAttribute(BaseDefine.CAPTCHA);
				}
			}
			
			String mobile=reqDataJson.getString("mobile");
			
			MemberInfoExample memberInfoExample = new MemberInfoExample();
			memberInfoExample.createCriteria().andDelFlagEqualTo("0").andMobileEqualTo(mobile);
			int count=memberInfoService.countByExample(memberInfoExample);
			if(count>0){
				throw new ArgException("该手机号已注册过");
			}
			String verificationCode=CommonUtil.getRandomNum(6);
			JSONObject param=new JSONObject();
			
			MobileVerificationCode mobileVerificationCode1 = mobileVerificationCodeService.findModelByMobile(mobile);
			if(mobileVerificationCode1 != null){
				Date createDate = mobileVerificationCode1.getCreateDate();
				Date date = new Date();
				long useDate = (date.getTime()-createDate.getTime())/1000;
				if(useDate <= 60){
					throw new ArgException("60秒内不能重复获取验证码,请稍后在尝试");
				}
			}
			//特定参数
			JSONObject reqData=new JSONObject();
			reqData.put("mobile", mobile);
			reqData.put("content", "告诉你个秘密，你的验证码是"+verificationCode+",千万不要告诉别人哟！");
			reqData.put("smsType", "1");
			reqData.put("sourceClient", "2");
			reqData.put("ip", StringUtil.getIpAddr(request));
			param.put("reqData", reqData);
			//判断<=60s 提示 60秒内不能重复获取验证码，请稍后
			
			//调用短信接口
			JSONObject result=JSONObject.fromObject(HttpUtil.sendPostRequest(Config.getProperty("msgServerUrl")+"/api/sms/sendImmediately", CommonUtil.createReqData(param).toString()));
			if("0000".equals(result.getString("returnCode"))){
				MobileVerificationCode mobileVerificationCode=new MobileVerificationCode();
				mobileVerificationCode.setMobile(mobile);
				mobileVerificationCode.setVerificationCode(verificationCode);
				mobileVerificationCode.setIsChecked("0");
				mobileVerificationCode.setDelFlag("0");
				mobileVerificationCode.setCreateDate(new Date());
				mobileVerificationCodeService.insertSelective(mobileVerificationCode);
			}else{
				throw new ArgException("发送失败，请重新获取!");
			}
			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
		} catch (ArgException arge) {
			return new ResponseMsg(ResponseMsg.ERROR, arge.getMessage());

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}

	}
	
	
	@RequestMapping(value = "/api/n/memberRegister")
	@ResponseBody
	public ResponseMsg memberRegister(HttpServletRequest request) {
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			//系统类型
			String system = "webApp";
			if (JsonUtils.isEmpty(reqDataJson, "mobile")) {
				throw new ArgException("请输入手机号");
			}
			if(!StringUtil.isMobile(reqDataJson.getString("mobile"))){
				throw new ArgException("请输入正确的手机号");
			}
			if(StringUtil.isMobile(reqDataJson.getString("mobileVerificationCode"))){
				throw new ArgException("请输入验证码");
			}
			if(JsonUtils.isEmpty(reqDataJson, "password")){
				throw new ArgException("请输入密码");
			}
			
			String mobile=reqDataJson.getString("mobile");
			String password = reqDataJson.getString("password");
			MemberInfoExample memberInfoExample = new MemberInfoExample();
			memberInfoExample.createCriteria().andDelFlagEqualTo("0").andMobileEqualTo(mobile);
			int count=memberInfoService.countByExample(memberInfoExample);
			if(count>0){
				throw new ArgException("该手机号已注册过，请直接登录!");
			}
			
			if(!mobileVerificationCodeService.verify(mobile,reqDataJson.getString("mobileVerificationCode"))){
				throw new ArgException("验证码错误!");
			}
			String regClient = "";
			String ua = request.getHeader("user-agent").toLowerCase();
			if (!StringUtil.isBlank(ua) && ua.indexOf("micromessenger")>0) {
				//微信浏览器打开就只有微信公众号支付支付
				regClient = Const.MICRO_MALL;
			}else{
				regClient = Const.WEB_APP;
			}
			Integer platformContactId = 0;
			if(reqDataJson.has("platformContactId")){
				platformContactId = reqDataJson.getInt("platformContactId");
			}
			JSONObject returnData = memberInfoService.addMemberInfoRegister(system,mobile,password,regClient,request,platformContactId);
			
			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, returnData);
		} catch (ArgException arge) {
			return new ResponseMsg(ResponseMsg.ERROR, arge.getMessage());

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}

	}
	
	/**
	 * 
	 * 方法描述 ：游客更新信息成为正式会员
	 * @author  chenwf: 
	 * @date 创建时间：2017年6月12日 下午3:38:49 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/y/visitorsRegisteredMembers")
	@ResponseBody
	public ResponseMsg visitorsRegisteredMembers(HttpServletRequest request) {
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			
			Object[] obj = {"mobile", "mobileVerificationCode"};
			this.requiredStr(obj, request);
			Integer memberId = getMemberId(request);
			JSONObject returnData = memberInfoService.visitorsRegisteredMembers(reqPRM, reqDataJson, memberId,request);
			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, returnData);
		} catch (ArgException arge) {
			return new ResponseMsg(ResponseMsg.ERROR, arge.getMessage());

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}

	}
	
	/**
	 * 方法描述 ：获取验证码(重置密码 使用)
	 * @author  chenwf: 
	 * @date 创建时间：2017年4月19日 上午10:14:03 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/n/getMobileVerificationCode4ResetPassword")
	@ResponseBody
	public ResponseMsg getMobileVerificationCode4ResetPassword(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");
			
			if(JsonUtils.isEmpty(reqDataJson, "mobile")){
				throw new ArgException("请输入手机号");
			}
			
			if(JsonUtils.isEmpty(reqDataJson, "validCode")){
				throw new ArgException("图形验证码不能为空");
			}
			String validCode = reqDataJson.getString("validCode");
			if(request.getSession().getAttribute(BaseDefine.CAPTCHA)==null){
				throw new ArgException("请刷新图形验证码");
			}
			String captcha = (String) request.getSession().getAttribute(BaseDefine.CAPTCHA);
			if (!captcha.toLowerCase().equals(validCode.toLowerCase())) {
				throw new ArgException("图形验证码错误");
			}else{
				request.getSession().removeAttribute(BaseDefine.CAPTCHA);
			}
			
			String mobile=reqDataJson.getString("mobile");
			
			/*if(StringUtil.isMobile(mobile)){
				throw new ArgException("请输入正确的手机号");
			}*/
			
			MemberInfoExample memberInfoExample = new MemberInfoExample();
			memberInfoExample.createCriteria().andDelFlagEqualTo("0").andMobileEqualTo(mobile);
			int count = memberInfoService.countByExample(memberInfoExample);
			if(count <= 0){
				throw new ArgException("手机号码未注册");
			}
			MobileVerificationCode mobileVerificationCode1 = mobileVerificationCodeService.findModelByMobile(mobile);
			if(mobileVerificationCode1 != null){
				Date createDate = mobileVerificationCode1.getCreateDate();
				Date date = new Date();
				long useDate = (date.getTime()-createDate.getTime())/1000;
				if(useDate <= 60){
					throw new ArgException("60秒内不能重复获取验证码,请稍后在尝试");
				}
			}
			String verificationCode=CommonUtil.getRandomNum(6);
			
			JSONObject param=new JSONObject();
			//特定参数
			JSONObject reqData=new JSONObject();
			reqData.put("mobile", mobile);
			reqData.put("content", "告诉你个秘密，你的验证码是"+verificationCode+"，千万不要告诉别人哟！");
			reqData.put("smsType", "1");
			reqData.put("sourceClient", "2");
			reqData.put("ip", StringUtil.getIpAddr(request));
			param.put("reqData", reqData);
			
			//调用短信接口
			JSONObject result=JSONObject.fromObject(HttpUtil.sendPostRequest(Config.getProperty("msgServerUrl")+"/api/sms/sendImmediately", CommonUtil.createReqData(param).toString()));
			if("0000".equals(result.getString("returnCode"))){
				MobileVerificationCode mobileVerificationCode=new MobileVerificationCode();
				mobileVerificationCode.setMobile(mobile);
				mobileVerificationCode.setVerificationCode(verificationCode);
				mobileVerificationCode.setIsChecked("0");
				mobileVerificationCode.setDelFlag("0");
				mobileVerificationCode.setCreateDate(new Date());
				mobileVerificationCodeService.insertSelective(mobileVerificationCode);
			}else{
				throw new ArgException("发送失败，请重新获取!");
			}
			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
		} catch (ArgException arge) {
			return new ResponseMsg(ResponseMsg.ERROR, arge.getMessage());

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}
	}
	
	/**
	 * 
	 * 方法描述 ：验证短信验证码
	 * @author  chenwf: 
	 * @date 创建时间：2017年4月19日 上午11:15:07 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/n/verificationSMSCode")
	@ResponseBody
	public ResponseMsg verificationSMSCode(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");
			
			String mobile = reqDataJson.getString("mobile");
			String mobileVerificationCode = reqDataJson.getString("mobileVerificationCode");
			
			if(StringUtil.isBlank(mobile)){
				throw new ArgException("手机号码不能为空");
			}
			if(StringUtil.isBlank(mobileVerificationCode)){
				throw new ArgException("验证码不能为空");
			}
			
			if(!mobileVerificationCodeService.verify(mobile,mobileVerificationCode)){
				throw new ArgException("验证码错误");
			}else{
				return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG);
			}
			
		} catch (ArgException arge) {
			return new ResponseMsg(ResponseMsg.ERROR, arge.getMessage());

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}
		
	}
	/**
	 * 
	 * 方法描述 ：重置密码
	 * @author  chenwf: 
	 * @date 创建时间：2017年4月19日 上午10:16:57 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/n/resetPassword")
	@ResponseBody
	public ResponseMsg resetPassword(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");
			
			String mobile = reqDataJson.getString("mobile");
			
			String password = reqDataJson.getString("password");
			
			MemberInfoExample memberInfoExample = new MemberInfoExample();
			List<MemberInfo> memberInfoList;
			if(StringUtil.isBlank(mobile)){
				throw new ArgException("手机号码不能为空");
			}else{
				memberInfoExample.createCriteria().andDelFlagEqualTo("0").andMobileEqualTo(mobile).andMVerfiyFlagEqualTo("1");
				memberInfoList = memberInfoService.selectByExample(memberInfoExample);
			}
			
			if(StringUtil.isBlank(password)){
				throw new ArgException("密码不能为空");
			}else{
				if(CollectionUtils.isNotEmpty(memberInfoList)){
					MemberInfo memberInfo = memberInfoList.get(0);
					memberInfo.setLoginPass(password);
					memberInfoService.updateByPrimaryKeySelective(memberInfo);
					return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
				}else{
					throw new ArgException("该用户不存在");
				}
			}
		} catch (ArgException arge) {
			return new ResponseMsg(ResponseMsg.ERROR, arge.getMessage());

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}
	}
	
	@RequestMapping(value = "/api/y/addLoginLogByStartUp")
	@ResponseBody
	public ResponseMsg addLoginLogByStartUp(HttpServletRequest request){
		try {
			String regClient = "";
			String ua = request.getHeader("user-agent").toLowerCase();
			if (!StringUtil.isBlank(ua) && ua.indexOf("micromessenger")>0) {
				//微信浏览器打开就只有微信公众号支付支付
				regClient = "MICRO_MALL";
			}else{
				regClient = "WEB_APP";
			}
			Integer memberId = getMemberId(request);
			AppLoginLog appLoginLog = new AppLoginLog();
			appLoginLog.setCreateDate(new Date());
			appLoginLog.setDelFlag("0");
			appLoginLog.setMemberId(memberId);
			appLoginLog.setSystem(regClient);
			appLoginLog.setType("2");
			appLoginLogService.saveModel(appLoginLog);
			
			SysAppMessage sysAppMessage;
			long sysTime = 0;
			long tranTime = 0;
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("memberId", memberId);
			//系统消息红点
			List<SysAppMessageCustom> systemMsgs = sysAppMessageService.getSystemMsg(params);
			if(CollectionUtil.isNotEmpty(systemMsgs)){
				sysAppMessage = systemMsgs.get(0);
				sysTime = sysAppMessage.getCreateDate().getTime();
			}
			//交易消息红点
			List<SysAppMessageCustom> transactionMsgs = sysAppMessageService.getTransactionMsg(params);
			if(CollectionUtil.isNotEmpty(transactionMsgs)){
				sysAppMessage = transactionMsgs.get(0);
				tranTime = sysAppMessage.getCreateDate().getTime();
			}
			//系统启动页  cfg  APP_START_IMAGE
			String systemBootPage = "";
			List<SysParamCfg> sysParamCfgs = DataDicUtil.getSysParamCfg("APP_START_IMAGE_ANDROID");
			if(CollectionUtils.isNotEmpty(sysParamCfgs)){
				systemBootPage = sysParamCfgs.get(0).getParamValue();
			}
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("sysTime", sysTime);
			map.put("tranTime", tranTime);
			map.put("systemBootPage", systemBootPage);
			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG,map);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}
	}
	
	/**
	 * 
	 * 方法描述 ：获取验证码(注册，重置密码不适用)
	 * @author  chenwf: 
	 * @date 创建时间：2017年8月24日 上午9:17:58 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/n/getVerificationCode")
	@ResponseBody
	public ResponseMsg getVerificationCode(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");
			
			if(JsonUtils.isEmpty(reqDataJson, "mobile")){
				throw new ArgException("请输入手机号");
			}
			
			if(JsonUtils.isEmpty(reqDataJson, "validCode")){
				throw new ArgException("图形验证码不能为空");
			}
			String validCode = reqDataJson.getString("validCode");
			if(request.getSession().getAttribute(BaseDefine.CAPTCHA)==null){
				throw new ArgException("请刷新图形验证码");
			}
			String captcha = (String) request.getSession().getAttribute(BaseDefine.CAPTCHA);
			if (!captcha.toLowerCase().equals(validCode.toLowerCase())) {
				throw new ArgException("图形验证码错误");
			}else{
				request.getSession().removeAttribute(BaseDefine.CAPTCHA);
			}
			
			String mobile=reqDataJson.getString("mobile");			
			
			MobileVerificationCode mobileVerificationCode1 = mobileVerificationCodeService.findModelByMobile(mobile);
			if(mobileVerificationCode1 != null){
				Date createDate = mobileVerificationCode1.getCreateDate();
				Date date = new Date();
				long useDate = (date.getTime()-createDate.getTime())/1000;
				if(useDate <= 60){
					throw new ArgException("60秒内不能重复获取验证码,请稍后在尝试");
				}
			}
			MemberInfo memberInfo = memberInfoService.findModelByMobile(mobile);
			if(memberInfo != null){
				throw new ArgException("该手机号码已被绑定");
			}
			String verificationCode=CommonUtil.getRandomNum(6);
			
			JSONObject param=new JSONObject();
			//特定参数
			JSONObject reqData=new JSONObject();
			reqData.put("mobile", mobile);
			reqData.put("content", "告诉你个秘密，你的验证码是"+verificationCode+"，千万不要告诉别人哟！");
			reqData.put("smsType", "1");
			reqData.put("sourceClient", "2");
			reqData.put("ip", StringUtil.getIpAddr(request));
			param.put("reqData", reqData);
			
			//调用短信接口
			JSONObject result=JSONObject.fromObject(HttpUtil.sendPostRequest(Config.getProperty("msgServerUrl")+"/api/sms/sendImmediately", CommonUtil.createReqData(param).toString()));
			if("0000".equals(result.getString("returnCode"))){
				MobileVerificationCode mobileVerificationCode=new MobileVerificationCode();
				mobileVerificationCode.setMobile(mobile);
				mobileVerificationCode.setVerificationCode(verificationCode);
				mobileVerificationCode.setIsChecked("0");
				mobileVerificationCode.setDelFlag("0");
				mobileVerificationCode.setCreateDate(new Date());
				mobileVerificationCodeService.insertSelective(mobileVerificationCode);
			}else{
				throw new ArgException("发送失败，请重新获取!");
			}
			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
		} catch (ArgException arge) {
			return new ResponseMsg(ResponseMsg.ERROR, arge.getMessage());

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}
	}
	
	/**
	 * 
	 * 方法描述 ：快速登入获取验证码
	 * @author  chenwf: 
	 * @date 创建时间：2017年12月15日 下午3:21:40 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/n/getMobileLoginCode")
	@ResponseBody
	public ResponseMsg getMobileLoginCode(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");
			
			if(JsonUtils.isEmpty(reqDataJson, "mobile")){
				throw new ArgException("请输入手机号");
			}
			
			if(JsonUtils.isEmpty(reqDataJson, "validCode")){
				throw new ArgException("图形验证码不能为空");
			}
			String validCode = reqDataJson.getString("validCode");
			
			if(request.getSession().getAttribute(BaseDefine.CAPTCHA)==null){
				throw new ArgException("请刷新图形验证码");
			}
			
			String captcha = (String) request.getSession().getAttribute(BaseDefine.CAPTCHA);
			if (!captcha.toLowerCase().equals(validCode.toLowerCase())) {
				throw new ArgException("图形验证码错误");
			}else{
				request.getSession().removeAttribute(BaseDefine.CAPTCHA);
			}
			
			String mobile=reqDataJson.getString("mobile");			
			
			MobileVerificationCode mobileVerificationCode1 = mobileVerificationCodeService.findModelByMobile(mobile);
			if(mobileVerificationCode1 != null){
				Date createDate = mobileVerificationCode1.getCreateDate();
				Date date = new Date();
				long useDate = (date.getTime()-createDate.getTime())/1000;
				if(useDate <= 60){
					throw new ArgException("60秒内不能重复获取验证码,请稍后在尝试");
				}
			}
			String verificationCode=CommonUtil.getRandomNum(6);
			
			JSONObject param=new JSONObject();
			//特定参数
			JSONObject reqData=new JSONObject();
			reqData.put("mobile", mobile);
			reqData.put("content", "告诉你个秘密，你的验证码是"+verificationCode+"，千万不要告诉别人哟！");
			reqData.put("smsType", "1");
			reqData.put("sourceClient", "2");
			reqData.put("ip", StringUtil.getIpAddr(request));
			param.put("reqData", reqData);
			
			//调用短信接口
			JSONObject result=JSONObject.fromObject(HttpUtil.sendPostRequest(Config.getProperty("msgServerUrl")+"/api/sms/sendImmediately", CommonUtil.createReqData(param).toString()));
			if("0000".equals(result.getString("returnCode"))){
				MobileVerificationCode mobileVerificationCode=new MobileVerificationCode();
				mobileVerificationCode.setMobile(mobile);
				mobileVerificationCode.setVerificationCode(verificationCode);
				mobileVerificationCode.setIsChecked("0");
				mobileVerificationCode.setDelFlag("0");
				mobileVerificationCode.setCreateDate(new Date());
				mobileVerificationCodeService.insertSelective(mobileVerificationCode);
			}else{
				throw new ArgException("发送失败，请重新获取!");
			}
			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
		} catch (ArgException arge) {
			return new ResponseMsg(ResponseMsg.ERROR, arge.getMessage());

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}
	}
	
	/**
	 * 输入邀请码（生成上下级关系）
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/y/updateMemberInvitationCode")
	@ResponseBody
	public ResponseMsg updateMemberInvitationCode(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");
			
			Object[] obj = {"invitationCode"};
			this.requiredStr(obj,request);
			
			Map<String, Object> map = memberInfoService.updateMemberInvitationCode(reqPRM,reqDataJson,request);
			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG,map);
		} catch (ArgException arge) {
			return new ResponseMsg(ResponseMsg.ERROR, arge.getMessage());

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}
	}
}
