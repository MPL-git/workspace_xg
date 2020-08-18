package com.jf.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.InterventionOrderStatusLogMapper;
import com.jf.entity.InterventionOrderStatusLog;
import com.jf.entity.InterventionOrderStatusLogExample;

@Service
@Transactional
public class InterventionOrderStatusLogService extends BaseService<InterventionOrderStatusLog, InterventionOrderStatusLogExample> {

	@Autowired
	private InterventionOrderStatusLogMapper interventionOrderStatusLogMapper;
	
	@Autowired
	public void setInterventionOrderStatusLogMapper(InterventionOrderStatusLogMapper interventionOrderStatusLogMapper) {
		super.setDao(interventionOrderStatusLogMapper);
		this.interventionOrderStatusLogMapper = interventionOrderStatusLogMapper;
	}
	
	
	
}
