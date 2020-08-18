package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.MemberAccountMapper;
import com.jf.entity.MemberAccount;
import com.jf.entity.MemberAccountExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MemberAccountService extends BaseService<MemberAccount,MemberAccountExample> {
	@Autowired
	private MemberAccountMapper memberAccountMapper;
	
	@Autowired
	public void setMemberAccountMapper(MemberAccountMapper memberAccountMapper) {
		super.setDao(memberAccountMapper);
		this.memberAccountMapper = memberAccountMapper;
	}

}
