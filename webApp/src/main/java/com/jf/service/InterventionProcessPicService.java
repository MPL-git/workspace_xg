package com.jf.service;

import com.jf.common.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.InterventionProcessPicMapper;
import com.jf.entity.InterventionProcessPic;
import com.jf.entity.InterventionProcessPicExample;

/**
  * @author  chenwf: 
  * @date 创建时间：2018年4月3日 上午9:28:18 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class InterventionProcessPicService extends BaseService<InterventionProcessPic, InterventionProcessPicExample> {
	
	@Autowired
	private InterventionProcessPicMapper interventionProcessPicMapper;

	@Autowired
	public void setInterventionProcessPicMapper(InterventionProcessPicMapper interventionProcessPicMapper) {
		this.setDao(interventionProcessPicMapper);
		this.interventionProcessPicMapper = interventionProcessPicMapper;
	}
	
}
