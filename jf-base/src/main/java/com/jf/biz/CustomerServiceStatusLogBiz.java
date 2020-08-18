package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.CustomerServiceStatusLogExtMapper;
import com.jf.dao.CustomerServiceStatusLogMapper;
import com.jf.entity.CustomerServiceStatusLog;
import com.jf.entity.CustomerServiceStatusLogExample;
import com.jf.entity.CustomerServiceStatusLogExt;
import com.jf.entity.CustomerServiceStatusLogExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CustomerServiceStatusLogBiz extends BaseService<CustomerServiceStatusLog, CustomerServiceStatusLogExample> {

	@Autowired
	private CustomerServiceStatusLogMapper dao;
	@Autowired
	private CustomerServiceStatusLogExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setCustomerServiceStatusLogMapper(CustomerServiceStatusLogMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public CustomerServiceStatusLogExt save(CustomerServiceStatusLogExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public CustomerServiceStatusLog update(CustomerServiceStatusLog model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		CustomerServiceStatusLog model = new CustomerServiceStatusLog();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public CustomerServiceStatusLogExt findById(int id){
		return extDao.findById(id);
	}

	public CustomerServiceStatusLogExt find(CustomerServiceStatusLogExtExample example){
		return extDao.find(example.fill());
	}

	public int count(CustomerServiceStatusLogExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, CustomerServiceStatusLogExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, CustomerServiceStatusLogExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, CustomerServiceStatusLogExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(CustomerServiceStatusLogExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<CustomerServiceStatusLogExt> list(CustomerServiceStatusLogExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<CustomerServiceStatusLogExt> paginate(CustomerServiceStatusLogExtExample example) {
		List<CustomerServiceStatusLogExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
