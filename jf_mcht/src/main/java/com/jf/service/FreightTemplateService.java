package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.FreightTemplateCustomMapper;
import com.jf.dao.FreightTemplateMapper;
import com.jf.entity.FreightTemplate;
import com.jf.entity.FreightTemplateCustom;
import com.jf.entity.FreightTemplateExample;
import com.jf.entity.ProvinceFreight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FreightTemplateService extends BaseService<FreightTemplate,FreightTemplateExample> {
	@Autowired
	private FreightTemplateMapper dao;
	@Autowired
	private FreightTemplateCustomMapper freightTemplateCustomMapper;
	@Autowired
	private ProvinceFreightService provinceFreightService;
	
	@Autowired
	public void setFreightTemplateMapper(FreightTemplateMapper freightTemplateMapper) {
		super.setDao(freightTemplateMapper);
		this.dao = freightTemplateMapper;
	}

	public void save(FreightTemplate freightTemplate,List<ProvinceFreight> provinceFreights) {
		if(freightTemplate.getId()!=null){
			this.updateByPrimaryKeySelective(freightTemplate);
		}else{
			this.insertSelective(freightTemplate);
		}
		for(ProvinceFreight provinceFreight:provinceFreights){
			provinceFreight.setFreightTemplateId(freightTemplate.getId());
			provinceFreightService.insertSelective(provinceFreight);
		}
	}

	public List<FreightTemplateCustom> selectCustomByExample(FreightTemplateExample e) {
		return freightTemplateCustomMapper.selectByExample(e);
	}

}
