package com.jf.controller;

import com.jf.common.AppContext;
import com.jf.common.base.ArgException;
import com.jf.common.base.ResponseMsg;
import com.jf.common.constant.Const;
import com.jf.common.utils.DataDicUtil;
import com.jf.common.utils.FileUtil;
import com.jf.common.utils.JsonUtils;
import com.jf.common.utils.StringUtil;
import com.jf.controller.base.BaseController;
import com.jf.dao.InformationMapper;
import com.jf.entity.AppAccessToken;
import com.jf.entity.AppAccessTokenExample;
import com.jf.entity.Information;
import com.jf.entity.InformationExample;
import com.jf.entity.MemberInfo;
import com.jf.entity.MemberInfoExample;
import com.jf.entity.SysParamCfg;
import com.jf.entity.SysParamCfgExample;
import com.jf.entity.SysStatus;
import com.jf.service.AppAccessTokenService;
import com.jf.service.MemberInfoService;
import com.jf.service.MobileVerificationCodeService;
import com.jf.service.OrderDtlService;
import com.jf.service.SubDepositOrderService;
import com.jf.service.SysParamCfgService;
import com.jf.service.jixin.JiXinService;
import com.jf.vo.request.FastLoginRequest;
import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MainController extends BaseController{

	@Resource
	private MemberInfoService memberInfoService;
	@Resource
	private AppAccessTokenService appAccessTokenService;
	@Resource
	private SysParamCfgService sysParamCfgService;
	@Resource
	private MobileVerificationCodeService mobileVerificationCodeService;
	@Resource
	private InformationMapper informationMapper;
	@Resource
	private SubDepositOrderService subDepositOrderService;
	@Resource
	private OrderDtlService orderDtlService;
	@Autowired
	private AppContext appContext;
	@Autowired
	private JiXinService jiXinService;

	/**
	 * 一键登录
	 */
	@RequestMapping(value = "/api/n/fastLogin")
	@ResponseBody
	public ResponseMsg fastLogin() {
		FastLoginRequest request = appContext.getRequestAndValidate(FastLoginRequest.class);
		request.setSprChnl(DataDicUtil.getSprChnl(request.getSprChnl()));
		String system = request.getSystem();
		String mobile = jiXinService.getPhoneNumber(request.getToken(), request.getAuthCode(), request.getCarrier(), system);
		if (StringUtil.isBlank(mobile)) {
			return ResponseMsg.error("一键授权失败");
//			return ResponseMsg.error("4010", "本机号码获取失败");
		}

		Date now = new Date();
		String version = request.getVersion();
		String ip = appContext.getIpAddr();
		String imei = request.getImei() == null ? "" : request.getImei();
		String type = Const.MEMBER_INFO_TYPE_MEMBER;
		String systemVersion = appContext.systemVersion();
		String regClient = "IOS".equals(system) ? "1" : "2";

		MemberInfo memberInfo = memberInfoService.findModelByMobile(mobile);
		if (memberInfo == null) { //没有该用户，注册用户信息
			memberInfo = memberInfoService.addMemberInfo(type, mobile, null, null,
					null, request.getSprChnl(), null, regClient, ip, now, imei,
					request.getMobileBrand(), request.getMobileModel(), null, null, null, mobile, "1");
		} else {
			Map<String, String> map = memberInfoService.checkMemberStatus(memberInfo, system, Integer.parseInt(version), "3"); // 3登陆限制
			if ("false".equals(map.get("success"))) {
				return ResponseMsg.success(map);
			}
			memberInfoService.updateMemberExtend(memberInfo.getId(), request.getSprChnl());
		}

		JSONObject returnData = memberInfoService.thirdLogin(memberInfo, request.getDeviceId(), ip, request.getMobileBrand(), request.getMobileModel(),
				system, version, request.getVersionNum(), imei, type,systemVersion);
		return ResponseMsg.success(returnData);
	}
	
	@RequestMapping(value = "/api/n/login")
	@ResponseBody
	public ResponseMsg login(HttpServletRequest request) {
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数

			if (JsonUtils.isEmpty(reqDataJson, "loginCode")) {
				throw new ArgException("请输入帐号");
			}
			if (JsonUtils.isEmpty(reqDataJson, "password")) {
				throw new ArgException("请输入密码");
			}
			String loginCode = reqDataJson.getString("loginCode");
			String password = reqDataJson.getString("password");
			String system = reqDataJson.getString("system");
			String mobileBrand = reqDataJson.getString("mobileBrand");
			String mobileModel = reqDataJson.getString("mobileModel");
			String version = reqDataJson.getString("version");
			String versionNum = reqDataJson.getString("versionNum");
			String ip = StringUtil.getIpAddr(request);
			String imei = "";
			if(!JsonUtils.isEmpty(reqDataJson, "imei")){
				imei = reqDataJson.getString("imei");
			}
			String deviceId = reqDataJson.getString("deviceId");
			MemberInfoExample memberInfoExample = new MemberInfoExample();
			memberInfoExample.createCriteria().andDelFlagEqualTo("0").andMobileEqualTo(loginCode).andMVerfiyFlagEqualTo("1");
			List<MemberInfo> memberInfos = memberInfoService.selectByExample(memberInfoExample);
			if (memberInfos == null || memberInfos.size() == 0) {
				throw new ArgException("帐号或密码错误");
			}
			MemberInfo memberInfo = memberInfos.get(0);
			if(StringUtil.isBlank(memberInfo.getLoginPass())){
				throw new ArgException("帐号或密码错误");
			}
			if (!memberInfo.getLoginPass().toLowerCase().equals(password.toLowerCase())) {
				throw new ArgException("帐号或密码错误");
			}
			Map<String, String> map = memberInfoService.checkMemberStatus(memberInfo,reqPRM.getString("system"),reqPRM.getInt("version"),"3");
			if("false".equals(map.get("success"))){
				return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, map);
			}
			String systemVersion = null;
			if(reqPRM.containsKey("systemVersion")){
				systemVersion = reqPRM.getString("systemVersion");
			}
			JSONObject returnData = memberInfoService.thirdLogin(memberInfo, deviceId, ip, mobileBrand, mobileModel,
					system, version, versionNum, imei, "1",systemVersion);
			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, returnData);
		} catch (ArgException arge) {
			return new ResponseMsg(ResponseMsg.ERROR, arge.getMessage());

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}

	}
	
	@RequestMapping(value = "/api/n/mobileLogin")
	@ResponseBody
	public ResponseMsg mobileLogin(HttpServletRequest request) {
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			
			if (JsonUtils.isEmpty(reqDataJson, "loginCode")) {
				throw new ArgException("请输入帐号");
			}
			if (JsonUtils.isEmpty(reqDataJson, "verifyCode")) {
				throw new ArgException("请输入验证码");
			}
			Date createDate = new Date();
			String loginCode = reqDataJson.getString("loginCode");
			String verifyCode = reqDataJson.getString("verifyCode");
			String system = reqDataJson.getString("system");
			String mobileBrand = reqDataJson.getString("mobileBrand");
			String mobileModel = reqDataJson.getString("mobileModel");
			String version = reqDataJson.getString("version");
			String versionNum = reqDataJson.getString("versionNum");
			String ip = StringUtil.getIpAddr(request);
			String imei = "";
			String type = Const.MEMBER_INFO_TYPE_MEMBER;
			if(!JsonUtils.isEmpty(reqDataJson, "imei")){
				imei = reqDataJson.getString("imei");
			}
			String deviceId = reqDataJson.getString("deviceId");
			
			//渠道类型
			String sprChnl = reqDataJson.getString("sprChnl");
			sprChnl = DataDicUtil.getSprChnl(sprChnl);
			String regClient = "";
			if(system.equals("IOS")){
				regClient = "1";
			}else{
				regClient = "2";
			}
			//判断验证码
			if(!mobileVerificationCodeService.verify(loginCode,verifyCode)){
				throw new ArgException("验证码错误");
			}
			
			MemberInfoExample memberInfoExample = new MemberInfoExample();
			memberInfoExample.createCriteria().andDelFlagEqualTo("0").andMobileEqualTo(loginCode).andMVerfiyFlagEqualTo("1");
			List<MemberInfo> memberInfos = memberInfoService.selectByExample(memberInfoExample);
			MemberInfo memberInfo = null;
			if (CollectionUtils.isEmpty(memberInfos)) {
				//没有该用户，注册用户信息
				memberInfo = memberInfoService.addMemberInfo(type, loginCode, null, null, 
						null, sprChnl, null, regClient, ip, createDate, imei, 
						mobileBrand, mobileModel, null, null, null,loginCode,"1");
			}else{
				memberInfo = memberInfos.get(0);
				Map<String, String> map = memberInfoService.checkMemberStatus(memberInfo,reqPRM.getString("system"),reqPRM.getInt("version"),"3");
				if("false".equals(map.get("success"))){
					return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, map);
				}
				memberInfoService.updateMemberExtend(memberInfo.getId(), sprChnl);
			}

			String systemVersion = null;
			if(reqPRM.containsKey("systemVersion")){
				systemVersion = reqPRM.getString("systemVersion");
			}
			JSONObject returnData = memberInfoService.thirdLogin(memberInfo, deviceId, ip, mobileBrand, mobileModel,
					system, version, versionNum, imei, type,systemVersion);
			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, returnData);
		} catch (ArgException arge) {
			return new ResponseMsg(ResponseMsg.ERROR, arge.getMessage());

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}

	}
	
	
	

	@RequestMapping(value = "/api/y/loginOut")
	@ResponseBody
	public ResponseMsg loginOut(HttpServletRequest request) {
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数

			Integer memberId = null;
			if(!JsonUtils.isEmpty(reqDataJson, "memberId")){
				memberId = reqDataJson.getInt("memberId");
			}
			String deviceId = reqDataJson.getString("deviceId");
			String token = reqPRM.getString("token");

			AppAccessTokenExample appAccessTokenExample = new AppAccessTokenExample();
			appAccessTokenExample.createCriteria().andMemberIdEqualTo(memberId).andDeviceIdEqualTo(deviceId).andAccessTokenEqualTo(token);
			List<AppAccessToken> appAccessTokens = appAccessTokenService.selectByExample(appAccessTokenExample);
			if (appAccessTokens != null && appAccessTokens.size() > 0) {
				AppAccessToken appAccessToken = appAccessTokens.get(0);
				appAccessToken.setAccessToken(null);
				appAccessToken.setUpdateDate(new Date());
				appAccessTokenService.updateByPrimaryKey(appAccessToken);
			}

			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}

	}

	@RequestMapping(value = "/n/platform/protocol")
	public String protocol(HttpServletRequest request) {
		return "main/protocol";
	}
	
	/**
	 * 
	 * 方法描述 ：积分规则
	 * @author  chenwf: 
	 * @date 创建时间：2017年12月19日 下午5:58:21 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/n/platform/getIntegralRule")
	public String getIntegralRule(HttpServletRequest request) {
		return "main/integralRule";
	}
	
	/**
	 * 
	 * 方法描述 ：特别说明
	 * @author  chenwf: 
	 * @date 创建时间：2017年4月21日 下午7:01:32 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/n/platform/specialInstructions")
	public String specialInstructions(HttpServletRequest request) {
		return "main/specialInstructions";
	}
	
	/**
	 * 
	 * 方法描述 ：获取信息管理规则
	 * @author  chenwf: 
	 * @date 创建时间：2018年10月16日 下午10:01:32 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/n/platform/getInfomationRule")
	@ResponseBody
	public ResponseMsg getInfomationRule(HttpServletRequest request) {
		//数据库获取
		JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
		JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
		String type = reqDataJson.getString("type");
		String content = "";
		String title = "";
		Integer id = null;
		if("1".equals(type)){//1醒购隐私政策
			id = 159;
			title = "醒购隐私政策";
		}
		if(id != null){
			InformationExample informationExample = new InformationExample();
			informationExample.createCriteria().andIdEqualTo(id).andStatusEqualTo("1").andDelFlagEqualTo("0");
			List<Information> informations = informationMapper.selectByExampleWithBLOBs(informationExample);
			if(CollectionUtils.isNotEmpty(informations)){
				content = informations.get(0).getContent();
			}
		}
		Map<String, String> map = new HashMap<String, String>();
		map.put("content",content);
		map.put("title",title);
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, map);
	}
	
	/**
	 * 
	 * 方法描述 ：获取主页品类
	 * @author  chenwf: 
	 * @date 创建时间：2017年4月25日 下午6:15:36 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/n/getMainCategoryId")
	@ResponseBody
	public ResponseMsg getMainCategoryId(HttpServletRequest request) {
		try {
			List<Map<String,Object>> returnData = new ArrayList<Map<String,Object>>();
			Map<String,Object> map = new HashMap<String,Object>();
			String newUserEnjoyPic = "";
			SysParamCfgExample sysParamCfgExample = new SysParamCfgExample();
			sysParamCfgExample.or().andParamCodeEqualTo("APP_CATALOG");
			sysParamCfgExample.or().andParamCodeEqualTo("APP_NEW_USER_ENJOY");
			sysParamCfgExample.setOrderByClause("param_id");
			List<SysParamCfg> sysParamCfgs = sysParamCfgService.selectByExample(sysParamCfgExample);
			if(CollectionUtils.isNotEmpty(sysParamCfgs)){
				for (SysParamCfg sysParamCfg : sysParamCfgs) {
					Map<String,Object> dataMap = new HashMap<String,Object>();
					
					if(sysParamCfg.getParamCode().equals("APP_NEW_USER_ENJOY")){
						if(!StringUtil.isBlank(sysParamCfg.getParamValue())){
							newUserEnjoyPic = FileUtil.getImageServiceUrl()+sysParamCfg.getParamValue();
						}
					}else{
						dataMap.put("id", sysParamCfg.getParamValue());
						dataMap.put("name", sysParamCfg.getParamName());
						dataMap.put("pic", FileUtil.getImageServiceUrl()+sysParamCfg.getParamDesc());
						//paramOrder 该字段用来排序 现用来储存 广告推荐
						dataMap.put("adCatalog", sysParamCfg.getParamOrder());
						returnData.add(dataMap);
					}
				}
			}
			map.put("newUserEnjoyPic", newUserEnjoyPic);
			map.put("categoryList", returnData);
			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG,map);
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}

	}
	
	/**
	 * 
	 * 方法描述 ：第三方联合登入
	 * @author  chenwf: 
	 * @date 创建时间：2017年5月9日 下午6:20:47 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/n/thirdPartyLogin")
	@ResponseBody
	public ResponseMsg thirdPartyLogin(HttpServletRequest request) {
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			Object[] obj = {"thirdPartyId","thirdPartyMark","regArea","regClient",
					"regIp","deviceId","mobileBrand","mobileModel","version","versionNum","sprChnl","system"};
			this.requiredStr(obj, request);
			String systemVersion = null;
			if(reqPRM.containsKey("systemVersion")){
				systemVersion = reqPRM.getString("systemVersion");
			}
			JSONObject returnData = memberInfoService.addMemberInfoBythirdPartyLogin(reqDataJson,request,systemVersion);
			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG,returnData);
		} catch (ArgException arge) {
			return new ResponseMsg(ResponseMsg.ERROR, arge.getMessage());

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}

	}
	
	/**
	 * 
	 * 方法描述 ：游客登入
	 * @author  chenwf: 
	 * @date 创建时间：2017年5月9日 下午6:20:47 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/n/visitolLogin")
	@ResponseBody
	public ResponseMsg visitolLogin(HttpServletRequest request,HttpServletResponse response) {
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数

			Object[] obj = {"regClient","regIp","deviceId","mobileBrand","mobileModel","version","versionNum","system"};
			this.requiredStr(obj, request);
			//客户端类型 1IOS 2Android
			String regClient = reqDataJson.getString("regClient");
			//注册IP
			String regIp = StringUtil.getIpAddr(request);
			//渠道类型
			String sprChnl = reqDataJson.getString("sprChnl");
			sprChnl = DataDicUtil.getSprChnl(sprChnl);
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
			//昵称
			String nick = "游客";
			//序列号
			String imei = "";
			if(reqDataJson.containsKey("imei")){
				imei = reqDataJson.getString("imei");
			}
			String systemVersion = null;
			if(reqPRM.containsKey("systemVersion")){
				systemVersion = reqPRM.getString("systemVersion");
			}
			
			Date createDate = new Date();
			//创建游客登入信息
			MemberInfo memberInfo = memberInfoService.findModelByImei(imei,Const.MEMBER_INFO_TYPE_TOURIST);
			//游客登入，根据设备id查找是否存在，存在则登入，不存在则先创建在登入
			JSONObject returnData = new JSONObject();
			if(memberInfo == null){
//				memberInfo = memberInfoService.findModelByImei(imei,Const.MEMBER_INFO_TYPE_NEW_TOURIST);
//				if(memberInfo == null){
//					memberInfo = new MemberInfo();
//					memberInfo.setType("4");
//					memberInfo.setNick(nick);
//					memberInfo.setSprChnl(sprChnl);
//					//memberInfo.setRegArea(regArea);
//					memberInfo.setRegClient(regClient);
//					memberInfo.setRegIp(regIp);
//					memberInfo.setCreateDate(createDate);
//					memberInfo.setDelFlag("0");
//					memberInfo.setReqImei(imei);
//					memberInfo.setPic("");
//					memberInfo.setReqMobileBrand(mobileBrand);
//					memberInfo.setReqMobileModel(mobileModel);
//					memberInfoService.insertSelective(memberInfo);
//					
//					//创建用户账户信息
//					MemberAccount memberAccount = new MemberAccount();
//					memberAccount.setMemberId(memberInfo.getId());
//					memberAccount.setCreateBy(memberInfo.getId());
//					memberAccount.setCreateDate(new Date());
//					memberAccount.setDelFlag("0");
//					memberAccount.setIntegral(0);
//					memberAccount.setgValue(0);
//					memberAccountService.insertSelective(memberAccount);
//				}
//				Integer memberId = null;
//				String type = "";
//				if(memberInfo != null){
//					memberId = memberInfo.getId();
//					nick = memberInfo.getNick();
//					type = memberInfo.getType();
//				}
				returnData.put("memberId", "");
				returnData.put("token", "");
				returnData.put("nick", "");
				returnData.put("type", "");
				return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG,returnData);
			}
			returnData = memberInfoService.thirdLogin(memberInfo, deviceId, regIp, mobileBrand, mobileModel, system, 
					version, versionNum, imei, memberInfo.getType(), systemVersion);
			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG,returnData);
		} catch (ArgException arge) {
			return new ResponseMsg();

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}

	}
	
	@RequestMapping(value = "/api/n/getDataDic")
	@ResponseBody
	public ResponseMsg getDataDic(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			
			Object[] obj = {"tableName","colName"};
			this.requiredStr(obj,request);
			
			String tableName = reqDataJson.getString("tableName");
			String colName = reqDataJson.getString("colName");
			String type = reqDataJson.getString("type");
			Integer orderDtlId = null;
			if(reqDataJson.containsKey("orderDtlId") && !StringUtil.isBlank(reqDataJson.getString("orderDtlId"))){
				orderDtlId = reqDataJson.getInt("orderDtlId");
			}
			if(tableName.equals("BU_APPEAL_ORDER") && colName.equals("APPEAL_TYPE")){
				type = "A";
			}
			List<Map<String,Object>> returnData = new ArrayList<Map<String,Object>>();
			List<SysStatus> sysStatusList = DataDicUtil.getStatusListByType(tableName, colName, type);
			if(sysStatusList != null && sysStatusList.size() > 0){
				for (SysStatus sysStatus : sysStatusList) {
					Map<String,Object> map = new HashMap<String,Object>();
					//售后申请 过滤掉 退款A 和 直赔D
					if(tableName.equals("BU_CUSTOMER_SERVICE_ORDER") && colName.equals("SERVICE_TYPE")){
						if(sysStatus.getStatusValue().equals("D")||sysStatus.getStatusValue().equals("A")){
							continue;
						}
					}else if(tableName.equals("BU_CUSTOMER_SERVICE_ORDER") && colName.equals("REASON") && orderDtlId != null){
						if(Const.CUSTOMER_ORDER_TYPE_A.equals(type)){
							if("A7".equals(sysStatus.getStatusValue())){
								//判断该订单是否属于预售商品
								boolean isPreDepositOrder = subDepositOrderService.getIsPreDepositOrder(orderDtlId);
								if(!isPreDepositOrder){
									continue;
								}
							}
						}else if(Const.CUSTOMER_ORDER_TYPE_B.equals(type) || Const.CUSTOMER_ORDER_TYPE_C.equals(type)){
							if("B1".equals(sysStatus.getStatusValue()) || "C1".equals(sysStatus.getStatusValue())){
								boolean isReturn7days = orderDtlService.getOrderProductIsReturn7days(orderDtlId);
								if(!isReturn7days){
									continue;
								}
							}
						}
					}
					map.put("statusDesc", sysStatus.getStatusDesc());
					map.put("statusValue", sysStatus.getStatusValue());
					returnData.add(map);
				}
			}
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,returnData);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}
	
	/**
	 * 
	 * 方法描述 ：获取配置数据类型
	 * @author  chenwf: 
	 * @date 创建时间：2017年7月6日 下午4:25:41 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/n/getConfigDataType")
	@ResponseBody
	public ResponseMsg getConfigDataType(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			
			Object[] obj = {"code"};
			this.requiredStr(obj,request);
			
			String code = reqDataJson.getString("code");
			List<Map<String,Object>> returnData = new ArrayList<Map<String,Object>>();
			List<SysParamCfg> sysParamCfgs = DataDicUtil.getSysParamCfg(code);
			if(CollectionUtils.isNotEmpty(sysParamCfgs)){
				for (SysParamCfg sysParamCfg : sysParamCfgs) {
					Map<String,Object> map = new HashMap<String,Object>();
					map.put("paramName", sysParamCfg.getParamName());
					map.put("paramValue", FileUtil.getImageServiceUrl()+sysParamCfg.getParamValue());
					map.put("paramDesc", sysParamCfg.getParamDesc());
					map.put("paramOrder", sysParamCfg.getParamOrder());
					returnData.add(map);
				}
			}
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,returnData);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}
	
}
