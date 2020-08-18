package com.jf.controller;

import com.jf.common.AppContext;
import com.jf.common.base.ArgException;
import com.jf.common.base.ResponseMsg;
import com.jf.common.constant.Const;
import com.jf.common.constant.StateConst;
import com.jf.common.utils.CollectionUtil;
import com.jf.common.utils.Config;
import com.jf.common.utils.DataDicUtil;
import com.jf.common.utils.DateUtil;
import com.jf.common.utils.FileUtil;
import com.jf.common.utils.JsonUtils;
import com.jf.common.utils.StringUtil;
import com.jf.controller.base.BaseController;
import com.jf.dao.PersonalCenterThemeBackgroundMapper;
import com.jf.entity.*;
import com.jf.entity.dto.AdDTO;
import com.jf.service.*;
import com.jf.vo.SVipInfo;
import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.*;

/**
  * @author  chenwf: 
  * @date 创建时间：2017年4月19日 下午2:35:12 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Controller
public class MyInfoController extends BaseController{
	@Resource
	private MemberInfoService memberInfoService;
	
	@Resource
	private MobileVerificationCodeService mobileVerificationCodeService;
	
	@Resource
	private MemberGroupService memberGroupService;
	@Resource
	private MemberAccountService memberAccountService;
	@Resource
	private IntegralTypeService integralTypeService;
	@Resource
	private MemberCouponService memberCouponService;
	@Resource
	private MemberExtendService memberExtendService;
	@Resource
	private NovaPlanService novaPlanService;
	@Resource
	private AreaService areaService;
	@Autowired
	private MemberRemindService memberRemindService;
	@Autowired
	private MemberFootprintService memberFootprintService;
	@Autowired
	private MemberShopFootprintService memberShopFootprintService;
	@Autowired
	private MemberActivityFootprintService memberActivityFootprintService;
	@Autowired
	private PersonalCenterThemeBackgroundMapper personalCenterThemeBackgroundMapper;
	@Autowired
	private OrderDtlService orderDtlService;
	@Autowired
	private AdInfoService adInfoService;
	@Autowired
	private AppContext appContext;
	@Autowired
	private CommonService commonService;

	/**
	 * 
	 * 方法描述 ：修改密码
	 * @author  chenwf: 
	 * @date 创建时间：2017年4月19日 下午2:51:53 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/y/updatePassword")
	@ResponseBody
	public ResponseMsg updatePassword(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			
			String memberId = reqDataJson.getString("memberId");
			String oldPwd = reqDataJson.getString("oldPwd");
			String newPwd = reqDataJson.getString("newPwd");
			
			if(StringUtil.isBlank(memberId)){
				throw new ArgException("memberId不能为空");
			}
			if(StringUtil.isBlank(oldPwd)){
				throw new ArgException("请输入旧密码");
			}
			if(StringUtil.isBlank(newPwd)){
				throw new ArgException("请输入新密码");
			}
			MemberInfo memberInfo = memberInfoService.selectByPrimaryKey(Integer.valueOf(memberId));
			if(memberInfo != null){
				if(!memberInfo.getLoginPass().equals(oldPwd)){
					throw new ArgException("原密码不正确");
				}
			}
			if(oldPwd.equals(newPwd)){
				throw new ArgException("新密码与原密码相同，请重新设置");
			}
			
			memberInfo.setLoginPass(newPwd);
			memberInfoService.updateByPrimaryKeySelective(memberInfo);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,null);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}
	
	/**
	 * 
	 * 方法描述 ：设置密码
	 * @author  chenwf: 
	 * @date 创建时间：2017年4月19日 下午2:51:53 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/y/setPassword")
	@ResponseBody
	public ResponseMsg setPassword(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			
			String memberId = reqDataJson.getString("memberId");
			String newPwd = reqDataJson.getString("newPwd");
			
			if(StringUtil.isBlank(memberId)){
				throw new ArgException("memberId不能为空");
			}
			if(StringUtil.isBlank(newPwd)){
				throw new ArgException("请输入新密码");
			}
			MemberInfo memberInfo = memberInfoService.selectByPrimaryKey(Integer.valueOf(memberId));
			if(memberInfo != null){
				if(!memberInfo.getType().equals(Const.MEMBER_INFO_TYPE_MEMBER) && !memberInfo.getType().equals(Const.MEMBER_INFO_TYPE_THIRD)){
					throw new ArgException("会员才能设置密码");
				}else{
					if(!StringUtil.isBlank(memberInfo.getLoginPass())){
						throw new ArgException("您已设置过密码，可通过忘记密码找回。");
					}
				}
			}else{
				throw new ArgException("用户不存在");
			}
			
			memberInfo.setLoginPass(newPwd);
			memberInfoService.updateByPrimaryKeySelective(memberInfo);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,null);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}
	/**
	 * 
	 * 方法描述 ：获取用户个人信息
	 * @author  chenwf: 
	 * @date 创建时间：2017年4月19日 下午4:10:29 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/y/getUserInfo")
	@ResponseBody
	public ResponseMsg getUserInfo(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			
			Date currentDate = new Date();
			String system = reqPRM.getString("system");
			Integer version = reqPRM.getInt("version");
			Integer memberId = reqDataJson.getInt("memberId");
			MemberInfo memberInfo = memberInfoService.selectByPrimaryKey(memberId);
			MemberGroup memberGroup = memberGroupService.selectByPrimaryKey(memberInfo.getGroupId());
			JSONObject returnData = new JSONObject();
			String pic = StringUtil.getPic(memberInfo.getPic(), "");
			String mVerfiyFlag = memberInfo.getmVerfiyFlag();
			String status = memberInfo.getStatus();
			String memberThirdRegType = memberInfoService.getMemberThridLoginType(memberInfo,memberInfo.getId());
			SVipInfo sVipInfo = memberInfoService.isSVip(memberInfo, memberId);
			String svipContent = "";
			String birthday = "";
			String isBingMobile = "1";//是否已绑定手机 0 否 1是
			String mustBeBoundMobile = "0"; //是否要求必须绑定手机号 0 否 1是
			if(memberInfo.getBirthday() != null){
				birthday = DateUtil.getFormatDate(memberInfo.getBirthday(), "yyyy-MM-dd");
			}
			if(!"p".equalsIgnoreCase(status)){
				if(sVipInfo.isRealSVip()){
					svipContent = "最高5折优惠";
				}else{
					svipContent = "开通享特权 >";
				}
			}
			if (appContext.olderThan(68, 70)) { //低版本才需要判断qq、微博注册用户是否绑定过手机号
				if (("2".equals(memberThirdRegType) || "3".equals(memberThirdRegType)) && !"1".equals(mVerfiyFlag)) {
					isBingMobile = "0";
				}
			}else{
				if (!"1".equals(mVerfiyFlag)) {
					isBingMobile = "0";
				}
				if ("2".equals(memberThirdRegType) || "3".equals(memberThirdRegType)) {
					mustBeBoundMobile = "1";
				}
			}

			String levelPic = StringUtil.getPic(memberGroup.getPic(), "");
			boolean isHasPwd = true;
			boolean isHasBindingWx = true;
			if(StringUtil.isBlank(memberInfo.getLoginPass())){
				isHasPwd = false;
			}
			if(StringUtil.isBlank(memberInfo.getWeixinUnionid())){
				isHasBindingWx = false;
			}
			String customerServiceType = "3";//1商品详情客服 2订单详情客服 3我的平台客服
			String customerService = "1";
			String customerServiceSoftType = "2";//1智齿 2小能
			//获取优惠券数量(只统计未使用的优惠券)
			MemberCouponExample memberCouponExample = new MemberCouponExample();
			memberCouponExample.createCriteria().andMemberIdEqualTo(memberId).andStatusEqualTo("0").andDelFlagEqualTo("0")
				.andExpiryEndDateGreaterThan(currentDate);
			int memberCounponNum = memberCouponService.countByExample(memberCouponExample);
			//获取用户积分
			MemberAccount account = memberAccountService.findAccountByMemberId(memberId);
			BigDecimal profitInviteBalance = account.getProfitInviteBalance().subtract(account.getProfitInviteFreeze());
			//新星计划开启
			String novaPlanOpen = "0";
			String code = "";
			if(system.equals(Const.IOS)){
				code = "APP_IOS_NOVA_PLAN_OPEN";
			}else if(system.equals(Const.ANDROID)){
				code = "APP_ANDROID_NOVA_PLAN_OPEN";
			}
			if(!StringUtil.isBlank(code)){
				SysParamCfg paramCfgs = DataDicUtil.getSysParamCfgModel(code, system);
				if(paramCfgs != null){
					if("1".equals(paramCfgs.getParamName())){
						if(version >= Integer.valueOf(paramCfgs.getParamOrder())){
							novaPlanOpen = novaPlanService.getNewNovaPlanOpenType(memberInfo, memberId);
						}
					}
				}
			}
			List<SysParamCfg> rechargeCfgList = DataDicUtil.getSysParamCfg("APP_MEMBER_RECHARGE");
			if (CollectionUtil.isEmpty(rechargeCfgList)) {
				returnData.put("showRechargeCenterBtn", "0");
			}else{
				SysParamCfg rechargeCfg = rechargeCfgList.get(0);
				returnData.put("showRechargeCenterBtn", rechargeCfg.getParamOrder()); //充值中心开关 0关 1开
				returnData.put("rechargeCenterUrl", rechargeCfg.getParamValue()); //充值中心地址
				if(rechargeCfg.getParamOrder().equals("1")){//充值中心开关 0关 1开
					List<SysParamCfg> iosAuditVersionList = DataDicUtil.getSysParamCfg("IOS_AUDIT_VERSION");
					if(iosAuditVersionList!=null && iosAuditVersionList.size()>0){
						String iosAuditVersion = iosAuditVersionList.get(0).getParamValue();
						if(system.equals("1") && version >= Integer.parseInt(iosAuditVersion)){//ios
							returnData.put("showRechargeCenterBtn", "0"); //充值中心开关 0关 1开
						}
					}
				}
			}
			returnData.put("shopOwnerPublicityPage", "https://m.xgbuy.cc/webApp/xgbuy/views/index.html?redirect_url=YWN0aXZpdHkvc2hvcG93bmVyL3BhZ2VzL2ludHJvZHVjZS9pbmRleC5odG1s");
			returnData.put("profitInviteBalance", profitInviteBalance);
			returnData.put("novaPlanOpen", novaPlanOpen);
			returnData.put("invitationCode", "");
			returnData.put("isBingMobile", isBingMobile);
			returnData.put("mustBeBoundMobile", mustBeBoundMobile);
			returnData.put("memberThirdRegType", memberThirdRegType);
			returnData.put("MemberCounponNum", memberCounponNum);
			returnData.put("memberintegral", account.getIntegral());
			returnData.put("pic", pic);
			returnData.put("nick", memberInfo.getNick() == null ? "" : memberInfo.getNick());
			returnData.put("mobile", memberInfo.getMobile() == null ? "" : memberInfo.getMobile());
			returnData.put("sexType", memberInfo.getSexType() == null ? "" : memberInfo.getSexType());
			returnData.put("birthday", birthday);
			returnData.put("levelName", memberGroup.getName());
			returnData.put("levelPic", levelPic);
			returnData.put("isAcceptPush", memberInfo.getIsAcceptPush());
			returnData.put("type", memberInfo.getType());
			returnData.put("customerService", customerService);
			returnData.put("customerServiceType", customerServiceType);
			returnData.put("customerServiceSoftType", customerServiceSoftType);
			returnData.put("customerServiceMobile", "01053185321");
			returnData.put("customerServiceWorkTime", "( 工作日 9:00-22:00 )");
			returnData.put("isHasPwd", isHasPwd);
			returnData.put("isHasBindingWx", isHasBindingWx);
			returnData.put("shareAppTitle", "送您500元券礼包，快来领取吧");
			returnData.put("shareAppContent", "新人手机登录即可享受500元新人礼包，更有新人超低价秒杀包邮。更多潮牌特价精彩不容错过");
			returnData.put("shareAppUrl", Config.getProperty("mUrl")+"/xgbuy/views/?redirect_url=link/my-app.html");
			returnData.put("logo", FileUtil.getImageServiceUrl()+"/app/2017/share_app_logo.png");
			returnData.put("platformInvestmentUrl", Config.getProperty("mUrl")+"/xgbuy/views/?redirect_url=settled/index.html");
			returnData.put("helpCenterUrl", Config.getProperty("mUrl")+"/xgbuy/views/?redirect_url=help/index.html");
			returnData.put("isSvip", sVipInfo.isRealSVip() ? StateConst.TRUE : StateConst.FALSE);
			returnData.put("isTrailSvip", sVipInfo.isTrail() ? StateConst.TRUE : StateConst.FALSE);
			returnData.put("trailTip", buildTrailTip(sVipInfo));
			returnData.put("svipContent", svipContent);
			//STORY #1506
			returnData.put("regArea", StringUtil.isEmpty(memberInfo.getRegArea())?"未知城市":memberInfo.getRegArea());
			String province = "";
			String city = "";
			if(memberInfo.getProvince()!=null){
				province = memberInfo.getProvince().toString();
			}
			if(memberInfo.getCity()!=null){
				city = memberInfo.getCity().toString();
			}
			returnData.put("province", province);
			returnData.put("city", city);

			//STORY #1964
			Date date = new Date();
			MemberRemindExample memberRemindExample = new MemberRemindExample();
			memberRemindExample.createCriteria().andDelFlagEqualTo("0").andMemberIdEqualTo(memberId);
			Integer memberRemindCount = memberRemindService.countByExample(memberRemindExample); //收藏数
			int memberFootprintCount = memberFootprintService.getMemberFootprintListCount(memberId); //记录数
			int memberShopFootprintCount = memberShopFootprintService.getMemberShopFootprintCount(memberId);
			int memberActivityFootprintCount = memberActivityFootprintService.getMemberActivityFootprintCount(memberId);
			memberFootprintCount = memberFootprintCount + memberActivityFootprintCount + memberShopFootprintCount; //记录数
			String backgroundPic = ""; //主题背景
			PersonalCenterThemeBackgroundExample personalCenterThemeBackgroundExample = new PersonalCenterThemeBackgroundExample();
			personalCenterThemeBackgroundExample.createCriteria().andDelFlagEqualTo("0")
					.andBeginDateLessThanOrEqualTo(date)
					.andEndDateGreaterThanOrEqualTo(date)
					.andStatusEqualTo("1");
			personalCenterThemeBackgroundExample.setOrderByClause(" id desc");
			personalCenterThemeBackgroundExample.setLimitStart(0);
			personalCenterThemeBackgroundExample.setLimitSize(1);
			List<PersonalCenterThemeBackground> personalCenterThemeBackgroundList = personalCenterThemeBackgroundMapper.selectByExample(personalCenterThemeBackgroundExample);
			if(CollectionUtil.isNotEmpty(personalCenterThemeBackgroundList)) {
				if(sVipInfo.isRealSVip()) {
					backgroundPic = StringUtil.getPic(personalCenterThemeBackgroundList.get(0).getSvipThemeBackgroundPic(), "");
				}else {
					backgroundPic = StringUtil.getPic(personalCenterThemeBackgroundList.get(0).getCommonThemeBackgroundPic(), "");
				}
			}else {
				SysParamCfg paramCfgs = DataDicUtil.getSysParamCfgModel("THEME_BACKGROUND_PIC", null);
				backgroundPic = StringUtil.getPic(paramCfgs.getParamValue(), "");
			}
			List<Map<String, String>> svipPrivilegePics = new ArrayList<>(); //Svip特权图片列表
			List<SysParamCfg> cfgs = DataDicUtil.getSysParamCfg("APP_MEMBER_SVIP_PRIVILEGE_PIC");
			if(CollectionUtils.isNotEmpty(cfgs)){
				for (SysParamCfg sysParamCfg : cfgs) {
					Map<String, String> cfgMap = new HashMap<>();
					String linkId = sysParamCfg.getParamName();
					cfgMap.put("linkId", linkId);
					cfgMap.put("pic", StringUtil.getPic(sysParamCfg.getParamValue(), ""));
					svipPrivilegePics.add(cfgMap);
				}
			}
			SysParamCfg paramCfgSvip = DataDicUtil.getSysParamCfgModel("SVIP_SAVE_AMONT", null);
			BigDecimal saveAmont = new BigDecimal(paramCfgSvip.getParamValue()); //已节省金额
			if(sVipInfo.isRealSVip()){
				saveAmont = orderDtlService.getSvipSaveAmount(memberId);
				if(saveAmont == null){
					saveAmont = new BigDecimal("0");
				}
			}
			List<Map<String, Object>> resourceAdList = new ArrayList<>(); //资源位
			AdInfoExample adInfoExample = new AdInfoExample();
			adInfoExample.createCriteria().andDelFlagEqualTo("0")
					.andStatusEqualTo("1")
					.andCatalogEqualTo(Const.AD_CATALOG_PERSONAL)
					.andPositionEqualTo(Const.AD_POSITION_TOP_1)
					.andAutoUpDateLessThanOrEqualTo(date)
					.andAutoDownDateGreaterThanOrEqualTo(date);
			adInfoExample.setOrderByClause("ifnull(seq_no, 99999)");
			List<AdInfo> adInfoList = adInfoService.selectByExample(adInfoExample);
			for(AdInfo adInfo : adInfoList) {
				Map<String, Object> dataMap = new HashMap<>();
				Integer id = adInfo.getId();
				String adInfoPic = adInfo.getPic();
				String linkType = adInfo.getLinkType();
				Integer linkId = adInfo.getLinkId();
				String linkUrl = adInfo.getLinkUrl();
				AdDTO adDTO = new AdDTO();
				adDTO.setAdId(id+"");
				adDTO.setLinkType(linkType);
				adDTO.setLinkId(linkId);
				adDTO.setLinkUrl(linkUrl);
				adDTO.setPic(adInfoPic);
				adDTO.setType("2");
				dataMap = commonService.buildAdMap(adDTO);
				resourceAdList.add(dataMap);
			}
			returnData.put("memberRemindCount", memberRemindCount); //收藏数
			returnData.put("memberFootprintCount", memberFootprintCount); //记录数
			returnData.put("backgroundPic", backgroundPic); //主题背景
			returnData.put("svipPrivilegePics", svipPrivilegePics); //Svip特权图片列表
			returnData.put("saveAmont", saveAmont); //已节省金额
			returnData.put("resourceAdList", resourceAdList); //资源位

			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,returnData);
			
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}

	private String buildTrailTip(SVipInfo sVipInfo) {
		if (sVipInfo.isTrail() && sVipInfo.getSvipPracticeRecord() != null) {
			SvipPracticeRecord record = sVipInfo.getSvipPracticeRecord();
			return StringUtil.buildMsg("{}到期", DateUtil.getStandardDateTime(record.getPracticeEndTime()));
		}
		return "";
	}

	@RequestMapping(value = "/api/y/updateUserInfo")
	@ResponseBody
	public ResponseMsg updateUserInfo(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			String type = "1";
			if(!JsonUtils.isEmpty(reqDataJson, "type")){
				type = reqDataJson.getString("type");
			}
			MemberInfo memberInfo = memberInfoService.findMemberById(reqDataJson.getInt("memberId"));
			if(JsonUtils.isEmpty(reqDataJson, "memberId") && StringUtil.isBlank(reqDataJson.getString("memberId"))){
				throw new ArgException("memberId不能为空");
			}else{
				memberInfo.setId(Integer.valueOf(reqDataJson.getString("memberId")));
			}
			
			//修改图片 pic
			if(!JsonUtils.isEmpty(reqDataJson, "pic") && !StringUtil.isBlank(reqDataJson.getString("pic"))){
				memberInfo.setPic(reqDataJson.getString("pic"));
			}
			
			//修改昵称 nick
			if(!JsonUtils.isEmpty(reqDataJson, "nick") && !StringUtil.isBlank(reqDataJson.getString("nick"))){
				String nick = StringUtil.filterEmoji(reqDataJson.getString("nick"));
				memberInfo.setNick(nick);
			}
			
			//修改性别 sexType
			if(!JsonUtils.isEmpty(reqDataJson, "sexType") && !StringUtil.isBlank(reqDataJson.getString("sexType"))){
				memberInfo.setSexType(reqDataJson.getString("sexType"));
			}
			
			//修改出生日期 birthday
			if(!JsonUtils.isEmpty(reqDataJson, "birthday") && !StringUtil.isBlank(reqDataJson.getString("birthday"))){
				Date date = DateUtil.getFormatString(reqDataJson.getString("birthday"), "yyyy-MM-dd");
				memberInfo.setBirthday(date);
			}
			//修改性别 sexType
			if(!JsonUtils.isEmpty(reqDataJson, "isAcceptPush") && !StringUtil.isBlank(reqDataJson.getString("isAcceptPush"))){
				memberInfo.setIsAcceptPush(reqDataJson.getString("isAcceptPush"));
				if("1".equals(type)){
					memberInfo.setIsAcceptPush(reqDataJson.getString("isAcceptPush"));
				}else if("2".equals(type)){
					//更新会员扩展表信息
					memberExtendService.updateMemberExtendInfo(memberInfo.getId()); 
				}
			}
			memberInfoService.updateByPrimaryKeySelective(memberInfo);
			if(!memberInfo.getIsInfPerfect().equals("1") && !memberInfo.getType().equals(Const.MEMBER_INFO_TYPE_TOURIST)){
				setIsInfPerfect(memberInfo);
			}
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,null);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}
	
	/**
	 * 
	 * 方法描述 ：验证短信验证码 且 跟换绑定手机号码
	 * @author  chenwf: 
	 * @date 创建时间：2017年4月20日 下午2:01:25 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/y/updateBindMobile")
	@ResponseBody
	public ResponseMsg updateBindMobile(HttpServletRequest request){
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
			if(JsonUtils.isEmpty(reqDataJson, "memberId") && StringUtil.isBlank(reqDataJson.getString("memberId"))){
				throw new ArgException("memberId不能为空");
			}
			if(!mobileVerificationCodeService.verify(mobile,mobileVerificationCode)){
				throw new ArgException("验证码错误");
			}else{
				MemberInfo memberInfo = memberInfoService.findModelByMobile(mobile);
				if(memberInfo != null){
					throw new ArgException("该手机号码已被绑定");
				}else{
					memberInfo = new MemberInfo();
					//修改绑定手机 mobile
					memberInfo.setMobile(mobile);
					memberInfo.setmVerfiyFlag("1");
					memberInfo.setId(Integer.valueOf(reqDataJson.getString("memberId")));
					memberInfoService.updateByPrimaryKeySelective(memberInfo);
				}
			}
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG);
			
		} catch (ArgException arge) {
			return new ResponseMsg(ResponseMsg.ERROR, arge.getMessage());

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}
	}
	
	/**
	 * 
	 * 方法描述 ：上传更换用户头像
	 * @author  chenwf: 
	 * @date 创建时间：2017年4月20日 下午2:01:25 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/y/uploadUserPic")
	@ResponseBody
	public ResponseMsg uploadUserPic(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");
			
			Object[] obj = {"memberId","pic"};
			this.requiredStr(obj, request);
			
			String memberId = reqDataJson.getString("memberId");
			//APP端已文件流形式传到后台
			String pic = reqDataJson.getString("pic");
			String fileName = reqDataJson.getString("fileName");
			//图片上传 获取上传url
		    String filePath = FileUtil.saveFile(FileUtil.BaseToInputStream(pic), fileName, 1, 0);
			
			MemberInfo memberInfo = memberInfoService.findMemberById(Integer.valueOf(memberId));
			memberInfo.setId(Integer.valueOf(memberId));
			memberInfo.setPic(filePath);
			memberInfo.setUpdateBy(Integer.valueOf(memberId));
			memberInfo.setUpdateDate(new Date());
			memberInfoService.updateByPrimaryKeySelective(memberInfo);
			if(!memberInfo.getIsInfPerfect().equals("1") && !memberInfo.getType().equals(Const.MEMBER_INFO_TYPE_TOURIST)){
				setIsInfPerfect(memberInfo);
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("pic", FileUtil.getImageServiceUrl()+filePath);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,map);
		} catch (ArgException arge) {
			return new ResponseMsg(ResponseMsg.ERROR, arge.getMessage());

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}
	}
	private void setIsInfPerfect(MemberInfo memberInfo) {
		Date birthday = memberInfo.getBirthday();
		String nick = memberInfo.getNick();
		String pic = memberInfo.getPic();
		String sexType = memberInfo.getSexType();
		if(!StringUtil.isBlank(nick) && !StringUtil.isBlank(pic) && !StringUtil.isBlank(sexType) && birthday!= null){
			memberInfo.setIsInfPerfect("1");
			memberInfoService.updateByPrimaryKeySelective(memberInfo);
			//获取赠送积分比例
			IntegralType integralType = integralTypeService.findModel(Integer.valueOf(Const.INTEGRAL_TYPE_DATA_PERFECT));
			//积分
			Integer integral = 0;
			//比例成长值
			Integer gValue = 0;
			if(integralType != null){
				if(integralType.getIntType().equals("1")){
					integral = integralType.getIntegral() == null ? 0 : integralType.getIntegral();
				}
				if(integralType.getGrowType().equals("1")){
					gValue = integralType.getgValue() == null ? 0 : integralType.getgValue();
				}
			}
			memberAccountService.updateMemberGroupInfo(integral,gValue,memberInfo.getId()); 
		}
		
	}
	
	/**
	 * 
	 * 方法描述 ：个人资料--保存省市
	 * @author  yjc: 
	 * @date 创建时间：2019年11月20日17:18:46
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/y/saveMemberAreaInfo")
	@ResponseBody
	public ResponseMsg saveMemberAreaInfo(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");
			
			Object[] obj = {"memberId","province","city"};
			this.requiredStr(obj, request);
			
			String memberId = reqDataJson.getString("memberId");
			String province = reqDataJson.getString("province");
			String city = reqDataJson.getString("city");
			memberInfoService.saveMemberAreaInfo(memberId,province,city);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG);
		} catch (ArgException arge) {
			return new ResponseMsg(ResponseMsg.ERROR, arge.getMessage());

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}
	}
}
