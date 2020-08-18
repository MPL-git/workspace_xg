package com.jf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.jf.dao.MemberStatusLogMapper;
import com.jf.entity.MemberStatusLog;
import com.jf.entity.MemberStatusLogExample;

@Service
@Transactional
public class MemberStatusLogService extends BaseService<MemberStatusLog,MemberStatusLogExample> {
	@Autowired
	private MemberStatusLogMapper memberStatusLogMapper;
	
	@Autowired
	public void setmemberStatusLogMapper(MemberStatusLogMapper memberStatusLogMapper) {
		super.setDao(memberStatusLogMapper);
		this.memberStatusLogMapper = memberStatusLogMapper;
	}
	
}
