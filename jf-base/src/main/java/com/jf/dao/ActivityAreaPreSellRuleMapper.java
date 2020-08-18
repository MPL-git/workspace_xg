package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.ActivityAreaPreSellRule;
import com.jf.entity.ActivityAreaPreSellRuleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityAreaPreSellRuleMapper extends BaseDao<ActivityAreaPreSellRule,ActivityAreaPreSellRuleExample> {
    int countByExample(ActivityAreaPreSellRuleExample example);

    int deleteByExample(ActivityAreaPreSellRuleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ActivityAreaPreSellRule record);

    int insertSelective(ActivityAreaPreSellRule record);

    List<ActivityAreaPreSellRule> selectByExample(ActivityAreaPreSellRuleExample example);

    ActivityAreaPreSellRule selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ActivityAreaPreSellRule record, @Param("example") ActivityAreaPreSellRuleExample example);

    int updateByExample(@Param("record") ActivityAreaPreSellRule record, @Param("example") ActivityAreaPreSellRuleExample example);

    int updateByPrimaryKeySelective(ActivityAreaPreSellRule record);

    int updateByPrimaryKey(ActivityAreaPreSellRule record);
}