package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.ActivityRuleConfiguration;
import com.jf.entity.ActivityRuleConfigurationExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityRuleConfigurationMapper extends BaseDao<ActivityRuleConfiguration, ActivityRuleConfigurationExample> {
    int countByExample(ActivityRuleConfigurationExample example);

    int deleteByExample(ActivityRuleConfigurationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ActivityRuleConfiguration record);

    int insertSelective(ActivityRuleConfiguration record);

    List<ActivityRuleConfiguration> selectByExample(ActivityRuleConfigurationExample example);

    ActivityRuleConfiguration selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ActivityRuleConfiguration record, @Param("example") ActivityRuleConfigurationExample example);

    int updateByExample(@Param("record") ActivityRuleConfiguration record, @Param("example") ActivityRuleConfigurationExample example);

    int updateByPrimaryKeySelective(ActivityRuleConfiguration record);

    int updateByPrimaryKey(ActivityRuleConfiguration record);
}
