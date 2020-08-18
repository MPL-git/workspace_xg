package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.SingleProductActivityAuditLogExtMapper;
import com.jf.dao.SingleProductActivityAuditLogMapper;
import com.jf.entity.SingleProductActivityAuditLog;
import com.jf.entity.SingleProductActivityAuditLogExample;
import com.jf.entity.SingleProductActivityAuditLogExt;
import com.jf.entity.SingleProductActivityAuditLogExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SingleProductActivityAuditLogBiz extends BaseService<SingleProductActivityAuditLog, SingleProductActivityAuditLogExample> {

	@Autowired
	private SingleProductActivityAuditLogMapper dao;
	@Autowired
	private SingleProductActivityAuditLogExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setSingleProductActivityAuditLogMapper(SingleProductActivityAuditLogMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public SingleProductActivityAuditLogExt save(SingleProductActivityAuditLogExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public SingleProductActivityAuditLog update(SingleProductActivityAuditLog model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		SingleProductActivityAuditLog model = new SingleProductActivityAuditLog();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public SingleProductActivityAuditLogExt findById(int id){
		return extDao.findById(id);
	}

	public SingleProductActivityAuditLogExt find(SingleProductActivityAuditLogExtExample example){
		return extDao.find(example.fill());
	}

	public int count(SingleProductActivityAuditLogExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, SingleProductActivityAuditLogExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, SingleProductActivityAuditLogExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, SingleProductActivityAuditLogExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(SingleProductActivityAuditLogExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<SingleProductActivityAuditLogExt> list(SingleProductActivityAuditLogExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<SingleProductActivityAuditLogExt> paginate(SingleProductActivityAuditLogExtExample example) {
		List<SingleProductActivityAuditLogExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
