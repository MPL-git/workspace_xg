package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.FullDiscountMapper;
import com.jf.entity.FullDiscount;
import com.jf.entity.FullDiscountExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
  * @author  chenwf: 
  * @date 创建时间：2017年4月25日 下午5:00:07 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class FullDiscountService extends BaseService<FullDiscount, FullDiscountExample> {
	
	@Autowired
	private FullDiscountMapper fullDiscountMapper;
	@Autowired
	public void setFullDiscountMapper(FullDiscountMapper fullDiscountMapper) {
		this.setDao(fullDiscountMapper);
		this.fullDiscountMapper = fullDiscountMapper;
	}
	
	
}
