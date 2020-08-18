package com.jf.dao;

import com.jf.entity.DouyinSprPlan;
import com.jf.entity.DouyinSprPlanExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DouyinSprPlanMapper extends BaseDao<DouyinSprPlan, DouyinSprPlanExample> {
    int countByExample(DouyinSprPlanExample example);

    int deleteByExample(DouyinSprPlanExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DouyinSprPlan record);

    int insertSelective(DouyinSprPlan record);

    List<DouyinSprPlan> selectByExample(DouyinSprPlanExample example);

    DouyinSprPlan selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DouyinSprPlan record, @Param("example") DouyinSprPlanExample example);

    int updateByExample(@Param("record") DouyinSprPlan record, @Param("example") DouyinSprPlanExample example);

    int updateByPrimaryKeySelective(DouyinSprPlan record);

    int updateByPrimaryKey(DouyinSprPlan record);
}