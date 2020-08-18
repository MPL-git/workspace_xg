package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.InterventionProcessMapper;
import com.jf.entity.InterventionProcess;
import com.jf.entity.InterventionProcessExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
  * @author  chenwf: 
  * @date 创建时间：2018年4月3日 上午9:24:39 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class InterventionProcessService extends BaseService<InterventionProcess, InterventionProcessExample> {
	
	@Autowired
	private InterventionProcessMapper interventionProcessMapper;

	@Autowired
	public void setInterventionProcessMapper(InterventionProcessMapper interventionProcessMapper) {
		this.setDao(interventionProcessMapper);
		this.interventionProcessMapper = interventionProcessMapper;
	}
	
}
