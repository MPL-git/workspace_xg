package com.jf.service;

import java.util.List;
import java.util.Map;

import com.jf.common.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.SeckillBrandGroupCustomerMapper;
import com.jf.dao.SeckillBrandGroupMapper;
import com.jf.entity.SeckillBrandGroup;
import com.jf.entity.SeckillBrandGroupExample;

/**
  * @author  chenwf: 
  * @date 创建时间：2018年4月18日 下午3:17:44 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class SeckillBrandGroupService extends BaseService<SeckillBrandGroup, SeckillBrandGroupExample> {
	
	@Autowired
	private SeckillBrandGroupMapper seckillBrandGroupMapper;
	@Autowired
	private SeckillBrandGroupCustomerMapper seckillBrandGroupCustomerMapper;

	@Autowired
	public void setSeckillBrandGroupMapper(SeckillBrandGroupMapper seckillBrandGroupMapper) {
		this.setDao(seckillBrandGroupMapper);
		this.seckillBrandGroupMapper = seckillBrandGroupMapper;
	}

	public List<SeckillBrandGroup> getSeckillBrandGroup(Map<String, Object> brandGroupParams) {
		
		return seckillBrandGroupCustomerMapper.getSeckillBrandGroup(brandGroupParams);
	}

	
}
