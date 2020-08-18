package com.jf.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.google.common.collect.Lists;
import com.jf.common.base.BaseService;
import com.jf.common.constant.StateConst;

import com.jf.entity.*;
import com.jf.vo.SVipInfo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.common.base.ArgException;
import com.jf.common.constant.BaseDefine;
import com.jf.common.constant.Const;
import com.jf.common.utils.DateUtil;
import com.jf.common.utils.FileUtil;
import com.jf.common.utils.StringUtil;
import com.jf.dao.AppAccessTokenMapper;
import com.jf.dao.MemberInfoCustomMapper;
import com.jf.dao.MemberInfoMapper;

import net.sf.json.JSONObject;

@Service
@Transactional
public class MemberInfoService extends BaseService<MemberInfo,MemberInfoExample> {
	@Autowired
	private MemberInfoMapper memberInfoMapper;
	
	@Autowired
	private MemberInfoCustomMapper memberInfoCustomMapper;
	
	@Autowired
	private AppAccessTokenMapper appAccessTokenMapper;
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
	private MemberAccountService memberAccountService;
	@Resource
	private AppAccessTokenService appAccessTokenService;
	@Resource
	private CutPriceOrderDtlService cutPriceOrderDtlService;
	@Autowired
	private MobileVerificationCodeService mobileVerificationCodeService;
	@Resource
	private MemberStatusLogService memberStatusLogService;
	@Resource
	private MemberAttentionService memberAttentionService;
	@Autowired
	private MemberNovaRecordService memberNovaRecordService;
	@Resource
	private MemberExtendService memberExtendService;
	@Resource
	private AreaService areaService;
	@Autowired
	private SVipPracticeRecordService sVipPracticeRecordService;
	
	
	@Autowired
	public void setMemberInfoMapper(MemberInfoMapper memberInfoMapper) {
		super.setDao(memberInfoMapper);
		this.memberInfoMapper = memberInfoMapper;
	}
	
	public AppAccessToken queryAccessToken(Integer memberId ,String token){
		AppAccessTokenExample appAccessTokenExample=new AppAccessTokenExample();
		appAccessTokenExample.createCriteria().andAccessTokenEqualTo(token).andMemberIdEqualTo(memberId).andDelFlagEqualTo("0");
		List<AppAccessToken> appAccessTokens=appAccessTokenMapper.selectByExample(appAccessTokenExample);
		if(appAccessTokens!=null&&appAccessTokens.size()>0){
			return appAccessTokens.get(0);
		}else{
			return null;
		}
	}

	public MemberInfo findMemberById(Integer memberId) {
		
		return memberInfoMapper.selectByPrimaryKey(memberId);
	}

	public void update(MemberInfo memberInfo) {
		memberInfo.setUpdateDate(new Date());
		memberInfoMapper.updateByPrimaryKeySelective(memberInfo);
	}

	public MemberInfo findModelByImei(String imei) {
		if(!StringUtil.isBlank(imei)){
			MemberInfoExample memberInfoExample = new MemberInfoExample();
			memberInfoExample.createCriteria().andReqImeiEqualTo(imei).andDelFlagEqualTo("0")
			.andReqImeiNotEqualTo("").andReqImeiNotEqualTo("unknown").andReqImeiNotEqualTo("00000000");
			List<MemberInfo> memberInfos = memberInfoMapper.selectByExample(memberInfoExample);
			if(CollectionUtils.isNotEmpty(memberInfos)){
				return memberInfos.get(0);
			}
		}
		return null;
	}

	public MemberInfo findModelByMobile(String mobile) {
		MemberInfoExample memberInfoExample = new MemberInfoExample();
		memberInfoExample.createCriteria().andMobileEqualTo(mobile).andDelFlagEqualTo("0");
		List<MemberInfo> memberInfos = memberInfoMapper.selectByExample(memberInfoExample);
		if(CollectionUtils.isNotEmpty(memberInfos)){
			return memberInfos.get(0);
		}
		return null;
	}

