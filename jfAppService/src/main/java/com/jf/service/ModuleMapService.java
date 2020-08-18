package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.ModuleMapMapper;
import com.jf.entity.ModuleMap;
import com.jf.entity.ModuleMapExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
  * @author  chenwf: 
  * @date 创建时间：2018年2月28日 上午11:02:12 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class ModuleMapService extends BaseService<ModuleMap, ModuleMapExample> {
	@Autowired
	private ModuleMapMapper moduleMapMapper;

	@Autowired
	public void setModuleMapMapper(ModuleMapMapper moduleMapMapper) {
		this.setDao(moduleMapMapper);
		this.moduleMapMapper = moduleMapMapper;
	}
	
	
}
