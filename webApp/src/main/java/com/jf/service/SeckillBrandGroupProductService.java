package com.jf.service;

import com.jf.common.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.SeckillBrandGroupProductMapper;
import com.jf.entity.SeckillBrandGroupProduct;
import com.jf.entity.SeckillBrandGroupProductExample;

/**
  * @author  chenwf: 
  * @date 创建时间：2018年4月18日 下午3:21:01 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class SeckillBrandGroupProductService extends BaseService<SeckillBrandGroupProduct, SeckillBrandGroupProductExample> {
	
	@Autowired
	private SeckillBrandGroupProductMapper seckillBrandGroupProductMapper;

	@Autowired
	public void setSeckillBrandGroupProductMapper(SeckillBrandGroupProductMapper seckillBrandGroupProductMapper) {
		this.setDao(seckillBrandGroupProductMapper);
		this.seckillBrandGroupProductMapper = seckillBrandGroupProductMapper;
	}
	
	
}
