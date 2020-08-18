package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.CustomerServiceOrderExtMapper;
import com.jf.dao.CustomerServiceOrderMapper;
import com.jf.entity.CustomerServiceOrder;
import com.jf.entity.CustomerServiceOrderExample;
import com.jf.entity.CustomerServiceOrderExt;
import com.jf.entity.CustomerServiceOrderExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CustomerServiceOrderBiz extends BaseService<CustomerServiceOrder, CustomerServiceOrderExample> {

	@Autowired
	private CustomerServiceOrderMapper dao;
	@Autowired
	private CustomerServiceOrderExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setCustomerServiceOrderMapper(CustomerServiceOrderMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public CustomerServiceOrderExt save(CustomerServiceOrderExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public CustomerServiceOrder update(CustomerServiceOrder model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		CustomerServiceOrder model = new CustomerServiceOrder();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public CustomerServiceOrderExt findById(int id){
		return extDao.findById(id);
	}

	public CustomerServiceOrderExt find(CustomerServiceOrderExtExample example){
		return extDao.find(example.fill());
	}

	public int count(CustomerServiceOrderExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, CustomerServiceOrderExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, CustomerServiceOrderExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, CustomerServiceOrderExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(CustomerServiceOrderExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<CustomerServiceOrderExt> list(CustomerServiceOrderExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<CustomerServiceOrderExt> paginate(CustomerServiceOrderExtExample example) {
		List<CustomerServiceOrderExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
