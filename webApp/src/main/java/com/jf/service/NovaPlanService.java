package com.jf.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.common.base.ArgException;
import com.jf.common.constant.BaseDefine;
import com.jf.common.constant.Const;
import com.jf.common.utils.DateUtil;
import com.jf.common.utils.JsonUtils;
import com.jf.common.utils.StringUtil;
import com.jf.entity.Information;
import com.jf.entity.MchtShopDynamic;
import com.jf.entity.MemberAccount;
import com.jf.entity.MemberDynamic;
import com.jf.entity.MemberDynamicExample;
import com.jf.entity.MemberInfo;
import com.jf.entity.MemberInfoCustom;
import com.jf.entity.MemberNovaRecord;
import com.jf.entity.MemberNovaRecordCustom;
import com.jf.entity.Salesman;
import com.jf.entity.Shopowner;

import net.sf.json.JSONObject;

@Service
@Transactional
public class NovaPlanService{
	@Autowired
	private MemberInfoService memberInfoService;
	@Autowired
	private MemberAccountService memberAccountService;
	@Autowired
	private MemberSignInSettingService memberSignInSettingService;
	@Autowired
	private MemberDynamicService memberDynamicService;
	@Autowired
	private IntegralDtlService integralDtlService;
	@Autowired
	private InformationService informationService;
	@Autowired
	private MemberNovaRecordService memberNovaRecordService;
	@Autowired
	private CommonService commonService;
	@Autowired
	private CombineOrderService combineOrderService;
	@Autowired
	private NovaStrategyService novaStrategyService;
	@Autowired
	private MchtShopDynamicService mchtShopDynamicService;
	@Autowired
	private MemberDynamicLikeService memberDynamicLikeService;
	@Autowired
	private CustomAdPageService customAdPageService;
	@Autowired
	private DecorateInfoService decorateInfoService;
	@Autowired
	private ShopownerService shopownerService;
	@Autowired
	private SalesmanService salesmanService;
	
	private static final int NOVA_TASK_COUNT = 6;//醒购新星计划任务邀请人数
	private static final int DYNAMICS_TASK_COUNT = 15;//签约期间发布动态数量
	private static final int SIGN_IN_COUNT = 15;//累计签到天数
	private static final int MEMBER_SUB_ORDER_COUNT = 10;//会员下级订单数量
	private static int min=10000000;
	private static int max=99999999;

	public Map<String, Object> getNovaPlanIndex(MemberInfo memberInfo) {
		Map<String, Object> map = new HashMap<>();
		Integer memberId = memberInfo.getId();
		String informationId = "";
		String novaPlanUrlInformationId = "";
		MemberAccount memberAccount = memberAccountService.findAccountByMemberId(memberId);
		BigDecimal profitInviteBalance = memberAccount.getProfitInviteBalance().subtract(memberAccount.getProfitInviteFreeze());//余额
		String openType = getNewNovaPlanOpenType(memberInfo, memberId);
		int memberSubCount = 0;
		Integer catalogId = null;
		if("1".equals(openType)){
			catalogId = Const.NOVA_PLAN_AGREEMENT_CATALOG_ID;
			novaPlanUrlInformationId = "238";
		}else if("3".equals(openType)){
			catalogId = Const.NOVA_PLAN_SHOPWNER_AGREEMENT_CATALOG_ID;
			novaPlanUrlInformationId = "251";
		}else{
			throw new ArgException("未开通");
		}
		
		List<Information> informations = informationService.getNovaPlanAgreement(catalogId);
		if(CollectionUtils.isNotEmpty(informations)){
			informationId = informations.get(0).getId().toString();
		}
		List<Map<String, Object>> novaPlanTaskList = getNovaPlanTaskList(memberInfo, memberId,openType,informationId);//协议任务
		List<Map<String, Object>> everyDateTaskList = getEveryDateTaskList(memberInfo, memberId,openType,memberSubCount);//日常任务
		List<Map<String, Object>> noviceTaskList = getNoviceTaskList(memberAccount.getId(),openType);//新手任务
		List<Map<String, Object>> renewalTaskList = new ArrayList<>();//续签任务
		map.put("novaPlanTaskList", novaPlanTaskList);
		map.put("everyDateTaskList", everyDateTaskList);
		map.put("noviceTaskList", noviceTaskList);
		map.put("renewalTaskList", renewalTaskList);
		map.put("profitInviteBalance", profitInviteBalance);
		map.put("openType", openType);
		map.put("mVerfiyFlag", memberInfo.getmVerfiyFlag());
		map.put("novaPlanAgreementUrl", commonService.getCloumnLinkUrl(informationId, "6"));
		map.put("novaPlanUrl", commonService.getCloumnLinkUrl(novaPlanUrlInformationId, "5"));
		return map;
	}
	/**
	 * 续签任务
	 * @param accId 
	 * @param memberInfo 
	 * @param id
	 * @param openType
	 * @return
	 */
	private List<Map<String, Object>> getRenewalTaskList(MemberInfo memberInfo, Integer memberId, Integer accId, String openType) {
		List<Map<String, Object>> renewalTaskList = new ArrayList<>();
		Map<String, Object> dataMap = new HashMap<>();
		if(!"0".equals(openType)){
			Map<String, Object> renewalMap = getRenewalTaskPro(memberInfo,memberId,"1");
			String statusText = "";
			if((boolean) renewalMap.get("isSuccess")){
				statusText = "已完成";
			}else{
				statusText = "未完成";
			}
			//好货推荐
			dataMap.put("title", "醒购新星计划任务进度");
			dataMap.put("content", "完成任一续签任务，才能获得续签资格哦~");
			dataMap.put("statusText", statusText);
			dataMap.put("status", statusText.equals("已完成") ? 1 : 0);
			dataMap.put("taskType", "2");
			renewalTaskList.add(dataMap);
		}
		return renewalTaskList;
	}
	
