package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.DeliveryOvertimeCnfMapper;
import com.jf.entity.DeliveryOvertimeCnf;
import com.jf.entity.DeliveryOvertimeCnfExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
  * @author  chenwf: 
  * @date 创建时间：2018年1月30日 下午12:21:54 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class DeliveryOvertimeCnfService extends BaseService<DeliveryOvertimeCnf, DeliveryOvertimeCnfExample> {
	@Autowired
	private DeliveryOvertimeCnfMapper deliveryOvertimeCnfMapper;

	@Autowired
	public void setDeliveryOvertimeCnfMapper(DeliveryOvertimeCnfMapper deliveryOvertimeCnfMapper) {
		this.setDao(deliveryOvertimeCnfMapper);
		this.deliveryOvertimeCnfMapper = deliveryOvertimeCnfMapper;
	}
	
}
