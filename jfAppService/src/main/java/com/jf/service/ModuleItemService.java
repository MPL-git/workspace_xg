package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.ModuleItemMapper;
import com.jf.entity.ModuleItem;
import com.jf.entity.ModuleItemExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
  * @author  chenwf: 
  * @date 创建时间：2018年2月28日 下午1:54:34 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class ModuleItemService extends BaseService<ModuleItem, ModuleItemExample> {
	
	@Autowired
	private ModuleItemMapper moduleItemMapper;

	@Autowired
	public void setModuleItemMapper(ModuleItemMapper moduleItemMapper) {
		this.setDao(moduleItemMapper);
		this.moduleItemMapper = moduleItemMapper;
	}
	
	
}
