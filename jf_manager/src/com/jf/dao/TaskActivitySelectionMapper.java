package com.jf.dao;

import com.jf.entity.TaskActivitySelection;
import com.jf.entity.TaskActivitySelectionExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface TaskActivitySelectionMapper extends BaseDao<TaskActivitySelection, TaskActivitySelectionExample>{
    int countByExample(TaskActivitySelectionExample example);

    int deleteByExample(TaskActivitySelectionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TaskActivitySelection record);

    int insertSelective(TaskActivitySelection record);

    List<TaskActivitySelection> selectByExample(TaskActivitySelectionExample example);

    TaskActivitySelection selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TaskActivitySelection record, @Param("example") TaskActivitySelectionExample example);

    int updateByExample(@Param("record") TaskActivitySelection record, @Param("example") TaskActivitySelectionExample example);

    int updateByPrimaryKeySelective(TaskActivitySelection record);

    int updateByPrimaryKey(TaskActivitySelection record);
}