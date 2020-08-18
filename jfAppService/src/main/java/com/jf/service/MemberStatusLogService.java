package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.MemberStatusLogMapper;
import com.jf.entity.MemberStatusLog;
import com.jf.entity.MemberStatusLogExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
public class MemberStatusLogService extends BaseService<MemberStatusLog, MemberStatusLogExample> {
	@Autowired
	private MemberStatusLogMapper memberStatusLogMapper;
	@Autowired
	public void setMemberStatusLogMapper(MemberStatusLogMapper memberStatusLogMapper) {
		this.setDao(memberStatusLogMapper);
		this.memberStatusLogMapper = memberStatusLogMapper;
	}
	/**
	 * 添加会员状态记录
	 * @param memberId
	 * @param status
	 */
	public void addMemberRegisterStatus(Integer memberId,String status) {
		MemberStatusLog log = new MemberStatusLog();
		log.setMemberId(memberId);
		log.setStatus(status);
		log.setCreateBy(memberId);
		log.setCreateDate(new Date());
		log.setDelFlag("0");
		insertSelective(log);
	}
	
	
}
