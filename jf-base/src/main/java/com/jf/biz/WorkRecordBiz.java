package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.WorkRecordExtMapper;
import com.jf.dao.WorkRecordMapper;
import com.jf.entity.WorkRecord;
import com.jf.entity.WorkRecordExample;
import com.jf.entity.WorkRecordExt;
import com.jf.entity.WorkRecordExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class WorkRecordBiz extends BaseService<WorkRecord, WorkRecordExample> {

	@Autowired
	private WorkRecordMapper dao;
	@Autowired
	private WorkRecordExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setWorkRecordMapper(WorkRecordMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public WorkRecordExt save(WorkRecordExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public WorkRecord update(WorkRecord model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		WorkRecord model = new WorkRecord();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public WorkRecordExt findById(int id){
		return extDao.findById(id);
	}

	public WorkRecordExt find(WorkRecordExtExample example){
		return extDao.find(example.fill());
	}

	public int count(WorkRecordExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, WorkRecordExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, WorkRecordExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, WorkRecordExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(WorkRecordExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<WorkRecordExt> list(WorkRecordExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<WorkRecordExt> paginate(WorkRecordExtExample example) {
		List<WorkRecordExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
