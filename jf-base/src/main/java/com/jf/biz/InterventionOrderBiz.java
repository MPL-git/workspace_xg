package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.InterventionOrderExtMapper;
import com.jf.dao.InterventionOrderMapper;
import com.jf.entity.InterventionOrder;
import com.jf.entity.InterventionOrderExample;
import com.jf.entity.InterventionOrderExt;
import com.jf.entity.InterventionOrderExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class InterventionOrderBiz extends BaseService<InterventionOrder, InterventionOrderExample> {

	@Autowired
	private InterventionOrderMapper dao;
	@Autowired
	private InterventionOrderExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setInterventionOrderMapper(InterventionOrderMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public InterventionOrderExt save(InterventionOrderExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public InterventionOrder update(InterventionOrder model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		InterventionOrder model = new InterventionOrder();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public InterventionOrderExt findById(int id){
		return extDao.findById(id);
	}

	public InterventionOrderExt find(InterventionOrderExtExample example){
		return extDao.find(example.fill());
	}

	public int count(InterventionOrderExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, InterventionOrderExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, InterventionOrderExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, InterventionOrderExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(InterventionOrderExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<InterventionOrderExt> list(InterventionOrderExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<InterventionOrderExt> paginate(InterventionOrderExtExample example) {
		List<InterventionOrderExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
