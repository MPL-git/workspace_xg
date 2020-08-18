package com.jf.dao;

import com.jf.entity.PopSettlePlan;
import com.jf.entity.PopSettlePlanExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PopSettlePlanMapper extends BaseDao<PopSettlePlan, PopSettlePlanExample>{
    int countByExample(PopSettlePlanExample example);

    int deleteByExample(PopSettlePlanExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PopSettlePlan record);

    int insertSelective(PopSettlePlan record);

    List<PopSettlePlan> selectByExample(PopSettlePlanExample example);

    PopSettlePlan selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PopSettlePlan record, @Param("example") PopSettlePlanExample example);

    int updateByExample(@Param("record") PopSettlePlan record, @Param("example") PopSettlePlanExample example);

    int updateByPrimaryKeySelective(PopSettlePlan record);

    int updateByPrimaryKey(PopSettlePlan record);
}