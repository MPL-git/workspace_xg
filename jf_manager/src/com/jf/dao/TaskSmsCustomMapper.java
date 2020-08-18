package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.TaskSms;
import com.jf.entity.TaskSmsCustom;
import com.jf.entity.TaskSmsCustomExample;

@Repository
public interface TaskSmsCustomMapper {
    
	public int countByCustomExample(TaskSmsCustomExample example);

	public List<TaskSmsCustom> selectByCustomExample(TaskSmsCustomExample example);

	public TaskSmsCustom selectByCustomPrimaryKey(Integer id);

	public int updateByCustomExampleSelective(@Param("record") TaskSms record, @Param("example") TaskSmsCustomExample example);

}