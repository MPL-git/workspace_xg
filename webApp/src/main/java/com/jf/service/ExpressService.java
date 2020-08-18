package com.jf.service;

import com.jf.common.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.ExpressMapper;
import com.jf.entity.Express;
import com.jf.entity.ExpressExample;

/**
  * @author  chenwf: 
  * @date 创建时间：2017年6月5日 下午8:31:35 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class ExpressService extends BaseService<Express, ExpressExample> {
	
	@Autowired
	private ExpressMapper expressMapper;

	@Autowired
	public void setExpressMapper(ExpressMapper expressMapper) {
		this.setDao(expressMapper);
		this.expressMapper = expressMapper;
	}
	
	
}
