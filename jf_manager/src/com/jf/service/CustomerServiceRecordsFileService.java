package com.jf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.CustomerServiceRecordsFileMapper;
import com.jf.entity.CustomerServiceRecordsFile;
import com.jf.entity.CustomerServiceRecordsFileExample;

@Service
@Transactional
public class CustomerServiceRecordsFileService extends BaseService<CustomerServiceRecordsFile, CustomerServiceRecordsFileExample> {
	@Autowired
	private CustomerServiceRecordsFileMapper customerServiceRecordsFileMapper;

	@Autowired
	public void setcustomerServiceRecordsFileMapper(CustomerServiceRecordsFileMapper customerServiceRecordsFileMapper) {
		super.setDao(customerServiceRecordsFileMapper);
		this.customerServiceRecordsFileMapper = customerServiceRecordsFileMapper;
	}
}
