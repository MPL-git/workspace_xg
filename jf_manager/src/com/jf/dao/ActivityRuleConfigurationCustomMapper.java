package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.ActivityRuleConfiguration;
import com.jf.entity.ActivityRuleConfigurationCustom;
import com.jf.entity.ActivityRuleConfigurationExample;
@Repository
public interface ActivityRuleConfigurationCustomMapper{
    int countByExample(ActivityRuleConfigurationExample example);
    List<ActivityRuleConfigurationCustom> selectByExample(ActivityRuleConfigurationExample example);
	void updateByEntity(ActivityRuleConfiguration activityRuleConfiguration);
}