package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.CustomerServiceLogExtMapper;
import com.jf.dao.CustomerServiceLogMapper;
import com.jf.entity.CustomerServiceLog;
import com.jf.entity.CustomerServiceLogExample;
import com.jf.entity.CustomerServiceLogExt;
import com.jf.entity.CustomerServiceLogExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CustomerServiceLogBiz extends BaseService<CustomerServiceLog, CustomerServiceLogExample> {

	@Autowired
	private CustomerServiceLogMapper dao;
	@Autowired
	private CustomerServiceLogExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setCustomerServiceLogMapper(CustomerServiceLogMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public CustomerServiceLogExt save(CustomerServiceLogExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public CustomerServiceLog update(CustomerServiceLog model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		CustomerServiceLog model = new CustomerServiceLog();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public CustomerServiceLogExt findById(int id){
		return extDao.findById(id);
	}

	public CustomerServiceLogExt find(CustomerServiceLogExtExample example){
		return extDao.find(example.fill());
	}

	public int count(CustomerServiceLogExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, CustomerServiceLogExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, CustomerServiceLogExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, CustomerServiceLogExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(CustomerServiceLogExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<CustomerServiceLogExt> list(CustomerServiceLogExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<CustomerServiceLogExt> paginate(CustomerServiceLogExtExample example) {
		List<CustomerServiceLogExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
