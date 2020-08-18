package com.jf.service;

import com.jf.common.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.ToutiaoCampaignInfoMapper;
import com.jf.entity.ToutiaoCampaignInfo;
import com.jf.entity.ToutiaoCampaignInfoExample;

@Service
@Transactional
public class ToutiaoCampaignInfoService extends BaseService<ToutiaoCampaignInfo, ToutiaoCampaignInfoExample> {

	@Autowired
	private ToutiaoCampaignInfoMapper toutiaoCampaignInfoMapper;
	
	@Autowired
	public void setToutiaoCampaignInfoMapper(ToutiaoCampaignInfoMapper toutiaoCampaignInfoMapper) {
		super.setDao(toutiaoCampaignInfoMapper);
		this.toutiaoCampaignInfoMapper = toutiaoCampaignInfoMapper;
	}
	
	
}
