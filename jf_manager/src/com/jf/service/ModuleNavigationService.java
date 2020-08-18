package com.jf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.ModuleNavigationMapper;
import com.jf.dao.NovaStrategyMapper;
import com.jf.entity.ModuleNavigation;
import com.jf.entity.ModuleNavigationExample;

@Service
@Transactional
public class ModuleNavigationService extends BaseService<ModuleNavigation,ModuleNavigationExample> {
	@Autowired
	private ModuleNavigationMapper moduleNavigationMapper;

	@Autowired
	public void setModuleNavigationMapper(ModuleNavigationMapper moduleNavigationMapper) {
		super.setDao(moduleNavigationMapper);
		this.moduleNavigationMapper = moduleNavigationMapper;
	}

}