	/**
	 * 新手任务
	 * @param memberInfo
	 * @param memberId
	 * @param openType 
	 * @return
	 */
	private List<Map<String, Object>> getNoviceTaskList(Integer accId, String openType) {
		List<Map<String, Object>> noviceTaskList = new ArrayList<>();
		Map<String, Object> dataMap = new HashMap<>();
		String title = "";
		String content = "";
		String linkUrl = "";
		Integer integralType = Integer.parseInt(Const.INTEGRAL_TYPE_SEE_NOVA_STRATEGY);
		if("1".equals(openType)){
			title = "新星攻略";
			content = "带你快速了解新星计划";
			linkUrl = commonService.getCloumnLinkUrl("", "8");
		}else if("3".equals(openType)){
			title = "店长攻略";
			content = "带你快速了解醒购店长";
			linkUrl = commonService.getCloumnLinkUrl("", "9");
		}
		String statusText = "";
		boolean isNoviceIntegral = integralDtlService.getIsNoviceIntegral(accId,integralType);
		if(isNoviceIntegral){
			statusText = "完成";
		}else{
			statusText = "+150积分";
		}
		
		//新星攻略
		dataMap.put("title", title);
		dataMap.put("content", content);
		dataMap.put("statusText", statusText);
		dataMap.put("status", statusText.equals("完成") ? 1 : 0);
		dataMap.put("linkUrl", linkUrl);
		dataMap.put("taskType", "8");
		noviceTaskList.add(dataMap);
		
		//好货推荐
		dataMap = new HashMap<>();
		dataMap.put("title", "好货推荐");
		dataMap.put("content", "好货推荐专区，更多精选商品等你来");
		dataMap.put("statusText", "好赚钱");
		dataMap.put("status", "0");
		dataMap.put("linkUrl", "");
		dataMap.put("taskType", "9");
		noviceTaskList.add(dataMap);
		return noviceTaskList;
	}

	/**
	 * 每日任务
	 * @param memberInfo
	 * @param memberId
	 * @param openType 
	 * @param memberSubCount 
	 * @return
	 */
	private List<Map<String, Object>> getEveryDateTaskList(MemberInfo memberInfo, Integer memberId, String openType, int memberSubCount) {
		List<Map<String, Object>> novaPlanTaskList = new ArrayList<>();//日常任务
		int sameDayDynamicsCount = memberDynamicService.getSameDayDynamicsCount(memberId);
		boolean memberIsSign = memberSignInSettingService.getMemberIsSign(memberId);
		String statusText = "";
		Map<String, Object> dataMap = new HashMap<>();
		//邀请好友
		dataMap = new HashMap<>();
		dataMap.put("title", "邀请好友");
		dataMap.put("content", "邀请好友注册并下单,即获得3元奖励");
		dataMap.put("statusText", "+3.00元");
		dataMap.put("status", "0");
		dataMap.put("taskType", "4");
		novaPlanTaskList.add(dataMap);
		//签到
		dataMap = new HashMap<>();
		if(memberIsSign){
			statusText = "已完成";
		}else{
			statusText = "未完成";
		}
		dataMap.put("title", "签到");
		dataMap.put("content", "每天签到，可获得现金/积分奖励");
		dataMap.put("statusText", statusText);
		dataMap.put("status", statusText.equals("已完成") ? 1 : 0);
		dataMap.put("taskType", "6");
		novaPlanTaskList.add(dataMap);
		//发布动态
		dataMap = new HashMap<>();
		if(sameDayDynamicsCount > 0){
			statusText = "已完成";
		}else{
			statusText = "未完成";
		}
		dataMap.put("title", "发布动态");
		dataMap.put("content", "每天发布动态信息，可促进好友成交订单");
		dataMap.put("statusText", statusText);
		dataMap.put("status", statusText.equals("已完成") ? 1 : 0);
		dataMap.put("taskType", "7");
		novaPlanTaskList.add(dataMap);
		return novaPlanTaskList;
	}

