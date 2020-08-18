package com.jf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.MemberBlackOperateLogCustomMapper;
import com.jf.dao.MemberBlackOperateLogMapper;
import com.jf.entity.MemberBlackOperateLog;
import com.jf.entity.MemberBlackOperateLogCustom;
import com.jf.entity.MemberBlackOperateLogExample;

@Service
@Transactional
public class MemberBlackOperateLogService extends BaseService<MemberBlackOperateLog, MemberBlackOperateLogExample> {
	
	@Autowired
	private MemberBlackOperateLogMapper memberBlackOperateLogMapper;
	
	@Autowired
	private MemberBlackOperateLogCustomMapper customMapper;
	
	@Autowired
	public void setMapper(MemberBlackOperateLogMapper memberBlackOperateLogMapper) {
		super.setDao(memberBlackOperateLogMapper);
		this.memberBlackOperateLogMapper = memberBlackOperateLogMapper;
	}
	
	public Integer countCustomeByExample(MemberBlackOperateLogExample example) {
		return customMapper.countByExample(example);
	}

	public List<MemberBlackOperateLogCustom> selectCustomByExample(MemberBlackOperateLogExample example) {
		return customMapper.selectByExample(example);
	}
	
}