	public JSONObject addMemberInfoRegister(String system, String mobile, String password, String regClient, HttpServletRequest request,Integer platformContactId) {
		MemberInfo memberInfo=new MemberInfo();
		memberInfo.setCreateDate(new Date());
		memberInfo.setUpdateDate(new Date());
		memberInfo.setDelFlag("0");
		memberInfo.setmVerfiyFlag("1");
		memberInfo.seteVerfiyFlag("0");
		memberInfo.setType("1");
		//memberInfo.setLoginCode(mobile);
		memberInfo.setRegClient(regClient);
		memberInfo.setMobile(mobile);
		memberInfo.setNick(mobile);
		memberInfo.setLoginPass(password);
		memberInfo.setGroupId(1);//注册会员标识
		insertSelective(memberInfo); 
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
		//添加会员注册状态
		memberStatusLogService.addMemberRegisterStatus(memberInfo.getId(), Const.MEMBER_INFO_STATUS_ACTIVATION_A);
		//会员扩展
		if(platformContactId.equals(0)){
			memberExtendService.addModel(memberInfo.getId(),"","");
		}else{
			memberExtendService.addModel(memberInfo.getId(),"platformContactId",platformContactId.toString());
		}
		String ip = StringUtil.getIpAddr(request);
		JSONObject returnData = login(memberInfo, system, ip,request);
		return returnData;
	}

	public void addLoginLog(Integer memberId, String ip, String system) {
		AppLoginLog appLoginLog = new AppLoginLog();
		appLoginLog.setCreateDate(new Date());
		appLoginLog.setDelFlag("0");
		appLoginLog.setIp(ip);
		appLoginLog.setMemberId(memberId);
		appLoginLog.setSystem(system);
		appLoginLog.setType("1");
		appLoginLogService.insertSelective(appLoginLog);

	}
	
	public JSONObject login(MemberInfo memberInfo, String system,String ip, HttpServletRequest request) {
		String accessToken = UUID.randomUUID().toString();

		// 插入一条登录日志
		addLoginLog(memberInfo.getId(), ip, system);

		MemberGroup memberGroup = memberGroupService.findModel(memberInfo.getGroupId());
		String levelPic = memberGroup.getPic();
		if(!StringUtil.isBlank(levelPic)){
			levelPic = FileUtil.getImageServiceUrl()+levelPic;
		}
		JSONObject returnData = new JSONObject();
		returnData.put("levelName", memberGroup.getName());
		returnData.put("levelPic", levelPic);
		returnData.put("token", accessToken);
		returnData.put("memberId", memberInfo.getId());
		returnData.put("nick", memberInfo.getNick());
		returnData.put("type", memberInfo.getType());
		request.getSession().setAttribute(BaseDefine.MEMBER_INFO, memberInfo);
		return returnData;
	}

	public MemberInfo createMemberInfo(MemberInfo memberInfo){
		insertSelective(memberInfo);
		//创建用户账户信息
		MemberAccount memberAccount = new MemberAccount();
		memberAccount.setMemberId(memberInfo.getId());
		memberAccount.setCreateBy(memberInfo.getId());
		memberAccount.setCreateDate(new Date());
		memberAccount.setDelFlag("0");
		memberAccountService.insertSelective(memberAccount);
		return memberInfo;
	}

