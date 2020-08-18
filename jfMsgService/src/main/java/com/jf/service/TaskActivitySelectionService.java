package com.jf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.common.base.BaseService;
import com.jf.dao.TaskActivitySelectionMapper;
import com.jf.entity.TaskActivitySelection;
import com.jf.entity.TaskActivitySelectionExample;

@Service
@Transactional
public class TaskActivitySelectionService extends BaseService<TaskActivitySelection, TaskActivitySelectionExample> {

	@Autowired
	private TaskActivitySelectionMapper taskActivitySelectionMapper;
	
	@Autowired
	public void setTaskActivitySelectionMapper(TaskActivitySelectionMapper taskActivitySelectionMapper) {
		super.setDao(taskActivitySelectionMapper);
		this.taskActivitySelectionMapper = taskActivitySelectionMapper;
	}
	
	
}
