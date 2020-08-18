package com.jf.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.TaskLogCustomMapper;
import com.jf.dao.TaskLogMapper;
import com.jf.entity.TaskLog;
import com.jf.entity.TaskLogCustom;
import com.jf.entity.TaskLogCustomExample;
import com.jf.entity.TaskLogExample;

@Service
@Transactional
public class TaskLogService extends BaseService<TaskLog, TaskLogExample> {

	@Autowired
	private TaskLogMapper taskLogMapper;
	
	@Autowired
	private TaskLogCustomMapper taskLogCustomMapper;
	
	@Autowired
	public void setTaskLogMapper(TaskLogMapper taskLogMapper) {
		super.setDao(taskLogMapper);
		this.taskLogMapper = taskLogMapper;
	}
	
	public int countByCustomExample(TaskLogCustomExample example) {
		return taskLogCustomMapper.countByCustomExample(example);
	}

    public List<TaskLogCustom> selectByCustomExample(TaskLogCustomExample example) {
    	return taskLogCustomMapper.selectByCustomExample(example);
    }

    public TaskLogCustom selectByCustomPrimaryKey(Integer id) {
    	return taskLogCustomMapper.selectByCustomPrimaryKey(id);
    }

    public int updateByCustomExampleSelective(@Param("record") TaskLog record, @Param("example") TaskLogCustomExample example) {
    	return taskLogCustomMapper.updateByCustomExampleSelective(record, example);
    }
	
}
