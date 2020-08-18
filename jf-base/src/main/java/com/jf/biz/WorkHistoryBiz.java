package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.WorkHistoryExtMapper;
import com.jf.dao.WorkHistoryMapper;
import com.jf.entity.WorkHistory;
import com.jf.entity.WorkHistoryExample;
import com.jf.entity.WorkHistoryExt;
import com.jf.entity.WorkHistoryExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class WorkHistoryBiz extends BaseService<WorkHistory, WorkHistoryExample> {

	@Autowired
	private WorkHistoryMapper dao;
	@Autowired
	private WorkHistoryExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setWorkHistoryMapper(WorkHistoryMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public WorkHistoryExt save(WorkHistoryExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public WorkHistory update(WorkHistory model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		WorkHistory model = new WorkHistory();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public WorkHistoryExt findById(int id){
		return extDao.findById(id);
	}

	public WorkHistoryExt find(WorkHistoryExtExample example){
		return extDao.find(example.fill());
	}

	public int count(WorkHistoryExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, WorkHistoryExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, WorkHistoryExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, WorkHistoryExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(WorkHistoryExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<WorkHistoryExt> list(WorkHistoryExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<WorkHistoryExt> paginate(WorkHistoryExtExample example) {
		List<WorkHistoryExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
