package com.jf.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;

import com.google.common.collect.Lists;
import com.jf.common.base.BaseService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.common.base.ArgException;
import com.jf.common.constant.Const;
import com.jf.common.utils.CommonUtil;
import com.jf.common.utils.DataDicUtil;
import com.jf.common.utils.DateUtil;
import com.jf.common.utils.StringUtil;
import com.jf.dao.CashTransferMapper;
import com.jf.dao.MemberSignInCustomMapper;
import com.jf.dao.MemberSignInMapper;
import com.jf.entity.CashTransfer;
import com.jf.entity.CashTransferExample;
import com.jf.entity.MemberAccount;
import com.jf.entity.MemberAccountDtl;
import com.jf.entity.MemberAccountDtlExample;
import com.jf.entity.MemberInfo;
import com.jf.entity.MemberSignIn;
import com.jf.entity.MemberSignInDtl;
import com.jf.entity.MemberSignInDtlCustom;
import com.jf.entity.MemberSignInDtlExample;
import com.jf.entity.MemberSignInExample;
import com.jf.entity.SignInCnf;
import com.jf.entity.SignInCnfDtl;
import com.jf.entity.SignInCnfDtlExample;
import com.jf.entity.SignInCnfExample;
import com.jf.entity.WithdrawCnf;
import com.jf.entity.WithdrawCnfExample;
import com.jf.entity.WithdrawOrder;
import com.jf.entity.WithdrawOrderExample;
import com.jf.entity.WithdrawOrderStatusLog;

import net.sf.json.JSONObject;

