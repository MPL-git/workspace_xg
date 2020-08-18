package com.jf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.ModuleMapMapper;
import com.jf.entity.ModuleMap;
import com.jf.entity.ModuleMapExample;

@Service
@Transactional
public class ModuleMapService extends BaseService<ModuleMap,ModuleMapExample> {
	@Autowired
	private ModuleMapMapper moduleMapMapper;
	@Autowired
	public void setDecorateAreaMapper(ModuleMapMapper moduleMapMapper) {
		super.setDao(moduleMapMapper);
		this.moduleMapMapper = moduleMapMapper;
	}
	
}
