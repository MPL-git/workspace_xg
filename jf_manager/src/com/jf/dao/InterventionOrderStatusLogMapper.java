package com.jf.dao;

import com.jf.entity.InterventionOrderStatusLog;
import com.jf.entity.InterventionOrderStatusLogExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface InterventionOrderStatusLogMapper extends BaseDao<InterventionOrderStatusLog, InterventionOrderStatusLogExample> {
    int countByExample(InterventionOrderStatusLogExample example);

    int deleteByExample(InterventionOrderStatusLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(InterventionOrderStatusLog record);

    int insertSelective(InterventionOrderStatusLog record);

    List<InterventionOrderStatusLog> selectByExample(InterventionOrderStatusLogExample example);

    InterventionOrderStatusLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") InterventionOrderStatusLog record, @Param("example") InterventionOrderStatusLogExample example);

    int updateByExample(@Param("record") InterventionOrderStatusLog record, @Param("example") InterventionOrderStatusLogExample example);

    int updateByPrimaryKeySelective(InterventionOrderStatusLog record);

    int updateByPrimaryKey(InterventionOrderStatusLog record);
}