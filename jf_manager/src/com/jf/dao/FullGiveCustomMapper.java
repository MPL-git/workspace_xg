package com.jf.dao;

import org.springframework.stereotype.Repository;

import com.jf.entity.FullGive;

@Repository
public interface FullGiveCustomMapper {
	
	public FullGive selectByActivityId(Integer activityId);

}
