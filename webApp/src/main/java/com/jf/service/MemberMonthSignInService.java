package com.jf.service;

import java.util.Date;

import com.jf.common.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.common.utils.DateUtil;
import com.jf.dao.MemberMonthSignInMapper;
import com.jf.entity.MemberMonthSignIn;
import com.jf.entity.MemberMonthSignInExample;

@Service
@Transactional
public class MemberMonthSignInService extends BaseService<MemberMonthSignIn, MemberMonthSignInExample> {
	@Autowired
	private MemberMonthSignInMapper memberMonthSignInMapper;
	@Autowired
	private MemberInfoService memberInfoService;
	@Autowired
	public void setMemberMonthSignInMapper(MemberMonthSignInMapper memberMonthSignInMapper) {
		this.setDao(memberMonthSignInMapper);
		this.memberMonthSignInMapper = memberMonthSignInMapper;
	}
	
	public boolean getMemberIsSign(Integer memberId) {
		Date currentDate = new Date();
		Date beginDate = DateUtil.getDateAfterAndBeginTime(currentDate, 0);
		Date endDate = DateUtil.getDateAfterAndEndTime(currentDate, 0);
		MemberMonthSignInExample memberMonthSignInExample = new MemberMonthSignInExample();
		memberMonthSignInExample.createCriteria().andMemberIdEqualTo(memberId).andCreateDateGreaterThanOrEqualTo(beginDate)
		.andCreateDateLessThanOrEqualTo(endDate).andDelFlagEqualTo("0");
		Integer count = countByExample(memberMonthSignInExample);
		return count > 0 ? true : false;
	}
	
	
}
