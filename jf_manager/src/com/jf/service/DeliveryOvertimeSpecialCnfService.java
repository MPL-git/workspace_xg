package com.jf.service;

import com.jf.common.utils.StringUtils;
import com.jf.dao.DeliveryOvertimeSpecialCnfAreaMapper;
import com.jf.dao.DeliveryOvertimeSpecialCnfMapper;
import com.jf.entity.DeliveryOvertimeSpecialCnf;
import com.jf.entity.DeliveryOvertimeSpecialCnfArea;
import com.jf.entity.DeliveryOvertimeSpecialCnfAreaExample;
import com.jf.entity.DeliveryOvertimeSpecialCnfExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author Pengl
 * @create 2020-02-26 上午 11:17
 */

@Service
@Transactional
public class DeliveryOvertimeSpecialCnfService extends BaseService<DeliveryOvertimeSpecialCnf, DeliveryOvertimeSpecialCnfExample> {

	@Autowired
	private DeliveryOvertimeSpecialCnfMapper deliveryOvertimeSpecialCnfMapper;

	@Autowired
	private DeliveryOvertimeSpecialCnfAreaMapper deliveryOvertimeSpecialCnfAreaMapper;

	@Autowired
	public void setDeliveryOvertimeSpecialCnfMapper(DeliveryOvertimeSpecialCnfMapper deliveryOvertimeSpecialCnfMapper) {
		super.setDao(deliveryOvertimeSpecialCnfMapper);
		this.dao = deliveryOvertimeSpecialCnfMapper;
	}

	public void updateDeliveryOvertimeSpecialCnf(DeliveryOvertimeSpecialCnf deliveryOvertimeSpecialCnf) {
		String address = deliveryOvertimeSpecialCnf.getRemarks();
		deliveryOvertimeSpecialCnf.setRemarks(null);
		DeliveryOvertimeSpecialCnf specialCnf = deliveryOvertimeSpecialCnfMapper.selectByPrimaryKey(deliveryOvertimeSpecialCnf.getId());
		deliveryOvertimeSpecialCnf.setCreateBy(specialCnf.getCreateBy());
		deliveryOvertimeSpecialCnf.setCreateDate(specialCnf.getCreateDate());
		deliveryOvertimeSpecialCnf.setRemarks(specialCnf.getRemarks());
		deliveryOvertimeSpecialCnf.setDelFlag(specialCnf.getDelFlag());
		if("0".equals(deliveryOvertimeSpecialCnf.getTimeType())) {
			deliveryOvertimeSpecialCnf.setDeliveryDate(null);
		}else {
			deliveryOvertimeSpecialCnf.setOvertime(null);
		}
		deliveryOvertimeSpecialCnfMapper.updateByPrimaryKey(deliveryOvertimeSpecialCnf);
		DeliveryOvertimeSpecialCnfAreaExample deliveryOvertimeSpecialCnfAreaExample = new DeliveryOvertimeSpecialCnfAreaExample();
		deliveryOvertimeSpecialCnfAreaExample.createCriteria()
				.andDelFlagEqualTo("0")
				.andSpecialCnfIdEqualTo(deliveryOvertimeSpecialCnf.getId());
		DeliveryOvertimeSpecialCnfArea deliveryOvertimeSpecialCnfArea = new DeliveryOvertimeSpecialCnfArea();
		deliveryOvertimeSpecialCnfArea.setDelFlag("1");
		deliveryOvertimeSpecialCnfAreaMapper.updateByExampleSelective(deliveryOvertimeSpecialCnfArea, deliveryOvertimeSpecialCnfAreaExample);
		eidtDeliveryOvertimeSpecialCnfArea(deliveryOvertimeSpecialCnf, address, deliveryOvertimeSpecialCnf.getUpdateBy());
	}

	public void insertDeliveryOvertimeSpecialCnf(DeliveryOvertimeSpecialCnf deliveryOvertimeSpecialCnf) {
		String address = deliveryOvertimeSpecialCnf.getRemarks();
		deliveryOvertimeSpecialCnf.setRemarks(null);
		deliveryOvertimeSpecialCnfMapper.insertSelective(deliveryOvertimeSpecialCnf);
		eidtDeliveryOvertimeSpecialCnfArea(deliveryOvertimeSpecialCnf, address, deliveryOvertimeSpecialCnf.getCreateBy());
	}

