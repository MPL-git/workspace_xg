package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.MallCategoryModuleMapper;
import com.jf.entity.MallCategoryModule;
import com.jf.entity.MallCategoryModuleExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
  * @author  chenwf: 
  * @date 创建时间：2018年7月18日 上午11:59:14 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class MallCategoryModuleService extends BaseService<MallCategoryModule, MallCategoryModuleExample> {
	@Autowired
	private MallCategoryModuleMapper mallCategoryModuleMapper;

	@Autowired
	public void setMallCategoryModuleMapper(MallCategoryModuleMapper mallCategoryModuleMapper) {
		this.setDao(mallCategoryModuleMapper);
		this.mallCategoryModuleMapper = mallCategoryModuleMapper;
	}
	
	
}
