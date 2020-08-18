package com.jf.dao;

import com.jf.entity.InterventionOrder;
import com.jf.entity.InterventionOrderExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface InterventionOrderMapper extends BaseDao<InterventionOrder, InterventionOrderExample> {
    int countByExample(InterventionOrderExample example);

    int deleteByExample(InterventionOrderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(InterventionOrder record);

    int insertSelective(InterventionOrder record);

    List<InterventionOrder> selectByExample(InterventionOrderExample example);

    InterventionOrder selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") InterventionOrder record, @Param("example") InterventionOrderExample example);

    int updateByExample(@Param("record") InterventionOrder record, @Param("example") InterventionOrderExample example);

    int updateByPrimaryKeySelective(InterventionOrder record);

    int updateByPrimaryKey(InterventionOrder record);
}