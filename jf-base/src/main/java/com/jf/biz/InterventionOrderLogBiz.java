package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.InterventionOrderLogExtMapper;
import com.jf.dao.InterventionOrderLogMapper;
import com.jf.entity.InterventionOrderLog;
import com.jf.entity.InterventionOrderLogExample;
import com.jf.entity.InterventionOrderLogExt;
import com.jf.entity.InterventionOrderLogExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class InterventionOrderLogBiz extends BaseService<InterventionOrderLog, InterventionOrderLogExample> {

	@Autowired
	private InterventionOrderLogMapper dao;
	@Autowired
	private InterventionOrderLogExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setInterventionOrderLogMapper(InterventionOrderLogMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public InterventionOrderLogExt save(InterventionOrderLogExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public InterventionOrderLog update(InterventionOrderLog model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		InterventionOrderLog model = new InterventionOrderLog();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public InterventionOrderLogExt findById(int id){
		return extDao.findById(id);
	}

	public InterventionOrderLogExt find(InterventionOrderLogExtExample example){
		return extDao.find(example.fill());
	}

	public int count(InterventionOrderLogExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, InterventionOrderLogExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, InterventionOrderLogExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, InterventionOrderLogExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(InterventionOrderLogExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<InterventionOrderLogExt> list(InterventionOrderLogExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<InterventionOrderLogExt> paginate(InterventionOrderLogExtExample example) {
		List<InterventionOrderLogExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