	/**
	 * 新星计划任务
	 * @param memberInfo
	 * @param memberId
	 * @param openType 
	 * @param informationId 
	 * @return
	 */
	public List<Map<String, Object>> getNovaPlanTaskList(MemberInfo memberInfo, Integer memberId, String openType, String informationId) {
		List<Map<String, Object>> novaPlanTaskList = new ArrayList<>();//协议任务
		Map<String, Object> dataMap = new HashMap<>();
		Date currentDate = new Date();
		Date novaProjectEndDate = memberInfo.getNovaProjectEndDate();
		String title = "";
		String content = "";
		String statusText = "";
		if("1".equals(openType)){
			title = "新星计划协议";
			content = "协议到期日："+DateUtil.getFormatDate(memberInfo.getNovaProjectEndDate(), "yyyy-MM-dd");
			long day = (novaProjectEndDate.getTime()-currentDate.getTime())/1000/60/60/24;
			statusText = "剩"+day+"天";
		}else if("3".equals(openType)){
			title = "醒购店长协议";
			content = "协议到期日："+DateUtil.getFormatDate(memberInfo.getNovaProjectEndDate(), "yyyy-MM-dd");
		}
		
		dataMap.put("title", title);
		dataMap.put("content", content);
		dataMap.put("statusText", statusText);
		dataMap.put("status", "0");
		dataMap.put("linkUrl", commonService.getCloumnLinkUrl(informationId, "6"));
		dataMap.put("taskType", "1");
		novaPlanTaskList.add(dataMap);
		return novaPlanTaskList;
	}

