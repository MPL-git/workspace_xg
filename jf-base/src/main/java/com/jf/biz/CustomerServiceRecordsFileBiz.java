package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.CustomerServiceRecordsFileExtMapper;
import com.jf.dao.CustomerServiceRecordsFileMapper;
import com.jf.entity.CustomerServiceRecordsFile;
import com.jf.entity.CustomerServiceRecordsFileExample;
import com.jf.entity.CustomerServiceRecordsFileExt;
import com.jf.entity.CustomerServiceRecordsFileExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CustomerServiceRecordsFileBiz extends BaseService<CustomerServiceRecordsFile, CustomerServiceRecordsFileExample> {

	@Autowired
	private CustomerServiceRecordsFileMapper dao;
	@Autowired
	private CustomerServiceRecordsFileExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setCustomerServiceRecordsFileMapper(CustomerServiceRecordsFileMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public CustomerServiceRecordsFileExt save(CustomerServiceRecordsFileExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public CustomerServiceRecordsFile update(CustomerServiceRecordsFile model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		CustomerServiceRecordsFile model = new CustomerServiceRecordsFile();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public CustomerServiceRecordsFileExt findById(int id){
		return extDao.findById(id);
	}

	public CustomerServiceRecordsFileExt find(CustomerServiceRecordsFileExtExample example){
		return extDao.find(example.fill());
	}

	public int count(CustomerServiceRecordsFileExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, CustomerServiceRecordsFileExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, CustomerServiceRecordsFileExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, CustomerServiceRecordsFileExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(CustomerServiceRecordsFileExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<CustomerServiceRecordsFileExt> list(CustomerServiceRecordsFileExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<CustomerServiceRecordsFileExt> paginate(CustomerServiceRecordsFileExtExample example) {
		List<CustomerServiceRecordsFileExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
