package com.jf.controller;

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
	private XiaonengCustomerServiceService xiaonengCustomerServiceService;
	@Resource
	private MemberCouponService memberCouponService;
	@Resource
	private MemberExtendService memberExtendService;
	@Resource
	private NovaPlanService novaPlanService;
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
			
			String memberId = getMemberIdStr(request);
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
			
			String newPwd = reqDataJson.getString("newPwd");
			
			if(StringUtil.isBlank(newPwd)){
				throw new ArgException("请输入新密码");
			}
			Integer memberId = getMemberId(request);
			MemberInfo memberInfo = memberInfoService.selectByPrimaryKey(memberId);
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
			Integer memberId = getMemberId(request);
			MemberInfo memberInfo = memberInfoService.selectByPrimaryKey(memberId);
			MemberGroup memberGroup = memberGroupService.selectByPrimaryKey(memberInfo.getGroupId());
			SVipInfo sVipInfo = memberInfoService.isSVip(memberInfo, memberId);
			JSONObject returnData = new JSONObject();
			String pic = StringUtil.getPic(memberInfo.getPic(), "");
			String birthday = "";
			if(memberInfo.getBirthday() != null){
				birthday = DateUtil.getFormatDate(memberInfo.getBirthday(), "yyyy-MM-dd");
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
			//获取优惠券数量
			MemberCouponExample memberCouponExample = new MemberCouponExample();
			memberCouponExample.createCriteria().andMemberIdEqualTo(memberId).andStatusEqualTo("0").andDelFlagEqualTo("0")
				.andExpiryEndDateGreaterThan(currentDate);
			int memberCounponNum = memberCouponService.countByExample(memberCouponExample);
			//获取用户积分
			MemberAccount account = memberAccountService.findAccountByMemberId(memberId);
			BigDecimal profitInviteBalance = account.getProfitInviteBalance().subtract(account.getProfitInviteFreeze());
			//新星计划开启
			String novaPlanOpen = "0";
			SysParamCfg paramCfgs = DataDicUtil.getSysParamCfgModel("APP_H5_NOVA_PLAN_OPEN", "3");
			if(paramCfgs != null){
				if("1".equals(paramCfgs.getParamName())){
					novaPlanOpen = novaPlanService.getNewNovaPlanOpenType(memberInfo, memberId);;
				}
			}
			List<SysParamCfg> rechargeCfgList = DataDicUtil.getSysParamCfg("APP_MEMBER_RECHARGE");
			if (CollectionUtil.isEmpty(rechargeCfgList)) {
				returnData.put("showRechargeCenterBtn", "0");
			}else{
				SysParamCfg rechargeCfg = rechargeCfgList.get(0);
				returnData.put("showRechargeCenterBtn", rechargeCfg.getParamOrder()); //充值中心开关 0关 1开
				returnData.put("rechargeCenterUrl", rechargeCfg.getParamValue()); //充值中心地址
			}
			returnData.put("profitInviteBalance", profitInviteBalance);
			returnData.put("novaPlanOpen", novaPlanOpen);
			returnData.put("invitationCode", "");
			returnData.put("memberCounponNum", memberCounponNum);
			returnData.put("memberintegral", account.getIntegral());
			returnData.put("pic", pic);
			returnData.put("nick", memberInfo.getNick() == null ? "" : memberInfo.getNick());
			returnData.put("mobile", memberInfo.getMobile() == null ? "" : memberInfo.getMobile());
			returnData.put("mVerfiyFlag", memberInfo.getmVerfiyFlag());
			returnData.put("weixinUnionid", memberInfo.getWeixinUnionid() == null ? "" : memberInfo.getWeixinUnionid());
			returnData.put("sexType", memberInfo.getSexType() == null ? "" : memberInfo.getSexType());
			returnData.put("birthday", birthday);
			returnData.put("levelName", memberGroup.getName());
			returnData.put("levelPic", levelPic);
			returnData.put("isAcceptPush", memberInfo.getIsAcceptPush());
			returnData.put("type", memberInfo.getType());
			returnData.put("customerService", "6");
			returnData.put("customerServiceType", "3");
			returnData.put("customerServiceMobile", "01053185321");
			returnData.put("customerServiceWorkTime", "( 工作日 9:00-22:00 )");
			returnData.put("isHasPwd", isHasPwd);
			returnData.put("isHasBindingWx", isHasBindingWx);
			returnData.put("xiaoNengId", "je_1000_1523349224914");//售后
			returnData.put("userLevel", "0");
			returnData.put("memberId", memberId);
			returnData.put("platformInvestmentUrl", Config.getProperty("mUrl")+"/settled/views/index.html");
			returnData.put("isSvip", sVipInfo.isRealSVip() ? StateConst.TRUE : StateConst.FALSE);
			returnData.put("isTrailSvip", sVipInfo.isTrail() ? StateConst.TRUE : StateConst.FALSE);
			returnData.put("trailTip", buildTrailTip(sVipInfo));

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
				SysParamCfg paramCfgsPic = DataDicUtil.getSysParamCfgModel("THEME_BACKGROUND_PIC", null);
				backgroundPic = StringUtil.getPic(paramCfgsPic.getParamValue(), "");
			}
			/*List<Map<String, String>> svipPrivilegePics = new ArrayList<>(); //Svip特权图片列表
			List<SysParamCfg> cfgs = DataDicUtil.getSysParamCfg("APP_SVIP_PRIVILEGE_PIC");
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
			if(sVipInfo.isRealSVip()) {
				saveAmont = orderDtlService.getSvipSaveAmount(memberId);
				if(saveAmont == null){
					saveAmont = new BigDecimal("0");
				}
			}*/
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
			/*returnData.put("svipPrivilegePics", svipPrivilegePics); //Svip特权图片列表
			returnData.put("saveAmont", saveAmont); //已节省金额*/
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
			
			Integer memberId = getMemberId(request);
			MemberInfo memberInfo = memberInfoService.findMemberById(memberId);
			String type = "1";
			if(!JsonUtils.isEmpty(reqDataJson, "type")){
				type = reqDataJson.getString("type");
			}
			//修改图片 pic
			if(!JsonUtils.isEmpty(reqDataJson, "pic") && !StringUtil.isBlank(reqDataJson.getString("pic"))){
				String pic = StringUtil.replace(reqDataJson.getString("pic"),"xgbuy.cc");
				memberInfo.setPic(pic);
			}
			
			//修改昵称 nick
			if(!JsonUtils.isEmpty(reqDataJson, "nick") && !StringUtil.isBlank(reqDataJson.getString("nick"))){
				memberInfo.setNick(reqDataJson.getString("nick"));
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
				if("1".equals(type)){
					memberInfo.setIsAcceptPush(reqDataJson.getString("isAcceptPush"));
				}else if("2".equals(type)){
					//更新会员扩展表信息
					memberExtendService.updateMemberExtendInfo(memberId); 
				}
			}
			memberInfoService.updateByPrimaryKeySelective(memberInfo);
			if(!memberInfo.getIsInfPerfect().equals("1") && !memberInfo.getType().equals(Const.MEMBER_INFO_TYPE_TOURIST)){
				memberInfoService.setIsInfPerfect(memberInfo);
			}
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,null);
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
			Integer memberId = getMemberId(request);
			if(StringUtil.isBlank(mobile)){
				throw new ArgException("手机号码不能为空");
			}
			if(StringUtil.isBlank(mobileVerificationCode)){
				throw new ArgException("验证码不能为空");
			}
			if(!mobileVerificationCodeService.verify(mobile,mobileVerificationCode)){
				throw new ArgException("验证码错误");
			}else{
				MemberInfo mobileInfo = memberInfoService.findModelByMobile(mobile);
				if(mobileInfo != null){
					throw new ArgException("该手机号码已被绑定");
				}else{
					MemberInfo memberInfo = memberInfoService.findMemberById(memberId);
					if(StringUtil.isBlank(memberInfo.getMobile()) && memberInfo.geteVerfiyFlag().equals("0")){
						//还没有绑定过手机
						//赠送积分
						memberInfoService.addGiftInte(Const.INTEGRAL_TALLY_MODE_INCOME,
								Const.INTEGRAL_TYPE_MOBILE_AUTHENTICATION,memberId,
								true,false);
					}
					//修改绑定手机 mobile
					memberInfo.setMobile(mobile);
					memberInfo.setmVerfiyFlag("1");
					memberInfo.setId(memberId);
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
			
			Object[] obj = {"pic"};
			this.requiredStr(obj, request);
			
			String memberId = getMemberIdStr(request);
			//APP端已文件流形式传到后台
			String pic = reqDataJson.getString("pic");
			pic = StringUtil.replace(pic, "xgbuy.cc");
//			String fileName = reqDataJson.getString("fileName");
//			//图片上传 获取上传url
//		    String filePath = FileUtil.saveFile(FileUtil.BaseToInputStream(pic), fileName, 1, 0);
			
			MemberInfo memberInfo = memberInfoService.findMemberById(Integer.valueOf(memberId));
			memberInfo.setId(Integer.valueOf(memberId));
			memberInfo.setPic(pic);
			memberInfo.setUpdateBy(Integer.valueOf(memberId));
			memberInfo.setUpdateDate(new Date());
			memberInfoService.updateByPrimaryKeySelective(memberInfo);
//			if(!memberInfo.getIsInfPerfect().equals("1") && !memberInfo.getType().equals(Const.MEMBER_INFO_TYPE_TOURIST)){
//				setIsInfPerfect(memberInfo);
//			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("pic", FileUtil.getImageServiceUrl()+pic);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,map);
		} catch (ArgException arge) {
			return new ResponseMsg(ResponseMsg.ERROR, arge.getMessage());

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
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