	/**
	 * 获取用户新星计划开通类型
	 * @param memberInfo
	 * @param memberId
	 * @return
	 */
	public String getNovaPlanOpenType(MemberInfo memberInfo, Integer memberId) {
		String openType = "0";//0未开通 1 已开通 2已过期
		Date currentDate = new Date();
		if(memberId != null){
			MemberNovaRecord memberNovaRecord = memberNovaRecordService.getCurrentContractSigning(memberId,"1");
			if(memberNovaRecord != null){
				Date novaProjectBeginDate = memberNovaRecord.getAgreementBeginDate();
				Date novaProjectEndDate = memberNovaRecord.getAgreementEndDate();
				if(novaProjectBeginDate == null || novaProjectEndDate == null){
					openType = "0";
				}else{
					if(novaProjectBeginDate.before(currentDate) && novaProjectEndDate.after(currentDate)){
						openType = "1";
					}else{
						openType = "2";
					}
				}
			}else{
				if(memberInfo == null){
					memberInfo = memberInfoService.selectByPrimaryKey(memberId);
				}
				if(memberInfo.getNovaProjectBeginDate() != null){
					openType = "2";
				}
			}
		}
		return openType;
	}
	
	
	public Map<String, Object> getRenewalTaskPro(MemberInfo memberInfo, Integer memberId,String reqType) {
		List<Map<String, Object>> dataList = new ArrayList<>();
		String openType = getNovaPlanOpenType(memberInfo, memberId);
		boolean isSuccess = false;
		Integer memberSubCount = 0;
		if(!"0".equals(openType)){
			Map<String, Object> dataMap = new HashMap<>();
			memberSubCount = memberInfoService.getMemberSubCount(memberInfo,memberId,openType);//签约期已邀请人数
			String statusText = "";
			if(memberSubCount >= NOVA_TASK_COUNT){
				statusText = "完成";
				isSuccess = true;
			}else{
				statusText = "去完成";
			}
			dataMap.put("title", "邀请好友");
			dataMap.put("content", "邀请任务：拉新"+NOVA_TASK_COUNT+"人；已完成"+memberSubCount+"人");
			dataMap.put("statusText", statusText);
			dataMap.put("status", statusText.equals("完成") ? 1 : 0);
			dataMap.put("taskType", "1");
			dataList.add(dataMap);
			
			
			if(!isSuccess || !"1".equals(reqType)){
				dataMap = new HashMap<>();
				int membmerCumulativeSignInCount = memberSignInSettingService.getMemberCumulativeSignInCount(memberInfo,memberId,openType);//签约期已累计签到天数
				if(membmerCumulativeSignInCount >= SIGN_IN_COUNT){
					statusText = "完成";
					isSuccess = true;
				}else{
					statusText = "去完成";
				}
				dataMap.put("title", "签到");
				dataMap.put("content", "签到任务：累计"+SIGN_IN_COUNT+"天；已签到"+membmerCumulativeSignInCount+"天");
				dataMap.put("statusText", statusText);
				dataMap.put("status", statusText.equals("完成") ? 1 : 0);
				dataMap.put("taskType", "2");
				dataList.add(dataMap);
				
			}
			if(!isSuccess || !"1".equals(reqType)){
				dataMap = new HashMap<>();
				int memberReleaseDynamicsCount = memberDynamicService.getMemberReleaseDynamicsCount(memberInfo,memberId,openType);//签约期间的发布动态数
				if(memberReleaseDynamicsCount >= DYNAMICS_TASK_COUNT){
					statusText = "完成";
					isSuccess = true;
				}else{
					statusText = "去完成";
				}
				dataMap.put("title", "发布动态");
				dataMap.put("content", "动态任务：参与"+DYNAMICS_TASK_COUNT+"天；已参与"+memberReleaseDynamicsCount+"天");
				dataMap.put("statusText", statusText);
				dataMap.put("status", statusText.equals("完成") ? 1 : 0);
				dataMap.put("taskType", "3");
				dataList.add(dataMap);
				
			}
			if(!isSuccess || !"1".equals(reqType)){
				dataMap = new HashMap<>();
				int memberSubOrderCount = combineOrderService.getMemberSubOrderCount(memberInfo,memberId,openType);//签约期间会员的下级订单数量
				if(memberSubOrderCount >= MEMBER_SUB_ORDER_COUNT){
					statusText = "完成";
					isSuccess = true;
				}else{
					statusText = "去完成";
				}
				dataMap.put("title", "下级好友下单");
				dataMap.put("content", "订单任务：付款"+MEMBER_SUB_ORDER_COUNT+"单；已完成"+memberSubOrderCount+"单");
				dataMap.put("statusText", statusText);
				dataMap.put("status", statusText.equals("完成") ? 1 : 0);
				dataMap.put("taskType", "4");
				dataList.add(dataMap);
			}
		}
		Map<String, Object> map = new HashMap<>();
		map.put("dataList", dataList);
		map.put("memberSubCount", memberSubCount);
		map.put("isSuccess", isSuccess);
		return map;
	}
	public Map<String, Object> getMemberRenewalTaskProgress(JSONObject reqPRM, JSONObject reqDataJson, MemberInfo memberInfo) {
		Map<String, Object> taskProMap = getRenewalTaskPro(memberInfo, memberInfo.getId(), "2");
		List<Map<String, Object>> dataList = (List<Map<String, Object>>) taskProMap.get("dataList");
		Map<String, Object> map = new HashMap<>();
		map.put("renewalTaskList", dataList);
		return map;
	}
	public Map<String, Object> addMemberOpenNovaPlan(MemberInfo memberInfo, HttpServletRequest request) {
		Integer memberId = memberInfo.getId();
		String openType = getNovaPlanOpenType(memberInfo, memberId);
		boolean isSuccess = true;
		String msg = "";
		Date currentDate = new Date();
		Map<String, Object> map = new HashMap<>();
		Integer informationtId = null;
		if("1".equals(openType)){
			Date begin = memberInfo.getNovaProjectBeginDate();
			Date end = memberInfo.getNovaProjectEndDate();
			if(begin.after(currentDate)){
				isSuccess = false;
				msg = "您已开通新星计划";
				map.put("isSuccess", isSuccess);
				map.put("msg", msg);
				return map;
			}
		}
		if(!"1".equals(memberInfo.getmVerfiyFlag()) || StringUtil.isBlank(memberInfo.getMobile())){
			isSuccess = false;
			msg = "未绑定手机号码，不能开通新星计划";
			map.put("isSuccess", isSuccess);
			map.put("msg", msg);
			return map;
		}
		List<Information> informations = informationService.getNovaPlanAgreement(Const.NOVA_PLAN_AGREEMENT_CATALOG_ID);
		if(CollectionUtils.isNotEmpty(informations)){
			informationtId = informations.get(0).getId();
		}else{
			isSuccess = false;
			msg = "协议未生效";
			map.put("isSuccess", isSuccess);
			map.put("msg", msg);
			return map;
		}
		if(!"0".equals(openType)){
			//已开通，判断是否有续签资格
			Map<String, Object> taskProMap = getRenewalTaskPro(null, memberId, "1");
			boolean b = (boolean) taskProMap.get("isSuccess");
			if(!b){
				isSuccess = false;
				msg = "您当前暂无续签资格，请先完成任一续签任务~";
				map.put("isSuccess", isSuccess);
				map.put("msg", msg);
				return map;
			}
		}
		if(isSuccess){
			MemberNovaRecord memberNovaRecord = new MemberNovaRecord();
			Date beginDate = null;
			Date endDate = null;
			if("1".equals(openType)){
				beginDate = memberInfo.getNovaProjectEndDate();
				endDate = DateUtil.addDay(beginDate, 90);
			}else{
				beginDate = currentDate;
				endDate = DateUtil.addDay(beginDate, 90);
			}
			if("0".equals(openType)){
				msg = "开通成功~";
			}else{
				msg = "您已成功续签~";
			}
			memberNovaRecord.setMemberId(memberId);
			memberNovaRecord.setInformationtId(informationtId);
			memberNovaRecord.setContractTime(currentDate);
			memberNovaRecord.setAgreementBeginDate(beginDate);
			memberNovaRecord.setAgreementEndDate(endDate);
			memberNovaRecord.setCreateBy(memberId);
			memberNovaRecord.setCreateDate(currentDate);
			memberNovaRecord.setDelFlag("0");
			memberNovaRecordService.insertSelective(memberNovaRecord);
			
			memberInfo.setNovaProjectBeginDate(beginDate);
			memberInfo.setNovaProjectEndDate(endDate);
			memberInfo.setUpdateDate(currentDate);
			if(StringUtil.isBlank(memberInfo.getInvitationCode())){
				memberInfo.setInvitationCode((new Random().nextInt(max)%(max-min+1) + min)+"");
			}
			memberInfoService.updateByPrimaryKeySelective(memberInfo);
			request.getSession().setAttribute(BaseDefine.MEMBER_INFO, memberInfo);
		}
		map.put("isSuccess", isSuccess);
		map.put("msg", msg);
		return map;
	}
	
