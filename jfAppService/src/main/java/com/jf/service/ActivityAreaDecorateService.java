package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.ActivityAreaDecorateMapper;
import com.jf.entity.ActivityAreaDecorate;
import com.jf.entity.ActivityAreaDecorateExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
  * @author  chenwf: 
  * @date 创建时间：2018年2月28日 上午10:54:35 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class ActivityAreaDecorateService extends BaseService<ActivityAreaDecorate, ActivityAreaDecorateExample> {
	@Autowired
	private ActivityAreaDecorateMapper activityAreaDecorateMapper;
	
	@Autowired
	public void setActivityAreaDecorateMapper(ActivityAreaDecorateMapper activityAreaDecorateMapper) {
		this.setDao(activityAreaDecorateMapper);
		this.activityAreaDecorateMapper = activityAreaDecorateMapper;
	}
	
	
}
