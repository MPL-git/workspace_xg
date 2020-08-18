package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.CustomerServicePicMapper;
import com.jf.entity.CustomerServicePic;
import com.jf.entity.CustomerServicePicExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
  * @author  chenwf: 
  * @date 创建时间：2017年5月4日 上午10:49:50 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class CustomerServicePicService extends BaseService<CustomerServicePic, CustomerServicePicExample> {
	
	@Autowired
	private CustomerServicePicMapper customerServicePicMapper;

	@Autowired
	public void setCustomerServicePicMapper(CustomerServicePicMapper customerServicePicMapper) {
		this.setDao(customerServicePicMapper);
		this.customerServicePicMapper = customerServicePicMapper;
	}

	public List<CustomerServicePic> findListByServiceOrderId(Integer serviceOrderId,Integer memberId) {
		CustomerServicePicExample customerServicePicExample = new CustomerServicePicExample();
		customerServicePicExample.createCriteria().andServiceOrderIdEqualTo(serviceOrderId).andDelFlagEqualTo("0").andCreateByEqualTo(memberId);
		return customerServicePicMapper.selectByExample(customerServicePicExample);
	}

	public void add(Integer id, String str, Integer memberId, Date date, String remarks) {
		CustomerServicePic customerServicePic = new CustomerServicePic();
		customerServicePic.setServiceOrderId(id);
		customerServicePic.setPic(str);
		customerServicePic.setCreateBy(memberId);
		customerServicePic.setCreateDate(date);
		customerServicePic.setRemarks(remarks);
		customerServicePic.setDelFlag("0");
		
		saveModel(customerServicePic);
		
	}

	public void saveModel(CustomerServicePic customerServicePic) {
		
		customerServicePicMapper.insertSelective(customerServicePic);
	}
	
}
