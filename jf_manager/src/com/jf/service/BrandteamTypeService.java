package com.jf.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jf.dao.BrandteamTypeCustomMapper;
import com.jf.dao.BrandteamTypeMapper;
import com.jf.dao.DecorateAreaMapper;
import com.jf.dao.DecorateInfoMapper;
import com.jf.entity.BrandteamType;
import com.jf.entity.BrandteamTypeCustom;
import com.jf.entity.BrandteamTypeCustomExample;
import com.jf.entity.BrandteamTypeExample;
import com.jf.entity.DecorateArea;
import com.jf.entity.DecorateInfo;
import com.jf.entity.DecorateInfoExample;

@Service
public class BrandteamTypeService extends BaseService<BrandteamType, BrandteamTypeExample> {

	@Autowired
	private BrandteamTypeMapper brandteamTypeMapper;
	
	@Autowired
	private BrandteamTypeCustomMapper brandteamTypeCustomMapper;
	
	@Autowired
	private DecorateInfoMapper decorateInfoMapper;
	@Autowired
	private DecorateAreaMapper decorateAreaMapper;
	
	@Autowired
	public void setBrandteamTypeMapper(BrandteamTypeMapper brandteamTypeMapper) {
		super.setDao(brandteamTypeMapper);
		this.brandteamTypeMapper = brandteamTypeMapper;
	}
	
	public Integer brandteamTypeCountByExample(BrandteamTypeCustomExample example) {
		return brandteamTypeCustomMapper.brandteamTypeCountByExample(example);
	}

	public List<BrandteamTypeCustom> brandteamTypeCustomselectByExample(BrandteamTypeCustomExample example) {
		return brandteamTypeCustomMapper.brandteamTypeCustomselectByExample(example);
	}
	
	public BrandteamTypeCustom brandteamTypeCustomselectByPrimaryKey(Integer id) {
		return brandteamTypeCustomMapper.brandteamTypeCustomselectByPrimaryKey(id);
	}
	
	public void update(BrandteamType brandteamType) {//更新
		this.updateByPrimaryKeySelective(brandteamType);
		DecorateInfo di = new DecorateInfo();
		di.setDecorateName(brandteamType.getName());
		di.setUpdateDate(new Date());
		DecorateInfoExample example = new DecorateInfoExample();
		example.createCriteria().andIdEqualTo(brandteamType.getDecorateInfoId());
		decorateInfoMapper.updateByExampleSelective(di, example);
	}
	
	
	public void save(DecorateInfo di, DecorateArea da, BrandteamType brandteamType) {//插入装修信息ID
		decorateInfoMapper.insertSelective(di);
		da.setDecorateInfoId(di.getId());
		decorateAreaMapper.insertSelective(da);
		brandteamType.setDecorateInfoId(di.getId());
		this.insertSelective(brandteamType);
	}
	
}
