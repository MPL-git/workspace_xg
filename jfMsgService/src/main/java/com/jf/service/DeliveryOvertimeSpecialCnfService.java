package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.*;
import com.jf.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Pengl
 * @create 2020-02-28 上午 9:31
 */
@Service
@Transactional
public class DeliveryOvertimeSpecialCnfService extends BaseService<DeliveryOvertimeSpecialCnf, DeliveryOvertimeSpecialCnfExample> {

	@Autowired
	private DeliveryOvertimeSpecialCnfMapper deliveryOvertimeSpecialCnfMapper;

	@Autowired
	private DeliveryOvertimeSpecialCnfCustomMapper deliveryOvertimeSpecialCnfCustomMapper;

	@Autowired
	private DeliveryOvertimeSpecialCnfAreaMapper deliveryOvertimeSpecialCnfAreaMapper;

	@Autowired
	private DeliveryOvertimeBackupsMapper deliveryOvertimeBackupsMapper;

	@Autowired
	public void setDeliveryOvertimeSpecialCnfMapper(DeliveryOvertimeSpecialCnfMapper deliveryOvertimeSpecialCnfMapper) {
		this.setDao(deliveryOvertimeSpecialCnfMapper);
		this.deliveryOvertimeSpecialCnfMapper = deliveryOvertimeSpecialCnfMapper;
	}

