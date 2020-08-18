package com.jf.service;

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
import com.jf.common.utils.StringUtil;
import com.jf.dao.AssistanceDtlCustomMapper;
import com.jf.dao.AssistanceDtlMapper;
import com.jf.entity.AssistanceDtl;
import com.jf.entity.AssistanceDtlCustom;
import com.jf.entity.AssistanceDtlExample;
import com.jf.entity.MemberAccount;
import com.jf.entity.MemberInfo;
import com.jf.entity.MemberSupplementarySignIn;
import com.jf.entity.MemberSupplementarySignInExample;
import com.jf.entity.SupplementarySignInSetting;

import net.sf.json.JSONObject;

@Service
@Transactional
public class AssistanceDtlService extends BaseService<AssistanceDtl, AssistanceDtlExample> {
	
	@Autowired
	private AssistanceDtlMapper assistanceDtlMapper;
	@Autowired
	private AssistanceDtlCustomMapper assistanceDtlCustomMapper;
	@Autowired
	private MemberInfoService memberInfoService;
	@Autowired
	private MemberSupplementarySignInService memberSupplementarySignInService;
	@Autowired
	private SupplementarySignInSettingService supplementarySignInSettingService;
	@Autowired
	private MemberAccountService memberAccountService;
	@Autowired
	public void setAssistanceDtlMapper(AssistanceDtlMapper assistanceDtlMapper) {
		this.setDao(assistanceDtlMapper);
		this.assistanceDtlMapper = assistanceDtlMapper;
	}
	/**
	 * 助力详情
	 * @param reqDataJson
	 * @param memberId
	 * @return
	 */
	public List<Map<String, Object>> getMyAssistanceDtlList(JSONObject reqDataJson, Integer memberId) {
		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
		Integer memberSupplementarySignInId = reqDataJson.getInt("memberSupplementarySignInId");
		List<AssistanceDtlCustom> dtlCustoms = assistanceDtlCustomMapper.getMyAssistanceDtlList(memberSupplementarySignInId);
		if(CollectionUtils.isNotEmpty(dtlCustoms)){
			for (AssistanceDtlCustom assistanceDtlCustom : dtlCustoms) {
				Map<String, Object> dataMap = new HashMap<String, Object>();
				String proStatusStr = "等待下载APP助力";
				if("1".equals(assistanceDtlCustom.getProStatus())){
					proStatusStr = "助力成功";
				}
				dataMap.put("nick", assistanceDtlCustom.getNick());
				dataMap.put("weixinHead", StringUtil.getPic(assistanceDtlCustom.getWeixinHead(), ""));
				dataMap.put("proStatusStr", proStatusStr);
				dataList.add(dataMap);
			}
		}
		return dataList;
	}
	public Map<String, String> getMemberAssistanceStatus(Integer invitationCount, Integer settingInvitationCount,
			Date beginTime, Date endTime, Integer memberId, Integer memberSupplementarySignInId) {
		//invitationCount 已邀请好友人数  settingInvitationCount设置的需邀请人数
		String memberAssistanceStatus = "";//1立即助力
		String memberAssistanceStatusStr = "";
		String errMsg = "";
		Date currentDate = new Date();
		boolean isNewEnjoy = memberInfoService.getIsNewEnjoy(memberId, null);
		if(isNewEnjoy){
			if(invitationCount < settingInvitationCount){
				if(currentDate.compareTo(beginTime) >= 0 && currentDate.compareTo(endTime) < 0){
					AssistanceDtlExample assistanceDtlExample = new AssistanceDtlExample();
					assistanceDtlExample.createCriteria().andMemberIdEqualTo(memberId).andMemberSupplementarySignInIdEqualTo(memberSupplementarySignInId).andDelFlagEqualTo("0");
					List<AssistanceDtl> assistanceDtls = selectByExample(assistanceDtlExample);
					if(CollectionUtils.isNotEmpty(assistanceDtls)){
						AssistanceDtl assistanceDtl = assistanceDtls.get(0);
						if("1".equals(assistanceDtl.getProStatus())){
							memberAssistanceStatus = "4";
							memberAssistanceStatusStr = "已助力完成";
							errMsg = "邀请已助力完成";
						}else{
							memberAssistanceStatus = "2";
							memberAssistanceStatusStr = "前往下载登入";
							errMsg = "请前往下载登陆，为好友助力";
						}
					}else{
						memberAssistanceStatus = "1";
						memberAssistanceStatusStr = "立即助力";
					}
				}else{
					memberAssistanceStatus = "5";
					memberAssistanceStatusStr = "任务已超时";
					errMsg = "任务已超时";
				}
			}else{
				memberAssistanceStatus = "3";
				memberAssistanceStatusStr = "已助力成功";
				errMsg = "已助力成功";
			}
		}else{
			memberAssistanceStatus = "6";
			memberAssistanceStatusStr = "签到领礼包";
			errMsg = "只有新用户才能参与助力哦";
		}
		Map<String, String> map = new HashMap<String, String>();
		map.put("memberAssistanceStatus", memberAssistanceStatus);
		map.put("memberAssistanceStatusStr", memberAssistanceStatusStr);
		map.put("errMsg", errMsg);
		return map;
	}
	
