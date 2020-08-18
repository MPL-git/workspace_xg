package com.jf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.ApprovalApplicationPicMapper;
import com.jf.entity.ApprovalApplicationPic;
import com.jf.entity.ApprovalApplicationPicExample;

@Service
@Transactional
public class ApprovalApplicationPicService extends BaseService<ApprovalApplicationPic, ApprovalApplicationPicExample> {
	
	@Autowired
	private ApprovalApplicationPicMapper approvalApplicationPicMapper;
	
	
	@Autowired
	public void setApprovalApplicationPicMapper(ApprovalApplicationPicMapper approvalApplicationPicMapper) {
		super.setDao(approvalApplicationPicMapper);
		this.approvalApplicationPicMapper = approvalApplicationPicMapper;
	}
	

}
