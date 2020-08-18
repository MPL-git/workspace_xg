package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.WorkExtMapper;
import com.jf.dao.WorkMapper;
import com.jf.entity.Work;
import com.jf.entity.WorkExample;
import com.jf.entity.WorkExt;
import com.jf.entity.WorkExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class WorkBiz extends BaseService<Work, WorkExample> {

	@Autowired
	private WorkMapper dao;
	@Autowired
	private WorkExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setWorkMapper(WorkMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public WorkExt save(WorkExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public Work update(Work model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		Work model = new Work();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public WorkExt findById(int id){
		return extDao.findById(id);
	}

	public WorkExt find(WorkExtExample example){
		return extDao.find(example.fill());
	}

	public int count(WorkExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, WorkExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, WorkExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, WorkExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(WorkExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<WorkExt> list(WorkExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<WorkExt> paginate(WorkExtExample example) {
		List<WorkExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