/**
  * @author  chenwf: 
  * @date 创建时间：2018年5月17日 上午9:20:41 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class MemberSignInService extends BaseService<MemberSignIn, MemberSignInExample> {
	@Autowired
	private MemberSignInMapper memberSignInMapper;
	@Autowired
	private MemberSignInCustomMapper memberSignInCustomMapper;
	@Autowired
	private MemberSignInDtlService memberSignInDtlService;
	@Autowired
	private MemberAccountService memberAccountService;
	@Autowired
	private SignInCnfService signInCnfService;
	@Autowired
	private SignInCnfDtlService signInCnfDtlService;
	@Autowired
	private MemberAccountDtlService memberAccountDtlService;
	@Autowired
	private MemberInfoService memberInfoService;
	@Autowired
	private WithdrawOrderService withdrawOrderService;
	@Autowired
	private WithdrawCnfService withdrawCnfService;
	@Autowired
	private BlackListService blackListService;
	@Autowired
	private WxRedpackService wxRedpackService;
	@Autowired
	private WithdrawOrderStatusLogService withdrawOrderStatusLogService;
	@Autowired
	private MemberCouponService memberCouponService;
	@Resource
	private CustomAdPageService customAdPageService;
	@Resource
	private MemberControlService memberControlService;
	@Resource
	private MemberMobileWeixinMapService memberMobileWeixinMapService;
	@Resource
	private CashTransferMapper cashTransferMapper;
	@Resource
	private MemberSignInSettingService memberSignInSettingService;
	
	@Autowired
	public void setMemberSignInMapper(MemberSignInMapper memberSignInMapper) {
		this.setDao(memberSignInMapper);
		this.memberSignInMapper = memberSignInMapper;
	}

	public Map<String, Object> getMemberSignInPage(Integer memberId) {
		Map<String,Object> map = new HashMap<String,Object>();
		if(memberId != null){
			map = blackListService.getIsBlack(memberId,"1");
			if((boolean) map.get("isBlack")){
				return map;
			}
		}
		Date currentDate = new Date();
		MemberSignIn memberSignIn;
		Integer currentContinuity = 0;
		boolean currentSignIn = false;
		//默认7天
		int days = 7;
		int inviteLimit = 0;
		Integer accId = null;
		String nick = "";
		String headPic = "";
		BigDecimal memberBalance = new BigDecimal("0");
		Date lastSignInDate = null;
		Date begin = DateUtil.getDateAfterAndBeginTime(currentDate, 0);
		Date end = DateUtil.getDateAfterAndEndTime(currentDate, 0);
		List<Map<String,Object>> isSignInList = new ArrayList<Map<String,Object>>();
		SignInCnfExample signInCnfExample = new SignInCnfExample();
		signInCnfExample.createCriteria().andIsEffectEqualTo("1").andDelFlagEqualTo("0");
		signInCnfExample.setLimitStart(0);
		signInCnfExample.setLimitSize(1);
		signInCnfExample.setOrderByClause("id desc");
		List<SignInCnf> inCnfs = signInCnfService.selectByExample(signInCnfExample);
		if(CollectionUtils.isNotEmpty(inCnfs)){
			SignInCnf signInCnf = inCnfs.get(0);
			inviteLimit = signInCnf.getInviteLimit();
			SignInCnfDtlExample signInCnfDtlExample = new SignInCnfDtlExample();
			signInCnfDtlExample.createCriteria().andSignInCnfIdEqualTo(signInCnf.getId()).andRateTypeEqualTo("3").andDelFlagEqualTo("0");
			signInCnfDtlExample.setLimitStart(0);
			signInCnfDtlExample.setLimitSize(1);
			signInCnfDtlExample.setOrderByClause("id desc");
			List<SignInCnfDtl> inCnfDtls = signInCnfDtlService.selectByExample(signInCnfDtlExample);
			if(CollectionUtils.isNotEmpty(inCnfDtls)){
				days = inCnfDtls.get(0).getAccumulationDay();
			}
		}
		if(memberId != null){
			MemberInfo memberInfo = memberInfoService.selectByPrimaryKey(memberId);
			MemberAccount memberAccount = memberAccountService.findAccountByMemberId(memberId);
			nick = memberInfo.getNick();
			headPic = memberInfo.getPic();
			memberBalance = memberAccount.getBalance().subtract(memberAccount.getFreeze());
			accId = memberAccount.getId();
			List<MemberSignIn> memberSignIns = getMemberSignInModels(memberId);
			if(CollectionUtils.isNotEmpty(memberSignIns)){
				memberSignIn = memberSignIns.get(0);
				currentContinuity = memberSignIn.getCurrentContinuity();
				lastSignInDate = memberSignIn.getLastSignInDate();
				if(lastSignInDate.after(begin) && lastSignInDate.before(end)){
					currentSignIn = true;
				}else{
					if(lastSignInDate.before(DateUtil.addDay(begin, -1)) || lastSignInDate.after(DateUtil.addDay(end, -1))){
						currentContinuity = 0;
					}
				}
			}
		}
		for (int i = 0; i < days; i++) {
			String extraInfo = "";
			Map<String,Object> isSignInMap = new HashMap<String,Object>();
			boolean isSignIn = false;
			if(i < currentContinuity % days || (currentContinuity % days == 0 && currentContinuity != 0 && currentSignIn)){
				isSignIn = true;
			}
			if(i == 6){
				if(isSignIn){
					//查找今天创建的签到明细且有额外金额
					if(accId != null){
						MemberAccountDtlExample memberAccountDtlExample = new MemberAccountDtlExample();
						memberAccountDtlExample.createCriteria().andAccIdEqualTo(accId).
						andDelFlagEqualTo("0").andBizTypeEqualTo("6");
						memberAccountDtlExample.setLimitStart(0);
						memberAccountDtlExample.setLimitSize(1);
						memberAccountDtlExample.setOrderByClause("id desc");
						List<MemberAccountDtl> memberAccountDtls = memberAccountDtlService.selectByExample(memberAccountDtlExample);
						if(CollectionUtils.isNotEmpty(memberAccountDtls)){
							extraInfo = memberAccountDtls.get(0).getAmount().toString();
						}
					}
				}else{
					extraInfo = "额外红包";
				}
			}
			isSignInMap.put("isSignIn", isSignIn);
			isSignInMap.put("extraInfo", extraInfo);
			isSignInList.add(isSignInMap);
		}
		//类型 1 首页主题管 2 日常营销入口 3 商品主题馆 4 现金签到 5 砍价免费拿 6 购物车 7 消息 8 商品未上架
		Map<String,Object> adPageMap = customAdPageService.getCustomAdPage(Const.PAGE_TYPE_4,null);
		map.put("decorateInfoId", adPageMap.get("decorateInfoId").toString());
		map.put("isSignInList", isSignInList);
		map.put("currentSignIn", currentSignIn);
		map.put("memberBalance", memberBalance);
		map.put("inviteLimit", inviteLimit);
		map.put("nick", nick);
		map.put("headPic", StringUtil.getPic(headPic, ""));
		return map;
	}

	public Map<String, Object> addMemberSignIn(Integer memberId, JSONObject reqDataJson) {
		Map<String,Object> map = new HashMap<String,Object>();
		if(memberId != null){
			map = blackListService.getIsBlack(memberId,"1");
			if((boolean) map.get("isBlack")){
				return map;
			}
		}
		Date currentDate = new Date();
		Integer sourceMemberId = null;
		String signInClient = "";
		if(reqDataJson.containsKey("sourceMemberId") && !StringUtil.isBlank( reqDataJson.getString("sourceMemberId"))){
			sourceMemberId = reqDataJson.getInt("sourceMemberId");
		}
		if(reqDataJson.containsKey("signInClient")){
			signInClient = reqDataJson.getString("signInClient");
		}
		//判断会员是否已签到
		Date begin = DateUtil.getDateAfterAndBeginTime(currentDate, 0);
		Date end = DateUtil.getDateAfterAndEndTime(currentDate, 0);
		Integer currentContinuity = null;
		BigDecimal haveSignInAmount = new BigDecimal("0");//已签到金额
		BigDecimal memberSignInAmount = new BigDecimal("0");//签到金额
		BigDecimal sevenMemberSignInAmount = new BigDecimal("0");//连续签到金额
		BigDecimal helpAmount = new BigDecimal("0");//来源签到助力金额
		BigDecimal hundred = new BigDecimal("100");
		int helpPeopleNum = 0;
		boolean isAmountRate = false;//是否匹配金额比例
		boolean sevenSignIn = false;
		String continuityDay = "";
		//额外会员id
		MemberAccount memberAccount = memberAccountService.findAccountByMemberId(memberId);
		List<MemberSignIn> memberSignIns = getMemberSignInModels(memberId);
		MemberSignIn memberSignIn = null;
		if(CollectionUtils.isNotEmpty(memberSignIns)){
			memberSignIn = memberSignIns.get(0);
			//预防高并发，再次查询会员控制表是否已签到
			Date lastSignInDate = memberSignIn.getLastSignInDate();
			if(lastSignInDate.after(begin) && lastSignInDate.before(end)){
				throw new ArgException("今天已经签到到过了哦,请勿重复签到！");
			}else{
				memberControlService.findMemberIsBlack(memberId,"1",null);
				if(lastSignInDate.after(DateUtil.addDay(begin, -1)) && lastSignInDate.before(DateUtil.addDay(end, -1))){
					memberSignIn.setCurrentContinuity(memberSignIn.getCurrentContinuity()+1);
					if(memberSignIn.getMostContinuity() < memberSignIn.getCurrentContinuity()){
						memberSignIn.setMostContinuity(memberSignIn.getMostContinuity()+1);
					}
				}else{
					memberSignIn.setCurrentContinuity(1);
				}
			}
			memberSignIn.setLastSignInDate(currentDate);
			updateByPrimaryKeySelective(memberSignIn);
			currentContinuity = memberSignIn.getCurrentContinuity();
		}else{
			memberSignIn = new MemberSignIn();
			memberSignIn.setMemberId(memberId);
			memberSignIn.setLastSignInDate(currentDate);
			if(sourceMemberId != null){
				memberSignIn.setSourceMemberId(sourceMemberId);
			}
			memberSignIn.setCreateBy(memberId);
			memberSignIn.setCreateDate(currentDate);
			memberSignIn.setDelFlag("0");
			memberSignIn.setCurrentContinuity(1);
			memberSignIn.setMostContinuity(1);
			memberSignIn.setLastSignInDate(currentDate);
			insertSelective(memberSignIn);
			currentContinuity = memberSignIn.getCurrentContinuity();
		}
		
		//还未签到就去签到
		//1:判断用户签到规则类型
		List<String> memberRateTypeList = getMemberRateType(memberId,memberSignIns);
		//2：获取规则类型
		SignInCnfExample signInCnfExample = new SignInCnfExample();
		signInCnfExample.createCriteria().andIsEffectEqualTo("1").andDelFlagEqualTo("0");
		signInCnfExample.setLimitStart(0);
		signInCnfExample.setLimitSize(1);
		signInCnfExample.setOrderByClause("id desc");
		List<SignInCnf> inCnfs = signInCnfService.selectByExample(signInCnfExample);
		if(CollectionUtils.isNotEmpty(inCnfs)){
			Random rand = new Random();
			SignInCnf signInCnf = inCnfs.get(0);
			Integer signInDtlId = null;
			Integer inviteLimit = signInCnf.getInviteLimit();
			BigDecimal baseAmount = signInCnf.getBaseAmount();
			MemberSignInDtlExample memberSignInDtlExample = new MemberSignInDtlExample();
			memberSignInDtlExample.createCriteria().andSignInCnfIdEqualTo(signInCnf.getId()).andMemberIdEqualTo(memberId).andRewardTypeEqualTo("1").andDelFlagEqualTo("0");
			List<MemberSignInDtl> memberSignInDtls = memberSignInDtlService.selectByExample(memberSignInDtlExample);
			if(CollectionUtils.isNotEmpty(memberSignInDtls)){
				for (MemberSignInDtl memberSignInDtl : memberSignInDtls) {
					Date helpDate = memberSignInDtl.getCreateDate();
					String isHelp = memberSignInDtl.getIsHelp() == null ? "" : memberSignInDtl.getIsHelp();
					haveSignInAmount = memberSignInDtl.getAmount().add(haveSignInAmount);
					if(helpDate.after(begin) && helpDate.before(end) && isHelp.equals("1")){
						helpPeopleNum ++;
					}
				}
			}
			
			SignInCnfDtlExample signInCnfDtlExample = new SignInCnfDtlExample();
			signInCnfDtlExample.createCriteria().andSignInCnfIdEqualTo(signInCnf.getId()).andRateTypeIn(memberRateTypeList).andDelFlagEqualTo("0");
			signInCnfDtlExample.setOrderByClause("id");
			List<SignInCnfDtl> inCnfDtls = signInCnfDtlService.selectByExample(signInCnfDtlExample);
			for (SignInCnfDtl cnfDtl : inCnfDtls) {
				Integer accumulationDay = cnfDtl.getAccumulationDay();
				BigDecimal beginRate = cnfDtl.getBeginRate();
				BigDecimal endRate = cnfDtl.getEndRate();
				BigDecimal beginAmount = cnfDtl.getBeginAmount();
				BigDecimal endAmount = cnfDtl.getEndAmount();
				Double randRate = beginRate.doubleValue() + ((endRate.doubleValue() - beginRate.doubleValue()) * rand.nextDouble());
				BigDecimal rate = new BigDecimal(randRate).divide(hundred).setScale(5, BigDecimal.ROUND_DOWN);
				//1 基础比例2 首次签到3 连续签到n天额外比例4金额区间比例5首次好友签6邀请新用户签到比例7邀请老用户签比例
				String rateType = cnfDtl.getRateType();
				if(rateType.equals("3") && currentContinuity != null && accumulationDay != null 
						&& currentContinuity > 0 && accumulationDay > 0 && currentContinuity % accumulationDay == 0){
					continuityDay = accumulationDay.toString();
					sevenSignIn = true;
					sevenMemberSignInAmount = baseAmount.multiply(rate).setScale(2, BigDecimal.ROUND_DOWN);
				}else if(rateType.equals("2")){
					signInDtlId = cnfDtl.getId();
					memberSignInAmount = baseAmount.multiply(rate).setScale(2, BigDecimal.ROUND_DOWN);
					break;
				}else if(rateType.equals("1") || rateType.equals("4")){
					signInDtlId = cnfDtl.getId();
					if(rateType.equals("4") && memberAccount.getBalance().compareTo(beginAmount) >= 0 
							&& memberAccount.getBalance().compareTo(endAmount) < 0){
						isAmountRate = true;
						memberSignInAmount = baseAmount.multiply(rate).setScale(2, BigDecimal.ROUND_DOWN);
					}
					if(rateType.equals("1") && !isAmountRate){
						memberSignInAmount = baseAmount.multiply(rate).setScale(2, BigDecimal.ROUND_DOWN);
					}
				}
			}
			
			//////////////////////////////////////助力开始///////////////////////////////////////////////////////
			int count = 0;
			if(sourceMemberId != null && !memberId.equals(sourceMemberId)){
				MemberSignInDtlExample smSignInDtlExample = new MemberSignInDtlExample();
				smSignInDtlExample.createCriteria().andRewardTypeEqualTo("1").andCurrentSourceMemberIdEqualTo(sourceMemberId).andDelFlagEqualTo("0")
				.andCreateDateBetween(begin, end).andIsHelpEqualTo("1");
				helpPeopleNum = memberSignInDtlService.countByExample(smSignInDtlExample);//查询砍价来源用户的助力人数
				count = memberControlService.findMemberIsBlack(sourceMemberId,"2",inviteLimit);
				if(helpPeopleNum < inviteLimit && count > 0){//已帮忙助力人数 > 最大助力人数，将不在助力
					//查询当前用户的助力比例
					String smRateType = "";
					//查询当前用户是否在APP上签到过，没有签到过，按首次好友签到比例来计算
					MemberSignInExample smSignInExample = new MemberSignInExample();
					smSignInExample.createCriteria().andMemberIdEqualTo(memberId).andDelFlagEqualTo("0");
					int cutOrderNum = countByExample(smSignInExample);//当前用户是否签到过
					if(cutOrderNum > 0){
						boolean isNewEnjoy = memberInfoService.getIsNewEnjoy(memberId,null);//查询当前登入用户是否是新用户
						//判断邀请新用户签到比例
						if(isNewEnjoy){
							//新用户
							smRateType = "6";
						}else{
							//老用户
							smRateType = "7";
						}
					}else{
						//首次好友签到
						smRateType = "5";
					}
					SignInCnfDtlExample smSignInCnfDtlExample = new SignInCnfDtlExample();
					smSignInCnfDtlExample.createCriteria().andSignInCnfIdEqualTo(signInCnf.getId()).andRateTypeEqualTo(smRateType).andDelFlagEqualTo("0");
					smSignInCnfDtlExample.setOrderByClause("id");
					smSignInCnfDtlExample.setLimitStart(0);
					smSignInCnfDtlExample.setLimitSize(1);
					List<SignInCnfDtl> sminCnfDtls = signInCnfDtlService.selectByExample(smSignInCnfDtlExample);
					for (SignInCnfDtl cnfDtl : sminCnfDtls) {
						BigDecimal beginRate = cnfDtl.getBeginRate();
						BigDecimal endRate = cnfDtl.getEndRate();
						Double randRate = beginRate.doubleValue() + ((endRate.doubleValue() - beginRate.doubleValue()) * rand.nextDouble());
						helpAmount = baseAmount.multiply(new BigDecimal(randRate)).divide(hundred).setScale(2, BigDecimal.ROUND_DOWN);
					}
				}
			}
			//////////////////////////////////////助力结束///////////////////////////////////////////////////////
			
			
			//m明细表
			MemberSignInDtl memberSignInDtl = new MemberSignInDtl();
			memberSignInDtl.setMemberId(memberId);
			memberSignInDtl.setMemberSignIn(memberSignIn.getId());
			memberSignInDtl.setRewardType("1");//奖励类型1现金2 积分(预留)
			memberSignInDtl.setAmount(memberSignInAmount);
			memberSignInDtl.setSignInCnfId(signInCnf.getId());
			memberSignInDtl.setSignInCnfDtl(signInDtlId);
			memberSignInDtl.setCurrentSourceMemberId(sourceMemberId);
			if(sourceMemberId != null && helpPeopleNum < inviteLimit && count > 0){
				memberSignInDtl.setIsHelp("1");//是否为源会员助力0否1是
				memberSignInDtl.setHelpAmount(helpAmount);
			}else{
				memberSignInDtl.setIsHelp("0");
			}
			if(sevenSignIn){
				memberSignInDtl.setExtraAmount(sevenMemberSignInAmount);
			}
			memberSignInDtl.setSignInClient(signInClient);//签到客户端1app 2h5
			memberSignInDtl.setCreateBy(memberId);
			memberSignInDtl.setCreateDate(currentDate);
			memberSignInDtl.setDelFlag("0");
			memberSignInDtlService.insertSelective(memberSignInDtl);
			//m账户明细表
			MemberAccountDtl memberAccountDtl = new MemberAccountDtl();
			memberAccountDtl.setAccId(memberAccount.getId());
			memberAccountDtl.setTallyMode(Const.INTEGRAL_TALLY_MODE_INCOME);
			memberAccountDtl.setTotalFreeze(memberAccount.getFreeze());
			memberAccountDtl.setAmount(memberSignInAmount);
			memberAccountDtl.setBalance(memberAccount.getBalance().add(memberSignInAmount));
			memberAccountDtl.setBizType(Const.MEMBER_ACCOUNT_BIZ_TYPE_SIGN_IN);
			memberAccountDtl.setBizId(memberSignInDtl.getId());
			memberAccountDtl.setCreateBy(memberId);
			memberAccountDtl.setCreateDate(currentDate);
			memberAccountDtl.setDelFlag("0");
			memberAccountDtlService.insertSelective(memberAccountDtl);
			//7天连续签到
			if(sevenSignIn){
				MemberAccountDtl sevenMemberAccountDtl = new MemberAccountDtl();
				sevenMemberAccountDtl.setAccId(memberAccount.getId());
				sevenMemberAccountDtl.setTallyMode(Const.INTEGRAL_TALLY_MODE_INCOME);
				sevenMemberAccountDtl.setTotalFreeze(memberAccount.getFreeze());
				sevenMemberAccountDtl.setAmount(sevenMemberSignInAmount);
				sevenMemberAccountDtl.setBalance(memberAccount.getBalance().add(sevenMemberSignInAmount));
				sevenMemberAccountDtl.setBizType(Const.MEMBER_ACCOUNT_BIZ_TYPE_SIGNATURE);
				sevenMemberAccountDtl.setBizId(memberAccountDtl.getId());
				sevenMemberAccountDtl.setCreateBy(memberId);
				sevenMemberAccountDtl.setCreateDate(currentDate);
				sevenMemberAccountDtl.setDelFlag("0");
				memberAccountDtlService.insertSelective(sevenMemberAccountDtl);
			}
			//m账户表
			memberAccount.setBalance(memberAccount.getBalance().add(memberSignInAmount).add(sevenMemberSignInAmount));
			memberAccount.setUpdateBy(memberId);
			memberAccount.setUpdateDate(currentDate);
			memberAccountService.updateByPrimaryKeySelective(memberAccount);
			
		}else{
			throw new ArgException("很遗憾，签到未获得金额");
		}
		map.put("continuityDay", continuityDay);
		map.put("sevenSignIn", sevenSignIn);
		map.put("memberSignInAmount", memberSignInAmount);
		return map;
	}


	private List<String> getMemberRateType(Integer memberId,List<MemberSignIn> memberSignIns) {
		//1 基础比例2 首次签到3 连续签到n天额外比例4金额区间比例
		List<String> rateTypeList = new ArrayList<String>();
		if(CollectionUtils.isEmpty(memberSignIns)){
			rateTypeList.add("2");
		}else{
			rateTypeList.add("1");
			rateTypeList.add("3");
			rateTypeList.add("4");
		}
		
		return rateTypeList;
	}

	private List<MemberSignIn> getMemberSignInModels(Integer memberId) {
		return memberSignInCustomMapper.getMemberSignInModels(memberId);
	}

	public Map<String, Object> getShareFriendSignIn(Integer memberId, JSONObject reqDataJson) {
		Map<String,Object> map = new HashMap<String, Object>();
		List<Map<String,Object>> dataList = new ArrayList<Map<String,Object>>();
		Date currentDate = new Date();
		Date begin = DateUtil.getDateAfterAndBeginTime(currentDate, 0);
		Date end = DateUtil.getDateAfterAndEndTime(currentDate, 0);
		BigDecimal drawHelpAmount = new BigDecimal("0");
		BigDecimal unDrawHelpAmount = new BigDecimal("0");
		Integer inviteLimit = 0;
		//签到方案表
		SignInCnfExample signInCnfExample = new SignInCnfExample();
		signInCnfExample.createCriteria().andIsEffectEqualTo("1").andDelFlagEqualTo("0");
		signInCnfExample.setLimitStart(0);
		signInCnfExample.setLimitSize(1);
		signInCnfExample.setOrderByClause("id desc");
		List<SignInCnf> inCnfs = signInCnfService.selectByExample(signInCnfExample);
		if(CollectionUtils.isNotEmpty(inCnfs)){
			inviteLimit = inCnfs.get(0).getInviteLimit();
		}
		//签到明细表
		MemberSignInDtlExample memberSignInDtlExample = new MemberSignInDtlExample();
		memberSignInDtlExample.createCriteria().andCreateDateBetween(begin, end).andIsHelpEqualTo("1")
					.andCurrentSourceMemberIdEqualTo(memberId).andRewardTypeEqualTo("1").andDelFlagEqualTo("0");
		memberSignInDtlExample.setLimitStart(0);
		memberSignInDtlExample.setLimitSize(inviteLimit);
		memberSignInDtlExample.setOrderByClause("id");
		List<MemberSignInDtl> memberSignInDtls = memberSignInDtlService.selectByExample(memberSignInDtlExample);
		String unDrawHelpAmountMIds = "";
		if(CollectionUtils.isNotEmpty(memberSignInDtls)){
			for (MemberSignInDtl memberSignInDtl : memberSignInDtls) {
				Map<String,Object> dataMap = new HashMap<String, Object>();
				Integer mId = memberSignInDtl.getMemberId();
				MemberInfo m = memberInfoService.selectByPrimaryKey(mId);
				String headPic = m.getWeixinHead();
				if(StringUtil.isBlank(headPic)){
					headPic = m.getPic();
				}
				String isHelpAmountGet = memberSignInDtl.getIsHelpAmountGet();
				BigDecimal helpAmount = memberSignInDtl.getHelpAmount();
				if(isHelpAmountGet.equals("1")){
					drawHelpAmount = drawHelpAmount.add(helpAmount);
				}else{
					unDrawHelpAmount = unDrawHelpAmount.add(helpAmount);
					unDrawHelpAmountMIds = unDrawHelpAmountMIds+m.getId()+",";
				}
				boolean isNewEnjoy = memberInfoService.getIsNewEnjoy(mId,m);
				dataMap.put("headPic", StringUtil.getPic(headPic, ""));
				dataMap.put("helpAmount", helpAmount);
				dataMap.put("isNewEnjoy", isNewEnjoy);
				dataMap.put("mId", m.getId());
				dataList.add(dataMap);
			}
		}
		if(!StringUtil.isBlank(unDrawHelpAmountMIds)){
			unDrawHelpAmountMIds = unDrawHelpAmountMIds.substring(0,unDrawHelpAmountMIds.length()-1);
		}
		map.put("unDrawHelpAmountMIds", unDrawHelpAmountMIds);
		map.put("memberSize", memberSignInDtls.size());
		map.put("unDrawHelpAmount", unDrawHelpAmount);
		map.put("drawHelpAmount", drawHelpAmount);
		map.put("inviteLimit", inviteLimit);
		map.put("dataList", dataList);
		return map;
	}

	public List<String> getSignInBroadcastContent(JSONObject reqDataJson) {
		List<String> infos = new ArrayList<>();
		String type = "1";
		if(reqDataJson.containsKey("type")){
			type = reqDataJson.getString("type");
		}
		if("1".equals(type)){
			infos = DataDicUtil.getMemberWithDrawInfo();
		}else if("2".equals(type)){
			infos = memberSignInSettingService.getNewSignInBroadcastContent();
		}
		return infos;
	}

	public List<Map<String,Object>> getSignInOrDrawRecords(Integer memberId, JSONObject reqDataJson, Integer pageSize) {
		String type = reqDataJson.getString("type");
		Integer currentPage = reqDataJson.getInt("currentPage");
		List<Map<String,Object>> dataList = new ArrayList<Map<String,Object>>();
		List<String> bizTypeList = new ArrayList<String>();
		MemberAccount memberAccount = memberAccountService.findAccountByMemberId(memberId);
		if(type.equals("1")){//签到记录
			bizTypeList.add("1");
			bizTypeList.add("5");
			bizTypeList.add("6");
			if(memberAccount != null){
				MemberAccountDtlExample memberAccountDtlExample = new MemberAccountDtlExample();
				memberAccountDtlExample.createCriteria().andAccIdEqualTo(memberAccount.getId()).
				andDelFlagEqualTo("0").andBizTypeIn(bizTypeList);
				memberAccountDtlExample.setLimitStart(currentPage*pageSize);
				memberAccountDtlExample.setLimitSize(pageSize);
				memberAccountDtlExample.setOrderByClause("id desc");
				List<MemberAccountDtl> memberAccountDtls = memberAccountDtlService.selectByExample(memberAccountDtlExample);
				if(CollectionUtils.isNotEmpty(memberAccountDtls)){
					for (MemberAccountDtl memberAccountDtl : memberAccountDtls) {
						Map<String,Object> dataMap = new HashMap<String,Object>();
						//业务类型1 签到2 提现3 兑换优惠券4 冻结 5好友签到助力6 连签红包7解冻
						String bizType = memberAccountDtl.getBizType();
						String content = "";
						if(bizType.equals("1")){
							content = "签到红包";
						}else if(bizType.equals("2")){
							content = "微信红包提现";
						}else if(bizType.equals("3")){
							content = "现金券提现";
						}else if(bizType.equals("4")){
							content = "好友签到助力红包";
						}else if(bizType.equals("5")){
							content = "好友签到奖励红包";
						}else if(bizType.equals("6")){
							content = "连签红包";
						}else if(bizType.equals("7")){
							content = "微信红包退还";
						}else{
							content = "其他";
						}
						dataMap.put("date", DateUtil.getFormatDate(memberAccountDtl.getCreateDate(), "yyyy-MM-dd"));
						dataMap.put("amount", memberAccountDtl.getAmount());
						dataMap.put("content", content);
						dataList.add(dataMap);
					}
				}
			}
		}else if(type.equals("2")){ //提现记录:仅查询1、2类型
			WithdrawOrderExample withdrawOrderExample = new WithdrawOrderExample();
			withdrawOrderExample.createCriteria().andMemberIdEqualTo(memberId).andDelFlagEqualTo("0").andWithdrawTypeIn(Lists.newArrayList("1", "2"));
			withdrawOrderExample.setLimitStart(currentPage*pageSize);
			withdrawOrderExample.setLimitSize(pageSize);
			withdrawOrderExample.setOrderByClause("id desc");
			List<WithdrawOrder> withdrawOrders = withdrawOrderService.selectByExample(withdrawOrderExample);
			if(CollectionUtils.isNotEmpty(withdrawOrders)){
				for (WithdrawOrder withdrawOrder : withdrawOrders) {
					Map<String,Object> dataMap = new HashMap<String,Object>();
					String withdrawType = withdrawOrder.getWithdrawType();
					//状态（任何状态都需判断用户的余额是否足够）1 申请中2审核通过(此状态需冻结用户金额)4 提现成功（此状态扣除金额，解除冻结）5 审核不通过6 提现失败（此状态需解除冻结）
					String status = withdrawOrder.getStatus();
					String statusName = "";
					String content = "";
					String rejectReason = "";
					if(status.equals("1")){
						statusName = "申请中";
						if("1".equals(withdrawOrder.getWithdrawMethod())){
							statusName = "线下提现(申请中)";
						}
					}else if(status.equals("2")){
						statusName = "审核通过";
					}else if(status.equals("3")){
						statusName = "";
					}else if(status.equals("4")){
						statusName = "提现成功";
					}else if(status.equals("5")){
						statusName = "审核不通过";
					}else if(status.equals("6")){
						statusName = "提现失败";
					}else if(status.equals("7")){
						CashTransferExample cashTransferExample = new CashTransferExample();
						cashTransferExample.createCriteria().andDelFlagEqualTo("0")
							.andStatusEqualTo("2")
							.andWithdrawOrderIdEqualTo(withdrawOrder.getId());
						cashTransferExample.setOrderByClause(" id desc");
						List<CashTransfer> cashTransferList = cashTransferMapper.selectByExample(cashTransferExample);
						if(cashTransferList != null && cashTransferList.size() > 0 && !StringUtil.isEmpty(cashTransferList.get(0).getRejectReason())) {
							rejectReason = "原因："+cashTransferList.get(0).getRejectReason();
						}
					}
					if(withdrawType.equals("1")){
						content = "现金券提现";
					}else if(withdrawType.equals("2")){
						content = "微信红包提现";
					}else{
						content = "其他";
					}
					if(status.equals("7")) {
						content = "线下提现失败";
					}
					dataMap.put("date", DateUtil.getFormatDate(withdrawOrder.getCreateDate(), "yyyy-MM-dd"));
					dataMap.put("amount", withdrawOrder.getAmount());
					dataMap.put("content", content);
					dataMap.put("statusName", statusName);
					dataMap.put("rejectReason", rejectReason);
					dataList.add(dataMap);
				}
			}
		}
		
		return dataList;
	}

	public void addReceiveExtraAmount(Integer memberId, JSONObject reqDataJson) {
		if(memberId != null){
			Map<String,Object>map = blackListService.getIsBlack(memberId,"1");
			if((boolean) map.get("isBlack")){
				throw new ArgException(map.get("blackReason").toString());
			}
		}
		String mIds = reqDataJson.getString("mIds");
		Date currentDate = new Date();
		Date begin = DateUtil.getDateAfterAndBeginTime(currentDate, 0);
		Date end = DateUtil.getDateAfterAndEndTime(currentDate, 0);
		if(!StringUtil.isBlank(mIds)){
			//储存前端的未领取金额用户id
			List<Integer> mIdList = new ArrayList<Integer>();
			//储存后端查询出来的未领取金额用户id
			List<Integer> memberIdList = new ArrayList<Integer>();
			//储存前后端的未领取金额用户交集id
			List<Integer> resultIds = new ArrayList<Integer>();
			for (String str : mIds.split(",")) {
				mIdList.add(Integer.valueOf(str));
			}
			MemberSignInDtlExample memberSignInDtlExample = new MemberSignInDtlExample();
			memberSignInDtlExample.createCriteria().andCurrentSourceMemberIdEqualTo(memberId).andIsHelpEqualTo("1")
			.andCreateDateBetween(begin, end).andIsHelpEqualTo("1").andIsHelpAmountGetEqualTo("0")
			.andDelFlagEqualTo("0");
			memberSignInDtlExample.setLimitStart(0);
			memberSignInDtlExample.setLimitSize(6);
			List<MemberSignInDtl> signInDtls = memberSignInDtlService.selectByExample(memberSignInDtlExample);
			if(CollectionUtils.isNotEmpty(signInDtls)){
				for (MemberSignInDtl memberSignInDtl : signInDtls) {
					memberIdList.add(memberSignInDtl.getMemberId());
				}
			}
			if(CollectionUtils.isNotEmpty(memberIdList)){
				//取交集
				resultIds.clear();
				resultIds.addAll(memberIdList);
				resultIds.retainAll(mIdList);
			}
			if(CollectionUtils.isNotEmpty(resultIds)){
				MemberAccount account = memberAccountService.findAccountByMemberId(memberId);
				Integer accId = account.getId();
				BigDecimal balance = new BigDecimal("0");
				for (MemberSignInDtl memberSignInDtl : signInDtls) {
					Integer userId = memberSignInDtl.getMemberId();
					if(resultIds.contains(userId)){
						balance = balance.add(memberSignInDtl.getHelpAmount());
						MemberSignInDtlCustom custom = new MemberSignInDtlCustom();
						custom.setId(memberSignInDtl.getId());
						custom.setUpdateBy(memberId);
						custom.setUpdateDate(currentDate);
						custom.setBeginDate(begin);
						custom.setEndDate(end);
						int count = memberSignInDtlService.updateReceiveExtraAmount(custom);
						if(count <= 0){
							throw new ArgException("未有可领取的红包");
						}
						MemberAccountDtl accountDtl = new MemberAccountDtl();
						accountDtl.setAccId(accId);
						accountDtl.setAmount(memberSignInDtl.getHelpAmount());
						accountDtl.setBalance(account.getBalance().add(memberSignInDtl.getHelpAmount()));
						accountDtl.setBizType(Const.MEMBER_ACCOUNT_BIZ_TYPE_FRIEND_SIGN_IN);
						accountDtl.setBizId(memberSignInDtl.getId());
						accountDtl.setTallyMode(Const.INTEGRAL_TALLY_MODE_INCOME);
						accountDtl.setCreateBy(memberId);
						accountDtl.setCreateDate(currentDate);
						accountDtl.setDelFlag("0");
						memberAccountDtlService.insertSelective(accountDtl);
					}
				}
				account.setBalance(balance.add(account.getBalance()));
				account.setUpdateBy(memberId);
				account.setUpdateDate(currentDate);
				memberAccountService.updateByPrimaryKeySelective(account);
			}else{
				throw new ArgException("未有可领取的红包");
			}
		}else{
			throw new ArgException("未有可领取的红包");
		}
	}

	public Map<String, Object> getWithdrawCashtraPage(Integer memberId, JSONObject reqDataJson, Integer pageSize) {
		Integer currentPage = reqDataJson.getInt("currentPage");
		boolean isWecharUser = false;
		boolean isMobileUser = false;
		boolean isWithdrawFail = false;//是否存在提现失败单据
		WithdrawCnfExample cnfExample = new WithdrawCnfExample();
		cnfExample.createCriteria().andIsEffectEqualTo("1").andDelFlagEqualTo("0");
		cnfExample.setLimitStart(currentPage*pageSize);
		cnfExample.setLimitSize(pageSize);
		cnfExample.setOrderByClause("amount");
		List<WithdrawCnf> cnfs = withdrawCnfService.selectByExample(cnfExample);
		MemberInfo memberInfo = memberInfoService.selectByPrimaryKey(memberId);
		if(memberInfo.getmVerfiyFlag().equals("1")){
			isMobileUser = true;
		}else if(!StringUtil.isBlank(memberInfo.getWeixinUnionid())){
			isWecharUser = true;
		}
		WithdrawOrderExample withdrawOrderExample = new WithdrawOrderExample();
		withdrawOrderExample.createCriteria().andMemberIdEqualTo(memberId).andStatusEqualTo("6").andDelFlagEqualTo("0").andWithdrawMethodEqualTo("0").andWithdrawTypeEqualTo("2");
		int withdrawOrderFails = withdrawOrderService.countByExample(withdrawOrderExample);
		if(withdrawOrderFails > 0){
			isWithdrawFail = true;
		}
		Map<String, Object> map = new HashMap<>();
		map.put("withdrawCnf", cnfs);
		map.put("isWecharUser", isWecharUser);
		map.put("isMobileUser", isMobileUser);
		map.put("isWithdrawFail", isWithdrawFail);
		return map;
	}

	public Map<String, Object> addMemberWithdrawCash(Integer memberId, JSONObject reqDataJson, JSONObject reqPRM) {
		//提现步骤：
		//1：app上申请提现，处于提现中（冻结用户金额）
		//2：平台端审核（通过：直接通过，失败：解冻用户冻结金额）
		//3_1：定时查询红包提现审核通过的插入红包表
		//3_2: 定时查询优惠券的审核通过直接给用户优惠券（解冻用户金额，扣除用户余额）
		//4：定时查询红包表未发包的红包，发放给用户（发放失败：解冻用户金额）
		//5：定时查询用户是否有领取红包，发放失败的过滤掉（已领取成功：解冻用户金额，扣除用户余额，失败：解冻用户金额）
		Integer version = reqPRM.getInt("version");
		String system = reqPRM.getString("system");
		Map<String,Object> blackMap = blackListService.getIsBlack(memberId,"1");
		if((boolean) blackMap.get("isBlack")){
			throw new ArgException(blackMap.get("blackReason").toString());
		}
		String type = "0";//0正常 1未绑定手机 2 未绑定微信
		MemberInfo memberInfo = memberInfoService.selectByPrimaryKey(memberId);
		if(!memberInfo.getmVerfiyFlag().equals("1")){
			if((version <= 51 && system.equals(Const.ANDROID)) || (version <=54 && system.equals(Const.IOS))){
				throw new ArgException("需要先绑定手机才能提现哦");
			}else{
				int count = memberMobileWeixinMapService.getIsMemberRelation(memberId, null);
				if(count <= 0){
					type = "1";
				}
			}
		}else if(StringUtil.isBlank(memberInfo.getWeixinUnionid())){
			if((version <= 51 && system.equals(Const.ANDROID)) || (version <=54 && system.equals(Const.IOS))){
				throw new ArgException("请先绑定微信账号方便提现");
			}else{
				int count = memberMobileWeixinMapService.getIsMemberRelation(memberId, null);
				if(count <= 0){
					type = "2";
				}
			}
		}
		if("0".equals(type)){
			Integer withdrawCashId = reqDataJson.getInt("withdrawCashId");
			WithdrawCnfExample withdrawCnfExample = new WithdrawCnfExample();
			withdrawCnfExample.createCriteria().andIdEqualTo(withdrawCashId).andIsEffectEqualTo("1").andDelFlagEqualTo("0");
			List<WithdrawCnf> withdrawCnfs = withdrawCnfService.selectByExample(withdrawCnfExample);
			if(CollectionUtils.isNotEmpty(withdrawCnfs)){
				MemberAccount memberAccount = memberAccountService.findAccountByMemberId(memberId);
				if(memberAccount != null){
					BigDecimal freeze = memberAccount.getFreeze();
					BigDecimal balance = memberAccount.getBalance();
					Date currentDate = new Date();
					WithdrawCnf withdrawCnf = withdrawCnfs.get(0);
					WithdrawOrder withdrawOrder = new WithdrawOrder();
					String remarks = "";
					String orderCode = "W"+CommonUtil.getOrderCode();
					//冻结金额
					memberAccount.setFreeze(withdrawCnf.getAmount());//该次冻结的金额,不是冻结的总金额
					memberAccount.setUpdateBy(memberId);
					memberAccount.setUpdateDate(currentDate);
					int count = memberAccountService.updateMemberAccountFreeze(memberAccount);
					if(count <= 0){
						throw new ArgException("余额不足");
					}
					
					if(withdrawCnf.getWithdrawType().equals("1")){
						//查询优惠券是否可以正常领取
						if(withdrawCnf.getCouponId() != null){
							memberCouponService.addReceiveWithdrawCoupon(memberId, withdrawCnf.getCouponId());
						}else{
							throw new ArgException("非法用户");
						}
						remarks = "兑换优惠券：冻结金额";
						withdrawOrder.setStatus("1");
						withdrawOrder.setOrderCode(orderCode);
						withdrawOrder.setMemberId(memberId);
						withdrawOrder.setAccId(memberAccount.getId());
						withdrawOrder.setAmount(withdrawCnf.getAmount());
						withdrawOrder.setWithdrawType(withdrawCnf.getWithdrawType());
						withdrawOrder.setCouponId(withdrawCnf.getCouponId());
						withdrawOrder.setWithdrawCnfId(withdrawCnf.getId());
						withdrawOrder.setCreateBy(memberId);
						withdrawOrder.setCreateDate(currentDate);
						withdrawOrder.setDelFlag("0");
						withdrawOrderService.insertSelective(withdrawOrder);
					}else if(withdrawCnf.getWithdrawType().equals("2")){
						//查询下该用户是否存在提现失败的单据
						WithdrawOrderExample withdrawOrderExample = new WithdrawOrderExample();
						withdrawOrderExample.createCriteria().andMemberIdEqualTo(memberId).andStatusEqualTo("6").andDelFlagEqualTo("0").andWithdrawMethodEqualTo("0").andWithdrawTypeEqualTo("2");
						int withdrawOrderFails = withdrawOrderService.countByExample(withdrawOrderExample);
						if(withdrawOrderFails > 0){
							//存在提现失败的记录
							if(reqDataJson.containsKey("realName") && !StringUtil.isBlank(reqDataJson.getString("realName"))){
								withdrawOrder.setRealName(reqDataJson.getString("realName"));
							}else{
								throw new ArgException("请填写姓名");
							}
							if(reqDataJson.containsKey("alipayAccount") && !StringUtil.isBlank(reqDataJson.getString("alipayAccount"))){
								withdrawOrder.setAlipayAccount(reqDataJson.getString("alipayAccount"));
							}else{
								throw new ArgException("请填写支付宝账号");
							}
							withdrawOrder.setWithdrawMethod("1");
						}else{
							withdrawOrder.setWithdrawMethod("0");
						}
						remarks = "兑换微信红包：冻结金额";
						withdrawOrder.setStatus("1");
						withdrawOrder.setOrderCode(orderCode);
						withdrawOrder.setMemberId(memberId);
						withdrawOrder.setAccId(memberAccount.getId());
						withdrawOrder.setAmount(withdrawCnf.getAmount());
						withdrawOrder.setWithdrawType(withdrawCnf.getWithdrawType());
						withdrawOrder.setWithdrawCnfId(withdrawCnf.getId());
						
						withdrawOrder.setCreateBy(memberId);
						withdrawOrder.setCreateDate(currentDate);
						withdrawOrder.setDelFlag("0");
						withdrawOrderService.insertSelective(withdrawOrder);
					}else{
						throw new ArgException("非法用户");
					}
					//账户明细表
					MemberAccountDtl accountDtl = new MemberAccountDtl();
					accountDtl.setAccId(memberAccount.getId());
					accountDtl.setTallyMode(Const.INTEGRAL_TALLY_MODE_ACCOUNT);
					accountDtl.setFreezeAmount(withdrawCnf.getAmount());
					accountDtl.setTotalFreeze(freeze.add(withdrawCnf.getAmount()));
					accountDtl.setBalance(balance);
					accountDtl.setBizType("4");//业务类型1 签到2 提现3 兑换优惠券4 冻结 5好友签到助力6 连签红包7解冻
					accountDtl.setBizId(withdrawOrder.getId());
					accountDtl.setCreateBy(memberId);
					accountDtl.setCreateDate(currentDate);
					accountDtl.setDelFlag("0");
					accountDtl.setRemarks(remarks);
					memberAccountDtlService.insertSelective(accountDtl);
					//记录流水
					WithdrawOrderStatusLog log = new WithdrawOrderStatusLog();
					log.setStatus("1");
					log.setWithdrawOrderId(withdrawOrder.getId());
					log.setCreateBy(memberId);
					log.setCreateDate(currentDate);
					log.setDelFlag("0");
					withdrawOrderStatusLogService.insertSelective(log);
				}else{
					throw new ArgException("非法用户");
				}
			}else{
				throw new ArgException("提现失败");
			}
		}
		Map<String, Object> map = new HashMap<>();
		map.put("type", type);
		return map;
	}

	public Map<String, Object> addRedPack(Integer memberId, MemberAccount memberAccount, String rewardGift, Date currentDate, String signInClient) {
		Map<String, Object> map = new HashMap<String, Object>();
		BigDecimal memberSignInAmount = new BigDecimal("0");//签到金额
		boolean isAmountRate = false;//是否匹配金额比例
		boolean firstSignIn = false;
		Date dayBeginDate = DateUtil.getDateAfterAndBeginTime(currentDate, 0);
		Date dayEndDate = DateUtil.getDateAfterAndEndTime(currentDate, 0);
		List<MemberSignIn> memberSignIns = getMemberSignInModels(memberId);
		MemberSignIn memberSignIn = null;
		if(CollectionUtils.isNotEmpty(memberSignIns)){
			memberSignIn = memberSignIns.get(0);
			Date lastSignInDate = memberSignIn.getLastSignInDate();
			if(lastSignInDate.after(DateUtil.addDay(dayBeginDate, -1)) && lastSignInDate.before(DateUtil.addDay(dayEndDate, -1))){
				memberSignIn.setCurrentContinuity(memberSignIn.getCurrentContinuity()+1);
				if(memberSignIn.getMostContinuity() < memberSignIn.getCurrentContinuity()){
					memberSignIn.setMostContinuity(memberSignIn.getMostContinuity()+1);
				}
			}else{
				memberSignIn.setCurrentContinuity(1);
			}
			memberSignIn.setLastSignInDate(currentDate);
			updateByPrimaryKeySelective(memberSignIn);
		}else{
			memberSignIn = new MemberSignIn();
			memberSignIn.setMemberId(memberId);
			memberSignIn.setLastSignInDate(currentDate);
			memberSignIn.setCreateBy(memberId);
			memberSignIn.setCreateDate(currentDate);
			memberSignIn.setDelFlag("0");
			memberSignIn.setCurrentContinuity(1);
			memberSignIn.setMostContinuity(1);
			memberSignIn.setLastSignInDate(currentDate);
			insertSelective(memberSignIn);
			firstSignIn = true;
		}
		//获取规则类型
		SignInCnfExample signInCnfExample = new SignInCnfExample();
		signInCnfExample.createCriteria().andIsEffectEqualTo("1").andDelFlagEqualTo("0");
		signInCnfExample.setLimitStart(0);
		signInCnfExample.setLimitSize(1);
		signInCnfExample.setOrderByClause("id desc");
		List<SignInCnf> inCnfs = signInCnfService.selectByExample(signInCnfExample);
		if(CollectionUtils.isNotEmpty(inCnfs)){
			Random rand = new Random();
			SignInCnf signInCnf = inCnfs.get(0);
			Integer signInDtlId = null;
			BigDecimal baseAmount = signInCnf.getBaseAmount();
			//判断用户签到规则类型1 基础比例2 首次签到4金额区间比例
			List<String> memberRateTypeList = new ArrayList<String>();
			if(firstSignIn){
				memberRateTypeList.add("2");
			}else{
				memberRateTypeList.add("1");
				memberRateTypeList.add("4");
			}
			SignInCnfDtlExample signInCnfDtlExample = new SignInCnfDtlExample();
			signInCnfDtlExample.createCriteria().andSignInCnfIdEqualTo(signInCnf.getId()).andRateTypeIn(memberRateTypeList).andDelFlagEqualTo("0");
			signInCnfDtlExample.setOrderByClause("id");
			List<SignInCnfDtl> inCnfDtls = signInCnfDtlService.selectByExample(signInCnfDtlExample);
			for (SignInCnfDtl cnfDtl : inCnfDtls) {
				BigDecimal beginRate = cnfDtl.getBeginRate();
				BigDecimal endRate = cnfDtl.getEndRate();
				BigDecimal beginAmount = cnfDtl.getBeginAmount();
				BigDecimal endAmount = cnfDtl.getEndAmount();
				Double randRate = beginRate.doubleValue() + ((endRate.doubleValue() - beginRate.doubleValue()) * rand.nextDouble());
				BigDecimal rate = new BigDecimal(randRate).divide(new BigDecimal("100")).setScale(5, BigDecimal.ROUND_DOWN);
				//1 基础比例2 首次签到3 连续签到n天额外比例4金额区间比例5首次好友签6邀请新用户签到比例7邀请老用户签比例
				String rateType = cnfDtl.getRateType();
				if(rateType.equals("2")){
					signInDtlId = cnfDtl.getId();
					memberSignInAmount = baseAmount.multiply(rate).setScale(2, BigDecimal.ROUND_DOWN);
					break;
				}else if(rateType.equals("1") || rateType.equals("4")){
					signInDtlId = cnfDtl.getId();
					if(rateType.equals("4") && memberAccount.getBalance().compareTo(beginAmount) >= 0 
							&& memberAccount.getBalance().compareTo(endAmount) < 0){
						isAmountRate = true;
						memberSignInAmount = baseAmount.multiply(rate).setScale(2, BigDecimal.ROUND_DOWN);
					}
					if(rateType.equals("1") && !isAmountRate){
						memberSignInAmount = baseAmount.multiply(rate).setScale(2, BigDecimal.ROUND_DOWN);
					}
				}
			}
			//m明细表
			MemberSignInDtl memberSignInDtl = new MemberSignInDtl();
			memberSignInDtl.setMemberId(memberId);
			memberSignInDtl.setMemberSignIn(memberSignIn.getId());
			memberSignInDtl.setRewardType("1");//奖励类型1现金2 积分(预留)
			memberSignInDtl.setAmount(memberSignInAmount);
			memberSignInDtl.setSignInCnfId(signInCnf.getId());
			memberSignInDtl.setSignInCnfDtl(signInDtlId);
			memberSignInDtl.setIsHelp("0");
			memberSignInDtl.setSignInClient(signInClient);//签到客户端1app 2h5
			memberSignInDtl.setCreateBy(memberId);
			memberSignInDtl.setCreateDate(currentDate);
			memberSignInDtl.setDelFlag("0");
			memberSignInDtlService.insertSelective(memberSignInDtl);
			//m账户明细表
			MemberAccountDtl memberAccountDtl = new MemberAccountDtl();
			memberAccountDtl.setAccId(memberAccount.getId());
			memberAccountDtl.setTallyMode(Const.INTEGRAL_TALLY_MODE_INCOME);
			memberAccountDtl.setTotalFreeze(memberAccount.getFreeze());
			memberAccountDtl.setAmount(memberSignInAmount);
			memberAccountDtl.setBalance(memberAccount.getBalance().add(memberSignInAmount));
			memberAccountDtl.setBizType(Const.MEMBER_ACCOUNT_BIZ_TYPE_SIGN_IN);
			memberAccountDtl.setBizId(memberSignInDtl.getId());
			memberAccountDtl.setCreateBy(memberId);
			memberAccountDtl.setCreateDate(currentDate);
			memberAccountDtl.setDelFlag("0");
			memberAccountDtlService.insertSelective(memberAccountDtl);
			memberAccount = memberAccountService.updateModelByEx(memberAccount,memberAccount.getBalance().add(memberSignInAmount),null,null,currentDate);
		}else{
			throw new ArgException("很遗憾，签到未获得金额");
		}
		map.put("memberSignInAmount", memberSignInAmount);
		map.put("memberAccount", memberAccount);
		return map;
	}
	
}
