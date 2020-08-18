package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.MchtBrandChgProductTypeCustomMapper;
import com.jf.dao.MchtBrandChgProductTypeMapper;
import com.jf.entity.MchtBrandChgProductType;
import com.jf.entity.MchtBrandChgProductTypeCustom;
import com.jf.entity.MchtBrandChgProductTypeExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MchtBrandChgProductTypeService extends BaseService<MchtBrandChgProductType,MchtBrandChgProductTypeExample> {
	@Autowired
	private MchtBrandChgProductTypeMapper dao;
	
	@Autowired
	private MchtBrandChgProductTypeCustomMapper mchtBrandChgProductTypeCustomMapper;
	
	@Autowired
	public void setMchtBrandChgProductTypeMapper(MchtBrandChgProductTypeMapper mchtBrandChgProductTypeMapper) {
		super.setDao(mchtBrandChgProductTypeMapper);
		this.dao = mchtBrandChgProductTypeMapper;
	}
	
	public List<MchtBrandChgProductTypeCustom> selectCustomByExample(MchtBrandChgProductTypeExample example){
		return mchtBrandChgProductTypeCustomMapper.selectByExample(example);
	}

	public void batchInsert(List<MchtBrandChgProductType> mchtBrandChgProductTypeList) {
		mchtBrandChgProductTypeCustomMapper.batchInsert(mchtBrandChgProductTypeList);
	}

}