	public MemberInfo addMemberInfo(String type, String nick, String thirdPartyMark, String thirdPartyId, String unionid,
			String sprChnl, String regArea, String regClient, String regIp, Date createDate, String imei,
			String mobileBrand, String mobileModel, String sexType, String birthday, String pic, String mobile, String mVerfiyFlag, String invitationMemberId) {
		MemberInfo memberInfo = new MemberInfo();
		if(!StringUtil.isBlank(mobile)){
			memberInfo.setMobile(mobile);
		}
		if(!StringUtil.isBlank(mVerfiyFlag)){
			memberInfo.setmVerfiyFlag(mVerfiyFlag);
		}
		if(!StringUtil.isBlank(type)){
			memberInfo.setType(type);
		}
		if(!StringUtil.isBlank(nick)){
			memberInfo.setNick(nick);
		}
		if(!StringUtil.isBlank(thirdPartyMark)){
			if(thirdPartyMark.equals("WX")){
				memberInfo.setWeixinId(thirdPartyId);
				memberInfo.setRemarks(thirdPartyMark);
			}
		}
		if(!StringUtil.isBlank(unionid)){
			memberInfo.setWeixinUnionid(unionid);
		}
		if(!StringUtil.isBlank(sprChnl)){
			memberInfo.setSprChnl(sprChnl);
		}
		if(!StringUtil.isBlank(regArea)){
			memberInfo.setRegArea(regArea);
		}
		if(!StringUtil.isBlank(regClient)){
			memberInfo.setRegClient(regClient);
		}
		if(!StringUtil.isBlank(regIp)){
			memberInfo.setRegIp(regIp);
		}
		if(!StringUtil.isBlank(imei)){
			memberInfo.setReqImei(imei);
		}
		if(!StringUtil.isBlank(mobileBrand)){
			memberInfo.setReqMobileBrand(mobileBrand);
		}
		if(!StringUtil.isBlank(mobileModel)){
			memberInfo.setReqMobileModel(mobileModel);
		}
		if(!StringUtil.isBlank(sexType)){
			memberInfo.setSexType(sexType);
		}
		if(!StringUtil.isBlank(birthday)){
			Date date = DateUtil.getFormatString(birthday, "yyyy-MM-dd");
			memberInfo.setBirthday(date);
		}
		if(!StringUtil.isBlank(pic)){
			memberInfo.setPic(pic);
		}
		if(!StringUtil.isBlank(invitationMemberId)){
			memberInfo.setInvitationMemberId(Integer.parseInt(invitationMemberId));
			memberInfo.setInvitationCodeBindTime(new Date());
		}
		memberInfo.setCreateDate(createDate);
		memberInfo.setDelFlag("0");
		memberInfo.setGroupId(1);//注册会员标识
		insertSelective(memberInfo);
		//创建用户账户信息
		MemberAccount memberAccount = new MemberAccount();
		memberAccount.setMemberId(memberInfo.getId());
		memberAccount.setCreateBy(memberInfo.getId());
		memberAccount.setCreateDate(createDate);
		memberAccount.setDelFlag("0");
		memberAccountService.insertSelective(memberAccount);
		//绑定好友关系(互相关注)
		memberAttentionService.addMemberMutualConcern(memberInfo.getId(),memberInfo.getInvitationMemberId());
		//会员扩展
		memberExtendService.addModel(memberInfo.getId(),"","");
		return memberInfo;
	}

	public MemberInfo updateweixinUnionId(String weixinUnionId, Integer memberId) {
		//1.查看weixinUnionid微信用户是否存在
		Date date = new Date();
		MemberInfo memberInfo;
		List<MemberInfo> memberInfos = findModelByUnionId(weixinUnionId);
		if(CollectionUtils.isEmpty(memberInfos)){
			//2.存在,更换微信绑定
			memberInfo = findMemberById(memberId);
			if(memberInfo != null && memberInfo.getId() != null){
				memberInfo.setWeixinUnionid(weixinUnionId);
				memberInfo.setUpdateDate(date);
				updateByPrimaryKeySelective(memberInfo);
			}else{
				throw new ArgException("未登入");
			}
		}else{
			//2.不存在
			throw new ArgException("绑定失败,您的微信已注册过醒购或已绑定过其他账号。");
		}
		
		return memberInfo;
	}

	public List<MemberInfo> findModelByUnionId(String weixinUnionId) {
		MemberInfoExample memberInfoExample = new MemberInfoExample();
		memberInfoExample.createCriteria().andWeixinUnionidEqualTo(weixinUnionId).andDelFlagEqualTo(Const.FLAG_FALSE);
		memberInfoExample.setOrderByClause("id desc");
		List<MemberInfo> memberInfos = selectByExample(memberInfoExample);
		return memberInfos;
	}

	public void addGiftInte(String tallyMode ,String type, Integer memberId, boolean isGiftInte, boolean isGiftGVlaue) {
		MemberAccount memberAccount = memberAccountService.findAccountByMemberId(memberId);
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
		if(integral != 0 && isGiftInte){
			memberAccount.setIntegral(integral+memberAccount.getIntegral());
			integralDtlService.addIntegralDtl(memberAccount.getId(), tallyMode, type, 
					integral, memberAccount.getIntegral(), null, memberId,"");
		}
		if(gValue != 0 && isGiftGVlaue){
			memberAccount.setgValue(gValue+memberAccount.getgValue());
			growthDtlService.addGrowthDtl(memberAccount.getgValue(), gValue, memberAccount.getId(), memberId, null,type);
		}
		memberAccountService.updateByPrimaryKeySelective(memberAccount);
	}

