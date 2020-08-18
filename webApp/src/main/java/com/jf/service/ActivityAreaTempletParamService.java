package com.jf.service;

import java.util.List;

import com.jf.common.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.common.constant.Const;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.ActivityAreaTempletParamMapper;
import com.jf.entity.ActivityAreaTempletParam;
import com.jf.entity.ActivityAreaTempletParamExample;

/**
  * @author  chenwf: 
  * @date 创建时间：2017年11月9日 上午9:22:49 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class ActivityAreaTempletParamService extends BaseService<ActivityAreaTempletParam, ActivityAreaTempletParamExample> {
	
	@Autowired
	private ActivityAreaTempletParamMapper activityAreaTempletParamMapper;

	@Autowired
	public void setActivityAreaTempletParamMapper(ActivityAreaTempletParamMapper activityAreaTempletParamMapper) {
		this.setDao(activityAreaTempletParamMapper);
		this.activityAreaTempletParamMapper = activityAreaTempletParamMapper;
	}


	public List<ActivityAreaTempletParam> findListByQuery(QueryObject queryObject) {
		
		return activityAreaTempletParamMapper.selectByExample(builderQuery(queryObject));
	}
	
	public ActivityAreaTempletParamExample builderQuery(QueryObject queryObject) {
		ActivityAreaTempletParamExample example = new ActivityAreaTempletParamExample();
		ActivityAreaTempletParamExample.Criteria criteria = example.createCriteria();
		criteria.andDelFlagEqualTo(Const.FLAG_FALSE);
		if(queryObject.getQuery("id") != null){
			criteria.andIdEqualTo(queryObject.getQueryToInt("id"));
		}
		if(queryObject.getQuery("activityAreaId") != null){
			criteria.andActivityAreaIdEqualTo(queryObject.getQueryToInt("activityAreaId"));
		}
		if(queryObject.getQuery("suffix") != null){
			criteria.andSuffixEqualTo(queryObject.getQueryToStr("suffix"));
		}
		if(queryObject.getQuery("sort") != null){
			example.setOrderByClause(queryObject.getQueryToStr("sort"));
		}
		return example;
	}

	
	
}
