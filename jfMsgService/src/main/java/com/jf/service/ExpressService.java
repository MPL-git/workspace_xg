package com.jf.service;

import com.jf.common.constant.Const;
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

	public Express findByKdnCode(String kdnCode){
		QueryObject queryObject = new QueryObject(1,1);
		queryObject.addQuery("kdnCode", kdnCode);
		List<Express> list = findList(queryObject);
		return list.size() > 0 ? list.get(0) : null;
	}

	public List<Express> findList(QueryObject queryObject) {
		return dao.selectByExample(builderQuery(queryObject));
	}

	private ExpressExample builderQuery(QueryObject queryObject) {
		ExpressExample example = new ExpressExample();
		ExpressExample.Criteria criteria = example.createCriteria();
		criteria.andDelFlagEqualTo(Const.FLAG_FALSE);
		if(queryObject.getQuery("id") != null){
			criteria.andIdEqualTo(queryObject.getQueryToInt("id"));
		}
		if(queryObject.getQuery("kdnCode") != null){
			criteria.andKdnCodeEqualTo(queryObject.getQueryToStr("kdnCode"));
		}
		if(queryObject.getSort().size() > 0){
			example.setOrderByClause(queryObject.getSortString());
		}
		return example;
	}
}
