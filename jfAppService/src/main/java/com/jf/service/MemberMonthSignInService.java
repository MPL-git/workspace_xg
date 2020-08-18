package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.MemberMonthSignInMapper;
import com.jf.entity.MemberMonthSignIn;
import com.jf.entity.MemberMonthSignInExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	
}
