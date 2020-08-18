package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.MemberNovaRecordCustomMapper;
import com.jf.dao.MemberNovaRecordMapper;
import com.jf.entity.MemberNovaRecord;
import com.jf.entity.MemberNovaRecordCustom;
import com.jf.entity.MemberNovaRecordExample;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class MemberNovaRecordService extends BaseService<MemberNovaRecord, MemberNovaRecordExample> {
	@Autowired
	private MemberNovaRecordMapper memberNovaRecordMapper;
	@Autowired
	private MemberNovaRecordCustomMapper memberNovaRecordCustomMapper;
	@Autowired
	public void setMemberNovaRecordMapper(MemberNovaRecordMapper memberNovaRecordMapper) {
		this.setDao(memberNovaRecordMapper);
		this.memberNovaRecordMapper = memberNovaRecordMapper;
	}
	public List<MemberNovaRecordCustom> getMemberRenewalProgressLog(Map<String, Object> paramsMap) {
		
		return memberNovaRecordCustomMapper.getMemberRenewalProgressLog(paramsMap);
	}
	public MemberNovaRecord getCurrentContractSigning(Integer memberId, String oprnType) {
		MemberNovaRecord memberNovaRecord = null;
		Date currentDate = new Date();
		MemberNovaRecordExample memberNovaRecordExample = new MemberNovaRecordExample();
		MemberNovaRecordExample.Criteria criteria = memberNovaRecordExample.createCriteria();
		criteria.andMemberIdEqualTo(memberId).andDelFlagEqualTo("0");
		if("1".equals(oprnType)){
			criteria.andAgreementBeginDateLessThanOrEqualTo(currentDate).andAgreementEndDateGreaterThanOrEqualTo(currentDate);
		}else{
			memberNovaRecordExample.setOrderByClause("id desc");
			memberNovaRecordExample.setLimitStart(0);
			memberNovaRecordExample.setLimitSize(1);
		}
		List<MemberNovaRecord> memberNovaRecords = selectByExample(memberNovaRecordExample);
		if(CollectionUtils.isNotEmpty(memberNovaRecords)){
			memberNovaRecord = memberNovaRecords.get(0);
		}
		return memberNovaRecord;
	}
	
	
}
