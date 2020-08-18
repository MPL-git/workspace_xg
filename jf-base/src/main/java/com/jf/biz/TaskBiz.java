package com.jf.biz;

import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import com.jf.dao.TaskMapper;
import com.jf.dao.TaskExtMapper;
import com.jf.entity.Task;
import com.jf.entity.TaskExample;
import com.jf.entity.TaskExt;
import com.jf.entity.TaskExtExample;
import com.jf.common.base.BaseService;

@Service
public class TaskBiz extends BaseService<Task, TaskExample> {

	@Autowired
	private TaskMapper dao;
	@Autowired
	private TaskExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setTaskMapper(TaskMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public TaskExt save(TaskExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public Task update(Task model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		Task model = new Task();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public TaskExt findById(int id){
		return extDao.findById(id);
	}

	public TaskExt find(TaskExtExample example){
		return extDao.find(example.fill());
	}

	public int count(TaskExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, TaskExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, TaskExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, TaskExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(TaskExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<TaskExt> list(TaskExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<TaskExt> paginate(TaskExtExample example) {
		List<TaskExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
