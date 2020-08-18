package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.InterventionOrderLogMapper;
import com.jf.entity.InterventionOrderLog;
import com.jf.entity.InterventionOrderLogExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
  * @author  chenwf: 
  * @date 创建时间：2018年4月3日 上午9:19:55 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class InterventionOrderLogService extends BaseService<InterventionOrderLog, InterventionOrderLogExample> {
	
	@Autowired
	private InterventionOrderLogMapper interventionOrderLogMapper;

	@Autowired
	public void setInterventionOrderLogMapper(InterventionOrderLogMapper interventionOrderLogMapper) {
		this.setDao(interventionOrderLogMapper);
		this.interventionOrderLogMapper = interventionOrderLogMapper;
	}
	
	
}
