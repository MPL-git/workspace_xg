package com.jf.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.AreaMapper;
import com.jf.entity.Area;
import com.jf.entity.AreaExample;
import com.jf.entity.MchtContact;
import com.jf.entity.PlatformContractReturnLis;

@Service
@Transactional
public class AreaService extends BaseService<Area,AreaExample> {
	@Autowired
	private AreaMapper areaMapper;
	
	@Autowired
	public void setAreaMapper(AreaMapper areaMapper) {
		super.setDao(areaMapper);
		this.areaMapper = areaMapper;
	}
	
	/**
	 * 获取省市区名称
	 */
	public List<Area> getAddress(MchtContact contact){
		Integer county = contact.getCounty();
		Integer province = contact.getProvince();
		Integer city = contact.getCity();
		List<Integer> areaIdList = Arrays.asList(county,province,city);
		AreaExample areaExample = new AreaExample();
		com.jf.entity.AreaExample.Criteria criteria2 = areaExample.createCriteria();
		criteria2.andAreaIdIn(areaIdList);
		List<Area> areaList = areaMapper.selectByExample(areaExample);
		return areaList;
	}
}