	public Map<String, Object> getMemberRenewalProgressLog(JSONObject reqPRM, JSONObject reqDataJson) {
		List<Map<String, Object>> dataList = new ArrayList<>();
		Integer memberId = reqDataJson.getInt("memberId");
		Integer currentPage = reqDataJson.getInt("currentPage");
		Integer pageSize = reqDataJson.getInt("pageSize");
		String openType = getNewNovaPlanOpenType(null, memberId);
		Map<String, Object> paramsMap = new HashMap<>();
		paramsMap.put("memberId", memberId);
		paramsMap.put("openType", openType);
		paramsMap.put("currentPage", currentPage*pageSize);
		paramsMap.put("pageSize", pageSize);
		List<MemberNovaRecordCustom> memberNovaRecords = memberNovaRecordService.getMemberRenewalProgressLog(paramsMap);
		if(CollectionUtils.isNotEmpty(memberNovaRecords)){
			for (MemberNovaRecordCustom memberNovaRecordCustom : memberNovaRecords) {
				Map<String, Object> dataMap = new HashMap<>();
				Integer catalogId = memberNovaRecordCustom.getCatalogId();
				String title = "";
				if(catalogId == Const.NOVA_PLAN_AGREEMENT_CATALOG_ID){
					title = "新星计划";
				}else if(catalogId == Const.NOVA_PLAN_SHOPWNER_AGREEMENT_CATALOG_ID){
					title = "醒购店长";
				}
				dataMap.put("title", title);
				dataMap.put("contractDateStr", DateUtil.getFormatDate(memberNovaRecordCustom.getContractTime(), "yyyy-MM-dd HH:mm:ss"));
				dataMap.put("planEffectiveDate", DateUtil.getFormatDate(memberNovaRecordCustom.getAgreementBeginDate(), "yyyy-MM-dd"+"至"+DateUtil.getFormatDate(memberNovaRecordCustom.getAgreementEndDate(), "yyyy-MM-dd")));
				dataMap.put("informationtIdName", "《"+memberNovaRecordCustom.getTitle()+"》");
				dataMap.put("informationtUrl", commonService.getCloumnLinkUrl(memberNovaRecordCustom.getInformationtId().toString(), "5"));
				dataList.add(dataMap);
			}
		}
		Map<String, Object> map = new HashMap<>();
		map.put("dataList", dataList);
		return map;
	}
	
	public Map<String, Object> getNovaPlanHelpCenter(Integer memberId) {
		boolean isNoviceIntegral = true;
		String openType = getNewNovaPlanOpenType(null, memberId);
		String type = "";
		Integer integralType = Integer.parseInt(Const.INTEGRAL_TYPE_SEE_NOVA_STRATEGY);
		if("1".equals(openType)){
			type = "0";
		}else if("3".equals(openType)){
			type = "1";
		}
		MemberAccount memberAccount = memberAccountService.findAccountByMemberId(memberId);
		List<Map<String, Object>> dataList = novaStrategyService.getNovaPlanHelpCenter(type);
		if(CollectionUtils.isNotEmpty(dataList)){
			isNoviceIntegral = integralDtlService.getIsNoviceIntegral(memberAccount.getId(),integralType);
		}
		Map<String, Object> map = new HashMap<>();
		map.put("dataList", dataList);
		map.put("isNoviceIntegral", isNoviceIntegral);
		map.put("type", type);
		return map;
	}
	public void addReadStrategyIntegral(MemberInfo memberInfo) {
		Integer integralType = Integer.parseInt(Const.INTEGRAL_TYPE_SEE_NOVA_STRATEGY);
		MemberAccount memberAccount = memberAccountService.findAccountByMemberId(memberInfo.getId());
		boolean isNoviceIntegral = integralDtlService.getIsNoviceIntegral(memberAccount.getId(),integralType);
		if(isNoviceIntegral){
			throw new ArgException("您已领取该任务奖励，请勿重复领取");
		}
		Integer integral = 150;
		memberAccountService.addGiftIntegral(integral, memberInfo, memberInfo.getId(), Const.INTEGRAL_TYPE_SEE_NOVA_STRATEGY);
		
	}
	
