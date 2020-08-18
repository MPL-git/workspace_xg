package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.CustomerServiceStatusLogCustomMapper;
import com.jf.dao.CustomerServiceStatusLogMapper;
import com.jf.entity.CustomerServiceStatusLog;
import com.jf.entity.CustomerServiceStatusLogExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CustomerServiceStatusLogService extends BaseService<CustomerServiceStatusLog,CustomerServiceStatusLogExample> {
	@Autowired
	private CustomerServiceStatusLogMapper dao;
	
	@Autowired
	private CustomerServiceStatusLogCustomMapper customerServiceStatusLogCustomMapper;
	
	@Autowired
	public void setCustomerServiceStatusLogMapper(CustomerServiceStatusLogMapper customerServiceStatusLogMapper) {
		super.setDao(customerServiceStatusLogMapper);
		this.dao = customerServiceStatusLogMapper;
	}
	
}
