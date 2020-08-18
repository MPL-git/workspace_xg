package com.jf.service;

import java.util.ArrayList;
import java.util.Calendar;
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
import com.jf.common.utils.DateUtil;
import com.jf.dao.MemberSupplementarySignInCustomMapper;
import com.jf.dao.MemberSupplementarySignInMapper;
import com.jf.entity.MemberAccount;
import com.jf.entity.MemberInfo;
import com.jf.entity.MemberSupplementarySignIn;
import com.jf.entity.MemberSupplementarySignInCustom;
import com.jf.entity.MemberSupplementarySignInExample;
import com.jf.entity.SignInManage;
import com.jf.entity.SignInManageExample;
import com.jf.entity.SupplementarySignInSetting;

import net.sf.json.JSONObject;

@Service
@Transactional
public class MemberSupplementarySignInService extends BaseService<MemberSupplementarySignIn, MemberSupplementarySignInExample> {
	@Autowired
	private MemberSupplementarySignInMapper memberSupplementarySignInMapper;
	@Autowired
	private MemberSupplementarySignInCustomMapper memberSupplementarySignInCustomMapper;
	@Autowired
	private MemberAccountService memberAccountService;
	@Autowired
	private SupplementarySignInSettingService supplementarySignInSettingService;
	@Autowired
	private AssistanceDtlService assistanceDtlService;
	@Autowired
	private SignInManageService signInManageService;
	@Autowired
	private CumulativeSignInSettingService cumulativeSignInSettingService;
	@Autowired
	public void setMemberSupplementarySignInMapper(MemberSupplementarySignInMapper memberSupplementarySignInMapper) {
		this.setDao(memberSupplementarySignInMapper);
		this.memberSupplementarySignInMapper = memberSupplementarySignInMapper;
	}
	/**
	 * 我领取的邀请得补签任务
	 * @param memberId
	 * @param currentPage 
	 * @param pageSize 
	 * @return
	 */
	public Map<String, Object> getMySupplementarySignTaskList(Integer memberId, Integer currentPage, Integer pageSize) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
		Date currentDate = new Date();
		Integer memberSupplementaryCardNum = 0;
		if(memberId != null){
			MemberAccount account = memberAccountService.findAccountByMemberId(memberId);
			memberSupplementaryCardNum = account.getSupplementaryCard() == null ? 0 : account.getSupplementaryCard();
		}
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("memberId", memberId);
		paramsMap.put("currentPage", currentPage * pageSize);
		paramsMap.put("pageSize", pageSize);
		List<MemberSupplementarySignInCustom> customs = memberSupplementarySignInCustomMapper.getMySupplementarySignTaskList(paramsMap);
		if(CollectionUtils.isNotEmpty(customs)){
			for (MemberSupplementarySignInCustom model : customs) {
				Map<String, Object> dataMap = new HashMap<String, Object>();
				Integer invitationCount = model.getInvitationCount() == null ? 0 : model.getInvitationCount();//已邀请人数
				Integer settingInvitationCount = model.getSettingInvitationCount() == null ? 0 : model.getSettingInvitationCount();//设置的需邀请人数
				Integer memberSupplementarySignInId = model.getId();
				Date beginTime = model.getBeginTime();
				Date endTime = model.getEndTime();
				String taskStatus = "0";//0邀请中 1 失败 2 完成
				String content = "需邀请"+settingInvitationCount+"，还剩"+(settingInvitationCount-invitationCount)+"人";
				if(invitationCount >= settingInvitationCount){
					taskStatus = "2";
					content = "已完成邀请，补签卡已发放";
				}else if(currentDate.compareTo(endTime) >= 0){
					taskStatus = "1";
				}
				dataMap.put("memberSupplementarySignInId", memberSupplementarySignInId);
				dataMap.put("beginTime", beginTime);
				dataMap.put("endTime", endTime);
				dataMap.put("taskStatus", taskStatus);
				dataMap.put("settingInvitationCount", settingInvitationCount);
				dataMap.put("needInvitationCount", settingInvitationCount-invitationCount);
				dataMap.put("supplementaryCardCount", model.getSupplementaryCardCount());
				dataMap.put("content", content);
				dataList.add(dataMap);
			}
		}
		map.put("dataList", dataList);
		map.put("memberSupplementaryCardNum", memberSupplementaryCardNum);
		map.put("currentDate", currentDate);
		return map;
	}
	
	/**
	 * 补签分享页面
	 * @param reqDataJson
	 * @param memberId
	 * @return
	 */
	public Map<String, Object> getMySupplementarySignSharePage(JSONObject reqDataJson, MemberInfo memberInfo) {
		Integer memberId = memberInfo.getId();
		String mVerfiyFlag = memberInfo.getmVerfiyFlag();
		Map<String, Object> map = new HashMap<String, Object>();
		Integer memberSupplementarySignInId = reqDataJson.getInt("memberSupplementarySignInId");
		Date currentDate = new Date();
		MemberSupplementarySignIn memberSupplementarySignIn = selectByPrimaryKey(memberSupplementarySignInId);
		SupplementarySignInSetting supplementarySignInSetting = supplementarySignInSettingService.selectByPrimaryKey(memberSupplementarySignIn.getSupplementarySignInSettingId());
		Integer sourceMemberId = memberSupplementarySignIn.getMemberId();
		Integer invitationCount = memberSupplementarySignIn.getInvitationCount() == null ? 0 : memberSupplementarySignIn.getInvitationCount();
		Integer settingInvitationCount = supplementarySignInSetting.getInvitationCount();
		Date beginTime = memberSupplementarySignIn.getBeginTime();
		Date endTime = memberSupplementarySignIn.getEndTime();
		String status = "";//1:本人进入 2：好友助力
		if(sourceMemberId.equals(memberId)){
			//本人进来
			status = "1";
			mVerfiyFlag = "1";
			String taskStatus = "0";//0邀请中 1 失败 2 完成
			String content = "";
			if(invitationCount >= settingInvitationCount){
				taskStatus = "2";
			}else if(currentDate.compareTo(endTime) >= 0){
				taskStatus = "1";
			}
			List<Map<String, Object>> dataList = assistanceDtlService.getMyAssistanceDtlList(reqDataJson,sourceMemberId);
			map.put("dataList", dataList);
			if("0".equals(taskStatus) || "1".equals(taskStatus)){
				content = "已邀请"+invitationCount+"位好友，还需邀请"+(settingInvitationCount-invitationCount)+"位好友";
			}else{
				content = "已完成邀请，补签卡已发放";
			}
			map.put("taskStatus", taskStatus);
			map.put("content", content);
		}else{
			//好友助力
			status = "2";
			List<Map<String, Object>> cumulativeAwardList = new ArrayList<Map<String, Object>>();
			Map<String, String> assistanceStatusMap = assistanceDtlService.getMemberAssistanceStatus(invitationCount,settingInvitationCount,beginTime,endTime,memberId,memberSupplementarySignInId);//获取会员助力状态
			//累签宝箱
			Calendar cal = Calendar.getInstance();
			int year = cal.get(Calendar.YEAR);
			int month = cal.get(Calendar.MONTH )+1;
			SignInManageExample signInManageExample = new SignInManageExample();
			signInManageExample.createCriteria().andYearEqualTo(year).andMonthEqualTo(month).andDelFlagEqualTo("0");
			List<SignInManage> inManages = signInManageService.selectByExample(signInManageExample);
			if(CollectionUtils.isNotEmpty(inManages)){
				/**获取当月累签奖励配置*/
				cumulativeAwardList = cumulativeSignInSettingService.getCumulativeAwardList(memberId,inManages.get(0).getId(),0);
			}
			map.put("cumulativeSignInSettingList", cumulativeAwardList);
			map.put("memberAssistanceStatus", assistanceStatusMap.get("memberAssistanceStatus"));
			map.put("memberAssistanceStatusStr", assistanceStatusMap.get("memberAssistanceStatusStr"));
		}
		map.put("currentDate", currentDate);
		map.put("beginTime", beginTime);
		map.put("endTime", endTime);
		map.put("status", status);
		map.put("currentMemberId", memberId);
		map.put("sourceMemberId", sourceMemberId);
		map.put("mVerfiyFlag", mVerfiyFlag);
		return map;
	}
	
	public void addSupplementarySignTask(Integer memberId, JSONObject reqDataJson) {
		Integer supplementarySignInSettingId = reqDataJson.getInt("supplementarySignInSettingId");
		Date currentDate = new Date();
		String receiveDateStr = DateUtil.getFormatDate(currentDate, "yyyyMMdd");
		MemberSupplementarySignInExample memberSupplementarySignInExample = new MemberSupplementarySignInExample();
		memberSupplementarySignInExample.createCriteria().andMemberIdEqualTo(memberId).andSupplementarySignInSettingIdEqualTo(supplementarySignInSettingId)
		.andReceiveDateStrEqualTo(receiveDateStr).andDelFlagEqualTo("0");
		List<MemberSupplementarySignIn> memberSupplementarySignIns = selectByExample(memberSupplementarySignInExample);
		if(CollectionUtils.isNotEmpty(memberSupplementarySignIns)){
			throw new ArgException("同一个任务当天只能领取一次");
		}
		MemberSupplementarySignIn memberSupplementarySignIn = new MemberSupplementarySignIn();
		memberSupplementarySignIn.setSupplementarySignInSettingId(supplementarySignInSettingId);
		memberSupplementarySignIn.setMemberId(memberId);
		memberSupplementarySignIn.setBeginTime(currentDate);
		memberSupplementarySignIn.setEndTime(DateUtil.addDay(currentDate, 1));
		memberSupplementarySignIn.setReceiveDateStr(receiveDateStr);
		memberSupplementarySignIn.setStatus("1");
		memberSupplementarySignIn.setCreateBy(memberId);
		memberSupplementarySignIn.setCreateDate(currentDate);
		memberSupplementarySignIn.setDelFlag("0");;
		insertSelective(memberSupplementarySignIn);
	}
	
	
}
