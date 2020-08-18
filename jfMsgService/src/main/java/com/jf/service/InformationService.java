package com.jf.service;

import java.util.Date;
import java.util.List;

import com.jf.common.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.common.constant.Const;
import com.jf.common.ext.exception.BizException;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.InformationMapper;
import com.jf.entity.Information;
import com.jf.entity.InformationExample;

@Service
@Transactional
public class InformationService extends BaseService<Information,InformationExample> {

	@Autowired
	private InformationMapper informationMapper;
	
	@Autowired
	public void setInformationMapper(InformationMapper informationMapper) {
		super.setDao(informationMapper);
		this.informationMapper = informationMapper;
	}

	public Information findById(int id){
		return informationMapper.selectByPrimaryKey(id);
	}

	public Information save(Information model){
		model.setCreateDate(new Date());
		informationMapper.insertSelective(model);
		return model;
	}

	public Information update(Information model){
		model.setUpdateDate(new Date());
		informationMapper.updateByPrimaryKeySelective(model);
		return model;
	}

	public void delete(int id){
		Information model = findById(id);
		if (model == null || model.getDelFlag().equals(Const.FLAG_TRUE)) {
			throw new BizException("ID为[" + id + "]的数据不存在");
		}
		model.setDelFlag(Const.FLAG_TRUE);
		update(model);
	}

	public int count(QueryObject queryObject) {
		return informationMapper.countByExample(builderQuery(queryObject));
	}

	public List<Information> findList(QueryObject queryObject) {
		return informationMapper.selectByExample(builderQuery(queryObject));
	}

	public Page<Information> paginate(QueryObject queryObject) {
		InformationExample example = builderQuery(queryObject);
		example.setLimitStart(queryObject.getLimitStart());
		example.setLimitSize(queryObject.getPageSize());
		example.setOrderByClause(" release_time desc");
		List<Information> list = informationMapper.selectByExample(example);
		int totalCount = informationMapper.countByExample(example);
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

	private InformationExample builderQuery(QueryObject queryObject) {
		InformationExample example = new InformationExample();
		InformationExample.Criteria criteria = example.createCriteria();
		criteria.andDelFlagEqualTo(Const.FLAG_FALSE);
		criteria.andInfoTypeLike("%4%");
		criteria.andStatusEqualTo("1");
		if(queryObject.getQuery("id") != null){
			criteria.andIdEqualTo(queryObject.getQueryToInt("id"));
		}
		if(queryObject.getQuery("catalogId") != null){
			criteria.andCatalogIdEqualTo(queryObject.getQueryToInt("catalogId"));
		}
		if(queryObject.getQuery("catalogIds") != null){
			List<Integer> catalogIds = queryObject.getQuery("catalogIds");
			criteria.andCatalogIdIn(catalogIds);
		}
		if(queryObject.getQuery("title") != null){
			criteria.andTitleLike("%" + queryObject.getQueryToStr("title") + "%");
		}
		if(queryObject.getSort().size() > 0){
			example.setOrderByClause(queryObject.getSortString());
		}
		return example;
	}
}
