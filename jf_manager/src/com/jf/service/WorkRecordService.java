package com.jf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.WorkRecordMapper;
import com.jf.entity.WorkRecord;
import com.jf.entity.WorkRecordExample;

@Service
@Transactional
public class WorkRecordService extends BaseService<WorkRecord, WorkRecordExample> {
	@Autowired
	private WorkRecordMapper workRecordMapper;

	@Autowired
	public void setworkRecordMapper(WorkRecordMapper workRecordMapper) {
		super.setDao(workRecordMapper);
		this.workRecordMapper = workRecordMapper;
	}
}
