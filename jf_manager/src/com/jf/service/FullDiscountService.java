package com.jf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.FullDiscountCustomMapper;
import com.jf.dao.FullDiscountMapper;
import com.jf.entity.FullDiscount;
import com.jf.entity.FullDiscountExample;

@Service
@Transactional
public class FullDiscountService extends BaseService<FullDiscount, FullDiscountExample>{
	@Autowired
	private FullDiscountMapper fullDiscountMapper;
	
	@Autowired
	private FullDiscountCustomMapper fullDiscountCustomMapper;
	
	@Autowired
	public void setFullDiscountMapper(FullDiscountMapper fullDiscountMapper) {
		super.setDao(fullDiscountMapper);
		this.fullDiscountMapper = fullDiscountMapper;
	}
	
	public FullDiscount selectByActivityId(Integer activityId){
		return fullDiscountCustomMapper.selectByActivityId(activityId);
	}
}
