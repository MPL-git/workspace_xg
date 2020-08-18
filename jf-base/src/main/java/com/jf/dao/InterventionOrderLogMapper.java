package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.InterventionOrderLog;
import com.jf.entity.InterventionOrderLogExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InterventionOrderLogMapper extends BaseDao<InterventionOrderLog, InterventionOrderLogExample> {
    int countByExample(InterventionOrderLogExample example);

    int deleteByExample(InterventionOrderLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(InterventionOrderLog record);

    int insertSelective(InterventionOrderLog record);

    List<InterventionOrderLog> selectByExample(InterventionOrderLogExample example);

    InterventionOrderLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") InterventionOrderLog record, @Param("example") InterventionOrderLogExample example);

    int updateByExample(@Param("record") InterventionOrderLog record, @Param("example") InterventionOrderLogExample example);

    int updateByPrimaryKeySelective(InterventionOrderLog record);

    int updateByPrimaryKey(InterventionOrderLog record);
}
