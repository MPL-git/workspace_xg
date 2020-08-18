package com.jf.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jf.common.base.ArgException;
import com.jf.common.base.ResponseMsg;
import com.jf.common.constant.BaseDefine;
import com.jf.common.constant.Const;
import com.jf.common.utils.FileUtil;
import com.jf.common.utils.HttpUtil;
import com.jf.common.utils.JsonUtils;
import com.jf.common.utils.SecurityUtil;
import com.jf.common.utils.StringUtil;
import com.jf.common.utils.WeixinUtil;
import com.jf.entity.MchtSettledApply;
import com.jf.entity.MemberGroup;
import com.jf.entity.MemberInfo;
import com.jf.entity.MemberInfoExample;
import com.jf.entity.SysParamCfg;
import com.jf.entity.SysParamCfgExample;
import com.jf.entity.WeixinXcxMemberBind;
import com.jf.entity.WeixinXcxMemberBindExample;
import com.jf.entity.WeixinXcxSprDtl;
import com.jf.entity.WeixinXcxSprLog;
import com.jf.service.MchtSettledApplyService;
import com.jf.service.MemberAttentionService;
import com.jf.service.MemberExtendService;
import com.jf.service.MemberGroupService;
import com.jf.service.MemberInfoService;
import com.jf.service.MemberStatusLogService;
import com.jf.service.MobileVerificationCodeService;
import com.jf.service.SysParamCfgService;
import com.jf.service.WeixinXcxMemberBindService;
import com.jf.service.WeixinXcxSprDtlService;
import com.jf.service.WeixinXcxSprLogService;

@Controller
public class MainController extends BaseController {
	private static Logger logger = LoggerFactory.getLogger(MainController.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Resource
	private MchtSettledApplyService mchtSettledApplyService;
	@Resource
	private SysParamCfgService sysParamCfgService;

	@Resource
	private MemberInfoService memberInfoService;

	@Resource
	private MemberGroupService memberGroupService;
	@Resource
	private MobileVerificationCodeService mobileVerificationCodeService;

	@Resource
	private WeixinXcxMemberBindService weixinXcxMemberBindService;

	@Resource
	private WeixinXcxSprLogService weixinXcxSprLogService;

	@Resource
	private WeixinXcxSprDtlService weixinXcxSprDtlService;
	@Resource
	private MemberStatusLogService memberStatusLogService;
	@Resource
	private MemberAttentionService memberAttentionService;
	@Resource
	private MemberExtendService memberExtendService;
	
	@RequestMapping(value = "/")
	public String index(HttpServletRequest request, Model model) {
		return "main/index";
	}

	// 入驻申请提交
	@RequestMapping(value = "/applySubmit.shtml")
	@ResponseBody
	public Map<String, Object> applySubmit(HttpServletRequest request, MchtSettledApply mchtSettledApply) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			mchtSettledApplyService.saveMchtApply(request, mchtSettledApply);
		} catch (Exception e) {
			e.printStackTrace();
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
		}

