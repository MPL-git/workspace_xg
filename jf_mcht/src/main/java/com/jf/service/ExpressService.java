package com.jf.service;

import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.ExpressMapper;
import com.jf.entity.Express;
import com.jf.entity.ExpressExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ExpressService {

	@Autowired
	private ExpressMapper dao;

	public Express findById(int id){
		return dao.selectByPrimaryKey(id);
	}

	public List<Express> findList(QueryObject queryObject) {
		return dao.selectByExample(builderQuery(queryObject));
	}

	public Page<Express> paginate(QueryObject queryObject) {
		ExpressExample example = builderQuery(queryObject);
		example.setLimitStart(queryObject.getLimitStart());
		example.setLimitSize(queryObject.getPageSize());

		List<Express> list = dao.selectByExample(example);
		int totalCount = dao.countByExample(example);
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

	private ExpressExample builderQuery(QueryObject queryObject) {
		ExpressExample example = new ExpressExample();
		ExpressExample.Criteria criteria = example.createCriteria();
		if(queryObject.getQuery("delFlag") != null){
			criteria.andDelFlagEqualTo(queryObject.getQueryToStr("delFlag"));
		}
		if(queryObject.getQuery("id") != null){
			criteria.andIdEqualTo(queryObject.getQueryToInt("id"));
		}
		if(queryObject.getSort().size() > 0){
			example.setOrderByClause(queryObject.getSortString());
		}
		return example;
	}
}
