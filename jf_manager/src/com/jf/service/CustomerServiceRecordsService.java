package com.jf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.CustomerServiceRecordsMapper;
import com.jf.entity.CustomerServiceRecords;
import com.jf.entity.CustomerServiceRecordsExample;

@Service
@Transactional
public class CustomerServiceRecordsService extends BaseService<CustomerServiceRecords, CustomerServiceRecordsExample> {
	@Autowired
	private CustomerServiceRecordsMapper customerServiceRecordsMapper;

	@Autowired
	public void setcustomerServiceRecordsMapper(CustomerServiceRecordsMapper customerServiceRecordsMapper) {
		super.setDao(customerServiceRecordsMapper);
		this.customerServiceRecordsMapper = customerServiceRecordsMapper;
	}
}
