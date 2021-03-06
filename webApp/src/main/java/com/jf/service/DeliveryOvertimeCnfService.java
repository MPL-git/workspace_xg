package com.jf.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jf.common.base.BaseService;
import com.jf.dao.DeliveryOvertimeSpecialCnfAreaMapper;
import com.jf.dao.DeliveryOvertimeSpecialCnfMapper;
import com.jf.entity.*;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.common.utils.DateUtil;
import com.jf.dao.DeliveryOvertimeCnfMapper;

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
	private DeliveryOvertimeSpecialCnfMapper deliveryOvertimeSpecialCnfMapper;

	@Autowired
	private DeliveryOvertimeSpecialCnfAreaMapper deliveryOvertimeSpecialCnfAreaMapper;

	@Autowired
	public void setDeliveryOvertimeCnfMapper(DeliveryOvertimeCnfMapper deliveryOvertimeCnfMapper) {
		this.setDao(deliveryOvertimeCnfMapper);
		this.deliveryOvertimeCnfMapper = deliveryOvertimeCnfMapper;
	}

	public Map<String, Object> getDeliveryOvertimeCnf(String address) {
		Date date = new Date();
		Integer deliveryOvertime = null;
		Date deliveryDate = null;
		boolean deliveryDateFlag = true;
		DeliveryOvertimeSpecialCnfExample deliveryOvertimeSpecialCnfExample = new DeliveryOvertimeSpecialCnfExample();
		deliveryOvertimeSpecialCnfExample.createCriteria().andDelFlagEqualTo("0")
				.andBeginPayDateLessThanOrEqualTo(date)
				.andEndPayDateGreaterThanOrEqualTo(date);
		List<DeliveryOvertimeSpecialCnf> deliveryOvertimeSpecialCnfList = deliveryOvertimeSpecialCnfMapper.selectByExample(deliveryOvertimeSpecialCnfExample);
		if(deliveryOvertimeSpecialCnfList.size() > 0) {
			DeliveryOvertimeSpecialCnf deliveryOvertimeSpecialCnf = deliveryOvertimeSpecialCnfList.get(0);
			DeliveryOvertimeSpecialCnfAreaExample deliveryOvertimeSpecialCnfAreaExample = new DeliveryOvertimeSpecialCnfAreaExample();
			deliveryOvertimeSpecialCnfAreaExample.createCriteria().andDelFlagEqualTo("0")
					.andSpecialCnfIdEqualTo(deliveryOvertimeSpecialCnf.getId());
			List<DeliveryOvertimeSpecialCnfArea> deliveryOvertimeSpecialCnfAreaList = deliveryOvertimeSpecialCnfAreaMapper.selectByExample(deliveryOvertimeSpecialCnfAreaExample);
			for(DeliveryOvertimeSpecialCnfArea deliveryOvertimeSpecialCnfArea : deliveryOvertimeSpecialCnfAreaList) {
				if(address.contains(deliveryOvertimeSpecialCnfArea.getAddress())) {
					String timeType = deliveryOvertimeSpecialCnf.getTimeType();
					if(timeType.equals("0")){
						deliveryOvertime = deliveryOvertimeSpecialCnf.getOvertime();
						if(deliveryOvertime != null){
							deliveryDate = DateUtil.addHour(date, deliveryOvertime);
						}
					}else if(timeType.equals("1")){
						deliveryDate = deliveryOvertimeSpecialCnf.getDeliveryDate();
					}
					deliveryDateFlag = false;
					break;
				}
			}
		}
		if(deliveryDateFlag) {
			DeliveryOvertimeCnfExample deliveryOvertimeCnfExample = new DeliveryOvertimeCnfExample();
			deliveryOvertimeCnfExample.createCriteria().andDelFlagEqualTo("0").andBeginDateLessThanOrEqualTo(date).andEndDateGreaterThanOrEqualTo(date);
			deliveryOvertimeCnfExample.setOrderByClause("id desc");
			List<DeliveryOvertimeCnf> deliveryOvertimeCnfs = selectByExample(deliveryOvertimeCnfExample);
			if(CollectionUtils.isNotEmpty(deliveryOvertimeCnfs)){
				DeliveryOvertimeCnf deliveryOvertimeCnf = deliveryOvertimeCnfs.get(0);
				String timeType = deliveryOvertimeCnf.getTimeType();
				if(timeType.equals("0")){
					deliveryOvertime = deliveryOvertimeCnf.getOvertime();
					if(deliveryOvertime != null){
						deliveryDate = DateUtil.addHour(date, deliveryOvertime);
					}
				}else if(timeType.equals("1")){
					deliveryDate = deliveryOvertimeCnf.getDeliveryDate();
				}
			}
		}
		if(deliveryDate == null){
			deliveryOvertime = OrderService.DELIVERY_OVER_TIME;
			deliveryDate = DateUtil.addHour(date, deliveryOvertime);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("deliveryDate", deliveryDate);
		map.put("deliveryOvertime", deliveryOvertime);
		return map;
	}
	
}
