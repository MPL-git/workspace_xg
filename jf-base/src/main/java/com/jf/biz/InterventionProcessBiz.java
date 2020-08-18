package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.InterventionProcessExtMapper;
import com.jf.dao.InterventionProcessMapper;
import com.jf.entity.InterventionProcess;
import com.jf.entity.InterventionProcessExample;
import com.jf.entity.InterventionProcessExt;
import com.jf.entity.InterventionProcessExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class InterventionProcessBiz extends BaseService<InterventionProcess, InterventionProcessExample> {

	@Autowired
	private InterventionProcessMapper dao;
	@Autowired
	private InterventionProcessExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setInterventionProcessMapper(InterventionProcessMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public InterventionProcessExt save(InterventionProcessExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public InterventionProcess update(InterventionProcess model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		InterventionProcess model = new InterventionProcess();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public InterventionProcessExt findById(int id){
		return extDao.findById(id);
	}

	public InterventionProcessExt find(InterventionProcessExtExample example){
		return extDao.find(example.fill());
	}

	public int count(InterventionProcessExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, InterventionProcessExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, InterventionProcessExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, InterventionProcessExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(InterventionProcessExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<InterventionProcessExt> list(InterventionProcessExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<InterventionProcessExt> paginate(InterventionProcessExtExample example) {
		List<InterventionProcessExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
