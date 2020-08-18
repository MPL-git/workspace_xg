package com.jf.service;

import com.jf.common.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.InterventionOrderStatusLogMapper;
import com.jf.entity.InterventionOrderStatusLog;
import com.jf.entity.InterventionOrderStatusLogExample;

/**
  * @author  chenwf: 
  * @date 创建时间：2018年4月3日 上午9:23:11 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class InterventionOrderStatusLogService extends BaseService<InterventionOrderStatusLog, InterventionOrderStatusLogExample> {
	
	@Autowired
	private InterventionOrderStatusLogMapper interventionOrderStatusLogMapper;

	@Autowired
	public void setInterventionOrderStatusLogMapper(InterventionOrderStatusLogMapper interventionOrderStatusLogMapper) {
		this.setDao(interventionOrderStatusLogMapper);
		this.interventionOrderStatusLogMapper = interventionOrderStatusLogMapper;
	}
	
}