	public boolean getIsNewEnjoy(Integer memberId, MemberInfo m) {
		boolean isNewEnjoy = false;
		Date currentDate = new Date();
		Date begin = DateUtil.getDateAfterAndBeginTime(currentDate, 0);
		Date end = DateUtil.getDateAfterAndEndTime(currentDate, 0);
		if(m == null || m.getId() == null){
			m = selectByPrimaryKey(memberId);
		}
		Date createDate = m.getCreateDate();
		if(createDate.after(begin) && createDate.before(end)){
			isNewEnjoy = true;
		}
		return isNewEnjoy;
	}

	public String getMemberAuditStatus(Integer memberId, String activityType) {
		//0不提示信息 1已邀请过 2未邀请过
		String auditStatus = "1";
		//判断是否是新用户&& 是否已经邀请过了
		List<Integer> mIds = new ArrayList<Integer>();
		mIds.add(memberId);
		MemberInfo m = selectByPrimaryKey(memberId);
		boolean isNewEnjoy = getIsNewEnjoy(memberId, m);
		if(!StringUtil.isBlank(m.getReqImei())){
			MemberInfoExample infoExample = new MemberInfoExample();
			infoExample.createCriteria().andReqImeiEqualTo(m.getReqImei()).andDelFlagEqualTo("0");
			List<MemberInfo> memberInfos = selectByExample(infoExample);
			if(CollectionUtils.isNotEmpty(memberInfos)){
				for (MemberInfo memberInfo : memberInfos) {
					mIds.add(memberInfo.getId());
				}
			}
		}
		if(CollectionUtils.isNotEmpty(mIds)){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("mIds", mIds);
			map.put("activityType", activityType);
			int count = cutPriceOrderDtlService.getAlreadyInvitedCount(map);
			if(isNewEnjoy && count <= 0){
				auditStatus = "2";
			}
		}
		return auditStatus;
	}

