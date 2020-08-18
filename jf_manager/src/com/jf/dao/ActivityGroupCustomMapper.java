package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.ActivityGroupCustom;
import com.jf.entity.ActivityGroupExample;

@Repository
public interface ActivityGroupCustomMapper {
  public List<ActivityGroupCustom> selectByCustomExample(ActivityGroupExample activityGroupCustomMapper);
  int countByCustomExample(ActivityGroupExample example);
	   
}
