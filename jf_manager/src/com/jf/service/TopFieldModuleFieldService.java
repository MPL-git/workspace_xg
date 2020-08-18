package com.jf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.TopFieldModuleFieldMapper;
import com.jf.entity.TopFieldModuleField;
import com.jf.entity.TopFieldModuleFieldExample;

@Service
@Transactional
public class TopFieldModuleFieldService extends BaseService<TopFieldModuleField, TopFieldModuleFieldExample> {

	@Autowired
	private TopFieldModuleFieldMapper topFieldModuleFieldMapper;
	
	@Autowired
	public void setTopFieldModuleFieldMapper(TopFieldModuleFieldMapper topFieldModuleFieldMapper) {
		super.setDao(topFieldModuleFieldMapper);
		this.topFieldModuleFieldMapper = topFieldModuleFieldMapper;
	}
}
