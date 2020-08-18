package com.jf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.ApprovalApplicationLogCustomMapper;
import com.jf.dao.ApprovalApplicationLogMapper;
import com.jf.entity.ApprovalApplicationLog;
import com.jf.entity.ApprovalApplicationLogCustom;
import com.jf.entity.ApprovalApplicationLogExample;
import com.jf.entity.ApprovalApplicationNodeCustom;
@Service
@Transactional
public class ApprovalApplicationLogService extends BaseService<ApprovalApplicationLog, ApprovalApplicationLogExample> {
	
	@Autowired
	private ApprovalApplicationLogMapper approvalApplicationLogMapper;
	
	@Autowired
	private ApprovalApplicationLogCustomMapper approvalApplicationLogCustomMapper;
	
	
	@Autowired
	public void setApprovalApplicationLogMapper(ApprovalApplicationLogMapper approvalApplicationLogMapper) {
		super.setDao(approvalApplicationLogMapper);
		this.approvalApplicationLogMapper = approvalApplicationLogMapper;
	}
	
	
	public  List<ApprovalApplicationLogCustom> selectCustomByExample(ApprovalApplicationLogExample example){
		return approvalApplicationLogCustomMapper.selectCustomByExample(example);
	};

}
