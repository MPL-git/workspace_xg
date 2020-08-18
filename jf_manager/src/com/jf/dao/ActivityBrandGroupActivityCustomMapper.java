package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.ActivityBrandGroupActivityCustom;
import com.jf.entity.ActivityBrandGroupActivityCustomExample;

@Repository
public interface ActivityBrandGroupActivityCustomMapper {
	public List<ActivityBrandGroupActivityCustom> selectByCustomExample(ActivityBrandGroupActivityCustomExample activitybrandGroupactivityCustomMapper);
	int countByCustomExample(ActivityBrandGroupActivityCustomExample example);
	   
}
