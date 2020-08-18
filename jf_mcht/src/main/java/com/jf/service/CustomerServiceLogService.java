package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.CustomerServiceLogCustomMapper;
import com.jf.dao.CustomerServiceLogMapper;
import com.jf.entity.CustomerServiceLog;
import com.jf.entity.CustomerServiceLogCustom;
import com.jf.entity.CustomerServiceLogCustomExample;
import com.jf.entity.CustomerServiceLogExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CustomerServiceLogService extends BaseService<CustomerServiceLog,CustomerServiceLogExample> {
	@Autowired
	private CustomerServiceLogMapper dao;
	
	@Autowired
	private CustomerServiceLogCustomMapper customerServiceLogCustomMapper;
	
	@Autowired
	public void setCustomerServiceLogMapper(CustomerServiceLogMapper customerServiceLogMapper) {
		super.setDao(customerServiceLogMapper);
		this.dao = customerServiceLogMapper;
	}

	public List<CustomerServiceLogCustom> selectCustomerServiceLogCustomByExample(CustomerServiceLogCustomExample example) {
		return customerServiceLogCustomMapper.selectByExample(example);
	}
}