		return resMap;
	}

	/**
	 * 
	 * 方法描述 ：获取主页品类
	 * 
	 * @author chenwf:
	 * @date 创建时间：2017年4月25日 下午6:15:36
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/n/getMainCategoryId")
	@ResponseBody
	public ResponseMsg getMainCategoryId(HttpServletRequest request) {
		try {
			List<Map<String, Object>> returnData = new ArrayList<Map<String, Object>>();
			Map<String, Object> map = new HashMap<String, Object>();
			String newUserEnjoyPic = "";
			SysParamCfgExample sysParamCfgExample = new SysParamCfgExample();
			sysParamCfgExample.or().andParamCodeEqualTo("APP_CATALOG");
			sysParamCfgExample.or().andParamCodeEqualTo("APP_NEW_USER_ENJOY");
			sysParamCfgExample.setOrderByClause("param_id");
			List<SysParamCfg> sysParamCfgs = sysParamCfgService.selectByExample(sysParamCfgExample);
			if (CollectionUtils.isNotEmpty(sysParamCfgs)) {
				for (SysParamCfg sysParamCfg : sysParamCfgs) {
					Map<String, Object> dataMap = new HashMap<String, Object>();

					if (sysParamCfg.getParamCode().equals("APP_NEW_USER_ENJOY")) {
						if (!StringUtil.isBlank(sysParamCfg.getParamValue())) {
							newUserEnjoyPic = FileUtil.getImageServiceUrl() + sysParamCfg.getParamValue();
						}
					} else {
						dataMap.put("id", sysParamCfg.getParamValue());
						dataMap.put("name", sysParamCfg.getParamName());
						dataMap.put("pic", FileUtil.getImageServiceUrl() + sysParamCfg.getParamDesc());
						// paramOrder 该字段用来排序 现用来储存 广告推荐
						dataMap.put("adCatalog", sysParamCfg.getParamOrder());
						returnData.add(dataMap);
					}
				}
			}
			map.put("newUserEnjoyPic", newUserEnjoyPic);
			map.put("categoryList", returnData);
			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, map);

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
	 * 方法描述 ：软件使用许可协议
	 * 
	 * @author chenwf:
	 * @date 创建时间：2017年4月21日 下午7:01:14
	 * @version
	 * @param request
	 * @return
	 */
