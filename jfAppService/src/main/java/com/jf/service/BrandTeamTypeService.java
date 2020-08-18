package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.BrandTeamTypeMapper;
import com.jf.entity.BrandTeamType;
import com.jf.entity.BrandTeamTypeExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BrandTeamTypeService extends BaseService<BrandTeamType, BrandTeamTypeExample> {
	@Autowired
	private BrandTeamTypeMapper brandTeamTypeMapper;
	@Autowired
	public void setBrandTeamTypeMapper(BrandTeamTypeMapper brandTeamTypeMapper) {
		this.setDao(brandTeamTypeMapper);
		this.brandTeamTypeMapper = brandTeamTypeMapper;
	}
	
	public List<BrandTeamType> findModelsById(Integer brandTeamTypeId) {
		BrandTeamTypeExample brandTeamTypeExample = new BrandTeamTypeExample();
		brandTeamTypeExample.createCriteria().andIdEqualTo(brandTeamTypeId).andStatusEqualTo("1").andDelFlagEqualTo("0");
		return selectByExample(brandTeamTypeExample);
	}

	public List<BrandTeamType> findModels() {
		BrandTeamTypeExample teamTypeExample = new BrandTeamTypeExample();
		teamTypeExample.createCriteria().andStatusEqualTo("1").andDelFlagEqualTo("0");
		teamTypeExample.setOrderByClause("ifnull(seq_no,99999),id desc");
		return brandTeamTypeMapper.selectByExample(teamTypeExample);
	}
	
	
}
