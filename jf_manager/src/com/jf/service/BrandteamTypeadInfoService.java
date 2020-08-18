package com.jf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jf.dao.BrandteamTypeadInfoCustomMapper;
import com.jf.dao.BrandteamTypeadInfoMapper;
import com.jf.entity.BrandteamTypeadInfo;
import com.jf.entity.BrandteamTypeadInfoCustom;
import com.jf.entity.BrandteamTypeadInfoCustomExample;
import com.jf.entity.BrandteamTypeadInfoExample;

@Service
public class BrandteamTypeadInfoService extends BaseService<BrandteamTypeadInfo, BrandteamTypeadInfoExample> {

	@Autowired
	private BrandteamTypeadInfoMapper brandteamTypeadInfoMapper;
	
	@Autowired
	private BrandteamTypeadInfoCustomMapper brandteamTypeadInfoCustomMapper;
	
	@Autowired
	public void setBrandteamTypeadInfoMapper(BrandteamTypeadInfoMapper brandteamTypeadInfoMapper) {
		super.setDao(brandteamTypeadInfoMapper);
		this.brandteamTypeadInfoMapper = brandteamTypeadInfoMapper;
	}
	
	public Integer brandteamTypeadInfoCountByExample(BrandteamTypeadInfoCustomExample example) {
		return brandteamTypeadInfoCustomMapper.brandteamTypeadInfoCountByExample(example);
	}

	public List<BrandteamTypeadInfoCustom> brandteamTypeadInfoCustomselectByExample(BrandteamTypeadInfoCustomExample example) {
		return brandteamTypeadInfoCustomMapper.brandteamTypeadInfoCustomselectByExample(example);
	}
	
	public BrandteamTypeadInfoCustom brandteamTypeadInfoCustomselectByPrimaryKey(Integer id) {
		return brandteamTypeadInfoCustomMapper.brandteamTypeadInfoCustomselectByPrimaryKey(id);
	}
	
}
