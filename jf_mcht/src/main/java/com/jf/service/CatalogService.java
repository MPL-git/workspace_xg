package com.jf.service;

import com.jf.common.constant.Const;
import com.jf.common.ext.exception.BizException;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.CatalogMapper;
import com.jf.entity.Catalog;
import com.jf.entity.CatalogExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class CatalogService {

	@Autowired
	private CatalogMapper dao;

	public Catalog findById(int id){
		return dao.selectByPrimaryKey(id);
	}

	public Catalog save(Catalog model){
		model.setCreateDate(new Date());
		dao.insertSelective(model);
		return model;
	}

	public Catalog update(Catalog model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKeySelective(model);
		return model;
	}

	public void delete(int id){
		Catalog model = findById(id);
		if (model == null || model.getDelFlag().equals(Const.FLAG_TRUE)) {
			throw new BizException("ID为[" + id + "]的数据不存在");
		}
		model.setDelFlag(Const.FLAG_TRUE);
		update(model);
	}

	public int count(QueryObject queryObject) {
		return dao.countByExample(builderQuery(queryObject));
	}

	public List<Catalog> findList(QueryObject queryObject) {
		return dao.selectByExample(builderQuery(queryObject));
	}

	public Page<Catalog> paginate(QueryObject queryObject) {
		CatalogExample example = builderQuery(queryObject);
		example.setLimitStart(queryObject.getLimitStart());
		example.setLimitSize(queryObject.getPageSize());

		List<Catalog> list = dao.selectByExample(example);
		int totalCount = dao.countByExample(example);
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

	private CatalogExample builderQuery(QueryObject queryObject) {
		CatalogExample example = new CatalogExample();
		CatalogExample.Criteria criteria = example.createCriteria();
		criteria.andDelFlagEqualTo(Const.FLAG_FALSE);
		criteria.andStatusEqualTo("1");
		if(queryObject.getQuery("id") != null){
			criteria.andIdEqualTo(queryObject.getQueryToInt("id"));
		}
		if(queryObject.getQuery("ids") != null){
			List<Integer> ids = queryObject.getQuery("ids");
			criteria.andIdIn(ids);
		}
		if(queryObject.getQuery("parentId") != null){
			criteria.andParentIdEqualTo(queryObject.getQueryToInt("parentId"));
		}
		if(queryObject.getSort().size() > 0){
			example.setOrderByClause(queryObject.getSortString());
		}
		return example;
	}
}
