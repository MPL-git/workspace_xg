package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.ActivityBrandGroupCustom;
import com.jf.entity.ActivityBrandGroupCustomExample;

@Repository
public interface ActivityBrandGroupCustomMapper {
	public List<ActivityBrandGroupCustom> selectByCustomExample(ActivityBrandGroupCustomExample activitybrandGroupCustomMapper);
	int countByCustomExample(ActivityBrandGroupCustomExample example);
	 
}