	// 1未邀请过 2当前助力 3其他单助力/旧用户
	public int getMemberAssistanceStatus(Integer memberId, Integer currentCutOrderId) {
		boolean isNewEnjoy = getIsNewEnjoy(memberId, null);
		List<CutPriceOrderDtl> dtls = cutPriceOrderDtlService.findMemberAssistanceLog(memberId);
		if (CollectionUtils.isEmpty(dtls)) { //无助力记录
			if (isNewEnjoy) { //当日注册新用户
				return 1;
			}
			return 3;
		}

		// 有助力记录
		boolean currentAssistance = false; //当前单是否助力过
		for (CutPriceOrderDtl dtl : dtls) {
			if (dtl.getCutPriceOrderId().equals(currentCutOrderId)) {
				currentAssistance = true;
				break;
			}
		}
		return currentAssistance ? 2 : 3;
	}
	
	
	public JSONObject visitorsRegisteredMembers(JSONObject reqPRM, JSONObject reqDataJson, Integer memberId, HttpServletRequest request) {
		String mobile=reqDataJson.getString("mobile");
		String password = null;
		String type = "";
		if(reqDataJson.containsKey("type") && !StringUtil.isBlank(reqDataJson.getString("type"))){
			type = reqDataJson.getString("type");
			if("1".equals(type)){
				password=reqDataJson.getString("password");
			}
		}else{
			password=reqDataJson.getString("password");
		}
		String mobileVerificationCode = reqDataJson.getString("mobileVerificationCode");
		if(!StringUtil.isMobile(reqDataJson.getString("mobile"))){
			throw new ArgException("请输入正确的手机号");
		}
		if(!mobileVerificationCodeService.verify(mobile,mobileVerificationCode)){
			throw new ArgException("验证码错误!");
		}
		//从游客变为正式会员
		MemberInfoExample memberInfoCount = new MemberInfoExample();
		memberInfoCount.createCriteria().andMobileEqualTo(mobile).andDelFlagEqualTo("0");
		int count = memberInfoMapper.countByExample(memberInfoCount);
		if(count > 0){
			throw new ArgException("该手机号已注册过或已被绑定过!");
		}
		MemberInfoExample memberInfoExample = new MemberInfoExample();
		memberInfoExample.createCriteria().andDelFlagEqualTo("0").andIdEqualTo(memberId);
		List<MemberInfo> memberInfos = memberInfoMapper.selectByExample(memberInfoExample);
		MemberInfo memberInfo = new MemberInfo();
		if(CollectionUtils.isNotEmpty(memberInfos)){
			memberInfo = memberInfos.get(0);
			memberInfo.setMobile(mobile);
			memberInfo.setmVerfiyFlag("1");
			memberInfo.setNick(mobile);
			//memberInfo.setLoginCode(mobile);
			memberInfo.setLoginPass(password);
			memberInfo.setType("1");
			memberInfo.setUpdateBy(memberId);
			memberInfo.setUpdateDate(new Date());
			memberInfoMapper.updateByPrimaryKeySelective(memberInfo);
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
			MemberAccount memberAccount = memberAccountService.findAccountByMemberId(memberId);
			if(memberAccount != null){
				memberAccount.setIntegral(memberAccount.getIntegral()+integral);
				memberAccount.setgValue(memberAccount.getgValue()+gValue);
				memberAccountService.updateModel(memberAccount);
				memberGroupService.updateMemberGroup(memberAccount.getgValue(),memberId);
				if(gValue != 0){
					growthDtlService.addGrowthDtl(gValue, gValue, memberAccount.getId(), memberId, null, Const.INTEGRAL_TYPE_MOBILE_AUTHENTICATION);
				}
				if(integral != 0){
					integralDtlService.addIntegralDtl(memberAccount.getId(), Const.INTEGRAL_TALLY_MODE_INCOME, 
							Const.INTEGRAL_TYPE_MOBILE_AUTHENTICATION, integral, integral, null, memberInfo.getId(),"1");
				}
			}
		}else{
			throw new ArgException("找不到该会员");
		}
		String accessToken = UUID.randomUUID().toString();
		MemberGroup memberGroup = memberGroupService.findModel(memberInfo.getGroupId());
		String levelPic = "";
		String levelName = "";
		if(memberGroup != null){
			levelPic = memberGroup.getPic();
			levelName = memberGroup.getName();
			if(!StringUtil.isBlank(levelPic)){
				levelPic = FileUtil.getImageServiceUrl()+levelPic;
			}
		}
		request.getSession().setAttribute(BaseDefine.MEMBER_INFO, memberInfo);
		JSONObject returnData = new JSONObject();
		returnData.put("levelName", levelName);
		returnData.put("levelPic", levelPic);
		returnData.put("token", accessToken);
		returnData.put("memberId", memberInfo.getId());
		returnData.put("nick", memberInfo.getNick());
		returnData.put("type", memberInfo.getType());
		return returnData;
	}
	
	public String getIsSvip(MemberInfo memberInfo, Integer memberId) {
		Date currentDate = new Date();
		if(memberInfo == null){
			memberInfo = selectByPrimaryKey(memberId);
		}
		String isSvip = memberInfo.getIsSvip();
		Date svipExpireDate = memberInfo.getSvipExpireDate();
		if("1".equals(isSvip) && svipExpireDate != null && currentDate.before(svipExpireDate)){
			isSvip = "1";
		}else{
			isSvip = "0";
		}
		return isSvip;
	}

	public boolean getMemberIsBindSuperior(MemberInfo memberInfo, Integer memberId) {
		boolean isBind = true;
		if(memberInfo == null){
			memberInfo = selectByPrimaryKey(memberId);
		}
		if(memberInfo.getInvitationMemberId() == null){
			isBind = false;
		}
		return isBind;
	}
	
	/**
	 * 找出会员的下级会员的数量
	 */
	public int getMemberSubCount(MemberInfo memberInfo, Integer memberId,String openType) {
		Integer count = 0;
		Date currentDate = new Date();
		if(memberInfo == null){
			memberInfo = selectByPrimaryKey(memberId);
		}
		
		MemberNovaRecord memberNovaRecord = memberNovaRecordService.getCurrentContractSigning(memberInfo.getId(),openType);
		if(memberNovaRecord != null){
			Date novaProjectBeginDate = memberNovaRecord.getAgreementBeginDate();
			Date novaProjectEndDate = memberNovaRecord.getAgreementEndDate();
			MemberInfoExample memberInfoExample = new MemberInfoExample();
			memberInfoExample.createCriteria().andInvitationMemberIdEqualTo(memberId).andInvitationCodeBindTimeLessThanOrEqualTo(currentDate)
			.andInvitationCodeBindTimeGreaterThanOrEqualTo(novaProjectBeginDate).andDelFlagEqualTo("0");
			count = countByExample(memberInfoExample);
		}
		return count;
	}
	
