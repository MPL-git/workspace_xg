package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.ModuleNavigationMapper;
import com.jf.entity.ModuleNavigation;
import com.jf.entity.ModuleNavigationExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
  * @author  yjc: 
  * @date 创建时间：2019年7月2日08:51:10
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class ModuleNavigationService extends BaseService<ModuleNavigation, ModuleNavigationExample> {
	@Autowired
	private ModuleNavigationMapper moduleNavigationMapper;

	@Autowired
	public void setModuleNavigation(ModuleNavigationMapper moduleNavigationMapper) {
		this.setDao(moduleNavigationMapper);
		this.moduleNavigationMapper = moduleNavigationMapper;
	}
	
	
}
