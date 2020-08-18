package com.jf.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jf.common.base.BaseService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.common.base.ArgException;
import com.jf.common.constant.Const;
import com.jf.common.utils.DateUtil;
import com.jf.common.utils.StringUtil;
import com.jf.dao.MemberSignInSettingCustomMapper;
import com.jf.dao.MemberSignInSettingMapper;
import com.jf.entity.MemberAccount;
import com.jf.entity.MemberInfo;
import com.jf.entity.MemberMonthSignIn;
import com.jf.entity.MemberMonthSignInExample;
import com.jf.entity.MemberNovaRecord;
import com.jf.entity.MemberSignInSetting;
import com.jf.entity.MemberSignInSettingCustom;
import com.jf.entity.MemberSignInSettingExample;
import com.jf.entity.SignInSetting;

import net.sf.json.JSONObject;

@Service
@Transactional
public class MemberSignInSettingService extends BaseService<MemberSignInSetting, MemberSignInSettingExample> {
	@Autowired
	private MemberSignInSettingMapper memberSignInSettingMapper;
	@Autowired
	private MemberSignInSettingCustomMapper memberSignInSettingCustomMapper;
	@Autowired
	private SignInSettingService signInSettingService;
	@Autowired 
	private BlackListService blackListService;
	@Autowired
	private MemberSignInService memberSignInService;
	@Autowired 
	private MemberAccountService memberAccountService;
	@Autowired
	private CouponService couponService;
	@Autowired
	private IntegralDtlService integralDtlService;
	@Autowired
	private MemberMonthSignInService memberMonthSignInService;
	@Autowired
	private MemberInfoService memberInfoService;
	@Autowired
	private MemberNovaRecordService memberNovaRecordService;
	@Autowired
	public void setMemberSignInSettingMapper(MemberSignInSettingMapper memberSignInSettingMapper) {
		this.setDao(memberSignInSettingMapper);
		this.memberSignInSettingMapper = memberSignInSettingMapper;
	}
	/**
	 * 获取用户当月的签到记录
	 * @param memberId 
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public List<MemberSignInSetting> getMonthMemberSignInRecord(Integer memberId, Date beginDate, Date endDate) {
		MemberSignInSettingExample memberSignInSettingExample = new MemberSignInSettingExample();
		memberSignInSettingExample.createCriteria().andMemberIdEqualTo(memberId).andCreateDateGreaterThan(beginDate) 
		.andCreateDateLessThanOrEqualTo(endDate).andDelFlagEqualTo("0");
		return memberSignInSettingMapper.selectByExample(memberSignInSettingExample);
	}
	
	public Map<String, Object> addMemberNewSignIn(Integer memberId, JSONObject reqDataJson) {
		Map<String,Object> blackMap = blackListService.getIsBlack(memberId,"1");
		if((boolean) blackMap.get("isBlack")){
			throw new ArgException(blackMap.get("blackReason").toString());
		}
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> couponList = new ArrayList<Map<String, Object>>();
		Date currentDate = new Date();
		Integer signInSettingId = reqDataJson.getInt("signInSettingId");
		String signInClient = reqDataJson.getString("signInClient");
		SignInSetting signInSetting = signInSettingService.selectByPrimaryKey(signInSettingId);
		if(signInSetting == null){
			throw new ArgException("签到失败，请联系客服");
		}
		Date dayBeginDate = DateUtil.getDateAfterAndBeginTime(currentDate, 0);
		Date dayEndDate = DateUtil.getDateAfterAndEndTime(currentDate, 0);
		String currentDateStr = DateUtil.getFormatDate(currentDate, "yyyyMM");
		MemberAccount account = memberAccountService.findAccountByMemberId(memberId);
		Date signInDate = signInSetting.getSignInDate();//签到时间
		Integer signInManageId = signInSetting.getSignInManageId();
		String signInDateStr = DateUtil.getFormatDate(signInDate, "yyyyMMdd");
		String rewardType = signInSetting.getRewardType();//奖励类型1.现金红包2.积分3.优惠券
		String rewardGift = signInSetting.getRewardGift();
		String signInType = "1";//签到类型 1.正常签到 2.补签
		BigDecimal memberSignInAmount = new BigDecimal("0");
		Integer integral = 0;
		if(!DateUtil.getFormatDate(signInDate, "yyyyMM").equals(DateUtil.getFormatDate(new Date(), "yyyyMM"))){
			throw new ArgException("请签到本月的日期");
		}
		//判断用户是否签到过
		MemberSignInSettingExample memberSignInSettingExample = new MemberSignInSettingExample();
		memberSignInSettingExample.createCriteria().andMemberIdEqualTo(memberId).andSignInSettingIdEqualTo(signInSettingId).andDelFlagEqualTo("0");
		Integer memberSignInSettingCount = countByExample(memberSignInSettingExample);
		if(memberSignInSettingCount > 0){
			throw new ArgException("您已签到，请勿重复签到");
		}
		if(signInDate.compareTo(dayBeginDate) < 0 || signInDate.compareTo(dayEndDate) >= 0){
			signInType = "2";
			//补签限制3次
			MemberSignInSettingExample signEx = new MemberSignInSettingExample();
			signEx.createCriteria().andMemberIdEqualTo(memberId).andDelFlagEqualTo("0").andSignInTypeEqualTo("2").andSignInDateStrLike(currentDateStr+"%");
			Integer signExCount = countByExample(signEx);
			if(signExCount > 3){
				throw new ArgException("每人每月限制补签3次！");
			}
			//补签要扣除补签卡
			Integer supplementaryCard = account.getSupplementaryCard() - 1;
			if(supplementaryCard >= 0){
				account = memberAccountService.updateModelByEx(account, null,null,supplementaryCard, currentDate);
			}else{
				throw new ArgException("补签卡不足");
			}
		}
		//TODO 并发处理(目前数据库控制，签到时间字符串和主键id和memberId为唯一索引)
		MemberSignInSetting memberSignInSetting = addModel(signInSettingId,memberId,currentDate,signInType,signInDateStr);
		if("1".equals(rewardType)){
			//现金红包
			Map<String, Object> redPackMap = memberSignInService.addRedPack(memberId,account,rewardGift,currentDate,signInClient);
			memberSignInAmount = new BigDecimal(redPackMap.get("memberSignInAmount").toString());
		}else if("2".equals(rewardType) && !StringUtil.isBlank(rewardGift)){
			//积分
			integral = Integer.parseInt(rewardGift);
			Integer balance = account.getIntegral() + integral;
			integralDtlService.addIntegralDtl(account.getId(), Const.INTEGRAL_TALLY_MODE_INCOME, Const.INTEGRAL_TYPE_SIGN_IN_GIFT, integral, balance, memberSignInSetting.getId(), memberId, "6");
			memberAccountService.updateModelByEx(account, null,balance,null, currentDate);
		}else if("3".equals(rewardType) && !StringUtil.isBlank(rewardGift)){
			couponList = couponService.addSignInCoupon(memberId,rewardGift,currentDate);
		}else{
			throw new ArgException("异常数据");
		}
		MemberMonthSignInExample memberMonthSignInExample = new MemberMonthSignInExample();
		memberMonthSignInExample.createCriteria().andMemberIdEqualTo(memberId).andSignInManageIdEqualTo(signInManageId).andDelFlagEqualTo("0");
		List<MemberMonthSignIn> memberMonthSignIns = memberMonthSignInService.selectByExample(memberMonthSignInExample);
		if(CollectionUtils.isNotEmpty(memberMonthSignIns)){
			MemberMonthSignIn monthSignIn = memberMonthSignIns.get(0);
			monthSignIn.setSignInCount(monthSignIn.getSignInCount()+1);
			monthSignIn.setUpdateDate(currentDate);
			monthSignIn.setUpdateBy(memberId);
			memberMonthSignInService.updateByExampleSelective(monthSignIn, memberMonthSignInExample);
		}else{
			MemberMonthSignIn monthSignIn = new MemberMonthSignIn();
			monthSignIn.setSignInManageId(signInManageId);
			monthSignIn.setSignInCount(1);
			monthSignIn.setMemberId(memberId);
			monthSignIn.setCreateBy(memberId);
			monthSignIn.setCreateDate(currentDate);
			monthSignIn.setDelFlag("0");
			memberMonthSignInService.insertSelective(monthSignIn);
		}
		map.put("rewardType", rewardType);
		map.put("memberSignInAmount", memberSignInAmount);
		map.put("integral", integral);
		map.put("couponList", couponList);
		map.put("currentDate", new Date());
		return map;
	}
	public MemberSignInSetting addModel(Integer signInSettingId, Integer memberId, Date currentDate, String signInType, String signInDateStr) {
		MemberSignInSetting memberSignInSetting = new MemberSignInSetting();
		memberSignInSetting.setSignInSettingId(signInSettingId);
		memberSignInSetting.setMemberId(memberId);
		memberSignInSetting.setSignInTime(currentDate);
		memberSignInSetting.setSignInDateStr(signInDateStr);
		memberSignInSetting.setSignInType(signInType);
		memberSignInSetting.setCreateBy(memberId);
		memberSignInSetting.setCreateDate(currentDate);
		memberSignInSetting.setDelFlag("0");
		memberSignInSettingMapper.insertSelective(memberSignInSetting);
		return memberSignInSetting;
	}
	
	public boolean getMemberIsSign(Integer memberId) {
		Date currentDate = new Date();
		Date beginDate = DateUtil.getDateAfterAndBeginTime(currentDate, 0);
		Date endDate = DateUtil.getDateAfterAndEndTime(currentDate, 0);
		MemberSignInSettingExample memberSignInSettingExample = new MemberSignInSettingExample();
		memberSignInSettingExample.createCriteria().andMemberIdEqualTo(memberId).andCreateDateGreaterThanOrEqualTo(beginDate).andCreateDateLessThanOrEqualTo(endDate).andDelFlagEqualTo("0");
		Integer memberSignInSettingCount = countByExample(memberSignInSettingExample);
		return memberSignInSettingCount > 0 ? true : false;
	}
	
	/**
	 * 获取用户签约期间签到天数
	 * @param memberId 
	 * @param openType 
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public Integer getMemberCumulativeSignInCount(MemberInfo memberInfo, Integer memberId, String openType) {
		Integer memberSignInCount = 0;
		Date currentDate = new Date();
		if(memberInfo == null){
			memberInfo = memberInfoService.selectByPrimaryKey(memberId);
		}
		memberId = memberInfo.getId();
		if(memberId != null){
			MemberNovaRecord memberNovaRecord = memberNovaRecordService.getCurrentContractSigning(memberInfo.getId(),openType);
			if(memberNovaRecord != null){
				Date novaProjectBeginDate = memberNovaRecord.getAgreementBeginDate();
				Date novaProjectEndDate = memberNovaRecord.getAgreementEndDate();
				MemberSignInSettingExample memberSignInSettingExample = new MemberSignInSettingExample();
				memberSignInSettingExample.createCriteria().andMemberIdEqualTo(memberId).andDelFlagEqualTo("0")
				.andSignInTimeGreaterThanOrEqualTo(novaProjectBeginDate).andSignInTimeLessThanOrEqualTo(currentDate);
				memberSignInCount = countByExample(memberSignInSettingExample);
			}
		}
		return memberSignInCount;
	}
	/**
	 * 新版本签到轮播
	 */
	public List<String> getNewSignInBroadcastContent() {
		List<String> infos = new ArrayList<>();
		List<MemberSignInSettingCustom> customs = memberSignInSettingCustomMapper.getNewSignInBroadcastContent();
		if(CollectionUtils.isNotEmpty(customs)){
			for (MemberSignInSettingCustom custom : customs) {
				String rewardType = custom.getRewardType();
				String rewardGift = custom.getRewardGift();
				String nick = custom.getNick();
				String mobile = custom.getMobile();
				String info = "";
				String rewardName = "";
				if(!StringUtil.isBlank(nick)){
					nick = StringUtil.getNickStar(nick,null);
				}else if(!StringUtil.isBlank(mobile)){
					nick = StringUtil.getNickStar(null,mobile);
				}else{
					continue;
				}
				if("1".equals(rewardType)){
					rewardName = rewardGift + "元现金红包";
				}else if("2".equals(rewardType)){
					rewardName = rewardGift + "积分";
				}else if("3".equals(rewardType)){
					rewardName = rewardGift + "元优惠券";
				}else if("4".equals(rewardType)){
					rewardName = rewardGift + "天大礼包";
				}else{
					continue;
				}
				info = nick + "领取" + rewardName;
				infos.add(info);
			}
		}
		return infos;
	}
}
