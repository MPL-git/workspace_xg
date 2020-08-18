package com.jf.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.jf.common.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.CustomerServiceStatusLogCustomMapper;
import com.jf.dao.CustomerServiceStatusLogMapper;
import com.jf.entity.CustomerServiceStatusLog;
import com.jf.entity.CustomerServiceStatusLogCustom;
import com.jf.entity.CustomerServiceStatusLogExample;

/**
  * @author  chenwf: 
  * @date 创建时间：2017年5月4日 上午10:51:48 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class CustomerServiceStatusLogService extends BaseService<CustomerServiceStatusLog, CustomerServiceStatusLogExample> {
	
	@Autowired
	private CustomerServiceStatusLogMapper customerServiceStatusLogMapper;
	
	@Autowired
	private CustomerServiceStatusLogCustomMapper customerServiceStatusLogCustomMapper;
	
	@Autowired
	public void setCustomerServiceStatusLogMapper(CustomerServiceStatusLogMapper customerServiceStatusLogMapper) {
		this.setDao(customerServiceStatusLogMapper);
		this.customerServiceStatusLogMapper = customerServiceStatusLogMapper;
	}

	public List<CustomerServiceStatusLogCustom> getRefundDetailLog(Map<String, Object> params) {
		
		return customerServiceStatusLogCustomMapper.getRefundDetailLog(params);
	}

	public CustomerServiceStatusLog add(Integer id, String status, String proStatus, Integer memberId, String remarks,
			Date date) {
		CustomerServiceStatusLog customerServiceStatusLog = new CustomerServiceStatusLog();
		customerServiceStatusLog.setServiceOrderId(id);
		customerServiceStatusLog.setStatus(status);
		customerServiceStatusLog.setProStatus(proStatus);
		customerServiceStatusLog.setCreateBy(memberId);
		customerServiceStatusLog.setCreateDate(date);
		customerServiceStatusLog.setRemarks(remarks);
		customerServiceStatusLog.setDelFlag("0");
		
		return saveModel(customerServiceStatusLog);
		
	}

	public CustomerServiceStatusLog saveModel(CustomerServiceStatusLog customerServiceStatusLog) {
		
		customerServiceStatusLogMapper.insertSelective(customerServiceStatusLog);
		return customerServiceStatusLog;
	}

	public CustomerServiceStatusLog save(int serviceOrderId, String status, String proStatus, String remark){
		CustomerServiceStatusLog model = new CustomerServiceStatusLog();
		model.setServiceOrderId(serviceOrderId);
		model.setStatus(status);
		model.setProStatus(proStatus);
		model.setRemarks(remark);
		return save(model);
	}

	public CustomerServiceStatusLog save(CustomerServiceStatusLog model){
		model.setCreateDate(new Date());
		dao.insertSelective(model);
		return model;
	}
	
}
