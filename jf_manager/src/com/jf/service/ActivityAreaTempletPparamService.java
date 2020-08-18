package com.jf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.ActivityAreaTempletPparamMapper;
import com.jf.dao.SeckillTimeMapper;
import com.jf.entity.ActivityAreaTempletPparam;
import com.jf.entity.ActivityAreaTempletPparamExample;

/**
 * 
 * @ClassName ActivityAreaTempletPparamService
 * @Description TODO(这里用一句话描述这个方法的作用)
 * @author pengl
 * @date 2017年11月9日 上午10:03:51
 */
@Service
@Transactional
public class ActivityAreaTempletPparamService extends BaseService<ActivityAreaTempletPparam, ActivityAreaTempletPparamExample> {

	@Autowired
	private ActivityAreaTempletPparamMapper activityAreaTempletPparamMapper;
	
	@Autowired
	public void setActivityAreaTempletPparamMapper(ActivityAreaTempletPparamMapper activityAreaTempletPparamMapper) {
		super.setDao(activityAreaTempletPparamMapper);
		this.activityAreaTempletPparamMapper = activityAreaTempletPparamMapper;
	}
	
	
}