	public void addMemberAssistanceDtl(MemberInfo memberInfo, JSONObject reqDataJson, JSONObject reqPRM) {
		Integer memberId = memberInfo.getId();
		MemberSupplementarySignIn memberSupplementarySignIn = null;;
		Integer memberSupplementarySignInId = reqDataJson.getInt("memberSupplementarySignInId");
		MemberSupplementarySignInExample memberSupplementarySignInExample = new MemberSupplementarySignInExample();
		memberSupplementarySignInExample.createCriteria().andIdEqualTo(memberSupplementarySignInId).andDelFlagEqualTo("0");
		List<MemberSupplementarySignIn> memberSupplementarySignIns = memberSupplementarySignInService.selectByExample(memberSupplementarySignInExample);
		if(CollectionUtils.isNotEmpty(memberSupplementarySignIns)){
			memberSupplementarySignIn = memberSupplementarySignIns.get(0);
		}else{
			throw new ArgException("邀请已过期");
		}
		SupplementarySignInSetting supplementarySignInSetting = supplementarySignInSettingService.selectByPrimaryKey(memberSupplementarySignIn.getSupplementarySignInSettingId());
		if(supplementarySignInSetting == null || supplementarySignInSetting.getId() == null){
			throw new ArgException("邀请已过期");
		}
		Map<String, String> assistanceStatusMap = getMemberAssistanceStatus(memberSupplementarySignIn.getInvitationCount(),
				supplementarySignInSetting.getInvitationCount(), memberSupplementarySignIn.getBeginTime(),
				memberSupplementarySignIn.getEndTime(), memberId, memberSupplementarySignInId);
		if(!"1".equals(assistanceStatusMap.get("memberAssistanceStatus"))){
			throw new ArgException(assistanceStatusMap.get("errMsg"));
		}
		AssistanceDtlExample assistanceDtlExample = new AssistanceDtlExample(); 
		assistanceDtlExample.createCriteria().andMemberIdEqualTo(memberId).andMemberSupplementarySignInIdEqualTo(memberSupplementarySignInId).andDelFlagEqualTo("0");
		List<AssistanceDtl> assistanceDtls = selectByExample(assistanceDtlExample);
		if(CollectionUtils.isNotEmpty(assistanceDtls)){
			throw new ArgException("您已助力过，请勿重复助力");
		}
		String system = reqPRM.getString("system");
		String proStatus = "0";
		if(Const.WX_XCX.equals(system)){
			if(!"1".equals(memberInfo.getmVerfiyFlag()) || StringUtil.isBlank(memberInfo.getMobile())){
				throw new ArgException("您未绑定手机，请先绑定手机");
			}else{
				proStatus = "1";
				Integer invitationCountSetting = supplementarySignInSetting.getInvitationCount();
				Integer invitationCount = memberSupplementarySignIn.getInvitationCount() + 1;
				Integer supplementaryCardCount = supplementarySignInSetting.getSupplementaryCardCount();
				if(invitationCountSetting != null){
					if(invitationCountSetting > invitationCount){
						if(CollectionUtils.isNotEmpty(memberSupplementarySignIns)){
							MemberSupplementarySignIn memberSupplementarySignInUpdate = memberSupplementarySignIns.get(0);
							memberSupplementarySignInUpdate.setInvitationCount(memberSupplementarySignInUpdate.getInvitationCount()+1);
							if(memberSupplementarySignInUpdate.getInvitationCount() == invitationCountSetting){
								memberSupplementarySignInUpdate.setStatus("2");
							}
							int count = memberSupplementarySignInService.updateByExampleSelective(memberSupplementarySignInUpdate, memberSupplementarySignInExample);
							if(count <= 0){
								return;
							}
							//赠送补签卡给用户
							if(memberSupplementarySignInUpdate.getInvitationCount() == invitationCountSetting){
								MemberAccount memberAccount = memberAccountService.findAccountByMemberId(memberSupplementarySignInUpdate.getMemberId());
								MemberAccount memberAccountUpdate = new MemberAccount();
								memberAccountUpdate.setId(memberAccount.getId());
								memberAccountUpdate.setSupplementaryCard(memberAccount.getSupplementaryCard()+supplementaryCardCount);
								memberAccountService.updateByPrimaryKeySelective(memberAccountUpdate);
							}
						}
						
					}
				}
			}
		}
		AssistanceDtl assistanceDtl = new AssistanceDtl();
		assistanceDtl.setMemberSupplementarySignInId(memberSupplementarySignInId);
		assistanceDtl.setMemberId(memberId);
		assistanceDtl.setSourceMemberId(memberSupplementarySignIn.getMemberId());
		assistanceDtl.setProStatus(proStatus);
		assistanceDtl.setCreateBy(memberId);
		assistanceDtl.setCreateDate(new Date());
		assistanceDtl.setDelFlag("0");;
		insertSelective(assistanceDtl);
	}
	
	
}
