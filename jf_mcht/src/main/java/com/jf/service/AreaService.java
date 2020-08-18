package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.AreaMapper;
import com.jf.entity.Area;
import com.jf.entity.AreaExample;
import com.jf.entity.MchtContact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class AreaService extends BaseService<Area,AreaExample> {
	@Autowired
	private AreaMapper AreaMapper;
	
	@Autowired
	public void setAreaMapper(AreaMapper AreaMapper) {
		super.setDao(AreaMapper);
		this.AreaMapper = AreaMapper;
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
		List<Area> areaList = AreaMapper.selectByExample(areaExample);
		return areaList;
	}
}
