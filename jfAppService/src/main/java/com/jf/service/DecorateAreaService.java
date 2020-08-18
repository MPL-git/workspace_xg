package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.DecorateAreaMapper;
import com.jf.entity.DecorateArea;
import com.jf.entity.DecorateAreaExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
  * @author  chenwf: 
  * @date 创建时间：2018年2月28日 上午11:05:32 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class DecorateAreaService extends BaseService<DecorateArea, DecorateAreaExample> {
	
	@Autowired
	private DecorateAreaMapper decorateAreaMapper;

	@Autowired
	public void setDecorateAreaMapper(DecorateAreaMapper decorateAreaMapper) {
		this.setDao(decorateAreaMapper);
		this.decorateAreaMapper = decorateAreaMapper;
	}
	
	
}
