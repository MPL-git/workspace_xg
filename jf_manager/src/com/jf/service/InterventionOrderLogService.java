package com.jf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.InterventionOrderLogCustomMapper;
import com.jf.dao.InterventionOrderLogMapper;
import com.jf.entity.InterventionOrderLog;
import com.jf.entity.InterventionOrderLogCustom;
import com.jf.entity.InterventionOrderLogExample;

@Service
@Transactional
public class InterventionOrderLogService extends BaseService<InterventionOrderLog, InterventionOrderLogExample> {

	@Autowired
	private InterventionOrderLogMapper interventionOrderLogMapper;
	
	@Autowired
	private InterventionOrderLogCustomMapper interventionOrderLogCustomMapper;
	
	@Autowired
	public void setInterventionOrderLogMapper(InterventionOrderLogMapper interventionOrderLogMapper) {
		super.setDao(interventionOrderLogMapper);
		this.interventionOrderLogMapper = interventionOrderLogMapper;
	}
	
	public int countByCustomExample(InterventionOrderLogExample example) {
		return interventionOrderLogCustomMapper.countByCustomExample(example);
	}

	public List<InterventionOrderLogCustom> selectByCustomExample(InterventionOrderLogExample example) {
		return interventionOrderLogCustomMapper.selectByCustomExample(example);
	}

	public InterventionOrderLogCustom selectByPrimaryKeyCustom(Integer id) {
		return interventionOrderLogCustomMapper.selectByPrimaryKeyCustom(id);
	}
	
}
