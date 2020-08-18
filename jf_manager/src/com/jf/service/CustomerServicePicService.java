package com.jf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.CustomerServicePicMapper;
import com.jf.entity.CustomerServicePic;
import com.jf.entity.CustomerServicePicExample;

@Service
@Transactional
public class CustomerServicePicService extends BaseService<CustomerServicePic,CustomerServicePicExample> {
	@Autowired
	private CustomerServicePicMapper customerServicePicMapper;
	
	@Autowired
	public void setCustomerServicePicMapper(CustomerServicePicMapper customerServicePicMapper) {
		super.setDao(customerServicePicMapper);
		this.customerServicePicMapper = customerServicePicMapper;
	}
}
