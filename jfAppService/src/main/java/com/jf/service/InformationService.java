package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.InformationMapper;
import com.jf.entity.Information;
import com.jf.entity.InformationExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class InformationService extends BaseService<Information, InformationExample> {
	@Autowired
	private InformationMapper informationMapper;
	
	public static final int NOVA_PLAN_AGREEMENT_ID = 102;//醒购新星计划任务邀请人数
	@Autowired
	public void setInformationMapper(InformationMapper informationMapper) {
		this.setDao(informationMapper);
		this.informationMapper = informationMapper;
	}
	public List<Information> getNovaPlanAgreement(Integer catalogId) {
		InformationExample informationExample = new InformationExample();
		informationExample.createCriteria().andCatalogIdEqualTo(catalogId).andStatusEqualTo("1").andDelFlagEqualTo("0");
		informationExample.setOrderByClause("id desc");
		informationExample.setLimitStart(0);
		informationExample.setLimitSize(1);
		return selectByExample(informationExample);
	}
	public List<Information> selectByExampleWithBLOBs(InformationExample informationExample) {
		return informationMapper.selectByExampleWithBLOBs(informationExample);
	}
	
	
}
