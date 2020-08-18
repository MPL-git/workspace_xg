package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.KdnWuliuInfoMapper;
import com.jf.entity.KdnWuliuInfo;
import com.jf.entity.KdnWuliuInfoExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class KdnWuliuInfoService extends BaseService<KdnWuliuInfo,KdnWuliuInfoExample> {

	@Autowired
	private KdnWuliuInfoMapper dao;

	@Autowired
	public void setKdnWuliuInfoMapper(KdnWuliuInfoMapper kdnWuliuInfoMapper) {
		super.setDao(kdnWuliuInfoMapper);
		this.dao = kdnWuliuInfoMapper;
	}
	
	public KdnWuliuInfo findById(int id){
		return dao.selectByPrimaryKey(id);
	}

	public KdnWuliuInfo save(KdnWuliuInfo model){
		model.setCreateDate(new Date());
		dao.insertSelective(model);
		return model;
	}

	public KdnWuliuInfo update(KdnWuliuInfo model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKeySelective(model);
		return model;
	}

	public List<KdnWuliuInfo> findList(QueryObject queryObject) {
		return dao.selectByExample(builderQuery(queryObject));
	}

	private KdnWuliuInfoExample builderQuery(QueryObject queryObject) {
		KdnWuliuInfoExample example = new KdnWuliuInfoExample();
		KdnWuliuInfoExample.Criteria criteria = example.createCriteria();
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
