package com.jf.service;

import java.util.Date;
import java.util.List;

import com.jf.common.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.CustomerServiceLogMapper;
import com.jf.entity.CustomerServiceLog;
import com.jf.entity.CustomerServiceLogExample;

/**
  * @author  chenwf: 
  * @date 创建时间：2017年5月5日 下午1:42:48 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class CustomerServiceLogService extends BaseService<CustomerServiceLog, CustomerServiceLogExample> {
	
	@Autowired
	private CustomerServiceLogMapper customerServiceLogMapper;

	@Autowired
	public void setCustomerServiceLogMapper(CustomerServiceLogMapper customerServiceLogMapper) {
		this.setDao(customerServiceLogMapper);
		this.customerServiceLogMapper = customerServiceLogMapper;
	}
	
	/**
	 * 
	 * 方法描述 :根据售后订单id找日志
	 * @author  chenwf: 
	 * @date 创建时间：2017年7月18日 下午7:52:09 
	 * @version
	 * @param serviceOrderId
	 * @return
	 */
	public List<CustomerServiceLog> findListByServiceOrderId(Integer serviceOrderId) {
		CustomerServiceLogExample customerServiceLogExample = new CustomerServiceLogExample();
		customerServiceLogExample.createCriteria().andServiceOrderIdEqualTo(serviceOrderId).andDelFlagEqualTo("0");
		customerServiceLogExample.setOrderByClause("create_date desc");
		return customerServiceLogMapper.selectByExample(customerServiceLogExample);
	}

	public CustomerServiceLog save(Integer serviceOrderId, String operatorType, Integer createBy, String content, String remark) {
		CustomerServiceLog model = new CustomerServiceLog();
		model.setServiceOrderId(serviceOrderId);
		model.setOperatorType(operatorType);
		model.setContent(content);
		model.setRemarks(remark);
		model.setCreateBy(1);
		model.setCreateDate(new Date());
		model.setDelFlag("0");
		return save(model);
	}

	private CustomerServiceLog save(CustomerServiceLog model) {
		customerServiceLogMapper.insertSelective(model);
		return model;
	}
	
}
