package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.CustomerServicePicCustomMapper;
import com.jf.dao.CustomerServicePicMapper;
import com.jf.entity.CustomerServicePic;
import com.jf.entity.CustomerServicePicCustom;
import com.jf.entity.CustomerServicePicExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CustomerServicePicService extends BaseService<CustomerServicePic,CustomerServicePicExample> {
	@Autowired
	private CustomerServicePicMapper dao;
	
	@Autowired
	private CustomerServicePicCustomMapper customerServicePicCustomMapper;
	
	@Autowired
	public void setCustomerServicePicMapper(CustomerServicePicMapper customerServicePicMapper) {
		super.setDao(customerServicePicMapper);
		this.dao = customerServicePicMapper;
	}

	public List<CustomerServicePicCustom> getPicsByServiceOrderId(Integer serviceOrderId) {
		return customerServicePicCustomMapper.getPicsByServiceOrderId(serviceOrderId);
	}
	
}
