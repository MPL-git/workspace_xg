package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.SeckillTimeMapper;
import com.jf.entity.SeckillTime;
import com.jf.entity.SeckillTimeExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
  * @author  chenwf: 
  * @date 创建时间：2017年9月29日 下午2:56:17 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class SeckillTimeService extends BaseService<SeckillTime, SeckillTimeExample> {
	@Autowired
	private SeckillTimeMapper seckillTimeMapper;

	@Autowired
	public void setSeckillTimeMapper(SeckillTimeMapper seckillTimeMapper) {
		this.setDao(seckillTimeMapper);
		this.seckillTimeMapper = seckillTimeMapper;
	}
	
	
}
