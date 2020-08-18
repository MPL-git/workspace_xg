package com.jf.service;

import com.jf.common.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.InterventionOrderMapper;
import com.jf.entity.InterventionOrder;
import com.jf.entity.InterventionOrderExample;

/**
  * @author  chenwf: 
  * @date 创建时间：2018年4月3日 上午9:21:48 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class InterventionOrderService extends BaseService<InterventionOrder, InterventionOrderExample> {
	
	@Autowired
	private InterventionOrderMapper interventionOrderMapper;

	@Autowired
	public void setInterventionOrderMapper(InterventionOrderMapper interventionOrderMapper) {
		this.setDao(interventionOrderMapper);
		this.interventionOrderMapper = interventionOrderMapper;
	}
	
}
