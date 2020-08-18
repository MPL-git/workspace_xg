package com.jf.service;

import com.jf.common.constant.Const;
import com.jf.common.ext.exception.BizException;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.ActivityAuthMapper;
import com.jf.entity.ActivityAuth;
import com.jf.entity.ActivityAuthExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ActivityAuthService {

	@Autowired
	private ActivityAuthMapper dao;

	public ActivityAuth findById(int id){
		return dao.selectByPrimaryKey(id);
	}

	public ActivityAuth findByActivityId(int activityId){
		QueryObject queryObject = new QueryObject(1,1);
		queryObject.addQuery("delFlag", Const.FLAG_FALSE);
		queryObject.addQuery("activityId", activityId);
		List<ActivityAuth> list = list(queryObject);
		return list.size() > 0 ? list.get(0) : null;
	}

	public ActivityAuth save(ActivityAuth model){
		model.setCreateDate(new Date());
		dao.insertSelective(model);
		return model;
	}

	public ActivityAuth update(ActivityAuth model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		ActivityAuth model = findById(id);
		if (model == null || model.getDelFlag().equals(Const.FLAG_TRUE)) {
			throw new BizException("ID为[" + id + "]的数据不存在");
		}
		model.setDelFlag(Const.FLAG_TRUE);
		update(model);
	}

	public int count(QueryObject queryObject) {
		return dao.countByExample(builderQuery(queryObject));
	}

	public List<ActivityAuth> list(QueryObject queryObject) {
		return dao.selectByExample(builderQuery(queryObject));
	}

	public Page<ActivityAuth> paginate(QueryObject queryObject) {
		ActivityAuthExample example = builderQuery(queryObject);
		example.setLimitStart(queryObject.getLimitStart());
		example.setLimitSize(queryObject.getPageSize());
		List<ActivityAuth> list = dao.selectByExample(example);
		int totalCount = dao.countByExample(example);
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

	private ActivityAuthExample builderQuery(QueryObject queryObject) {
		ActivityAuthExample example = new ActivityAuthExample();
		ActivityAuthExample.Criteria criteria = example.createCriteria();
		if(queryObject.getQuery("delFlag") != null){
			criteria.andDelFlagEqualTo(queryObject.getQueryToStr("delFlag"));
		}
		if(queryObject.getQuery("id") != null){
			criteria.andIdEqualTo(queryObject.getQueryToInt("id"));
		}
		if(queryObject.getQuery("activityId") != null){
			criteria.andActivityIdEqualTo(queryObject.getQueryToInt("activityId"));
		}
		if(queryObject.getSort().size() > 0){
			example.setOrderByClause(queryObject.getSortString());
		}
		return example;
	}
}
