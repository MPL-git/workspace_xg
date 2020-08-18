package com.jf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jf.dao.SourceProductTypeCustomMapper;
import com.jf.dao.SourceProductTypeMapper;
import com.jf.entity.SourceProductType;
import com.jf.entity.SourceProductTypeCustom;
import com.jf.entity.SourceProductTypeCustomExample;
import com.jf.entity.SourceProductTypeExample;

@Service
public class SourceProductTypeService extends BaseService<SourceProductType, SourceProductTypeExample> {

	@Autowired
	private SourceProductTypeMapper sourceProductTypeMapper;
	
	@Autowired
	private SourceProductTypeCustomMapper sourceProductTypemMapper;
	
	@Autowired
	public void setOrderViewlogMapper(SourceProductTypeMapper sourceProductTypeMapper) {
		super.setDao(sourceProductTypeMapper);
		this.sourceProductTypeMapper = sourceProductTypeMapper;
	}
	
	public Integer sourceProductTypecountByExample(SourceProductTypeCustomExample example) {
		return sourceProductTypemMapper.sourceProductTypeCountByExample(example);
	}

	public List<SourceProductTypeCustom> sourceProductTypeCustomselectByExample(SourceProductTypeCustomExample example) {
		return sourceProductTypemMapper.sourceProductTypeCustomselectByExample(example);
	}
	
	public SourceProductType sourceProductTypeCustomselectByPrimaryKey(Integer id) {
		return sourceProductTypemMapper.sourceProductTypeCustomselectByPrimaryKey(id);
	}
	
}
