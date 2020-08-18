package com.jf.controller;

import com.jf.common.AppContext;
import com.jf.common.base.ArgException;
import com.jf.common.base.ResponseMsg;
import com.jf.common.constant.Const;
import com.jf.common.utils.CollectionUtil;
import com.jf.common.utils.CommonUtil;
import com.jf.common.utils.Config;
import com.jf.common.utils.DataDicUtil;
import com.jf.common.utils.FileUtil;
import com.jf.common.utils.HttpUtil;
import com.jf.common.utils.JsonUtils;
import com.jf.common.utils.StringUtil;
import com.jf.controller.base.BaseController;
import com.jf.entity.AppLoginLog;
import com.jf.entity.IntegralType;
import com.jf.entity.MemberAccount;
import com.jf.entity.MemberInfo;
import com.jf.entity.MemberInfoExample;
import com.jf.entity.MobileVerificationCode;
import com.jf.entity.SysAppMessage;
import com.jf.entity.SysAppMessageCustom;
import com.jf.entity.SysAppMessageExt;
import com.jf.entity.SysParamCfg;
import com.jf.service.AppLoginLogService;
import com.jf.service.GrowthDtlService;
import com.jf.service.IntegralDtlService;
import com.jf.service.IntegralTypeService;
import com.jf.service.MemberAccountService;
import com.jf.service.MemberExtendService;
import com.jf.service.MemberInfoService;
import com.jf.service.MemberJgRelationService;
import com.jf.service.MemberMobileWeixinMapService;
import com.jf.service.MemberStatusLogService;
import com.jf.service.MobileVerificationCodeService;
import com.jf.service.SysAppMessageService;
import com.jf.service.jixin.JiXinService;
import com.jf.vo.request.FastBindMobileRequest;
import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MemberController extends BaseController{

	@Resource
	private MemberInfoService memberInfoService;
	@Resource
	private MobileVerificationCodeService mobileVerificationCodeService;
	@Resource
	private MemberAccountService memberAccountService;
	@Resource
	private AppLoginLogService appLoginLogService;
	@Resource
	private IntegralTypeService integralTypeService;
	@Resource
	private GrowthDtlService growthDtlService;
	@Resource
	private IntegralDtlService integralDtlService;
	@Resource
	private SysAppMessageService sysAppMessageService;
	@Resource
	private MemberStatusLogService memberStatusLogService;
	@Resource
	private MemberMobileWeixinMapService memberMobileWeixinMapService;
	@Resource
	private MemberJgRelationService memberJgRelationService;
	@Resource
	private MemberExtendService memberExtendService;
	@Autowired
	private AppContext appContext;
	@Autowired
	private JiXinService jiXinService;
	
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
			reqData.put("sourceClient", "1");
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
			
			Object[] obj = {"regIp","deviceId","mobileBrand","mobileModel","version","versionNum","system","sprChnl"};
			this.requiredStr(obj, request);
			//渠道类型
			String sprChnl = reqDataJson.getString("sprChnl");
			sprChnl = DataDicUtil.getSprChnl(sprChnl);
			//TODO 注册地区 暂时固定值为 厦门
			//String regArea = reqDataJson.getString("regArea");
			//设备id
			String deviceId = reqDataJson.getString("deviceId");
			//手机品牌
			String mobileBrand = reqDataJson.getString("mobileBrand");
			//手机品牌
			String mobileModel = reqDataJson.getString("mobileModel");
			//系统类型
			String system = reqDataJson.getString("system");
			//版本
			String version = reqDataJson.getString("version");
			//版本号
			String versionNum = reqDataJson.getString("versionNum");
			//注册IP
			String regIp = StringUtil.getIpAddr(request);
			//序列号
			String imei = "";
			if(!JsonUtils.isEmpty(reqDataJson, "imei")){
				imei = reqDataJson.getString("imei");
			}
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
			if(system.equals("IOS")){
				regClient = "1";
				if(!StringUtil.isBlank(mobileModel) && mobileModel.contains(",")){
					String newIosMobileModel = DataDicUtil.getStatusDesc("BU_MEMBER_INFO", "REQ_MOBILE_MODEL", mobileModel);
					if(!StringUtil.isBlank(newIosMobileModel)){
						mobileModel = newIosMobileModel;
					}
				}
			}else{
				regClient = "2";
			}
			
			MemberInfo memberInfo=new MemberInfo();
			memberInfo.setCreateDate(new Date());
			memberInfo.setUpdateDate(new Date());
			memberInfo.setDelFlag("0");
			memberInfo.setSprChnl(sprChnl);
			memberInfo.setmVerfiyFlag("1");
			memberInfo.seteVerfiyFlag("0");
			memberInfo.setType("1");
			//memberInfo.setLoginCode(mobile);
			memberInfo.setRegClient(regClient);
			memberInfo.setRegIp(regIp);
			memberInfo.setMobile(mobile);
			memberInfo.setNick(mobile);
			memberInfo.setLoginPass(reqDataJson.getString("password"));
			memberInfo.setGroupId(1);//注册会员标识
			memberInfo.setReqImei(imei);
			memberInfo.setReqMobileBrand(mobileBrand);
			memberInfo.setReqMobileModel(mobileModel);
			memberInfoService.insertSelective(memberInfo); 
			//金额和积分的兑换比例
			IntegralType integralType = integralTypeService.findModel(Integer.valueOf(Const.INTEGRAL_TYPE_MOBILE_AUTHENTICATION));
			Integer integral = 0;
			Integer gValue = 0;
			if(integralType != null){
				if(integralType.getIntType().equals("1")){
					integral = integralType.getIntegral() == null ? 0 : integralType.getIntegral();
				}
				if(integralType.getGrowType().equals("1")){
					gValue = integralType.getgValue() == null ? 0 : integralType.getgValue();
				}
			}
			//创建用户账户信息
			MemberAccount memberAccount = new MemberAccount();
			memberAccount.setMemberId(memberInfo.getId());
			memberAccount.setCreateBy(memberInfo.getId());
			memberAccount.setCreateDate(new Date());
			memberAccount.setDelFlag("0");
			memberAccount.setIntegral(integral);
			memberAccount.setgValue(gValue);
			memberAccountService.insertSelective(memberAccount);
			if(gValue != 0){
				growthDtlService.addGrowthDtl(gValue, gValue, memberAccount.getId(), memberInfo.getId(), null, Const.INTEGRAL_TYPE_MOBILE_AUTHENTICATION);
			}
			if(integral != 0){
				integralDtlService.addIntegralDtl(memberAccount.getId(), Const.INTEGRAL_TALLY_MODE_INCOME, 
						Const.INTEGRAL_TYPE_MOBILE_AUTHENTICATION, integral, integral, null, memberInfo.getId(),"1");
			}
			String systemVersion = null;
			if(reqPRM.containsKey("systemVersion")){
				systemVersion = reqPRM.getString("systemVersion");
			}
			//添加会员注册状态
			memberStatusLogService.addMemberRegisterStatus(memberInfo.getId(), Const.MEMBER_INFO_STATUS_ACTIVATION_A);
			//会员扩展
			memberExtendService.addModel(memberInfo.getId(),"","", null);
			JSONObject returnData = memberInfoService.thirdLogin(memberInfo, deviceId, regIp, mobileBrand, mobileModel,
					system, version, versionNum, imei, "1", systemVersion);
	
			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, returnData);
		} catch (ArgException arge) {
			return new ResponseMsg(ResponseMsg.ERROR, arge.getMessage());

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}

	}

	/**
	 * 绑定手机号（一键绑定）
	 */
	@RequestMapping(value = "/api/y/fastBindMobile")
	@ResponseBody
	public ResponseMsg bindMobile() {
		FastBindMobileRequest request = appContext.getRequestAndValidate(FastBindMobileRequest.class);
		String mobile = jiXinService.getPhoneNumber(request.getToken(), request.getAuthCode(), request.getCarrier(), request.getSystem());
		if (StringUtil.isBlank(mobile)) {
			return ResponseMsg.error("一键绑定失败");
		}
		try {
			return memberInfoService.doBindMobile(mobile, request.getMemberId(), null, request.getDeviceId());
		} catch (ArgException arge) {
			return ResponseMsg.error(arge.getMessage());
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

			Object[] obj = { "memberId", "mobile", "password", "mobileVerificationCode","regIp","deviceId","mobileBrand","mobileModel","version","versionNum","system" };
			this.requiredStr(obj, request);

			Integer memberId = reqDataJson.getInt("memberId");
			String mobile=reqDataJson.getString("mobile");
			String password=reqDataJson.getString("password");
			String mobileVerificationCode = reqDataJson.getString("mobileVerificationCode");
			//设备id
			String deviceId = reqDataJson.getString("deviceId");
			if(!StringUtil.isMobile(reqDataJson.getString("mobile"))){
				throw new ArgException("请输入正确的手机号");
			}

			if(!mobileVerificationCodeService.verify(mobile,mobileVerificationCode)){
				throw new ArgException("验证码错误!");
			}

			//从游客变为正式会员
			return memberInfoService.doBindMobile(mobile, memberId, password, deviceId);
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
			reqData.put("sourceClient", "1");
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
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");
			
			Object[] obj = {"memberId","regIp","mobileBrand","mobileModel","system","version","versionNum"};
			this.requiredStr(obj,request);
			//注册IP
			String regIp = StringUtil.getIpAddr(request);
			//手机品牌
			String mobileBrand = reqDataJson.getString("mobileBrand");
			//手机品牌
			String mobileModel = reqDataJson.getString("mobileModel");
			//系统类型
			String system = reqDataJson.getString("system");
			//版本
			String version = reqDataJson.getString("version");
			//序列号
			String imei = "";
			if(reqDataJson.containsKey("imei") && !StringUtil.isBlank(reqDataJson.getString("imei"))){
				imei = reqDataJson.getString("imei");
			}
			//版本号
			String versionNum = reqDataJson.getString("versionNum");
			//会员id
			Integer memberId = reqDataJson.getInt("memberId");
			String systemVersion = null;
			String registrationId = "";
			if(reqPRM.containsKey("systemVersion")){
				systemVersion = reqPRM.getString("systemVersion");
			}
			if(reqDataJson.containsKey("registrationId") && !StringUtil.isBlank(reqDataJson.getString("registrationId"))){
				registrationId = reqDataJson.getString("registrationId");
				memberJgRelationService.addJgRelation(memberId, registrationId);
			}
			MemberInfo memberInfo = memberInfoService.findMemberById(memberId);
			if(memberInfo != null){
				String reqImei = memberInfo.getReqImei();
				if(StringUtil.isBlank(reqImei) && !StringUtil.isBlank(imei)){
					memberInfo.setReqImei(imei);
					memberInfoService.updateByPrimaryKeySelective(memberInfo);
				}
			}
			AppLoginLog appLoginLog = new AppLoginLog();
			appLoginLog.setCreateDate(new Date());
			appLoginLog.setDelFlag("0");
			appLoginLog.setIp(regIp);
			appLoginLog.setMemberId(memberId);
			appLoginLog.setMobileBrand(mobileBrand);
			appLoginLog.setMobileModel(mobileModel);
			appLoginLog.setSystem(system);
			appLoginLog.setVersion(version);
			appLoginLog.setVersionNum(versionNum);
			appLoginLog.setSystemVersion(systemVersion);
			appLoginLog.setImei(imei);
			appLoginLog.setType("2");
			appLoginLogService.saveModel(appLoginLog);
			
			SysAppMessage sysAppMessage;
			long sysTime = 0;
			long tranTime = 0;
			long activitySelectionTime = 0;
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
			//精选活动消息红点
			SysAppMessageExt activitySelection = sysAppMessageService.findLatest(memberId, "3");
			if(activitySelection != null){
				activitySelectionTime = activitySelection.getCreateDate().getTime();
			}
			//系统启动页  cfg  APP_START_IMAGE
			String systemBootPage = "";
			String code = "";
			String systemType = "";
			if(reqPRM.containsKey("system")){
				systemType = reqPRM.getString("system");
			}
			if(systemType.equals(Const.ANDROID)){
				code = "APP_START_IMAGE_ANDROID";
			}else{
				code = "APP_START_IMAGE_IOS";
			}
			List<SysParamCfg> sysParamCfgs = DataDicUtil.getSysParamCfg(code);
			if(CollectionUtils.isNotEmpty(sysParamCfgs)){
				systemBootPage = sysParamCfgs.get(0).getParamValue();
			}
			
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("sysTime", sysTime);
			map.put("tranTime", tranTime);
			map.put("activitySelectionTime", activitySelectionTime);
			map.put("systemBootPage", systemBootPage.replace("http://p.xgbuy.cc", FileUtil.getImageServiceUrl()));
			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG,map);
		} catch (ArgException arge) {
			return new ResponseMsg(ResponseMsg.ERROR, arge.getMessage());

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
			reqData.put("sourceClient", "1");
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
			reqData.put("sourceClient", "1");
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
	 * 方法描述 ：系统启动页
	 * @author  chenwf: 
	 * @date 创建时间：2018年6月25日 下午2:32:51 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/n/getSystemBootPage")
	@ResponseBody
	public ResponseMsg getSystemBootPage(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			
			//系统启动页  cfg  APP_START_IMAGE
			List<String> list = new ArrayList<String>();
			String code = "";
			String systemType = "";
			//清除缓存版本号
			String appClearVersion = "0";
			String appClearMark = "0";
			if(reqPRM.containsKey("system")){
				systemType = reqPRM.getString("system");
			}
			if(systemType.equals(Const.ANDROID)){
				code = "APP_START_IMAGE_ANDROID";
			}else{
				code = "APP_START_IMAGE_IOS";
			}
			List<SysParamCfg> sysParamCfgs = DataDicUtil.getSysParamCfg(code);
			if(CollectionUtils.isNotEmpty(sysParamCfgs)){
				String systemBootPage = StringUtil.getPic(sysParamCfgs.get(0).getParamValue(), "");
				list.add(systemBootPage);
			}
			
			List<SysParamCfg> versionCfgs = DataDicUtil.getSysParamCfg("APP_CLEAN_CACHE");
			if(CollectionUtils.isNotEmpty(versionCfgs)){
				appClearVersion = versionCfgs.get(0).getParamValue();
				appClearMark = versionCfgs.get(0).getParamName();
			}
			//隐私政策url
			String privacyPolicyUrl = Config.getProperty("mUrl")+"/xgbuy/views/index.html?redirect_url=link/information.html";
			//投诉举报电话 
			String appealMobile = "";
			String customerServiceWorkTime = "";
			List<SysParamCfg> sysParamCfgMobileList = DataDicUtil.getSysParamCfg("APPEAL_MOBILE");
			if(CollectionUtils.isNotEmpty(sysParamCfgMobileList)){
				appealMobile = sysParamCfgMobileList.get(0).getParamValue();
				customerServiceWorkTime = sysParamCfgMobileList.get(0).getParamName();
			}
			//醒购知识产权保护平台
			String intellectualPropertyRightUrl = "";
			List<SysParamCfg> sysParamCfgUrlList = DataDicUtil.getSysParamCfg("INTELLECTUAL_PROPERTY_RIGHT_URL");
			if(CollectionUtils.isNotEmpty(sysParamCfgUrlList)){
				intellectualPropertyRightUrl = sysParamCfgUrlList.get(0).getParamValue();
			}
			String appealContent = "温馨提示：如有涉及知识产权投诉，请前往醒购知识产权保护平台";
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("privacyPolicyUrl", privacyPolicyUrl);
			map.put("systemBootPages", list);
			map.put("appClearVersion", appClearVersion);
			map.put("appClearMark", appClearMark);
			map.put("appealMobile", appealMobile);
			map.put("customerServiceWorkTime", customerServiceWorkTime);
			map.put("intellectualPropertyRightUrl", intellectualPropertyRightUrl);
			map.put("appealContent", appealContent);
			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG,map);
		} catch (ArgException arge) {
			return new ResponseMsg(ResponseMsg.ERROR, arge.getMessage());

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}
	}
	
	/**
	 * 获取会员注销条件
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/y/getMemberCancellationCondition")
	@ResponseBody
	public ResponseMsg getMemberCancellationCondition(HttpServletRequest request) {
		try {
			String content = "";
			List<SysParamCfg> bulletinBoardPics = DataDicUtil.getSysParamCfg("APP_ACCOUNT_CANCEL_PIC");
			if(CollectionUtils.isNotEmpty(bulletinBoardPics)){
				content = StringUtil.getPic(bulletinBoardPics.get(0).getParamValue(), "");
			}
			
			List<String> list = Arrays.asList("安全/隐私顾虑","这是多余账户","醒购购物遇到困难","其他原因");
			List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
			for (String str : list) {
				Map<String, Object> dataMap = new HashMap<String, Object>();
				boolean selection = false;
				String contentId = "";
				if("安全/隐私顾虑".equals(str)){
					selection = true;
					contentId = "2";
				}else if("这是多余账户".equals(str)){
					contentId = "3";
				}else if("醒购购物遇到困难".equals(str)){
					contentId = "4";
				}else if("其他原因".equals(str)){
					contentId = "5";
				}
				dataMap.put("contentId", contentId);
				dataMap.put("content", str);
				dataMap.put("selection", selection);
				dataList.add(dataMap);
			}
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("content", content);
			map.put("dataList", dataList);
			map.put("confirmCancel", "账户注销后，您将无法登陆醒购平台。根据法律法规，您的交易数据将至少保留三年。");
			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG,map);
		} catch (ArgException arge) {
			return new ResponseMsg(ResponseMsg.ERROR, arge.getMessage());

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}
	}
	
	/**
	 * 注销用户(更改会员激活状态)
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/y/updateMemberCancellation")
	@ResponseBody
	public ResponseMsg updateMemberCancellation(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");
			
			Integer memberId = reqDataJson.getInt("memberId");//会员id
			String cancelReason = reqDataJson.getString("cancelReason");//会员id
			Map<String, Object> map = memberInfoService.memberJudgeCanCancal(memberId,cancelReason);
			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG,map);
		} catch (ArgException arge) {
			return new ResponseMsg(ResponseMsg.ERROR, arge.getMessage());

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}
	}
	
	/**
	 * 会员关联手机或关联微信用户
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/y/getMemberRelation")
	@ResponseBody
	public ResponseMsg getMemberRelation(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");
			
			Map<String, Object> map = memberMobileWeixinMapService.getMemberRelation(reqPRM, reqDataJson);
			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG,map);
		} catch (ArgException arge) {
			return new ResponseMsg(ResponseMsg.ERROR, arge.getMessage());

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}
	}
	
	
	@RequestMapping(value = "/api/y/addJgRelation")
	@ResponseBody
	public ResponseMsg addJgRelation(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");
			
			Object[] obj = {"memberId"};
			this.requiredStr(obj,request);
			//会员id
			Integer memberId = reqDataJson.getInt("memberId");
			String registrationId = "";
			if(reqDataJson.containsKey("registrationId") && !StringUtil.isBlank(reqDataJson.getString("registrationId"))){
				registrationId = reqDataJson.getString("registrationId");
				memberJgRelationService.addJgRelation(memberId, registrationId);
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
			
			Map<String, Object> map = memberInfoService.updateMemberInvitationCode(reqPRM,reqDataJson);
			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG,map);
		} catch (ArgException arge) {
			return new ResponseMsg(ResponseMsg.ERROR, arge.getMessage());

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}
	}
}
