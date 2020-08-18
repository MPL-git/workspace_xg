package com.jf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.common.base.BaseService;
import com.jf.dao.TaskMarketingMapper;
import com.jf.entity.TaskMarketing;
import com.jf.entity.TaskMarketingExample;

@Service
@Transactional
public class TaskMarketingService extends BaseService<TaskMarketing, TaskMarketingExample> {

	@Autowired
	private TaskMarketingMapper taskMarketingMapper;
	
	@Autowired
	public void setTaskMarketingMapper(TaskMarketingMapper taskMarketingMapper) {
		super.setDao(taskMarketingMapper);
		this.taskMarketingMapper = taskMarketingMapper;
	}
	
	
}