	/**
	 * 找出会员的下级会员
	 */
	public List<Integer> getMemberSubUser(Integer memberId) {
		List<Integer> memberIds = new ArrayList<>();
		MemberInfoExample memberInfoExample = new MemberInfoExample();
		memberInfoExample.createCriteria().andInvitationMemberIdEqualTo(memberId).andDelFlagEqualTo("0");
		List<MemberInfo> infos = selectByExample(memberInfoExample);
		if(CollectionUtils.isNotEmpty(infos)){
			for (MemberInfo info : infos) {
				memberIds.add(info.getId());
			}
		}
		return memberIds;
	}
	
	/**
	 * 找出邀请码相对的会员
	 */
	public MemberInfo getMemberByInvitationCode(String invitationCode) {
		MemberInfo memberInfo = null;
		MemberInfoExample memberInfoExample = new MemberInfoExample();
		memberInfoExample.createCriteria().andInvitationCodeEqualTo(invitationCode).andDelFlagEqualTo("0");
		List<MemberInfo> infos = selectByExample(memberInfoExample);
		if(CollectionUtils.isNotEmpty(infos)){
			memberInfo = infos.get(0);
		}
		return memberInfo;
	}

	public Map<String, Object> updateMemberInvitationCode(JSONObject reqPRM, JSONObject reqDataJson, HttpServletRequest request) {
		String invitationCode = reqDataJson.getString("invitationCode");
		MemberInfo memberInfo = (MemberInfo) request.getSession().getAttribute(BaseDefine.MEMBER_INFO);
		Integer memberId = memberInfo.getId();
		Map<String, Object> map = new HashMap<>();
		boolean isSuccess = true;
		String msg = "成功绑定上级";
		if(memberInfo.getInvitationMemberId() != null){
			isSuccess = false;
			msg = "您已绑定上级，请勿重复绑定";
			map.put("isSuccess", isSuccess);
			map.put("msg", msg);
			return map;
		}
		MemberInfo parentUser = getMemberByInvitationCode(invitationCode);
		if(parentUser == null){
			isSuccess = false;
			msg = "邀请码不存在";
			map.put("isSuccess", isSuccess);
			map.put("msg", msg);
			return map;
		}
		if(parentUser.getCreateDate().after(memberInfo.getCreateDate())){
			isSuccess = false;
			msg = "绑定失败，不能绑定新用户为上级";
			map.put("isSuccess", isSuccess);
			map.put("msg", msg);
			return map;
		}
		if(isSuccess){
			Date currentDate = new Date();
			MemberInfoExample memberInfoExample = new MemberInfoExample();
			memberInfoExample.createCriteria().andInvitationMemberIdIsNull().andDelFlagEqualTo("0").andIdEqualTo(memberId);
			memberInfo.setInvitationMemberId(parentUser.getId());
			memberInfo.setInvitationCodeBindTime(currentDate);
			memberInfo.setUpdateDate(currentDate);
			int count = updateByExampleSelective(memberInfo, memberInfoExample);
			if(count <= 0){
				throw new ArgException("您已绑定上级，请勿重复绑定");
			}
			request.getSession().setAttribute(BaseDefine.MEMBER_INFO, memberInfo);
			//互相关注
			reqDataJson.put("memberId", memberId);
			reqDataJson.put("friendMemberId", parentUser.getId());
			reqDataJson.put("type", "1");
			memberAttentionService.updateMemberAttention(reqPRM, reqDataJson);
			reqDataJson.put("memberId", parentUser.getId());
			reqDataJson.put("friendMemberId", memberId);
			reqDataJson.put("type", "1");
			memberAttentionService.updateMemberAttention(reqPRM, reqDataJson);
			//用户增加150积分
			Integer integral = 150;
			memberAccountService.addGiftIntegral(integral,memberInfo,memberId,Const.INTEGRAL_TYPE_INVITATION_CODE);
		}
		map.put("isSuccess", isSuccess);
		map.put("msg", msg);
		return map;
	}
	