	public void specialAreaDeliveryLastDate() {
		DeliveryOvertimeSpecialCnfExample deliveryOvertimeSpecialCnfExample = new DeliveryOvertimeSpecialCnfExample();
		deliveryOvertimeSpecialCnfExample.createCriteria().andDelFlagEqualTo("0");
		List<DeliveryOvertimeSpecialCnf> deliveryOvertimeSpecialCnfList = deliveryOvertimeSpecialCnfMapper.selectByExample(deliveryOvertimeSpecialCnfExample);
		for(DeliveryOvertimeSpecialCnf deliveryOvertimeSpecialCnf : deliveryOvertimeSpecialCnfList) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("beginPayDate", deliveryOvertimeSpecialCnf.getBeginPayDate());
			map.put("endPayDate", deliveryOvertimeSpecialCnf.getEndPayDate());
			map.put("timeType", deliveryOvertimeSpecialCnf.getTimeType());
			map.put("overtime", deliveryOvertimeSpecialCnf.getOvertime());
			map.put("deliveryDate", deliveryOvertimeSpecialCnf.getDeliveryDate());
			DeliveryOvertimeSpecialCnfAreaExample deliveryOvertimeSpecialCnfAreaExample = new DeliveryOvertimeSpecialCnfAreaExample();
			deliveryOvertimeSpecialCnfAreaExample.createCriteria().andDelFlagEqualTo("0")
					.andSpecialCnfIdEqualTo(deliveryOvertimeSpecialCnf.getId());
			List<DeliveryOvertimeSpecialCnfArea> deliveryOvertimeSpecialCnfAreaList = deliveryOvertimeSpecialCnfAreaMapper.selectByExample(deliveryOvertimeSpecialCnfAreaExample);
			List<String> addressList = new ArrayList<String>();
			for(DeliveryOvertimeSpecialCnfArea deliveryOvertimeSpecialCnfArea : deliveryOvertimeSpecialCnfAreaList) {
				addressList.add(deliveryOvertimeSpecialCnfArea.getAddress());
			}
			if(addressList.size() > 0) {
				map.put("addressList", addressList);
				deliveryOvertimeSpecialCnfCustomMapper.updateDeliveryOvertimeBackups(map);
				deliveryOvertimeSpecialCnfCustomMapper.insertDeliveryOvertimeBackups(map);
				deliveryOvertimeSpecialCnfCustomMapper.specialAreaDeliveryLastDate(map);
			}
		}
	}

	public void deliveryLastDate(Date bPayDate, Date ePayDate, Date dDate) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("bPayDate", bPayDate);
		paramMap.put("ePayDate", ePayDate);
		DeliveryOvertimeSpecialCnfExample deliveryOvertimeSpecialCnfExample = new DeliveryOvertimeSpecialCnfExample();
		deliveryOvertimeSpecialCnfExample.createCriteria().andDelFlagEqualTo("0")
			.andBeginPayDateLessThanOrEqualTo(ePayDate)
			.andEndPayDateGreaterThanOrEqualTo(bPayDate);
		List<DeliveryOvertimeSpecialCnf> deliveryOvertimeSpecialCnfList = deliveryOvertimeSpecialCnfMapper.selectByExample(deliveryOvertimeSpecialCnfExample);
		List<String> addressList = new ArrayList<String>();
		List<Map<String, Object>> addressMapList = new ArrayList<Map<String, Object>>();
		for(DeliveryOvertimeSpecialCnf deliveryOvertimeSpecialCnf : deliveryOvertimeSpecialCnfList) {
			DeliveryOvertimeSpecialCnfAreaExample deliveryOvertimeSpecialCnfAreaExample = new DeliveryOvertimeSpecialCnfAreaExample();
			deliveryOvertimeSpecialCnfAreaExample.createCriteria().andDelFlagEqualTo("0")
					.andSpecialCnfIdEqualTo(deliveryOvertimeSpecialCnf.getId());
			List<DeliveryOvertimeSpecialCnfArea> deliveryOvertimeSpecialCnfAreaList = deliveryOvertimeSpecialCnfAreaMapper.selectByExample(deliveryOvertimeSpecialCnfAreaExample);
			for(DeliveryOvertimeSpecialCnfArea deliveryOvertimeSpecialCnfArea : deliveryOvertimeSpecialCnfAreaList) {
				addressList.add(deliveryOvertimeSpecialCnfArea.getAddress());
			}
			if(addressList.size() > 0) {
				Map<String, Object> addressMap = new HashMap<String, Object>();
				addressMap.put("beginPayDate", deliveryOvertimeSpecialCnf.getBeginPayDate());
				addressMap.put("endPayDate", deliveryOvertimeSpecialCnf.getEndPayDate());
				addressMap.put("addressList", addressList);
				addressMapList.add(addressMap);
			}
		}
		if(addressMapList.size() > 0) {
			paramMap.put("addressMapList", addressMapList);
		}
		List<Map<String, Object>> subOrderList = deliveryOvertimeSpecialCnfCustomMapper.deliveryOvertimeSubOrderList(paramMap);
		Date date = new Date();
		for(Map<String, Object> subOrderMap : subOrderList) {
			Integer subOrderId = Integer.parseInt(subOrderMap.get("sub_order_id").toString());
			Date deliveryLastDate = subOrderMap.get("delivery_last_date")==null?null:(Date)subOrderMap.get("delivery_last_date");
			Integer deliveryOvertime = subOrderMap.get("delivery_overtime")==null?null:Integer.parseInt(subOrderMap.get("delivery_overtime").toString());

			DeliveryOvertimeBackupsExample deliveryOvertimeBackupsExample = new DeliveryOvertimeBackupsExample();
			deliveryOvertimeBackupsExample.createCriteria().andDelFlagEqualTo("0").andSubOrderIdEqualTo(subOrderId);
			List<DeliveryOvertimeBackups> deliveryOvertimeBackupsList = deliveryOvertimeBackupsMapper.selectByExample(deliveryOvertimeBackupsExample);
			if(deliveryOvertimeBackupsList.size() > 0) {
				DeliveryOvertimeBackups deliveryOvertimeBackups = deliveryOvertimeBackupsList.get(0);
				deliveryOvertimeBackups.setDeliveryLastDate(deliveryLastDate);
				deliveryOvertimeBackups.setDeliveryOvertime(deliveryOvertime);
				deliveryOvertimeBackups.setUpdateDate(date);
				deliveryOvertimeBackupsMapper.updateByPrimaryKey(deliveryOvertimeBackups);
			}else {
				DeliveryOvertimeBackups deliveryOvertimeBackups = new DeliveryOvertimeBackups();
				deliveryOvertimeBackups.setSubOrderId(subOrderId);
				deliveryOvertimeBackups.setDeliveryLastDate(deliveryLastDate);
				deliveryOvertimeBackups.setDeliveryOvertime(deliveryOvertime);
				deliveryOvertimeBackups.setCreateBy(1);
				deliveryOvertimeBackups.setCreateDate(date);
				deliveryOvertimeBackupsMapper.insertSelective(deliveryOvertimeBackups);
			}

			Map<String, Object> soMap = new HashMap<String, Object>();
			soMap.put("id", subOrderId);
			soMap.put("deliveryLastDate", dDate);
			deliveryOvertimeSpecialCnfCustomMapper.updateSubOrder(soMap);
		}
	}


}
