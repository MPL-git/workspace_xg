package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.TaskSmsMapper;
import com.jf.entity.TaskSms;
import com.jf.entity.TaskSmsExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Pengl
 * @create 2019-11-26 下午 4:22
 */
@Service
@Transactional
public class TaskSmsService extends BaseService<TaskSms, TaskSmsExample> {

	@Autowired
	private TaskSmsMapper taskSmsMapper;

	@Autowired
	public void setTaskSmsMapper(TaskSmsMapper taskSmsMapper) {
		this.setDao(taskSmsMapper);
		this.taskSmsMapper = taskSmsMapper;
	}




}
