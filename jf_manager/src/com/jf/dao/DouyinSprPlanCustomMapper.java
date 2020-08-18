package com.jf.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.DouyinSprPlan;
import com.jf.entity.DouyinSprPlanCustom;
import com.jf.entity.DouyinSprPlanCustomExample;

@Repository
public interface DouyinSprPlanCustomMapper {
    int countByCustomExample(DouyinSprPlanCustomExample example);

    List<DouyinSprPlanCustom> selectByCustomExample(DouyinSprPlanCustomExample example);

    DouyinSprPlanCustom selectByCustomPrimaryKey(Integer id);

    int updateByCustomExampleSelective(@Param("record") DouyinSprPlan record, @Param("example") DouyinSprPlanCustomExample example);

    List<Map<String, Object>> getDouyinSprPlanList(Map<String, Object> paramMap);
    
    int countDouyinSprPlanList(Map<String, Object> paramMap);
    
    List<Map<String, Object>> getDouyinSprPlanStatisticsList(Map<String, Object> paramMap);
    
}