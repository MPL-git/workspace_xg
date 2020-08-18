package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.TaskActivitySelectionExtMapper;
import com.jf.dao.TaskActivitySelectionMapper;
import com.jf.entity.TaskActivitySelection;
import com.jf.entity.TaskActivitySelectionExample;
import com.jf.entity.TaskActivitySelectionExt;
import com.jf.entity.TaskActivitySelectionExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TaskActivitySelectionBiz extends BaseService<TaskActivitySelection, TaskActivitySelectionExample> {

	@Autowired
	private TaskActivitySelectionMapper dao;
	@Autowired
	private TaskActivitySelectionExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------






	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setTaskActivitySelectionMapper(TaskActivitySelectionMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public TaskActivitySelectionExt save(TaskActivitySelectionExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public TaskActivitySelection update(TaskActivitySelection model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		TaskActivitySelection model = new TaskActivitySelection();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public TaskActivitySelectionExt findById(int id){
		return extDao.findById(id);
	}

	public TaskActivitySelectionExt find(TaskActivitySelectionExtExample example){
		return extDao.find(example.fill());
	}

	public int count(TaskActivitySelectionExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, TaskActivitySelectionExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, TaskActivitySelectionExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, TaskActivitySelectionExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(TaskActivitySelectionExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<TaskActivitySelectionExt> list(TaskActivitySelectionExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<TaskActivitySelectionExt> paginate(TaskActivitySelectionExtExample example) {
		List<TaskActivitySelectionExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
