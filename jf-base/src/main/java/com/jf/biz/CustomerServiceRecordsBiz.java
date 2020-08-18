package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.CustomerServiceRecordsExtMapper;
import com.jf.dao.CustomerServiceRecordsMapper;
import com.jf.entity.CustomerServiceRecords;
import com.jf.entity.CustomerServiceRecordsExample;
import com.jf.entity.CustomerServiceRecordsExt;
import com.jf.entity.CustomerServiceRecordsExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CustomerServiceRecordsBiz extends BaseService<CustomerServiceRecords, CustomerServiceRecordsExample> {

	@Autowired
	private CustomerServiceRecordsMapper dao;
	@Autowired
	private CustomerServiceRecordsExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setCustomerServiceRecordsMapper(CustomerServiceRecordsMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public CustomerServiceRecordsExt save(CustomerServiceRecordsExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public CustomerServiceRecords update(CustomerServiceRecords model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		CustomerServiceRecords model = new CustomerServiceRecords();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public CustomerServiceRecordsExt findById(int id){
		return extDao.findById(id);
	}

	public CustomerServiceRecordsExt find(CustomerServiceRecordsExtExample example){
		return extDao.find(example.fill());
	}

	public int count(CustomerServiceRecordsExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, CustomerServiceRecordsExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, CustomerServiceRecordsExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, CustomerServiceRecordsExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(CustomerServiceRecordsExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<CustomerServiceRecordsExt> list(CustomerServiceRecordsExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<CustomerServiceRecordsExt> paginate(CustomerServiceRecordsExtExample example) {
		List<CustomerServiceRecordsExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
