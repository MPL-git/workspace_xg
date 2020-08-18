package com.jf.dao;

import com.jf.entity.WorkHistory;
import com.jf.entity.WorkHistoryExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface WorkHistoryMapper extends BaseDao<WorkHistory, WorkHistoryExample>{
    int countByExample(WorkHistoryExample example);

    int deleteByExample(WorkHistoryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(WorkHistory record);

    int insertSelective(WorkHistory record);

    List<WorkHistory> selectByExample(WorkHistoryExample example);

    WorkHistory selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") WorkHistory record, @Param("example") WorkHistoryExample example);

    int updateByExample(@Param("record") WorkHistory record, @Param("example") WorkHistoryExample example);

    int updateByPrimaryKeySelective(WorkHistory record);

    int updateByPrimaryKey(WorkHistory record);
}