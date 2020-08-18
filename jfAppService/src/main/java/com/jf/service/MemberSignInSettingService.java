package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.common.utils.DateUtil;
import com.jf.dao.MemberSignInSettingMapper;
import com.jf.entity.MemberInfo;
import com.jf.entity.MemberNovaRecord;
import com.jf.entity.MemberSignInSetting;
import com.jf.entity.MemberSignInSettingExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
public class MemberSignInSettingService extends BaseService<MemberSignInSetting, MemberSignInSettingExample> {
	@Autowired
	private MemberSignInSettingMapper memberSignInSettingMapper;
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
	 * 获取用户当天是否签到
	 * @param memberId 
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public Integer getMemberSignInCount(Integer memberId) { 
		Integer memberSignInCount = 0;
		Date currentDate = new Date();
		String signInDateStr = DateUtil.getFormatDate(currentDate, "yyyyMMdd");
		if(memberId != null){
			MemberSignInSettingExample memberSignInSettingExample = new MemberSignInSettingExample();
			memberSignInSettingExample.createCriteria().andMemberIdEqualTo(memberId).andSignInDateStrEqualTo(signInDateStr).andDelFlagEqualTo("0");
			memberSignInCount = countByExample(memberSignInSettingExample);
		}
		return memberSignInCount;
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
			MemberNovaRecord memberNovaRecord = memberNovaRecordService.getCurrentContractSigning(memberId,openType);
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
	
}
