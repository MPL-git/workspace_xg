package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.InterventionOrderStatusLogExtMapper;
import com.jf.dao.InterventionOrderStatusLogMapper;
import com.jf.entity.InterventionOrderStatusLog;
import com.jf.entity.InterventionOrderStatusLogExample;
import com.jf.entity.InterventionOrderStatusLogExt;
import com.jf.entity.InterventionOrderStatusLogExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class InterventionOrderStatusLogBiz extends BaseService<InterventionOrderStatusLog, InterventionOrderStatusLogExample> {

	@Autowired
	private InterventionOrderStatusLogMapper dao;
	@Autowired
	private InterventionOrderStatusLogExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setInterventionOrderStatusLogMapper(InterventionOrderStatusLogMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public InterventionOrderStatusLogExt save(InterventionOrderStatusLogExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public InterventionOrderStatusLog update(InterventionOrderStatusLog model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		InterventionOrderStatusLog model = new InterventionOrderStatusLog();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public InterventionOrderStatusLogExt findById(int id){
		return extDao.findById(id);
	}

	public InterventionOrderStatusLogExt find(InterventionOrderStatusLogExtExample example){
		return extDao.find(example.fill());
	}

	public int count(InterventionOrderStatusLogExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, InterventionOrderStatusLogExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, InterventionOrderStatusLogExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, InterventionOrderStatusLogExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(InterventionOrderStatusLogExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<InterventionOrderStatusLogExt> list(InterventionOrderStatusLogExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<InterventionOrderStatusLogExt> paginate(InterventionOrderStatusLogExtExample example) {
		List<InterventionOrderStatusLogExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
