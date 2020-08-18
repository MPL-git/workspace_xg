package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.ActivityCustom;
import com.jf.entity.ActivityExample;
@Repository
public interface ActivityCustomMapper{

	List<ActivityCustom> selectByExample(ActivityExample example);

	String getActivityStatusByProductId(Integer productId);
	
}