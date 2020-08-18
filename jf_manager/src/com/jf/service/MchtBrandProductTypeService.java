package com.jf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.MchtBrandProductTypeCustomMapper;
import com.jf.dao.MchtBrandProductTypeMapper;
import com.jf.entity.MchtBrandProductType;
import com.jf.entity.MchtBrandProductTypeCustom;
import com.jf.entity.MchtBrandProductTypeExample;

@Service
@Transactional
public class MchtBrandProductTypeService extends BaseService<MchtBrandProductType,MchtBrandProductTypeExample> {
	@Autowired
	private MchtBrandProductTypeMapper dao;
	
	@Autowired
	private MchtBrandProductTypeCustomMapper mchtBrandProductTypeCustomMapper;
	
	@Autowired
	public void setMchtBrandProductType(MchtBrandProductTypeMapper mchtBrandProductTypeMapper) {
		super.setDao(mchtBrandProductTypeMapper);
		this.dao = mchtBrandProductTypeMapper;
	}
	
	public List<MchtBrandProductTypeCustom> selectCustomByExample(MchtBrandProductTypeExample example){
		return mchtBrandProductTypeCustomMapper.selectByExample(example);
	}

	public void batchInsert(List<MchtBrandProductType> mchtBrandProductTypeList) {
		mchtBrandProductTypeCustomMapper.batchInsert(mchtBrandProductTypeList);
	}

}
