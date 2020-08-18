package com.jf.service;

import java.util.List;

import com.jf.common.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.common.constant.Const;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.SingleProductActivityCnfMapper;
import com.jf.entity.SingleProductActivityCnf;
import com.jf.entity.SingleProductActivityCnfExample;


@Service
@Transactional
public class SingleProductActivityCnfService extends BaseService<SingleProductActivityCnf, SingleProductActivityCnfExample> {
	
	@Autowired
	private SingleProductActivityCnfMapper singleProductActivityCnfMapper;
	@Autowired
	public void setSingleProductActivityCnfMapper(SingleProductActivityCnfMapper singleProductActivityCnfMapper) {
		this.setDao(singleProductActivityCnfMapper);
		this.singleProductActivityCnfMapper = singleProductActivityCnfMapper;
	}
	public void updateByModel(SingleProductActivityCnf singleProductActivityCnf) {
		
		singleProductActivityCnfMapper.updateByPrimaryKeySelective(singleProductActivityCnf);
	}
	
	public List<SingleProductActivityCnf> findListQuery(QueryObject queryObject) {
		
		return singleProductActivityCnfMapper.selectByExample(builderQuery(queryObject));
	}
	private SingleProductActivityCnfExample builderQuery(QueryObject queryObject) {
		SingleProductActivityCnfExample example = new SingleProductActivityCnfExample();
		SingleProductActivityCnfExample.Criteria criteria = example.createCriteria();
		criteria.andDelFlagEqualTo(Const.FLAG_FALSE);
		if(queryObject.getQuery("activityType") != null){
			criteria.andActivityTypeEqualTo(queryObject.getQueryToStr("activityType"));
		}
		if(queryObject.getQuery("sort") != null){
			example.setOrderByClause(queryObject.getQueryToStr("sort"));
		}
		return example;
	}
}
