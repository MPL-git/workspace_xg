package com.jf.dao;

import com.jf.entity.WeixinXcxSprPlan;
import com.jf.entity.WeixinXcxSprPlanExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WeixinXcxSprPlanMapper extends BaseDao<WeixinXcxSprPlan, WeixinXcxSprPlanExample> {
    int countByExample(WeixinXcxSprPlanExample example);

    int deleteByExample(WeixinXcxSprPlanExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(WeixinXcxSprPlan record);

    int insertSelective(WeixinXcxSprPlan record);

    List<WeixinXcxSprPlan> selectByExample(WeixinXcxSprPlanExample example);

    WeixinXcxSprPlan selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") WeixinXcxSprPlan record, @Param("example") WeixinXcxSprPlanExample example);

    int updateByExample(@Param("record") WeixinXcxSprPlan record, @Param("example") WeixinXcxSprPlanExample example);

    int updateByPrimaryKeySelective(WeixinXcxSprPlan record);

    int updateByPrimaryKey(WeixinXcxSprPlan record);
}