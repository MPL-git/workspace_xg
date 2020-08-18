package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.DecorateModuleMapper;
import com.jf.entity.DecorateModule;
import com.jf.entity.DecorateModuleExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
  * @author  chenwf: 
  * @date 创建时间：2018年2月28日 上午11:09:00 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class DecorateModuleService extends BaseService<DecorateModule, DecorateModuleExample> {
	
	@Autowired
	private DecorateModuleMapper decorateModuleMapper;

	@Autowired
	public void setDecorateModuleMapper(DecorateModuleMapper decorateModuleMapper) {
		this.setDao(decorateModuleMapper);
		this.decorateModuleMapper = decorateModuleMapper;
	}

	public List<DecorateModule> selectByExampleWithBLOBs(DecorateModuleExample decorateModuleExample) {
		return decorateModuleMapper.selectByExampleWithBLOBs(decorateModuleExample);
	}
	
}