	/**
	 * 获取会员关注的用户
	 */
	public List<MemberInfoCustom> getMemberFollowUser(Map<String, Object> paramsMap) {
		
		return memberInfoCustomMapper.getMemberFollowUser(paramsMap);
	}
	/**
	 * 获取我的粉丝
	 */
	public List<MemberInfoCustom> getMemberFansUser(Map<String, Object> paramsMap) {
		
		return memberInfoCustomMapper.getMemberFansUser(paramsMap);
	}
	/**
	 * 获取我的推荐好友
	 */
	public List<MemberInfoCustom> getMemberRecommendUser(Map<String, Object> paramsMap) {

		return memberInfoCustomMapper.getMemberRecommendUser(paramsMap);
	}

	public void setIsInfPerfect(MemberInfo memberInfo) {
		Date birthday = memberInfo.getBirthday();
		String nick = memberInfo.getNick();
		String pic = memberInfo.getPic();
		String sexType = memberInfo.getSexType();
		if(!StringUtil.isBlank(nick) && !StringUtil.isBlank(sexType) && birthday!= null){
			memberInfo.setIsInfPerfect("1");
			updateByPrimaryKeySelective(memberInfo);
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

	public boolean isRealSVip(MemberInfo memberInfo, Integer memberId) {
		if (memberInfo == null) {
			memberInfo = selectByPrimaryKey(memberId);
		}
		return memberInfo != null
				&& StateConst.TRUE.equals(memberInfo.getIsSvip())
				&& memberInfo.getSvipExpireDate() != null
				&& new Date().before(memberInfo.getSvipExpireDate());
	}

	public SVipInfo isSVip(MemberInfo memberInfo, Integer memberId) {
		if(memberId==null) return SVipInfo.no();

		//真实svip判断
		if (isRealSVip(memberInfo, memberId)) {
			return SVipInfo.real();
		}
		//试用svip判断
		SvipPracticeRecord svipPracticeRecord = sVipPracticeRecordService.getMemberLastRecRecord(memberId);
		boolean isUsableSVipPractice = isSVipPractice(svipPracticeRecord);
		if (isUsableSVipPractice) {
			return SVipInfo.trail(svipPracticeRecord);
		}
		return SVipInfo.no();
	}

	private boolean isSVipPractice(SvipPracticeRecord recRecord) {
		if (recRecord == null || StateConst.TRUE.equals(recRecord.getDelFlag()) || recRecord.getPracticeStartTime() == null || recRecord.getPracticeEndTime() == null) {
			return false;
		}
		Date now = new Date();
		return now.after(recRecord.getPracticeStartTime()) && now.before(recRecord.getPracticeEndTime());
	}

	public void updateMemberSvipInfo(SvipOrder order) {
		Date curentDate = new Date();
		Integer memberId = order.getMemberId();
		Integer yearsOfPurchase = order.getYearsOfPurchase();
		MemberInfo memberInfo = findMemberById(memberId);
		Date svipExpireDate = memberInfo.getSvipExpireDate();
		if (svipExpireDate == null) {
			svipExpireDate = DateUtil.addYear(curentDate, yearsOfPurchase);
		} else {
			boolean isSvip = isRealSVip(memberInfo, memberId);
			if (isSvip) {
				svipExpireDate = DateUtil.addYear(svipExpireDate, yearsOfPurchase);
			} else {
				svipExpireDate = DateUtil.addYear(curentDate, yearsOfPurchase);
			}
		}
		memberInfo.setIsSvip("1");
		memberInfo.setSvipExpireDate(svipExpireDate);
		memberInfo.setUpdateDate(curentDate);
		memberInfo.setUpdateBy(memberId);
		updateByPrimaryKeySelective(memberInfo);
	}

	public void saveMemberAreaInfo(String memberId, String province, String city) {
		Area provinceArea = areaService.selectByPrimaryKey(Integer.parseInt(province));
		Area cityArea = areaService.selectByPrimaryKey(Integer.parseInt(city));
		MemberInfo mi = new MemberInfo();
		mi.setUpdateDate(new Date());
		mi.setRegArea(provinceArea.getAreaName()+cityArea.getAreaName());
		mi.setProvince(Integer.parseInt(province));
		mi.setCity(Integer.parseInt(city));
		MemberInfoExample mie = new MemberInfoExample();
		mie.createCriteria().andDelFlagEqualTo("0").andIdEqualTo(Integer.parseInt(memberId));
		this.updateByExampleSelective(mi, mie);
	}
}
