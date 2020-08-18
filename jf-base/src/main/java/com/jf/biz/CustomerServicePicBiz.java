package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.CustomerServicePicExtMapper;
import com.jf.dao.CustomerServicePicMapper;
import com.jf.entity.CustomerServicePic;
import com.jf.entity.CustomerServicePicExample;
import com.jf.entity.CustomerServicePicExt;
import com.jf.entity.CustomerServicePicExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CustomerServicePicBiz extends BaseService<CustomerServicePic, CustomerServicePicExample> {

	@Autowired
	private CustomerServicePicMapper dao;
	@Autowired
	private CustomerServicePicExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setCustomerServicePicMapper(CustomerServicePicMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public CustomerServicePicExt save(CustomerServicePicExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public CustomerServicePic update(CustomerServicePic model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		CustomerServicePic model = new CustomerServicePic();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public CustomerServicePicExt findById(int id){
		return extDao.findById(id);
	}

	public CustomerServicePicExt find(CustomerServicePicExtExample example){
		return extDao.find(example.fill());
	}

	public int count(CustomerServicePicExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, CustomerServicePicExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, CustomerServicePicExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, CustomerServicePicExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(CustomerServicePicExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<CustomerServicePicExt> list(CustomerServicePicExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<CustomerServicePicExt> paginate(CustomerServicePicExtExample example) {
		List<CustomerServicePicExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
