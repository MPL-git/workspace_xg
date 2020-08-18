package com.jf.service;

import java.util.List;

import com.jf.common.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.InformationMapper;
import com.jf.entity.Information;
import com.jf.entity.InformationExample;

@Service
@Transactional
public class InformationService extends BaseService<Information, InformationExample> {
	@Autowired
	private InformationMapper informationMapper;
	@Autowired
	public void setInformationMapper(InformationMapper informationMapper) {
		this.setDao(informationMapper);
		this.informationMapper = informationMapper;
	}
	public List<Information> selectByExampleWithBLOBs(InformationExample informationExample) {
		
		return informationMapper.selectByExampleWithBLOBs(informationExample);
	}
	public List<Information> getNovaPlanAgreement(Integer catalogId) {
		InformationExample informationExample = new InformationExample();
		informationExample.createCriteria().andCatalogIdEqualTo(catalogId).andStatusEqualTo("1").andDelFlagEqualTo("0");
		informationExample.setOrderByClause("id desc");
		informationExample.setLimitStart(0);
		informationExample.setLimitSize(1);
		return selectByExample(informationExample);
	}
	
}
