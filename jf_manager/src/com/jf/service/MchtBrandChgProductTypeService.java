package com.jf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.MchtBrandChgProductTypeCustomMapper;
import com.jf.dao.MchtBrandChgProductTypeMapper;
import com.jf.entity.MchtBrandChgProductType;
import com.jf.entity.MchtBrandChgProductTypeCustom;
import com.jf.entity.MchtBrandChgProductTypeExample;

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
