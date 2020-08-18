package com.jf.dao;

import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Param;

import java.util.List;

import com.jf.common.base.BaseDao;
import com.jf.entity.TaskLog;
import com.jf.entity.TaskLogExample;

@Repository
public interface TaskLogMapper extends BaseDao<TaskLog, TaskLogExample> {
    int countByExample(TaskLogExample example);

    int deleteByExample(TaskLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TaskLog record);

    int insertSelective(TaskLog record);

    List<TaskLog> selectByExample(TaskLogExample example);

    TaskLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TaskLog record, @Param("example") TaskLogExample example);

    int updateByExample(@Param("record") TaskLog record, @Param("example") TaskLogExample example);

    int updateByPrimaryKeySelective(TaskLog record);

    int updateByPrimaryKey(TaskLog record);
}
