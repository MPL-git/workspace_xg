package com.jf.dao;

import com.jf.entity.ActivityRuleConfigurationExt;
import com.jf.entity.ActivityRuleConfigurationExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityRuleConfigurationExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    ActivityRuleConfigurationExt findById(int id);

    ActivityRuleConfigurationExt find(ActivityRuleConfigurationExtExample example);

    List<ActivityRuleConfigurationExt> list(ActivityRuleConfigurationExtExample example);

    List<Integer> listId(ActivityRuleConfigurationExtExample example);

    int count(ActivityRuleConfigurationExtExample example);

    long sum(@Param("field") String field, @Param("example") ActivityRuleConfigurationExtExample example);

    int max(@Param("field") String field, @Param("example") ActivityRuleConfigurationExtExample example);

    int min(@Param("field") String field, @Param("example") ActivityRuleConfigurationExtExample example);

}

