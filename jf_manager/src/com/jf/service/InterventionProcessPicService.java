package com.jf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.InterventionProcessPicMapper;
import com.jf.entity.InterventionProcessPic;
import com.jf.entity.InterventionProcessPicExample;

@Service
@Transactional
public class InterventionProcessPicService extends BaseService<InterventionProcessPic, InterventionProcessPicExample> {

	@Autowired
	private InterventionProcessPicMapper interventionProcessPicMapper;
	
	@Autowired
	public void setInterventionProcessPicMapper(InterventionProcessPicMapper interventionProcessPicMapper) {
		super.setDao(interventionProcessPicMapper);
		this.interventionProcessPicMapper = interventionProcessPicMapper;
	}
	
}