	public Map<String, Object> getMemberAttentions(JSONObject reqPRM, JSONObject reqDataJson, Integer memberId) {
		String type = reqDataJson.getString("type");//1关注 2粉丝 3 推荐好友 4他的关注 5他的粉丝
		Integer currentLoginMemberId = memberId;
		if("4".equals(type) || "5".equals(type)){
			if(!JsonUtils.isEmpty(reqDataJson, "friendMemberId")){
				memberId = reqDataJson.getInt("friendMemberId");
			}else{
				throw new ArgException("系统异常");
			}
		}
		MemberInfo memberInfo = memberInfoService.selectByPrimaryKey(memberId);
		Integer currentPage = reqDataJson.getInt("currentPage");
		Integer pageSize = reqDataJson.getInt("pageSize");
		List<Map<String, Object>> dataList = new ArrayList<>();
		Integer invitationMemberId = memberInfo.getInvitationMemberId();
		Map<String, Object> paramsMap = new HashMap<>();
		if("1".equals(type) || "4".equals(type)){
			paramsMap.put("memberId", memberId);
			paramsMap.put("invitationMemberId", invitationMemberId);
			paramsMap.put("currentLoginMemberId", currentLoginMemberId);
			paramsMap.put("type", type);
			paramsMap.put("currentPage", currentPage*pageSize);
			paramsMap.put("pageSize", pageSize);
			List<MemberInfoCustom> memberInfoCustoms = memberInfoService.getMemberFollowUser(paramsMap);
			if(CollectionUtils.isNotEmpty(memberInfoCustoms)){
				for (MemberInfoCustom member : memberInfoCustoms) {
					Map<String, Object> dataMap = new HashMap<>();
					//STORY #1886
					String shieldingDynamics = member.getShieldingDynamics();
					String nick = member.getNick();
					String pic = member.getPic();
					String sexType = member.getSexType();
					Integer friendFollowNum = member.getFollowNum();//我关注的数量
					Integer attentionMeNum =  member.getAttentionMeNum();//关注我的数量
					Integer frientMenberId = member.getId();
					String regArea = member.getRegArea() == null ? "未知城市" : member.getRegArea();
					Integer parentInvitationMemberId = member.getInvitationMemberId();
					String followStatus = "1";//1已关注 2互相关注
					String friendRelationship = "";
					if(invitationMemberId != null && frientMenberId.equals(invitationMemberId)){
						friendRelationship = "推荐人";
					}else if(parentInvitationMemberId != null && memberId.equals(parentInvitationMemberId)){
						friendRelationship = "推荐好友";
					}
					if("4".equals(type)){
						if(!currentLoginMemberId.equals(frientMenberId)){
							if(friendFollowNum > 0 && attentionMeNum > 0){
								followStatus = "2";
							}else if(friendFollowNum > 0 && attentionMeNum <= 0){
								followStatus = "1";
							}else{
								followStatus = "3";
							}
						}else{
							followStatus = "4";
						}
					}else{
						if(friendFollowNum > 0){
							followStatus = "2";
						}
					}
					nick = StringUtil.getNickRule(nick, "");
					dataMap.put("nick", nick);
					dataMap.put("frientMenberId", frientMenberId);
					dataMap.put("pic", StringUtil.getPic(pic, ""));
					dataMap.put("sexType", sexType);//1男 2女
					dataMap.put("followStatus", followStatus);
					dataMap.put("friendRelationship", friendRelationship);
					dataMap.put("regArea", regArea);
					dataMap.put("shieldingDynamics", shieldingDynamics);
					dataList.add(dataMap);
				}
			}
		}else if("2".equals(type) || "5".equals(type)){
			paramsMap.put("memberId", memberId);
			paramsMap.put("invitationMemberId", invitationMemberId);
			paramsMap.put("currentLoginMemberId", currentLoginMemberId);
			paramsMap.put("type", type);
			paramsMap.put("currentPage", currentPage*pageSize);
			paramsMap.put("pageSize", pageSize);
			List<MemberInfoCustom> memberInfoCustoms = memberInfoService.getMemberFansUser(paramsMap);
			if(CollectionUtils.isNotEmpty(memberInfoCustoms)){
				for (MemberInfoCustom member : memberInfoCustoms) {
					Map<String, Object> dataMap = new HashMap<>();
					String nick = member.getNick();
					String pic = member.getPic();
					String sexType = member.getSexType();
					Integer friendFollowNum = member.getFollowNum();//我关注的数量
					Integer attentionMeNum =  member.getAttentionMeNum();//关注我的数量
					Integer frientMenberId = member.getId();
					String regArea = member.getRegArea() == null ? "未知城市" : member.getRegArea();
					Integer parentInvitationMemberId = member.getInvitationMemberId();
					String followStatus = "3";//1已关注 2互相关注 3 未关注
					String friendRelationship = "";
					if(invitationMemberId != null && invitationMemberId.equals(frientMenberId)){
						friendRelationship = "推荐人";
					}else if(parentInvitationMemberId != null && memberId.equals(parentInvitationMemberId)){
						friendRelationship = "推荐好友";
					}
					if("5".equals(type)){
						if(!currentLoginMemberId.equals(frientMenberId)){
							if(friendFollowNum > 0 && attentionMeNum > 0){
								followStatus = "2";
							}else if(friendFollowNum > 0 && attentionMeNum <= 0){
								followStatus = "1";
							}else{
								followStatus = "3";
							}
						}else{
							followStatus = "4";
						}
					}else{
						if(friendFollowNum > 0){
							followStatus = "2";
						}
					}
					nick = StringUtil.getNickRule(nick, "");
					dataMap.put("nick", nick);
					dataMap.put("frientMenberId", frientMenberId);
					dataMap.put("pic", StringUtil.getPic(pic, ""));
					dataMap.put("sexType", sexType);//1男 2女
					dataMap.put("followStatus", followStatus);
					dataMap.put("friendRelationship", friendRelationship);
					dataMap.put("regArea", regArea);
					dataList.add(dataMap);
				}
			}
		}else if("3".equals(type)){
			paramsMap.put("memberId", memberId);
			paramsMap.put("currentPage", currentPage*pageSize);
			paramsMap.put("pageSize", pageSize);
			List<MemberInfoCustom> memberInfoCustoms = memberInfoService.getMemberRecommendUser(paramsMap);
			if(CollectionUtils.isNotEmpty(memberInfoCustoms)){
				for (MemberInfoCustom member : memberInfoCustoms) {
					Map<String, Object> dataMap = new HashMap<>();
					String nick = member.getNick();
					String pic = member.getPic();
					String sexType = member.getSexType();
					String regArea = member.getRegArea() == null ? "未知城市" : member.getRegArea();
					Integer payOrderCount = member.getPayOrderCount();
					String payOrderCountStr = "";
					if(payOrderCount != null && payOrderCount > 0){
						payOrderCountStr = "已付"+payOrderCount+"单";
					}
					nick = StringUtil.getNickRule(nick, "");
					dataMap.put("nick", nick);
					dataMap.put("frientMenberId", member.getId());
					dataMap.put("pic", StringUtil.getPic(pic, ""));
					dataMap.put("sexType", sexType);//1男 2女
					dataMap.put("regArea", regArea);
					dataMap.put("payOrderCountStr", payOrderCountStr);
					dataList.add(dataMap);
				}
			}
		}
		Map<String, Object> map = new HashMap<>();
		map.put("dataList", dataList);
		return map;
	}
	
	
	public Map<String, Object> addMemberDynamicForward(JSONObject reqPRM, JSONObject reqDataJson) {
		Integer memberId = reqDataJson.getInt("memberId");
		Integer dynamicId = reqDataJson.getInt("mchtShopDynamicId");
		String type = reqDataJson.getString("type");
		Integer forwardCount = 0;
		//查询该动态id是否存在
		List<MchtShopDynamic> mchtShopDynamics = mchtShopDynamicService.findAuditsStatusModels(dynamicId);
		if(CollectionUtils.isEmpty(mchtShopDynamics)){
			throw new ArgException("抱歉，该动态已被删除");
		}
		MemberDynamicExample memberDynamicExample = new MemberDynamicExample();
		memberDynamicExample.createCriteria().andMchtShopDynamicIdEqualTo(dynamicId).andMemberIdEqualTo(memberId).andDelFlagEqualTo("0");
		List<MemberDynamic> memberDynamics = memberDynamicService.selectByExample(memberDynamicExample);
		if(CollectionUtils.isNotEmpty(memberDynamics)){
			throw new ArgException("您已转发过相关动态，请勿重复操作~");
		}
		MchtShopDynamic mchtShopDynamic = mchtShopDynamics.get(0);
		if("0".equals(type)){
			mchtShopDynamic.setForwardCount(mchtShopDynamic.getForwardCount()+1);
			forwardCount = mchtShopDynamic.getForwardCount();
			mchtShopDynamicService.updateByPrimaryKeySelective(mchtShopDynamic);
			
			MemberDynamic memberDynamic = new MemberDynamic();
			memberDynamic.setMemberId(memberId);
			memberDynamic.setMchtShopDynamicId(dynamicId);
			memberDynamic.setCreateDate(new Date());
			memberDynamic.setDelFlag("0");
			memberDynamicService.insertSelective(memberDynamic);
		}else{
			Integer memberDynamicId = reqDataJson.getInt("memberDynamicId");
			memberDynamicExample = new MemberDynamicExample();
			memberDynamicExample.createCriteria().andIdEqualTo(memberDynamicId).andDelFlagEqualTo("0");
			memberDynamics = memberDynamicService.selectByExample(memberDynamicExample);
			if(CollectionUtils.isEmpty(memberDynamics)){
				throw new ArgException("该条信息已被删除");
			}
			MemberDynamic memberDynamic = memberDynamics.get(0);
			memberDynamic.setForwardCount(memberDynamic.getForwardCount()+1);
			memberDynamicService.updateByPrimaryKeySelective(memberDynamic);
			forwardCount = memberDynamic.getForwardCount();
			
			memberDynamic = new MemberDynamic();
			memberDynamic.setMemberId(memberId);
			memberDynamic.setMchtShopDynamicId(dynamicId);
			memberDynamic.setCreateDate(new Date());
			memberDynamic.setDelFlag("0");
			memberDynamicService.insertSelective(memberDynamic);
		}
		String forwardCountStr = mchtShopDynamicService.formatNumber(forwardCount);
		Map<String, Object> map = new HashMap<>();
		map.put("forwardCount", forwardCountStr);
		return map;
	}
	public Map<String, Object> addMemberDynamicFabulous(JSONObject reqPRM, JSONObject reqDataJson) {
		Integer memberId = reqDataJson.getInt("memberId");
		Integer dynamicId = reqDataJson.getInt("mchtShopDynamicId");
		String type = reqDataJson.getString("type");
		Integer fabulousCount = 0;
		//查询该动态id是否存在
		List<MchtShopDynamic> mchtShopDynamics = mchtShopDynamicService.findAuditsStatusModels(dynamicId);
		if(CollectionUtils.isEmpty(mchtShopDynamics)){
			throw new ArgException("抱歉，该动态已被删除");
		}
		String returnType = "";
		Integer count = 0;
		MchtShopDynamic mchtShopDynamic = mchtShopDynamics.get(0);
		if("0".equals(type)){//店铺点赞
			returnType = memberDynamicLikeService.updateMemberDynamicFabulous(memberId,dynamicId,type);
			if(returnType.equals("1")){
				count = 1;
			}else{
				count = -1;
			}
			mchtShopDynamic.setLikeCount(mchtShopDynamic.getLikeCount()+count);
			mchtShopDynamicService.updateByPrimaryKeySelective(mchtShopDynamic);
			fabulousCount = mchtShopDynamic.getLikeCount();
		}else{
			Integer memberDynamicId = reqDataJson.getInt("memberDynamicId");
			returnType = memberDynamicLikeService.updateMemberDynamicFabulous(memberId,memberDynamicId,type);
			if(returnType.equals("1")){
				count = 1;
			}else{
				count = -1;
			}
			//转发店铺点赞
			MemberDynamicExample memberDynamicExample = new MemberDynamicExample();
			memberDynamicExample.createCriteria().andIdEqualTo(memberDynamicId).andDelFlagEqualTo("0");
			List<MemberDynamic> memberDynamics = memberDynamicService.selectByExample(memberDynamicExample);
			if(CollectionUtils.isEmpty(memberDynamics)){
				throw new ArgException("该条信息已被删除");
			}
			MemberDynamic memberDynamic = memberDynamics.get(0);
			memberDynamic.setLikeCount(memberDynamic.getLikeCount()+count);
			memberDynamicService.updateByPrimaryKeySelective(memberDynamic);
			fabulousCount = memberDynamic.getLikeCount();
		}
		String fabulousCountStr = mchtShopDynamicService.formatNumber(fabulousCount);
		Map<String, Object> map = new HashMap<>();
		map.put("fabulousCount", fabulousCountStr);
		map.put("tallyModel", returnType);
		return map;
	}
	public Map<String, Object> getXgShopwnerEquityDetail(MemberInfo memberInfo, JSONObject reqPRM,JSONObject reqDataJson) {
		Map<String, Object> map = new HashMap<>();
		Integer decorateInfoId = null;
		String status = "0";
		String informationId = "";
		Map<String, Object> decorateInfoMap = null;
		Integer memberId = null;
		if(!JsonUtils.isEmpty(reqDataJson, "infoId")){
			status = "1";//在这边再次赋值，预览兼容使用
			decorateInfoId = reqDataJson.getInt("infoId");//1预览
		}else{
			if(memberInfo != null){
				memberId = memberInfo.getId();
				Integer invitationMemberId = memberInfo.getInvitationMemberId();//是否绑定上级
				if(invitationMemberId == null){
					Shopowner shopowner = shopownerService.findModelByMemberId(memberId);//是否是店长
					if(shopowner == null){
						Salesman salesman = salesmanService.findModelByMemberId(memberId);//是否是业务员
						if(salesman == null){
							status = "1";
							Map<String,Object> adPageMap = customAdPageService.getCustomAdPage(Const.PAGE_TYPE_21, null);
							if(!StringUtil.isBlank(adPageMap.get("decorateInfoId").toString())){
								decorateInfoId = Integer.parseInt(adPageMap.get("decorateInfoId").toString());
							}
						}
					}
				}
			}
		}
		if(decorateInfoId != null){
			decorateInfoMap = decorateInfoService.getHomePageDecorateInfo(decorateInfoId, reqPRM, memberId);
			//协议
			Integer catalogId = Const.NOVA_PLAN_SHOPWNER_AGREEMENT_CATALOG_ID;
			List<Information> informations = informationService.getNovaPlanAgreement(catalogId);
			if(CollectionUtils.isNotEmpty(informations)){
				informationId = informations.get(0).getId().toString();
			}
		}
		map.put("decorateInfoMap", decorateInfoMap);
		map.put("informationId", informationId);
		map.put("title", "醒购店长权益详情");
		map.put("status", status);
		return map;
	}
	
	public String getNewNovaPlanOpenType(MemberInfo memberInfo, Integer memberId) {
		String openType = "0";//0未开通 1 已开通 2已过期
		Date currentDate = new Date();
		if(memberId != null){
			Shopowner shopowner = shopownerService.findModelByMemberId(memberId);
			if(shopowner != null){
				openType = "3";
			}else{
				MemberNovaRecord memberNovaRecord = memberNovaRecordService.getCurrentContractSigning(memberId,"1");
				if(memberNovaRecord != null){
					Date novaProjectBeginDate = memberNovaRecord.getAgreementBeginDate();
					Date novaProjectEndDate = memberNovaRecord.getAgreementEndDate();
					if(novaProjectBeginDate == null || novaProjectEndDate == null){
						openType = "0";
					}else{
						if(novaProjectBeginDate.before(currentDate) && novaProjectEndDate.after(currentDate)){
							openType = "1";
						}else{
							openType = "2";
						}
					}
				}else{
					if(memberInfo == null){
						memberInfo = memberInfoService.selectByPrimaryKey(memberId);
					}
					if(memberInfo.getNovaProjectBeginDate() != null){
						openType = "2";
					}
				}
			}
		}
		return openType;
	}
}