//	@RequestMapping(value = "/n/platform/softwareProtocol")
//	public String softwareProtocol(HttpServletRequest request) {
//		// html一样，弄成统一，softwareProtocol,jsp废弃
//		return "main/protocol";
//	}

	/**
	 * 
	 * 方法描述 ：特别说明
	 * 
	 * @author chenwf:
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
	 * 方法描述 ：积分规则
	 * 
	 * @author chenwf:
	 * @date 创建时间：2017年12月19日 下午5:58:21
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/n/platform/getIntegralRule")
	public String getIntegralRule(HttpServletRequest request) {
		return "main/integralRule";
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
			password = SecurityUtil.md5Encode(password);
			MemberInfoExample memberInfoExample = new MemberInfoExample();
			memberInfoExample.createCriteria().andDelFlagEqualTo("0").andMobileEqualTo(loginCode).andMVerfiyFlagEqualTo("1");
			List<MemberInfo> memberInfos = memberInfoService.selectByExample(memberInfoExample);
			if (memberInfos == null || memberInfos.size() == 0) {
				throw new ArgException("帐号或密码错误");
			}

			MemberInfo memberInfo = memberInfos.get(0);
			if (StringUtil.isBlank(memberInfo.getLoginPass())) {
				throw new ArgException("帐号或密码错误");
			}
			if (!memberInfo.getLoginPass().toLowerCase().equals(password.toLowerCase())) {
				throw new ArgException("帐号或密码错误");
			}
			if (!memberInfo.getStatus().equals("A")) {
				throw new ArgException("帐号异常");
			}

			request.getSession().setAttribute(BaseDefine.MEMBER_INFO, memberInfo);

			String ip = StringUtil.getIpAddr(request);
			String userAgent = request.getHeader("user-agent").toLowerCase();
			String system = "H5";
			if (!StringUtil.isBlank(userAgent) && userAgent.indexOf("micromessenger") > 0) {
				system = "GZH";
			}
			memberInfoService.addLoginLog(memberInfo.getId(), ip, system);

			MemberGroup memberGroup = memberGroupService.findModel(memberInfo.getGroupId());
			String levelPic = memberGroup.getPic();
			if (!StringUtil.isBlank(levelPic)) {
				levelPic = FileUtil.getImageServiceUrl() + levelPic;
			}
			JSONObject returnData = new JSONObject();
			returnData.put("levelName", memberGroup.getName());
			returnData.put("levelPic", levelPic);
			returnData.put("memberId", memberInfo.getId());
			returnData.put("nick", memberInfo.getNick());
			returnData.put("type", memberInfo.getType());
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
			request.getSession().invalidate();
			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
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
			String loginSystem = "H5";
			String ip = StringUtil.getIpAddr(request);
			String imei = "";
			String invitationCode = "";
			String type = Const.MEMBER_INFO_TYPE_MEMBER;
			if (!JsonUtils.isEmpty(reqDataJson, "imei")) {
				imei = reqDataJson.getString("imei");
			}
			if (!JsonUtils.isEmpty(reqDataJson, "invitationCode")) {
				invitationCode = reqDataJson.getString("invitationCode");//邀请码，链接直接把邀请码替换成会员id
			}

			String regClient = "";
			String ua = request.getHeader("user-agent").toLowerCase();
			if (!StringUtil.isBlank(ua) && ua.indexOf("micromessenger") > 0) {
				// 微信浏览器打开就只有微信公众号支付支付
				regClient = Const.MICRO_MALL;
				loginSystem = "GZH";
			} else {
				regClient = Const.WEB_APP;
			}
			// 判断验证码
			if (!mobileVerificationCodeService.verify(loginCode, verifyCode)) {
				throw new ArgException("验证码错误");
			}

			MemberInfoExample memberInfoExample = new MemberInfoExample();
			memberInfoExample.createCriteria().andDelFlagEqualTo("0").andMobileEqualTo(loginCode).andMVerfiyFlagEqualTo("1");
			List<MemberInfo> memberInfos = memberInfoService.selectByExample(memberInfoExample);
			MemberInfo memberInfo = null;
			if (CollectionUtils.isEmpty(memberInfos)) {
				// 没有该用户，注册用户信息
				memberInfo = memberInfoService.addMemberInfo(type, loginCode, null, null, null, null, null, regClient, ip, createDate, imei, null, null, null, null, null, loginCode, "1",invitationCode);
			} else {
				memberInfo = memberInfos.get(0);
				if (!memberInfo.getStatus().equals("A")) {
					throw new ArgException("帐号异常");
				}
			}
			//添加会员注册状态
			memberStatusLogService.addMemberRegisterStatus(memberInfo.getId(), Const.MEMBER_INFO_STATUS_ACTIVATION_A);
			JSONObject returnData = memberInfoService.login(memberInfo, loginSystem, ip, request);
			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, returnData);
		} catch (ArgException arge) {
			return new ResponseMsg(ResponseMsg.ERROR, arge.getMessage());

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}

	}

	/**
	 * 小程序会员登录
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/n/wxSmallAppLogin")
	@ResponseBody
	public ResponseMsg wxSmallAppLogin(HttpServletRequest request) {
		try {
			boolean isNewMember = false;
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数

			logger.debug("小程序请求的参数" + reqDataJson);


			if (JsonUtils.isEmpty(reqDataJson, "code")) {
				throw new ArgException("登录出错");
			}

			String code = reqDataJson.getString("code");
			String invitationCode = "";

			Integer sprDtlId = null;//推广明细id
			if (!JsonUtils.isEmpty(reqDataJson, "sprParam")) {
				String sprParam = reqDataJson.getString("sprParam");
				sprDtlId = Integer.valueOf(sprParam.substring(sprParam.indexOf("dtlid_") + 6));
			}
			if (!JsonUtils.isEmpty(reqDataJson, "invitationCode")) {
				invitationCode = reqDataJson.getString("invitationCode");
			}
			String ip = StringUtil.getIpAddr(request);
			//获取用户的微信信息
			String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + WeixinUtil.xcxAppId + "&secret=" + WeixinUtil.xcxAppSecret + "&js_code=" + code + "&grant_type=authorization_code";
			String jsonStr;
			jsonStr = HttpUtil.httpGet(url);
			JSONObject resJson = JSONObject.fromObject(jsonStr);
			logger.debug("微信返回数据：" + resJson);
			if (!resJson.has("errcode") || "0".equals(resJson.get("errcode"))) {
				String openid = resJson.getString("openid");
				String sessionKey = resJson.getString("session_key");
				request.getSession().setAttribute("xcxOpenId", openid);
				request.getSession().setAttribute("xcxSessionKey", sessionKey);
				
				
				MemberInfo memberInfo = null;
				// 自动登录
				WeixinXcxMemberBindExample weixinXcxMemberBindExample = new WeixinXcxMemberBindExample();
				weixinXcxMemberBindExample.createCriteria().andDelFlagEqualTo("0").andOpenIdEqualTo(openid);
				List<WeixinXcxMemberBind> weixinXcxMemberBinds = weixinXcxMemberBindService.selectByExample(weixinXcxMemberBindExample);
				if (weixinXcxMemberBinds != null && weixinXcxMemberBinds.size() > 0) {
					memberInfo = memberInfoService.selectByPrimaryKey(weixinXcxMemberBinds.get(0).getMemberId());
				} else {

					String encryptedData = reqDataJson.getString("encryptedData");
					String iv = reqDataJson.getString("iv");
					JSONObject userInfoData = JSONObject.fromObject(WeixinUtil.xcxAesDecrypt(encryptedData, sessionKey, iv));
					
					logger.debug("解密后的数据:"+userInfoData);
					
					JSONObject watermark = userInfoData.getJSONObject("watermark");
					if (!WeixinUtil.xcxAppId.equals(watermark.getString("appid"))) {
						logger.info("-----------------------小程序登录出错-----原因：appId验证不通过----------");
						logger.info(jsonStr);
						return new ResponseMsg(ResponseMsg.ERROR, "登录出错", null);
					}
					String unionid = userInfoData.getString("unionId");
					MemberInfoExample memberInfoExample = new MemberInfoExample();
					memberInfoExample.createCriteria().andDelFlagEqualTo("0").andWeixinUnionidEqualTo(unionid);
					List<MemberInfo> memberInfos = memberInfoService.selectByExample(memberInfoExample);
					if (memberInfos != null && memberInfos.size() > 0) {
						memberInfo = memberInfos.get(0);
					} else {// 创建新会员
						memberInfo = new MemberInfo();
						memberInfo.setType("2");
						memberInfo.setRemarks("微信小程序");
						memberInfo.setWeixinUnionid(unionid);
						memberInfo.setRegClient("5");
						memberInfo.setRegIp(ip);
						memberInfo.setCreateDate(new Date());
						memberInfo.setDelFlag("0");
						memberInfo.setGroupId(1);// 注册会员标识
						memberInfo.setStatus("A");
						if (userInfoData.has("nickName")) {
							memberInfo.setNick(StringUtil.filterEmoji(userInfoData.getString("nickName")));
						}
						if (userInfoData.has("avatarUrl")) {
							memberInfo.setPic(userInfoData.getString("avatarUrl"));
						}
						if (userInfoData.has("gender")) {
							String sexType = userInfoData.getString("gender");
							if ("1".equals(sexType)) {
								memberInfo.setSexType("1");
							}
							if ("2".equals(sexType)) {
								memberInfo.setSexType("0");
							}
							if ("0".equals(sexType)) {
								memberInfo.setSexType("2");
							}
						}

						String regArea = "";
						if (userInfoData.has("country") && !StringUtil.isEmpty(userInfoData.getString("country"))) {
							regArea = regArea + userInfoData.getString("country");
						}
						if (userInfoData.has("province") && !StringUtil.isEmpty(userInfoData.getString("province"))) {
							regArea = regArea + userInfoData.getString("province");
						}
						if (userInfoData.has("city") && !StringUtil.isEmpty(userInfoData.getString("city"))) {
							regArea = regArea + userInfoData.getString("city");
						}
						if (StringUtil.isEmpty(regArea)) {
							memberInfo.setRegArea(regArea);
						}
						if (!StringUtil.isBlank(invitationCode)) {
							System.out.println("我是新星用户上级");
							memberInfo.setInvitationMemberId(Integer.parseInt(invitationCode));
							memberInfo.setInvitationCodeBindTime(new Date());
						}
						// 推广标志
						if (sprDtlId != null) {
							memberInfo.setWeixinXcxSprDtlId(sprDtlId);
							memberInfo.setNewWeixinXcxSprDtlId(sprDtlId);
						}
						memberInfoService.createMemberInfo(memberInfo);
						//添加会员注册状态
						memberStatusLogService.addMemberRegisterStatus(memberInfo.getId(), Const.MEMBER_INFO_STATUS_ACTIVATION_A);
						//互相关注
						if(!StringUtil.isBlank(invitationCode)){
							memberAttentionService.addMemberMutualConcern(memberInfo.getId(), Integer.parseInt(invitationCode));
						}
						//会员扩展
						memberExtendService.addModel(memberInfo.getId(),"","");
						isNewMember = true;
					}

					// 微信会员做绑定
					WeixinXcxMemberBind weixinXcxMemberBind = new WeixinXcxMemberBind();
					weixinXcxMemberBind.setCreateDate(new Date());
					weixinXcxMemberBind.setDelFlag("0");
					weixinXcxMemberBind.setOpenId(openid);
					weixinXcxMemberBind.setMemberId(memberInfo.getId());
					weixinXcxMemberBindService.insertSelective(weixinXcxMemberBind);
				}

				/************** 推广处理*********begin ********************/
				
				boolean need2UpdateMemberInfo=false;
				MemberInfo memberInfo4Update = new MemberInfo();
				memberInfo4Update.setId(memberInfo.getId());
				memberInfo4Update.setUpdateDate(new Date());
				if (sprDtlId != null) {
					WeixinXcxSprDtl weixinXcxSprDtl = weixinXcxSprDtlService.selectByPrimaryKey(sprDtlId);
					if (!((weixinXcxSprDtl == null) 
							|| (!StringUtil.isEmpty(weixinXcxSprDtl.getSprType()) && !weixinXcxSprDtl.getSprType().equals(reqDataJson.has("sprType") ? reqDataJson.getString("sprType") : "")) 
							|| (!StringUtil.isEmpty(weixinXcxSprDtl.getSprValue()) && !weixinXcxSprDtl.getSprValue().equals(reqDataJson.has("sprValue") ? reqDataJson.getString("sprValue") : "")))) {// 判断参数值是否和数据库里面的匹配
						need2UpdateMemberInfo=true;
						if (memberInfo.getWeixinXcxSprDtlId() == null) {
							memberInfo4Update.setWeixinXcxSprDtlId(sprDtlId);
							memberInfo4Update.setNewWeixinXcxSprDtlId(sprDtlId);
						}else {
							memberInfo4Update.setNewWeixinXcxSprDtlId(sprDtlId);
						}
						// 增加推广记录
						WeixinXcxSprLog weixinXcxSprLog = new WeixinXcxSprLog();
						weixinXcxSprLog.setMemberId(memberInfo.getId());
						weixinXcxSprLog.setSprDtlId(sprDtlId);
						weixinXcxSprLog.setCreateDate(new Date());
						weixinXcxSprLog.setIp(ip);
						weixinXcxSprLogService.insertSelective(weixinXcxSprLog);
					}
				}
				
				//微信广告处理
				if(!JsonUtils.isEmpty(reqDataJson, "gdtVid")){
					need2UpdateMemberInfo = true;
					memberInfo4Update.setWeixinGdtVid(reqDataJson.getString("gdtVid"));
					memberInfo4Update.setWeixinAdinfo(reqDataJson.getString("weixinadinfo"));
				}
				
				if(need2UpdateMemberInfo){
					memberInfoService.updateByPrimaryKeySelective(memberInfo4Update);
				}

				/************** 推广处理**********end *********************/

				// 登录
				request.getSession().setAttribute(BaseDefine.MEMBER_INFO, memberInfo);
				memberInfoService.addLoginLog(memberInfo.getId(), ip, "XCX");

				JSONObject returnData = new JSONObject();
				if (isNewMember) {
					returnData.put("isNewMember", "1");
				} else {
					returnData.put("isNewMember", "0");
				}
				returnData.put("sessionId", request.getSession().getId());

				return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, returnData);

			} else {
				logger.info("-----------------------小程序登录出错---------------");
				logger.info(jsonStr);
				return new ResponseMsg(ResponseMsg.ERROR, "小程序登录出错", null);
			}

		} catch (ArgException arge) {
			return new ResponseMsg(ResponseMsg.ERROR, arge.getMessage());

		} catch (Exception e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR);
		}

	}

	/**
	 * 小程序会员登录后获取会员信息
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/y/wxSmallAppGetUserInfo")
	@ResponseBody
	public ResponseMsg wxSmallAppGetUserInfo(HttpServletRequest request) {
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			JSONObject userInfo = reqDataJson.getJSONObject("userInfo");
			Integer memberId = this.getMemberId(request);
			if (memberId == null) {
				return new ResponseMsg(ResponseMsg.ERROR, "用户未登录", null);
			}
			MemberInfo memberInfo = memberInfoService.selectByPrimaryKey(memberId);
			MemberInfo memberInfo4Update = new MemberInfo();
			memberInfo4Update.setId(memberId);
			boolean needUpdateMemberInfo = false;
			if (StringUtil.isEmpty(memberInfo.getNick()) && userInfo.has("nickName") && !StringUtil.isEmpty(StringUtil.filterEmoji(userInfo.getString("nickName")))) {
				memberInfo4Update.setNick(StringUtil.filterEmoji(userInfo.getString("nickName")));
				needUpdateMemberInfo = true;
			}

			if (StringUtil.isEmpty(memberInfo.getPic()) && userInfo.has("avatarUrl")) {
				memberInfo4Update.setPic(userInfo.getString("avatarUrl"));
				needUpdateMemberInfo = true;
			}

			if (StringUtil.isEmpty(memberInfo.getSexType()) && userInfo.has("gender")) {
				String sexType = userInfo.getString("gender");
				if ("1".equals(sexType)) {
					memberInfo4Update.setSexType("1");
				}
				if ("2".equals(sexType)) {
					memberInfo4Update.setSexType("0");
				}
				if ("0".equals(sexType)) {
					memberInfo4Update.setSexType("2");
				}
				needUpdateMemberInfo = true;
			}

			if (StringUtil.isEmpty(memberInfo.getRegArea())) {
				String regArea = "";
				if (userInfo.has("country") && !StringUtil.isEmpty(userInfo.getString("country"))) {
					regArea = regArea + userInfo.getString("country");
				}
				if (userInfo.has("province") && !StringUtil.isEmpty(userInfo.getString("province"))) {
					regArea = regArea + userInfo.getString("province");
				}
				if (userInfo.has("city") && !StringUtil.isEmpty(userInfo.getString("city"))) {
					regArea = regArea + userInfo.getString("city");
				}
				if (StringUtil.isEmpty(regArea)) {
					memberInfo4Update.setRegArea(regArea);
				}
				needUpdateMemberInfo = true;
			}

			if (needUpdateMemberInfo) {
				memberInfoService.updateByPrimaryKeySelective(memberInfo4Update);
			}

			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, null);
		} catch (ArgException arge) {
			return new ResponseMsg(ResponseMsg.ERROR, arge.getMessage());

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}

	}

	public static void main(String[] args) {
		System.out.println("jrtoutiao_xx_dtlid_4587".substring("jrtoutiao_xx_dtlid_4587".indexOf("dtlid_") + 6));
	}
}
