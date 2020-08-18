package com.jf.dao;

import org.springframework.stereotype.Repository;

import com.jf.entity.FullDiscount;

@Repository
public interface FullDiscountCustomMapper {
	
	public FullDiscount selectByActivityId(Integer activityId);

}
