package com.jf.service;

import com.jf.dao.DeliveryOvertimeSpecialCnfAreaMapper;
import com.jf.entity.DeliveryOvertimeSpecialCnfArea;
import com.jf.entity.DeliveryOvertimeSpecialCnfAreaExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Pengl
 * @create 2020-02-26 下午 5:55
 */
@Service
public class DeliveryOvertimeSpecialCnfAreaService extends BaseService<DeliveryOvertimeSpecialCnfArea, DeliveryOvertimeSpecialCnfAreaExample> {

	@Autowired
	private DeliveryOvertimeSpecialCnfAreaMapper deliveryOvertimeSpecialCnfAreaMapper;

	@Autowired
	public void setDeliveryOvertimeSpecialCnfAreaMapper(DeliveryOvertimeSpecialCnfAreaMapper deliveryOvertimeSpecialCnfAreaMapper) {
		super.setDao(deliveryOvertimeSpecialCnfAreaMapper);
		this.dao = deliveryOvertimeSpecialCnfAreaMapper;
	}




}
