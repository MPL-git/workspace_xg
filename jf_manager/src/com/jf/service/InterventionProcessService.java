package com.jf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.InterventionProcessMapper;
import com.jf.entity.InterventionProcess;
import com.jf.entity.InterventionProcessExample;

@Service
@Transactional
public class InterventionProcessService extends BaseService<InterventionProcess, InterventionProcessExample> {

	@Autowired
	private InterventionProcessMapper interventionProcessMapper;

	@Autowired
	public void setInterventionProcessMapper(InterventionProcessMapper interventionProcessMapper) {
		super.setDao(interventionProcessMapper);
		this.interventionProcessMapper = interventionProcessMapper;
	}

}