	public void eidtDeliveryOvertimeSpecialCnfArea(DeliveryOvertimeSpecialCnf deliveryOvertimeSpecialCnf, String address, Integer staffId) {
		if(!StringUtils.isEmpty(address)) {
			Date date = new Date();
			String[] addressStrA = address.split("-");
			for(String addressStr : addressStrA) {
				if(!StringUtils.isEmpty(addressStr)) {
					String[] addressStrsB =  addressStr.split(",");
					DeliveryOvertimeSpecialCnfArea deliveryOvertimeSpecialCnfArea = null;
					if(!"id".equals(addressStrsB[0])) {
						deliveryOvertimeSpecialCnfArea = deliveryOvertimeSpecialCnfAreaMapper.selectByPrimaryKey(Integer.parseInt(addressStrsB[0]));
						deliveryOvertimeSpecialCnfArea.setProvince(null);
						deliveryOvertimeSpecialCnfArea.setCity(null);
						deliveryOvertimeSpecialCnfArea.setCounty(null);
					}else {
						deliveryOvertimeSpecialCnfArea = new DeliveryOvertimeSpecialCnfArea();
					}
					deliveryOvertimeSpecialCnfArea.setSpecialCnfId(deliveryOvertimeSpecialCnf.getId());
					String[] areaId = addressStrsB[1].split("\\.");
					if(!"province".equals(areaId[0])) {
						deliveryOvertimeSpecialCnfArea.setProvince(Integer.parseInt(areaId[0]));
					}
					if(!"city".equals(areaId[1])) {
						deliveryOvertimeSpecialCnfArea.setCity(Integer.parseInt(areaId[1]));
					}
					if(!"county".equals(areaId[2])) {
						deliveryOvertimeSpecialCnfArea.setCounty(Integer.parseInt(areaId[2]));
					}
					deliveryOvertimeSpecialCnfArea.setAddress(addressStrsB[2]);
					if(!StringUtils.isEmpty(deliveryOvertimeSpecialCnfArea.getId())) {
						deliveryOvertimeSpecialCnfArea.setUpdateBy(staffId);
						deliveryOvertimeSpecialCnfArea.setUpdateDate(date);
						deliveryOvertimeSpecialCnfArea.setDelFlag("0");
						deliveryOvertimeSpecialCnfAreaMapper.updateByPrimaryKey(deliveryOvertimeSpecialCnfArea);
					}else {
						deliveryOvertimeSpecialCnfArea.setCreateBy(staffId);
						deliveryOvertimeSpecialCnfArea.setCreateDate(date);
						deliveryOvertimeSpecialCnfAreaMapper.insertSelective(deliveryOvertimeSpecialCnfArea);
					}
				}
			}
		}
	}

	public void delDeliveryOvertimeSpecialCnf(DeliveryOvertimeSpecialCnf deliveryOvertimeSpecialCnf) {
		deliveryOvertimeSpecialCnfMapper.updateByPrimaryKeySelective(deliveryOvertimeSpecialCnf);
		DeliveryOvertimeSpecialCnfAreaExample deliveryOvertimeSpecialCnfAreaExample = new DeliveryOvertimeSpecialCnfAreaExample();
		deliveryOvertimeSpecialCnfAreaExample.createCriteria()
				.andDelFlagEqualTo("0")
				.andSpecialCnfIdEqualTo(deliveryOvertimeSpecialCnf.getId());
		DeliveryOvertimeSpecialCnfArea deliveryOvertimeSpecialCnfArea = new DeliveryOvertimeSpecialCnfArea();
		deliveryOvertimeSpecialCnfArea.setUpdateBy(deliveryOvertimeSpecialCnf.getUpdateBy());
		deliveryOvertimeSpecialCnfArea.setUpdateDate(deliveryOvertimeSpecialCnf.getUpdateDate());
		deliveryOvertimeSpecialCnfArea.setDelFlag("1");
		deliveryOvertimeSpecialCnfAreaMapper.updateByExampleSelective(deliveryOvertimeSpecialCnfArea, deliveryOvertimeSpecialCnfAreaExample);
	}


}
