package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.ProvinceFreightCustomMapper;
import com.jf.dao.ProvinceFreightMapper;
import com.jf.entity.ProvinceFreight;
import com.jf.entity.ProvinceFreightCustom;
import com.jf.entity.ProvinceFreightExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class ProvinceFreightService extends BaseService<ProvinceFreight,ProvinceFreightExample> {
	@Autowired
	private ProvinceFreightMapper dao;
	
	@Autowired
	private ProvinceFreightCustomMapper provinceFreightCustomMapper;
	
	@Autowired
	public void setProvinceFreightMapper(ProvinceFreightMapper provinceFreightMapper) {
		super.setDao(provinceFreightMapper);
		this.dao = provinceFreightMapper;
	}
	
	public List<ProvinceFreightCustom> selectByCustomExample(ProvinceFreightExample e){
		return provinceFreightCustomMapper.selectByExample(e);
	}

	public void save(ProvinceFreight provinceFreight) {
		if(provinceFreight.getId()!=null){
			this.updateByPrimaryKeySelective(provinceFreight);
		}else{
			this.insertSelective(provinceFreight);
		}
	}

	public String getAreaIds(HashMap<String, Object> map) {
		return provinceFreightCustomMapper.getAreaIds(map);
	}
}
