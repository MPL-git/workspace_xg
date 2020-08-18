package com.jf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.CustomerServiceLogCustomMapper;
import com.jf.dao.CustomerServiceLogMapper;
import com.jf.entity.CustomerServiceLog;
import com.jf.entity.CustomerServiceLogCustom;
import com.jf.entity.CustomerServiceLogExample;

@Service
@Transactional
public class CustomerServiceLogService extends BaseService<CustomerServiceLog,CustomerServiceLogExample> {
	@Autowired
	private CustomerServiceLogMapper customerServiceLogMapper;
	@Autowired
	private CustomerServiceLogCustomMapper customerServiceLogCustomMapper;
	
	@Autowired
	public void setCustomerServiceLogMapper(CustomerServiceLogMapper customerServiceLogMapper) {
		super.setDao(customerServiceLogMapper);
		this.customerServiceLogMapper = customerServiceLogMapper;
	}
	
	public int countCustomerServiceLogCustomByExample(CustomerServiceLogExample example){
		return customerServiceLogCustomMapper.countByExample(example);
	}
    public List<CustomerServiceLogCustom> selectCustomerServiceLogCustomByExample(CustomerServiceLogExample example){
    	return customerServiceLogCustomMapper.selectByExample(example);
    }
    public CustomerServiceLogCustom selectCustomerServiceLogCustomByPrimaryKey(Integer id){
    	return customerServiceLogCustomMapper.selectByPrimaryKey(id);
    }
}
