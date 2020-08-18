package com.jf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.common.base.BaseService;
import com.jf.dao.MemberPvMiddleLogMapper;
import com.jf.entity.MemberPvMiddleLog;
import com.jf.entity.MemberPvMiddleLogExample;

@Service
@Transactional
public class MemberPvMiddleLogSerivce extends BaseService<MemberPvMiddleLog, MemberPvMiddleLogExample> {

	@Autowired
	private MemberPvMiddleLogMapper memberPvMiddleLogMapper;
	
	@Autowired
	public void setMemberPvMiddleLogMapper(MemberPvMiddleLogMapper memberPvMiddleLogMapper) {
		super.setDao(memberPvMiddleLogMapper);
		this.memberPvMiddleLogMapper = memberPvMiddleLogMapper;
	}
	
	
}
