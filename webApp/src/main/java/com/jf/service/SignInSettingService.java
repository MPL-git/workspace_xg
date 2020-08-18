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

import com.jf.common.utils.DateUtil;
import com.jf.common.utils.StringUtil;
import com.jf.dao.MemberSignInSettingCustomMapper;
import com.jf.dao.SignInSettingMapper;
import com.jf.entity.MemberAccount;
import com.jf.entity.MemberExtend;
import com.jf.entity.MemberSignInSetting;
import com.jf.entity.MemberSignInSettingCustom;
import com.jf.entity.SignInSetting;
import com.jf.entity.SignInSettingExample;

import net.sf.json.JSONObject;

@Service
@Transactional
public class SignInSettingService extends BaseService<SignInSetting, SignInSettingExample> {
	@Autowired
	private SignInSettingMapper signInSettingMapper;
	@Autowired
	private MemberSignInSettingCustomMapper memberSignInSettingCustomMapper;
	@Autowired
	private MemberSignInSettingService memberSignInSettingService;
	@Autowired
	private CumulativeSignInSettingService cumulativeSignInSettingService;
	@Autowired
	private MemberAccountService memberAccountService;
	@Autowired
	private BlackListService blackListService;
	@Autowired
	private MemberExtendService memberExtendService;
	@Autowired
	public void setSignInSettingMapper(SignInSettingMapper signInSettingMapper) {
		this.setDao(signInSettingMapper);
		this.signInSettingMapper = signInSettingMapper;
	}
	public Map<String, Object> getSignInHomePage(Integer memberId, JSONObject reqDataJson) {
		Date currentDate = new Date();
		String type = "1";// 1请求的是当月的数据 2上月的数据
		if(reqDataJson.containsKey("type") && !StringUtil.isBlank(reqDataJson.getString("type"))){
			type = reqDataJson.getString("type");
		}
		if("2".equals(type)){
			currentDate = DateUtil.addMonth(currentDate, -1);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		int year = Integer.parseInt(DateUtil.getFormatDate(currentDate, "yyyy"));
		int month = Integer.parseInt(DateUtil.getFormatDate(currentDate, "MM"));
		Date dayBeginDate = DateUtil.getDateAfterAndBeginTime(currentDate, 0);
		Date dayEndDate = DateUtil.getDateAfterAndEndTime(currentDate, 0);
		Date monthBeginDate = DateUtil.getMonthBeginData(currentDate);
		Date monthEndDate = DateUtil.getMonthEndData(currentDate);
		Integer memberMonthSignInCount = 0;//会员当月累计签到总数量
		Integer signInManageId = null;
		BigDecimal memberBalance = new BigDecimal("0");
		Integer supplementaryCardNum = 0;
		Integer currentSignInSettingId = null;
		String currentSignInStatus = "";
		String isSignInRemind = "0";
		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
		Map<Integer, MemberSignInSetting> memberMonthSignInMap = new HashMap<Integer, MemberSignInSetting>();//存放会员当月的签到
		if(memberId != null){
			/**会员拉黑*/
			map = blackListService.getIsBlack(memberId,"1");
			if((boolean) map.get("isBlack")){
				return map;
			}
			/**获取当月签到记录*/
			List<MemberSignInSetting> memberSignInSettings = memberSignInSettingService.getMonthMemberSignInRecord(memberId,monthBeginDate,monthEndDate);
			if(CollectionUtils.isNotEmpty(memberSignInSettings)){
				memberMonthSignInCount = memberSignInSettings.size();
				for (MemberSignInSetting memberSignInSetting : memberSignInSettings) {
					memberMonthSignInMap.put(memberSignInSetting.getSignInSettingId(), memberSignInSetting);
				}
			}
			/**获取用户账户数据*/
			MemberAccount memberAccount = memberAccountService.findAccountByMemberId(memberId);
			memberBalance = memberAccount.getBalance().subtract(memberAccount.getFreeze());
			supplementaryCardNum = memberAccount.getSupplementaryCard();
		}
		/**获取当月签到配置*/
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("year", year);
		params.put("month", month);
		List<MemberSignInSettingCustom> customs = memberSignInSettingCustomMapper.getMonthSignInSetting(params);
		if(CollectionUtils.isNotEmpty(customs)){
			signInManageId = customs.get(0).getSignInManageId();
			for (MemberSignInSettingCustom custom : customs) {
				Map<String, Object> dataMap = new HashMap<String, Object>();
				Integer signInSettingId = custom.getSignInSettingId();
				String rewardName = custom.getRewardName();
				String rewardType = custom.getRewardType();
				String rewardGift = custom.getRewardGift();
				Date signInDate = custom.getSignInDate();
				String signInStatus = "0";//0未签到 1已签到 2可补签到 3不可签到
				if("1".equals(type)){
					if(memberMonthSignInMap.containsKey(signInSettingId)){
						signInStatus = "1";
					}else if(dayBeginDate.compareTo(signInDate) > 0){
						signInStatus = "2";
					}
				}else{
					if(memberMonthSignInMap.containsKey(signInSettingId)){
						signInStatus = "1";
					}else{
						signInStatus = "3";
					}
				}
				if(signInDate.compareTo(dayBeginDate) == 0){
					currentSignInSettingId = signInSettingId;
					currentSignInStatus = signInStatus;
				}
				dataMap.put("signInSettingId", signInSettingId);
				dataMap.put("rewardName", rewardName);
				dataMap.put("rewardType", rewardType);
				dataMap.put("rewardGift", rewardGift);
				dataMap.put("signInStatus", signInStatus);
				dataMap.put("signInDate", signInDate);
				dataList.add(dataMap);
			}
		}
		/**获取当月累签奖励配置*/
		List<Map<String, Object>> cumulativeAwardList = cumulativeSignInSettingService.getCumulativeAwardList(memberId,signInManageId,memberMonthSignInCount);
		if(memberId != null){
			List<MemberExtend> memberExtends = memberExtendService.findModelByMemberId(memberId);
			if(CollectionUtils.isNotEmpty(memberExtends)){
				isSignInRemind = StringUtil.isBlank(memberExtends.get(0).getSignInRemind()) ? "0" : memberExtends.get(0).getSignInRemind();
			}
		}
		map.put("isSignInRemind", isSignInRemind);
		map.put("supplementaryCardNum", supplementaryCardNum);
		map.put("currentDate", new Date());
		map.put("dayBeginDate", dayBeginDate);
		map.put("dayEndDate", dayEndDate);
		map.put("memberBalance", memberBalance);
		map.put("memberMonthSignInCount", memberMonthSignInCount);
		map.put("monthSignInList", dataList);
		map.put("cumulativeAwardList", cumulativeAwardList);
		map.put("currentSignInSettingId", currentSignInSettingId);
		map.put("currentSignInStatus", currentSignInStatus);
		return map;
	}
	
	
}
