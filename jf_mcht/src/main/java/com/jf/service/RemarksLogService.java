package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.RemarksLogCustomMapper;
import com.jf.dao.RemarksLogMapper;
import com.jf.entity.RemarksLog;
import com.jf.entity.RemarksLogExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RemarksLogService extends BaseService<RemarksLog,RemarksLogExample> {
	@Autowired
	private RemarksLogMapper dao;
	
	@Autowired
	private RemarksLogCustomMapper remarksLogCustomMapper;
	
	@Autowired
	public void setRemarksLogMapper(RemarksLogMapper remarksLogMapper) {
		super.setDao(remarksLogMapper);
		this.dao = remarksLogMapper;
	}

	public RemarksLog getRemarksLogBySubOrderId(Integer subOrderId){
		return remarksLogCustomMapper.getRemarksLogBySubOrderId(subOrderId);
	}
}
