package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.TaskLog;
import com.jf.entity.TaskLogCustom;
import com.jf.entity.TaskLogCustomExample;
@Repository
public interface TaskLogCustomMapper {
    
	public int countByCustomExample(TaskLogCustomExample example);

    public List<TaskLogCustom> selectByCustomExample(TaskLogCustomExample example);

    public TaskLogCustom selectByCustomPrimaryKey(Integer id);

    public int updateByCustomExampleSelective(@Param("record") TaskLog record, @Param("example") TaskLogCustomExample example);

}